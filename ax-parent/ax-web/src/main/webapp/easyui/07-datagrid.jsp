<%--
  Created by IntelliJ IDEA.
  User: dell3020mt-50
  Date: 2018/4/11
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>datagrid</title>
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
            $("#mytable").datagrid({
                columns:[[
                    {title:"编号",field:"id",checkbox:true},
                    {title:"姓名",field:"name"},
                    {title:"年龄",field:"age"},
                    {title:"地址",field:"address"}
                ]],
                url:'${pageContext.request.contextPath}/json/datagrid_data.json',  //指定数据表格发送ajax请求的地址
                rownumbers:true,
//                定义工具栏
                toolbar:[
                    //标题       图标
                    {text:'添加',iconCls:'icon-add',
                        //绑定事件
                        handler:function () {
                            alert("....");
                        }
                    },
                    {text:'修改',iconCls:'icon-edit',handler:function () {
                            alert("....");
                    }},
                    {text:'删除',iconCls:'icon-remove',handler:function () {
                        alert("....");
                    }},
                    {text:'查询',iconCls:'icon-search',handler:function () {
                        alert("....");
                    }}
                    ],
                //使用分页条
                pagination:true
            });
        })
    </script>
</body>
</html>
