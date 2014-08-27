<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
.pages {float:right; width:auto; height:auto; margin:10px 10px 10px 0px;
 font-size:12px;}
.pages ul{
    float: right;
    width: auto;
    height: 20px;
}
.pages ul li {
    cursor:default;
    margin-left: 4px;
    float: left;
    list-style:none; 
    height: 20px;
    line-height:20px;
    background-color: #ffffff;
    border: 1px solid #5a686b;
    
}
.pages span{
    margin-left:5px;
    margin-right:5px;
    color:#000099;
    display:"";
}
.pages input[type="text"]{
    padding:0px;
    width:32px;
    min-height:20px;
} 

.pages input[type="button"]{
	width: 25px;
	height: 22px;
	min-height:22px;
    padding:0px;
}


</style>

<script type="text/javascript">
	function enterPress(e){ 
		var e = e || window.event; 
		if(e.keyCode == 13){
			jumppage(); 
		} 
	}
	
	//输入页面跳转
	function jumppage(){
		var page=$("#inputpage").val();
		var totalPages=$("#maxpage").val();
		if (parseInt(page)>0 &&parseInt(page)<=parseInt(totalPages)){
			selectpage(page);
		}else{
			if(parseInt(totalPages)==1){
				alert("数据只能以一页显示！");
			
			}else{
		    	alert("请输入1至"+totalPages+"之间的页数！");
		    }
		}
	}    
	//页面跳转
	function selectpage(page){
		window.location.href="<%= request.getContextPath() %>${pageController.url}?page="+page;
	}

</script>

<div class="pages">
	<ul >
		<c:choose>
		<c:when test="${pageController.currentPage>1}">
			<li style="cursor: pointer;" onclick="selectpage(1);"><span>首页</span></li>
			<li style="cursor: pointer;" onclick="selectpage(<c:out value="${pageController.currentPage-1}"/>);" ><span>上页</span></li>
		</c:when>
		<c:otherwise>
			<li style="color:#cccccc;">&nbsp;首页&nbsp;</li>
			<li style="color:#cccccc;">&nbsp;上页&nbsp;</li>
		</c:otherwise>
		</c:choose>
		<c:forEach items="${pageController.pageViewList}" varStatus="status" var="pageView" >
			<c:choose>
			<c:when  test="${pageController.pageViewList[status.index]==pageController.currentPage}">
				<li  id="currentpage" style="background-color:#333333;"><span style="font-weight:bolder;color:#ffffff;"><c:out value="${pageController.pageViewList[status.index]}"/></span></li> 
			</c:when>
			<c:when test="${pageController.pageViewList[status.index]=='...' }">
				<li style="border: 0px;line-height:36px;background-color:transparent;"><c:out value="${pageController.pageViewList[status.index]}"/></li>
			</c:when>
			<c:otherwise>
				<li style="cursor: pointer;" onclick="selectpage(<c:out value="${pageController.pageViewList[status.index]}"/>);" ><span><c:out value="${pageController.pageViewList[status.index]}"/></span> </li> 
			</c:otherwise>
			</c:choose>
	    </c:forEach>
	    
	    <c:choose>
		<c:when test="${pageController.currentPage<pageController.totalPages}">
			<li style="cursor: pointer;" onclick="selectpage(<c:out value="${pageController.currentPage+1}"/>);"><span>下页</span></li>
			<li style="cursor: pointer;" onclick="selectpage(<c:out value="${pageController.totalPages}"/>);" ><span>末页</span></li>
		</c:when>
		<c:otherwise>
			<li style="color:#cccccc;">&nbsp;下页&nbsp;</li>
			<li style="color:#cccccc;">&nbsp;末页&nbsp;</li>
		</c:otherwise>
		</c:choose>
	    <li style="border: 0px;background-color:transparent;" >
	    <input type="hidden" id="page"  value="${pageController.currentPage}"/>	
	    <input type="hidden" id="currentPage"  value="${pageController.currentPage}"/>	
		<input type="hidden" id="maxpage"  value="${pageController.totalPages }"/>	    
	   	 转到:
	    <input type="text"  id="inputpage" onkeyup="value=this.value.replace(/\D+/g,'')" value="" onkeypress="enterPress(event);" />
	    <input type="button"  class="go" onclick="jumppage();" value="GO"/>
	    </li>
	</ul> 
</div>

