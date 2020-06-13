var localObj = window.location;

var contextPath = localObj.pathname.split("/")[1];

var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath + "/";

$(function () {
    $('#credit_payment_tender_supplier_list_toobar_id').form('clear');
    // 初始化表格
    var datagridDataOptions = {
        /*fit: true, //datagrid高度是否自适应
        nowrap: true, //是否只显示一行，即文本过多是否省略部分
        striped: true,
        fitColumns: true,
        border: false,
        pagination: true,// 分页工具栏
        pageSize: 10,
        pageList: [5, 10, 20, 30, 40, 50, 100, 500],
        pageNumber: 1,
        checkOnSelect: false,
        selectOnCheck: false,
        singleSelect: true,*/
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        fitColumns : true,
        pagination : true,
        striped:true,
        pageSize : 10,
        pageNumber : 1,
        pageList: [5, 10, 20, 30, 40, 50, 100, 500],
        toolbar: "#credit_payment_tender_supplier_list_toobar_id",
        idField: "id",
        showPageList: true,
        toolbar: [
            {
                text: '增加', iconCls: 'icon-add', handler: function () {
                    AddSupplier();
                }
            },
            {
                text: '修改', iconCls: 'icon-bookmark-edit', handler: function () {
                    SuppliersUpdate();
                }
            },
            {
                text: '删除', iconCls: 'icon-remove', handler: function () {
                    SuppliersDelete();
                }
            },
            {
                text: '刷新', iconCls: 'icon-reload', handler: function () {
                    findAction();
                }
            },
            {
                text: '导出数据', iconCls: 'icon-save', handler: function () {
                    exportExcelAll();
                }
            }, '-'],
        columns: [[
            {
                title: "ID",
                field: "id",
                width: 120,
                hidden: 'true'
            },
            {
                title: "供应商ID",
                field: "supplierId",
                width: 120,
                hidden: 'true'
            },
            {
                title: "供应商中文名",
                field: "chineseFullName",
                width: 110,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "创建时间",
                field: "createDate",
                width: 140,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "法人代表",
                field: "legalPerson",
                width: 70,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "供应商简称",
                field: "supplierAbbreviation",
                width: 80,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "助记码",
                field: "mnemonicCode",
                width: 70,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "供应商编码",
                field: "supplierCode",
                width: 160,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "企业属性",
                field: "enterpriseAttribute",
                width: 80,
                align: "right",
                formatter: function (value, row, index) {
                    /*  return "<a href='javascript:;' onclick='editRow(event)'>编辑</a>&nbsp;&nbsp;<a href='javascript:;' onclick='deleteRow(event)'>删除</a>";*/
                    if (value == 1) {
                        return "民企"
                    } else if (value == 2) {
                        return "国企";
                    } else {
                        return "其他";
                    }
                }
            }, {
                title: "供应商等级",
                field: "supplierLevel",
                width: 80,
                align: "right",
                sortable: true,
                order: "asc",
                formatter: function (value, row, index) {
                    /*  return "<a href='javascript:;' onclick='editRow(event)'>编辑</a>&nbsp;&nbsp;<a href='javascript:;' onclick='deleteRow(event)'>删除</a>";*/
                    if (value == 1) {
                        return "一级"
                    } else if (value == 2) {
                        return "二级";
                    } else if (value == 3) {
                        return "三级";
                    } else {
                        return "其他";
                    }
                }
            }, {
                title: "所属行业",
                field: "industryInvolved",
                width: 80,
                align: "right",
                sortable: true,
                order: "asc",
                formatter: function (value, row, index) {
                    /*  return "<a href='javascript:;' onclick='editRow(event)'>编辑</a>&nbsp;&nbsp;<a href='javascript:;' onclick='deleteRow(event)'>删除</a>";*/
                    if (value == 1) {
                        return "制造业"
                    } else if (value == 2) {
                        return "医疗";
                    } else {
                        return "其他";
                    }
                }
            }, {
                title: "主联系人",
                field: "mainContact",
                width: 40,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "电话",
                field: "supplierPhone",
                width: 100,
                align: "right",
            }, {
                title: "邮箱",
                field: "email",
                width: 100,
                align: "left"
            }, {
                title: "主页",
                field: "homePage",
                width: 100,
                align: "left"
            }, {
                title: "注册资金",
                field: "registeredCapital",
                width: 100,
                align: "left"
            }, {
                title: "供应商状态",
                field: "supplierStatus",
                width: 100,
                align: "left",
                sortable: true,
                order: "asc",
                formatter: function (value, row, index) {
                    /*  return "<a href='javascript:;' onclick='editRow(event)'>编辑</a>&nbsp;&nbsp;<a href='javascript:;' onclick='deleteRow(event)'>删除</a>";*/
                    if (value == 1) {
                        return "启用"
                    } else if (value == 2) {
                        return "停用";
                    }else {
                        return "其他";
                    }
                }
            },
        ]],
        url: "supplier.html?act=list",
        method: "GET",
        loadFilter: function (data) {
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows,
                "footer": data.state != 0 ? [] : data.data.footer
            }
        }
    };
    $("#supplier_table").datagrid(datagridDataOptions);
    $.parser.parse($('.needParse').parent());
    // 添加双击事件
    // $("#credit_payment_tender_Haier_table_id").datagrid({
    //     onDblClickRow: function (rowIndex, rowData) {
    //         var _src = "creditPaymentTender/creditPaymentTenderIframe_" + rowData.code;
    //         $('#detail_window_id').window('open');
    //         $('#detail_iframe_id').attr('src', _src);
    //         rowData = null;
    //         _src = null;
    //     }
    // });
    // 初始化分页器
    var pager = $("#supplier_table").datagrid("getPager");
    var pagerDataOptions = {
        buttons: [{
            iconCls: "fa fa-file-excel-o",
            handler: function () {
                exportExcel()
            }
        }, "-",
        ],
        layout: ["list", "sep", "first", "prev", "links", "next", "last",
            "sep", "refresh"
        ]
    };
    pager.pagination(pagerDataOptions);
})


