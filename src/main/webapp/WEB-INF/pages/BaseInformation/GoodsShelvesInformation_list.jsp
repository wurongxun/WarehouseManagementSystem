<%--
  Created by IntelliJ IDEA.
  User: WuRongXun
  Date: 2020/2/10
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script type="text/javascript"
        src="<%=basePath%>/static/js/InformationManagement/GoodsShelvesInformation_list.js"></script>
<div id="GoodsShelvesInformation_list_toobar_id" style="padding: 10px; display: inherit; height:9%;overflow-x:auto;"
     onkeypress="enter(event) ">
    <form id="find_GoodsShelvesInformation_form_id" action="" style="float: none; display: inline-block;">
        <input name="goodsShelvesName" class="easyui-textbox" data-options="prompt:'货架名称'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="warehouseName" class="easyui-textbox" data-options="prompt:'仓库名称'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="goodsShelvesCode" class="easyui-textbox" data-options="prompt:'货架编号'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="warehouseCode" class="easyui-textbox" data-options="prompt:'仓库编号'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="goodsShelvesNo" class="easyui-textbox" data-options="prompt:'货架号'" style="width: 130px"
               onkeypress="enter(event)">

        <select class="easyui-combobox" name="status"
                data-options="prompt:'状态',validType:'length[0,20]',editable:false"
                style="width: 90px">
            <option value=""></option>
            <option value="1">启用</option>
            <option value="2">停用</option>
        </select>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-search',plain:true" onclick="findAction()">搜索</a>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-empty',plain:true"  onclick="doClearAction()">清空</a>
    </form>
</div>
<div style="width:100%;height:91%;">
    <div id="" class="easyui-panel" data-options="fit:true,border:false">
        <table id="GoodsShelvesInformation_table"></table>
    </div>
</div>
<div id="AddGoodsShelvesInformation-dialog" class="easyui-dialog" title="添加货架信息"
     data-options="modal:true,iconCls:'icon-add',closed:true,footer:'#AddGoodsShelvesInformation-buttons'"
     style="width:90%;height:80%;">
    <form class="form-inline " role="form" id="AddGoodsShelvesInformation-dialog-from-id">
        <fieldset>
            <legend></legend>
            <div <%--class="form-group"--%>>
                <label class="col-sm-1 control-label" for="goodsShelvesName">货架名称</label>
                <div class="col-sm-3">
                    <input class="form-control" name="goodsShelvesName" id="goodsShelvesName" type="text"
                           onkeyup="value=value.replace(/[^\w\u4E00-\u9FA5]/g, '')" placeholder="货架名称"/>
                </div>
                <label class="col-sm-1 control-label" for="goodsShelvesNo">货架号</label>
                <div class="col-sm-3">
                    <input class="form-control" name="goodsShelvesNo" id="goodsShelvesNo" type="text"
                           onkeyup="checkInt(this.value,1,50,this.id);" placeholder="货架号1-50"/>
                </div>
                <label class="col-sm-1 control-label" for="warehouseId">所属仓库</label>
                <div class="col-sm-3">
                    <select id="warehouseId" name="warehouseId" class="form-control"
                            type="text"
                            style="width: 183px">
                    </select>
                </div>

                <label class="col-sm-1 control-label" for="openingDate">启用日期</label>
                <div class="col-sm-3">
                    <input class="easyui-datetimebox" name="openingDate" id='openingDate'
                           data-options="formatter:formatter,parser:parser,required:true,showSeconds:true"
                           style="width:183px">
                </div>

            </div>
        </fieldset>
        <fieldset>
            <legend></legend>
            <div <%--class="form-group"--%>>
                <label class="col-sm-1 control-label" for="goodsShelvesLayerNo">货架层数</label>
                <div class="col-sm-3">
                    <input class="form-control" id="goodsShelvesLayerNo" goodsShelvesLayerNo="area" type="text"
                           onkeyup="checkInt(this.value,0,4,this.id);"
                           placeholder="货架层数1-4"/>
                </div>
                <label class="col-sm-1 control-label" for="goodsShelvesRowNo">货架排数</label>
                <div class="col-sm-3">
                    <input class="form-control" id="goodsShelvesRowNo" name="goodsShelvesRowNo" type="text"
                           maxlength='3' onkeyup="checkInt(this.value,0,100,this.id);"
                           placeholder="货架排数 输入1-100"/>
                </div>
                <label class="col-sm-1 control-label" for="status">状态</label>
                <div class="col-sm-3">
                    <select id="status" name="status" class="form-control" type="text" style="width: 183px">
                        <option value="">请选择</option>
                        <option value="1">启用</option>
                        <option value="2">停用</option>
                    </select>
                </div>
            </div>
        </fieldset>
       <%-- <fieldset>
            <legend></legend>
            <div>
                <label for="warehouseRemark" class="col-sm-1 control-label">商品备注</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="warehouseRemark" name="warehouseRemark" type="text"
                              placeholder="备注"/>
                </div>
            </div>
        </fieldset>--%>
    </form>
</div>

<div id="UpGoodsShelvesInformation-dialog" class="easyui-dialog" title="修改仓库信息"
     data-options="modal:true,iconCls:'icon-add',closed:true,href:'goodsShelvesInformation.html?act=go_edit',footer:'#UpGoodsShelvesInformation-buttons'"
     style="width:60%;height:40%;"></div>
<div id="AddGoodsShelvesInformation-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a id="addGoodsShelvesInformation_buttons_submit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="submitAddGoodsShelvesInformation()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick=" $('#AddGoodsShelvesInformation-dialog-from-id').form('clear');">清空</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick="$('#AddGoodsShelvesInformation-dialog').window('close')">取消</a>
</div>

<div id="UpGoodsShelvesInformation-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a id="UpGoodsShelvesInformation_buttons_submit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="submitUpGoodsShelvesInformation()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick=" $('#UpGoodsShelvesInformation-dialog-Edit-from').form('clear');">清空</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick="$('#UpGoodsShelvesInformation-dialog').window('close')">取消</a>
</div>
