<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/bootstrap.css">

<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>


</head>

<body>
	<h3>
		<span class="label label-primary">Test ---New</span>
	</h3>
	<hr>
	<div class="container">
	<div class="navbar navbar-default" role="navigation">
	    <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">导航</a>
          </div>
		<div class="navbar-collapse collapse" id="bs-example-navbar-collapse-1" role="navigation">
			<ul class="nav navbar-nav" role="tablist" id="myTab" style="text-align: center;">
				<li class="active"><a href="#home" onclick="shjow()">测试1</a></li>
				<li><a href="#profile">测试2</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Dropdown <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>
						<li class="divider"></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
		</div>
		</div>
</div>
		<div class="tab-content">
			<div class="tab-pane active" id="home">
			11111
			</div>
			<div class="tab-pane" id="profile">333333333</div>
		</div>
	<!--   <ul class="nav nav-tabs" role="tablist">
  <li role="presentation" class="active"><a href="#">Home</a></li>
  <li role="presentation"><a href="#">Profile</a></li>
  <li role="presentation"><a href="#">Messages</a></li>
</ul> -->
	<!-- <div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">Panel heading without title</div>
			<div class="panel-body">Basic panel example11</div>
		</div>
	</div> -->


</body>
<script type="text/javascript">
	$('#myTab a').click(function(e) {
		console.info(e);
		e.data="lalallalala";
		//  e.preventDefault()
		$(this).tab('show');
	})
</script>
</html>
