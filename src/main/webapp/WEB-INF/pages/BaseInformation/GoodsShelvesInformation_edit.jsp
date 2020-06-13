<%--
  Created by IntelliJ IDEA.
  User: Eason
  Date: 2020-03-03
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-inline " role="form" id="UpGoodsShelvesInformation-dialog-Edit-from">
    <input id="_id" name="_id" type="hidden">
    <fieldset>
        <legend></legend>
        <div <%--class="form-group"--%>>
            <label class="col-sm-1 control-label" for="_goodsShelvesLayerNo">货架层数</label>
            <div class="col-sm-3">
                <input class="form-control" id="_goodsShelvesLayerNo" name="_goodsShelvesLayerNo" type="text"
                       onkeyup="checkInt(this.value,0,4,this.id);"
                       placeholder="货架层数1-4"/>
            </div>
            <label class="col-sm-1 control-label" for="_goodsShelvesRowNo">货架排数</label>
            <div class="col-sm-3">
                <input class="form-control" id="_goodsShelvesRowNo" name="_goodsShelvesRowNo" type="text"
                       maxlength='3' onkeyup="checkInt(this.value,0,100,this.id);"
                       placeholder="货架排数 输入1-100"/>
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
</form>
