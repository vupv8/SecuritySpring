<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Login</title>
</head>
<body>
	<h1>Login</h1>

	<!-- /login?error=true -->
	<c:if test="${param.error == 'true'}">
		<div style="color: red; margin: 10px 0px;">

			Login Failed!!!<br /> Reason :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

		</div>
	</c:if>
	<form name='f'
		action="${pageContext.request.contextPath}/j_spring_security_check"
		method='POST' class="was-validated">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		
		<p>User:</p>
		<input type='text' name='username' value=''>
		<p>Password:</p>
		<input type='password' name='password' /> <input name="submit"
			type="submit" value="submit" />

	</form>
</body>
</html>