function exportExcelAll() {
    location.href = "supplier.html?act=exportSupplierInformation";
}

//导出excel

function exportExcel() {
    $.messager.prompt("导出配置", '请输入需要导出的行数', function (r) {
        if (r != null && r != "") {
            var re = /^[0-9]*[1-9][0-9]*$/;
            if (re.test(r)) {
                location.href = "creditPaymentTenderHaier/exportExcel?" + "page=1&rows=" + r +
                    "&creditOrderCode=" + $("input[name$='creditOrderCode']").val() +
                    "&transactionId=" + $("input[name$='transactionId']").val() +
                    "&creditOrderContractCode=" + $("input[name$='creditOrderContractCode']").val() +
                    "&consumerName=" + $("input[name$='consumerName']").val() +
                    "&consumerTelephone=" + $("input[name$='consumerTelephone']").val() +
                    "&merchantShoppeName=" + $("input[name$='merchantShoppeName']").val() +
                    "&periods=" + $("#periods").combobox('getText') +
                    "&submitTime1=" + $("input[name$='submitTime1']").val() +
                    "&submitTime2=" + $("input[name$='submitTime2']").val() +
                    "&tradeTime1=" + $("input[name$='tradeTime1']").val() +
                    "&tradeTime2=" + $("input[name$='tradeTime2']").val() +
                    "&receviedTime1=" + $("input[name$='receviedTime1']").val() +
                    "&receviedTime2=" + $("input[name$='receviedTime2']").val() +
                    "&state=" + $("input[name$='state']").val() +
                    "&tags=" + $("input[name$='tags']").val();
            } else {
                $.messager.alert("提示", "请输入正整数。", "error");
            }
        }
    });
}


/*重新加载表格*/

function loadDatagrid() {
    console.log($("input[name$='Find_chineseFullName']").val())
    $("#find__supplier_list_form_id").datagrid("load", {

        customerCode: $("input[name$='find_customerCode']").val(),//供应商编号
        customerAbbreviation: $("input[name$='find_customerAbbreviation']").val(),//供应商简称
        creditOrderContractCode: $("input[name$='find_creditOrderContractCode']").val(),//合同号
        phone: $("input[name$='find_phone']").val(),
        mainContact: $("input[name$='find_mainContact']").val(),
        chineseFullName: $("input[name$='chineseFullName']").val(),
        email: $("input[name$='find_email']").val(),
        legalPerson: $("input[name$='find_customertatus']").val(),//企业状态
        enterpriseAttribute: $("input[name$='find_customerLevel']").val(),//企业等级
    });
}

