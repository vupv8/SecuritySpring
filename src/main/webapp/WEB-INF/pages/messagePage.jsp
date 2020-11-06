<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>
	<c:out escapeXml="true" value="${message}" />
</h1>
<h3>Message Page</h3>


<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">

	<c:forEach items="${ListMessage}" var="Message">

		<c:if
			test="${Message.senderid == userid and (Message.status==1 or Message.status==3)}">
			<p style="text-align: left; background-color: #B0E0E6"">
				<c:out escapeXml="true" value="${Message.messagecontent}" />
			</p>
		</c:if>
		<c:if
			test="${Message.senderid != userid and (Message.status==1 or Message.status==3)}">
			<p style="text-align: right; background-color: #B0E0E6">
				<a
					onclick="return loadEdit('${Message.messageid}',<c:out escapeXml='true' value='${Message.messagecontent}' />)">
					Edit </a> <a
					onclick="return confirmDelete(this, '/user/delmessage/${Message.messageid}')"
					href="${pageContext.request.contextPath}/user/delmessage/${Message.messageid}">
					Del </a>
				<c:out escapeXml="true" value="${Message.messagecontent}" />
			</p>
		</c:if>

	</c:forEach>

	<form:form method="POST" action="/SecuritySpring/user/addmessage"
		modelAttribute="messages">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<p style="width: 100%">
			<form:hidden path="receiverid" value="${userid}" />
			<form:hidden path="messageid" id="id" />
			<form:input path="messagecontent" id="content" />
			<input type="submit" value="Submit" />
		</p>

	</form:form>

</security:authorize>



