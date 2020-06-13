<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="<%=basePath%>static/js/InformationManagement/SuppliersInformation_list.js"></script>

<div id="credit_payment_tender_supplier_list_toobar_id" style="padding: 10px; display: inherit; height:9%;overflow-x:auto;" onkeypress="enter(event) ">

    <form id="find__supplier_list_form_id" action="" style="float: none; display: inline-block;">
        <input name="find_supplierCode" class="easyui-textbox" data-options="prompt:'供应商编号'" style="width: 130px" onkeypress="enter(event)">
        <input name="find_supplierAbbreviation" class="easyui-textbox" data-options="prompt:'供应商简称'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="find_mnemonicCode" class="easyui-textbox" data-options="prompt:'助记码'" style="width: 130px"
               onkeypress="enter(event)">
        <input name="find_phone" class="easyui-textbox" data-options="prompt:'电话'" style="width: 130px" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')" onkeypress="enter(event)">
        <input name="find_mainContact" class="easyui-textbox" data-options="prompt:'主要联系人'" style="width: 130px" onkeypress="enter(event)">
        <input name="find_chineseFullName" class="easyui-textbox" data-options="prompt:'供应商中文全称'" style="width: 130px" onkeypress="enter(event)">
        <input name="find_email" class="easyui-textbox" data-options="prompt:'供应商邮箱'" style="width: 130px" onkeypress="enter(event)">
        <select id="find_supplierStatus" class="easyui-combobox" name="find_supplierStatus" data-options="prompt:'企业状态',validType:'length[0,20]',editable:false"
                style="width: 90px">
            <option value="1">启用</option>
            <option value="2">停用</option>
        </select>
        <select class="easyui-combobox" name="find_supplierLevel" data-options="prompt:'供应商等级',validType:'length[0,20]',editable:false"
                style="width: 90px">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>

        </select>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-search',plain:true" onclick="findAction()">搜索</a>
        <a class="easyui-linkbutton" data-options="iconCls:'fa micons-empty',plain:true"  onclick="doClearAction()">清空</a>
    </form>
</div>
<div style="width:100%;height:91%;">
    <div id="sdsd" class="easyui-panel" data-options="fit:true,border:false">
        <table id="supplier_table"></table>
    </div>
