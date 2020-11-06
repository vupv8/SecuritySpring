<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>




<c:if test="${pageContext.request.userPrincipal.name != null}">
	<div class="hero__categories">
		<div class="hero__categories__all">
			<i class="fa fa-bars"></i> <span>List User</span>
		</div>
		<ul>
			<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
				<c:forEach items="${listAllUser}" var="User">
					<li><a
						href="${pageContext.request.contextPath}/user/info/${User.userid}">
							${User.username}</a></li>
				</c:forEach>
			</security:authorize>
		</ul>
	</div>
</c:if>

