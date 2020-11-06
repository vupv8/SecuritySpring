<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<body>
	<h1><c:out escapeXml="true" value="${message}" /></h1>
	<h1>Admin Page</h1>
	<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">

		<h3>Welcome : <c:out escapeXml="true" value="${pageContext.request.userPrincipal.name}" /></h3>

		<b>This is protected page!</b>
	</security:authorize>
</body>
