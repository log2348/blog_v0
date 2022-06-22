let index = {
	
	init: function() {
		$("#btn-join").bind("click", () => {
			this.save();
		})
		
		$("#btn-login").bind("click", () => {
			this.login();
		})
	},
	
	save: function() {
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		}
		console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(data, textStatus, xhr) {
			// 통신 성공
			console.log("xhr : " + xhr); // XMLHttpRequest 객체
			console.log(xhr);
			console.log("textStatus : " + textStatus);
			console.log("data : " + data);
			alert("회원가입이 완료되었습니다.");
			location.href = "/blog";
		}).fail(function(error) {
			// 통신 실패
			console.log(error);
			alert("회원가입에 실패하였습니다.");
		})
	},
	
	login: function() {
		
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		}
		
		$.ajax({
			type: "POST",
			url: "/blog/api/user/login",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(data, textStatus, xhr) {
			// 통신 성공
			alert("로그인 완료")
			console.log(data);
			location.href="/blog"
		}).fail(function(error) {
			// 통신 실패
			alert("로그인 실패")
			console.log(error)
		});
	}
}

index.init();