<%--
  Created by IntelliJ IDEA.
  User: WuRongXun
  Date: 2019/12/27
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="<%=basePath%>static/js/InformationManagement/UserInformation_list.js"></script>
<div id="detailedList_WarehousingInformation_list_toobar_id"
     style="padding: 10px; display: inherit; height:9%;overflow-x:auto;"
     onkeypress="enter(event) ">
    <form id="detailedList_UserInformation_form_id" action="" style="float: none; display: inline-block;">
        <input name="userName" class="easyui-textbox" data-options="prompt:'用户名'"
               style="width: 130px"
               onkeypress="enter(event)">
        <%-- <input name="userRole" class="easyui-textbox" data-options="prompt:'用户角色'"
                style="width: 130px"
                onkeypress="enter(event)">--%>
        <input name="department" class="easyui-textbox" data-options="prompt:'所属部门'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="jobNumber" class="easyui-textbox" data-options="prompt:'工号'"
               style="width: 130px"
               onkeypress="enter(event)">
        <input name="phone" class="easyui-textbox" data-options="prompt:'电话'"
               style="width: 130px"
               onkeypress="enter(event)" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')">
        <input name="age" class="easyui-textbox" data-options="prompt:'年龄'"
               style="width: 130px"
               onkeypress="enter(event)">
        <input name="userCode" class="easyui-textbox" data-options="prompt:'编号'"
               style="width: 130px"
               onkeypress="enter(event)">
        <input name="email" class="easyui-textbox" data-options="prompt:'Email'"
               style="width: 130px"
               onkeypress="enter(event)">
        <select class="easyui-combobox" name="sex"
                data-options="prompt:'性别',validType:'length[0,20]',editable:false"
                style="width: 90px">
            <option value=""></option>
            <option value="1">男</option>
            <option value="2">女</option>
        </select>
        <select class="easyui-combobox" name="status"
                data-options="prompt:'状态',validType:'length[0,20]',editable:false"
                style="width: 90px">
            <option value=""></option>
            <option value="1">启用</option>
            <option value="2">停用</option>
        </select>

        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-search',plain:true"
           onclick="findActionUserInformation()">搜索</a>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-empty',plain:true"
           onclick="doClearAction()">清空</a>
    </form>
</div>
<div style="width:100%;height:91%;">
    <div id="" class="easyui-panel" data-options="fit:true,border:false">
        <table id="user_table"></table>
    </div>
</div>


<div id="role-user-dialog" class="easyui-dialog" title="分配角色"
     data-options="modal:true,iconCls:'icon-add',closed:true,footer:'#role-buttons'"
     style="width:60%;height:50%;">
        <div id="" class="easyui-panel" style="height:100%;width: 100%;" data-options="fit:true,border:false">
            <table id="role_table" class="easyui-datagrid" style="height:100%;width: 100%;"
                   data-options="fitColumns:true,scrollbarSize:0"></table>
        </div>
</div>
<%--添加用户--%>
<div id="adduser-dialog"
     class="easyui-dialog"
     title="添加用户"
     data-options="modal:true,iconCls:'icon-add',closed:true,footer:'#adduser-buttons'"
     style="width:600px;height:400px;">
    <%--href:'<%=request.getContextPath()%>/user.html?act=go_adduser',--%>
    <form id="adduser_form">
        <fieldset>
            <legend></legend>
            <div class="form-group">
                <div class="form-group">
                    <label for="userName">用户名</label>
                    <input class="form-control" id="userName" name="userName" type="text" placeholder="请输入用户名"
                           <%--onkeyup="validateUsername()"--%>onkeyup="user()">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input class="form-control" type="text" id="email" name="email"
                           placeholder="请输入账号" <%--onkeyup="isEmail()"--%> onkeyup="user()">
                </div>
                <div class="form-group">
                    <label for="age">年龄</label>
                    <input class="form-control" type="text" id="age" name="age"
                           placeholder="请输入账号" >
                </div>
                <div class="form-group">
                    <label for="sex">性别</label>

                        <select id="sex" name="sex" class="form-control" type="text" style="width: 183px">
                            <option value="">请选择</option>
                            <option value="1">男</option>
                            <option value="2">女</option>
                        </select>

                </div>
                <div class="form-group">
                    <label for="phone">电话</label>
                    <input class="form-control" type="text" id="phone" name="phone"
                           placeholder="请输入电话" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')" >
                </div>
                <div class="form-group">
                    <label for="address">地址</label>
                    <input class="form-control" type="text" id="address" name="address"
                           placeholder="请输入地址" >
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码">
                </div>
                <div class="form-group">
                    <label for="determinePassword1">确认密码</label>
                    <input type="password" name="determinePassword1" class="form-control col-sm-5"
                           id="determinePassword1" <%--onkeyup="validate()"--%> onkeyup="user()">
                </div>
            </div>
        </fieldset>
    </form>
</div>

<div id="adduser-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a id="adduser_buttonemail_log" class="easyui-linkbutton" data-options=" iconCls:'icon-page-white-error'"><span
            id="tishiemail"></span></a>
    <a id="adduser_buttonsuser_log" class="easyui-linkbutton" data-options=" iconCls:'icon-page-white-error'"><span
            id="tishiuser"></span></a>
    <a id="adduser_buttons_log" class="easyui-linkbutton" data-options=" iconCls:'icon-page-white-error'"><span
            id="tishis"></span></a>
    <a id="adduser_buttons_submit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitAddUser()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'" onclick="$('#adduser-dialog').window('close')">取消</a>
</div>

<div id="role-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitRole()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'" onclick="RoledialogClose()">取消</a>
</div>
<script type="text/javascript">

</script>