<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<h1><c:out escapeXml="true" value="${message}" /></h1>
<h1>Add Task Page</h1>
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
	<form:form method="POST" action="/SecuritySpring/admin/addtasksave?${_csrf.parameterName}=${_csrf.token}"
		enctype="multipart/form-data" modelAttribute="task"
		class="was-validated">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<p>Task Name</p>
		<form:input path="taskname" />
		<p>Description</p>
		<form:input path="description" />
		<p>Select a file to upload</p>
		<input type="file" name="file" />
		<input type="submit" value="Submit" class="site-btn" />
	</form:form>

	<a href="${pageContext.request.contextPath}/admin/listtask">Back</a>
</security:authorize>
