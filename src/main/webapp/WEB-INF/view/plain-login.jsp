<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Custom Login Page</title>
<style>
	.failed {
		color: red
	}
</style>
</head>
<h3>My Custom Login Page</h3>
<form:form action="${pageContext.request.contextPath}/authenticateUser"
	method="POST">

	<c:if test="${ param.error != null }">
		<i class="failed">Sorry! Your have entered invalid credentials.</i>
	</c:if>

	<p>
		UserName : <input type="text" name="username" />
	</p>
	<p>
		Password : <input type="password" name="password" />
	</p>
	<input type="submit" value="Submit" />
</form:form>
<body>

</body>
</html>