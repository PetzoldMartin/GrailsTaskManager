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
	<div class="body">
		<h1>Time Manager Treeview</h1>
		

		<p><b>Fett</b> = CompoundTask</p>
		<p>Normal = SubTask</p>
		
		<p></p>
		
		<!-- simple debug view -->
		<p>${map}</p>
		
		
		<g:if test = "${map.root != null}">
			<h4>${map.root.name}</h4>
			<p>${map.root.description}</p>
			<g:if test = "${map.root.parent != null}">
				<p>Parent Task: ${map.root.parent}</p>
			</g:if>
		</g:if>

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
	</div>
</body>
</html>
