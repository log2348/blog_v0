
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
			if(response.status == 200) {
			alert("글쓰기가 완료되었습니다.");
			location.href="/";				
			} else {
				alert("글쓰기 실패. 다시 시도해주세요.");
			}
		})
		.fail(function(error) {
			alert("글쓰기에 실패했습니다.");
		});
		
	},
	
	delete: function() {
		let id = $("#board-id").text();
		
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id
		})
		.done(function(response) {
			alert("게시글이 삭제되었습니다.");
			location.href="/";
		})
		.fail(function(error) {
			console.log(error);
			alert("게시글이 삭제되지 않았습니다.");
		});
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
}
	
}

index.init();