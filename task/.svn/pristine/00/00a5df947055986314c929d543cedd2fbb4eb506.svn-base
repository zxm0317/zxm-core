<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"   uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%= request.getContextPath()%>/css/simple.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%= request.getContextPath()%>/js/jquery-1.8.0.min.js"></script>
<title>要客特征管理 -- 特征详细信息</title>
<script type="text/javascript">

	function verify(){
		var taskName=$("#taskName").val();
		if(!taskName){
			alert("请输入计划任务名称！");
			return false;
		}
		var taskCode=$("#taskCode").val();
		if(!taskCode){
			alert("请输入计划任务编码！");
			return false;
		}		
		var verifyTaskCodeKey=$("#verifyTaskCodeKey").val();
		if(verifyTaskCodeKey!=1){
			alert("任务编码已被占用，请重新输入！");
			return false;
		}		
		var cron=$("#cron").val();
		if(!cron){
			alert("请输入时间表达式！");
			return false;
		}		

		return true;
	}

	function verifyTaskCode(){
		var taskCode=$("#taskCode").val();	
		var jobId="";
		if($("#jobId").length) {
			jobId=$("#jobId").val();
		}
		if(taskCode!=""){
			$.post("<%= request.getContextPath() %>/job/verifyTaskCode.do",{"taskCode":taskCode,"jobId":jobId},verifyTaskCodeResults,"json");
		}
	}
	
	function verifyTaskCodeResults(json){
		
		if(json==1){
			//alert("任务编码唯一，可以使用！");
			$("#verifyTaskCodeKey").val(1);
			$("#codeKey").css("color","#339900");
			$("#codeKey").html("√");
		}else{
			alert("任务编码已被占用，请重新确定！");
			$("#verifyTaskCodeKey").val(0);
			$("#codeKey").css("color","#ff0000");
			$("#codeKey").html("×");

			//$("#taskCode")[0].focus();  
		}
	
	} 
	

</script>
</head>
<body>
	<div class="rightContent">
		<div class="path">您的位置：计划任务管理&nbsp;&gt;&nbsp;<c:choose> <c:when test="${job!=null}">修改任务</c:when><c:otherwise>添加任务</c:otherwise></c:choose></div>
		<div class="clr"></div>
		<form action="<%= request.getContextPath() %>/job/savejob.do" method="post" onsubmit="return verify();">
        <div class="editInfoMap">
			<ul >
				<c:if test="${job!=null}">
          		<li  class="monopolize">
                <span class="monopolize_name">任务编号：</span>
                <span class="monopolize_content">
                	${job.id}
                <input type="hidden" id="jobId"   name="jobId" value="${job.id}"/>
                </span>
                </li>
                </c:if>
            	<li >
                <span class="name">任务名称：</span>
                <span class="content">
                	<input type="text"  value="${job.taskName}" id="taskName" name="taskName" /><font color="#ff0000">*</font>
                </span>
                </li>
            	<li >
                <span class="name">任务编码：</span>
                <span class="content">
                <input type="text"  value="${job.taskCode}" id="taskCode" name="taskCode" onblur="verifyTaskCode();"/><font color="#ff0000">*</font>
                <font  id="codeKey" style="font-weight: bold;color:#339900"></font>
                <input type="hidden" id="verifyTaskCodeKey" name="verifyTaskCodeKey" value="1"/>
                </span>
                </li>    
				<li >
                <span class="name">计划状态：</span>
                <span class="content" id="status">
				    <select name="isEnable" id="isEnable" >
						<option <c:if test="${job.isEnable}">selected="selected"</c:if>  value="true">启用</option>
						<option <c:if test="${!job.isEnable}">selected="selected"</c:if>  value="false">停用</option>
					</select><font color="#ff0000">*</font>
				</span>
                </li>       
             	<li >
                <span class="name">流水号顺序：</span>
                <span class="content" id="status">
                   <select name="isOrder" id="isOrder" >
						<option <c:if test="${job.isOrder}">selected="selected"</c:if>  value="true">是</option>
						<option <c:if test="${!job.isOrder}">selected="selected"</c:if>  value="false">否</option>
					</select><font color="#ff0000">*</font>
				</span>
                </li>
            	<li  class="monopolize">
                <span class="monopolize_name">时间表达式：</span>
                <span class="monopolize_content">
                	<input type="text"  value="${job.cron}" id="cron" name="cron" /><font color="#ff0000">*</font>
                </span>
                </li>
            	<li  class="monopolize">
                <span class="monopolize_name">备注说明：</span>
                <span class="monopolize_content">
                	<textarea  id="memo" name="memo">${job.memo}</textarea>
                </span>
                </li>                  
            </ul>
		</div>
		<div class="clr"/>
		<div class="bottomButton">
		<input type="submit"  value="提  交"/>
		<input type="button" value="返  回" onclick="history.back();return false;"/>
		</div>
		</form>
	</div>
</body>
</html>




