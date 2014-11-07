<%@ page import="de.fh_zwickau.spv.ExamResult" %>



<div class="fieldcontain ${hasErrors(bean: examResultInstance, field: 'grade', 'error')} required">
	<label for="grade">
		<g:message code="examResult.grade.label" default="Grade" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="grade" value="${fieldValue(bean: examResultInstance, field: 'grade')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: examResultInstance, field: 'exam', 'error')} required">
	<label for="exam">
		<g:message code="examResult.exam.label" default="Exam" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="exam" name="exam.id" from="${de.fh_zwickau.spv.Exam.list()}" optionKey="id" required="" value="${examResultInstance?.exam?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: examResultInstance, field: 'student', 'error')} required">
	<label for="student">
		<g:message code="examResult.student.label" default="Student" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="student" name="student.id" from="${de.fh_zwickau.spv.Student.list()}" optionKey="id" required="" value="${examResultInstance?.student?.id}" class="many-to-one"/>

</div>

