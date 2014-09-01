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

<title>My JSP 'chart.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript">
	$(function() {
		$('#container').highcharts({
			chart : {
				type : 'column'
			},
			title : {
				text : 'My first Highcharts chart'
			},
			xAxis : {
				categories : [ 'my', 'first', 'chart' ]
			},
			yAxis : {
				title : {
					text : 'something'
				}
			},
			series : [ {
				name : 'Jane',
				data : [ 1, 9, 4 ]
			}, {
				name : 'John',
				data : [ 5, 7, 3 ]
			} ]
		});

		$('#container2')
				.highcharts(
						{
							title : {
								text : 'Browser market shares at a specific website, 2010'
							},
							plotOptions : {
								pie : {
									allowPointSelect : true,
									cursor : 'pointer',
									dataLabels : {
										enabled : true,
										color : '#000000',
										connectorColor : '#000000',
										format : '<b>{point.name}</b>: {point.percentage:.1f} %'
									}
								}
							},
							series : [ {
								type : 'pie',
								name : 'Browser share',
								data : [ [ 'Firefox', 45.0 ], [ 'IE', 26.8 ], {
									name : 'Chrome',
									y : 12.8,
									sliced : true,
									selected : true
								}, [ 'Safari', 8.5 ], [ 'Opera', 6.2 ],
										[ 'Others', 0.7 ] ]
							} ]
						});

	});
</script>
</head>

<body>
	<div id="container"></div>
	<div id="container2"></div>
</body>
</html>
