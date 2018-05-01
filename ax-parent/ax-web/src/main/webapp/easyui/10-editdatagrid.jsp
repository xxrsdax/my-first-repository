<%--
  Created by IntelliJ IDEA.
  User: dell3020mt-50
  Date: 2018/4/22
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑数据表格</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
    <script  src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
</head>
<body>
<table id="mytable">
</table>
<script type="text/javascript">
    $(function () {
         var myIndex = -1 ;
        $("#mytable").datagrid({
            columns:[[
                {title:"编号",field:"id",checkbox:true},
                {width:100,title:"姓名",field:"name",editor:{
                                                 type:"validatebox",
                                                 options:{}
                                                    }
                },
                {width:100,title:"年龄",field:"age",editor:{
                    type:"numberbox",
                    options:{}
                }},
                {width:100,title:"日期",field:"address",editor:{
                    type:"datebox",
                    options:{}
                }}
            ]],
            url:'${pageContext.request.contextPath}/json/datagrid_data.json',  //指定数据表格发送ajax请求的地址
            rownumbers:true,
//                定义工具栏
            toolbar:[
                //标题       图标
                {text:'添加',iconCls:'icon-add',
                    //绑定事件
                    handler:function () {
                        $("#mytable").datagrid("insertRow",{
                            index:0, //表示在第一行插入数据
                            row:{} //表示插入的是空行
                        });
                        myIndex  = 0;
                        $("#mytable").datagrid("beginEdit",myIndex); //设置为可编辑
                    }
                },
                {text:'修改',iconCls:'icon-edit',handler:function () {
                        var rows = $("#mytable").datagrid("getSelections");
                        if(rows.length == 1){
                            myIndex = $("#mytable").datagrid("getRowIndex",rows[0]);
                            $("#mytable").datagrid("beginEdit",myIndex);
                        }
                }},
                {text:'删除',iconCls:'icon-remove',handler:function () {
                    var rows = $("#mytable").datagrid("getSelections");
                    if(rows.length == 1){
                        myIndex = $("#mytable").datagrid("getRowIndex",rows[0]);
                        $("#mytable").datagrid("deleteRow",myIndex);   //删除第一行
                    }
                }},
                {text:'保存',iconCls:'icon-search',handler:function () {
                        $("#mytable").datagrid("endEdit",myIndex);   //结束编辑
                }}
            ],
            //使用分页条
            pagination:true,
            pageList:[1,3,5],
            onAfterEdit:function () {
                alert("0000");
            }
        });
    })
</script>
</body>
</html>
