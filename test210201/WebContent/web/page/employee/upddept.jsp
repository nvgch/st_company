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
					<label class="layui-form-label">部门</label>
					<div class="layui-input-inline">
						<select name='codeDept'></select>
					</div>
				</div>

				

				

				<div class="layui-form-item">
					<div class="layui-input-inline">

						<input type="button" value='确定' lay-submit lay-filter="upddept"
							class="layui-btn"> 
						<input type="reset" value="重置"
							class="layui-btn layui-btn-primary" onclick="init()">
					</div>
					<input type="button" value='关闭' onclick='toClose()'
							class="layui-btn"> 
				</div>
               <input type="hidden" name='action' value='upddept'>

			</form>

		</div>
	</fieldset>
	
	<script type="text/javascript">
	initSelect();
	function initSelect(){
		ajax('/department',{action:'sel',code:'',name:''},'json',function(data){
			console.log(data)
			var html = '<option value=""></option>'
			$.each(data,function(index,element){
				html += '<option value="'+this.code+'">'+this.name+'</option>'
			})
			$("select[name='codeDept']").html(html);
			form.render();
			init();
		});
	}
	
	
	
	function init(){
		var code = '<%=request.getParameter("code")%>';

		 ajax('/employee',{code:code,action:'get'},'json',function(data){
			console.log(data)
			form.val("formA",data);
		}) 
	}
	formOn('/employee','submit(upddept)','text',function(data){
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