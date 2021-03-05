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
	<div class="layui-collapse">
		<div class="layui-colla-item">
			<h2 class="layui-colla-title">部门信息</h2>
			<div class="layui-colla-content layui-show">
				内容区域
				<fieldset class="layui-elem-field layui-field-title">
					<legend>查询条件</legend>
					<div class="layui-field-box">
						<form class="layui-form">
							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label">部门编号</label>
									<div class="layui-input-inline">
										<input type="text" name="code" placeholder="请输入部门编号"
											autocomplete="off" class="layui-input">
									</div>
									<label class="layui-form-label">部门名称</label>
									<div class="layui-input-inline">
										<input type="text" name="name" placeholder="请输入部门名称"
											autocomplete="off" class="layui-input">
									</div>


									<div class="layui-input-inline">
										<input type="button" class="layui-btn" lay-submit
											lay-filter='sel' value="查询"> <input type="reset"
											class="layui-btn  layui-btn-primary" value="重置">
									</div>
									<input type="button" class="layui-btn" onclick='openAdd()'
										value="添加">
								</div>
							</div>
							<input type="hidden" name='action' value='list'> <input
								type="hidden" name='pageIndex' value='1'> <input
								type="hidden" name='pageLimit' value='10'>


						</form>
					</div>


				</fieldset>

			</div>
		</div>

	</div>
	<table class="layui-table">
		<colgroup>
			<col width="10%">
			<col width="20%">
			<col width="20%">
			<col width="20%">
			<col width="20%">
		</colgroup>
		<thead>
			<tr>
				<th>序号</th>
				<th>编号</th>
				<th>名称</th>
				<th>电话</th>
				<th>操作</th>
			</tr>

		</thead>
		<tbody id='user_tbody'>


		</tbody>
	</table>
	<div id="pageInfo" style="text-align: right; padding-right: 30px"></div>

	<script type="text/javascript">
		element.render();

		formOn('/department', 'submit(sel)', 'json', function(data) {
			var pageIndex = $("input[name='pageIndex']").val();
			var pageLimit = $("input[name='pageLimit']").val();
			var html = ''
				$.each(data.data, function(index, element) {
					element.id = (index + 1)+(pageIndex-1)*pageLimit
					html += laytpl($("#tradd").html()).render(element)
				})
				$("#user_tbody").html(html);
			
			
			//alert(data)
			console.log(data)
			var pageIndex = $("input[name='pageIndex']").val();
			var pageLimit = $("input[name='pageLimit']").val();
			laypage.render({
				elem : 'pageInfo',
				count : data.count //数据总数，从服务端得到
				,
				limits : [ 10, 20, 30, 40, 60, 70, ],
				layout : [ 'count', 'prev', 'page', 'next', 'limit', 'refresh',
						'skip' ],
				curr : pageIndex,
				limit : pageLimit,
				jump : function(obj, first) {
					//obj包含了当前分页的所有参数，比如：

					$("input[name='pageIndex']").val(obj.curr);
					$("input[name='pageLimit']").val(obj.limit);
					//首次不执行
					if (!first) {
						//do something
						refresh()
					}
				}
			});

			

			/* $.each(
							data,
							function(index, d) {
								var html1 = '<tr>'
										+ '<td>'
										+ (index + 1)
										+ '</td>'
										+ '<td>'
										+ d.code
										+ '</td>'
										+ '<td>'
										+ d.name
										+ '</td>'
										+ '<td>'
										+ d.tel
										+ '</td>'
										+ '<td>'
										+ "<input onclick='del(\"{{d.code}}\")' type='button' value='删除'  class='layui-btn layui-btn-sm layui-btn-danger'>"
										+ '</td>' + '</tr>'
								html += html1;
							})
			 */
		})

		refresh();
		function refresh() {
			$("input[value='查询']").click();
		}
		function openAdd() {
			layer.open({
				type : 2,
				area : [ '750px', '550px' ],
				maxmin : true,
				end : function() {
					refresh();
				},
				content : "/test210201/web/page/department/add.jsp"
			})

		}
		function openUpd(code) {
			layer.open({
				type : 2,
				area : [ '750px', '550px' ],
				maxmin : true,
				end : function() {
					refresh();
				},
				content : "/test210201/web/page/department/upd.jsp?code="
						+ code
			})

		}

		function del(code) {
			layer.confirm("确定进行该操作？", {
				icon : 3,
				title : '提示'
			}, function(index) {
				ajax('/department', {
					code : code,
					action : 'del'
				}, 'text', function(data) {
					if (1 == data)
						layer.msg('删除成功', refresh)
					else {
						layer.msg('删除失败')
					}
				})
			});
		}
	</script>
	<script type="text/html" id="tradd">
			<tr>
			<td>{{ d.id }}</td>
			<td>{{ d.code }}</td>
			<td>{{ d.name }}</td>
			<td>{{ d.tel }}</td>
			<td><input onclick="del('{{d.code}}')" type="button" value="删除" class="layui-btn layui-btn-sm layui-btn-danger">
			&nbsp;
            <a href="javascript:del('{{d.code}}');" class="layui-btn layui-btn-sm layui-btn-danger">
            <i class='layui-icon layui-icon-delete'></i>
            </a>

            &nbsp;
			<input ONCLICK='openUpd("{{d.code}}")' type="button" value="修改" class="layui-btn layui-btn-sm" />
			
			
			</td>
			</tr>
			
		</script>

</body>
</html>