<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file="../layout/header.jsp" %>

<div class="container">
  <br /><br />
  <div class="float-right">
    글 번호 : <span id="board-id"><i>${board.id}</i></span
    ><br />
    작성자 : <span><i>${board.userId.username}</i></span>
  </div>
  <br /><br />
  <div class="form-group m-2">
    <h3>${board.title}</h3>
  </div>
  <hr />
  <div class="form-group m-2">
    <p>${board.content}</p>
  </div>
  <br /><br />
  <hr />
  <button class="btn btn-outline-dark float-right m-2" onclick="history.back()">목록</button>
  <input type="hidden" value="${principal.user.id}" id="principal-id" />
  <c:if test="${board.userId.id == principal.user.id}">
    <a href="/board/update_post_form/${board.id}" class="btn btn-success float-right m-2">수정</a>
    <button class="btn btn-danger float-right m-2" id="btn-delete">삭제</button>
  </c:if>
  <br /><br />
  <br />

  <div class="card">
    <div class="card-body">
      <textarea rows="2" class="form-control" id="reply-content"></textarea>
    </div>
    <div class="card-footer">
      <button class="btn btn-primary float-right" id="btn-reply-save">등록</button>
    </div>
    <div></div>
  </div>
  <br />
  <div class="card">
    <div class="card-header">전체 댓글</div>
  </div>
  <ul class="list-group" id="reply--box">
    <c:forEach var="reply" items="${board.replies}">
      <li class="list-group-item d-flex justify-content-between">
        <h6>${reply.user.username} : ${reply.content}</h6>
        <c:if test="${reply.user.id eq principal.user.id}">
          <button class="btn btn-danger btn-sm" id="btn-reply-delete" onclick="index.replyDelete(${reply.board.id}, ${reply.id})">삭제</button>
        </c:if>
      </li>
    </c:forEach>
  </ul>
</div>
<br /><br />

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>
