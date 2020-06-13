<%--
  Created by IntelliJ IDEA.
  User: WuRongXun
  Date: 2020/2/10
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script type="text/javascript"
        src="<%=basePath%>/static/js/InformationManagement/warehousingEntryInformatin_list.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/BigDecimal/js-big-decimal.min.js"></script>
<%--<script type="text/javascript" src="<%=basePath%>/jquery-easyui-1.7.0/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>--%>
<div id="detailedList_WarehousingInformation_list_toobar_id"
     style="padding: 10px; display: inherit; height:9%;overflow-x:auto;"
     onkeypress="enter(event) ">
    <form id="detailedList_WarehousingInformation_form_id" action="" style="float: none; display: inline-block;">
        <input name="Warehousing_detailedListCode" class="easyui-textbox" data-options="prompt:'入库单编号'"
               style="width: 130px"
               onkeypress="enter(event)">
        <input name="Warehousing_detailedListName" class="easyui-textbox" data-options="prompt:'入库单名称'"
               style="width: 130px"
               onkeypress="enter(event)">
        <input name="Warehousing_staffName" class="easyui-textbox" data-options="prompt:'负责人名'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="Warehousing_receivingDelivererPersonName" class="easyui-textbox" data-options="prompt:'送货人名'"
               style="width: 130px"
               onkeypress="enter(event)">
        <input name="Warehousing_receivingDelivererPersonPhone" class="easyui-textbox" data-options="prompt:'送货人电话'"
               style="width: 130px"
               onkeypress="enter(event)">
        <input name="Warehousing_receivingDelivererPersonAddress" class="easyui-textbox" data-options="prompt:'送货人地址'"
               style="width: 130px"
               onkeypress="enter(event)">

        <select class="easyui-combobox" name="Warehousing_status"
                data-options="prompt:'状态',validType:'length[0,20]',editable:false"
                style="width: 90px">
            <option value=""></option>
            <option value="1">入库成功</option>
            <option value="2">等待审核</option>
            <option value="3">审核成功</option>
            <option value="4">等待入库</option>
            <option value="5">审核不通过</option>
            <option value="6">申请单已取消</option>
            <option value="7">出库成功</option>
            <option value="8">等待出库</option>
        </select>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-search',plain:true"
           onclick="findActionWarehousingEntry()">搜索</a>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-empty',plain:true"
           onclick="doClearAction()">清空</a>
    </form>
</div>
<div style="width:100%;height:91%;">
    <div id="" class="easyui-panel" data-options="fit:true,border:false">
        <table id="detailedList_WarehousingInformation_table"></table>
    </div>
</div>

<%--申请入库 dialog--%>
<div id="applicant_detailedListWarehousing-dialog" class="easyui-dialog" title="申请入库"
     data-options="modal:true,iconCls:'icon-add',closed:true,footer:'#Add_DetaildList_Warehousing_buttons'"
     style="width:90%;height:80%;">
    <div id="" data-options="fit:true,border:false" style="height:20%;overflow-x:auto;">
        <form class="form-inline " role="form" id="applicant_detailedListWarehousing-dialog-from-id">
            <fieldset>
                <legend></legend>
                <div>
                    <label class="col-sm-1 control-label" for="detailedListName">入库单名称</label>
                    <div class="col-sm-3">
                        <input class="form-control" name="detailedListName" required="required" id="detailedListName"
                               type="text"
                               placeholder="入库单名称"/>
                    </div>
                    <label class="col-sm-1 control-label" for="receivingDelivererPersonName">送货人名</label>
                    <div class="col-sm-3">
                        <input class="form-control" name="receivingDelivererPersonName"
                               required="required" id="receivingDelivererPersonName" type="text" placeholder="送货人名"/>
                    </div>
                    <label class="col-sm-1 control-label" for="receivingDelivererPersonPhone">送货人电话</label>
                    <div class="col-sm-3">
                        <input class="form-control" name="receivingDelivererPersonPhone"
                               required="required" id="receivingDelivererPersonPhone" type="text" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')" placeholder="送货人电话"/>
                    </div>
                </div>
                <div>
                    <label class="col-sm-1 control-label" for="receivingDelivererPersonAddress">送货人地址</label>
                    <div class="col-sm-3">
                        <input class="form-control" name="receivingDelivererPersonAddress"
                               required="required" id="receivingDelivererPersonAddress" type="text"
                               placeholder="送货人地址"/>
                    </div>
                </div>
            </fieldset>

        </form>
    </div>
    <div id="" data-options="fit:true,border:false" style="height:80%;background-color:red;overflow-x:auto;">
        <table id="father_CommodityInformationWarehousing_list_table"></table>
    </div>
