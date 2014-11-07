<%@ page import="de.fh_zwickau.spv.Lecturer" %>



<div class="fieldcontain ${hasErrors(bean: lecturerInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="lecturer.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${lecturerInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: lecturerInstance, field: 'exams', 'error')} ">
	<label for="exams">
		<g:message code="lecturer.exams.label" default="Exams" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${lecturerInstance?.exams?}" var="e">
    <li><g:link controller="exam" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="exam" action="create" params="['lecturer.id': lecturerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'exam.label', default: 'Exam')])}</g:link>
</li>
</ul>


</div>

