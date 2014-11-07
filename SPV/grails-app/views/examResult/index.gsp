
<%@ page import="de.fh_zwickau.spv.ExamResult" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'examResult.label', default: 'ExamResult')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-examResult" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<sec:ifAnyGranted roles="ROLE_LECTURER">
					<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				</sec:ifAnyGranted>
			</ul>
		</div>
		<div id="list-examResult" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="student.name" title="Student" />
						<g:sortableColumn property="exam.subject.name" title="Fach" />
						<g:sortableColumn property="grade" title="${message(code: 'examResult.grade.label', default: 'Zensur')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${examResultInstanceList}" status="i" var="examResultInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>
							<g:link controller="student" action="show" id="${examResultInstance.student.id}">
								${examResultInstance.student.name}
							</g:link>
						</td>
						<td>
							<g:link controller="exam" action="show" id="${examResultInstance.exam.id}">
								${examResultInstance.exam.subject.name}
							</g:link> @ ${examResultInstance.exam.date.format('dd.MM.yy')}
						</td>
						<td>
							<g:link action="show" id="${examResultInstance.id}">
								${fieldValue(bean: examResultInstance, field: "grade")}
							</g:link>
						</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${examResultInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
