<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/web/header.jsp" %>


<title>系统注册</title>
</head>
<body>

	<fieldset class="layui-elem-field" style='margin:20px;padding:20px'>
		<legend>字段集区块 - 默认风格</legend>
		<div class="layui-field-box">


			<form class="layui-form layui-form-pane" >

				<div class="layui-form-item">
					<label class="layui-form-label">账户</label>
					<div class="layui-input-inline">
						<input type="text" name="code" required lay-verify="required"
							placeholder="请输入账户" autocomplete="off" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">密码</label>
					<div class="layui-input-inline">
						<input type="password" name="pass" required lay-verify="required"
							placeholder="请输入密码" autocomplete="off" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">姓名</label>
					<div class="layui-input-inline">
						<input type="text" name="name" required lay-verify="required"
							placeholder="请输入姓名" autocomplete="off" class="layui-input">
					</div>
				</div>

<div class="layui-form-item">
					<label class="layui-form-label">
					<img alt="" src="/test210201/AuthCodeServlet"
					onclick="this.src='/test210201/AuthCodeServlet?'+Math.random();">
					
					
					</label>
					<div class="layui-input-inline">
						<input type="text" name="authCode" required lay-verify="required"
							placeholder="请输入验证码" autocomplete="off" class="layui-input">
					</div>
				</div>
				

				<div class="layui-form-item">
					<div class="layui-input-inline">

						<input type="button" value='注册' lay-submit lay-filter="reg"
							class="layui-btn"> 
						<input type="reset" value="重置"
							class="layui-btn layui-btn-primary">
					</div>
					<input type="button" value="跳转登录" onclick="toJsp('/test210201/web/login.jsp')"
							class="layui-btn">
				</div>
               <input type="hidden" name='action' value='reg'>

			</form>

		</div>
	</fieldset>
<script type="text/javascript">







/* var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer; */


/* form.on('submit(reg)',function(data){
	
	

      $.ajax({
    	  type:"post",
    	  data:data.field,
    	  url:base.app+"/employee",
    	  dataType:"text",
    	  success:function(data){
    		  //alert(data)
    		  
    		  if('no'==data){
    			  layer.msg("验证码错误");
    		  }
    		  else if(1 == data){
    			  layer.msg("注册成功")
    		  }else{
    			  layer.msg("注册失败")
    		  }

    	  }
      });	
}) */
formOn('/employee','submit(reg)','text',function(data){
	  //alert(data)
	  
	 if('no'==data){
		  layer.msg("验证码错误");
	  }
	  else if(1 == data){
		  layer.msg("注册成功")
	  }
	  else if('E'==data){
		  layer.msg("账号重复");
	  }
	  else{
		  layer.msg("注册失败")
	  }

})



</script>
</body>
</html>