//查询

function findAction() {
    $("#supplier_table").datagrid({
        queryParams: {
            supplierCode: $("input[name$='find_supplierCode']").val(),//供应商编号
            supplierAbbreviation: $("input[name$='find_supplierAbbreviation']").val(),//供应商简称
            mnemonicCode: $("input[name$='find_mnemonicCode']").val(),//助记码
            supplierPhone: $("input[name$='find_phone']").val(),
            mainContact: $("input[name$='find_mainContact']").val(),
            chineseFullName: $("input[name$='chineseFullName']").val(),
            email: $("input[name$='find_email']").val(),
            supplierStatus: $("input[name$='find_supplierStatus']").val(),//企业状态
            supplierLevel: $("input[name$='find_supplierLevel']").val(),//企业等级
        },
        loadFilter: function (data) {
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows,
                "footer": data.state != 0 ? [] : data.data.footer
            }
        }

    });
    var pager = $("#supplier_table").datagrid("getPager");
    var pagerDataOptions = {
        buttons: [{
            iconCls: "fa fa-file-excel-o",
            handler: function () {
                exportExcel()
            }
        }, "-",
        ],
        layout: ["list", "sep", "first", "prev", "links", "next", "last",
            "sep", "refresh"
        ]
    };
    pager.pagination(pagerDataOptions);
}

/*
 * 重新获取资方单号
*/
function getTransactionId() {
    $.get("creditPaymentTenderHaier/getTransactionId", function (data) {
        $.messager.alert('提示', data.message, 'info');
    });
}

function enter(event) {
    if (event.which == "13") {
        findAction();
    }
}


/* * 清空查询条件*/

function doClearAction() {

    $('#credit_payment_tender_supplier_list_toobar_id').form('clear');
    findAction();

}

function doClearAction_addsupplier() {
    $('#supplier-dialog_from').form('clear');
}

function downloadContract() {
    $.messager.prompt("导出配置", '请输入需要导出的行数', function (r) {
        if (r != null && r != "") {
            var re = /^[0-9]*[1-9][0-9]*$/;
            location.href = "" + "page=1&rows=" + r +
                "&creditOrderCode=" + $("input[name$='creditOrderCode']").val() +
                "&creditOrderContractCode=" + $("input[name$='creditOrderContractCode']").val() +
                "&consumerName=" + $("input[name$='consumerName']").val() +
                "&consumerTelephone=" + $("input[name$='consumerTelephone']").val() +
                "&merchantShoppeName=" + $("input[name$='merchantShoppeName']").val() +
                "&periods=" + $("#periods").combobox('getText') +
                "&submitTime1=" + $("input[name$='submitTime1']").val() +
                "&submitTime2=" + $("input[name$='submitTime2']").val() +
                "&tradeTime1=" + $("input[name$='tradeTime1']").val() +
                "&tradeTime2=" + $("input[name$='tradeTime2']").val() +
                "&state=" + $("input[name$='state']").val() +
                "&tags=" + $("input[name$='tags']").val();
        } else {
            $.messager.alert("提示", "请输入正整数。", "error");
        }
    });
}


function ExportHaierByTransactionTime() {
    var startTime = $("input[name$='transaction_start_time']").val();
    var endTime = $("input[name$='transaction_end_time']").val();
    var a = (Date.parse(endTime) - Date.parse(startTime)) / 3600 / 1000;
    if (startTime.length > 0 && endTime.length) {
        if (a <= 0) {
            $.messager.show({
                title: '温馨提示',
                msg: '时间填写错误',
                timeout: 4000,
                showType: 'null',
                style: {}
            });
        } else {
            location.href = 'creditPaymentTenderHaier/exportHaierByTransactionTime?startTime='
                + $("input[name$='transaction_start_time']").val()
                + '&endTime='
                + $("input[name$='transaction_end_time']").val();
            closeByTransactionTime()
        }
    } else {
        $.messager.show({
            title: '温馨提示',
            msg: '时间输入不能为空',
            timeout: 4000,
            showType: null,
            style: {}
        })
    }
}

function AddSupplier() {
    doClearAction_addsupplier();
    $("#supplier-dialog").dialog("open")
}

