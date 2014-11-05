<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Task ${message(code: taskid) }</title>
</head>
<body>
  <div >
   <table>
  	<thead> 
  	  <tr>
  	    <th>${message(code: 'Booking.date.label', default: 'Bookingdate')}</th>
  	    <th>${message(code: 'Booking.amount.label', default: 'Booked Hours')}</th>
  	    <th>${message(code: 'Booking.start.label', default: 'Startdate')}</th>
  	    <th>${message(code: 'Booking.end.label', default: 'Enddate')}</th>
  	   
  	  </tr>
  	</thead>
  	<tbody>
  	   <g:each in="${bookings}" var="mapEntry">
  	    <tr>
  	  	  <td>${mapEntry.date.format('dd.MM.yyyy')}</td>
  	  	  <td>${mapEntry.amount}</td>
  	  	  <td>${mapEntry.start.format('dd.MM.yyyy')}</td>
  	  	  <td>${mapEntry.end.format('dd.MM.yyyy')}</td>
  	    </tr>
  	  </g:each>
  	  <tr>
  	  <g:render template="editBookingsline"/>
  	  </tr>
  	</tbody>
  	
  </table>
  <fieldset class="buttons">
					<g:actionSubmit class="save" action="updateBookings" test="1" value="${message(code: 'default.button.save.label', default: 'Save new Booking')}" />
	</fieldset>
  </div>
</body>
</html>