<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/web/header.jsp" %>
<title>主页面</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">layui 后台布局</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="<%=app %>/web/base/img/logo.ico" class="layui-nav-img">
          用户[${ user.name }]
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">修改资料</a></dd>
          <dd><a href="">安全设置</a></dd>
          <dd><a href="">修改头像</a></dd>
          
        </dl>
      </li>
      <li class="layui-nav-item"><a onclick="toLogout()">注销</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">信息维护</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:openUrl('/web/page/list.jsp');">部门信息维护</a></dd>
            <dd><a href="javascript:openUrl('/web/page/employee/list.jsp');">员工信息维护</a></dd>
            <dd><a href="javascript:;">项目信息维护</a></dd>
            <dd><a href="javascript:;">绩效信息维护</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">部门信息</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">部门信息维护</a></dd>
          
          </dl>
        </li>
        
        <li class="layui-nav-item">
          <a href="javascript:;">员工信息</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">员工信息维护</a></dd>
          
          </dl>
        </li>
        
        <li class="layui-nav-item">
          <a href="javascript:;">项目信息</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">项目信息维护</a></dd>
          
          </dl>
        </li>
         <li class="layui-nav-item">
          <a href="javascript:;">绩效信息</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">绩效信息维护</a></dd>
          
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">其他</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:openUrl('/web/404.jsp');">测试</a></dd>
       
          </dl>
           <dl class="layui-nav-child">
            <dd><a href="javascript:;" class="demoA" data-url="/web/404.jsp">测试2</a></dd>
       
          </dl>
          
        </li>
      
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <iframe name="frameA" width='99%' height='97%'></iframe>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>

<script>
//JavaScript代码区域
function openUrl(url){
	window.open(base.app+url,'frameA')
}
function toLogout(){
	layer.confirm("是否进行注销？",{
		icon:3,
		title:"提示"
	},function(index){
		location.href=base.app+'/employee?action=logout'
	})
}
  var element = layui.element;
  element.render();
</script>
</body>
</html>