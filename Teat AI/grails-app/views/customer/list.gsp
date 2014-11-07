<%@ page import="org.example.Customer"%>
<!DOCTYPE html>
<html >
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>

</head>


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

<!-- Anything inside here should use the $scope of CustomerController -->
<body ng-app  ng-controller="CustomerController">
 
    <!-- When this form is submitted, cancel the action and run CustomerController.addCustomer() -->
 
    <table>
        <!-- Do this for every Customer in CustomerController.customers -->
        <tr ng-repeat="customer in customers">
            <td>
                <!-- Call complete( id ) for this one when it's clicked -->
                <input type="checkbox" id="customer.Id"/> 
                 <span ng-bind="customer.firstName"></span>
                 <span ng-bind="customer.lastName"></span>
                 <span ng-bind="customer.gender"></span>
                
            </td>
        </tr>
    </table>
 
</body>

<script>  
function CustomerController( $scope, $http ) {    
    // bindable list of customers
    $scope.customers = []
    // load all customers, copying to the "customers" list on success
    $scope.loadCustomers = function() {
        $http.get("../customer/AjaxList").success( function( data ) {
            $scope.customers = data
        })
    }
    // when we first stat up, load customers
    $scope.loadCustomers()
};
</script>

</html>