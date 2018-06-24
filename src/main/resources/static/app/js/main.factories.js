var bookStoreApp = angular.module('bookStoreAppFactories',[]);

bookStoreApp.factory('homeFactory',function(){

    var homeFactoryObj = {

        getMsg:getMsg
    };


  return homeFactoryObj;

    function getMsg (){

        return "Hello!Welcome to Bookstore-app!";
    };


});

bookStoreApp.factory('booksFactory',function($http,suportFactory){

    var base_url = '/api/books'
    var listBooks = [];

    var factoryObj = {

        getAll:getAll,
        getOne:getOne,
        remove:remove,
        add:add,
        edit:edit,
        addVote:addVote,
        maxVotes:maxVotes,
        top10List:top10List
        
    }

    return factoryObj;

    function getAll(config){

        return $http.get(base_url, config)
                    .then(suportFactory.getDataSuccess,suportFactory.getDataError);
    }
  
    function getOne(id){

        return $http.get(base_url+'/'+id)
                    .then(suportFactory.getDataSuccess,suportFactory.getDataError);
    }

    function remove(id){

        return $http.delete(base_url+ '/'+id);
    }

    function add(book){

        return $http.post(base_url,book);
    }

    function edit(book){
        return $http.put(base_url+'/'+book.id,book);
    }
    function addVote(book){

        return $http.put(base_url+'/'+book.id+'/vote',book);
    }

    function maxVotes(){

        return $http.get(base_url+'/max_votes')
                    .then(suportFactory.getDataSuccess,suportFactory.getDataError);
    }

    function top10List(){

        return $http.get(base_url+'/top_10')
                    .then(suportFactory.getDataSuccess,suportFactory.getDataError);
    }

})

bookStoreApp.factory('authorsFactory',function($http,suportFactory){

    var base_url='/api/authors';

    var factoryObj = {

        getAll:getAll,
        getOne:getOne,
        getAuthorBooks:getAuthorBooks,
        add:add,
        edit:edit,
        remove:remove,
        addBook:addBook,
        
    }

    return factoryObj;

    function getAll(config){

        return $http.get(base_url,config)
                    .then(suportFactory.getDataSuccess,suportFactory.getDataError);

    }

    function getOne(id){

        return $http.get(base_url+ '/'+id)
                    .then(suportFactory.getDataSucces,suportFactory.getDataError);
    }

    function add(author){

        return $http.post(base_url,author);
    }


    function edit(author){

        return $http.put(base_url+'/'+author.id, author);
    }

    function remove(id){

        return $http.delete(base_url+'/'+id);
    }


    function addBook(book, id){
        return $http.post(base_url+'/'+id+'/new_book', book);
    }


    function getAuthorBooks(id){

        return $http.get(base_url+'/'+ id +'/books')
                    .then(suportFactory.getDataSuccess,suportFactory.getDataError);
    }

  
});

bookStoreApp.factory('publishersFactory',function($http,suportFactory){

    var base_url = '/api/publishers'

    var factoryObj = {

        getAll:getAll,
        getOne:getOne,
        getPublisherBooks:getPublisherBooks,
        add:add,
        edit:edit,
        remove:remove,
        addBook:addBook
      
    }

    return factoryObj;

    function getAll(config){

        return $http.get(base_url,config)
                    .then(suportFactory.getDataSuccess,suportFactory.getDataError);
    }

    function getOne(id){

        return $http.get(base_url+ '/'+id)
                    .then(suportFactory.getDataSuccess,suportFactory.getDataError);
    }


    function add(publisher){

        return $http.post(base_url,publisher);

    }

    function edit(publisher){
        return $http.put(base_url+'/'+publisher.id, publisher);
    }

    function remove(id){
        return  $http.delete(base_url+'/'+id);
    }

    function getPublisherBooks(id){

        return $http.get(base_url+'/'+id+'/books')
                    .then(suportFactory.getDataSuccess,suportFactory.getDataError);
    }

    function addBook(book, id){
        return $http.post(base_url+'/'+id+'/new_book', book);
    }


    

});


bookStoreApp.factory('suportFactory',function(){

    factoryObj = {
        getDataSuccess:getDataSuccess,
        getDataError:getDataError,
        prepLoadAuthors:prepLoadAuthors,
        prepLoadPublishers:prepLoadPublishers,
        changeMode:changeMode,
        copyData:copyData

    }

    return factoryObj;
    

    function getDataSuccess(resp){
        
                return resp;
    }

    function getDataError(resp){
        
                return alert('Error!Failed to load data!');
    }

    function prepLoadAuthors(a){

        return a.firstName + " " +a.lastName;
    }

    function prepLoadPublishers(p){

        return p.name;
    }

    function changeMode(condition){

        condition=!condition;

        return condition;

    }

    function copyData(data){

        return angular.copy(data);
    }



})

