<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"   uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%= request.getContextPath()%>/css/simple.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%= request.getContextPath()%>/js/jquery-1.8.0.min.js"></script>
<title>要客特征管理 -- 特征详细信息</title>
<script type="text/javascript">

	function modEdit(obj,module){
		$(module).find("#serialNumber").removeAttr("disabled"); 
		$(module).find("#requestType").removeAttr("disabled"); 
		$(module).find("#url").removeAttr("disabled"); 
		$(module).find("#parameter").removeAttr("disabled"); 
		$(module).find("#submitSpan").html(" <input type='submit' value='保&nbsp;&nbsp;存'  />");
	}

	
	function verify(module){
		var requestType=$(module).find("#requestType").val();
		if(!requestType){
			alert("请选择请求方式！");
			return false;
		}
		var url=$(module).find("#url").val();
		if(!url){
			alert("请输入请求地址！");
			return false;
		}		
		
		return true;
	}
	
	function editInfo(){
		var jobId=$("#jobId").val();
		window.location.href="<%= request.getContextPath() %>/job/editJob.do?jobId="+jobId;
		
	}
	
	function delHttpRequest(httpRequestId){
		window.location.href="<%= request.getContextPath() %>/job/delHttpRequest.do?httpRequestId="+httpRequestId;

	}
	
	
	$(document).ready(function(){
		var resultsInfo=$("#resultsInfo").val();
		if(resultsInfo!=null && resultsInfo!=""){
			alert(resultsInfo);
		}
		
	}); 
