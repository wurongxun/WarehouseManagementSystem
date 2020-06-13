<%--
  Created by IntelliJ IDEA.
  User: WuRongXun
  Date: 2019/12/22
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="<%=basePath%>static/js/InformationManagement/RoleInformation_list.js"></script>
<div id="" style="padding: 10px; display: inherit; height:9%;overflow-x:auto;" onkeypress="enter(event) ">
    <form id="detailedList_RoleInformation_form_id" action="" style="float: none; display: inline-block;">
        <input name="roleName" class="easyui-textbox" data-options="prompt:'角色名'"
               style="width: 130px"
               onkeypress="enter(event)">
        <select class="easyui-combobox" name="find_status"
                data-options="prompt:'状态',validType:'length[0,20]',editable:false"
                style="width: 90px">
            <option value="">请选择</option>
            <option value="1">启用</option>
            <option value="2">停用</option>
        </select>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-search',plain:true"
           onclick="findActionRoleInformation()">搜索</a>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-empty',plain:true"
           onclick="doClearAction()">清空</a>
    </form>
</div>
<div style="width:100%;height:91%;">
    <div id="" class="easyui-panel" data-options="fit:true,border:false">
        <table id="role_table"></table>
    </div>
</div>
<div id="role-dialog"
     class="easyui-dialog"
     title="添加角色"
     data-options="modal:true,iconCls:'icon-add',closed:true,href:'<%=request.getContextPath()%>/role.html?act=go_edit',footer:'#role-buttons'"
     style="width:50%;height:40%;.">
</div>
<div id="role-menu-dialog" class="easyui-dialog" title="分配菜单" style="width:40%;height:50%;"
     data-options="modal:true,iconCls:'icon-add',closed:true,footer:'#menu-buttons'">
    <ul id="role-menu-tree" class="easyui-tree"></ul>
</div>
<%--<div id="role-tool" style="padding: 5px 15px 5px 0px;text-align: right">
    <a class="easyui-linkbutton"  data-options="iconCls:'icon-add'"  onclick="AddRole()" >添加</a>
    <a href="#" class="easyui-linkbutton"  data-options=" iconCls:'icon-seve'">修改</a>
</div>--%>
<div id="role-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitRoleFrom()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'" onclick="AddRoleClose()">取消</a>
</div>
<div id="menu-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitRoleMenu()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'" onclick=" MenuClose()">取消</a>
</div>

