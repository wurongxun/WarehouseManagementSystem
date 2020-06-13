<%--
  Created by IntelliJ IDEA.
  User: WuRongXun
  Date: 2020/2/4
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script type="text/javascript"
        src="<%=basePath%>/static/js/InformationManagement/CommodityInformation_list.js"></script>
<div id="credit_CommodityInformation_list_toobar_id" style="padding: 10px; display: inherit; height:9%;overflow-x:auto;"
     onkeypress="enter(event) ">
    <form id="find_CommodityInformation_form_id" action="" style="float: none; display: inline-block;">
        <input name="commodityName" class="easyui-textbox" data-options="prompt:'商品名称'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="supplierName" class="easyui-textbox" data-options="prompt:'所属供应商'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="supplierPhone" class="easyui-textbox" data-options="prompt:'供应商电话'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="commodityMn" class="easyui-textbox" data-options="prompt:'商品型号'" style="width: 130px"
               onkeypress="enter(event)">
        <select class="easyui-combobox" name="status" data-options="prompt:'商品状态',validType:'length[0,20]',editable:false" style="width: 100px">
            <option value="1">启用</option>
            <option value="2">停用</option>
        </select>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-search',plain:true" onclick="findAction()">搜索</a>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-empty',plain:true"  onclick="doClearAction()">清空</a>
    </form>
</div>
<div style="width:100%;height:91%;">
    <div id="sdsd" class="easyui-panel" data-options="fit:true,border:false">
        <table id="CommodityInformation_table_id"></table>
    </div>
