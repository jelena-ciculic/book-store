
var bookStoreApp = angular.module('bookStoreAppControllers',[]);


bookStoreApp.controller('homeCtrl',function($scope,homeFactory){

	

	$scope.greating = homeFactory.getMsg();
})


bookStoreApp.controller('top10Ctrl',function($scope,booksFactory){

	$scope.topList = [];

	$scope.getBooks = function(){

		booksFactory.top10List().then(function(resp){

			console.log('top10: ' ,resp);
			$scope.topList = resp.data;
		})
	}

	$scope.getBooks();
})

bookStoreApp.controller('booksCtrl',function($scope,$http,$location,booksFactory,authorsFactory,publishersFactory,suportFactory){


	$scope.currentPage = 0;
	$scope.totalPages = 0;
	$scope.books=[];
	$scope.authors=[];
	$scope.publishers=[];
	$scope.filterBook={};
	$scope.addBookMode=false;
	$scope.filterBookMode = false;
	$scope.delBook={};

	

	$scope.addMode = function(){

		$scope.addBookMode = suportFactory.changeMode($scope.addBookMode);
	}

	$scope.filterMode  = function(){

		$scope.filterBookMode = suportFactory.changeMode($scope.filterBookMode);
	}

	$scope.cancelAdd = function(){

		$scope.newBook = {};
		$scope.addBookMode = suportFactory.changeMode($scope.addBookMode);

	}

	$scope.prepForDel = function(book){

		$scope.delBook = angular.copy(book);
	}

	$scope.cancelFilter = function(){
		
				$scope.filterBook = {};
				$scope.filterBookMode = suportFactory.changeMode($scope.filterBookMode);
				$scope.loadBooks();
			
		
			}

	$scope.loadBooks = function(){

		var config = {params:{}};

		config.params.page =$scope.currentPage;

		if($scope.filterBook.title != ""){
            config.params.title = $scope.filterBook.title;
		}
		
		if($scope.filterBook.author != ""){
            config.params.author = $scope.filterBook.author;
		}
		
		if($scope.filterBook.minVotes != ""){
            config.params.minVotes = $scope.filterBook.minVotes;
		}
		
		booksFactory.getAll(config).then(function(resp){

			console.log('resp_books',resp)
			$scope.books = resp.data;
			$scope.totalPages = Number(resp.headers().totalpages);
		})
	
	}

	$scope.loadBooks();

	$scope.loadAuthors = function(){
	
		authorsFactory.getAll().then(function(resp){

			console.log('resp_authors', resp);
			$scope.authors = resp.data;
		})
	}

	$scope.loadAuthors();

	$scope.loadPublishers = function(){
	
		publishersFactory.getAll().then(function(resp){

			console.log('resp_publishers', resp);
			$scope.publishers = resp.data;
		})
	}

	$scope.loadPublishers();


	$scope.forward = function(){
        if($scope.currentPage > 0) {
            $scope.currentPage = $scope.currentPage - 1;
            $scope.loadBooks();
        }
    };

    $scope.backward = function(){
        if($scope.currentPage < $scope.totalPages - 1){
            $scope.currentPage = $scope.currentPage + 1;
            $scope.loadBooks();
        }
	};
	
	$scope.removeBook = function(book){

		booksFactory.remove(book.id).then(function(){

			$scope.loadBooks();

		})
	}

	$scope.addBook = function(book){

		booksFactory.add(book).then(function(){

			$scope.newBook = {};
			$scope.addBookMode=suportFactory.changeMode($scope.addBookMode);
			$scope.loadBooks();
		})
	}

	$scope.prepAuthor = function(author){

		
		return suportFactory.prepLoadAuthors(author);
		
	}

	$scope.prepPublisher  = function(publisher){

		return suportFactory.prepLoadPublishers(publisher);
	}


	$scope.maxVotes = function(){
		
				var promise = booksFactory.maxVotes();
				promise.then(function(resp){
					console.log('resp_maxVotes:', resp);
					$scope.maxVoteBook = resp.data;
				})
	}
		
	$scope.maxVotes();

	$scope.vote = function(book){

		booksFactory.addVote(book).then(function(){

			$scope.loadBooks();
			$scope.maxVotes();
		})
	}

	$scope.prepForEdit = function(id){

		$location.path('/books/' + id);
	}

	$scope.searchBook = function(){

		$scope.loadBooks();
		$scope.filterBook={};
	}

});


