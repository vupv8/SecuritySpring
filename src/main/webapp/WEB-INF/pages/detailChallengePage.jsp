
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="lable"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<h1>${message}</h1>
<h3>Detail Challenge Page</h3>

<security:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
	<form:form method="POST"
		action="/SecuritySpring/user/submitanswer/${challenge.challengename}"
		class="was-validated">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<p>Challenge Name</p>
		<c:out escapeXml="true" value="${challenge.challengename}" />
		<p>Suggest</p>
		<p><c:out escapeXml="true" value="${challenge.suggest}" /></p>
		<input name="answer" value="" />
		<input type="submit" value="Submit" />

	</form:form>

	<a href="${pageContext.request.contextPath}/">Back</a>
</security:authorize>
