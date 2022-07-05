<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<main class="container py-5">

	<div>
		<form action="/story/image/upload" enctype="multipart/form-data" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<div class="input-group mt-3">
				<input type="file" class="custom-file-input" id="customFile"> <label class="custom-file-label" for="customFile">Choose File</label>
			</div>
			<div class="input-group mt-3">
				<div class="input-group-prepend">
					<span class="input-group-text">Description</span>
				</div>
				<input type="text" name="storyText" class="form-control" required="required">
			</div>
			<div class="input-group mt-3">
				<button type="submit" class="btn btn-info">파일 등록</button>
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