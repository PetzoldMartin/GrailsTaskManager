<%@ page import="org.example.Registration" %>



<div class="fieldcontain ${hasErrors(bean: registrationInstance, field: 'race', 'error')} ">
	<label for="race">
		<g:message code="registration.race.label" default="Race" />
		
	</label>
	<g:select id="race" name="race.id" from="${org.example.Race.list()}" optionKey="id" value="${registrationInstance?.race?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: registrationInstance, field: 'runner', 'error')} ">
	<label for="runner">
		<g:message code="registration.runner.label" default="Runner" />
		
	</label>
	<g:select id="runner" name="runner.id" from="${org.example.Runner.list()}" optionKey="id" value="${registrationInstance?.runner?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: registrationInstance, field: 'paid', 'error')} ">
	<label for="paid">
		<g:message code="registration.paid.label" default="Paid" />
		
	</label>
	<g:checkBox name="paid" value="${registrationInstance?.paid}" />

</div>