</div>
<div id="supplier-dialog" class="easyui-dialog" title="添加供应商" data-options="modal:true,iconCls:'icon-add',closed:true,footer:'#addsupplier-buttons'"
     style="width:90%;height:80%;">
    <form class="form-inline " role="form" id="supplier-dialog_from">
        <fieldset>
            <legend></legend>
            <legend>供应商信息编辑</legend>
            <div class="form-group">
                <label class="col-sm-1 control-label" for="chineseFullName">供应商中文全称</label>
                <div class="col-sm-3">
                    <input class="form-control" name="chineseFullName" id="chineseFullName" type="text" placeholder="供应商中文全称" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')"/>
                </div>
                <label class="col-sm-1 control-label" for="mnemonicCode">助记码</label>
                <div class="col-sm-3">
                    <input class="form-control" name="mnemonicCode" id="mnemonicCode" type="text" placeholder="英文大写'腾讯'：TX"  onkeyup="this.value=this.value.replace(/[^A-Z]/g,'')"/>
                </div>
                <label class="col-sm-1 control-label" for="supplierAbbreviation">供应商简称</label>
                <div class="col-sm-3">
                    <input class="form-control" name="supplierAbbreviation" id="supplierAbbreviation" />
                </div>
                <%--</div>
                <div >--%>
                <label class="col-sm-1 control-label" for="organizationCode">组织机构代码</label>
                <div class="col-sm-3">
                    <input class="form-control" id="organizationCode" onkeyup="this.value=this.value.replace(/[^A-Z0-9\\-]/g,'');"
                           name="organizationCode" />
                </div>
                <label class="col-sm-1 control-label" for="provinceCode">省份编码</label>
                <div class="col-sm-3">
                    <input class="form-control" id="provinceCode" name="provinceCode" type="text" onkeyup="this.value=this.value.replace(/[^A-Z0-9\\-]/g,'');" />
                </div>
                <label class="col-sm-1 control-label" for="cityCode">城市编码</label>
                <div class="col-sm-3">
                    <input class="form-control" id="cityCode" name="cityCode" type="text" placeholder="123456" />
                </div>
                <label class="col-sm-1 control-label" for="countyCode">国家代码:</label>
                <div class="col-sm-3">
                    <input class="form-control" name="countyCode" id="countyCode" type="text" onkeyup="this.value=this.value.replace(/[^A-Z0-9\\-]/g,'');"
                           placeholder="单位/万元" />
                </div>
                <label class="col-sm-1 control-label" for="address">地址:</label>
                <div class="col-sm-3">
                    <input class="form-control" type="text" id="address" name="address" />
                </div>
                <label class="col-sm-1 control-label" for="supplierPhone">供应商电话:</label>
                <div class="col-sm-3">
                    <input class="form-control" name="supplierPhone" id="supplierPhone" type="text" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')"
                           placeholder="供应商电话号码" />
                </div>
                <label class="col-sm-1 control-label" for="supplierNameEnglish">供应商英文名</label>
                <div class="col-sm-3">
                    <input class="form-control" name="supplierNameEnglish" id="supplierNameEnglish" onkeyup="this.value=this.value.replace(/[^a-zA-Z]/g,'')"/>
                </div>
                <label class="col-sm-1 control-label" for="supplierProperty">客户资产</label>
                <div class="col-sm-3">
                    <input class="form-control" name="supplierProperty" id="supplierProperty" type="text" onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="单位/万元" />
                </div>

                <label class="col-sm-1 control-label" for="mainContact">供应商联系人名</label>
                <div class="col-sm-3">
                    <input class="form-control" id="mainContact" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label" for="postalCode">邮政编码</label>
                <div class="col-sm-3">
                    <input class="form-control" id="postalCode" name="postalCode" type="text" onkeyup="this.value=this.value.replace(/[^0-9\\-]/g,'');" />
                </div>
                <label class="col-sm-1 control-label" for="email">Email</label>
                <div class="col-sm-3">
                    <input class="form-control" type="email" id="email" name="email" />
                </div>
                <label class="col-sm-1 control-label" for="homePage">主页</label>
                <div class="col-sm-3">
                    <input class="form-control" id="homePage" name="homePage" type="text" placeholder="网址必须以http://或者https://开头，且必须是个网址^_^！" />
                </div>
                <label class="col-sm-1 control-label" for="registeredCapital">注册资本</label>
                <div class="col-sm-3">
                    <input class="form-control" id="registeredCapital" name="registeredCapital" type="text" onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,3})?).*$/g, '$1')"
                           placeholder="注册资本" />
                </div>

                <label class="col-sm-1 control-label" for="applicationDate">申请时间</label>
                <!--指定 date标记-->
                <div class="col-sm-3 control-label">
                    <input id='applicationDate' name="applicationDate" class="easyui-datetimebox" data-options="formatter:ww3,parser:w3" style="width: 183px">
                </div>

                <label class="col-sm-1 control-label" for="roadTransportPermit">道路运输许可证</label>
                <div class="col-sm-3">
                    <input class="form-control" id="roadTransportPermit" name="roadTransportPermit" type="text" placeholder="道路运输许可证号" />
                </div>
                <label class="col-sm-1 control-label" for="approvalNo">注册编号</label>
                <div class="col-sm-3">
                    <input class="form-control" id="approvalNo" name="approvalNo" type="text" placeholder="企业注册编号" />
                </div>
                <label class="col-sm-1 control-label" for="legalPerson">法人代表</label>
                <div class="col-sm-3">
                    <input class="form-control" id="legalPerson" name="legalPerson" type="text" placeholder="法人代表" />
                </div>
            </div>
            <div>

                <label class="col-sm-1 control-label" for="currency">货币</label>
                <div class="col-sm-3">
                    <input class="form-control" id="currency" name="currency" type="text" />
                </div>
                <label class="col-sm-1 control-label" for="businessLicenseNo">营业执照编号</label>
                <div class="col-sm-3">
                    <input class="form-control" type="text" id="businessLicenseNo" name="businessLicenseNo" />
                </div>
                <label class="col-sm-1 control-label" for="taxRegistrationNo">税务登记</label>
                <div class="col-sm-3">
                    <input class="form-control" id="taxRegistrationNo" name="taxRegistrationNo" type="text" />
                </div>

            </div>
            <div>

                <label class="col-sm-1 control-label" for="mainBusiness">主营业务</label>
                <div class="col-sm-3">
                    <input class="form-control" id="mainBusiness" name="mainBusiness" type="text" />
                </div>
                <label class="col-sm-1 control-label" for="cooperationIntention">营业执照编号</label>
                <div class="col-sm-3">
                    <input class="form-control" type="text" id="cooperationIntention" name="cooperationIntention"  οnkeyup="this.value=this.value.replace(/\D/g,'')"/>
                </div>
                <label class="col-sm-1 control-label" for="approvalAuthority">批准机关</label>
                <div class="col-sm-3">
                    <input class="form-control" id="approvalAuthority" name="approvalAuthority" type="text" />
                </div>
                <label class="col-sm-1 control-label" for="contactsTwo">联系人2</label>
                <div class="col-sm-3">
                    <input class="form-control" id="contactsTwo" name="contactsTwo" type="text" placeholder="副联系人" />
                </div>
                <label class="col-sm-1 control-label" for="phoneTwo">副联系人电话</label>
                <div class="col-sm-3">
                    <input class="form-control" id="phoneTwo" name="phoneTwo" type="text" placeholder="副联系人电话" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')"  />
                </div>
                <label class="col-sm-1 control-label" for="registrationDate">注册时间</label>
                <!--指定 date标记-->
                <div class="col-sm-3 control-label">
                    <input id='registrationDate' name="registrationDate" class="easyui-datetimebox" data-options="formatter:ww3,parser:w3" style="width: 183px">
                </div>
            </div>
        </fieldset>
        <fieldset>
            <div>
                <label for="enterpriseAttribute" class="col-sm-1 control-label">企业属性</label>
                <div class="col-sm-3"><select id="enterpriseAttribute" class="form-control" style="width: 183px">
                    <option value="1">民企</option>
                    <option value="2">国企</option>
                </select>
                </div>
                <label for="supplierLevel" class="col-sm-1 control-label">客户级别</label>
                <div class="col-sm-3">
                    <select id="supplierLevel" class="form-control" style="width: 183px">
                        <option value="1">1级</option>
                        <option value="2">2级</option>
                        <option value="3">3级</option>
                    </select>
                </div>
                <label for="industryInvolved" class="col-sm-1 control-label">所属行业</label>
                <div class="col-sm-3">
                    <select id="industryInvolved" class="form-control" style="width: 183px">
                        <option value="1">制造业</option>
                        <option value="2">医疗</option>
                    </select>
                </div>
                <label for="supplierStatus" class="col-sm-1 control-label">供应商状态</label>
                <div class="col-sm-3">
                    <select class="form-control" id="supplierStatus" title="大魔王" style="width: 183px">
                        <option value="1">启用</option>
                        <option value="2">停用</option>
                    </select>
                </div>

            </div>

        </fieldset>
        <fieldset>
            <div form-group>
                <label for="remark" class="col-sm-1 control-label">备注</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="remark" name="remark" type="text" placeholder="备注" />
                </div>
            </div>
        </fieldset>
    </form>
