<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container" >
	<form>
	<br/>
		<div class="form-group">
			<label for="title">Title  :  </label>
			<input type="text" placeholder="제목을 입력하세요" id="title" class="form-control">
		</div>

		<div class="form-group">
			<label for="title">Content  :  </label>
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>		
		
		<button type="button" id="btn-save" class="btn btn-primary">Save</button>
	</form>
</div>
<br/><br/>

<script type="text/javascript">
      $('.summernote').summernote({
        placeholder: '내용을 입력하세요',
        tabsize: 2,
        height: 400
      });
      
</script>
<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp" %>