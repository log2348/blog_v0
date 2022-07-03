
let index = {
	
	init: function() {
		$("#btn-save").bind("click", () => {
			this.save();
		})
		
		$("#btn-delete").bind("click", () => {
			this.delete();
		})
		
		$("#btn-update").bind("click", () => {
			this.update();
		})		
		
		$("#btn-reply-save").bind("click", () => {
			this.replySave();
		})		
		
	},
	
	save: function() {
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		$.ajax({
			type: "POST",
			url: "/api/board/save",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		})
		.done(function(response) {
			if(data.title == "" || data.title.trim() == ""){
				alert("제목을 입력해주세요.")
			} else {
				if(data.content == "" || data.content.trim() == "") {
					alert("내용을 입력해주세요.")
				} else {
					alert("게시글 등록 완료.");
					location.href="/board/list_form";				
				}				
			}
		})
		.fail(function(error) {
			alert("게시글 등록 실패.");
		});
		
	},
	
	delete: function() {
		let id = $("#board-id").text();
		let deleteCheck = confirm("삭제하시겠습니까?");
		
		if(deleteCheck) {
			$.ajax({
				type: "DELETE",
				url: "/api/board/" + id
			})
			.done(function(response) {
				alert("게시글이 삭제되었습니다.");
				location.href="/board/list_form";
			})
			.fail(function(error) {
				console.log(error);
				alert("게시글이 삭제되지 않았습니다.");
			});
			
		}
		
	},
	
	
	update: function() {
		let id = $("#board-id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		})
		.done(function(response) {
			alert("게시글이 수정되었습니다.");
			location.href="/";
		})
		.fail(function(error) {
			console.log(error);
			alert("게시글이 수정되지 않았습니다.");
		});
	},
	
	replySave: function() {
		let data = {
			boardId: $("#board-id").text(),
			content: $("#reply-content").val()
		}
		
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		})
		.done(function(response) {
			if(data.content == "" || data.content.trim() == "") {
				alert("내용을 입력해주세요.")
			} else {
				appendReply(response.data);
			}
			console.log(response)
		})
		.fail(function(error) {
			alert("오류 발생. 댓글이 등록되지 않았습니다.")
			console.log(error);
		});
		
	},
	
	replyDelete: function(boardId, replyId) {

		let deleteCheck = confirm("삭제하시겠습니까?");
		
		if(deleteCheck) {
			
			$.ajax({
				type: "DELETE",
				url: `/api/board/${boardId}/reply/${replyId}`	
			})
			.done(function(response) {
				if(deleteCheck) {
					location.href=`/board/${boardId}`;
					alert("댓글이 삭제되었습니다.");
				}
			})
			.fail(function(error) {
				alert("오류 발생. 댓글이 삭제되지 않았습니다.");
			})
			
		}
		
	}
	
}

function appendReply(reply) {
	let principalId = $("#principal-id").val();
	
	let childElement = `<li class="list-group-item d-flex justify-content-between">
		  	<h6>${reply.user.username} : ${reply.content}</h6>
		  	<c:if test="${reply.user.id == principalId}">
			  	<button class="btn btn-danger btn-sm" onclick="index.replyDelete(${reply.board.id}, ${reply.id})">삭제</button>
		  	</c:if>
		  </li>`;
		   
	$("#reply--box").prepend(childElement);
	$("#reply-content").val("");	  
}

index.init();