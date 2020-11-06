<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	
	


<h1><c:out escapeXml="true" value="${message}" /></h1>
<h3>Challenge Page</h3>

<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
	<table class="table table-bordered">
	<spring:htmlEscape defaultHtmlEscape="true" />
		<tr>
			<th>STT</th>
			<th>ChallengeID</th>
			<th>Challengename</th>
			<th>Suggest</th>
			<!-- <th colspan="2">Action</th> -->
		</tr>
		
		<c:set var="stt" value="0" />
		<c:forEach items="${listChallenge}" var="listChallenge">
			<tr>
				<td>${stt=stt+1}</td>
				<td><c:out escapeXml="true" value="${listChallenge.challengeid}" /></td>
				<td><a
					href="${pageContext.request.contextPath}/user/detailchallenge/${listChallenge.challengeid}">
						<c:out escapeXml="true" value="${listChallenge.challengename}" /></a></td>
				<td><c:out escapeXml="true" value="${listChallenge.suggest}" /></td>
				<%-- <td><a
					href="${pageContext.request.contextPath}/admin/editchallenge/${listChallenge.challengeid}">
						Edit</a></td>
				<td><form:form method="POST"
						action="/SecuritySpring/admin//delchallenge/${listChallenge.challengeid}">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<input type="submit" value="Del challenge"
							onclick="return confirm('Are you sure you want to Delete?');" />
					</form:form>
				
				
				</td> --%>
			</tr>
		</c:forEach>
	</table>

	<form:form method="GET" action="/SecuritySpring/admin/addchallenge"
		modelAttribute="challenge">
		<input type="submit" value="Add Challenge" />
	</form:form>
</security:authorize>


