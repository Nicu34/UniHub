<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>AccessDenied page</title>

	<!-- Chat ref -->
	<link rel="stylesheet" type="text/css" media="screen" href="https://cdn.conversejs.org/css/converse.min.css">
	<script src="https://cdn.conversejs.org/dist/converse.min.js"></script>
</head>
<body>
	<div class="generic-container">
		<div class="authbar">
			<span>Dear <strong>${loggedinuser}</strong>, You are not authorized to access this page.</span> <span class="floatRight"><a href="<c:url value="/logout" />">Logout</a></span>
		</div>
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