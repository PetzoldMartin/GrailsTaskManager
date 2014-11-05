<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<meta name="layout" content="main"/>
	<style type="text/css">
		textarea.taskedit {
			width: 12em;
			height: 2.5em;
			overflow: auto; /* IE always renders vertical scrollbar without this */
			vertical-align: top;
		}
	</style>
	<title><g:message code="tbp.tasks.edit" default="Tasks bearbeitenen" /></title>
</head>
<body>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
		</ul>
	</div>
		<g:if test="${flash.invalidToken}">
			<div class="message" role="error">Bitte Seite nicht erneut laden!</div>
		</g:if>
	<div class="body">
		<div id="list-task" class="content scaffold-list" role="main">
			<h1><g:message code="tbp.tasks.planninglist" default="Task Planungsliste" /></h1>
			<g:form useToken="true">
				<table>
					<thead>
						<tr>
							<g:render template="editline" model="['kind': 'heading']"></g:render>
						</tr>
					</thead>
					<tbody>
						<g:each in="${taskInfos}" status="i" var="taskInfo">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd' ; loop = i}">
								<g:render template="editline" 
									model="['kind': 'edit', 'taskInfo': taskInfo, 'compoundTasks': compoundTasks, 'compoundTaskMap': compoundTaskMap]" />
							</tr>
						</g:each>
						<g:each in="${1..2}" var="j" >
							<tr>
								<g:render template="editline" 
									model="['kind': 'create','compoundTasks': compoundTasks]" />
							</tr>
						</g:each> 
					</tbody>
				</table>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="updateTasks" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	
	</div>
</body>
</html>