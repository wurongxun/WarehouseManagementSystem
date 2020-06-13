<%--
  Created by IntelliJ IDEA.
  User: Eason
  Date: 2020-02-27
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script type="text/javascript"
        src="<%=basePath%>/static/js/InformationManagement/CommodityInformation_edit.js"></script>

<form class="form-inline " role="form" id="UpCommodityInformation_dialog_Edit_from" action="#">
    <input id="_id" name="_id" type="hidden">
    <input id="_commodityId" name="_commodityId" type="hidden">
    <fieldset>
        <legend></legend>
        <legend>商品信息编辑</legend>
        <div class="form-group">
            <label class="col-sm-1 control-label" for="_commodityName">商品名称</label>
            <div class="col-sm-3">
                <input class="form-control" name="_commodityName" id="_commodityName" type="text"
                       onkeyup="value=value.replace(/[^\w\u4E00-\u9FA5]/g, '')" placeholder="商品名称"/>
            </div>
            <label class="col-sm-1 control-label" for="_commodityAbbreviation">商品简称</label>
            <div class="col-sm-3">
                <input class="form-control" id="_commodityAbbreviation" name="_commodityAbbreviation"
                       onkeyup="value=value.replace(/[^\w\u4E00-\u9FA5]/g, '')" type="text" placeholder="商品简称"/>
            </div>
            <label class="col-sm-1 control-label" for="_commodityMn">商品型号</label>
            <div class="col-sm-3">
                <input class="form-control" name="_commodityMn" id="_commodityMn"
                       onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9\\-]/g,'');"/>
            </div>
            <label class="col-sm-1 control-label" for="_commoditySpecs">商品规格</label>
            <div class="col-sm-3">
                <input class="form-control" id="_commoditySpecs" name="_commoditySpecs"
                       onkeyup="this.value=this.value.replace(/[^A-Z0-9\\-]/g,'');"/>
            </div>
            <label class="col-sm-1 control-label" for="_commodityColor">商品颜色</label>
            <div class="col-sm-3">
                <input class="form-control" id="_commodityColor" name="_commodityColor" type="text"/>
            </div>
            <label class="col-sm-1 control-label" for="_commodityAttribute">商品属性</label>
            <div class="col-sm-3">
                <input class="form-control" id="_commodityAttribute" name="_commodityAttribute" type="text"
                       placeholder="商品属性"/>
            </div>
            <label class="col-sm-1 control-label" for="_commodityBarCode">商品条码</label>
            <div class="col-sm-3">
                <input class="form-control" name="_commodityBarCode" id="_commodityBarCode" type="text"
                       onkeyup="this.value=this.value.replace(/[^A-Z0-9\\-]/g,'');"
                       placeholder="商品条码"/>
            </div>
            <label class="col-sm-1 control-label" for="_commodityBrand">商品品牌:</label>
            <div class="col-sm-3">
                <input class="form-control" type="text" id="_commodityBrand" name="_commodityBrand" placeholder="商品品牌"/>
            </div>
            <label class="col-sm-1 control-label" for="_storageTemperature">存放温度</label>
            <div class="col-sm-3">
                <input class="form-control" name="_storageTemperature" id="_storageTemperature" type="text"
                       placeholder="存放温度" onkeyup="this.value=this.value.replace(/[^\-?\d.]/g,'')"/>
            </div>
            <label class="col-sm-1 control-label" for="_encoderNumber">码盘单层数量</label>
            <div class="col-sm-3">
                <input class="form-control" name="_encoderNumber" id="_encoderNumber"
                       onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="码盘单层数量"/>
            </div>
            <label class="col-sm-1 control-label" for="_encoderHeight">码盘层高</label>
            <div class="col-sm-3">
                <input class="form-control" name="_encoderHeight" id="_encoderHeight"
                       onkeyup="value=value.replace(/[^\d]/g,'')" type="text" placeholder="码盘层高"/>
            </div>

            <label class="col-sm-1 control-label" for="_productionDate">生产日期</label>
            <div class="col-sm-3">
                <input id='_productionDate' name="_productionDate" class="easyui-datetimebox"
                       data-options="formatter:formatter,parser:parser,required:true,showSeconds:true" style="width: 183px">
            </div>
        </div>
    </fieldset>
    <fieldset>
        <legend></legend>
        <div class="form-group">
            <label class="col-sm-1 control-label" for="_qgp">保质期</label>
            <div class="col-sm-3">
                <input class="form-control" id="_qgp" name="_qgp" type="text"
                       onkeyup="this.value=this.value.replace(/[^0-9\\-]/g,'');" placeholder="保质期/月"/>
            </div>
            <label class="col-sm-1 control-label" for="_dimensionUnit">尺寸单位</label>
            <div class="col-sm-3">
                <input class="form-control" type="text" value="M" id="_dimensionUnit" name="_dimensionUnit"
                       placeholder="尺寸单位:'M/CM..'" disabled>
            </div>
            <label class="col-sm-1 control-label" for="_massUnit">质量单位</label>
            <div class="col-sm-3">
                <input class="form-control" type="text" id="_massUnit" value="KG" name="_massUnit" placeholder="质量单位:'KG/G..'"
                       onkeyup="this.value=this.value.replace(/[^A-Z]/g,'')" disabled>
            </div>
            <label class="col-sm-1 control-label" for="_itemLength">单品长</label>
            <div class="col-sm-3">
                <input class="form-control" id="_itemLength" name="_itemLength" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="单品长"/>
            </div>
            <label class="col-sm-1 control-label" for="_itemWide">单品宽</label>
            <div class="col-sm-3">
                <input class="form-control" id="_itemWide" name="_itemWide" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="单品宽"/>
            </div>
            <label class="col-sm-1 control-label" for="_itemHigh">单品高</label>
            <div class="col-sm-3">
                <input class="form-control" id="_itemHigh" name="_itemHigh" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="单品高"/>
            </div>
            <label class="col-sm-1 control-label" for="_fclLength">长整箱</label>
            <div class="col-sm-3">
                <input class="form-control" id="_fclLength" name="_fclLength" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="长整箱"/>
            </div>
            <label class="col-sm-1 control-label" for="_fclWide">宽整箱</label>
            <div class="col-sm-3">
                <input class="form-control" id="_fclWide" name="_fclWide" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="宽整箱"/>
            </div>
            <label class="col-sm-1 control-label" for="_fclHigh">高整箱</label>
            <div class="col-sm-3">
                <input class="form-control" id="_fclHigh" name="_fclHigh" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="高整箱"/>
            </div>
            <label class="col-sm-1 control-label" for="_singleVolume">单个体积</label>
            <div class="col-sm-3">
                <input class="form-control" id="_singleVolume" name="_singleVolume" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="单个体积"/>
            </div>
            <label class="col-sm-1 control-label" for="_totalVolume">整箱体积</label>
            <div class="col-sm-3">
                <input class="form-control" id="_totalVolume" name="_totalVolume" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="总体积"/>
            </div>
            <label class="col-sm-1 control-label" for="_grossWeight">毛重</label>
            <div class="col-sm-3">
                <input class="form-control" id="_grossWeight" name="_grossWeight" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="毛重"/>
            </div>
            <label class="col-sm-1 control-label" for="_netWeight">净重</label>
            <div class="col-sm-3">
                <input class="form-control" id="_netWeight" name="_netWeight" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="净重"/>
            </div>
            <label class="col-sm-1 control-label" for="_commodityUnitPrice">商品单价</label>
            <div class="col-sm-3">
                <input class="form-control" id="_commodityUnitPrice" name="_commodityUnitPrice" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="商品单价"/>
            </div>
            <label class="col-sm-1 control-label" for="_commodityTotalPrice">箱总价</label>
            <div class="col-sm-3">
                <input class="form-control" id="_commodityTotalPrice" name="_commodityTotalPrice" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="箱总价"/>
            </div>
        </div>
    </fieldset>
    <fieldset>
        <legend></legend>
        <div>
            <label class="col-sm-1 control-label" for="_supplierId">所属供应商</label>
            <div class="col-sm-3">
                <select id="_supplierId" name="_supplierId" class="form-control" type="text" style="width: 183px">
                </select>
            </div>
            <label class="col-sm-1 control-label" for="_commodityCategory">商品大类</label>
            <div class="col-sm-3">
                <select id="_commodityCategory" name="_commodityCategory" onchange="ChangeCommoditySubgroup_Edit()"
                        class="form-control" type="text" style="width: 183px">
                </select>
            </div>
            <label class="col-sm-1 control-label" for="_commoditySubgroup">商品小类</label>
            <div class="col-sm-3">
                <select id="_commoditySubgroup" name="_commoditySubgroup" class="form-control" type="text"
                        style="width: 183px">
                </select>
            </div>
            <label class="col-sm-1 control-label" for="_status">状态</label>
            <div class="col-sm-3">
                <select id="_status" name="_status" class="form-control" type="text" style="width: 183px">
                    <option value="">请选择</option>
                    <option value="1">启用</option>
                    <option value="2">停用</option>
                </select>
            </div>
            <label class="col-sm-1 control-label" for="_storageUnit">存储单位</label>
            <div class="col-sm-3">
                <select id="_storageUnit" name="_storageUnit" class="form-control" type="text" style="width: 183px">
                    <option value="">请选择</option>
                    <option value="1">箱</option>
                    <option value="2">单品</option>
                </select>
            </div>
            <label class="col-sm-1 control-label" for="_commodityStorageType">存储类型</label>
            <div class="col-sm-3">
                <select id="_commodityStorageType" name="_commodityStorageType" class="form-control" type="text"
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
            <label for="_commodityRemark" class="col-sm-1 control-label">商品备注</label>
            <div class="col-sm-10">
                <textarea class="form-control" id="_commodityRemark" name="_commodityRemark" type="text"
                          placeholder="备注"/>
            </div>
        </div>
    </fieldset>
</form>