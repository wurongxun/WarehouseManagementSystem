$(function () {
    // 初始化表格
    var datagridDataOptions = {
        fit: true, //datagrid高度是否自适应
        nowrap: true, //是否只显示一行，即文本过多是否省略部分
        striped: true,
        border: false,
        pagination: true,
        pageSize: 15,
        pageList: [5, 15, 25, 30, 40, 50, 100, 500],
        pageNumber: 1,
        rownumbers: true, //显示行号列
        checkOnSelect: false,
        selectOnCheck: false,
        singleSelect: false, //是允许选择一行
        /* singleSelect: true,*/
        fitColumns: true,
        idField: "id",
        showPageList: true,
        toolbar: [
            {
                text: '批量添加架位', iconCls: 'icon-add', handler: function () {
                    AddShelfPositionInformation_Batch();
                }
            },
           /* {
                text: '单独添加架位', iconCls: 'icon-add', handler: function () {
                    AddShelfPositionInformation_Single();
                }
            },*/
            {
                text: '修改', iconCls: 'icon-bookmark-edit', handler: function () {
                    RoleUpdate();
                }
            },
            {
                text: '删除', iconCls: 'icon-remove', handler: function () {
                    deviceInfoDeleteClick();
                }
            },
            {
                text: '刷新', iconCls: 'icon-reload', handler: function () {
                    deviceInfoRefreshClick();
                }
            },
            {
                text: '导出', iconCls: 'icon-save', handler: function () {
                    exportExcel();
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
                title: "架位编号",
                field: "shelfPositionCode",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc"
            },
            {
                title: "所属货架号",
                field: "goodsShelvesNo",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc"
            },
            {
                title: "仓库名称",
                field: "warehouseName",
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
                title: "仓库编码",
                field: "warehouseCode",
                width: 70,
                align: "left",
                sortable: true,
                order: "asc"
            },
            {
                title: "长",
                field: "length",
                width: 70,
                align: "left",
                sortable: true,
                order: "asc"
            },
            {
                title: "宽",
                field: "wide",
                width: 70,
                align: "left",
                sortable: true,
                order: "asc"
            },
            {
                title: "高",
                field: "height",
                width: 70,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "负责人",
                field: "warehousePersonInCharge",
                width: 80,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "仓库类型",
                field: "warehouseType",
                width: 80,
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
                formatter: function (value, row, index) {
                    if (value == 1) {
                        return "工具";
                    } else {
                        return "药品";
                    }
                }
            }, {
                title: "存储状态",
                field: "isItEmpty",
                width: 80,
                align: "right",
                sortable: true,
                order: "asc",
                formatter: function (value, row, index) {
                    if (value == 1) {
                        return "有货物";
                    } else if (value == 2) {
                        return "无货物";
                    } else if (value = 3) {
                        return "已被订单选中";
                    } else {
                        return "数据存在";
                    }
                }
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
        url: "shelfPositionInformation.html?act=list",
        method: "GET",
        loadFilter: function (data) {
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows,
                "footer": data.state != 0 ? [] : data.data.footer
            }
        }
    };
    $("#ShelfPositionInformation_table").datagrid(datagridDataOptions);
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
    var pager = $("#ShelfPositionInformation_table").datagrid("getPager");
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
    location.href = "shelfPositionInformation.html?act=exportShelfPositionInformation"+
        "&goodsShelvesNo=" + $("input[name$='goodsShelvesNo']").val() +
        "&shelfPositionCode=" + $("input[name$='shelfPositionCode']").val() +
        "&shelfPositionRowNo=" + $("input[name$='shelfPositionRowNo']").val() +
        "&shelfPositionLayerNo=" + $("input[name$='shelfPositionLayerNo']").val() +
        "&warehousePersonInCharge=" + $("input[name$='warehousePersonInCharge']").val() +
        "&warehouseCode=" + $("input[name$='warehouseCode']").val() +
        "&warehouseName=" + $("input[name$='warehouseName']").val() +
        "&warehouseType=" + $("input[name$='warehouseType']").val() +
        "&status=" + $("input[name$='status']").val();

}


/*重新加载表格*/

function loadDatagrid() {
    $("#ShelfPositionInformation_table").datagrid("load", {
        goodsShelvesNo: $("input[name$='goodsShelvesNo']").val(),
        shelfPositionCode: $("input[name$='shelfPositionCode']").val(),
        shelfPositionRowNo: $("input[name$='shelfPositionRowNo']").val(),
        shelfPositionLayerNo: $("input[name$='shelfPositionLayerNo']").val(),
        warehousePersonInCharge: $("input[name$='warehousePersonInCharge']").val(),
        warehouseCode: $("input[name$='warehouseCode']").val(),
        warehouseName: $("input[name$='warehouseName']").val(),
        warehouseType: $("input[name$='warehouseType']").val(),
        status: $("input[name$='status']").val(),
        tradeTime2: $("input[name$='tradeTime2']").val()
    });
}

//查询

function findAction() {
    loadDatagrid();
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
    $('#find_ShelfPositionInformation_form_id').form('clear');
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

function AddShelfPositionInformation_Batch() {
    $('#AddShelfPositionInformation-dialog-from-id').form('clear');
    getWarehouseInformationData();
    $("#addShelfPositionInformation_buttons_submit").hide();
    $('#AddShelfPositionInformation-dialog').dialog("open");

}

/*获取仓库信息*/
function getWarehouseInformationData() {
    $.ajax({
        type: "GET",
        url: "warehouseInformation.html?act=listAll",
        data: {},
        dataType: "json",
        success: function (json) {
            $("#warehouseId").find("option").remove();
            var html = '<option value="">请选择</option>';
            $.each(json.data, function (i, value) {
                console.log(i + ' ' + value.warehouseId);
                html += '<option value="' + value.warehouseId + '">' + value.warehouseName + '</option>';
            });
            console.log(html)
            $('#warehouseId').append(html);
        },
        error: function () {
            alert(" getWarehouseInformationData存在错误")
        }
    });
}

/*根据仓库ID 查看当前仓库有多少货架 并放入 下拉框中*/
function getGoodsShelvesInformationData() {
    $("#goodsShelvesId").find("option").remove();
    var warehouseId = jQuery("#warehouseId").val();
    $.ajax({
        type: "GET",
        url: "goodsShelvesInformation.html?act=QueryGoodsShelvesInformationWarehouseId&warehouseId=" + warehouseId,
        data: {},
        dataType: "json",
        success: function (json) {
            sessionStorage.setItem("GoodsShelvesInformationJson", JSON.stringify(json.data));
            if (json.state == 0) {
                console.log("json.data" + json.data.message)
                var html = '<option value="">请选择</option>';
                $.each(json.data, function (i, value) {

                    console.log(i + ' ' + value.goodsShelvesId);
                    html += '<option value="' + value.goodsShelvesId + '">' + value.goodsShelvesName + '</option>';
                });
                console.log(html)
                $('#goodsShelvesId').append(html);
            } else {
                $.messager.show({
                    title: '温馨提示',
                    msg: json.message,
                    timeout: 4000,
                    showType: 'null',
                    style: {}
                });
            }

        },
        error: function () {
            alert(" getGoodsShelvesInformationData存在错误")
        }
    });
}

function InputJudgement() {
    if (jQuery("#warehouseId").val() != null &&
        jQuery("#goodsShelvesId").val() != null &&
        jQuery("#length").val() != null &&
        jQuery("#wide").val() != null &&
        jQuery("#height").val() != null &&
        jQuery("#status").val() != null) {
        console.log(".show();")
        $("#addShelfPositionInformation_buttons_submit").show();
    } else {
        $("#addShelfPositionInformation_buttons_submit").hide();
    }

}

/*批量添加架位*/
function submitAddShelfPositionInformation_Batch() {
    var goodsShelvesId = jQuery("#goodsShelvesId").val();
    console.log("goodsShelvesId " + goodsShelvesId)
    var GoodsShelvesInformation = {};
    $.ajax({
        type: "GET",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "goodsShelvesInformation.html?act=SingleGoodsShelvesInformationByGoodsShelvesId&goodsShelvesId=" + goodsShelvesId,
        data: {},
        success: function (json) {
            console.log("json.data.id  " + json.data.id)
            var goodsShelvesLayerNo = json.data.goodsShelvesLayerNo;
            var goodsShelvesRowNo = json.data.goodsShelvesRowNo;
            $.messager.confirm("操作提示", "将产生" + json.data.goodsShelvesLayerNo * json.data.goodsShelvesRowNo + "个架位，确定要操作吗？", function (data) {
                if (data) {
                    //do 确定
                    var ShelfPositionInformation = {};
                    ShelfPositionInformation.warehouseId = $("#warehouseId").val();
                    ShelfPositionInformation.goodsShelvesId = $("#goodsShelvesId").val();
                    ShelfPositionInformation.dimensionUnit = "M";
                    console.log("$(\"#dimensionUnit\").val()" + $("#dimensionUnit").val() + "  $(\"#massUnit\").val() " + $("#massUnit").val())
                    ShelfPositionInformation.massUnit = "KG";
                    ShelfPositionInformation.length = $("#length").val();
                    ShelfPositionInformation.wide = $("#wide").val();
                    ShelfPositionInformation.height = $("#height").val();
                    ShelfPositionInformation.status = $("#status").val();
                    console.log("json.data.goodsShelvesLayerNo   " + goodsShelvesLayerNo)

                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        url: "shelfPositionInformation.html?act=AddShelfPositionInformation_Batch&goodsShelvesLayerNo=" + json.data.goodsShelvesLayerNo + "&goodsShelvesRowNo=" + json.data.goodsShelvesRowNo,
                        data: JSON.stringify(ShelfPositionInformation),
                        success: function (jsondata) {
                            if (jsondata.state == 0) {
                                $('#AddShelfPositionInformation-dialog').window('close')
                                $('#AddShelfPositionInformation-dialog-from-id').form('clear');
                                $.messager.show({
                                    title: '温馨提示',
                                    msg: jsondata.message,
                                    timeout: 5000,
                                    showType: 'slide'
                                });
                            } else {
                                $.messager.show({
                                    title: '温馨提示',
                                    msg: jsondata.message,
                                    timeout: 5000,
                                    showType: 'slide'
                                });
                            }
                        },
                        error: function () {
                            $.messager.show({
                                title: "温馨提示",
                                msg: "批量添加，后台错误",
                                timeout: 4000,
                                showType: 'slide'
                            });
                        }
                    })
                } else {
                    //do 取消
                }
            });
        },
        error: function () {
            $.messager.show({
                title: '温馨提示',
                msg: 'JS submitAddShelfPositionInformation(()__act=SingleGoodsShelvesInformation 错误',
                timeout: 5000,
                showType: 'slide'
            });
        }
    })

    /*  $.each(GoodsShelvesInformationJson.data, function (i, value) {
          console.log(i + ' ' + value.goodsShelvesId);
      });*/

}
