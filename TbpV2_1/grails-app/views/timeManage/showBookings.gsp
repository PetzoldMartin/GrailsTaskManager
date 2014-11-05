<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
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
  	  	  <td>${mapEntry.date}</td>
  	  	  <td>${mapEntry.amount}</td>
  	  	  <td>${mapEntry.start}</td>
  	  	  <td>${mapEntry.end}</td>
  	    </tr>
  	  </g:each>
  	</tbody>
  	
  </table>
  </div>
</body>
</html>