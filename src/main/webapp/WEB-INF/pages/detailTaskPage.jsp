
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="lable"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<h1>${message}</h1>
<h3>Detail Task Page</h3>
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">


	<p>Task Name :</p>
	<lable name="taskname">${task.taskname}</lable>

	<p>Description :</p>
	<lable>${task.description}</lable>

	<p>Attach File :</p>
	<lable>${task.attachfile}</lable>

	<p>Date Created :</p>
	<lable>${task.datecreated}</lable>

	<p>Creator :</p>
	<lable>${task.username}</lable>

	<a
		href="${pageContext.request.contextPath}/user/download/${task.attachfile}">
		Download</a>

</security:authorize>
<security:authorize access="hasAnyRole('ROLE_USER')">
	<form:form method="POST" action="/SecuritySpring/user/addtasksave"
		class="was-validated" enctype="multipart/form-data"
		modelAttribute="task">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<table>
			<tr>
				<td><input name="taskid" type="hidden" value="${task.taskid}"></td>
			</tr>
			<tr>
				<td><input name="taskname" type="hidden"
					value="${task.taskname}"></td>
			</tr>
			<tr>
				<td>Select a file to upload</td>
				<td><input type="file" name="file" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>

	</form:form>

	<a href="${pageContext.request.contextPath}/">Back</a>
</security:authorize>
