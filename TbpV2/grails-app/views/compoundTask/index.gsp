
<%@ page import="de.fh_zwickau.pti.tbpv2.CompoundTask" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'compoundTask.label', default: 'CompoundTask')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-compoundTask" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="list" action="index" controller="task"><g:message code="default.tree.label" args="['Task']" /></g:link></li>
				<g:if test="${!CompoundTask.count}">
				<li><g:link class="create" action="genTestData"><g:message default="Testdaten anlegen" code="default.testdata.label" /></g:link></li>
				</g:if>
				<g:else>
				<li><g:link class="create" action="delTestData"><g:message default="Testdaten löschen" code="default.notestdata.label" /></g:link></li>
				</g:else>
			</ul>
		</div>
		<div id="list-compoundTask" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'compoundTask.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'compoundTask.description.label', default: 'Description')}" />
					
						<th><g:message code="compoundTask.superTask.label" default="Super Task" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${compoundTaskInstanceList}" status="i" var="compoundTaskInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${compoundTaskInstance.id}">${fieldValue(bean: compoundTaskInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: compoundTaskInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: compoundTaskInstance, field: "info")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${compoundTaskInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