bookStoreApp.controller('editBookCtrl',function($scope,$http,$location,$routeParams,booksFactory,authorsFactory,publishersFactory,suportFactory){

	$scope.book = null;
	$scope.authors = [];
	$scope.publishers=[];
	$scope.editMode = false;

	$scope.getBook = function(){
	
		booksFactory.getOne($routeParams.id).then(function(resp){

			console.log('resp: ', resp);
			$scope.book=resp.data;
		})
	}


	$scope.getBook();

	
	$scope.loadAuthors = function(){

			authorsFactory.getAll().then(function(resp){

				console.log('resp_authors:',resp);
				$scope.authors=resp.data;
			})
	}
		
	$scope.loadAuthors();
		
			$scope.loadPublishers = function(){

				publishersFactory.getAll().then(function(resp){
					console.log('resp_publishers:',resp)
					$scope.publishers=resp.data;
				})
				
			
	}
		
	$scope.loadPublishers();

	$scope.prepAuthor = function(author){
		
				return suportFactory.prepLoadAuthors(author);
	}
		
	$scope.prepPublisher  = function(publisher){
		
				return suportFactory.prepLoadPublishers(publisher);
	}

	$scope.changeMode=function(){

		$scope.editMode=suportFactory.changeMode($scope.editMode);
		$scope.editBook=suportFactory.copyData($scope.book);
		
	}

	$scope.editData = function(b){

		booksFactory.edit(b).then(function(){

			$scope.getBook();
			$scope.editBook={};
			$scope.editMode=suportFactory.changeMode($scope.editMode);
			//$location.path('/books')


		})
	}

});

bookStoreApp.controller('authorsCtrl',function($scope,$http,$location,authorsFactory,booksFactory,suportFactory){

	$scope.authors=[];
	$scope.booksByAuthor=[];
	$scope.searchAuthor={};
	$scope.newAuthor={};
	$scope.updateAuthor={};
	$scope.delAuth={};


	$scope.loadAuthors = function(){

		var config = {params:{}};
		
		if($scope.searchAuthor.name != ""){
			config.params.name = $scope.searchAuthor.name
		}

		authorsFactory.getAll(config).then(function(resp){

			console.log('resp: ',resp)
			$scope.authors=resp.data;
		})
					 

	}

	$scope.loadAuthors();

	$scope.loadAuthorBooks= function(id){

		authorsFactory. getAuthorBooks(id).then(function(resp){

			$scope.booksByAuthor=resp.data;
		})
	}

	$scope.filterAuthor = function(){

		$scope.loadAuthors();
		$scope.searchAuthor={};
	}

	$scope.addAuthor = function(a){

		authorsFactory.add(a).then(function(){

			$scope.loadAuthors();
			$scope.newAuthor = {};
		})
	}

	$scope.seeDetails = function(id){

		$location.path('/authors/' +id);

	}

	$scope.prepForUpdate  = function(a){
		$scope.updateAuthor = suportFactory.copyData(a);
	}

	$scope.editAuthor = function(author){

		authorsFactory.edit(author).then(function(){
			$scope.loadAuthors();
			$scope.updateAuthor={};
		})
	}

	$scope.cancelUpdateAuthor = function(){
		$scope.updateAuthor={};
	}

	$scope.prepForDel = function(a){
		$scope.delAuth = suportFactory.copyData(a);
	}

	$scope.deleteAuthor = function(id){

		authorsFactory.remove(id).then(function(){

			$scope.delAuth={};
			$scope.loadAuthors();
		})
	}

});

