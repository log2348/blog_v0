<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container px-4 px-lg-5 mt-5">
	<div
		class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
		<c:forEach var="storyImage" items="${imagePage.content}">
			<div class="col mb-5">
				<div class="card h-100">
					<img class="card-img-top" width="200px" height="140px"
						src="http://localhost:9090/upload/${storyImage.imageUrl}">
					<div class="card-body p-4">
						<div class="text-center">
							<h5 class="fw-bolder">${storyImage.imageTitle}</h5>
						</div>
					</div>
					<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
						<div class="text-center">
							<a class="btn btn-outline-dark mt-auto" href="#">View details</a>
						</div>
					</div>
				</div>
			</div>

		</c:forEach>
	</div>
</div>


<%@ include file="../layout/footer.jsp"%>