<%@page import="de.fh_zwickau.pti.tbpv2.TaskService"%>
<%@page import="org.apache.tools.ant.Task"%>
<%@ page import="de.fh_zwickau.pti.tbpv2.Task"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>TimeManager</title>
</head>
<body>
	<div class="body" style="margin: 10px">
		<h1>Time Manager Traceview</h1>
	
		<!-- simple debug view -->
		<!--  <p>${map}</p> -->
		
		<!-- Current Task Info -->
		<g:if test = "${map.root != null}">
			<h4>${map.root.name}</h4>
			<div style="margin: 10px">
				<p>${map.root.description}</p>
			</div>
		</g:if>

		<div style="background-color:#f0f0f0; margin-top: 20px; margin-bottom: 10px">
		<!-- Trace Navigation -->
		<p> Parent Trace </p>
		<p>
			<g:each in="${map.trace}" var="item">
				<g:link action="show" id="${item.getAt('id')}"><b>${item.getAt('name')}</b></g:link> / 
			</g:each>
		</p>
		</div>

		<!-- SubTask Table -->
		<table>
			<thead>
				<tr>
					<th>${message(code: 'Tasks')}</th>
					<th>${message(code: 'Description')}</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${map.leafs}" var="leafEntry">
					<tr>
					
						<g:if test = "${leafEntry.getAt('compound')}">
							<td><g:link action="show" id="${leafEntry.getAt('id')}"><b>${leafEntry.getAt('name')}</b></g:link></td>
						</g:if>
							
						<g:else>
							<td><g:link action="showBookings" id="${leafEntry.getAt('id')}">${leafEntry.getAt('name')}</g:link></td>
						</g:else>
							<td>${leafEntry.getAt('description')}</td>
					</tr>
				</g:each>
			</tbody>

		</table>
		<div style="background-color:#f0f0f0;">
			<p><b>Fett</b> = CompoundTask</p>
			<p>Normal = SubTask</p>
		</div>
	</div>
</body>



</html>
