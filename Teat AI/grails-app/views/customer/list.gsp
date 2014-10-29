<%@ page import="org.example.Customer" %>
<!DOCTYPE html>
<html>
<li><g:link class="list" action="index"><g:message code="show customer" /></g:link></li>

	<table>
    <tr><th>Name</th><th> </th><th>Gender</th></tr>
    <g:each in="${Customer.list()}" var="cust">
        <tr><td>${cust.lastName}<td>${cust.firstName}</td><td>${cust.gender}</td></tr>
    </g:each>
</table>
</html>