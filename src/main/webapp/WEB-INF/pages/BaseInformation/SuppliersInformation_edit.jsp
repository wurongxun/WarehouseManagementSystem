<%--
  Created by IntelliJ IDEA.
  User: WuRongXun
  Date: 2019/12/22
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="SuppliersInformation-updata-from" class="form-inline" action="#">
    <input id="_id" name="_id" type="hidden">
    <input id="_supplierId" name="_supplierId" type="hidden">
    <fieldset>
        <legend></legend>
        <legend>供应商信息编辑</legend>
        <div class="form-group">
            <label class="col-sm-1 control-label" for="_chineseFullName">供应商中文全称</label>
            <div class="col-sm-3">
                <input class="form-control" name="_chineseFullName" id="_chineseFullName" type="text"
                       placeholder="供应商中文全称" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')"/>
            </div>
            <label class="col-sm-1 control-label" for="_mnemonicCode">助记码</label>
            <div class="col-sm-3">
                <input class="form-control" name="_mnemonicCode" id="_mnemonicCode" type="text"
                       placeholder="英文大写'腾讯'：TX" onkeyup="this.value=this.value.replace(/[^A-Z]/g,'')"/>
            </div>
            <label class="col-sm-1 control-label" for="_supplierAbbreviation">供应商简称</label>
            <div class="col-sm-3">
                <input class="form-control" name="_supplierAbbreviation" id="_supplierAbbreviation"/>
            </div>
            <%--</div>
            <div >--%>
            <label class="col-sm-1 control-label" for="_organizationCode">组织机构代码</label>
            <div class="col-sm-3">
                <input class="form-control" id="_organizationCode"
                       onkeyup="this.value=this.value.replace(/[^A-Z0-9\\-]/g,'');"
                       _name="organizationCode"/>
            </div>
            <label class="col-sm-1 control-label" for="_provinceCode">省份编码</label>
            <div class="col-sm-3">
                <input class="form-control" id="_provinceCode" name="_provinceCode" type="text"
                       onkeyup="this.value=this.value.replace(/[^A-Z0-9\\-]/g,'');"/>
            </div>
            <label class="col-sm-1 control-label" for="_cityCode">城市编码</label>
            <div class="col-sm-3">
                <input class="form-control" name="_cityCode" id="_cityCode" type="text" placeholder="123456"/>
            </div>
            <label class="col-sm-1 control-label" for="_countyCode">国家代码:</label>
            <div class="col-sm-3">
                <input class="form-control" name="_countyCode" id="_countyCode" type="text"
                       onkeyup="this.value=this.value.replace(/[^A-Z0-9\\-]/g,'');"
                       placeholder="单位/万元"/>
            </div>
            <label class="col-sm-1 control-label" for="_address">地址:</label>
            <div class="col-sm-3">
                <input class="form-control" type="text" id="_address" name="_address"/>
            </div>
            <label class="col-sm-1 control-label" for="_supplierPhone">供应商电话:</label>
            <div class="col-sm-3">
                <input class="form-control" name="_supplierPhone" id="_supplierPhone" type="text" maxlength="11"
                       onkeyup="this.value=this.value.replace(/\D/g,'')"
                       placeholder="供应商电话号码"/>
            </div>
            <label class="col-sm-1 control-label" for="_supplierNameEnglish">供应商英文名</label>
            <div class="col-sm-3">
                <input class="form-control" name="_supplierNameEnglish" id="_supplierNameEnglish"
                       onkeyup="this.value=this.value.replace(/[^a-zA-Z]/g,'')"/>
            </div>
            <label class="col-sm-1 control-label" for="_supplierProperty">客户资产</label>
            <div class="col-sm-3">
                <input class="form-control" name="_supplierProperty" id="_supplierProperty" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="单位/万元"/>
            </div>

            <label class="col-sm-1 control-label" for="_mainContact">供应商联系人名</label>
            <div class="col-sm-3">
                <input class="form-control" id="_mainContact" name="_mainContact" type="text"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label" for="_postalCode">邮政编码</label>
            <div class="col-sm-3">
                <input class="form-control" id="_postalCode" name="_postalCode" type="text"
                       onkeyup="this.value=this.value.replace(/[^0-9\\-]/g,'');"/>
            </div>
            <label class="col-sm-1 control-label" for="_email">Email</label>
            <div class="col-sm-3">
                <input class="form-control" type="email" id="_email" name="_email"/>
            </div>
            <label class="col-sm-1 control-label" for="_homePage">主页</label>
            <div class="col-sm-3">
                <input class="form-control" id="_homePage" name="_homePage" type="text"
                       placeholder="网址必须以http://或者https://开头，且必须是个网址^_^！"/>
            </div>
            <label class="col-sm-1 control-label" for="_registeredCapital">注册资本</label>
            <div class="col-sm-3">
                <input class="form-control" id="_registeredCapital" name="_registeredCapital" type="text"
                       onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                       placeholder="注册资本"/>
            </div>
            <label class="col-sm-1 control-label" for="_roadTransportPermit">道路运输许可证</label>
            <div class="col-sm-3">
                <input class="form-control" id="_roadTransportPermit" name="_roadTransportPermit" type="text"
                       placeholder="道路运输许可证号"/>
            </div>
            <label class="col-sm-1 control-label" for="_approvalNo">注册编号</label>
            <div class="col-sm-3">
                <input class="form-control" id="_approvalNo" name="_approvalNo" type="text" placeholder="企业注册编号"/>
            </div>
            <label class="col-sm-1 control-label" for="_legalPerson">法人代表</label>
            <div class="col-sm-3">
                <input class="form-control" id="_legalPerson" name="_legalPerson" type="text" placeholder="法人代表" />
            </div>

            <label class="col-sm-1 control-label" for="_currency">货币</label>
            <div class="col-sm-3">
                <input class="form-control" id="_currency" name="_currency" type="text"/>
            </div>
        </div>
        <div>


            <label class="col-sm-1 control-label" for="_businessLicenseNo">营业执照编号</label>
            <div class="col-sm-3">
                <input class="form-control" type="text" id="_businessLicenseNo" name="_businessLicenseNo"/>
            </div>
            <label class="col-sm-1 control-label" for="_taxRegistrationNo">税务登记</label>
            <div class="col-sm-3">
                <input class="form-control" id="_taxRegistrationNo" name="_taxRegistrationNo" type="text"/>
            </div>

        </div>
        <div>

            <label class="col-sm-1 control-label" for="_mainBusiness">主营业务</label>
            <div class="col-sm-3">
                <input class="form-control" id="_mainBusiness" name="_mainBusiness" type="text"/>
            </div>
            <label class="col-sm-1 control-label" for="_cooperationIntention">营业执照编号</label>
            <div class="col-sm-3">
                <input class="form-control" type="text" id="_cooperationIntention" name="_cooperationIntention"
                       οnkeyup="this.value=this.value.replace(/\D/g,'')"/>
            </div>
            <label class="col-sm-1 control-label" for="_approvalAuthority">批准机关</label>
            <div class="col-sm-3">
                <input class="form-control" id="_approvalAuthority" name="_approvalAuthority" type="text"/>
            </div>
            <label class="col-sm-1 control-label" for="_contactsTwo">联系人2</label>
            <div class="col-sm-3">
                <input class="form-control" id="_contactsTwo" name="_contactsTwo" type="text" placeholder="副联系人"/>
            </div>
            <label class="col-sm-1 control-label" for="_phoneTwo">副联系人电话</label>
            <div class="col-sm-3">
                <input class="form-control" id="_phoneTwo" name="_phoneTwo" type="text" placeholder="副联系人电话"/>
            </div>
            <label class="col-sm-1 control-label" for="_registrationDate">注册时间</label>
            <!--指定 date标记-->
            <div class="col-sm-3 control-label">
                <input id='_registrationDate' name="_registrationDate" class="easyui-datetimebox"
                       data-options="formatter:ww3,parser:w3" style="width: 183px">
            </div>
        </div>
    </fieldset>
    <fieldset>
        <div>
            <label for="_enterpriseAttribute" class="col-sm-1 control-label">企业属性</label>
            <div class="col-sm-3"><select id="_enterpriseAttribute" name="_enterpriseAttribute" class="form-control" style="width: 183px">
                <option value="1">民企</option>
                <option value="2">国企</option>
            </select>
            </div>
            <label for="_supplierLevel" class="col-sm-1 control-label">客户级别</label>
            <div class="col-sm-3">
                <select id="_supplierLevel" name="_supplierLevel" class="form-control" style="width: 183px">
                    <option value="1">1级</option>
                    <option value="2">2级</option>
                    <option value="3">3级</option>
                </select>
            </div>
            <label for="_industryInvolved" class="col-sm-1 control-label">所属行业</label>
            <div class="col-sm-3">
                <select id="_industryInvolved" name="_industryInvolved" class="form-control" style="width: 183px">
                    <option value="1">制造业</option>
                    <option value="2">医疗</option>
                </select>
            </div>
            <label for="_supplierStatus" class="col-sm-1 control-label">供应商状态</label>
            <div class="col-sm-3">
                <select class="form-control" id="_supplierStatus" name="_supplierStatus" title="大魔王" style="width: 183px">
                    <option value="1">启用</option>
                    <option value="2">停用</option>
                </select>
            </div>

        </div>

    </fieldset>
    <fieldset>
        <div form-group>
            <label for="_remark" class="col-sm-1 control-label">备注</label>
            <div class="col-sm-10">
                <textarea class="form-control" id="_remark" name="_remark" type="text" placeholder="备注"/>
            </div>
        </div>
    </fieldset>
</form>
