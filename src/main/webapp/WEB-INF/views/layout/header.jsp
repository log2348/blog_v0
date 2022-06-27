<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
  <head>
    <title>Blog Test</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <style>
      .fakeimg {
        height: 200px;
        background: #aaa;
      }
    </style>
  </head>
  <body>
    <div class="jumbotron text-center" style="margin-bottom: 0">
      <h1>My Blog Example</h1>
      <p>Welcome to my blog!</p>
    </div>

    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
      <a class="navbar-brand" href="/">BLOG</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
          <c:choose>
            <c:when test="${empty principal}">
              <li class="nav-item">
                <a class="nav-link" href="/auth/login_form">LOGIN</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/auth/join_form">JOIN</a>
              </li>
            </c:when>
            <c:otherwise>
              <li class="nav-item">
                <a class="nav-link" href="/board/save_post_form">BOARD</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/logout">LOGOUT</a>
              </li>
            </c:otherwise>
          </c:choose>
        </ul>
      </div>
    </nav>
  </body>