bookStoreApp.controller('editAuthorCtrl',function($scope,$routeParams,authorsFactory,booksFactory,publishersFactory,suportFactory){

	$scope.author=null;
	$scope.books=[];
	$scope.publishers=[];
	$scope.newBook={};
	$scope.delBook={};

	$scope.loadAuthor=function(){

		authorsFactory.getOne($routeParams.id).then(function(resp){
			console.log('resp_author:', resp)
			$scope.author=resp.data;
		})	

	}

	$scope.loadAuthor();

	$scope.loadBooks = function(){

		authorsFactory.getAuthorBooks($routeParams.id).then(function(resp){

			$scope.books = resp.data;
		})
	}

	$scope.loadBooks();

	$scope.loadPublishers = function(){
		
				var config = {params:{}};		
		
				publishersFactory.getAll(config).then(function(resp){
		
					console.log('resp_pub: ', resp);
					$scope.publishers=resp.data;
	})
	}

	$scope.prepPublisher = function(publisher){

		return suportFactory.prepLoadPublishers(publisher);
	}

	$scope.addBook = function(book){
		authorsFactory.addBook(book,$routeParams.id).then(function(){

			$scope.loadBooks();
			$scope.newBook = {};
		})
	}


});

bookStoreApp.controller('publishersCtrl',function($scope,$location,publishersFactory,suportFactory){

	$scope.publishers=[];
	$scope.booksByPublisher=[];
	$scope.filterPublisher={};
	$scope.newPublisher = {};
	$scope.editPub={};
	$scope.delPub={};

	$scope.loadPublishers = function(){

		var config = {params:{}};
		
		if($scope.filterPublisher.name != ""){
			config.params.name = $scope.filterPublisher.name;
		}
				

		publishersFactory.getAll(config).then(function(resp){

			console.log('resp_pub: ', resp);
			$scope.publishers=resp.data;
		})
	}

	$scope.loadPublishers();


	$scope.loadBooks = function(id){

		publishersFactory.getPublisherBooks(id).then(function(resp){

			console.log('resp_books: ',resp);
			$scope.booksByPublisher=resp.data;
		})

	}

	$scope.search = function(){
		$scope.loadPublishers();
		$scope.filterPublisher={};
	}

	$scope.addPublisher = function(pub){

		publishersFactory.add(pub).then(function(){
			$scope.newPublisher={};
			$scope.loadPublishers();
			
		})
	}



	$scope.details=function(id){

		$location.path('/publishers/' + id);

	}

	$scope.prepForUpdate = function(pub){

		$scope.editPub = suportFactory.copyData(pub);

	}


	$scope.editPublisher = function(pub){

		publishersFactory.edit(pub).then(function(){
			$scope.editPub={};
			$scope.loadPublishers();
		})
	}


	$scope.prepForDel = function(p){
		$scope.delPub = suportFactory.copyData(p);
	}

	$scope.deletePublisher = function(id){

		publishersFactory.remove(id).then(function(){
				
			$scope.loadPublishers();
			$scope.delPub={};

		})
	}
})


bookStoreApp.controller('editPubCtrl', function($scope,$routeParams,publishersFactory, authorsFactory,suportFactory){

	$scope.publisher={};
	$scope.books = [];
	$scope.authors=[];
	$scope.delBook={};
	$scope.newBook={};
	

	$scope.loadPublisher=function(){

		publishersFactory.getOne($routeParams.id).then(function(resp){
			console.log('resp:', resp);
			$scope.publisher=resp.data;

		})		

	}

	$scope.loadPublisher();

	$scope.loadBooks = function(){

		publishersFactory.getPublisherBooks($routeParams.id)
						.then(function(resp){

						console.log('resp: ',resp);
						$scope.books = resp.data;
		})

		
	}

	$scope.loadBooks();

	$scope.loadAuthors=function(){

		var config = {params:{}};

		authorsFactory.getAll().then(function(resp){
			console.log('authors:', resp);
			$scope.authors = resp.data;
		})
	}

	$scope.prepAuthor = function(author){
		
				
		return suportFactory.prepLoadAuthors(author);
				
	}


	$scope.clearData = function(){
		$scope.delBook={};
	}

	$scope.addBook = function(book){

		publishersFactory.addBook(book,$routeParams.id).then(function(){

			$scope.loadBooks();
			$scope.newBook={};
		})

	}

	

	

})








	

