<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>Task ${message(code: taskid) }</title>

</head>
<body>
	<h1>
		Booked Time:
		${message(code: timeBudgetPlan)}
		Available Time:
		${message(code: timeBudgetPlan-bookedTime)}
	</h1>
	<div>
		<g:form useToken="true" method="post" action="showBookings">

			<table>
				<thead>
					<tr>
						<th>
							${message(code: 'Booking.delete.label', default: 'to delete')}
						</th>
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

					<g:hiddenField name="taskid" value="${taskid}" />

					<g:each in="${bookings}" var="mapEntry">
						<g:hiddenField name="changed" value="${false}" />

						<tr>
							<td><g:checkBox name="toDelete" value="${mapEntry.id}"
									checked="${false}" /></td>
							<td><g:field name="date" type="text"
									value="${mapEntry.date.format('dd.MM.yyyy')}" size="10"
									pattern="${~/\d\d\.\d\d\.\d\d\d\d/}" readonly="${true}" /></td>
							<td><g:field name="amount" type="number"
									value="${mapEntry.amount}" min="0"
									 max="100000" size="6"
									 oninput="this.style.background='yellow';"
									onchange=" this.style.background='yellow';" /></td>
							<td><g:field name="start" type="text"
									value="${mapEntry.start.format('dd.MM.yyyy')}" size="10"
									oninput="this.style.background='yellow';"
									pattern="${~/\d\d\.\d\d\.\d\d\d\d/}" /></td>
							<td><g:field name="end" type="text"
									value="${mapEntry.end.format('dd.MM.yyyy')}" size="10"
									oninput="this.style.background='yellow';"
									pattern="${~/\d\d\.\d\d\.\d\d\d\d/}" /></td>
							<g:hiddenField name="id" value="${mapEntry.id}" />
							<g:hiddenField name="isNew" value="${false}" />

						</tr>
					</g:each>

					<tr>
						<g:render template="newBookingsline" />	
						<g:render template="hiddenBookingsline" />												
					</tr>
					

				</tbody>

			</table>

			<fieldset class="buttons">
				<g:actionSubmit class="return" action="show"
					value="${message(code: 'default.button.return.label', default: 'back to Projekt')}" />
				<g:link class="save" action="showParent" id="${taskid}">
					${message(code: 'default.button.save.label', default: 'back to Compundtask')}
				</g:link>
				<g:actionSubmit class="save" action="updateBookings"
					value="${message(code: 'default.button.save.label', default: 'Update Bookings')}" />
			</fieldset>


		</g:form>

	</div>

</body>
</html>
