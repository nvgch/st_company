function toJsp(url){
	location.href = base.app + url; 
}

/* function toJspLogin(){
//location.href = base.app+'/web/login.jsp'
toJsp('/web/login.jsp);
} */

var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
var element = layui.element;


function openadd(){
	layer.open({
		type:2,
		area:['750px','550px'],
		maxmin:true,
		content:base.app+"/web/page/department/add.jsp"
	})
}

function formOn(url,event,dataType,func){
	form.on(event,function(data){
		
		ajax(url,data.field,dataType,func)
		
	})
}
function ajax(url,field,dataType,func){
	$.ajax({
	    	  type:"post",
	    	  data:field,
	    	  url:base.app+url,
	    	  dataType:dataType,
	    	  success:func
	      });
}
