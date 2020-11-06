<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>


<c:if test="${pageContext.request.userPrincipal.name != null}">
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="header__logo">
					<a href="${pageContext.request.contextPath}/"><img
						src="${pageContext.request.contextPath}/img/img/logo.png" alt=""></a>
				</div>
			</div>
			<div class="col-lg-8">
				<nav class="header__menu">
					<ul>
						<li class="active"><a
							href="${pageContext.request.contextPath}/">Home</a></li>
						<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">

							<li><a
								href="${pageContext.request.contextPath}/user/listtask">
									Task </a></li>
						</security:authorize>
						<security:authorize access="hasRole('ROLE_ADMIN')">
							<li><a
								href="${pageContext.request.contextPath}/admin/listallinfouser">
									User Management </a></li>

						</security:authorize>

						<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">

							<li><a
								href="${pageContext.request.contextPath}/user/listchallenge">
									Challenges </a></li>
						</security:authorize>

					</ul>
				</nav>
			</div>

		</div>
		<div class="humberger__open">
			<i class="fa fa-bars"></i>
		</div>
	</div>
</c:if>