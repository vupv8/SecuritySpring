<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<h1><c:out escapeXml="true" value="${message}" /></h1>
<h3>User Infor Page</h3>
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
	<form:form method="POST" action="/SecuritySpring/user/saveinfo"
		modelAttribute="userInfo" class="was-validated">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<td><form:hidden path="userid" /></td>

		<p>Username :</p>
		<form:input path="username" disable="disabled" />
		<c:if
			test="${pageContext.request.userPrincipal.name == userInfo.username}">
			<td>Password :</td>
			<td><form:input path="password" value="" /></td>
		</c:if>

		<p>Fullname :</p>
		<form:input path="fullname" disable="disabled" />
		<p>Email :</p>
		<form:input path="email" />
		<p>Phone :</p>
		<form:input path="phone" />
		<c:if
			test="${pageContext.request.userPrincipal.name == userInfo.username}">
			<input type="submit" value="Edit Save" />
		</c:if>
		<c:if
			test="${pageContext.request.userPrincipal.name != userInfo.username}">
			<a
				href="${pageContext.request.contextPath}/user/message/${userInfo.userid}">Message</a>
		</c:if>
	</form:form>

	<a href="${pageContext.request.contextPath}/">Back</a>
</security:authorize>

