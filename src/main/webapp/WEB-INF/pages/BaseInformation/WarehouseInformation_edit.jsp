<%--
  Created by IntelliJ IDEA.
  User: Eason
  Date: 2020-03-02
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-inline " role="form" id="UpWarehouseInformation_dialog_Edit_from" action="#">
    <input id="_id" name="_id" type="hidden">
    <fieldset>
        <legend></legend>
        <div <%--class="form-group"--%>>
            <label class="col-sm-1 control-label" for="_warehouseName">仓库名称</label>
            <div class="col-sm-3">
                <input class="form-control" name="_warehouseName" id="_warehouseName" type="text"
                       onkeyup="value=value.replace(/[^\w\u4E00-\u9FA5]/g, '')" placeholder="仓库名称"/>
            </div>
            <label class="col-sm-1 control-label" for="_warehousePersonInChargeId">负责人姓名</label>
            <div class="col-sm-3">
                <select id="_warehousePersonInChargeId" name="_warehousePersonInChargeId" class="form-control"
                        type="text"
                        style="width: 183px">
                </select>
            </div>
            <label class="col-sm-1 control-label" for="_warehouseType">仓库类型</label>
            <div class="col-sm-3">
                <select id="_warehouseType" name="_warehouseType" class="form-control" type="text"
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
            <label class="col-sm-1 control-label" for="_openingDate">启用日期</label>
            <div class="col-sm-3">
                <input id='_openingDate' name="_openingDate" class="easyui-datetimebox"
                       data-options="formatter:ww3,parser:w3" style="width: 183px">
            </div>
            <label class="col-sm-1 control-label" for="_lengthUnit">尺寸单位</label>
            <div class="col-sm-3">
                <input class="form-control" type="text" value="M" id="_lengthUnit" name="_lengthUnit"
                       placeholder="尺寸单位:'M/CM..'" disabled>
            </div>
        </div>
    </fieldset>
    <fieldset>
        <legend></legend>
        <div <%--class="form-group"--%>>
            <label class="col-sm-1 control-label" for="_area">面积</label>
            <div class="col-sm-3">
                <input class="form-control" id="_area" name="_area" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="面积"/>
            </div>
            <label class="col-sm-1 control-label" for="_wide">宽度</label>
            <div class="col-sm-3">
                <input class="form-control" id="_wide" name="_wide" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="宽度"/>
            </div>
            <label class="col-sm-1 control-label" for="_height">高度</label>
            <div class="col-sm-3">
                <input class="form-control" id="_height" name="_height" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="高度"/>
            </div>
            <%--  </div>
              <div class="form-group">--%>
            <label class="col-sm-1 control-label" for="_commodityCategory">仓库存储大类</label>
            <div class="col-sm-3">
                <select id="_commodityCategory" name="_commodityCategory"
                        class="form-control" type="text" style="width: 183px">
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
        </div>
    </fieldset>
    <fieldset>
        <legend></legend>
        <div>
            <label for="_warehouseRemark" class="col-sm-1 control-label">商品备注</label>
            <div class="col-sm-10">
                    <textarea class="form-control" id="_warehouseRemark" name="_warehouseRemark" type="text"
                              placeholder="备注"/>
            </div>
        </div>
    </fieldset>
</form>