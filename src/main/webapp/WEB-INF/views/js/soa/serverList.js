var contextPath = "http://127.0.0.1:8081/dog/rest";
$(function () {
	loadServerList();
	/**
	 * 添加客户端
	 */
	$('.btn-flat').click(function() {
		window.location.href="createServer.html?date="+new Date();
	});
	
	$('.btn-primary').click(function() {		
		deleteServer($("#id").val());
	});	
	
	/**
	 * 搜索服务端列表
	 */
	$('.search').blur(function(){
		$.get(contextPath + "/server/findServer",{serverName:this.value,status:$("#status").val()},function(data){
			$(".table tbody").html('');
			if (data!=null && data!='fail'&&data!='error'){
				loadData(data);
			}
		});
	});
	
	/**
	 * 选择后，自动查询
	 */
	$('.search-select').change(function(){
		$.get(contextPath + "/server/findServer",{serverName:$("#serverName").val(),status:this.value},function(data){
			$(".table tbody").html('');
			if (data!=null && data!='fail'&&data!='error'){
				loadData(data);
			}
		});
	});
	
	
});

/**
 * 展示客户端列表
 */
function loadServerList(){
	$.get(contextPath + "/server/getList",function(data){
		if (data!=null && data!='fail'&&data!='error'){
			loadData(data);
		}
	});
}

/**
 * 将数据以HTML格式拼装
 * @param {Object} data
 */
function loadData(data){
	var server_tr_html ='';
	for (var i=0;i<data.length;i++){
		server_tr_html += '<tr><td>';
		server_tr_html += '        <div class="img"><img src="../img/server.jpg" /></div>';
		server_tr_html += '        <a href="javascript:queryServer(\''+data[i].id+'\');" class="name">'+data[i].serverName+'</a>';
		server_tr_html += '    </td>';
		server_tr_html += '    <td class="description">'+data[i].serverLabel+'</td>';
		server_tr_html += '    <td class="description">'+data[i].serverInfo+'</td>';
		server_tr_html += '    <td>';
		server_tr_html += '    	<span class="label label-success">'+autoValidate(data[i].status,['0','1'],['禁用','启用'])+'</span></td>';
		server_tr_html += '     <td>'+data[i].createTime+'</td>';
		server_tr_html += '     <td><ul class="actions">';
		server_tr_html += '        		<li><a href="javascript:setStatus(true,\''+data[i].id+'\');">启用</a></li>';
		server_tr_html += '        		<li><a href="javascript:setStatus(false,\''+data[i].id+'\');">禁用</a></li>';
		server_tr_html += '            	<li><a href="javascript:editServer(\''+data[i].id+'\');">编辑</a></li>';
		server_tr_html += '            	<li class="last"> <a data-toggle="modal" data-target="#deleteModal" onclick="setId(\''+data[i].id+'\')" href="javascript:void(0);">删除</a></li>';
		server_tr_html += '     </ul></td></tr>';

	}
	$(".table tbody").html('');
	$(".table tbody").append(server_tr_html); 
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
 * 删除客户端
 * @param {Object} id
 */
function deleteServer(id){
	$.get(contextPath + "/server/delete",{id:id},function(data){
		$('#deleteModal').modal('hide');
		loadServerList();
	});
}

/**
 * 设置需要删除的ID
 * @param {Object} id
 */
function setId(id){
	$("#id").val(id);
}

/**
 * 编辑服务信息
 * @param {Object} id
 */
function editServer(id){
	window.location.href='createServer.html?id='+id+"&date="+new Date();		
}

/**
 * 编辑服务信息
 * @param {Object} id
 */
function queryServer(id){
	window.location.href='serverInfo.html?id='+id+"&date="+new Date();		
}

/**
 * 设置启用与禁用状态
 * @param {Object} bl
 */
function setStatus(bl,id){
	var url = contextPath + "/server/setOpenStauts";
	if (!bl){
		url = contextPath + "/server/setCloseStatus";
	}	
	$.get(url,{id:id},function(data){
		loadServerList();
	});	
}
