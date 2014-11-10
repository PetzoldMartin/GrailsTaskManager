<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>SPV REST API Homepage</title>
</head>
<body>
  <div class="body">
  	<h1>Testausgabe der REST API</h1>
  	<h2>Links</h2>
  	<table>
  	<g:each in="${links.keySet()}" var="key">
  		<tr>
  		<td><g:link url="${links.get(key)}${key != home? '?format=xml' : ''}">${key}</g:link></td>
  		</tr>
  	</g:each>
  	</table>
  </div>
</body>
</html>