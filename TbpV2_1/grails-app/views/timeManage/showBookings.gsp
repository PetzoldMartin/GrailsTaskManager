<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>Task ${message(code: taskid) }</title>
</head>
<body>
	<div>
		<g:form params="[taskid: taskid,date: date,start: start,end: end,amount: amount]">
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
						<th>
							
						</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${bookings}" var="mapEntry">
						<tr>
							<td>
								${mapEntry.date.format('dd.MM.yyyy')}
							</td>
							<td>
								${mapEntry.amount}
							</td>
							<td>
								${mapEntry.start.format('dd.MM.yyyy')}
							</td>
							<td>
								${mapEntry.end.format('dd.MM.yyyy')}
							</td>
							<th class= "buttons">
							<g:actionSubmit class="delete" action="delete"  
								value="${message(code: 'default.button.delete.label', default: 'x')}"  
							/>
						</th>
						</tr>	
					</g:each>
					<tr>
						<g:render template="editBookingsline"/>
					</tr>
				</tbody>

			</table>
			<fieldset class="buttons">
			<g:actionSubmit class="return" action="index"  
					value="${message(code: 'default.button.return.label', default: 'back to Projekt')}"  
					/>	
				<g:actionSubmit class="save" action="updateBookings"  
					value="${message(code: 'default.button.save.label', default: 'Save new Booking')}"  
					/>	
			</fieldset>

		</g:form>
	</div>

</body>
</html>