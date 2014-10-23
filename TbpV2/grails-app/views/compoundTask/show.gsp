
<%@ page import="de.fh_zwickau.pti.tbpv2.CompoundTask" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'compoundTask.label', default: 'CompoundTask')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-compoundTask" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-compoundTask" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list compoundTask">
			
				<g:if test="${compoundTaskInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="compoundTask.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${compoundTaskInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${compoundTaskInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="compoundTask.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${compoundTaskInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${compoundTaskInstance?.superTask}">
				<li class="fieldcontain">
					<span id="superTask-label" class="property-label"><g:message code="compoundTask.superTask.label" default="Super Task" /></span>
					
						<span class="property-value" aria-labelledby="superTask-label"><g:link controller="compoundTask" action="show" id="${compoundTaskInstance?.superTask?.id}">${compoundTaskInstance?.superTask?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${compoundTaskInstance?.plans}">
				<li class="fieldcontain">
					<span id="plans-label" class="property-label"><g:message code="compoundTask.plans.label" default="Plans" /></span>
					
						<g:each in="${compoundTaskInstance.plans}" var="p">
						<span class="property-value" aria-labelledby="plans-label"><g:link controller="timePlanning" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${compoundTaskInstance?.subtasks}">
				<li class="fieldcontain">
					<span id="subtasks-label" class="property-label"><g:message code="compoundTask.subtasks.label" default="Subtasks" /></span>
					
						<g:each in="${compoundTaskInstance.subtasks}" var="s">
						<span class="property-value" aria-labelledby="subtasks-label"><g:link controller="task" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:compoundTaskInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${compoundTaskInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
