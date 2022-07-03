<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file="layout/header.jsp" %>


<div class="container" style="margin-top: 30px">
  <img class="card-img-top" src="/image/dog.png" />
  <div class="card-body">
    <c:if test="${not empty principal}">
      <h4 class="card-title"><b>${principal.user.username}</b> 님</h4>
      <a href="/user/update_user_form" class="badge badge-info">MY INFO</a>
    </c:if>
  </div>
</div>
<br/><br/>



<%@ include file="layout/footer.jsp" %>
