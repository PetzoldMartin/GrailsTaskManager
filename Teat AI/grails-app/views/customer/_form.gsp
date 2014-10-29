<%@ page import="org.example.Customer" %>



<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'birthday', 'error')} required">
	<label for="birthday">
		<g:message code="customer.birthday.label" default="Birthday" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="birthday" precision="day"  value="${customerInstance?.birthday}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="customer.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${customerInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'gender', 'error')} required">
	<label for="gender">
		<g:message code="customer.gender.label" default="Gender" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="gender" required="" value="${customerInstance?.gender}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="customer.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${customerInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'maritalStatus', 'error')} required">
	<label for="maritalStatus">
		<g:message code="customer.maritalStatus.label" default="Marital Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="maritalStatus" required="" value="${customerInstance?.maritalStatus}"/>

</div>

