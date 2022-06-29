<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>

<style>
  .multiLine {
    width: 250px;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
  }
</style>

<div class="container" style="margin-top: 30px">
<c:forEach var="board" items="${pageable.content}">
  <div class="card m-2">
    <div class="card-body">
      <h2>${board.title}</h2>
      <h6>Date : ${board.createDate}</h6>
      <h8 type="text" class="multiLine">${board.content}</h8>
      <a href="/board/${board.id}" class="btn btn-primary float-right">Detail</a>
      <br />
    </div>
  </div>
</c:forEach>
</div>
<br/>
<ul class="pagination justify-content-center">
  <li class="page-item"><a class="page-link" href="#">Prev</a></li>
  <li class="page-item"><a class="page-link" href="#">Next</a></li>
</ul>

<%@ include file="layout/footer.jsp" %>
