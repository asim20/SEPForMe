<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>HelloWorld Admin page</title>
</head>
	<script type="text/javascript">
  	// Create the XHR object.
	function createCORSRequest(method, url) {
	var xhr = new XMLHttpRequest();
	if ("withCredentials" in xhr) {
  		// XHR for Chrome/Firefox/Opera/Safari.
  		xhr.open(method, url, true);
	} else if (typeof XDomainRequest != "undefined") {
  		// XDomainRequest for IE.
  		xhr = new XDomainRequest();
  		xhr.open(method, url);
	} else {
  	// CORS not supported.
  	xhr = null;
	}
	return xhr;
	}

// Helper method to parse the title tag from the response.
function getTitle(text) {
	var x=document.cookie;
	console.log('asdasdas');
	if(document.cookie){
		console.log(x);
	}
return x;
}

// Make the actual CORS request.
function getSomething() {
// This is a sample server that supports CORS.
var url = 'http://localhost:8080/RolePages/login';

var xhr = createCORSRequest('GET', url);
if (!xhr) {
  alert('CORS not supported');
  return;
}

// Response handlers.
xhr.onload = function() {
  var text = xhr.responseText;
  var title = getTitle(text);
  alert('Response from CORS request to ' + url + ': ' + title);
};

xhr.onerror = function() {
  alert('Woops, there was an error making the request.');
};

xhr.send();
}
  </script>
<body>
    Dear <strong>${user}</strong>, Welcome to Admin Page.
    <a href="<c:url value="/logout" />">Logout</a><br>
    Get User Data: <button onclick="getSomething()" type="button">Get Data</button>
</body>
</html>