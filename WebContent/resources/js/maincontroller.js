
loginApp.factory('MainService',['$http','$rootScope','$q', function(http,rootScope,q){
	var newService = new MainService(http,rootScope,q);
	return newService;
}]);

loginApp.controller("MainCtrl", function($scope, $http, $location, $q, MainService, $uibModal) {
	$scope.mainService=MainService;
	$scope.user={};
	$scope.message="";
	
			
	if(!$scope.mainService.isInitialised)
	{
		MainService.isInitialised=true;				
		if(!$scope.mainService.isUserLoggedIn){    	
    		$location.path('/');
    	}else {
			$location.path('/dashboard');			
	    	$scope.mainService.isUserLoggedIn=true;
		} 		
		
	}

	
	$scope.login = function(){
			
		$scope.message="";

		var result = $http({
			method : 'POST',
			url : 'login',
			data : JSON.stringify($scope.user),			
		});
		result.success(function(data,status) {
			if(!data.result)
		    {
		    	$location.path('/dashboard');
		    	$scope.mainService.currentUser=data;
		    	$scope.mainService.isUserLoggedIn=true;		    	
		    }
		    else
		    {
		    	if (data.errorMsg == "Authentication Failed"){
		    		$scope.message="Wrong username or password, please try again !";	
		    	} 
		    }
		  });
		result.error(function (data, status){
			console.log('Error Occured with status :'+status);
		});
	};

	
	$scope.logout = function(){
		$location.path('/');
		$sessionService.remove("user")
		$scope.mainService.currentUser=null;
		$scope.mainService.isUserLoggedIn=false;
	};
	
	
});