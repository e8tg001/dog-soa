var contextPath = "http://127.0.0.1:8081/dog/rest";
$(function () {
	loadClientList();
	/**
	 * 添加客户端
	 */
	$('.btn-flat').click(function() {
		//alert("click..");
		var ip = $("#ip").val();
		var status = $("#status").val();
		if (ip==null || ip == '' || ip.length<=0){
			alert("ip不能为空！");
			return false;
		}
		var jsonForm = {
			ip:ip,
			status:$("#status").val(),
			systemType:$("#systemType").val()
		}
		$.post(contextPath + "/clinet/save",jsonForm,function(data){
			if (data && data == "success"){
				$(".dialog").css({display:"none"})
				$("#ip").val('');
				loadClientList();
			}
		});
		
	});
	
	$('.btn-primary').click(function() {		
		deleteClient($("#id").val());
	});	
	
	/**
	 * 展示客户端列表
	 */
	$('.search').blur(function(){
		$.get(contextPath + "/clinet/queryIP",{ip:this.value},function(data){
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
function loadClientList(){
	$.get(contextPath + "/clinet/getList",function(data){
		if (data!=null && data!='fail'&&data!='error' && data.length>0){
			loadData(data);
		}else {
			$(".alert-warning").alert('close')
		}
	});
}

/**
 * 将数据以HTML格式拼装
 * @param {Object} data
 */
function loadData(data){
	var client_tr_html ='';
	for (var i=0;i<data.length;i++){				
		client_tr_html += '<tr><td>';
		client_tr_html += '        <img src="../img/tpcip.jpg" class="img-circle avatar hidden-phone" />';
		client_tr_html += '        <a href="javascript:void(0);" class="name">'+data[i].ip+'</a>';
		client_tr_html += '        <span class="subtext">'+autoValidate(data[i].systemType,['1','2'],['内部系统','外部系统'])+'</span>';
		client_tr_html += '    </td>';
		client_tr_html += '    <td>'+data[i].createTime+'</td>';
		client_tr_html += '    <td>'+autoValidate(data[i].status,['0','1'],['禁用','启用'])+'</td>';
		client_tr_html += '    <td class="align-right"><a href="javascript:setStatus(true,\''+data[i].id+'\');">启用</a> <a href="javascript:setStatus(false,\''+data[i].id+'\');">禁用</a> <a data-toggle="modal" data-target="#deleteModal" onclick="setId(\''+data[i].id+'\')" href="javascript:void(0);">删除</a>';
		client_tr_html += '</tr>';
	}
	$(".table tbody").html('');
	$(".table tbody").append(client_tr_html); 
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
function deleteClient(id){
	$.get(contextPath + "/clinet/delete",{id:id},function(data){
		$('#deleteModal').modal('hide');
		loadClientList();
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
 * 设置启用与禁用状态
 * @param {Object} bl
 */
function setStatus(bl,id){
	var url = contextPath + "/clinet/setOpenStauts";
	if (!bl){
		url = contextPath + "/clinet/setCloseStatus";
	}	
	$.get(url,{id:id},function(data){
		loadClientList();
	});	
}
