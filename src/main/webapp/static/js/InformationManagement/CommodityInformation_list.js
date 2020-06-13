var localObj = window.location;

var contextPath = localObj.pathname.split("/")[1];

var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
$(function () {
    // 初始化表格
    var datagridDataOptions = {
        fit: true,
        border: true,
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        pagination: true,
        striped: true,
        pageSize: 10,
        pageNumber: 1,
        pageList: [5, 10, 20, 30, 40, 50, 100, 500],
        /* toolbar: "#credit_payment_tender_supplier_list_toobar_id",*/
        idField: "id",
        showPageList: true,
        toolbar: [
            {
                text: '增加', iconCls: 'icon-add', handler: function () {
                    AddCommodityInformation();
                }
            },
            {
                text: '修改', iconCls: 'icon-bookmark-edit', handler: function () {
                    CommodityInformationUpdate();
                }
            },
            {
                text: '删除', iconCls: 'icon-remove', handler: function () {
                    CommodityInformationDelete();
                }
            },
            {
                text: '刷新', iconCls: 'icon-reload', handler: function () {
                    findAction();
                }
            },
            {
                text: '导出商品信息', iconCls: 'icon-save', handler: function () {
                    exportExceCommodityInformationlAll();

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
            }, {
                title: "ID",
                field: "commodityId",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
                hidden: 'true'
            },
            {
                title: "商品名称",
                field: "commodityName",
                width: 120,
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
                title: "商品编码",
                field: "commodityCode",
                width: 70,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "商品品牌",
                field: "commdityBrand",
                width: 80,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "所属供应商",
                field: "supplierName",
                width: 160,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "供应商编号",
                field: "supplierCode",
                width: 110,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "供应商电话",
                field: "supplierPhone",
                width: 110,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "商品大类",
                field: "commodityCategory",
                width: 110,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "商品小类",
                field: "commoditySubgroup",
                width: 120,
                align: "right"
            }, {
                title: "商品型号",
                field: "commodityMn",
                width: 120,
                align: "right"
            }, {
                title: "存放温度/℃",
                field: "storageTemperature",
                width: 80,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "保质期时间",
                field: "qgp",
                width: 80,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "体积",
                field: "volume",
                width: 40,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "码盘层高",
                field: "encoderHeight",
                width: 40,
                align: "right",
            }, {
                title: "毛重",
                field: "grossWeight",
                width: 100,
                align: "left"
            }, {
                title: "净重",
                field: "netWeight",
                width: 100,
                align: "left"
            }, {
                title: "状态",
                field: "status",
                width: 100,
                align: "left",
                sortable: true,
                order: "asc"
            },
        ]],
        url: "commodityInformation.html?act=list",
        method: "GET",
        loadFilter: function (data) {
            doClearAction();
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows,
                "footer": data.state != 0 ? [] : data.data.footer
            }
        }
    };
    $("#CommodityInformation_table_id").datagrid(datagridDataOptions);
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
    var pager = $("#CommodityInformation_table_id").datagrid("getPager");
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

function doClearAction() {

    $('#find_CommodityInformation_form_id').form('clear');
    findAction();

}

/*重新加载表格*/
function loadDatagrid() {
    $("#CommodityInformation_table_id").datagrid({
        queryParams: {
            commodityName: $("input[name$='commodityName']").val(),
            supplierName: $("input[name$='supplierName']").val(),
            supplierPhone: $("input[name$='supplierPhone']").val(),
            commodityMn: $("input[name$='commodityMn']").val(),
            status: $("input[name$='status']").val(),
        },
        loadFilter: function (data) {
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows,
                "footer": data.state != 0 ? [] : data.data.footer
            }
        }

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

function AddCommodityInformation() {
    $('#AddCommodityInformation-dialog_from').form('clear');
    getSupplierIdData();
    getCommodityCategoryData();
    $("#AddCommodityInformation-dialog").dialog("open")
}


function ww4(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    var h = date.getHours();
    return y + '年' + (m < 10 ? ('0' + m) : m) + '月' + (d < 10 ? ('0' + d) : d) + '日' + (h < 10 ? ('0' + h) : h) + '点';

}

function w4(s) {
    var reg = /[\u4e00-\u9fa5]/  //利用正则表达式分隔
    var ss = (s.split(reg));
    var y = parseInt(ss[0], 10);
    var m = parseInt(ss[1], 10);
    var d = parseInt(ss[2], 10);
    var h = parseInt(ss[3], 10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h)) {
        return new Date(y, m - 1, d, h);
    } else {
        return new Date();
    }
}
function formatter(date){// 重写默认日期格式
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var hour = date.getHours().toString();
    var minute = date.getMinutes().toString();
    var second = date.getSeconds().toString();
    return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' +second;
}
function parser(date) {
    //重写formatter 必须 重写parser, 否则无论选择哪天, 日期均为当天日期
    //xxxx-x-x xx:xx:xx
    if (!date) return new Date();
    var array = date.split(" ");// 分成日期和时间两部分
    var arrayDate = array[0].split("-");
    var yearStr = arrayDate[0];
    var monthStr = arrayDate[1];
    var dayStr = arrayDate[2];
    var arrayTime = array[1].split(":");
    var hour = arrayTime[0];
    var minute = arrayTime[1];
    var second = arrayTime[2];
    var year = parseInt(yearStr, 10);
    var month = parseInt(monthStr, 10);
    var day = parseInt(dayStr, 10);
    if (!isNaN(year) && !isNaN(month) && !isNaN(day) && !isNaN(hour) && !isNaN(minute) && !isNaN(second)) {
        return new Date(year, month - 1, day, hour, minute, second);
    } else {
        return new Date();
    }
}



/*加载供应商信息*/
function getSupplierIdData() {
    $.ajax({
        type: "GET",
        url: "supplier.html?act=Find_SupplierInformationList",
        data: {},
        dataType: "json",
        success: function (json) {
            $("#supplierId").find("option").remove();
            var html = '<option value="">请选择</option>';
            $.each(json.data.rows, function (i, value) {

                console.log(i + ' ' + value.supplierId);
                html += '<option value="' + value.supplierId + '">' + value.chineseFullName + '</option>';
            });
            console.log(html)
            $('#supplierId').append(html);
        },
        error: function () {
            alert(" function getSupplierIdData存在错误")
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

/*小类*/
function ChangeCommoditySubgroup() {
    var parentClassCode = jQuery("#commodityCategory").val();
    $.ajax({
        type: "GET",
        url: "commodityInformation.html?act=Find_SupplierClassInformation&parentClassCode=" + parentClassCode,
        data: {},
        dataType: "json",
        success: function (json) {
            $("#commoditySubgroup").find("option").remove();
            var html = '<option value="">请选择</option>';
            $.each(json.data, function (i, value) {

                console.log(i + ' ' + value.code);
                html += '<option value="' + value.code + '">' + value.classificationName + '</option>';
            });
            console.log(html)
            $('#commoditySubgroup').append(html);
        },
        error: function () {
            alert(" getCommodityCategoryData存在错误")
        }
    });
}

function removable() {

    var parentClassCode = jQuery("#removable").val();
    if (parentClassCode != null && parentClassCode != "") {
        $("#disassembleNumber").show();
    } else {
        $("#disassembleNumber").hide();
    }
}

function submitCommodityInformation() {
    var commodityName = $("#commodityName").val();
    var commodityAbbreviation = $("#commodityAbbreviation").val();
    var commodityMn = $("#commodityMn").val();
    console.log(commodityName + "hahah  " + commodityAbbreviation + "  " + commodityName + " $(\"#productionDate\").val();  " + $("#productionDate").val())
    var CommodityInformation = {};
    CommodityInformation.commodityName = $("#commodityName").val();
    CommodityInformation.commodityAbbreviation = $("#commodityAbbreviation").val();
    CommodityInformation.commodityMn = $("#commodityMn").val();
    CommodityInformation.commoditySpecs = $("#commoditySpecs").val();
    CommodityInformation.commodityColor = $("#commodityColor").val();
    CommodityInformation.commodityAttribute = $("#commodityAttribute").val();
    CommodityInformation.commodityBarCode = $("#commodityBarCode").val();
    CommodityInformation.commodityBrand = $("#commodityBrand").val();
    CommodityInformation.storageTemperature = $("#storageTemperature").val();
    CommodityInformation.encoderNumber = $("#encoderNumber").val();
    CommodityInformation.encoderHeight = $("#encoderHeight").val();
    CommodityInformation.productionDate = $("#productionDate").val();
    CommodityInformation.qgp = $("#qgp").val();
    CommodityInformation.dimensionUnit = $("#dimensionUnit").val();
    CommodityInformation.massUnit = $("#massUnit").val();
    CommodityInformation.itemLength = $("#itemLength").val();
    CommodityInformation.itemWide = $("#itemWide").val();
    CommodityInformation.itemHigh = $("#itemHigh").val();
    CommodityInformation.fclLength = $("#fclLength").val();
    CommodityInformation.fclWide = $("#fclWide").val();
    CommodityInformation.fclHigh = $("#fclHigh").val();
    CommodityInformation.singleVolume = $("#singleVolume").val();
    CommodityInformation.totalVolume = $("#totalVolume").val();
    CommodityInformation.grossWeight = $("#grossWeight").val();
    CommodityInformation.netWeight = $("#netWeight").val();
    CommodityInformation.commodityUnitPrice = $("#commodityUnitPrice").val();
    CommodityInformation.commodityTotalPrice = $("#commodityTotalPrice").val();
    CommodityInformation.supplierId = $("#supplierId").val();
    CommodityInformation.commodityCategory = $("#commodityCategory").val();
    CommodityInformation.commoditySubgroup = $("#commoditySubgroup").val();
    CommodityInformation.status = $("#status").val();
    CommodityInformation.storageUnit = $("#storageUnit").val();
    CommodityInformation.commodityStorageType = $("#commodityStorageType").val();
    CommodityInformation.commodityRemark = $("#commodityRemark").val();
    console.log(JSON.stringify(CommodityInformation).toString())
    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "commodityInformation.html?act=AddCommodityInformation",
        data: JSON.stringify(CommodityInformation),
        success: function (data) {
            //$("#role_table").dataTable().fnClearTable();
            if (!data.state) {
                findAction();
                $.messager.show({
                    title: '温馨提示',
                    msg: data.message,
                    timeout: 5000,
                    showType: 'slide'
                });
                $("#AddCommodityInformation-dialog").dialog("close")
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
                msg: '异常',
                timeout: 5000,
                showType: 'slide'
            });
        }
    })
}

function CommodityInformationUpdate() {
    var row = $("#CommodityInformation_table_id").datagrid("getSelected");//选中一行数据
    if (row == null || row.length == 0) {
        return;
    }
    $.ajax({
        url: "commodityInformation.html?act=SingleCommodityInformation",
        data: {
            id: row.id,
            commodityId: row.commodityId,
        },
        success: function (data) {

            $('#UpCommodityInformation_dialog_Edit_from').form('clear');
            getSupplierIdData_Edit();
            console.log(" data.data.supplierId  " + data.data.supplierId)
            getCommodityCategoryData_Edit();
            $("#UpCommodityInformation-dialog").dialog({
                onLoad: function () {
                    $("#UpCommodityInformation_dialog_Edit_from").form("load", {
                        _id: data.data.id,
                        _commodityId: data.data.commodityId,
                        _commodityName: data.data.commodityName,
                        _commodityAbbreviation: data.data.commodityAbbreviation,
                        _commodityMn: data.data.commodityMn,
                        _commoditySpecs: data.data.commoditySpecs,
                        _commodityColor: data.data.commodityColor,
                        _commodityAttribute: data.data.commodityAttribute,
                        _commodityBarCode: data.data.commodityBarCode,
                        _commodityBrand: data.data.commodityBrand,
                        _storageTemperature: data.data.storageTemperature,
                        _encoderNumber: data.data.encoderNumber,
                        _encoderHeight: data.data.encoderHeight,
                        _productionDate: data.data.productionDate,
                        _qgp: data.data.qgp,
                        _itemLength: data.data.itemLength,
                        _itemWide: data.data.itemWide,
                        _itemHigh: data.data.itemHigh,
                        _fclLength: data.data.fclLength,
                        _fclWide: data.data.fclWide,
                        _fclHigh: data.data.fclHigh,
                        _singleVolume: data.data.singleVolume,
                        _totalVolume: data.data.totalVolume,
                        _grossWeight: data.data.grossWeight,
                        _netWeight: data.data.netWeight,
                        _commodityUnitPrice: data.data.commodityUnitPrice,
                        _commodityTotalPrice: data.data.commodityTotalPrice,
                        _supplierId: data.data.supplierId,
                        _commodityCategory: data.data.commodityCategory,
                        _commoditySubgroup: data.data.commoditySubgroup,
                        _status: data.data.status,
                        _storageUnit: data.data.storageUnit,
                        _commodityStorageType: data.data.commodityStorageType,
                        _commodityRemark: data.data.commodityRemark,
                    })
                }
            }).dialog("open")
        },
        error: function () {
            $.messager.show({
                title: '温馨提示',
                msg: 'JS CommodityInformationUpdate() 错误',
                timeout: 5000,
                showType: 'slide'
            });
        }
    })
}

/*加载供应商信息 Edit up弹窗*/
function getSupplierIdData_Edit() {
    $.ajax({
        type: "GET",
        url: "supplier.html?act=Find_SupplierInformationList",
        data: {},
        dataType: "json",
        success: function (json) {
            $("#_supplierId").find("option").remove();
            var html = '<option value="">请选择</option>';
            $.each(json.data.rows, function (i, value) {
                console.log(i + ' ' + value.supplierId);
                html += '<option value="' + value.supplierId + '">' + value.chineseFullName + '</option>';
            });
            console.log(html)
            $('#_supplierId').append(html);
        },
        error: function () {
            $.messager.show({
                title: '温馨提示',
                msg: 'getSupplierIdData_Edit() 错误',
                timeout: 5000,
                showType: 'slide'
            });
        }
    });

}

/*加载供应商信息 商品大类 commodityCategory Edit up弹窗*/
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
            $.messager.show({
                title: '温馨提示',
                msg: 'getCommodityCategoryData_Edit() 错误',
                timeout: 5000,
                showType: 'slide'
            });
        }
    });
}

