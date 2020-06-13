<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="copyright" content="All Rights Reserved, Copyright (C) 2020, WRX, Ltd."/>
    <title>仓库管理系统</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->

    <link rel="stylesheet" type="text/css" href="<%=basePath%>bootstrap-3.3.7/css/bootstrap.min.css"/>
    <%--    <link rel="stylesheet" type="text/css" href="<%=basePath%>bootstrap-3.3.7/css/bootstrap-select.min.css"/>
        <script type="text/javascript" src="<%=basePath%>bootstrap-3.3.7/js/bootstrap-select.js"></script>--%>
    <!-- Latest compiled and minified CSS -->
    <%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>--%>

    <%--  <!-- (Optional) Latest compiled and minified JavaScript translation files -->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/i18n/defaults-*.min.js"></script>--%>

    <link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.7.0/themes/bootstrap/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.7.0/themes/bootstrap/menu.css"/>
    <%--<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.7.0/css/bootstrap.css"/>--%>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.7.0/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.7.0/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.7.0/css/icon.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.7.0/css/micons.css"/>
    <script type="text/javascript" src="<%=basePath%>jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>jquery-easyui-1.7.0/src/jquery.menu.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/js/index/BaseJS.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/js/index/index.js"></script>
    <script type="text/javascript" src="<%=basePath%>bootstrap-3.3.7/js/bootstrap-select.js"></script>
    <script type="text/javascript" src="<%=basePath%>jquery-easyui-1.7.0/echarts.min.js"></script>
</head>
<base href="<%=basePath%>"/>
<body class="easyui-layout">
<div class="wu-header" data-options="region:'north',border:false,split:true">
    <div class="wu-header-left">
        <h1>仓库管理系统</h1>
    </div>
    <div style="text-align:right;width:100%;heigth:100%;/*background:url('<%=basePath%>jquery-easyui-1.7.0/images/bg_header_1.jpg')*/">

        <p><strong class="easyui-tooltip" title="沙雕">${activeUser.getUserName()}</strong>，欢迎您！</p>
        <p>
            <a class="screen-full" id="full" href="javascript:;">全屏</a>|
            <a class="screen-full" id="cancelFull" href="javascript:;">退出全屏</a>|
            <%--<a class="screen-full" id="isFull" href="javascript:;">是否全屏</a>|--%>
            <a href="javascript:void(0)" style="color: red">${activeUser.getRole().getRemark()}</a>|
            <a href="javascript:void(0)" id="mb" class="easyui-menubutton"
               data-options="menu:'#mms',iconCls:'icon-cog'"></a>
        </p>
    </div>
</div>
<div id="UpPassword-dialog"
     class="easyui-dialog"
     title="修改密码"
     data-options="modal:true,iconCls:'icon-wrench',closed:true,footer:'#up-buttons'"
     style="width:600px;height:400px;">
    <form id="password_form">
        <input name="id" id="user_id" value="${activeUser.getId()}" type="hidden">
        <div class="form-group">
            <label>用户名</label>
            <input class="form-control" type="text" value="${activeUser.getUserName()}"
                   placeholder="${activeUser.getUserName()}" readonly>
        </div>
        <div class="form-group">
            <label>当前账号</label>
            <input class="form-control" type="text" value="${ activeUser.getEmail()}"
                   placeholder="${ activeUser.getEmail()}" readonly>
        </div>
        <div class="form-group">
            <label for="newPassword">新密码</label>
            <input type="password" name="newpassword" class="form-control" id="newPassword" onkeyup="validate()">
        </div>
        <div class="form-group">
            <label for="determinePassword">确认密码</label>
            <input type="password" name="determinePassword" class="form-control col-sm-5" id="determinePassword"
                   onkeyup="validate()"><span id="tishi"></span>
        </div>
    </form>
</div>
<div id="up-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a id="submitUpPassword" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="submitUpPassword()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'" onclick="Passwor_dialog_Close()">取消</a>
</div>
<div id="mms" style="width:150px;">
    <div data-options="iconCls:'icon-undo'," onclick="upPassword()">修改密码</div>
    <div data-options="iconCls:'icon-redo' ,href:'logout.html'">安全退出</div>
    <%--<div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove'">Delete</div>
    <div>Select All</div>--%>
