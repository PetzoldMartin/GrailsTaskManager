<%@ page import="org.example.Customer"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>

</head>

<body >

	<li><g:link class="list" action="index">
			<g:message code="show customer" />
		</g:link></li>
		
		<li><g:link class="list" action="list2">
			<g:message code="angeluarsingle" />
		</g:link></li>



	<table>
		<tr>
			<th>Name</th>
			<th></th>
			<th>Gender</th>
		</tr>
		<g:each in="${Customer.list()}" var="cust">
			<tr>
				<td>
					${cust.lastName}
				
				<td>
					${cust.firstName}
				</td>
				<td>
					${cust.gender}
				</td>
			</tr>


		</g:each>
	</table>


	<br>

	
	<script>
	
var customer = angular.module('customer', []);
customer.controller('customerCtrl',
	    function ($scope, $http) {

	        $scope.getCustomer = function () {
	            $http.get('/CustomerRest').
	                success(function (data) {
	                    console.log("success: " + data);
	                    $scope.customer = data;
	                }).error(function (data) {
	                    console.log("error: " + data);
	                    $scope.customer = data;
	                });
	        };

	        $scope.getCustomer();
	    }
	);


</script>

	<div>
		<table class="table table-hover">
			<tr>
				<th>Title</th>
				<th>Author</th>
			</tr>

			<tr ng-repeat="c in customer">
				<td>{{c.firstName}}</td>
				<td>{{c.lastName}}</td>
				
			</tr>
		</table>
	</div>


</body>



</html>