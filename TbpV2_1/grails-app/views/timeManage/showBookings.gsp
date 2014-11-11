<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>Task ${message(code: taskid) }</title>

</head>
<body>
<h1>Booked Time: ${message(code: timeBudgetPlan)} Available Time: ${message(code: timeBudgetPlan-bookedTime)}</h1>
	<div>

		<table>
			<thead>
				<tr>
					<th>
						${message(code: 'Booking.date.label', default: 'Bookingdate')}
					</th>
					<th>
						${message(code: 'Booking.amount.label', default: 'Booked Hours')}
					</th>
					<th>
						${message(code: 'Booking.start.label', default: 'Startdate')}
					</th>
					<th>
						${message(code: 'Booking.end.label', default: 'Enddate')}
					</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${bookings}" var="mapEntry">
					<tr>
						<g:form action="showBookings" params="[taskid: taskid,bookingID: bookingID,date: date,start: start,end: end,amount: amount]">
					
						<td><g:field name="date" type="text"
								value="${mapEntry.date.format('dd.MM.yyyy')}" size="10"
								pattern="${~/\d\d\.\d\d\.\d\d\d\d/}" readonly="${true}" />

						</td>
						<td><g:field name="amount" type="number"
								value="${mapEntry.amount}" min="0" max="100000" size="6" /></td>
						<td><g:field name="start" type="text"
								value="${mapEntry.start.format('dd.MM.yyyy')}" size="10"
								pattern="${~/\d\d\.\d\d\.\d\d\d\d/}" /></td>
						<td><g:field name="end" type="text"
								value="${mapEntry.end.format('dd.MM.yyyy')}" size="10"
								pattern="${~/\d\d\.\d\d\.\d\d\d\d/}" /></td>
						<g:hiddenField name="bookingID" value="${mapEntry.id}" />
							<th class="buttons"><g:actionSubmit class="delete"
									action="deleteBookings"
									value="${message(code: 'default.button.delete.label', default: 'x')}" />
							</th>
							<th class="buttons">
							<g:actionSubmit class="save"
									action="changeBookings"
									value="${message(code: 'default.button.update.label', default: 'x')}" />
							</th>
									
						</g:form>
					</tr>
				</g:each>
				<g:form method="post"
					params="[taskid: taskid,date: date,start: start,end: end,amount: amount]"
					action="showBookings">
					<tr>
						<g:render template="newBookingsline" />
						<td class="buttons"><g:actionSubmit class="save"
								action="updateBookings"
								value="${message(code: 'default.button.save.label', default: 'Save Booking')}" />
						</td>
						<td class="buttons">
						</td>
					</tr>
				</g:form>
			</tbody>
		</table>

		<fieldset class="buttons">
			<g:actionSubmit class="return" action="show"
				value="${message(code: 'default.button.return.label', default: 'back to Projekt')}" />
			<g:link class="save" action="showParent" id="${taskid}">
				${message(code: 'default.button.save.label', default: 'back to Compundtask')}
			</g:link>

		</fieldset>



	</div>

</body>
</html>
