<%@ page import="de.fh_zwickau.pti.tbpv2.CompoundTask" %>



<div class="fieldcontain ${hasErrors(bean: compoundTaskInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="compoundTask.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="12" required="" value="${compoundTaskInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: compoundTaskInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="compoundTask.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="2000" required="" value="${compoundTaskInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: compoundTaskInstance, field: 'superTask', 'error')} ">
	<label for="superTask">
		<g:message code="compoundTask.superTask.label" default="Super Task" />
		
	</label>
	<g:select id="superTask" name="superTask.id" from="${de.fh_zwickau.pti.tbpv2.CompoundTask.list()}" optionKey="id" value="${compoundTaskInstance?.superTask?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: compoundTaskInstance, field: 'plans', 'error')} ">
	<label for="plans">
		<g:message code="compoundTask.plans.label" default="Plans" />
		
	</label>
	<g:select name="plans" from="${de.fh_zwickau.pti.tbpv2.TimePlanning.list()}" multiple="multiple" optionKey="id" size="5" value="${compoundTaskInstance?.plans*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: compoundTaskInstance, field: 'subtasks', 'error')} ">
	<label for="subtasks">
		<g:message code="compoundTask.subtasks.label" default="Subtasks" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${compoundTaskInstance?.subtasks?}" var="s">
    <li><g:link controller="task" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="task" action="create" params="['compoundTask.id': compoundTaskInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'task.label', default: 'Task')])}</g:link>
</li>
</ul>


</div>

