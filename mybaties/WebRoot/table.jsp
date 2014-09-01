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
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="js/bootstrap-datetimepicker.zh-CN.js"></script>

</head>

<body>
	<h3>
		<span class="label label-primary">Test ---Table</span>
	</h3>
	<hr>

	<div class="container">
		<div class="row">
			<div class="col-lg-2 ">
				<input type="text" class="form-control" placeholder="Username">
			</div>
			<div class="col-lg-2 ">
				<input type="text" class="form-control" placeholder="Username">
			</div>
			<div class="col-lg-2 ">
				<input type="text" class="form-control" placeholder="Username">
			</div>
			<div class="col-lg-2 ">
				<input type="text" class="form-control" placeholder="Username">
			</div>
		</div>
		<p></p>
		<div class="row">
			<div class="dropdown col-lg-2">
				<span>类别：</span>
				<button type="button" class="btn dropdown-toggle" id="dropdownMenu1"
					data-toggle="dropdown">
					请选择 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu"
					aria-labelledby="dropdownMenu1">
					<li role="presentation"><a role="" tabindex="-1" href="#">Java</a></li>
					<li role="presentation"><a role="" tabindex="-1" href="#">数据挖掘</a></li>
					<li role="presentation"><a role="" tabindex="-1" href="#">
							数据通信/网络 </a></li>
					<li role="presentation" class="divider"></li>
				</ul>
			</div>
			<div class="col-lg-2">
				<input type="text" class="form-control" placeholder="Username">
			</div>
			<input type="text" id="dp1" format="yyyy-MM-dd"
				class="span2 input-small" />
		</div>
		<div class="span12 ">

			<div class="panel-body">

				<table class="table table-hover table-bordered">
					<thead>
						<tr id="item">
							<th>姓名</th>
							<th>年龄</th>
							<th>爱好</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
					</tbody>
				</table>

				<div>
					<ul class="pagination pull-left">
						<div class="input-group ">
							<span class="input-group-addon">转到</span> <input type="text"
								class="form-control col-lg-6" id="jumpText" /> <span
								class="input-group-addon">页</span> <span class="input-group-btn">
								<button class="btn btn-default" type="button">确定</button>
							</span>
						</div>
					</ul>

					<ul class="pagination pull-right">
						<li><a href="#">上一页</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">下一页</a></li>
					</ul>
				</div>
			</div>
		</div>

		<a href="javascript:void(0)" id="a_pop1" class="btn btn-danger"
			data-placement="top"
			data-content="  
    风吹雨成花 时间追不上白马  
    你年少掌心的梦话 依然紧握着吗  
    云翻涌成夏 眼泪被岁月蒸发  
    这条路上的你我她 有谁迷路了吗  
    "
			data-original-title="时间煮雨">左侧</a>
	</div>

</body>
<script type="text/javascript">
	$(function() {
		$.ajax({
			type : "get",
			dataType : "json",
			url : "/mybaties/user/info.do",
			success : function(obj) {
				$("tr").eq(0).nextAll().remove(); //将除模板行的tr删除  
				for (var i = 0; i < obj.length; i++) {
					o1 = obj[i];
					var row = $("<tr></tr>");
					for (key in o1) {
						var td = $("<td></td>");
						if (o1[key] != null) {
							if (key != "id") {
								td.text(o1[key]);
								td.appendTo(row);
							}
						}
					}
					
					var td2 = $("<td></td>");
					var bt1=$("<input>",{"type":"button","value":"审核","class":"btn btn-info","name":"comfire"});
					bt1.click({ back: obj[i].id }, operation);
					bt1.appendTo(td2);
					var bt2=$("<input>",{"type":"button","value":"删除","class":"btn btn-danger","name":"comfire"});
					bt2.click({ back: obj[i].id }, operation);
					bt2.appendTo(td2);
					td2.appendTo(row);
					row.appendTo($("#tbody"));
				}

			}
		});

	});
	 //获取传过来的参数并显示  
    function operation(event) {  
        alert(event.data.back);  
    } 
</script>
<script type="text/javascript">
	$("#a_pop1").popover();
	/*  $('#dp1').datepicker({format: 'yyyy.mm.dd',
	 weekStart: 1,
	 autoclose: true,
	 todayBtn: 'linked',
	 language: 'zh-CN'}); */
	$('#dp1').datetimepicker({
		language : 'zh-CN',/*加载日历语言包，可自定义*/
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 3,
		startView : 2,
		minView : 2,
		forceParse : 0
	});
</script>

<script type="text/javascript">
	$('#myTab a').click(function(e) {
		console.info(e);
		e.data = "lalallalala";
		//  e.preventDefault()
		$(this).tab('show');
	})
</script>
</html>