</script>
</head>
<body>
	<div class="rightContent">
		<div class="path">您的位置：计划任务管理&nbsp;&gt;&nbsp;任务详情</div>
		<div class="clr"></div>
		<div class="subTitle">
			<span class="title">基本信息</span>
			<span class="function" id="infoButton">
				<input type="button" value="修改信息" onclick="editInfo();"/>
			</span>
		</div>
		<div class="clr"></div> 
        <div class="infoMap">
			<ul >
            	<li >
                <span class="name">任务编号：</span>
                <span class="content">
                ${job.id}
                </span>
                </li>
            	<li  >
                <span class="name">任务名称：</span>
                <span class="content">
                	${job.taskName}
                </span>
                </li>
            	<li >
                <span class="name">任务编码：</span>
                <span class="content">
                ${job.taskCode}            
                </span>
                </li>    
            	<li >
                <span class="name">流水号顺序：</span>
                <span class="content" id="status"><c:choose><c:when test="${job.isOrder}">是</c:when><c:otherwise>否</c:otherwise></c:choose>
				</span>
                </li>
            	<li >
                <span class="name">计划状态：</span>
                <span class="content" id="status">
				 <c:choose>
					 <c:when test="${job.isEnable}">
					 	启用
					 </c:when>
					 <c:otherwise>
					 	停用
					 </c:otherwise>
				 </c:choose>
				</span>
                </li>                
            	<li >
                <span class="name">运行状态：</span>
                <span class="content">${job.status}</span>
                </li>
            	<li  class="monopolize">
                <span class="monopolize_name">时间表达式：</span>
                <span class="monopolize_content">
                	 ${job.cron}
                </span>
                </li>
            	<li  class="monopolize">
                <span class="monopolize_name">备注说明：</span>
                <span class="monopolize_content">
                	<span  class="showMemo">${job.memo}</span>
                </span>
                </li>                  
            </ul>
		</div>
		<div class="clr dashed">&nbsp;</div> 
		<div class="subTitle">
		<span class="title">HTTP请求信息</span>
		<span class="function"> </span>
		</div>
		<div class="editHttpRequest">
			<c:forEach items="${httpRequestList}" var="httpRequest">
			<div class="httpRequestInfo" id="edit${httpRequest.id}">
			<form action="<%= request.getContextPath() %>/job/saveHttpRequest.do" method="post" onsubmit="return verify('#edit${httpRequest.id}');">
			<ul>
				<li >
                <span class="name">流&nbsp;水&nbsp;号：</span>
                <span class="short_content">
					<input type="text" disabled="disabled" onkeyup="value=this.value.replace(/\D+/g,'')" name="serialNumber" id="serialNumber"  value="${httpRequest.serialNumber}"/>
					<font color="#ff0000">*</font>
				</span>
                <span class="name">请求方式：</span>
                <span class="short_content">
					<select id="requestType" name="requestType"  disabled="disabled">
						<option value="">请选择</option>
	                	<c:forEach items="${requestTypeList}" var="rt">
							<option <c:if test="${rt.value==httpRequest.requestType}">selected="selected"</c:if> value="<c:out value="${rt.value}"/>"><c:out value="${rt.name}"/></option>
						</c:forEach>
					</select><font color="#ff0000">*</font>
				</span>
                </li>
				<li >
                <span class="name">请求地址：</span>
                <span class="content">
					<input type="text" disabled="disabled" name="url" id="url"  value="${httpRequest.url}"/><font color="#ff0000">*</font>
				</span>
				<span class="button" id="submitSpan">
					  <input type="button" value="修&nbsp;&nbsp;改"  onclick="modEdit(this,'#edit${httpRequest.id}');"/>
                </span>
				</li>
				<li>
                <span class="name">请求参数：</span>
                <span class="content">
                	<textarea name="parameter" id="parameter" disabled="disabled">${httpRequest.parameter}</textarea>
                	<br /><font color="#ff0000">&nbsp;&nbsp;JSON数据格式</font>
				</span>
				<span class="button" style="padding-top: 10px;">
					  <input type="hidden" value="${job.id}" name="jobId"/>
					  <input type="hidden" value=" ${httpRequest.id}" name="httpRequestId"/>
					  <input type="button" value="删&nbsp;&nbsp;除"  onclick="delHttpRequest(<c:out value="${httpRequest.id}"/>);"/>
                </span>
                </li> 
			</ul>
			</form>
			<div class="clr subDashed"></div>
			</div>
			</c:forEach>
			
			<div class="httpRequestInfo" id="newEdit">
			<form action="<%= request.getContextPath() %>/job/saveHttpRequest.do" method="post" onsubmit="return verify('#newEdit');">
			<ul>
				<li >
                <span class="name">流&nbsp;水&nbsp;号：</span>
                <span class="short_content">
					<input type="text"  name="serialNumber" onkeyup="value=this.value.replace(/\D+/g,'')" id="serialNumber"  value="<c:out value="${fn:length(httpRequestList)+1}"/>"/>
					<font color="#ff0000">*</font>
				</span>
                <span class="name">请求方式：</span>
                <span class="short_content">
					<select id="requestType" name="requestType"  >
						<option value="">请选择</option>
	                	<c:forEach items="${requestTypeList}" var="rt">
							<option  value="<c:out value="${rt.value}"/>"><c:out value="${rt.name}"/></option>
						</c:forEach>
					</select><font color="#ff0000">*</font>              
				</span>
                </li>
				<li >
                <span class="name">请求地址：</span>
                <span class="content">
					<input type="text" name="url" id="url"  value=""/><font color="#ff0000">*</font>              
				</span>
				</li>
				<li>
                <span class="name">请求参数：</span>
                <span class="content">
                	<textarea name="parameter" id="parameter"></textarea>
                	<br /><font color="#ff0000">&nbsp;&nbsp;JSON数据格式</font>
				</span>
				<span class="button">
					<input type="hidden" value="${job.id}" name="jobId"/>
					  <input type="submit" value="添&nbsp;&nbsp;加"/>
                </span>
                </li> 
			</ul>
			</form>
			</div>
			<div class="clr"></div>
		</div>	
		<div class="clr dashed">&nbsp;</div>
		<div class="bottomButton">
		<input type="hidden" value="${job.id}" id="jobId" name="jobId"/>
		<input type="button" value="返  回" onclick="window.location.href='<%= request.getContextPath() %>/job/pagedJobList.do'"/>
		</div>
	</div>
	<div>
		 <input type="hidden" id="resultsInfo" value="${resultsInfo}"/>
	</div>
</body>
</html>




