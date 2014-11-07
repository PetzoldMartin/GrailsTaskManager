<%@page import="de.fh_zwickau.pti.tbpv2.TaskService"%>
<%@page import="org.apache.tools.ant.Task"%>
<%@ page import="de.fh_zwickau.pti.tbpv2.Task" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>TimeManager</title>
</head>
<body>
  <div class="body">
  <h1>Time Manager</h1>

<!-- Current date -->  
<g:set var="now" value="${new Date()}" />
${now }
  
<!-- g:each in="${myMapNameInGSP}" var="item" -->
   
   <p>${myMapNameInGSP}</p>
<!--  /g:each -->
  
  <table>
  	<thead> 
  	  <tr>
  	    <th>${message(code: 'Task') }</th>
  	    <th>${message(code: 'budged') }</th>
  	    <th>${message(code: 'gebucht') }</th>
  	  </tr>
  	</thead>
  	<tbody>
  	  <g:each in="${myMapNameInGSP.taskInfos}" var="mapEntry">
  	    <tr>
  	  	  <td><g:link action="showBookings" id="${mapEntry.getAt('id')}"> ${mapEntry.getAt('name')}</g:link></td>
  	  	  <td>${mapEntry.getAt('timeBudgetPlan')}</td>
  	  	  <td>${mapEntry.getAt('timeBudgetUsed')}</td>
  	    </tr>
  	  </g:each>
  	</tbody>
  	
  </table>
  </div>
</body>
</html>
