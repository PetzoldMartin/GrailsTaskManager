<%@ page import="de.fh_zwickau.spv.Subject" %>



<div class="fieldcontain ${hasErrors(bean: subjectInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="subject.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${subjectInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: subjectInstance, field: 'exams', 'error')} ">
	<label for="exams">
		<g:message code="subject.exams.label" default="Exams" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${subjectInstance?.exams?}" var="e">
    <li><g:link controller="exam" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="exam" action="create" params="['subject.id': subjectInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'exam.label', default: 'Exam')])}</g:link>
</li>
</ul>


</div>