/*小类 Edit up弹窗*/
function ChangeCommoditySubgroup_Edit() {
    var parentClassCode = jQuery("#_commodityCategory").val();
    $.ajax({
        type: "GET",
        url: "commodityInformation.html?act=Find_SupplierClassInformation&parentClassCode=" + parentClassCode,
        data: {},
        dataType: "json",
        success: function (json) {
            $("#_commoditySubgroup").find("option").remove();
            var html = '<option value="">请选择</option>';
            $.each(json.data, function (i, value) {

                console.log(i + ' ' + value.code);
                html += '<option value="' + value.code + '">' + value.classificationName + '</option>';
            });
            console.log(html)
            $('#_commoditySubgroup').append(html);
        },
        error: function () {
            $.messager.show({
                title: '温馨提示',
                msg: 'getCommodityCategoryData存在错误',
                timeout: 5000,
                showType: 'slide'
            });
        }
    });
}

function submitUpCommodityInformation() {
    var CommodityInformation = {};
    CommodityInformation.id = $("#_id").val();
    CommodityInformation.commodityId = $("#_commodityId").val();
    CommodityInformation.commodityName = $("#_commodityName").val();
    CommodityInformation.commodityAbbreviation = $("#_commodityAbbreviation").val();
    CommodityInformation.commodityMn = $("#_commodityMn").val();
    CommodityInformation.commoditySpecs = $("#_commoditySpecs").val();
    CommodityInformation.commodityColor = $("#_commodityColor").val();
    CommodityInformation.commodityAttribute = $("#_commodityAttribute").val();
    CommodityInformation.commodityBarCode = $("#_commodityBarCode").val();
    CommodityInformation.commodityBrand = $("#_commodityBrand").val();
    CommodityInformation.storageTemperature = $("#_storageTemperature").val();
    CommodityInformation.encoderNumber = $("#_encoderNumber").val();
    CommodityInformation.encoderHeight = $("#_encoderHeight").val();
    CommodityInformation.productionDate = $("#_productionDate").val();
    CommodityInformation.qgp = $("#_qgp").val();
    CommodityInformation.dimensionUnit = $("#_dimensionUnit").val();
    CommodityInformation.massUnit = $("#_massUnit").val();
    CommodityInformation.itemLength = $("#_itemLength").val();
    CommodityInformation.itemWide = $("#_itemWide").val();
    CommodityInformation.itemHigh = $("#_itemHigh").val();
    CommodityInformation.fclLength = $("#_fclLength").val();
    CommodityInformation.fclWide = $("#_fclWide").val();
    CommodityInformation.fclHigh = $("#_fclHigh").val();
    CommodityInformation.singleVolume = $("#_singleVolume").val();
    CommodityInformation.totalVolume = $("#_totalVolume").val();
    CommodityInformation.grossWeight = $("#_grossWeight").val();
    CommodityInformation.netWeight = $("#_netWeight").val();
    CommodityInformation.commodityUnitPrice = $("#_commodityUnitPrice").val();
    CommodityInformation.commodityTotalPrice = $("#_commodityTotalPrice").val();
    CommodityInformation.supplierId = $("#_supplierId").val();
    CommodityInformation.commodityCategory = $("#_commodityCategory").val();
    CommodityInformation.commoditySubgroup = $("#_commoditySubgroup").val();
    CommodityInformation.status = $("#_status").val();
    CommodityInformation.storageUnit = $("#_storageUnit").val();
    CommodityInformation.commodityStorageType = $("#_commodityStorageType").val();
    CommodityInformation.commodityRemark = $("#_commodityRemark").val();
    console.log(JSON.stringify(CommodityInformation).toString())
    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "commodityInformation.html?act=UpCommodityInformation",
        data: JSON.stringify(CommodityInformation),
        success: function (result) {
            $('#UpCommodityInformation-dialog').window('close');
            findAction();
            $.messager.show({
                title: '温馨提示',
                msg: result.message,
                timeout: 5000,
                showType: 'slide'
            });
        },
        error: function () {
            $.messager.show({
                title: '温馨提示',
                msg: '异常',
                timeout: 5000,
                showType: 'slide'
            });
        }
    });
}

function submitCommodityInformationDelete(id, commodityId) {
    if (del()) {
        $.ajax({
            url: "commodityInformation.html?act=CommodityInformationDelete",
            data: {
                id: id,
                commodityId: commodityId,
            },
            success: function (data) {
                findAction();
                $.messager.show({
                    title: '温馨提示',
                    msg: data.message,
                    timeout: 5000,
                    showType: 'slide'
                });
            },
            error: function (data) {
                $.messager.show({
                    title: '温馨提示',
                    msg: "访问后台失败",
                    timeout: 5000,
                    showType: 'slide'
                });
            }

        })
    } else {
        return;
    }

}

function CommodityInformationDelete() {
    var row = $("#CommodityInformation_table_id").datagrid("getSelected");//选中一行数据
    if (row == null || row.length == 0) {
        return;
    }
    $.ajax({
        url: "role.html?act=Role_judge",
        success: function (data) {
            console.log(data.data.role.roleName)
            if (data.data.role.roleName == "admin") {
                submitCommodityInformationDelete(row.id, row.commodityId)
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

function exportExceCommodityInformationlAll() {
    location.href = "commodityInformation.html?act=exportCommodityInformation";
}