<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>LMS</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }" method="get">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>타이틀</th>
						<th>카테고리</th>
						<th>&nbsp;</th>
					</tr>
					
					<c:set var="count" value="${getTotalElements-(currentPage-1)*5}"/>
					<c:forEach items="${list}" var ="vo" varStatus="status">
						<tr>
							<td>${count-status.index}</td>
							<td>${vo.title}</td>
							<td>${vo.categoryName}</td>
							<td>
							<c:choose>
							<c:when test="${vo.rentable eq true }">	
								<a href="${pageContext.servletContext.contextPath }/rent?no=${vo.no}" class="btn">대여</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.servletContext.contextPath }/rent?no=${vo.no}" class="btn">예약</a>
							</c:otherwise>
							</c:choose>
							</td>
					</tr>
					</c:forEach>
					
				</table>
				<div class="pager">
					<ul>
					<c:if test='${startPage ne 1}'>
						<li><a href="${pageContext.servletContext.contextPath }/${startPage-pageNumPerBlock}">◀</a></li>
					</c:if>
					<c:set var="count" value="5" />
					<c:forEach begin="0" end="${pageNumPerBlock-1}" step="1"  var="x">
					
					<c:if test='${startPage+x le totalPages}'>
						<c:choose>
								<c:when test='${currentPage eq startPage+x}'>
									<li class="selected"><a href="${pageContext.servletContext.contextPath }/${startPage+x}">${startPage+x}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.servletContext.contextPath }/${startPage+x}">${startPage+x}</a></li>
								</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test='${startPage+x gt totalPages}'>
						
					</c:if>
						<!-- <li class="selected">8</li> -->
					</c:forEach>
					<c:if test='${totalPages gt startPage+count}'>
						<li><a href="${pageContext.servletContext.contextPath }/${startPage+pageNumPerBlock}">▶</a></li>
					</c:if>
					</ul>
				</div>				
				<div class="bottom">
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>