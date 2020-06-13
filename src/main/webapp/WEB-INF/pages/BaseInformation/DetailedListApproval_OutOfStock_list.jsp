<%--
  Created by IntelliJ IDEA.
  User: WuRongXun
  Date: 2020/3/21
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script type="text/javascript" src="<%=basePath%>/static/js/InformationManagement/DetailedListApproval_OutOfStock_list.js"></script>
<div id="DetailedListApproval_OutOfStock_list_toobar_id" style="padding: 10px; display: inherit; height:9%;overflow-x:auto;"
     onkeypress="enter(event) ">
    <form id="DetailedListApproval_ApprovalOutOfStock_list_form_id" action="" style="float: none; display: inline-block;">
        <input name="ApprovalOutOfStock_detailedListCode" class="easyui-textbox" data-options="prompt:'出库单编号'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="ApprovalOutOfStock_detailedListName" class="easyui-textbox" data-options="prompt:'出库单名称'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="ApprovalOutOfStock_staffName" class="easyui-textbox" data-options="prompt:'负责人名'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="ApprovalOutOfStock_receivingDelivererPersonName" class="easyui-textbox" data-options="prompt:'收货人名'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="ApprovalOutOfStock_receivingDelivererPersonPhone" class="easyui-textbox" data-options="prompt:'收货人电话'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="ApprovalOutOfStock_receivingDelivererPersonAddress" class="easyui-textbox" data-options="prompt:'收货人地址'" style="width: 130px"
               onkeypress="enter(event)">
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-search',plain:true" onclick="findAction_ApprovalOutOfStock()">搜索</a>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-empty',plain:true"
           onclick="doClearApprovalOutOfStock()">清空</a>
    </form>
</div>
<div style="width:100%;height:91%;">
    <div id="" class="easyui-panel" data-options="fit:true,border:false">
        <table id="detailedListApproval_OutOfStock_table"></table>
    </div>
</div>