/*
function validateUsername() {
    var username = $("#username").val();
    if (isNull(username)) {
        $("#tishiuser").html("用户名不能为空");
        $("#adduser_buttonsuser_log").show()
        $("#tishiuser").css("color", "green");
        $("#adduser_buttons_submit").hide();
        return false;
    } else {
        $("adduser_buttonsuser_log").hide();
        return true;
    }
}

function validate() {
    var determinePassword = $("#determinePassword1").val();
    var password = $("#password").val();
    if (password == determinePassword && !(isNull(determinePassword) || isNull(password))) {
        $("#tishis").html("两次密码相同");
        $("#adduser_buttons_log").hide();
        $("#tishis").css("color", "green");
        $("#adduser_buttons_submit").show();
        return true;
    } else if (isNull(determinePassword) || isNull(password)) {
        $("#tishis").html("密码不能为空");
        $("#adduser_buttons_log").show();
        $("#tishis").css("color", "red")
        $("#adduser_buttons_submit").hide();
        return false;
    } else {
        $("#tishis").html("两次密码不相同");
        $("#adduser_buttons_log").show();
        $("#tishis").css("color", "red")
        $("#adduser_buttons_submit").hide();
        return false;
    }
}
*/

// 格式化限制数字文本框输入，只能数字或者两位小数
function format_input_num(obj) {
    // 清除"数字"和"."以外的字符
    obj.value = obj.value.replace(/[^\d.]/g, "");
    // 验证第一个字符是数字
    obj.value = obj.value.replace(/^\./g, "");
    // 只保留第一个, 清除多余的
    obj.value = obj.value.replace(/\.{2,}/g, ".");
    obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
    // 只能输入两个小数
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');
}

function postComment() {

    //验证url网址
    if ($("input[name='url']").val()) {
        var str = $("input[name='url']").val();
        //判断URL地址的正则表达式为:http(s)?://([\w-]+\.)+[\w-]+(/[\w- ./?%&=]*)?
        //下面的代码中应用了转义字符"\"输出一个字符"/"
        var Expression = /http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
        var objExp = new RegExp(Expression);

        if (objExp.test(str) != true) {

            alert("网址格式不正确！请重新输入");
            return false;
        } else {
            alert("网址正确！");
        }

    }

}

function SuppliersDelete() {
    var row = $("#supplier_table").datagrid("getSelected");//选中一行数据
    if (row == null || row.length == 0) {
        return;
    }
    $.ajax({
        url: "role.html?act=Role_judge",
        success: function (data) {
            console.log(data.data.role.roleName)
            if (data.data.role.roleName == "admin") {
                submitSuppliersDelete(row.id, row.supplierId)
            } else {
                $.messager.show({
                    title:'温馨提示',
                    msg:'权限不足',
                    timeout:5000,
                    showType:'slide'
                });
            }

        },
        error: function () {
            alert(" SuppliersDelete()存在错误")
        }
    })

}

function submitSuppliersDelete(id, supplierId) {
    if (del()) {
        $.ajax({
            url: "supplier.html?act=SuppliersDelete",
            data: {
                id: id,
                supplierId: supplierId,
            },
            success: function (data) {
                findAction();
                $.messager.show({
                    title:'温馨提示',
                    msg:data.message,
                    timeout:5000,
                    showType:'slide'
                });
            },
            error: function () {
                alert("submitSuppliersDelete存在错误")
            }
        })
    } else {
        return;
    }

}

