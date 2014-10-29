<%@ page import="org.example.Tag" %>



<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="tag.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${tagInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'tasks', 'error')} ">
	<label for="tasks">
		<g:message code="tag.tasks.label" default="Tasks" />
		
	</label>
	

</div>

