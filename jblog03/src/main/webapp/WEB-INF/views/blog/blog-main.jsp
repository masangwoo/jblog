<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.postTitle}</h4>
					<p>
						<c:set var="cmt" value="${fn:replace(postVo.contents, newline, '<br/>') }" />
						<c:out value="${cmt}" escapeXml="false"/>
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList}" var = "vo" varStatus = "status">
                       <li><a href="${pageContext.request.contextPath}/blog/${blogvo.userId}/${vo.categoryNo}/${vo.no}">${vo.postTitle}</a> <span>${vo.regDate}</span>	</li>
            		</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogvo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${catList}" var = "vo" varStatus = "status">
					<li><a href="${pageContext.request.contextPath}/blog/${vo.blogId}/${vo.no}">${vo.categoryName}</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>