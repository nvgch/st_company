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


	<fieldset class="layui-elem-field layui-field-title"
		style='margin: 10px; padding: 15px'>
		<legend>图片维护</legend>
		<div class="layui-field-box">
			<button type="button" class="layui-btn" id="test1" >
				<i class="layui-icon layui-icon-upload"></i>上传图片
			</button>
			<div class="layui-upload-list">
				<table class="layui-table">
					<thead>
						<tr>
							<th>大小</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="demoList"></tbody>
				</table>
			</div>
		</div>
	</fieldset>
	<script type="text/javascript">
	var code='<%=request.getParameter("code") %>'
	
	layui.use('upload', function(){
  var upload = layui.upload;
   
  //执行实例
  var uploadInst = upload.render({
    elem: '#test1' //绑定元素
    ,url: '/test210201/EmployeeUpload?code='+code //上传接口
    ,data:code
    ,type:"post"
    ,done: function(res,index,upload){
      //上传完毕回调
      if(res.code=='1'){
    	  layer.msg('上传成功')
      }
    }
    ,error: function(){
      //请求异常回调
    }
  });
});
	
	</script>
</body>
</html>