</div>
<div id="AddCommodityInformation-dialog" class="easyui-dialog" title="添加商品信息"
     data-options="modal:true,iconCls:'icon-add',closed:true,footer:'#AddCommodityInformation-buttons'"
     style="width:90%;height:80%;">
    <form class="form-inline " role="form" id="AddCommodityInformation_dialog_from_id">
        <fieldset>
            <legend></legend>
            <legend>商品信息编辑</legend>
            <div class="form-group">
                <label class="col-sm-1 control-label" for="commodityName">商品名称</label>
                <div class="col-sm-3">
                    <input class="form-control" name="commodityName" id="commodityName" type="text"
                           onkeyup="value=value.replace(/[^\w\u4E00-\u9FA5]/g, '')" placeholder="商品名称"/>
                </div>
                <label class="col-sm-1 control-label" for="commodityAbbreviation">商品简称</label>
                <div class="col-sm-3">
                    <input class="form-control" name="commodityAbbreviation" id="commodityAbbreviation"
                           onkeyup="value=value.replace(/[^\w\u4E00-\u9FA5]/g, '')" type="text" placeholder="商品简称"/>
                </div>
                <label class="col-sm-1 control-label" for="commodityMn">商品型号</label>
                <div class="col-sm-3">
                    <input class="form-control" name="commodityMn" id="commodityMn"
                           onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9\\-]/g,'');"/>
                </div>
                <label class="col-sm-1 control-label" for="commoditySpecs">商品规格</label>
                <div class="col-sm-3">
                    <input class="form-control" id="commoditySpecs" name="commoditySpecs"
                           onkeyup="this.value=this.value.replace(/[^A-Z0-9\\-]/g,'');"/>
                </div>
                <label class="col-sm-1 control-label" for="commodityColor">商品颜色</label>
                <div class="col-sm-3">
                    <input class="form-control" id="commodityColor" name="commodityColor" type="text"/>
                </div>
                <label class="col-sm-1 control-label" for="commodityAttribute">商品属性</label>
                <div class="col-sm-3">
                    <input class="form-control" id="commodityAttribute" name="commodityAttribute" type="text"
                           placeholder="商品属性"/>
                </div>
                <label class="col-sm-1 control-label" for="commodityBarCode">商品条码</label>
                <div class="col-sm-3">
                    <input class="form-control" name="commodityBarCode" id="commodityBarCode" type="text"
                           onkeyup="this.value=this.value.replace(/[^A-Z0-9\\-]/g,'');"
                           placeholder="商品条码"/>
                </div>
                <label class="col-sm-1 control-label" for="commodityBrand">商品品牌:</label>
                <div class="col-sm-3">
                    <input class="form-control" type="text" id="commodityBrand" name="commodityBrand"
                           placeholder="商品品牌"/>
                </div>
                <label class="col-sm-1 control-label" for="storageTemperature">存放温度</label>
                <div class="col-sm-3">
                    <input class="form-control" name="storageTemperature" id="storageTemperature" type="text"
                           placeholder="存放温度" onkeyup="this.value=this.value.replace(/[^\-?\d.]/g,'')"/>
                </div>
                <label class="col-sm-1 control-label" for="encoderNumber">码盘单层数量</label>
                <div class="col-sm-3">
                    <input class="form-control" name="encoderNumber" id="encoderNumber"
                           onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="码盘单层数量"/>
                </div>
                <label class="col-sm-1 control-label" for="encoderHeight">码盘层高</label>
                <div class="col-sm-3">
                    <input class="form-control" name="encoderHeight" id="encoderHeight"
                           onkeyup="value=value.replace(/[^\d]/g,'')" type="text" placeholder="码盘层高"/>
                </div>

                <label class="col-sm-1 control-label" for="productionDate">生产日期</label>
                <div class="col-sm-3">
                    <input id='productionDate' name="productionDate" class="easyui-datetimebox"
                           data-options="formatter:formatter,parser:parser,required:true,showSeconds:true" style="width: 183px">
                </div>
            </div>
        </fieldset>
        <fieldset>
            <legend></legend>
            <div class="form-group">
                <label class="col-sm-1 control-label" for="qgp">保质期</label>
                <div class="col-sm-3">
                    <input class="form-control" id="qgp" name="qgp" type="text"
                           onkeyup="this.value=this.value.replace(/[^0-9\\-]/g,'');" placeholder="保质期/月"/>
                </div>
                <label class="col-sm-1 control-label" for="dimensionUnit">尺寸单位</label>
                <div class="col-sm-3">
                    <input class="form-control" type="text"  value="M" id="dimensionUnit" name="dimensionUnit"
                           placeholder="尺寸单位默认:'M/米..'" disabled>
                </div>
                <label class="col-sm-1 control-label" for="massUnit">质量单位</label>
                <div class="col-sm-3">
                    <input class="form-control" type="text" value="KG" id="massUnit" name="massUnit" placeholder="默认单位:'KG/千克'"
                           onkeyup="this.value=this.value.replace(/[^A-Z]/g,'')" disabled>
                </div>
                <label class="col-sm-1 control-label" for="itemLength">单品长</label>
                <div class="col-sm-3">
                    <input class="form-control" id="itemLength" name="itemLength" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="单品长"/>
                </div>
                <label class="col-sm-1 control-label" for="itemWide">单品宽</label>
                <div class="col-sm-3">
                    <input class="form-control" id="itemWide" name="itemWide" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="单品宽"/>
                </div>
                <label class="col-sm-1 control-label" for="itemHigh">单品高</label>
                <div class="col-sm-3">
                    <input class="form-control" id="itemHigh" name="itemHigh" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="单品高"/>
                </div>
                <label class="col-sm-1 control-label" for="fclLength">长整箱</label>
                <div class="col-sm-3">
                    <input class="form-control" id="fclLength" name="fclLength" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="长整箱"/>
                </div>
                <label class="col-sm-1 control-label" for="fclWide">宽整箱</label>
                <div class="col-sm-3">
                    <input class="form-control" id="fclWide" name="fclWide" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="宽整箱"/>
                </div>
                <label class="col-sm-1 control-label" for="fclHigh">高整箱</label>
                <div class="col-sm-3">
                    <input class="form-control" id="fclHigh" name="fclHigh" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="高整箱"/>
                </div>
                <label class="col-sm-1 control-label" for="singleVolume">单个体积</label>
                <div class="col-sm-3">
                    <input class="form-control" id="singleVolume" name="singleVolume" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="单个体积"/>
                </div>
                <label class="col-sm-1 control-label" for="totalVolume">整箱体积</label>
                <div class="col-sm-3">
                    <input class="form-control" id="totalVolume" name="totalVolume" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="总体积"/>
                </div>
                <label class="col-sm-1 control-label" for="grossWeight">毛重</label>
                <div class="col-sm-3">
                    <input class="form-control" id="grossWeight" name="grossWeight" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="毛重"/>
                </div>
                <label class="col-sm-1 control-label" for="netWeight">净重</label>
                <div class="col-sm-3">
                    <input class="form-control" id="netWeight" name="netWeight" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="净重"/>
                </div>
                <label class="col-sm-1 control-label" for="commodityUnitPrice">商品单价</label>
                <div class="col-sm-3">
                    <input class="form-control" id="commodityUnitPrice" name="commodityUnitPrice" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="商品单价"/>
                </div>
                <label class="col-sm-1 control-label" for="commodityTotalPrice">箱总价</label>
                <div class="col-sm-3">
                    <input class="form-control" id="commodityTotalPrice" name="commodityTotalPrice" type="text"
                           onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="箱总价"/>
                </div>
            </div>
        </fieldset>
        <fieldset>
            <legend></legend>
            <div>
                <label class="col-sm-1 control-label" for="supplierId">所属供应商</label>
                <div class="col-sm-3">
                    <select id="supplierId" name="supplierId" class="form-control" type="text" style="width: 183px">
                    </select>
                </div>
                <label class="col-sm-1 control-label" for="commodityCategory">商品大类</label>
                <div class="col-sm-3">
                    <select id="commodityCategory" name="commodityCategory" onchange="ChangeCommoditySubgroup()"
                            class="form-control" type="text" style="width: 183px">
                    </select>
                </div>
                <label class="col-sm-1 control-label" for="commoditySubgroup">商品小类</label>
                <div class="col-sm-3">
                    <select id="commoditySubgroup" name="commoditySubgroup" class="form-control" type="text"
                            style="width: 183px">
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
                <label class="col-sm-1 control-label" for="storageUnit">存储单位</label>
                <div class="col-sm-3">
                    <select id="storageUnit" name="storageUnit" class="form-control" type="text" style="width: 183px">
                        <option value="">请选择</option>
                        <option value="1">箱</option>
                        <option value="2">单品</option>
                    </select>
                </div>
                <label class="col-sm-1 control-label" for="commodityStorageType">存储类型</label>
                <div class="col-sm-3">
                    <select id="commodityStorageType" name="commodityStorageType" class="form-control" type="text"
                            style="width: 183px">
                        <option value="">请选择</option>
                        <option value="1">冷冻库</option>
                        <option value="2">常温库</option>
                    </select>
                </div>
            </div>
        </fieldset>
        <fieldset>
            <div form-group>
                <label for="commodityRemark" class="col-sm-1 control-label">商品备注</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="commodityRemark" name="commodityRemark" type="text"
                              placeholder="备注"/>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<div id="UpCommodityInformation-dialog"
     class="easyui-dialog"
     title="修改商品信息"
     data-options="modal:true,iconCls:'icon-add',closed:true,href:'<%=request.getContextPath()%>/commodityInformation.html?act=go_edit',footer:'#UpCommodityInformation-buttons'"
     style="width:90%;height:80%;">
</div>
<div id="AddCommodityInformation-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a id="adduser_buttons_submit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="submitCommodityInformation()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick=" $('#AddCommodityInformation_dialog_from_id').form('clear');">清空</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick="$('#AddCommodityInformation-dialog').window('close')">取消</a>
</div>
<div id="UpCommodityInformation-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a id="up_buttons_submit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="submitUpCommodityInformation()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick="$('#UpCommodityInformation-dialog').window('close')">取消</a>
</div>