function SuppliersUpdate() {
    var row = $("#supplier_table").datagrid("getSelected");//选中一行数据
    if (row == null || row.length == 0) {
        return;
    }
    $.ajax({
        url: "supplier.html?act=SingleInformation",
        data: {
            id: row.id,
            supplierId: row.supplierId,
        },
        success: function (data) {
            $('#SuppliersInformation-updata-from').form('clear');
            console.log(data.data.registrationDate + "  SuppliersUpdate()  " + data.data.applicationDate)
            /*$('#_applicationDate').val(data.data.applicationDate);*/
            $("#supplier-updata-dialog").dialog({
                onLoad: function () {
                    $("#SuppliersInformation-updata-from").form("load", {
                        _id: data.data.id,
                        _supplierId: data.data.supplierId,
                        _chineseFullName: data.data.chineseFullName,
                        _mnemonicCode: data.data.mnemonicCode,
                        _supplierAbbreviation: data.data.supplierAbbreviation,
                        _organizationCode: data.data.organizationCode,
                        _provinceCode: data.data.provinceCode,
                        _cityCode: data.data.cityCode,
                        _countyCode: data.data.countyCode,
                        _address: data.data.address,

                        _supplierPhone: data.data.address,
                        _supplierNameEnglish: data.data.supplierNameEnglish,
                        _supplierProperty: data.data.supplierProperty,
                        _mainContact: data.data.mainContact,

                        _postalCode: data.data.postalCode,
                        _email: data.data.email,
                        _homePage: data.data.homePage,
                        _registeredCapital: data.data.registeredCapital,
                        _roadTransportPermit: data.data.roadTransportPermit,
                        _currency: data.data.currency,
                        _businessLicenseNo: data.data.businessLicenseNo,

                        _taxRegistrationNo: data.data.taxRegistrationNo,
                        _mainBusiness: data.data.mainBusiness,
                        _cooperationIntention: data.data.cooperationIntention,
                        _approvalAuthority: data.data.approvalAuthority,

                        _contactsTwo: data.data.contactsTwo,
                        _phoneTwo: data.data.phoneTwo,
                        _enterpriseAttribute: data.data.enterpriseAttribute,
                        _supplierLevel: data.data.supplierLevel,
                        _approvalNo: data.data.approvalNo,
                        _industryInvolved: data.data.industryInvolved,
                        _supplierStatus: data.data.supplierStatus,
                        _remark: data.data.remark,
                        _legalPerson: data.data.legalPerson,

                    })
                }
            }).dialog("open")
        },
        error: function () {
            alert("存在错误")
        }
    })

}

function submitSupplierUpdata() {

    registrationDate = $("#_registrationDate").val()
    console.log("submitSupplierUpdata  " + registrationDate + "   $(\"#_id\").val()  " + $("#_id").val() + "$(\"#_supplierId\").val()  " + $("#_supplierId").val())
    var suppliersInformation = {};
    suppliersInformation.id = $("#_id").val();
    suppliersInformation.supplierId = $("#_supplierId").val();
    suppliersInformation.chineseFullName = $("#_chineseFullName").val();
    suppliersInformation.mnemonicCode = $("#_mnemonicCode").val();
    suppliersInformation.supplierAbbreviation = $("#_supplierAbbreviation").val();
    suppliersInformation.organizationCode = $("#_organizationCode").val();

    suppliersInformation.provinceCode = $("#_provinceCode").val();
    suppliersInformation.cityCode = $("#_cityCode").val();
    suppliersInformation.countyCode = $("#_countyCode").val();
    suppliersInformation.address = $("#_address").val();

    suppliersInformation.supplierPhone = $("#_supplierPhone").val();
    suppliersInformation.supplierNameEnglish = $("#_supplierNameEnglish").val();
    suppliersInformation.supplierProperty = $("#_supplierProperty").val();
    suppliersInformation.mainContact = $("#_mainContact").val();

    suppliersInformation.postalCode = $("#_postalCode").val();
    suppliersInformation.email = $("#_email").val();
    suppliersInformation.homePage = $("#_homePage").val();
    suppliersInformation.registeredCapital = $("#_registeredCapital").val();

    suppliersInformation.applicationDate = $("#_applicationDate").val();
    suppliersInformation.roadTransportPermit = $("#_roadTransportPermit").val();
    suppliersInformation.currency = $("#_currency").val();
    suppliersInformation.businessLicenseNo = $("#_businessLicenseNo").val();

    suppliersInformation.taxRegistrationNo = $("#_taxRegistrationNo").val();
    /!*税务登记*!/
    suppliersInformation.mainBusiness = $("#_mainBusiness").val();
    suppliersInformation.cooperationIntention = $("#_cooperationIntention").val();
    suppliersInformation.approvalAuthority = $("#_approvalAuthority").val();

    suppliersInformation.contactsTwo = $("#_contactsTwo").val();
    suppliersInformation.phoneTwo = $("#_phoneTwo").val();
    suppliersInformation.enterpriseAttribute = $("#_enterpriseAttribute").val();
    suppliersInformation.supplierLevel = $("#_supplierLevel").val();
    suppliersInformation.approvalNo = $("#_approvalNo").val();
    suppliersInformation.industryInvolved = $("#_industryInvolved").val();
    suppliersInformation.supplierStatus = $("#_supplierStatus").val();
    suppliersInformation.remark = $("#_remark").val();
    suppliersInformation.registrationDate = $("#_registrationDate").val()
    suppliersInformation.legalPerson = $("#_legalPerson").val()

    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "supplier.html?act=UpSupplierInformation",
        data: JSON.stringify(suppliersInformation),
        success: function (data) {
            //$("#role_table").dataTable().fnClearTable();
            if (!data.state) {
                findAction();
                $.messager.show({
                    title:'温馨提示',
                    msg:data.message,
                    timeout:5000,
                    showType:'slide'
                });

                $("#supplier-updata-dialog").dialog("close")
            } else {
                alert(data.message);
            }
        },
        error: function () {
            alert("异常！");
        }
    })
}

