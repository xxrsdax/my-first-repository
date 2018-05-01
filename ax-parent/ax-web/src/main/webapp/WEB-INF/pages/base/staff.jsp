<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
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
        function doAdd(){
            //alert("增加...");
            $('#addStaffWindow').window("open");
        }

        function doView(){
            alert("查看...");
        }

        function doDelete(){
           //调用数据表格的getSelections 方法，获取选中的数据
			var rows = $("#grid").datagrid("getSelections"); //json 数组格式的数据
			 if(rows.length == 0) {
                 //弹出提示信息
                 $.messager.alert("提示信息","请选择需要删除的派送员！","warning");

			 }else{
				 //选中派送员,弹出确认框
				 $.messager.confirm("删除确认","确定要删除吗？",function ( r ) {

				     if( r ) { //判断是否确认
                        //对数据进行进一步的操作
                        var array = new Array(); //创建数组对象
                        for(var i = 0 ;i <rows.length ;i++ ){
                            var staff = rows[i]; //获取json对象
                       		var id = staff.id;
                            array.push(staff.id);  //将获取到的id数据压入数组
                       	 }
                        var ids = array.join(","); //将数组中的数据拼接成字符串，并用  ，  分隔
						 														                    //注意此处细节
                        location.href="${pageContext.request.contextPath}/StaffAction_deleteBatch?ids="+ids; //发送请求，相当于 get 请求
					}
                 });
			 }

        }

        function doRestore(){
            alert("将取派员还原...");
        }
        //工具栏
        var toolbar = [ {
            id : 'button-view',
            text : '查询',
            iconCls : 'icon-search',
            handler : doView
        }, {
            id : 'button-add',
            text : '增加',
            iconCls : 'icon-add',
            handler : doAdd
        }, {
            id : 'button-delete',
            text : '删除',
            iconCls : 'icon-cancel',
            handler : doDelete
        },{
            id : 'button-save',
            text : '还原',
            iconCls : 'icon-save',
            handler : doRestore
        }];
        // 定义列
        var columns = [ [ {
            field : 'id',
            checkbox : true,
        },{
            field : 'name',
            title : '姓名',
            width : 120,
            align : 'center'
        }, {
            field : 'telephone',
            title : '手机号',
            width : 120,
            align : 'center'
        }, {
            field : 'haspda',
            title : '是否有PDA',
            width : 120,
            align : 'center',
            formatter : function(data,row, index){
                if(data=="1"){
                    return "有";
                }else{
                    return "无";
                }
            }
        }, {
            field : 'deltag',
            title : '是否删除',
            width : 120,
            align : 'center',
            formatter : function(data,row, index){  //将数据 0 或者 1 转化为  汉字提醒
                if(data=="0"){
                    return "正常使用"
                }else{
                    return "已删除";
                }
            }
        }, {
            field : 'standard',
            title : '取派标准',
            width : 120,
            align : 'center'
        }, {
            field : 'station',
            title : '所谓单位',
            width : 200,
            align : 'center'
        } ] ];

        $(function(){
            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility:"visible"});

            // 取派员信息表格，
            $('#grid').datagrid( {
                iconCls : 'icon-forward',
                fit : true,
                border : false,
                rownumbers : true,
                striped : true,
                pageList: [10,50,100 ],  //设置分页记录数
                pagination : true,
                toolbar : toolbar,  //工具栏
                url : "${pageContext.request.contextPath}/StaffAction_pageQuery",
                idField : 'id', //指定标志性字段
                columns : columns,  //定义表格有几列
                onDblClickRow : doDblClickRow //为表格绑定双击事件
            });

            // 添加取派员窗口
            $('#addStaffWindow').window({
                title: '添加取派员',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable:false
            });

            // 修改取派员窗口
            $('#editStaffWindow').window({
                title: '修改取派员',
                width: 400,
                modal: true, // 覆盖模式
                shadow: true, // 阴影
                closed: true,  //关闭
                height: 400, //高度
                resizable:false  //是否允许修改窗口尺寸
            });


        });

        //数据表格 绑定的双击事件
        function doDblClickRow(rowIndex, rowData){
         //打开指定窗口
            $("#editStaffWindow").window("open");
            //调用 form的方法，回显指定json格式的数据
			$("#editStaffForm").form("load",rowData);
        }
	</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
	<table id="grid"></table>  <!-- 数据表格-->
