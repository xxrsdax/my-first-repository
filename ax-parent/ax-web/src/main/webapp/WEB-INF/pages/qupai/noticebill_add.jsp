<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加业务受理单</title>
	<!-- 导入jquery核心类库 -->
	<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
	<!-- 导入easyui类库 -->
	<link rel="stylesheet" type="text/css"
		  href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"
		  href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css"
		  href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
	<link rel="stylesheet" type="text/css"
		  href="${pageContext.request.contextPath }/css/default.css">
	<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
	<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
	<script
			src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
			type="text/javascript"></script>
	<script type="text/javascript">
        $(function(){
            $("body").css({visibility:"visible"});

            // 对save按钮条件 点击事件
            $('#save').click(function(){
                // 对form 进行校验
                if($('#noticebillForm').form('validate')){

                    $('#noticebillForm').submit();
                }
            });
        });
	</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="north" style="height:31px;overflow:hidden;" split="false"
	 border="false">
	<div class="datagrid-toolbar">
		<a id="save" icon="icon-save" href="#" class="easyui-linkbutton"
		   plain="true">新单</a>
		<a id="edit" icon="icon-edit" href="${pageContext.request.contextPath }/page_qupai_noticebill.action" class="easyui-linkbutton"
		   plain="true">工单操作</a>
	</div>
</div>
<div region="center" style="overflow:auto;padding:5px;" border="false">
	<form id="noticebillForm" action="${pageContext.request.contextPath}/NoticebillAction_add.action" method="post">
		<table class="table-edit" width="95%" align="center">
			<tr class="title">
				<td colspan="4">客户信息</td>
			</tr>
			<tr>
				<td>来电号码:</td>
				<td><input type="text" class="easyui-validatebox" name="telephone"
						   required="true"  /></td>
				<script type="text/javascript">
					$(function () {
//					    为输入框绑定离焦事件  Bind the defocus event for the input box.
						$("input[name=telephone]").blur(function () {
						    // this.value获取当前输入框的值 Gets the value of the current input box.
							var telephone = this.value;

							$.post(
							    "${pageContext.request.contextPath}/NoticebillAction_findCustomerByTelephone.action",
								{"telephone":telephone}, //参数
								function (data) {
									 if(data != null ) {
											//data中的各个数据，赋值到相应的input
										 $("input[name=customerId]").val(data.id);
                                         $("input[name=customerName]").val(data.name);
                                         $("input[name=delegater]").val(data.name);
                                         $("input[name=pickaddress]").val(data.address);

									 }else{
										 //清空表单
                                         $("input[name=customerId]").val("");
                                         $("input[name=customerName]").val("");
                                         $("input[name=delegater]").val("");
                                         $("input[name=pickaddress]").val("");
									 }
                                }
							);
                        });
                    });
				</script>
				<td>客户编号:</td>
				<td><input type="text" class="easyui-validatebox"  name="customerId"
						  /></td>
			</tr>
			<tr>
				<td>客户姓名:</td>
				<td><input type="text" class="easyui-validatebox" name="customerName"
				            /></td>
				<td>联系人:</td>
				<td><input type="text" class="easyui-validatebox" name="delegater"
						   /></td>
			</tr>
			<tr class="title">
				<td colspan="4">货物信息</td>
			</tr>
			<tr>
				<td>品名:</td>
				<td><input type="text" class="easyui-validatebox" name="product"
						     /></td>
				<td>件数:</td>
				<td><input type="text" class="easyui-numberbox" name="num"
						    /></td>
			</tr>
			<tr>
				<td>重量:</td>
				<td><input type="text" class="easyui-numberbox" name="weight"
						     /></td>
				<td>体积:</td>
				<td><input type="text" class="easyui-validatebox" name="volume"
						    /></td>
			</tr>
			<tr>
				<td>取件地址</td>
				<td colspan="3"><input type="text" class="easyui-validatebox" name="pickaddress"
									   required="true" size="144"/></td>
			</tr>
			<tr>
				<td>到达城市:</td>
				<td><input type="text" class="easyui-validatebox" name="arrivecity"
						   /></td>
				<td>预约取件时间:</td>
				<td><input type="text" class="easyui-datebox" name="pickdate"
						   data-options="editable:false" /></td>
			</tr>
			<tr>
				<td>备注:</td>
				<td colspan="3"><textarea rows="5" cols="80" type="text" class="easyui-validatebox" name="remark"
										  ></textarea></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>