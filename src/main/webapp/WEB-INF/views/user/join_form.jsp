<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file="../layout/header.jsp" %>

<div class="container">
    <br />
    <div class="form-group">
      <label for="username">Username: </label>
      <input type="username" class="form-control" placeholder="Enter username" id="username" />
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" placeholder="Enter password" id="password" />
    </div>
    <div class="form-group">
      <label for="email">Email Address:</label>
      <input type="email" class="form-control" placeholder="Enter email" id="email" />
    </div>

    <button id="btn-join" type="button" class="btn btn-primary">JOIN</button>
  </form>
</div>
<br />

<script src="/blog/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>
