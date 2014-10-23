
<%@ page import="de.fh_zwickau.pti.tbpv2.SubTask" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'subTask.label', default: 'SubTask')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-subTask" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-subTask" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list subTask">
			
				<g:if test="${subTaskInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="subTask.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${subTaskInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${subTaskInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="subTask.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${subTaskInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${subTaskInstance?.superTask}">
				<li class="fieldcontain">
					<span id="superTask-label" class="property-label"><g:message code="subTask.superTask.label" default="Super Task" /></span>
					
						<span class="property-value" aria-labelledby="superTask-label"><g:link controller="compoundTask" action="show" id="${subTaskInstance?.superTask?.id}">${subTaskInstance?.superTask?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${subTaskInstance?.bookings}">
				<li class="fieldcontain">
					<span id="bookings-label" class="property-label"><g:message code="subTask.bookings.label" default="Bookings" /></span>
					
						<g:each in="${subTaskInstance.bookings}" var="b">
						<span class="property-value" aria-labelledby="bookings-label"><g:link controller="booking" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${subTaskInstance?.plans}">
				<li class="fieldcontain">
					<span id="plans-label" class="property-label"><g:message code="subTask.plans.label" default="Plans" /></span>
					
						<g:each in="${subTaskInstance.plans}" var="p">
						<span class="property-value" aria-labelledby="plans-label"><g:link controller="timePlanning" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:subTaskInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${subTaskInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
