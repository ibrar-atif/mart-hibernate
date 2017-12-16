
dirApp.controller(
'ProductController',
function($rootScope, $q, $scope, $state, $stateParams,$http) { 
	
	$scope.addProduct = function(){
		
    	$http.post('http://localhost:8080/product-web/' + 'product/new', $scope.newProduct)
    	.then(
    			function(response){    				    				
    				
    				alert("Product Added "+response.data.name )
    			},
    			function(errResponse){
    				return $q.reject(errResponse);
    			}
    	);
    
	}
	
	
	$scope.searchProduct = function(){
    	$http.get('http://localhost:8080/product-web/' + 'product/findbytype/'+ $scope.searchType)
    	.then(
    			function(response){    				    				
    				$scope.productList = response.data;
    			},
    			function(errResponse){
    				return $q.reject(errResponse);
    			}
    	);
    
	}
	
	$scope.deleteProduct = function(pr){
    	$http.delete('http://localhost:8080/product-web/' + 'product/delete/'+ pr.id)
    	.then(
    			function(response){
    				var index = $scope.productList.indexOf(pr);
    				if (index > -1) {
    					$scope.productList.splice(index, 1);
    				}
    			},
    			function(errResponse){
    				return $q.reject(errResponse);
    			}
    	);
    
	}
});