</div>
<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
	<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
		<div class="datagrid-toolbar">
			<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
		</div>
	</div>

	<div region="center" style="overflow:auto;padding:5px;" border="false">
		<form id="addStaffForm" action="${pageContext.request.contextPath}/StaffAction_addStaff" method="post">
			<table class="table-edit" width="80%" align="center">
				<tr class="title">
					<td colspan="2">收派员信息</td>
				</tr>
				<!-- TODO 这里完善收派员添加 table -->
				<%--<tr>--%>
					<%--<td>取派员编号</td>--%>
					<%--<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>--%>
				<%--</tr>--%>
				<tr>
					<td>姓名</td>
					<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>手机
						<script type="text/javascript">
                            $(function () {
								//为保存按钮添加事件  addStaffForm
								$("#save").click(function () {
									var v = $("#addStaffForm").form("validate");
									if(v){
                                        $("#addStaffForm").submit();
									}
                                });

                                var regex = /^1[3|4|5|7|8][0-9]{9}$/; //手机号的正则表达式
                                //自定义手机号校验规则
                                $.extend($.fn.validatebox.defaults.rules, {
                                   //规则名，可自定义
                                    telephone: {
                                        validator: function(value,param){
                                            return regex.test(value);
                                        },
                                        message: '手机号输入有误！'
                                    }
                                });
                            })
						</script>
					</td>
					<td><input type="text" data-options="validType:'telephone'"  name="telephone" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>单位</td>
					<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
				</tr>
				<tr>
					<td>取派标准</td>
					<td>
						<input type="text" name="standard" class="easyui-validatebox" required="true"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>


<%--修改派送员--%>
<div class="easyui-window" title="修改派送员" id="editStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
	<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
		<div class="datagrid-toolbar">
			<a id="edit" icon="icon-edit" href="#" class="easyui-linkbutton" plain="true" >保存</a>
		</div>
	</div>

	<div region="center" style="overflow:auto;padding:5px;" border="false">
		<form id="editStaffForm" action="${pageContext.request.contextPath}/StaffAction_editStaff" method="post">
			<!-- id数据-->
			<input type="hidden" name="id" />
			<table class="table-edit" width="80%" align="center">
				<tr class="title">
					<td colspan="2">收派员信息</td>
				</tr>
				<!-- TODO 这里完善收派员添加 table -->
				<%--<tr>--%>
				<%--<td>取派员编号</td>--%>
				<%--<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>--%>
				<%--</tr>--%>
				<tr>
					<td>姓名</td>
					<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>手机
						<script type="text/javascript">
                            $(function () {
                                //为保存按钮添加事件  addStaffForm
                                $("#edit").click(function () {
                                    var v = $("#editStaffForm").form("validate");
                                    if(v){
                                        $("#editStaffForm").submit();
                                    }
                                });

                                var regex = /^1[3|4|5|7|8][0-9]{9}$/; //手机号的正则表达式
                                //自定义手机号校验规则
                                $.extend($.fn.validatebox.defaults.rules, {
                                    //规则名，可自定义
                                    telephone: {
                                        validator: function(value,param){
                                            return regex.test(value);
                                        },
                                        message: '手机号输入有误！'
                                    }
                                });
                            })
						</script>
					</td>
					<td><input type="text" data-options="validType:'telephone'"  name="telephone" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>单位</td>
					<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
				</tr>
				<tr>
					<td>取派标准</td>
					<td>
						<input type="text" name="standard" class="easyui-validatebox" required="true"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>

</body>
</html>