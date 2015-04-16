<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>注册页面</title>
<!-- Bootstrap -->
<link href="<%=path%>/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="<%=path%>/dist/css/bootstrap-theme.min.css" rel="stylesheet">
<link rel="icon" href="../../favicon.ico">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
 <div class="container">
	<div class="panel panel-default" style="padding-top: 20px;">
		<form role="form" class="form-horizontal"
			action="user/register.action" method="post">
			<div class="form-group">
				<label class="col-sm-3 control-label">登录名</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="user.username" placeholder="请输入登录名" required autofocus/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">昵称</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="user.nickname" placeholder="请输入昵称" required/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">密码</label>
				<div class="col-sm-8">
					<input type="password" class="form-control" name="user.password" placeholder="请输入密码" required/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">确认密码</label>
				<div class="col-sm-8">
					<input type="password" class="form-control" name="repassword" placeholder="请确认密码" required/>
				</div>
			</div>
			<div class="form-group has-feedback">
				<label class="col-sm-3 control-label" for="email">邮箱</label>
				<div class="col-sm-8">
					<input type="email" class="form-control" id="email" name="user.email" placeholder="请输入邮箱"/>
					<!-- <span class="glyphicon glyphicon-remove form-control-feedback"></span> -->
				</div>
			</div>
			<div align="center">
				<button type="submit" class="btn btn-default">注册</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>