<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>


<h1><c:out escapeXml="true" value="${message}" /></h1>
<h3>User Management Page</h3>
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
	<table class="table table-bordered">
		<tr>
			<th>STT</th>
			<th>Username</th>
			<th>Fullname</th>
			<th>Email</th>
			<th>Phone</th>
			<th>Role</th>
			<th>Status</th>
			<th colspan="2">Action</th>

		</tr>
		<c:set var="stt" value="0" />
		<c:forEach items="${listAllInfoUser}" var="InfoUser">
			<tr>
				<td>${stt=stt+1}</td>
				<td><c:out escapeXml="true" value="${InfoUser.username}" /></td>
				<td><c:out escapeXml="true" value="${InfoUser.fullname}" /></td>
				<td><c:out escapeXml="true" value="${InfoUser.email}" /></td>
				<td><c:out escapeXml="true" value="${InfoUser.phone}" /></td>
				<td><c:out escapeXml="true" value="${InfoUser.role}" /></td>
				<td><c:out escapeXml="true" value="${InfoUser.enabled}" /></td>
				<td><a
					href="${pageContext.request.contextPath}/admin/edituser/${InfoUser.userid}">
						Edit</a></td>
				<td><form:form method="POST"
						action="/SecuritySpring/admin/deluser/${InfoUser.userid}">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<input type="submit" value="Del User"
							onclick="return confirm('Are you sure you want to Delete?');" />
					</form:form></td>
			</tr>
		</c:forEach>
	</table>

	<form:form method="GET" action="/SecuritySpring/admin/adduser"
		modelAttribute="infoUser">
		<input type="submit" value="Add User" />
	</form:form>
</security:authorize>

