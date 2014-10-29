<%@ page import="de.fh_zwickau.pti.tbpv2.Task" %>
<%@ page import="de.fh_zwickau.pti.tbpv2.CompoundTask" %>
<%@ page import="de.fh_zwickau.pti.tbpv2.SubTask" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="Task" />
		<g:set var="key" value="${listkey}"/>
		<title><g:message code="default.tree.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-task" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<g:if test="${!Task.count}">
				<li><g:link class="create" action="genTestData" controller="compoundTask"><g:message default="Testdaten anlegen" code="default.testdata.label" /></g:link></li>
				</g:if>
				<g:else>
				<li><g:link class="create" action="delTestData" controller="compoundTask"><g:message default="Testdaten löschen" code="default.notestdata.label" /></g:link></li>
				</g:else>
			</ul>
		</div>
		<div id="list-task" class="content scaffold-list" role="main">
			<h1><g:message code="default.tree.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<th>${message(code: 'task.name.label', default: 'Name')}</th>
					
						<th>${message(code: 'task.description.label', default: 'Description')}</th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tasklist}" status="i" var="mapEntry">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<g:set var="lev" value="${mapEntry.level}" />
						<td>${lev>0?('....'*(lev-1) + '+---'):''}<g:link action="show" id="${mapEntry.task.id}">${fieldValue(bean: mapEntry.task, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: mapEntry.task, field: "description")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${taskInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
