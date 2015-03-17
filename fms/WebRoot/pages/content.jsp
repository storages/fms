<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <jsp:include page="/pages/head/head.jsp"></jsp:include> --%>
<style type="text/css">
	a:hover{color:green;}
</style>

<a href="javascript:location.replace(url)"></a>
<!-- 左边菜单树 -->
<div class="navbar">
	<div class="navbar-inner" style=" background-image: url('${pageContext.request.contextPath}/images/head_bg.png'); background-size:100%; background-repeat:no-repeat;" id="setcss">
		<!-- <div class="container-fluid"></div> -->
			<%-- <img alt="公司标志" src="${pageContext.request.contextPath}/imges/ls_logo.png" style="width: 60px; height:50px; margin-top:5px; margin-bottom:5px;  margin-left:25px; float:left;"> --%>
				<!--/.brand-->
				
				<ul style="float: right;height:auto; margin: 0px 5px 0px 0px; padding:0px;">
					<li style="list-style: none; line-height:60px; color: white; font-size: 12px; float: left;">欢迎您：${u.loginName}</li>
					<c:if test="${u.userFlag=='L'}">
						<li style="list-style: none; line-height:60px; color: white; font-size: 12px;float: left;">　　您是【超级用户】　<a href="" style="color:white;">注销</a></li>
					</c:if>
					<c:if test="${u.userFlag=='S'}">
						<li style="list-style: none; line-height:60px; color: white; font-size: 12px;float: left;">　　您是【管理员】　<a href="" style="color:white;">注销</a></li>
					</c:if>
					<c:if test="${u.userFlag=='P'}">
						<li style="list-style: none; line-height:60px; color: white; font-size: 12px;float: left;">　　您是【普通用户】　<a href="" style="color:white;">注销</a></li>
					</c:if>
					<li style="list-style: none; line-height:60px; color: white; font-size: 12px;float: left;"><a href="${pageContext.request.contextPath}/test.jsp">去测试页面</a></li>
				</ul>
	</div>
</div>
<div class="sidebar" id="sidebar" style="left: 2px; margin-top: 70px;">
	<div id="main" style=" height:100%; overflow:auto;">
	
	<p style="font-size:15px; font-weight: bold; margin:5px 0px 0px 5px;">联硕塑胶制品公司</p>
	<ul id="browser" class="filetree">
		<li  class="closed"><span class="folder directer" >用户管理</span>
			<ul>
				<li><span class="file"><a href="javascript:void(0);" data-url="/pages/register.jsp" onclick="toMain('${pageContext.request.contextPath}/pages/register.jsp')">添加用户</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" onclick="findAllUserByFlag('${u.userFlag}')">用户权限</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" data-url="">用户分组</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" onclick="">操作日志</a></span></li>
			</ul>
		</li>
		<li  class="closed"><span class="folder directer">基础资料</span>
			<ul>
				<li><span class="file"><a href="javascript:void(0);" data-url="/params_getParameterValue.action">系统参数设置</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" data-url="/scmcoc_findAllScmcoc.action?isCustom=false">供应商管理</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" data-url="/scmcoc_findAllScmcoc.action?isCustom=true">客户管理</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" data-url="/dept_findAllDept.action" >部门管理</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" data-url="/settl_findAllSett.action" >结算方式</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" data-url="/currencies_findAllCurrencies.action" >交易货币管理</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" data-url="/unit_findAllUnit.action">计量单位管理</a></span></li>
				<!-- <li  class="closed"><span class="folder directer">仓库设置</span>
					<ul> -->
						<li><span class="file"><a href="javascript:void(0);" data-url="/stock_findAllStock.action" >仓库管理</a></span></li>
					<!-- </ul>
				</li> -->
			</ul>
		</li>
		<li  class="closed"><span class="folder directer">职员管理</span>
			<ul>
				<li><span class="file"><a href="javascript:void(0);" data-url="/empl_employees.action">职员信息</a></span></li>
			</ul>
		</li>
		<li><span class="file"><a href="javascript:void(0);" data-url="/quotation_findQuotations.action" >报价单</a></span></li>
		<li><span class="file"><a href="javascript:void(0);" data-url="/appbill_findAppBillHeads.action" >申请单</a></span></li>
		<li><span class="file"><a href="javascript:void(0);" data-url="/purchase_findPurchaseHeads.action" >采购单</a></span></li>
		<li  class="closed"><span class="folder directer">物料</span>
			<ul>
				<li><span class="file"><a href="javascript:void(0);" data-url="/bom_findBomExg.action">物料BOM表管理</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" data-url="/mater_findAllMaterialType.action">物料分类</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" data-url="/materInfo_findAllMaterial.action?imgExgFlag=I">物料清单</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" data-url="/storage_findAllInStorage.action">入库</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" >出库</a></span></li>
			</ul>
		</li>
		<li><span class="file"><a href="javascript:void(0);" data-url="#" >订单管理</a></span></li>
		
		<li class="closed"><span class="folder directer">报表查询</span>
			<ul>
				<li><span class="file"><a href="#">采购统计</a></span></li>
				<li><span class="file"><a href="#">订单统计</a></span></li>
			</ul>
		</li>
		<li class="closed"><span class="folder directer">外发加工</span>
			<ul>
				<li><span class="file"><a href="#">外发记录</a></span></li>
			</ul>
		</li>
		
		<li class="closed"><span class="folder directer">财务管理</span>
			<ul>
				<li><span class="file"><a href="">收款</a></span></li>
				<li><span class="file"><a href="">付款</a></span></li>
			</ul>
		</li>
		<li class="closed"><span class="folder directer">账单管理</span>
			<ul>
				<li><span class="file"><a href="">对账清单</a></span></li>
				<li><span class="file"><a href="">月度收款</a></span></li>
				<li><span class="file"><a href="">月度付款</a></span></li>
			</ul>
		</li>
	</ul>
	
	</div>
</div>



<!-- 右边主功能区 style="OVERFLOW-Y: auto; OVERFLOW-X:hidden;" -->
<div class="page-content" style="margin-left: 192px; height:89%; padding:0px; font-size: 12px; OVERFLOW-Y: auto; OVERFLOW-X:hidden;" id="tomain" >
	<jsp:include page="/pages/templet/templet.jsp"></jsp:include>
</div>
<!--/.page-content-->

<script type="text/javascript">
  $("a[data-url]").click(function(){
  var $tag=$(this);
  var data_url= $tag.attr("data-url");
  toMain(Global+data_url);
  });
	function findAllUserByFlag(flag){
		if("P"==flag || flag==""){
			alert("对不起，你没有权限查看！");
			return;
		}
		var url = "${pageContext.request.contextPath}/users_findAllUser.action";
		toMain(url);
	}
	
</script>

