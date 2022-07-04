<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="sec"
uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
  <sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<head>
  <title>My Blog</title>
  <meta charset="utf-8" />
  <meta name="${_csrf.parameterName}" content="${_csrf.token}">
  <meta name="_csrf_header" content="${_csrf.headerName}">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet" />
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
  <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="/"><b>BLOG</b></a>
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav">
        <c:choose>
          <c:when test="${empty principal}">
            <li class="nav-item">
              <a class="nav-link" href="/auth/login_form" style="font-size:18px">로그인</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/auth/join_form" style="font-size:18px">회원가입</a>
            </li>
          </c:when>
          <c:otherwise>
            <li class="nav-item">
              <a class="nav-link" href="/board/list_form" style="font-size:18px">게시판</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/logout" style="font-size:18px">로그아웃</a>
            </li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
    <div class="float-right">
      <form class="form-inline" action="/board/search" method="get">
        <input type="text" class="form-control m-2" name="q" placeholder="Search" value="${searchTitle}" />
        <input type="image" src="/image/search.png" width="50" height="50">
      </form>
    </div>
  </nav>
</body>
