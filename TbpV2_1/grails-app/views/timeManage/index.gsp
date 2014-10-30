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
  <table>
  	<thead> 
  	  <tr>
  	    <th>${message(code: 'Task') }</th>
  	    <th>${message(code: 'budged') }</th>
  	    <th>${message(code: 'gebucht') }</th>
  	  </tr>
  	</thead>
  	<tbody>
  	  <g:each in="${Task.getAll()}" var="mapEntry">
  	    <tr>
  	  	  <td>${mapEntry.getAt('name')}</td>
  	  	  <td>${mapEntry.getTimeBudgetPlan()}</td>
  	  	
  	    </tr>
  	  </g:each>
  	</tbody>
  	
  </table>
  </div>
</body>
</html>