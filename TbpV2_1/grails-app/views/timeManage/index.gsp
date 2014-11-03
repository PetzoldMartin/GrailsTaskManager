<%@page import="de.fh_zwickau.pti.tbpv2.TimeManageController"%>

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
  	  <g:each in="${tasks}" var="it">
  	    <tr>
  	  	  <td>${it.getAt('name')}</td>
  	  	  <td>${it.getTimeBudgetPlan()}</td>
  	  	  <td>${}</td>
  	  	  <td>${it.id}</td>
  	  	
  	    </tr>
  	  </g:each>
  	</tbody>
  	
  </table>
  </div>
</body>
</html>