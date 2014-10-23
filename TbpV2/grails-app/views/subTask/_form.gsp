<%@ page import="de.fh_zwickau.pti.tbpv2.SubTask" %>



<div class="fieldcontain ${hasErrors(bean: subTaskInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="subTask.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="12" required="" value="${subTaskInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: subTaskInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="subTask.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="2000" required="" value="${subTaskInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: subTaskInstance, field: 'superTask', 'error')} ">
	<label for="superTask">
		<g:message code="subTask.superTask.label" default="Super Task" />
		
	</label>
	<g:select id="superTask" name="superTask.id" from="${de.fh_zwickau.pti.tbpv2.CompoundTask.list()}" optionKey="id" value="${subTaskInstance?.superTask?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: subTaskInstance, field: 'bookings', 'error')} ">
	<label for="bookings">
		<g:message code="subTask.bookings.label" default="Bookings" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${subTaskInstance?.bookings?}" var="b">
    <li><g:link controller="booking" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="booking" action="create" params="['subTask.id': subTaskInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'booking.label', default: 'Booking')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: subTaskInstance, field: 'plans', 'error')} ">
	<label for="plans">
		<g:message code="subTask.plans.label" default="Plans" />
		
	</label>
	<g:select name="plans" from="${de.fh_zwickau.pti.tbpv2.TimePlanning.list()}" multiple="multiple" optionKey="id" size="5" value="${subTaskInstance?.plans*.id}" class="many-to-many"/>

</div>