</div>

<%--table 行数据操作--%>
<div id="tb">
    <a class="easyui-linkbutton" iconCls="icon-add" onclick="addWarehousingInformation()">添加商品</a>
    <a class="easyui-linkbutton" iconCls="icon-cut" onclick="MessagerShow('当前数量： '+CountCommodityQuantity())">合计数量</a>
    <a class="easyui-linkbutton" iconCls="icon-no" onclick="removeit()">删除</a>
</div>

<div id="Add_DetaildList_Warehousing_buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a id="addGoodsShelvesInformation_buttons_submit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="submitAddDetaildListWarehousing()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick=" doClearActionDetailedListWarehousingDialog()">清空</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick="CloseDetailedListWarehousingDialog()">取消</a>
</div>


<%--申请入库 dialog- 选择商品子弹窗--%>
<div id="Sun_CommodityInformation_dialog" class="easyui-dialog" title="请选择商品"
     data-options="modal:true,iconCls:'icon-add',closed:true,footer:'#Sun_CommodityInformation_dialog_buttons'"
     style="width:85%;height:75%;">
    <div id="Sun_CommodityInformation_list_toobar_id"
         style="padding: 10px; display: inherit; height:11%;overflow-x:auto;"
         onkeypress="enter(event) ">
        <form id="find_Sun_CommodityInformation_form_id" action="" style="float: none; display: inline-block;">
            <input name="Sun_commodityName" class="easyui-textbox" data-options="prompt:'商品名称'" style="width: 130px"
                   onkeypress="enter(event)">
            <input name="Sun_supplierName" class="easyui-textbox" data-options="prompt:'所属供应商'" style="width: 130px"
                   onkeypress="enter(event)">
            <input name="Sun_supplierPhone" class="easyui-textbox" data-options="prompt:'供应商电话'" style="width: 130px"
                   onkeypress="enter(event)" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')">
            <input name="Sun_commodityMn" class="easyui-textbox" data-options="prompt:'商品型号'" style="width: 130px"
                   onkeypress="enter(event)">
            <a class="easyui-linkbutton" data-options="iconCls:'fa micons-search',plain:true"
               onclick="findAction_find_Sun_CommodityInformation_form()">搜索</a>
            <a class="easyui-linkbutton" data-options="iconCls:'fa micons-empty',plain:true"
               onclick="Sun_doClearAction()">清空</a>
        </form>
        <div style="float: right; display: inline-block;">
        </div>
    </div>
    <div style="width:100%;height:89%;">
        <div id="" class="easyui-panel" data-options="fit:true,border:false">
            <table id="Sun_CommodityInformation_table_id"></table>
        </div>
    </div>
</div>

<%--选择商品窗 提交按钮--%>
<div id="Sun_CommodityInformation_dialog_buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a id="add_buttons_submit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="submitSun_CommodityInformation_dialog()">确定</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick="$('#Sun_CommodityInformation_dialog').window('close')">取消</a>
</div>


<%--
<div id="UpGoodsShelvesInformation-dialog" class="easyui-dialog" title="修改仓库信息"
     data-options="modal:true,iconCls:'icon-add',closed:true,href:'goodsShelvesInformation.html?act=go_edit',footer:'#UpGoodsShelvesInformation-buttons'"
     style="width:60%;height:40%;"></div>

<div id="UpGoodsShelvesInformation-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a id="UpGoodsShelvesInformation_buttons_submit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="submitUpGoodsShelvesInformation()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick=" $('#UpGoodsShelvesInformation-dialog-Edit-from').form('clear');">清空</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick="$('#UpGoodsShelvesInformation-dialog').window('close')">取消</a>
</div>--%>
<script type="text/javascript">

</script>