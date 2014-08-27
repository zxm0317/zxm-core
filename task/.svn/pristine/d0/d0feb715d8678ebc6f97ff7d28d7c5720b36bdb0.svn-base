<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"   uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%= request.getContextPath()%>/css/simple.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%= request.getContextPath()%>/js/jquery-1.8.0.min.js"></script>
<title>任务管理系统--计划任务管理</title>
<script type="text/javascript">	
	function enableJob(jobId,taskName){
		if(confirm("您确定需要启用【"+taskName+"】任务？")){
			if(jobId!=""){
				$.post("<%= request.getContextPath() %>/job/enableJob.do",{"jobId":jobId},enableJobResults,"json");
			}
		}
	}
	function enableJobResults(json){
		var job=json.job;
		if(json.resultCode==1){
			$("#isEnable"+job.id).html("启用");
			$("#status"+job.id).html(job.status);
			$("#disableSpan"+job.id).show();
			$("#enableSpan"+job.id).hide();
		}
		alert(json.resultInfo);
	
	} 
	function disableJob(jobId,taskName){
		if(confirm("您确定需要停用【"+taskName+"】任务？")){
			if(jobId!=""){
				$.post("<%= request.getContextPath() %>/job/disableJob.do",{"jobId":jobId},disableJobResults,"json");
			}
		}
	}
	function disableJobResults(json){
		var job=json.job;
		if(json.resultCode==1){
			$("#isEnable"+job.id).html("停用");
			$("#status"+job.id).html(job.status);		
			$("#disableSpan"+job.id).hide();
			$("#enableSpan"+job.id).show();
		}
		alert(json.resultInfo);
	
	} 
	
	
	function executeJob(jobId,taskName){
		
		if(confirm("您确定需要立即执行一次【"+taskName+"】任务？")){
			if(jobId!=""){
				$.post("<%= request.getContextPath() %>/job/executeJob.do",{"jobId":jobId},executeJobResults,"json");
			}
		}
	}
	function executeJobResults(json){
	
		if(json==1){
			alert("触发执行成功，具体请查任务日志！");
		}
	
	} 


</script>
</head>
<body>
	<div class="rightContent">
		<div class="path">您的位置：计划任务管理 &nbsp;&gt;&nbsp;任务列表</div>
		<div class="clr"></div> 
		<div class="filter">
		<form action="<%= request.getContextPath() %>/job/jobList.do" method="post" onsubmit="return true;">
			<div class="search">
				<ul >
					<li >关键字：
					<input name="keywords" id="keywords" style="width: 200px;" type="text" value=""/>
					状态：
					<select name="isEnable" >
						<option  value="">请选择</option>
						<option  value="true">启用</option>
						<option  value="false">停用</option>
					</select>
					<input type="submit" value="搜 索"/>
					</li>
				</ul>
			</div>
		</form>
		</div>
		<div class="clr"></div> 
		<div style="width: 100%;margin-top: 6px;margin-bottom: 6px;margin-left: 10px;color: #ff0000;">
		运行状态：【None：任务未启动或者已删除】【NORMAL:正常状态，存在于任务池中】【PAUSED：暂停状态】【COMPLETE：触发器完成，但是任务可能还正在执行中】【BLOCKED：线程阻塞状态】【ERROR：出现错误】
		</div>
		<div style="width: 100%;">
			<table class="listMap" border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr >
			<th width="6%">编号</th>
			<th width="14%">计划名称</th>	
			<th width="12%">计划编码</th>
			<th width="10%">计划状态</th>
			<th width="12%">时间表达式</th>	
			<th width="10%">运行状态</th>
			<th width="18%">备注</th>
			<th width="18%">操作</th>
			</tr>
			<c:forEach items="${scheduleJobList}" var="job">
			<tr>
			 <td><c:out value="${job.id}"/></td>
			 <td ><c:out value="${job.taskName}"/></td>
			 <td><c:out value="${job.taskCode}"/></td>
			 <td id="isEnable${job.id}"> 
			<c:choose>
				 <c:when test="${job.isEnable}">
				 	启用
				 </c:when>
				 <c:otherwise>
				 	停用
				 </c:otherwise>
			 </c:choose>
			 </td>
			 <td><c:out value="${job.cron}"/></td>
			 <td id="status${job.id}"><c:out value="${job.status}"/></td>
			 <td><c:out value="${job.memo}"/></td>
			 <td class="function">
			 	<span id="disableSpan<c:out value="${job.id}"/>" <c:if test="${!job.isEnable}">style="display: none;"</c:if>>
					<a onclick="disableJob(<c:out value="${job.id}"/>,'<c:out value="${job.taskName}"/>');">停用</a>
					<a onclick="executeJob(<c:out value="${job.id}"/>,'<c:out value="${job.taskName}"/>');">立即执行</a>
				</span>
			 	<span id="enableSpan<c:out value="${job.id}"/>" <c:if test="${job.isEnable}">style="display: none;"</c:if>>
					<a onclick="enableJob(<c:out value="${job.id}"/>,'<c:out value="${job.taskName}"/>');">启用</a>
					<a onclick="window.location.href='<%= request.getContextPath() %>/job/deleteJob.do?entrance=list&jobId=${job.id}'">删除</a>
				</span>
			 	<a onclick="window.location.href='<%= request.getContextPath() %>/job/jobDetail.do?jobId=${job.id}'">详情</a>
			 	
			 </td>
			</tr>
			</c:forEach>
			
			</table>
			<div class="clr"></div> 
			<div class="listMapBottom">
			<div class="listMapBottom_left">
			<input type="button" value="新增任务" onclick="window.location.href='<%= request.getContextPath() %>/job/editJob.do'"/>
			</div>
			<div class="listMapBottom_right">
				<%@ include file="/WEB-INF/view/subView/pages.jsp" %>
			</div>
			</div>
		</div>
		
	</div>

</body>
</html>




