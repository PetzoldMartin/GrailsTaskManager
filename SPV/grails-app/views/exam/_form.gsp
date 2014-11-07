<%@ page import="de.fh_zwickau.spv.Exam" %>



<div class="fieldcontain ${hasErrors(bean: examInstance, field: 'results', 'error')} ">
	<label for="results">
		<g:message code="exam.results.label" default="Results" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${examInstance?.results?}" var="r">
    <li><g:link controller="examResult" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="examResult" action="create" params="['exam.id': examInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'examResult.label', default: 'ExamResult')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: examInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="exam.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${examInstance?.date}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: examInstance, field: 'lecturer', 'error')} required">
	<label for="lecturer">
		<g:message code="exam.lecturer.label" default="Lecturer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="lecturer" name="lecturer.id" from="${de.fh_zwickau.spv.Lecturer.list()}" optionKey="id" required="" value="${examInstance?.lecturer?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: examInstance, field: 'subject', 'error')} required">
	<label for="subject">
		<g:message code="exam.subject.label" default="Subject" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="subject" name="subject.id" from="${de.fh_zwickau.spv.Subject.list()}" optionKey="id" required="" value="${examInstance?.subject?.id}" class="many-to-one"/>

</div>