</div>
<!-- menu菜单 -->
<div id="mm" class="easyui-menu cs-tab-menu">
    <div id="mm-tabupdate">刷新</div>
    <div class="menu-sep"></div>
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseother">关闭其他</div>
    <div id="mm-tabcloseall">关闭全部</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright">关闭右侧</div>
</div>

<div data-options="region:'south'" style="height:3%;">
    <p style="text-align:center">&copy;2020</p>
</div>

<div data-options="region:'west'" title="菜单" style="width:8% ">
    <ul class="easyui-tree" id="leftMenu">
        <shiro:hasAnyRoles name="admin,boos">
            <li id="MenuItem1" iconCls="icon-wrench" data-options="">
                <span>系统配置</span>
                <ul>
                    <li data-options="url:'user.html?act=page'" iconCls="icon-user">
                        <span>用户管理</span>
                    </li>
                    <li id="MenuItem3" data-options="url:'role.html?act=page'" iconCls="icon-user-edit">
                        <span>角色管理</span>
                    </li>

                </ul>
            </li>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name="admin,boos,information">
            <li iconCls="icon-application-view-tile">
                <span>信息管理</span>
                <ul>
                    <li id="MenuItem5" data-options="url:'supplier.html?act=page'" iconCls="icon-user-group">
                        <span>供应商信息管理</span>
                    </li>
                    <li id="" data-options="url:'commodityInformation.html?act=page'"
                        iconCls="icon-table-column">
                        <span>商品信息管理</span>
                    </li>

                    <li data-options="url:'warehouseInformation.html?act=page'" iconCls="icon-bullet-home">
                        <span>仓库信息管理</span>
                    </li>
                    <li data-options="url:'<%=basePath%>goodsShelvesInformation.html?act=page'"
                        iconCls="micons-GoodsShelvesInformation">
                        <span>货架信息管理</span>
                    </li>
                    <li data-options="url:'<%=basePath%>shelfPositionInformation.html?act=page'"
                        iconCls="micons-ShelfPositionInformation">
                        <span>架位信息管理</span>
                    </li>

                </ul>
            </li>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name="admin,boos,outofstock,warehouse,outofstock">
            <li iconCls="micons-warehouse">
                <span>仓库管理</span>
                <ul>
                    <shiro:hasAnyRoles name="admin,boos,warehouse">
                        <li data-options="url:'warehousingEntryInformation.html?act=page'"
                            iconCls="micons-Commodity-Adjustment-Receive">
                            <span>商品入库</span>
                        </li>
                    </shiro:hasAnyRoles>
                    <shiro:hasAnyRoles name="admin,boos,outofstock">
                        <li data-options="url:'outOfStockInformation.html?act=page'"
                            iconCls="micons-Commodity-Adjustment-Roll-Out">
                            <span>商品出库</span>
                        </li>
                    </shiro:hasAnyRoles>
                </ul>
            </li>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name="admin,boos,approvalon">
            <li iconCls="micons-approval-settings">
                <span>出入审批</span>
                <ul>
                    <li data-options="url:'<%=basePath%>detailedListApprovalController.html?act=page&detailedListType=1'"
                        iconCls="micons-approval-warehousing">
                        <span>入库审批</span>
                    </li>
                    <li data-options="url:'<%=basePath%>detailedListApprovalController.html?act=page&detailedListType=2'"
                        iconCls="micons-approval-outofstock">
                        <span>出库审批</span>
                    </li>
                </ul>
            </li>
        </shiro:hasAnyRoles>
        <li iconCls="micons-Reportform">
            <span>信息报表</span>
            <ul>
                <li data-options="url:'warehousingOutOfStockReportForm.html?act=page'"
                    iconCls="micons-Warehousing-order-ok">
                    <span>出入库比例</span>
                </li>
                <li data-options="url:'shelfPositionInformationReportFrom.html?act=page'"
                    iconCls="micons-OutOfStock-order-ok">
                    <span>货架位信息</span>
                </li>
                <%--<li iconCls="micons-warehouse-query">
                    <span>仓库查询</span>
                </li>
                <li iconCls="micons-warehouse-inventory-query">
                    <span>仓库库存查询</span>
                </li>--%>
            </ul>
        </li>
    </ul>
</div>

<div data-options="region:'center'" title="首页" style=" height:87% ">
    <div id="tabs" class="easyui-tabs" style="/*background-color: #0000FF;*/ height: 100%;">
    </div>
</div>
</body>
<script>

</script>
</html>
