<%@ page import="org.example.Task" %>



<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'summary', 'error')} required">
	<label for="summary">
		<g:message code="task.summary.label" default="Summary" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="summary" required="" value="${taskInstance?.summary}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'details', 'error')} required">
	<label for="details">
		<g:message code="task.details.label" default="Details" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="details" cols="40" rows="5" maxlength="1000" required="" value="${taskInstance?.details}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'deadline', 'error')} ">
	<label for="deadline">
		<g:message code="task.deadline.label" default="Deadline" />
		
	</label>
	<g:datePicker name="deadline" precision="day"  value="${taskInstance?.deadline}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'timeSpent', 'error')} required">
	<label for="timeSpent">
		<g:message code="task.timeSpent.label" default="Time Spent" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="timeSpent" type="number" min="0" value="${taskInstance.timeSpent}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="task.tags.label" default="Tags" />
		
	</label>
	<g:select name="tags" from="${org.example.Tag.list()}" multiple="multiple" optionKey="id" size="5" value="${taskInstance?.tags*.id}" class="many-to-many"/>

</div>

