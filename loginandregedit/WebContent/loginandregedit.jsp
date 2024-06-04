<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>登录</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style>
		*{
			margin: 0 auto;
		}/* 
		body{
			background-color: #343a40;
		} */
		.container{
			position: relative;
			top: 100px;
		}
		.news-nav{
			clear: both;
			height: 80px;
			margin-left: 10px;
			margin-right: 10px;
			width: 300px;
			margin: 0 auto;
		}
		.news-nav li{
			float: left;
			list-style-type: none;
			margin: 0 10px;
			font-size: 30px;
			display: block;
			width: 86px;
			height: 79px;
			text-align: center;
			line-height: 79px;
			font-weight: bold;
			color: grey;
			cursor: pointer;
		}
		.news-nav li.on{
			color: pink;
			border-bottom-color: pink;
			border-bottom-style: solid;
			border-bottom-width: 2px;
		}
		#index-news-list-2{
			display: none;
		}
		.modal-dialog{
			max-width: 100% !important;
		}
		.modal-content{
			background:rgba(0,0,0,0.3);
			width: 700px;
		}
		.loginForm{
			width: 400px;
		}
		.loginForm .form-group{
			margin: 30px 0;
		}
		.loginForm .form-group .form-control{
			height: 40px;
			font-size: 15px;
		}
		input[type="checkbox"]{
			position:relative;
			top:-2px;
			vertical-align: middle;
			cursor: pointer;
			zoom:1.6;
		}
		input[type="radio"]{
			position:relative;
			top:-3px;
			vertical-align: middle;
			cursor: pointer;
			zoom:1.6;
		}
		.btn-primary{
			background-color: #3e4963; 
			border: 0px solid transparent;
			width: 400px;
			height: 50px;
			font-size: 24px;
			font-family: STKaiti;
		}
		label{
			color: #fff;
			font-size: 16px;
		}
	</style>
  </head>
  <body>
	<div class="container">
		<div class="modal-dialog" id="login_form">
			<div class="modal-content">
				<div class="modal-title">
					 <ul class="news-nav js-nav-title">
						<li class="on" data="login">登录</li>
						<li class="" data="register">注册</li>
					 </ul>
				</div>
				<div class="modal-body index-news-list" id="index-news-list-1">
					<form class="loginForm" id="loginForm" action="login" method="post">
						  <div class="form-group">
							  <input class="form-control required" name="username" id="name" type="text" placeholder="请输入用户名">
						  </div>
						  <div class="form-group">
							  <input class="form-control required" name="password" id="password" type="password" placeholder="请输入密码">
						  </div>
						  <div class="form-group">
							  <label for="remember">
								<input type="checkbox" name="remember" id="remember" value="0"/> 记住我
							  </label>
						  </div>
						  <div class="form-group">
							  <button class="btn btn-primary" type="submit">登录</button>
						  </div>
					 </form>
				</div>
				
				<div class="modal-body index-news-list" id="index-news-list-2">
					<form class="loginForm" id="registerForm" action="regedit" method="post">
					  <div class="form-group">
							<input class="form-control required" name="username" id="name" type="text" placeholder="请输入要注册的用户名">  
					  </div>  
					  <div class="form-group">  
							<input class="form-control required" name="password" id="rePassword" type="password" placeholder="请输入密码">  
					  </div>  
					  <div class="form-group">  
							<label style="margin-right: 20px;">性别</label>  
							<input id="nan" name="sex" type="radio" value="0">  
							<label for="nan" style="margin-right: 20px;">男</label>  
							<input id="nv" name="sex" type="radio" value="1">  
							<label for="nv">女</label>  
					  </div>  
					  <div class="form-group">  
							<input class="form-control required" name="birth" id="birth" type="date" placeholder="请输入生日">  
					  </div>   
					  <div class="form-group">  
							<input class="form-control required" name="addr" id="addr" type="text" placeholder="请输入所在地">  
					  </div>  
					  <div class="form-group">  
							<button class="btn btn-primary"  type="submit">注册</button>  
					  </div>  
					</form>
				</div>
				
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function () {
			$(".js-nav-title li").click(function(){
				$(this).attr("class","on");
				$(this).siblings().attr("class","");
				var index = $(".js-nav-title li").index(this);
				$(".index-news-list").css("display","none");
				$("#index-news-list-"+(index+1)).css("display","block");
			});
		});
		// 注册表单的提交事件  
		$('#registerForm').on('submit', function(e) {  
			e.preventDefault(); // 阻止表单的默认提交行为  
	  
			// 使用Ajax提交注册表单  
			$.ajax({  
				type: 'POST',  
				url: $(this).attr('action'), // 获取表单的action属性，即提交的目标URL  
				data: $(this).serialize(), // 序列化表单数据  
				success: function(response) {  
					// 注册成功后执行的代码  
					// 隐藏注册窗口，显示登录窗口  
					$('#index-news-list-2').css('display', 'none'); // 隐藏注册部分  
					$('#index-news-list-1').css('display', 'block'); // 显示登录部分  
					$(".js-nav-title li").eq(0).trigger('click'); // 触发登录导航的点击事件，以确保导航状态正确  
	  
					// 这里可以添加一些其他的成功提示，比如弹出一个消息框等  
					alert('注册成功！');  
				},  
				error: function(xhr, status, error) {  
					// 注册失败时执行的代码  
					alert('注册失败：' + error);  
				}  
			});  
		});
		  
		// 登录表单的提交事件
		$('#loginForm').on('submit', function(e) {    
		    
		    // 获取用户名和密码字段的值  
		    var username = $('#name').val().trim(); // 使用trim()去除可能的前后空格  
		    var password = $('#password').val().trim();  
		  
		    // 检查用户名和密码是否为空  
		    if (username === '' || password === '') {  
		    	// 显示错误消息，提示字段为空  
		        alert('用户名或密码不能为空！');
		        e.preventDefault(); // 阻止表单的默认提交行为    
		    }
		});
</script>
</body>
</html>


