<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<link rel="stylesheet" href="/test210201/layui-v2.5.7/layui/css/layui.css"
	type="text/css" />
<script type="text/javascript"
	src="/test210201/layui-v2.5.7/layui/layui.all.js"></script>



<title>系统登录</title>
</head>
<body>

	<fieldset class="layui-elem-field" style='margin:20px;padding:20px'>
		<legend>系统登陆</legend>
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

						<input type="button" value='登陆' lay-submit lay-filter="login"
							class="layui-btn"> 
						<input type="reset" value="重置"
							class="layui-btn layui-btn-primary">
					</div>
					<input type="button" value="跳转注册" onclick="toJspReg()"
							class="layui-btn">
				</div>
               <input type="hidden" name='action' value='login'>

			</form>

		</div>
	</fieldset>
<script type="text/javascript">
function toJspReg(){
	location.href = base.app+'/web/reg.jsp'
}





/* var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer; */

/* 
form.on('submit(login)',function(data){
	
	console.log(data.field);

      $.ajax({
    	  type:"post",
    	  data:data.field,
    	  url:"/test210201/employee",
    	  dataType:"text",
    	  success:function(data){
    		  //alert(data)
    		  
    		  if('no'==data){
    			  layer.msg("验证码错误");
    		  }
    		  else if(1 == data){
    			  layer.msg("登陆成功")
    		  }else{
    			  layer.msg("登陆失败")
    		  }

    	  }
      });	
}) */

formOn('/employee','submit(login)','text',function(data){
	  //alert(data)
	  
	  if('no'==data){
		  layer.msg("验证码错误");
	  }
	  else if('1' == data){
		  layer.msg("登陆成功")
		  toJsp('/web/page/main.jsp')
	  }
	  else if('0' == data){
		  layer.msg("账号不存在")
	  }
	  else{
		  layer.msg("密码错误")
	  }

})
</script>
</body>
</html>