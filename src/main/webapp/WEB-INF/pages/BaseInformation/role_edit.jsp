<%--
  Created by IntelliJ IDEA.
  User: WuRongXun
  Date: 2019/12/22
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<form id="role-from" class="form-inline" action="#">
    <input name="id" type="hidden">
    <div class="form-group">
        <label for="exampleInputName2">角色名</label>
        <input type="text" class="form-control" name="roleName" id="exampleInputName2" placeholder="Jane Doe">
    </div>
    <div class="form-group">
        <label for="exampleInputEmail2">备&nbsp;&nbsp;&nbsp;注</label>
        <input type="email" class="form-control" name="remark" id="exampleInputEmail2" placeholder="remark">
    </div>
    <div class="form-group">
        <label for="exampleInputEmail2">状态</label>
        <select id="usertype" name="status" class="selectpicker">
            <option value="1">启用</option>
            <option value="0">停用</option>
        </select>

    </div>

</form>--%>
<form class="role-from" role="form" id="role-from">
    <fieldset>
        <legend></legend>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="exampleInputName2">角色名</label>
            <div class="col-sm-4">
                <input class="form-control" name="roleName" id="exampleInputName2" type="text" placeholder="英文小写"  onkeyup="this.value=this.value.replace(/[^a-z]/g,'')"/>
            </div>
        </div>
    </fieldset>
    <fieldset>
        <legend></legend>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="remark"> 备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <div class="col-sm-4">
                <input class="form-control" name="remark" id="remark" type="text" placeholder="中文"  onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')"/>
            </div>
        </div>
    </fieldset>
    <fieldset>
        <legend></legend>
        <div class="form-group">
            <label for="status" class="col-sm-2 control-label">状态&nbsp;</label>
            <div class="col-sm-4">
                <select class="form-control" id="status"  name="status" style="width: 183px">
                    <option value="1">启用</option>
                    <option value="2">停用</option>
                </select>
            </div>
        </div>
    </fieldset>
</form>