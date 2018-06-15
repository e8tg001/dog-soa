var contextPath = "http://127.0.0.1:8081/dog/rest";
$(function () {
	//如果URL传入了ID，则表示当前操作是修改
	var urlMap = getRequestUrlValue();
	var id = urlMap["id"];
	if (id && id!=undefined && id!='' && id.length>0){
		loadServer(id);
	}
	
	$('.btn-glow').click(function() {	
		var id = $("#id").val();
		var serverName = myTrim($("#serverName").val());
		if (serverName==null || serverName == '' || serverName.length<=0){
			alert("服务名称不能为空！");
			return false;
		}
		var serverLabel = myTrim($("#serverLabel").val());
		if (serverLabel==null || serverLabel == '' || serverLabel.length<=0){
			alert("服务标识不能为空！");
			return false;
		}
		var systemType = $("#systemType").val();
		var status = $("#status").val();
		var serverType = $("#serverType").val();
		var sendType = $("#sendType").val();
		var serverUrl = myTrim($("#serverUrl").val());
		if (serverUrl==null || serverUrl == '' || serverUrl.length<=0){
			alert("服务地址不能为空！");
			return false;
		}
		if (serverType=='HTTP' && (serverUrl.indexOf("http://")==-1 && !serverUrl.indexOf("https://")==-1)){
			alert("HTTP服务地址格式不符合（HTTP://IP:端口）！");
			return false;
		}
		if (serverUrl.indexOf(":")==-1){
			alert("服务地址格式不符合（http://IP:端口/或IP:端口）！");
			return false;
		}
		var serverInfo = myTrim($("#serverInfo").val());
		var jsonForm = {
			id:id,
			serverName:serverName,
			serverLabel:serverLabel,
			systemType:systemType,
			serverType:serverType,
			sendType:sendType,
			serverUrl:serverUrl,
			serverInfo:serverInfo,
			status:status
		}	
		$.post(contextPath + "/server/save",jsonForm,function(data){
			if (data && data == "success"){
				$('.modal-body').html("操作成功!");
				$('#createServerModal').modal('show');
				$('#createServerModal').on('hide.bs.modal', function () {
      				window.location.href = "serverList.html";
				});				
			}else {
				$('.modal-body').html("操作失败!");
				$('#createServerModal').modal('show');
			}
		});
	});	
	
	
});

function loadServer(id){
	$.get(contextPath + "/server/get",{id:id},function(data){
		if (data && data.id){
			$("#id").val(data.id);
			$("#serverName").val(data.serverName);
			$("#serverLabel").val(data.serverLabel);
			$("#systemType").val(data.systemType);
			$("#status").val(data.status);
			$("#serverType").val(data.serverType);
			$("#sendType").val(data.sendType);
			$("#serverUrl").val(data.serverUrl);		
			$("#serverInfo").val(data.serverInfo);
		}
	});
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

/*
*	去掉前后空格
* str:字符串
*/
function myTrim(str){  
	return str.replace(/^\s+/,'').replace(/\s+$/,'');   
}



