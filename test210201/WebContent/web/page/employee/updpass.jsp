<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/web/header.jsp"%>
<title>Insert title here</title>
</head>
<body>
<fieldset class="layui-elem-field" style='margin:20px;padding:20px'>
		<legend>信息维护</legend>
		<div class="layui-field-box">


			<form class="layui-form layui-form-pane" lay-filter='formA'>

				<div class="layui-form-item">
					<label class="layui-form-label">员工编号</label>
					<div class="layui-input-inline">
						<input type="text" name="code" required lay-verify="required" readonly
							placeholder="请输入员工编号" autocomplete="off" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">员工密码</label>
					<div class="layui-input-inline">
						<input type="password" name="name" required lay-verify="required"  
							placeholder="请修改员工密码" autocomplete="off" class="layui-input">
					</div>
				</div>

				

				

				<div class="layui-form-item">
					<div class="layui-input-inline">

						<input type="button" value='确定' lay-submit lay-filter="updpass"
							class="layui-btn"> 
						<input type="reset" value="重置"
							class="layui-btn layui-btn-primary">
					</div>
					<input type="button" value='关闭' onclick='toClose()'
							class="layui-btn"> 
				</div>
               <input type="hidden" name='action' value='updpass'>

			</form>

		</div>
	</fieldset>
	
	<script type="text/javascript">
	init();
	function init(){
		var code = '<%=request.getParameter("code")%>';
		$("input[name='code']").val(code);
		/* ajax('/employee',{code:code,action:'get'},'json',function(data){
			console.log(data)
			form.val("formA",data);
		}) */
	}
	formOn('/employee','submit(updpass)','text',function(data){
		  //alert(data)
		  alert(data)
		if("1"== data){
			layer.msg("修改成功",toClose)
		}else {
			layer.msg("修改失败")
		}

	})
	
	function toClose(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭 
	}
	</script>
	
</body>
</html>