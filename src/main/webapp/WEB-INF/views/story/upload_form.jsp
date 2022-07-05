<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<main class="container py-5">

	<div>
		<form action="/story/image/upload" enctype="multipart/form-data"
			method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">
			<h6>제목 :</h6>
			<div class="input-group mt-3">
				<input type="text" name="imageTitle" class="form-control"
					required="required">
			</div>
			<br />
			<h6>첨부 파일 :</h6>
			<div class="input-group mt-3">
				<label class="custom-file-label" for="customFile">파일을 선택하세요</label><input
					type="file" name="file" class="form-control custom-file-input"
					id="customFile" required="required">
			</div>
			<br />
			<div class="input-group mt-3">
				<button type="submit" class="btn btn-primary">파일 등록</button>
			</div>
		</form>
	</div>

</main>


<script>
	$(".custom-file-input").on(
			"change",
			function() {
				var fileName = $(this).val().split("\\").pop();
				$(this).siblings(".custom-file-label").addClass("selected")
						.html(fileName);
			});
</script>

<%@ include file="../layout/footer.jsp"%>