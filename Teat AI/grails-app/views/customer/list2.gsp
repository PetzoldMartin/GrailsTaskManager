<%@ page import="org.example.Customer"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>

</head>

<body ng-app ng-controller="customerCtrlGrails">

	<h2>My customer input</h2>

	<form ng-submit="customerAdd()">
		<input type="text" ng-model="addFirstname" size="30"
			placeholder="Add Firstname"> <input type="text"
			ng-model="addLastname" size="30" placeholder="Add Lastname">
		<input type="text" ng-model="AddGender" size="30"
			placeholder="Add Gender"> <input type="submit"
			value="Add to Database">
	</form>

	<li><g:link class="list" action="index">
			<g:message code="show customer" />
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

	<div ng-repeat="x in customerList">
		<input type="checkbox" ng-model="x.done">
		<tr>
			<span ng-bind="x.firstname"></span>
		</tr>
		<tr>
			<span ng-bind="x.lastname"></span>
		</tr>
		<tr>
			<span ng-bind="x.gender"></span>
		</tr>
	</div>

	<p>
		<button ng-click="remove()">Remove marked</button>
	</p>

	<script>
	
var customer = angular.module('customer', []);
customer.controller('customerCtrl',
	    function ($scope, $http) {

	        $scope.getCustomer = function () {
	            $http.get('/myApp/book').
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

	


</body>



</html>