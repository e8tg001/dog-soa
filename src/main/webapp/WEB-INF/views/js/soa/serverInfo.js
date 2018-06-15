var contextPath = "http://127.0.0.1:8081/dog/rest";
$(function () {
	
	//URL传入了ID
	var urlMap = getRequestUrlValue();
	var id = urlMap["id"];
	if (id && id!=undefined && id!='' && id.length>0){
		$('#id').val(id);
		loadData();
		loadAuthorizeList();
	}else {
		alert("未获取到服务端ID，操作失败！");
	}
	
	//添加授权客户端
	$('.btn-glow').click(function() {
        addAuthorize();
    });
    //删除授权客户端
    $('.btn-primary').click(function() {		
		deleteAuthorize($("#authorizeId").val());
	});	
});

/**
 * 加载服端信息
 */
function loadData(){
	var id = $("#id").val();
	var jsonForm = {
		id:id
	}
	$.get(contextPath + "/server/get",jsonForm,function(data){
		if (data){
			$('#serverName').html(data.serverName);
			$('#serverLabel').html(data.serverLabel);
			$('#serverInfo').html(data.serverInfo);
			$('#serverUrl').html(data.serverUrl);
			$('#serverType').html(data.serverType);
			$('#sendType').html(data.sendType);
			$('#systemType').html(autoValidate(data.status,['1','2'],['内部系统','第三方系统']));			
			$('#status').html(autoValidate(data.status,['0','1'],['禁用','启用']));
		}
	});
}

/**
 * 加载服务端授权的客户端列表
 */
function loadAuthorizeList(){
	var id = $("#id").val();
	var jsonForm = {
		id:id
	}
	$.get(contextPath + "/server/getAuthorizeList",jsonForm,function(data){
		var tr_html = "";
		if (data && data[0].id){		
			for (var i=0;i<data.length;i++){
				tr_html += '<tr>';
			    tr_html += '<td><a href="#">' + data[i].ip + '</a></td>';
			    tr_html += '<td>' + data[i].dataTime + '</td>';
			    tr_html += '<td>';
			    tr_html += '	<div class="slider-frame primary">';
				if (data[i].status == '1'){
					tr_html += '        <span data-on-text="开" id="slider_'+data[i].id+'" data-off-text="关" onclick="setClientStatus(\''+data[i].id+'\')" class="slider-button on">开</span>';	
				}else {
					tr_html += '        <span data-on-text="开" id="slider_'+data[i].id+'" data-off-text="关" onclick="setClientStatus(\''+data[i].id+'\')" class="slider-button">关</span>';					
				}
			    tr_html += '	</div>';
			    tr_html += '</td>';
			   	tr_html += '<td></td>';
			   	tr_html += '<td><a data-toggle="modal" data-target="#deleteModal" onclick="setAuthorizeId(\''+data[i].id+'\')"  href="javascript:void(0);">删除</a></td>';
				tr_html += '</tr>';
			}
		}
		$(".table tbody").html('');
		$(".table tbody").append(tr_html); 
	});
}

/**
 * 设置客户端访问权限
 * @param {Object} id
 */
function setClientStatus(id){
	var sliderId = "slider_"+id;
	if ($('#'+sliderId).hasClass("on")) {
		setAuthorizeCloseStatus(id);
    } else {
        setAuthorizeOpenStatus(id);
    }
}

/**
 * 打开客户端访问权限
 * @param {Object} id
 */
function setAuthorizeOpenStatus(id){
	var sliderId = "slider_"+id;
	$.get(contextPath + "/server/setAuthorizeOpenStatus",{id:id},function(data){
		if (data && data == 'success'){			
			$('#'+sliderId).addClass('on').html($('#'+sliderId).data("on-text"));
		}else {
			alert("设置失败！");
		}
	});
}

/**
 * 关闭客户访问权限
 * @param {Object} id
 */
function setAuthorizeCloseStatus(id){
	var sliderId = "slider_"+id;
	$.get(contextPath + "/server/setAuthorizeCloseStatus",{id:id},function(data){
		if (data && data == 'success'){			
			$('#'+sliderId).removeClass('on').html($('#'+sliderId).data("off-text"));  
		}else {
			alert("设置失败！");
		}
	});
}

/**
 * 添加服务端访问授权
 */
function addAuthorize(){
	var id = $('#id').val();
	var ips = myTrim($('#ips').val());	
	if (ips == '' || ips == undefined || ips.length<=0){
		alert("请输入客户端IP，多个IP请用“,”逗号隔开！");
		return false;
	}
	var jsonForm = {
		id:id,
		ips:ips
	}
	$.post(contextPath + "/server/addAuthorize",jsonForm,function(data){
		if (data && data=='success'){
			alert("客户端添加成功！");
			loadAuthorizeList();
		}else {
			alert(data+"客户端添加失败，请确认数据是否合法！");
		}		
	});
}

/**
 * 删除客户端
 * @param {Object} id
 */
function deleteAuthorize(id){
	$.get(contextPath + "/server/deleteAuthorize",{id:id},function(data){
		$('#deleteModal').modal('hide');
		loadAuthorizeList();
	});
}

/**
 * 设置需要删除的ID
 * @param {Object} id
 */
function setAuthorizeId(id){
	$("#authorizeId").val(id);
}

/**
 * 判断值是否与指定参数相同，相同则取对应索引位的转义名称代替
 * @param {Object} value
 * @param {Object} param
 * @param {Object} change
 */
function autoValidate(value,param,change){	
	for (var i=0;i<param.length;i++){
		if (value == param[i]){
			return change[i];
		}
	}	
}

/**
 * 获取URL中的传参
 */
function getRequestUrlValue() {
   var url = location.search; //获取url中"?"符后的字串
   var theRequest = new Object();
   if (url.indexOf("?") != -1) {
      var str = url.substr(1);
      strs = str.split("&");
      for(var i = 0; i < strs.length; i ++) {
         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
      }
   }
   return theRequest;
}

/**
 * 编辑服务信息
 * @param {Object} id
 */
function editServer(){
	var id = $("#id").val();
	window.location.href='createServer.html?id='+id+"&date="+new Date();
}

/*
*	去掉前后空格
* str:字符串
*/
function myTrim(str){  
	return str.replace(/^\s+/,'').replace(/\s+$/,'');   
}



