<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container" >
	<form>
	<br/>
		<div class="form-group">
			<input type="hidden" id="board-id" value="${board.id}">
			<label for="title">Title  :  </label>
			<input type="text" value="${board.title}" id="title" class="form-control">
		</div>

		<div class="form-group">
			<label for="title">Content  :  </label>
			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		</div>		
		
		<button type="button" id="btn-update" class="btn btn-primary">수정</button>
	</form>
</div>
<br/><br/>

<script type="text/javascript">
      $('.summernote').summernote({
        placeholder: 'Write Here',
        tabsize: 2,
        height: 400
      });
      
</script>
<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp" %>