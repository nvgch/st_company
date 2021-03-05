function toJsp(url){
	location.href = url; 
}

/* function toJspLogin(){
//location.href = base.app+'/web/login.jsp'
toJsp('/web/login.jsp);
} */

var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
var element = layui.element;
var laytpl=layui.laytpl;
var laypage = layui.laypage;

/*function setPageInfo(elem, count, curr, limit, jump){
	laypage.render({
		elem : elem,
		count : count,
		limits : [ 10, 20, 30, 40, 50],
		layout:['count','prev','page','next','limit','refresh','skip'],
	    curr : curr,
	    limit :limit,
	    jump :jump
	})
}*/
function kk(){
	alert(555)
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
