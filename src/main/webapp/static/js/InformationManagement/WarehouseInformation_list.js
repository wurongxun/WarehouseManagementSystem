var localObj = window.location;

var contextPath = localObj.pathname.split("/")[1];

var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
$(function () {
    // 初始化表格
    var datagridDataOptions = {
        fit: true, //datagrid高度是否自适应
        nowrap: true, //是否只显示一行，即文本过多是否省略部分
        striped: true,
        border: false,
        pagination: true,
        fitColumns: true,
        pageSize: 10,
        pageList: [5, 10, 20, 30, 40, 50, 100, 500],
        pageNumber: 1,
        checkOnSelect: false,
        selectOnCheck: false,
        singleSelect: true,
        toolbar: "#WarehouseInformation_list_toobar_id",
        idField: "id",
        showPageList: true,
        toolbar: [
            {
                text: '增加', iconCls: 'icon-add', handler: function () {
                    AddWarehouseInformation();
                }
            },
            {
                text: '修改', iconCls: 'icon-bookmark-edit', handler: function () {
                    UpWarehouseInformation();
                }
            },
            {
                text: '删除', iconCls: 'icon-remove', handler: function () {
                   DeleteWarehouseInformation();
                }
            },
            {
                text: '刷新', iconCls: 'icon-reload', handler: function () {
                    findAction();
                }
            },
            {
                text: '导出', iconCls: 'icon-save', handler: function () {
                    location.href = "warehouseInformation.html?act=warehouseInformationExcel";
                }
            }, '-'],
        columns: [[
            {
                title: "ID",
                field: "id",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
                hidden: 'true'
            },
            {
                title: "仓库名称",
                field: "warehouseName",
                width: 90,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "创建时间",
                field: "createDate",
                width: 70,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "仓库号",
                field: "warehouseNumber",
                width: 70,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "仓库编码",
                field: "warehouseCode",
                width: 120,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "负责人",
                field: "warehousePersonInChargeName",
                width: 80,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "负责人电话",
                field: "warehousePersonInChargePhone",
                width: 80,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "仓库架位数量",
                field: "warehouseShelfPositionNumber",
                width: 160,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "仓库类型",
                field: "warehouseType",
                width: 110,
                align: "right",
                sortable: true,
                order: "asc",
                formatter: function (value, row, index) {
                    if (value == 1) {
                        return "冷冻库";
                    } else {
                        return "普通库";
                    }
                }
            }, {
                title: "存放商品大类",
                field: "commodityCategory",
                width: 120,
                align: "right",
            }, {
                title: "启用日期",
                field: "openingDate",
                width: 80,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "状态",
                field: "status",
                width: 80,
                align: "right",
                sortable: true,
                order: "asc",
                formatter: function (value, row, index) {
                    if (value == 1) {
                        return "启用";
                    } else {
                        return "停用";
                    }
                }
            },
        ]],
        url: "warehouseInformation.html?act=list",
        method: "GET",
        loadFilter: function (data) {
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows,
                "footer": data.state != 0 ? [] : data.data.footer
            }
        }
    };
    $("#WarehouseInformation_table").datagrid(datagridDataOptions);
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
    var pager = $("#WarehouseInformation_table").datagrid("getPager");
    var pagerDataOptions = {
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
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

/*重新加载表格*/

function loadDatagrid() {
    $("#WarehouseInformation_table").datagrid("load", {
        warehouseName: $("input[name$='warehouseName']").val(),
        warehousePersonInChargeName: $("input[name$='warehousePersonInChargeName']").val(),
        warehouseNumber: $("input[name$='warehouseNumber']").val(),
        warehousePersonInChargePhone: $("input[name$='warehousePersonInChargePhone']").val(),
        warehouseType: $("input[name$='warehouseType']").val(),
        /*commodityCategory: $("input[name$='Find-commodityCategory']").val(),*/
        status: $("input[name$='status']").val()
    });
}

//查询

function findAction() {
    loadDatagrid();
}

function enter(event) {
    if (event.which == "13") {
        findAction();
    }
}


/* * 清空查询条件*/

function doClearAction() {
    $('#find_WarehouseInformation_form_id').form('clear');
    loadDatagrid();
}

function downloadContract() {
    $.messager.prompt("导出配置", '请输入需要导出的行数', function (r) {
        if (r != null && r != "") {
            var re = /^[0-9]*[1-9][0-9]*$/;
            location.href = "creditPaymentTenderHaier/downloadContract?" + "page=1&rows=" + r +
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

function openAccountStatement() {
    $('#exportAccountStatementDetail').window('open');
}

function closeAccountStatement() {
    $('#exportAccountStatementDetail').window('close');
}

function doExportAccountStatementDetail() {
    location.href = 'creditPaymentTenderHaier/exportAccountStatementDetail?startTime='
        + $("input[name$='excel_start_time']").val()
        + '&endTime='
        + $("input[name$='excel_end_time']").val();
}

function doPTTagAction(parm) {
    var code = $(parm).attr('data-code');
    var _src = "creditPaymentTenderHaier/getTagIframe_" + code;
    $('#payment_tender_tag_window_id').window('open');
    $('#payment_tender_tag_iframe_id').attr('src', _src);
    _src = null;
}


function getJinChengSFTPFile() {
    window.parent.openPage('centerTabsId', '文件', 'fa fa-google-wallet', 'creditPaymentTenderHaier/file.html');
}

function openByTransactionTime() {
    $("#exportByTransactionTime").window("open");
}

function closeByTransactionTime() {
    $("#exportByTransactionTime").window("close");
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

function AddWarehouseInformation() {
    $('#AddWarehouseInformation-dialog-from-id').form('clear');
    getUserInformationIdData()
    getCommodityCategoryData()
    $("#AddWarehouseInformation-dialog").dialog("open")
}

/*加载工作人员信息*/
function getUserInformationIdData() {
    $.ajax({
        type: "GET",
        url: "userInformation.html?act=user_list",
        data: {},
        dataType: "json",
        success: function (json) {
            $("#warehousePersonInChargeId").find("option").remove();
            var html = '<option value="">请选择</option>';
            $.each(json, function (i, value) {

                console.log(i + ' ' + value.id);
                html += '<option value="' + value.id + '">' + value.userName + '</option>';
            });
            console.log(html)
            $('#warehousePersonInChargeId').append(html);
        },
        error: function () {
            alert("JS  function getUserInformationIdData 存在错误")
        }
    });

}

/*加载供应商信息 商品大类 commodityCategory*/
function getCommodityCategoryData() {
    $.ajax({
        type: "GET",
        url: "commodityInformation.html?act=Find_queryAllSupplierLargeClass",
        data: {},
        dataType: "json",
        success: function (json) {
            $("#commodityCategory").find("option").remove();
            var html = '<option value="">请选择</option>';
            $.each(json.data, function (i, value) {

                console.log(i + ' ' + value.code);
                html += '<option value="' + value.code + '">' + value.classificationName + '</option>';
            });
            console.log(html)
            $('#commodityCategory').append(html);
        },
        error: function () {
            alert(" getCommodityCategoryData存在错误")
        }
    });

}


function submitAddWarehouseInformation() {
    var WarehouseInformation = {};
    WarehouseInformation.warehouseName = $("#warehouseName").val();
    WarehouseInformation.warehouseNumber = $("#warehouseNumber").val();
    WarehouseInformation.warehousePersonInChargeId = $("#warehousePersonInChargeId").val()
    WarehouseInformation.warehouseType = $("#warehouseType").val();
    /*WarehouseInformation.storehouseBarCode = $("#storehouseBarCode").val();*/
    WarehouseInformation.openingDate = $("#openingDate").val();
    WarehouseInformation.lengthUnit = $("#lengthUnit").val();
    WarehouseInformation.area = $("#area").val();
    WarehouseInformation.wide = $("#wide").val();
    WarehouseInformation.height = $("#height").val();
    WarehouseInformation.commodityCategory = $("#commodityCategory").val();
    WarehouseInformation.status = $("#status").val();
    WarehouseInformation.warehouseRemark = $("#warehouseRemark").val();
    console.log(JSON.stringify(WarehouseInformation))
    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "warehouseInformation.html?act=AddWarehouseInformation",
        data: JSON.stringify(WarehouseInformation),
        success: function (data) {
            findAction();
            if (!data.state) {
                /* findAction();*/
                $.messager.show({
                    title: '温馨提示',
                    msg: data.message,
                    timeout: 5000,
                    showType: 'slide'
                });
                $("#AddWarehouseInformation-dialog").dialog("close")
            } else {
                $.messager.show({
                    title: '温馨提示',
                    msg: data.message,
                    timeout: 5000,
                    showType: 'slide'
                });
            }
        },
        error: function () {
            $.messager.show({
                title: '温馨提示',
                msg: 'submitAddWarehouseInformation异常',
                timeout: 5000,
                showType: 'slide'
            });
        }

    })
}

function UpWarehouseInformation() {
    $('#UpWarehouseInformation_dialog_Edit_from').form('clear');
    getUpWarehouseInformation_Edit();
}

/*加载供应商信息 商品大类 commodityCategory*/
function getCommodityCategoryData_Edit() {
    $.ajax({
        type: "GET",
        url: "commodityInformation.html?act=Find_queryAllSupplierLargeClass",
        data: {},
        dataType: "json",
        success: function (json) {
            $("#_commodityCategory").find("option").remove();
            var html = '<option value="">请选择</option>';
            $.each(json.data, function (i, value) {

                console.log(i + ' ' + value.code);
                html += '<option value="' + value.code + '">' + value.classificationName + '</option>';
            });
            console.log(html)
            $('#_commodityCategory').append(html);
        },
        error: function () {
            alert(" getCommodityCategoryData_Edit存在错误")
        }
    });

}

/*加载供应商信息 商品大类 commodityCategory*/
function getCommodityCategoryData_Find() {
    $.ajax({
        type: "GET",
        url: "commodityInformation.html?act=Find_queryAllSupplierLargeClass",
        data: {},
        dataType: "json",
        success: function (json) {
            console.log("Find-commodityCategory: ")
            $("#Find-commodityCategory").find("option").remove();
            var html = '<option value="">请选择</option>';
            $.each(json.data, function (i, value) {

                console.log(i + ' ' + value.code);
                html += '<option value="' + value.code + '">' + value.classificationName + '</option>';
            });
            console.log(html)
            $('#Find-commodityCategory').append(html);
        },
        error: function () {
            alert(" getCommodityCategoryData_Find 存在错误")
        }
    });

}

/*加载工作人员信息  Edit*/
function getUserInformationIdData_Edit() {
    $.ajax({
        type: "GET",
        url: "userInformation.html?act=user_list",
        data: {},
        dataType: "json",
        success: function (json) {
            $("#_warehousePersonInChargeId").find("option").remove();
            var html = '<option value="">请选择</option>';
            $.each(json, function (i, value) {

                console.log(i + ' ' + value.id);
                html += '<option value="' + value.id + '">' + value.userName + '</option>';
            });
            console.log(html)
            $('#_warehousePersonInChargeId').append(html);
        },
        error: function () {
            alert("JS  function getUserInformationIdData_Edit 存在错误")
        }
    });

}

function getUpWarehouseInformation_Edit() {
    var row = $("#WarehouseInformation_table").datagrid("getSelected");//选中一行数据
    if (row == null || row.length == 0) {
        return;
    }
    var WarehouseInformation = {};
    WarehouseInformation.id = row.id;
    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "warehouseInformation.html?act=SingleWarehouseInformation",
        data: JSON.stringify(WarehouseInformation),
        success: function (data) {
            getUserInformationIdData_Edit();
            getCommodityCategoryData_Edit();
            console.log(" data.data.SingleWarehouseInformation.id  " + data.data.id)
            $("#UpWarehouseInformation-dialog").dialog({
                onLoad: function () {
                    $("#UpWarehouseInformation_dialog_Edit_from").form("load", {
                        _id: data.data.id,
                        _warehouseName: data.data.warehouseName,
                        _warehousePersonInChargeId: data.data.warehousePersonInChargeId,
                        _warehouseType: data.data.warehouseType,
                        _openingDate: data.data.openingDate,
                        _area: data.data.area,
                        _wide: data.data.wide,
                        _height: data.data.height,
                        _commodityCategory: data.data.commodityCategory,
                        _status: data.data.status,
                        _warehouseRemark: data.data.warehouseRemark,
                    })
                }
            }).dialog("open")
        },
        error: function () {
            $.messager.show({
                title: '温馨提示',
                msg: 'JS getUpWarehouseInformation_Edit() 错误',
                timeout: 5000,
                showType: 'slide'
            });
        }
    })
}

function submitUpWarehouseInformation() {
    var WarehouseInformation = {};
    WarehouseInformation.warehouseName = $("#_warehouseName").val();
    WarehouseInformation.id = $("#_id").val();
    WarehouseInformation.warehousePersonInChargeId = $("#_warehousePersonInChargeId").val()
    WarehouseInformation.warehouseType = $("#_warehouseType").val();
    /*  WarehouseInformation.storehouseBarCode = $("#storehouseBarCode").val();*/
    WarehouseInformation.openingDate = $("#_openingDate").val();
    WarehouseInformation.lengthUnit = $("#_lengthUnit").val();
    WarehouseInformation.area = $("#_area").val();
    WarehouseInformation.wide = $("#_wide").val();
    WarehouseInformation.height = $("#_height").val();
    WarehouseInformation.commodityCategory = $("#_commodityCategory").val();
    WarehouseInformation.status = $("#_status").val();
    WarehouseInformation.warehouseRemark = $("#_warehouseRemark").val();
    console.log(JSON.stringify(WarehouseInformation))
    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "warehouseInformation.html?act=UpWarehouseInformation",
        data: JSON.stringify(WarehouseInformation),
        success: function (data) {
            findAction();
            $('#UpWarehouseInformation-dialog').window('close');
            $.messager.show({
                title: '温馨提示',
                msg: data.message,
                timeout: 5000,
                showType: 'slide'
            });

        },
        error: function () {
            $.messager.show({
                title: '温馨提示',
                msg: "submitUpWarehouseInformation: 错误",
                timeout: 5000,
                showType: 'slide'
            });
        }

    })

}

function DeleteWarehouseInformation() {
    var row = $("#WarehouseInformation_table").datagrid("getSelected");//选中一行数据
    if (row == null || row.length == 0) {
        $.messager.show({
            title: '温馨提示',
            msg: "请选择一行数据",
            timeout: 5000,
            showType: 'slide'
        });
        return;
    }

    var WarehouseInformation={};
    WarehouseInformation.id=row.id;
    $.ajax({
        url: "role.html?act=Role_judge",
        success: function (data) {
            console.log(data.data.role.roleName)
            if (data.data.role.roleName == "admin") {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    url: "warehouseInformation.html?act=DeleteWarehouseInformation",
                    data: JSON.stringify(WarehouseInformation),
                    success: function (data) {
                        findAction();
                        $.messager.show({
                            title: '温馨提示',
                            msg: data.message,
                            timeout: 5000,
                            showType: 'slide'
                        });

                    },
                    error: function () {
                        $.messager.show({
                            title: '温馨提示',
                            msg: "DeleteWarehouseInformation(): 错误",
                            timeout: 5000,
                            showType: 'slide'
                        });
                    }
                })
            } else {
                $.messager.show({
                    title: '温馨提示',
                    msg: '权限不足',
                    timeout: 5000,
                    showType: 'slide'
                });
            }

        },
        error: function () {
            $.messager.show({
                title: '温馨提示',
                msg: '访问权限错误',
                timeout: 5000,
                showType: 'slide'
            });
        }
    })
}

function checkInt(n, min, max, goodsShelvesRowNo) {
    var regex = /^\d+$/;
    if (regex.test(n)) {
        if (n <=max && n >= min) {
            /* alert("这是小于"+max+"的正整数！！")*/
        } else {
            document.getElementById(goodsShelvesRowNo).value = "";
            $.messager.show({
                title: '温馨提示',
                msg: "这不是大于"+min+"小于" + max + "的正整数！！",
                timeout: 800,
                showType: 'slide'
            });
        }
    } else {
        document.getElementById(goodsShelvesRowNo).value = "";

        $.messager.show({
            title: '温馨提示',
            msg: "非整数",
            timeout: 800,
            showType: 'slide'
        });

    }
}