function submitSupplier() {
    applicationDate = $("#applicationDate").val();
    registrationDate = $("#registrationDate").val();
    chineseFullName = $("#chineseFullName").val();
    console.log(applicationDate + "hahah  " + registrationDate + "  " + chineseFullName)
    var suppliersInformation = {};
    suppliersInformation.chineseFullName = $("#chineseFullName").val();
    suppliersInformation.mnemonicCode = $("#mnemonicCode").val();
    suppliersInformation.supplierAbbreviation = $("#supplierAbbreviation").val();
    suppliersInformation.organizationCode = $("#organizationCode").val();

    suppliersInformation.provinceCode = $("#provinceCode").val();
    suppliersInformation.cityCode = $("#cityCode").val();
    suppliersInformation.countyCode = $("#countyCode").val();
    suppliersInformation.address = $("#address").val();

    suppliersInformation.supplierPhone = $("#supplierPhone").val();
    suppliersInformation.supplierNameEnglish = $("#supplierNameEnglish").val();
    suppliersInformation.supplierProperty = $("#supplierProperty").val();
    suppliersInformation.mainContact = $("#mainContact").val();

    suppliersInformation.postalCode = $("#postalCode").val();
    suppliersInformation.email = $("#email").val();
    suppliersInformation.homePage = $("#homePage").val();
    suppliersInformation.registeredCapital = $("#registeredCapital").val();

    suppliersInformation.applicationDate = $("#applicationDate").val();
    suppliersInformation.roadTransportPermit = $("#roadTransportPermit").val();
    suppliersInformation.currency = $("#currency").val();
    suppliersInformation.businessLicenseNo = $("#businessLicenseNo").val();

    suppliersInformation.taxRegistrationNo = $("#taxRegistrationNo").val();
    /!*税务登记*!/
    suppliersInformation.mainBusiness = $("#mainBusiness").val();
    suppliersInformation.cooperationIntention = $("#cooperationIntention").val();
    suppliersInformation.approvalAuthority = $("#approvalAuthority").val();

    suppliersInformation.taxRegistrationNo = $("#contactsTwo").val();
    suppliersInformation.phoneTwo = $("#phoneTwo").val();
    suppliersInformation.enterpriseAttribute = $("#enterpriseAttribute").val();
    suppliersInformation.supplierLevel = $("#supplierLevel").val();
    suppliersInformation.approvalNo = $("#approvalNo").val();
    suppliersInformation.industryInvolved = $("#industryInvolved").val();
    suppliersInformation.supplierStatus = $("#supplierStatus").val();
    suppliersInformation.remark = $("#remark").val();
    suppliersInformation.registrationDate = $("#registrationDate").val();
    suppliersInformation.legalPerson = $("#legalPerson").val()

    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "supplier.html?act=addSupplier",
        data: JSON.stringify(suppliersInformation),
        success: function (data) {
            //$("#role_table").dataTable().fnClearTable();
            if (!data.state) {
                findAction();
                $.messager.show({
                    title:'温馨提示',
                    msg:data.message,
                    timeout:5000,
                    showType:'slide'
                });
                $("#supplier-dialog").dialog("close")
            } else {
                $.messager.show({
                    title:'温馨提示',
                    msg:data.message,
                    timeout:5000,
                    showType:'slide'
                });
            }
        },
        error: function () {
            $.messager.show({
                title:'温馨提示',
                msg:'异常',
                timeout:5000,
                showType:'slide'
            });
        }
    })
}

function del() {
    var msg = "您真的确定要删除吗？\n\n请确认！";
    if (confirm(msg) == true) {
        return true;
    } else {
        return false;
    }
}