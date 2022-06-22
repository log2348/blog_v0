<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file="../layout/header.jsp" %>

<div class="container">
  <form action="/action_page.php">
    <br />
    <div class="form-group">
      <label for="username">Username:</label>
      <input type="username" class="form-control" placeholder="Enter username" id="username" />
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" placeholder="Enter password" id="pwd" />
    </div>
    <button type="button" id="btn-login" class="btn btn-primary">LOGIN</button>
  </form>
</div>
<br />
<script src="/blog/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>
