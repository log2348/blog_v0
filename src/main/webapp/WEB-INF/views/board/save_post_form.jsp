<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container" >
	<form>
	<br/>
		<div class="form-group">
			<label for="title">Title  :  </label>
			<input type="text" placeholder="title" id="title" class="form-control">
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
/*
    function savePost() {
    	let title = document.querySelector("#title").value;
    	let content = document.querySelector("#content").value;
    	//let userId = document.querySelector('#userId').value;
    	
    	let board = {
    		title: title,
    		content: content
    	}
    	
    fetch("/blog/savePost", {
    	method: "post",
    	headers: {
    		'content-type': 'application/json; charset=utf-8'
    	},
    	body: JSON.stringify(board)
    })
    .then(res => res.text())
    .then(res => {
    	if(res == "ok") {
    		alert("글쓰기 성공");
    		location.href = "/";
    	} else {
    		alert("글쓰기 실패");
    	}
    });  
    
    }
*/   
      $('.summernote').summernote({
        placeholder: 'Write Here',
        tabsize: 2,
        height: 400
      });
      
</script>
<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp" %>