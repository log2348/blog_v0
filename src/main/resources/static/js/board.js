
let index = {
	
	init: function() {
		$("#btn-save").bind("click", () => {
			this.save();
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
		.done(function(data) {
			if(data.status == 200) {
			alert("글쓰기가 완료되었습니다.");
			location.href="/";				
			} else {
				alert("글쓰기 실패. 다시 시도해주세요.");
			}
		})
		.fail(function(error) {
			alert("글쓰기에 실패했습니다.");
		});
		
	}
	
}

index.init();