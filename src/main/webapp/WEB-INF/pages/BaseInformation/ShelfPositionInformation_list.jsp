<%--
  Created by IntelliJ IDEA.
  User: WuRongXun
  Date: 2020/2/10
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script type="text/javascript"
        src="<%=basePath%>/static/js/InformationManagement/ShelfPositionInformation_list.js"></script>
<div id="ShelfPositionInformation_list_toobar_id" style="padding: 10px; display: inherit; height: 9%;overflow-x:auto;"
     onkeypress="enter(event) ">
    <form id="find_ShelfPositionInformation_form_id" action=""
          style="float: none; display: inline-block;">
        <input name="goodsShelvesNo" class="easyui-textbox" data-options="prompt:'所属货架号'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="shelfPositionCode" class="easyui-textbox" data-options="prompt:'架位编号'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="warehousePersonInCharge" class="easyui-textbox" data-options="prompt:'仓库负责人'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="warehouseCode" class="easyui-textbox" data-options="prompt:'仓库编号'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="warehouseName" class="easyui-textbox" data-options="prompt:'仓库名称'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="shelfPositionRowNo" class="easyui-textbox" data-options="prompt:'架位排号'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="shelfPositionLayerNo" class="easyui-textbox" data-options="prompt:'架位层号'" style="width: 130px"
               onkeypress="enter(event)">
        <select class="easyui-combobox" name="warehouseType"
                data-options="prompt:'仓库类型',validType:'length[0,20]',editable:false"
                style="width: 90px">
            <option value=""></option>
            <option value="1">冷冻库</option>
            <option value="2">普通库</option>
        </select>
        <select class="easyui-combobox" name="status"
                data-options="prompt:'架位状态',validType:'length[0,20]',editable:false"
                style="width: 90px">
            <option value=""></option>
            <option value="1">启用</option>
            <option value="2">未启用</option>
        </select>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-search',plain:true" onclick="findAction()">搜索</a>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-empty',plain:true"
           onclick="doClearAction()">清空</a>
    </form>
</div>
<div style="width:100%;height:91%;">
    <div id="sdsd" class="easyui-panel" data-options="fit:true,border:false">
        <table id="ShelfPositionInformation_table"></table>
    </div>
</div>

<div id="AddShelfPositionInformation-dialog" class="easyui-dialog" title="批量添加架位信息"
     data-options="modal:true,iconCls:'icon-add',closed:true,footer:'#AddShelfPositionInformation-buttons'"
     style="width:60%;height:40%;">
    <form class="form-inline " role="form" id="AddShelfPositionInformation-dialog-from-id">
        <fieldset>
            <legend></legend>
            <div <%--class="form-group"--%>>
                <label class="col-sm-1 control-label" for="warehouseId">选择仓库</label>
                <div class="col-sm-3">
                    <select id="warehouseId" name="warehouseId" onchange="getGoodsShelvesInformationData()"
                            class="form-control"
                            type="text"
                            style="width: 183px" onchange="InputJudgement()">
                    </select>
                </div>
                <label class="col-sm-1 control-label" for="goodsShelvesId">选择货架</label>
                <div class="col-sm-3">
                    <select id="goodsShelvesId" name="goodsShelvesId" class="form-control"
                            type="text"
                            style="width: 183px" onchange="InputJudgement()">
                    </select>
                </div>

                <label class="col-sm-1 control-label" for="dimensionUnit">尺寸单位</label>
                <div class="col-sm-3">
                    <input class="form-control" type="text"  value="M"  id="dimensionUnit" name="dimensionUnit"
                           placeholder="尺寸单位默认:'M/米..'" readonly>
                </div>
                <label class="col-sm-1 control-label" for="massUnit">质量单位</label>
                <div class="col-sm-3">
                    <input class="form-control" type="text" value="KG"  id="massUnit" name="massUnit" placeholder="默认单位:'KG/千克'"
                           readonly>
                </div>

                <label class="col-sm-1 control-label" for="length">架位长</label>
                <div class="col-sm-3">
                    <input class="form-control" id="length" name="length" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="架位长" onchange="InputJudgement()"/>
                </div>
                <label class="col-sm-1 control-label" for="wide">架位宽</label>
                <div class="col-sm-3">
                    <input class="form-control" id="wide" name="wide" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="架位宽" onchange="InputJudgement()"/>
                </div>
                <label class="col-sm-1 control-label" for="height">架位高</label>
                <div class="col-sm-3">
                    <input class="form-control" id="height" name="height" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="架位高" onchange="InputJudgement()" />
                </div>

            </div>
        </fieldset>
        <fieldset>
            <legend></legend>
            <div>
                <label class="col-sm-1 control-label" for="status">状态</label>
                <div class="col-sm-3">
                    <select id="status" name="status" class="form-control" type="text" style="width: 183px" onchange="InputJudgement()">
                        <option value="">请选择</option>
                        <option value="1">启用</option>
                        <option value="2">停用</option>
                    </select>
                </div>
            </div>
        </fieldset>
    </form>
</div>

<div id="AddShelfPositionInformation-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a id="addShelfPositionInformation_buttons_submit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="submitAddShelfPositionInformation_Batch()" >提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
        onclick=" $('#AddShelfPositionInformation-dialog-from-id').form('clear'),InputJudgement()">清空</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick="$('#AddShelfPositionInformation-dialog').window('close')">取消</a>
</div>

