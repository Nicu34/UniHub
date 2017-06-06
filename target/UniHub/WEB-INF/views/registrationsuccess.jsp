<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Registration Confirmation Page</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>

	<!-- Chat ref -->
	<link rel="stylesheet" type="text/css" media="screen" href="https://cdn.conversejs.org/css/converse.min.css">
	<script src="https://cdn.conversejs.org/dist/converse.min.js"></script>
</head>
<body>
	<div class="generic-container">
		<%@include file="authheader.jsp" %>
		
		<div class="alert alert-success lead">
	    	${success}
		</div>
		
		<span class="well floatRight">
			Go to <a href="<c:url value='/list' />">Users List</a>
		</span>
	</div>
</body>
<!-- Chat initializer -->
<script>
	converse.initialize({
		bosh_service_url: 'https://conversejs.org/http-bind/',
		show_controlbox_by_default: true,
		allow_registration: false,
		locked_domain: '007jabber.com',
		hide_muc_server: true
	});
</script>
</html>