<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>


<h1><c:out escapeXml="true" value="${message}" /></h1>
<h3>Task Page</h3>
<table class="table table-bordered">
	<tr>
		<th>STT</th>
		<th>Taskname</th>
		<th>Descripsion</th>
		<th style="width: 150px;">Attack File</th>
		<th>Date Created</th>
		<th>Creator</th>
		<th>User</th>
		<th colspan="3">Action</th>
	</tr>
	<c:set var="stt" value="0" />
	<c:forEach items="${listTask}" var="ListTask">
		<c:if test="${ListTask.parents==0 }">
			<tr>
				<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
					<td>${stt=stt+1}</td>
					<td><a
						href="${pageContext.request.contextPath}/user/detail/${ListTask.taskid}"><c:out escapeXml="true" value="${ListTask.taskname}" /></a></td>
					<td><c:out escapeXml="true" value="${ListTask.description}" /></td>
					<td style="width: 150 px; overflow: scroll"><c:out escapeXml="true" value="${ListTask.attachfile}" /></td>
					<td><c:out escapeXml="true" value="${ListTask.datecreated}" /></td>
					<td><c:out escapeXml="true" value="${ListTask.username}" /></td>
					<td><c:out escapeXml="true" value="${ListTask.parents}" /></td>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<td><a
						href="${pageContext.request.contextPath}/user/download/${ListTask.attachfile}">
							Download</a></td>
					<%-- <td><a
						href="${pageContext.request.contextPath}/admin/edittask/${ListTask.taskid}">
							Edit</a></td>
					<td><a
						onclick="return confirmDelete(this, '/admin/deltask/${ListTask.taskid}')"
						href="${pageContext.request.contextPath}/admin/deltask/${ListTask.taskid}">
							Del</a></td> --%>
				</security:authorize>
			</tr>
			<tr>
				<td colspan="10">
					<table>
						<c:forEach items="${listTask}" var="ListTask1">
							<c:if
								test="${ListTask1.parents==ListTask.taskid and ListTask1.parents!=0}">
								<tr>
									<security:authorize access="hasAnyRole('ROLE_ADMIN')">
										<td><p>Trả bài</p></td>
										<td><a
											href="${pageContext.request.contextPath}/user/detail/${ListTask1.taskid}">${ListTask1.taskname}</a></td>
										<td><c:out escapeXml="true" value="${ListTask1.description}" /></td>
										<td><c:out escapeXml="true" value="${ListTask1.attachfile}" /></td>
										<td><c:out escapeXml="true" value="${ListTask1.datecreated}" /></td>
										<td><c:out escapeXml="true" value="${ListTask1.username}" /></td>
										<td><c:out escapeXml="true" value="${ListTask1.parents}" /></td>
									</security:authorize>
									<security:authorize access="hasRole('ROLE_ADMIN')">
										<td><a
											href="${pageContext.request.contextPath}/user/download/${ListTask1.attachfile}">
												Download</a></td>
										<%-- <td><a
											href="${pageContext.request.contextPath}/admin/edittask/${ListTask1.taskid}">
												Edit</a></td>
										<td><a
											onclick="return confirmDelete(this, '/admin/deltask/${ListTask1.taskid}')"
											href="${pageContext.request.contextPath}/admin/deltask/${ListTask1.taskid}">
												Del</a></td> --%>
									</security:authorize>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</td>

			</tr>
		</c:if>

	</c:forEach>
</table>
<security:authorize access="hasRole('ROLE_ADMIN')">
	<form:form method="GET" action="/SecuritySpring/admin/addtask"
		modelAttribute="task">
		<input type="submit" value="Add Task" />
	</form:form>
</security:authorize>

