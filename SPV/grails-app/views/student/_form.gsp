<%@ page import="de.fh_zwickau.spv.Student" %>



<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="student.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${studentInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'results', 'error')} ">
	<label for="results">
		<g:message code="student.results.label" default="Results" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${studentInstance?.results?}" var="r">
    <li><g:link controller="examResult" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="examResult" action="create" params="['student.id': studentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'examResult.label', default: 'ExamResult')])}</g:link>
</li>
</ul>


</div>

