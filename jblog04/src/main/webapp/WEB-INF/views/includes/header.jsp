<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


		<div id="header">
			<h1>${blogvo.title}</h1>
			<ul>
			<c:choose>
			<c:when test="${authUser.id eq null }">
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/blog/${authUser.id}">내 블로그 가기</a></li>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			</c:otherwise>
			</c:choose>
				<c:if test="${authUser.id eq blogvo.userId }">
				<li><a href="${pageContext.request.contextPath}/blog/${blogvo.userId}/admin">블로그 관리</a></li>
				</c:if>
			</ul>
		</div>