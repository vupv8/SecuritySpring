<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<h1>${message}</h1>
<h3>Edit User Page</h3>
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
	<form:form method="POST" action="/SecuritySpring/admin/editsave"
		modelAttribute="infoUser" class="was-validated">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<p>Username :</p>
		<form:input path="username" />
		<p>Password :</p>
		<form:input path="password" value="" />
		<p>Fullname :</p>
		<form:input path="fullname" />
		<p>Email :</p>
		<form:input path="email" />
		<p>Phone :</p>
		<form:input path="phone" />
		<p>Role :</p>
		<form:select path="role">
			<form:option value="ADMIN" />
			<form:option value="USER" />
		</form:select>
		<p>Status :</p>
		<form:select path="enabled">
			<form:option value="1" />
			<form:option value="0" />
		</form:select>
		<input type="submit" value="Edit Save" />

	</form:form>
	<a href="${pageContext.request.contextPath}/admin/listallinfouser">Back</a>
</security:authorize>
