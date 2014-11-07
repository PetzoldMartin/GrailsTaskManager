
<%@ page import="de.fh_zwickau.spv.ExamResult" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'examResult.label', default: 'ExamResult')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-examResult" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<sec:ifAnyGranted roles="ROLE_LECTURER">
					<li>
						<g:link class="create" action="create">
							<g:message code="default.new.label" args="[entityName]" />
						</g:link>
					</li>
				</sec:ifAnyGranted>
			</ul>
		</div>
		<div id="show-examResult" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list examResult">
			
				<g:if test="${examResultInstance?.grade}">
				<li class="fieldcontain">
					<span id="grade-label" class="property-label"><g:message code="examResult.grade.label" default="Grade" /></span>
					
						<span class="property-value" aria-labelledby="grade-label"><g:fieldValue bean="${examResultInstance}" field="grade"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${examResultInstance?.exam}">
				<li class="fieldcontain">
					<span id="exam-label" class="property-label"><g:message code="examResult.exam.label" default="Exam" /></span>
					
						<span class="property-value" aria-labelledby="exam-label">
							<g:link controller="exam" action="show" id="${examResultInstance?.exam?.id}">
								${examResultInstance?.exam?.subject?.name} @ ${examResultInstance?.exam?.date.format('dd.MM.yyyy')}
							</g:link>
						</span>
					
				</li>
				</g:if>
			
				<g:if test="${examResultInstance?.student}">
				<li class="fieldcontain">
					<span id="student-label" class="property-label"><g:message code="examResult.student.label" default="Student" /></span>
					
						<span class="property-value" aria-labelledby="student-label"><g:link controller="student" action="show" id="${examResultInstance?.student?.id}">${examResultInstance?.student?.name}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<sec:ifAnyGranted roles="ROLE_LECTURER">
				<g:form url="[resource:examResultInstance, action:'delete']" method="DELETE">
					<fieldset class="buttons">
						<g:link class="edit" action="edit" resource="${examResultInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</fieldset>
				</g:form>
			</sec:ifAnyGranted>
		</div>
	</body>
</html>
