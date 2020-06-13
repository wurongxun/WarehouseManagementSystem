<%--
  Created by IntelliJ IDEA.
  User: WuRongXun
  Date: 2020/2/9
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script type="text/javascript"
        src="<%=basePath%>/static/js/InformationManagement/WarehouseInformation_list.js"></script>
<div id="WarehouseInformation_list_toobar_id" style="padding: 10px; display: inherit; height:9%;overflow-x:auto;"
     onkeypress="enter(event) ">
    <form id="find_WarehouseInformation_form_id" action=""
          style="float: none; display: inline-block;">
        <input name="warehouseName" class="easyui-textbox" data-options="prompt:'仓库名称'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="warehousePersonInChargeName" class="easyui-textbox" data-options="prompt:'仓库负责人名'"
               style="width: 130px"
               onkeypress="enter(event)">
        <input name="warehouseNumber" class="easyui-textbox" data-options="prompt:'仓库号'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="warehousePersonInChargePhone" class="easyui-textbox" data-options="prompt:'负责人电话'" style="width: 130px"
               onkeypress="enter(event)">
        <select class="easyui-combobox" name="warehouseType"
                data-options="prompt:'仓库类型',validType:'length[0,20]',editable:false" style="width: 100px">
            <option value="">请选择</option>
            <option value="1">冷冻库</option>
            <option value="2">常温库</option>
        </select>
        <%--<select class="easyui-combobox"  id="Find-commodityCategory" name="Find-commodityCategory"
                data-options="prompt:'商品大类',editable:false" style="width: 100px">
        </select>--%>
        <select class="easyui-combobox" name="status" data-options="prompt:'商品状态',editable:false" style="width: 100px">
            <option value="1">启用</option>
            <option value="2">停用</option>
        </select>
        <a class=" easyui-linkbutton" data-options="iconCls:'fa micons-search',plain:true" onclick="findAction()">搜索</a>
        <a class="easyui-linkbutton"  data-options="iconCls:'fa micons-empty',plain:true"   onclick="doClearAction()">清空</a>
    </form>
</div>
<div style="width:100%;height:91%;">
    <div id="sdsd" class="easyui-panel" data-options="fit:true,border:false">
        <table id="WarehouseInformation_table"></table>
    </div>
</div>
<div id="AddWarehouseInformation-dialog" class="easyui-dialog" title="添加仓库信息"
     data-options="modal:true,iconCls:'icon-add',closed:true,footer:'#WarehouseInformation-buttons'"
     style="width:90%;height:80%;">
    <form class="form-inline " role="form" id="AddWarehouseInformation-dialog-from-id">
        <fieldset>
            <legend></legend>
            <div <%--class="form-group"--%>>
                <label class="col-sm-1 control-label" for="warehouseName">仓库名称</label>
                <div class="col-sm-3">
                    <input class="form-control" name="warehouseName" id="warehouseName" type="text"
                           onkeyup="value=value.replace(/[^\w\u4E00-\u9FA5]/g, '')" placeholder="仓库名称"/>
                </div>
                <label class="col-sm-1 control-label" for="warehouseNumber">仓库号</label>
                <div class="col-sm-3">
                    <input class="form-control" id="warehouseNumber" name="warehouseNumber" type="text"
                           onkeyup="checkInt(this.value,1,500,this.id);"
                           placeholder="仓库号1-500"/>
                </div>
                <label class="col-sm-1 control-label" for="warehousePersonInChargeId">负责人姓名</label>
                <div class="col-sm-3">
                    <select id="warehousePersonInChargeId" name="warehousePersonInChargeId" class="form-control"
                            type="text"
                            style="width: 183px">
                    </select>
                </div>
                <label class="col-sm-1 control-label" for="warehouseType">仓库类型</label>
                <div class="col-sm-3">
                    <select id="warehouseType" name="warehouseType" class="form-control" type="text"
                            style="width: 183px">
                        <option value="">请选择</option>
                        <option value="1">冷冻库</option>
                        <option value="2">常温库</option>
                    </select>
                </div>
                <%--  <label class="col-sm-1 control-label" for="storehouseBarCode">仓库条码</label>
                  <div class="col-sm-3">
                      <input class="form-control" name="storehouseBarCode" id="storehouseBarCode" type="text"
                             onkeyup="this.value=this.value.replace(/[^A-Z0-9\\-]/g,'');"
                             placeholder="商品条码"/>
                  </div>--%>
                <label class="col-sm-1 control-label" for="openingDate">启用日期</label>
                <div class="col-sm-3">
                    <input id='openingDate' name="openingDate" class="easyui-datetimebox"
                           data-options="formatter:formatter,parser:parser," style="width: 183px">
                </div>
                <label class="col-sm-1 control-label" for="lengthUnit">尺寸单位</label>
                <div class="col-sm-3">
                    <input class="form-control" type="text" value="M" id="lengthUnit" name="lengthUnit"
                           placeholder="尺寸单位:'M/CM..'" disabled>
                </div>
            </div>
        </fieldset>
        <fieldset>
            <legend></legend>
            <div <%--class="form-group"--%>>
                <label class="col-sm-1 control-label" for="area">面积</label>
                <div class="col-sm-3">
                    <input class="form-control" id="area" name="area" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="面积"/>
                </div>
                <label class="col-sm-1 control-label" for="wide">宽度</label>
                <div class="col-sm-3">
                    <input class="form-control" id="wide" name="wide" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="宽度"/>
                </div>
                <label class="col-sm-1 control-label" for="height">高度</label>
                <div class="col-sm-3">
                    <input class="form-control" id="height" name="height" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="高度"/>
                </div>
                <%--  </div>
                  <div class="form-group">--%>
                <label class="col-sm-1 control-label" for="commodityCategory">仓库存储大类</label>
                <div class="col-sm-3">
                    <select id="commodityCategory" name="commodityCategory"
                            class="form-control" type="text" style="width: 183px">
                    </select>
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
        <fieldset>
            <legend></legend>
            <div>
                <label for="warehouseRemark" class="col-sm-1 control-label">商品备注</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="warehouseRemark" name="warehouseRemark" type="text"
                              placeholder="备注"/>
                </div>
            </div>
        </fieldset>
    </form>
</div>

<div id="UpWarehouseInformation-dialog" class="easyui-dialog" title="修改仓库信息"
     data-options="modal:true,iconCls:'icon-add',closed:true,href:'<%=request.getContextPath()%>/warehouseInformation.html?act=go_edit',footer:'#UpWarehouseInformation-buttons'"
     style="width:90%;height:80%;"></div>
<div id="WarehouseInformation-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a id="adduser_buttons_submit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="submitAddWarehouseInformation()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick=" $('#AddWarehouseInformation-dialog-from-id').form('clear');">清空</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick="$('#AddWarehouseInformation-dialog').window('close')">取消</a>
</div>
<div id="UpWarehouseInformation-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a id="upuser_buttons_submit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="submitUpWarehouseInformation()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick=" $('#UpWarehouseInformation_dialog_Edit_from').form('clear');">清空</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick="$('#UpWarehouseInformation-dialog').window('close')">取消</a>
</div>