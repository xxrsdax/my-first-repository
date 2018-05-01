<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>layout</title>
    <%-- 引入 easyUI 的 四个必须文件 --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"  ></script>

</head>
<body class="easyui-layout">
<!-- 使用div元素描述每个区域 -->
<div title="XXX管理系统" style="height: 100px" data-options="region:'north'">北部区域</div>
<div title="系统菜单" style="width: 200px" data-options="region:'west'">
    <%--西部区域--%>
    <%--此处存在放折叠面板--%>
    <%--配置指定的class 作成折叠面板，         data-options：可以对一些的动作进行设置--%>
    <div class="easyui-accordion" data-options="fit:true"><!-- 自适应  （填充父容器，及上一层div ） -->
        <div title="面板一" data-options="iconCls:'icon-cut'" >
            <%--此处设置，为必须设置class 的值固定，id的值随意--%>
            <ul class="ztree" id="zTree"></ul>
            <%--页面加载方法--%>
            <script type="text/javascript" >
                $(function () {
//            页面加载完毕后，执行方法
//            设置setting的属性
                    var setting = {
                        data:{
                            simpleData:{
                                enable:true  // 使用简单json数据构造ztree节点
                            }
                        }
                    };      //名称随意，不一定是setting
//            设置菜单的数据
                    var zNodes = [
                        {"id":"1","pId":"0","name":"目录一"},  //键的名称 固定的
                        {"id":"4","pId":"1","name":"文件一"},
                        {"id":"2","pId":"0","name":"目录二"},
                        {"id":"5","pId":"2","name":"文件二"},
                        {"id":"3","pId":"0","name":"目录三"},
                        {"id":"6","pId":"3","name":"文件三"}
                    ];
//            调用方法，构造菜单
                    $.fn.zTree.init($("#zTree"),setting,zNodes);
                });
            </script>
        </div> <!-- 配置图标  在icon.css文件中，有相关的配置 -->
        <div title="面板二" data-options="iconCls:'icon-save'">
            <a id="but1" class="easyui-linkbutton" >动态添加选项卡</a>
            <script type="text/javascript">
                $(function () {
                    $("#but1").click(function(){   <!-- 为指定 id  绑定点击事件 -->

                        var existB = $("#mytabs").tabs("exists","新增选项卡");

                    if(existB) {
                        $("#mytabs").tabs("select","新增选项卡");
                    }else{
                        $("#mytabs").tabs("add",{    //执行与tabs有关的方法
                            title:"新增选项卡",  //配置title属性
                            iconCls:"icon-edit", //配置图标属性
                            closable:true,  //配置为可删除
                            content:" <iframe frameborder=\"0\"  width=\"100%\" height=\"100%\" src=\"https://www.baidu.com\" />"  //配置选项卡内的内容
                        });
                    }
                    });
                })
                
            </script>
        </div>
        <div title="面板三" data-options="iconCls:'icon-remove'">
            <%--此处设置，为必须设置class 的值固定，id的值随意--%>
            <ul class="ztree" id="zTree2"></ul>
            <%--页面加载方法--%>
            <script type="text/javascript" >
                $(function () {
//            页面加载完毕后，执行方法
//            设置setting的属性
                    var setting2 = {
                        data:{
                            simpleData:{
                                enable:true  // 使用简单json数据构造ztree节点
                            }
                        },
                        callback:{
//                            为节点绑定事件
                            onClick:function (event,treeId,treeNode) {
                                //判断该节点是否存在 page，若不存在，则绑定事件
                                if(treeNode.page != undefined) {
//                                    判断选项卡是否存在
                                    var exist = $("#mytabs").tabs("exists",treeNode.name);

                                    if(exist) {
                                        $("#mytabs").tabs("select",treeNode.name);
                                    }else{
                                        var page = treeNode.page;
                                        $("#mytabs").tabs("add",{
                                            title:treeNode.name,
                                            closable:true,
                                            content:' <iframe frameborder="0" height="100%" width="100%" src="'+page+'" ></iframe>'
                                        })
                                    }
                                }
                            }
                        }
                    };      //名称随意，不一定是setting
//           使用ajax请求获取，菜单结构数据
                  var url = "${pageContext.request.contextPath}/json/menu.json";
                    $.post(  //url ,参数，成功返回后执行的方法，格式
                        url,
                        {},
                        function (data) {
                            $.fn.zTree.init($("#zTree2"),setting2,data);
                        },
                        "json"
                    );
                });
            </script>
        </div>

        <div title="面板四" data-options="iconCls:'icon-edit'">编辑</div>
    </div>

</div>
<div data-options="region:'center'">
    <%--中心区域--%>
    <%--中心区域存放 选项卡 --%>
        <div id="mytabs" class="easyui-tabs" data-options="fit:true"><!-- 自适应  （填充父容器，及上一层div ） -->
            <div title="选项卡一" data-options="closable:true" data-options="iconCls:'icon-cut'" >剪切</div> <!-- 配置图标  在icon.css文件中，有相关的配置 -->
            <div title="选项卡二" data-options="closable:true" data-options="iconCls:'icon-save'">保存</div>  <!-- 配置移除按键 ，弹出选项卡需要另外配置-->
            <div title="选项卡三" data-options="closable:true" data-options="iconCls:'icon-remove'">移除</div>
            <div title="选项卡四" data-options="closable:true" data-options="iconCls:'icon-edit'">编辑</div>
        </div>
</div>
<div style="width: 100px" data-options="region:'east'">东部区域</div>
<div style="height: 50px" data-options="region:'south'">南部区域</div>
</body>
</html>