</div>

<div id="supplier-updata-dialog"
     class="easyui-dialog"
     title="修改商户信息"
     data-options="modal:true,iconCls:'icon-add',closed:true,href:'<%=request.getContextPath()%>/supplier.html?act=go_edit',footer:'#supplier-updata-buttons'"
     style="width:90%;height:80%;">
</div>

<div id="supplier-updata-buttons" style="padding: 5px 15px 5px 0px;text-align: right">
    <a id="supplier-updata_submit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="submitSupplierUpdata()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick="$('#supplier-updata-dialog').window('close')">取消</a>
</div>
<div id="addsupplier-buttons" style="padding: 5px 15px 5px 0px;text-align: right">

    <a id="adduser_buttons_submit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="submitSupplier()">提交</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick="doClearAction_addsupplier()">清空</a>
    <a class="easyui-linkbutton" data-options=" iconCls:'icon-no'"
       onclick="$('#supplier-dialog').window('close')">取消</a>
</div>
<script type="text/javascript">
    function ww4(date){
        var y = date.getFullYear();
        var m = date.getMonth()+1;
        var d = date.getDate();
        var h = date.getHours();
        return  y+'年'+(m<10?('0'+m):m)+'月'+(d<10?('0'+d):d)+'日'+(h<10?('0'+h):h)+'点';

    }
    function w4(s){
        var reg=/[\u4e00-\u9fa5]/  //利用正则表达式分隔
        var ss = (s.split(reg));
        var y = parseInt(ss[0],10);
        var m = parseInt(ss[1],10);
        var d = parseInt(ss[2],10);
        var h = parseInt(ss[3],10);
        if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h)){
            return new Date(y,m-1,d,h);
        } else {
            return new Date();
        }
    }

    function ww3(date) {
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();
        var h = date.getHours();
        var min = date.getMinutes();
        var s = date.getSeconds();
        var str = y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d) + ' ' + (h < 10 ? ('0' + h) : h)
            + ':' + (min < 10 ? ('0' + min) : min) + ':' + (s < 10 ? ('0' + s) : s);
        return str;
    }

    function w3(s) {
        if (!s) return new Date();
        var y = s.substring(0, 4);
        var m = s.substring(5, 7);
        var d = s.substring(8, 10);
        var h = s.substring(11, 14);
        var min = s.substring(15, 17);
        var sec = s.substring(18, 20);
        if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min) && !isNaN(sec)) {
            return new Date(y, m - 1, d, h, min, sec);
        } else {
            return new Date();
        }
    }
</script>