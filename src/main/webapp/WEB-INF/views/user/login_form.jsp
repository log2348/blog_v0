<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file="../layout/header.jsp" %>

<div class="container">
  <form action="/auth/loginProc" method="post">
    <br />
    <div class="form-group">
      <label for="username">Username:</label>
      <input type="text" class="form-control" placeholder="Enter username" value="tester" id="username" name="username"/>
    </div>
    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" class="form-control" placeholder="Enter password" value="1234" id="password" name="password"/>
    </div>
    <button type="submit" id="btn-login" class="btn btn-primary">LOGIN</button>
    <a href="https://kauth.kakao.com/oauth/authorize?client_id=a230199492e4aec300eeb42c5cdbb121&redirect_uri=http://localhost:9090/oauth/kakao/callback&response_type=code">
    	<img src="/image/kakao_login.png" width="74" height="38" >
    </a>
  </form>
</div>
<br />

<%@ include file="../layout/footer.jsp" %>
