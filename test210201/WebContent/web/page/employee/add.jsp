<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/web/header.jsp"%>
<title>添加记录</title>
</head>
<body>
	<fieldset class="layui-elem-field" style='margin:20px;padding:20px'>
		<legend>信息维护</legend>
		<div class="layui-field-box">


			<form class="layui-form layui-form-pane" >

				<div class="layui-form-item">
					<label class="layui-form-label">编号</label>
					<div class="layui-input-inline">
						<input type="text" name="code" required lay-verify="required"
							placeholder="请输入员工编号" autocomplete="off" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">姓名</label>
					<div class="layui-input-inline">
						<input type="text" name="name" required lay-verify="required"
							placeholder="请输入员工姓名" autocomplete="off" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">密码</label>
					<div class="layui-input-inline">
						<input type="text" name="pass" 
							placeholder="请输入密码" autocomplete="off" class="layui-input">
					</div>
				</div>


				

				<div class="layui-form-item">
					<div class="layui-input-inline">

						<input type="button" value='确定' lay-submit lay-filter="add"
							class="layui-btn"> 
						<input type="reset" value="重置"
							class="layui-btn layui-btn-primary">
					</div>
					<input type="button" value='关闭' onclick='toClose()'
							class="layui-btn"> 
				</div>
               <input type="hidden" name='action' value='add'>

			</form>

		</div>
	</fieldset>
	<script type="text/javascript">
	formOn('/employee','submit(add)','text',function(data){
		  //alert(data)
		  
		if("1"== data){
			layer.msg("添加员工成功",toClose)
		}else if("E" == data){
			layer.msg("员工编号重复")
		}else{
			layer.msg("添加员工失败")
		}

	})
	
	
	
	
	function toClose(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭 
	}
	
	
	
	</script>
</body>
</html>