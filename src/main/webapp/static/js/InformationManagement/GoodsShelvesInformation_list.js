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
        idField: "id",
        showPageList: true,
        toolbar: [
            {
                text: '增加', iconCls: 'icon-add', handler: function () {
                    AddGoodsShelvesInformation();
                }
            },
            {
                text: '修改', iconCls: 'icon-bookmark-edit', handler: function () {
                    GoodsShelvesInformationUpdate();
                }
            },
            {
                text: '删除', iconCls: 'icon-remove', handler: function () {
                    GoodsShelvesInformationDelete();
                }
            },
            {
                text: '刷新', iconCls: 'icon-reload', handler: function () {
                    loadDatagrid();
                }
            },
            {
                text: '导出', iconCls: 'icon-save', handler: function () {
                    exportGoodsShelvesInformation();
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
                title: "warehouseId",
                field: "warehouseId",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
                hidden: 'true'
            },
            {
                title: "货架名称",
                field: "goodsShelvesName",
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
            },  {
                title: "仓库编号",
                field: "warehouseCode",
                width: 120,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "货架编号",
                field: "goodsShelvesCode",
                width: 120,
                align: "right",
            }, {
                title: "仓库名称",
                field: "warehouseName",
                width: 80,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "启用时间",
                field: "openingDate",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc"
            }, {
                title: "货架号",
                field: "goodsShelvesNo",
                width: 110,
                align: "right",
                sortable: true,
                order: "asc",
            }, {
                title: "货架层数",
                field: "goodsShelvesLayerNo",
                width: 120,
                align: "right",
            }, {
                title: "货架排数",
                field: "goodsShelvesRowNo",
                width: 120,
                align: "right",
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
        url: "goodsShelvesInformation.html?act=list",
        method: "GET",
        loadFilter: function (data) {
            $('#find_GoodsShelvesInformation_form_id').form('clear');
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows,
                "footer": data.state != 0 ? [] : data.data.footer
            }
        }
    };
    $("#GoodsShelvesInformation_table").datagrid(datagridDataOptions);
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
    var pager = $("#GoodsShelvesInformation_table").datagrid("getPager");
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


/*重新加载表格*/

function loadDatagrid() {
    $("#GoodsShelvesInformation_table").datagrid("load", {
        goodsShelvesName: $("input[name$='goodsShelvesName']").val(),
        warehouseName: $("input[name$='warehouseName']").val(),
        goodsShelvesCode: $("input[name$='goodsShelvesCode']").val(),
        warehouseCode: $("input[name$='warehouseCode']").val(),
        goodsShelvesNo: $("input[name$='goodsShelvesNo']").val(),
        status: $("input[name$='status']").val(),

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
    $('#find_GoodsShelvesInformation_form_id').form('clear');
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

function formatter(date) {// 重写默认日期格式
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours().toString();
    var minute = date.getMinutes().toString();
    var second = date.getSeconds().toString();
    return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
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

function AddGoodsShelvesInformation() {
    $('#AddGoodsShelvesInformation-dialog-from-id').form('clear');
       getWarehouseInformation();
    $('#AddGoodsShelvesInformation-dialog').dialog("open")
}
/*加载仓库信息*/
function getWarehouseInformation() {
    $.ajax({
        method: "GET",
        url: "warehouseInformation.html?act=listAll",
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
            alert("JS  getWarehouseInformation() 存在错误")
        }
    });

}

function submitAddGoodsShelvesInformation() {
    var GoodsShelvesInformation={}
    GoodsShelvesInformation.goodsShelvesName=$("#goodsShelvesName").val();
    GoodsShelvesInformation.goodsShelvesNo=$("#goodsShelvesNo").val();
    GoodsShelvesInformation.warehouseId=$("#warehouseId").val();
    GoodsShelvesInformation.openingDate=$("#openingDate").val();
    GoodsShelvesInformation.goodsShelvesLayerNo=$("#goodsShelvesLayerNo").val();
    GoodsShelvesInformation.goodsShelvesRowNo=$("#goodsShelvesRowNo").val();
    GoodsShelvesInformation.status=$("#status").val();
    console.log(JSON.stringify(GoodsShelvesInformation))
    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "goodsShelvesInformation.html?act=AddGoodsShelvesInformation",
        data: JSON.stringify(GoodsShelvesInformation),
        success: function (data) {
            if (!data.state) {
                findAction();
                $.messager.show({
                    title: '温馨提示',
                    msg: data.message,
                    timeout: 5000,
                    showType: 'slide'
                });
                $("#AddGoodsShelvesInformation-dialog").dialog("close")
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
                msg: 'submitAddGoodsShelvesInformation异常',
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

function  GoodsShelvesInformationUpdate() {
    var row=$("#GoodsShelvesInformation_table").datagrid("getSelected");
    if (row == null || row.length == 0) {

        $.messager.show({
            title: '温馨提示',
            msg: "请选择一行数据",
            timeout: 800,
            showType: 'slide'
        });
        return ;
    }
    var GoodsShelvesInformation = {};
    GoodsShelvesInformation.id = row.id;
    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "goodsShelvesInformation.html?act=SingleGoodsShelvesInformation",
        data: JSON.stringify(GoodsShelvesInformation),
        success: function (data) {
            console.log(" data.data.SingleWarehouseInformation.id  " + data.data.id)
            $("#UpGoodsShelvesInformation-dialog").dialog({
                onLoad: function () {
                    $("#UpGoodsShelvesInformation-dialog-Edit-from").form("load", {
                        _id: data.data.id,
                        _goodsShelvesLayerNo:data.data.goodsShelvesLayerNo,
                        _goodsShelvesRowNo: data.data.goodsShelvesRowNo,
                        _status: data.data.status,
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
function submitUpGoodsShelvesInformation() {

    var GoodsShelvesInformation={};
    GoodsShelvesInformation.id=$("#_id").val();
    GoodsShelvesInformation.goodsShelvesLayerNo=$("#_goodsShelvesLayerNo").val();
    GoodsShelvesInformation.goodsShelvesRowNo=$("#_goodsShelvesRowNo").val();
    GoodsShelvesInformation.status=$("#_status").val();
    console.log(JSON.stringify(GoodsShelvesInformation))
    $.ajax({
        type:"POST",
        dataType:"JSON",
        contentType: "application/json; charset=utf-8",
        url: "goodsShelvesInformation.html?act=UpGoodsShelvesInformation",
        data:JSON.stringify(GoodsShelvesInformation),
        success:function (data) {
            if (!data.state){
                findAction();
                $('#UpGoodsShelvesInformation-dialog').window('close');
                $.messager.show({
                    title: '温馨提示',
                    msg: data.message,
                    timeout: 1000,
                    showType: 'slide'
                });
            }else {
                $.messager.show({
                    title: '温馨提示',
                    msg: data.message,
                    timeout: 1000,
                    showType: 'slide'
                });
            }
        },
        error:function (data) {
            $.messager.show({
                title: '温馨提示',
                msg: 'JS submitUpGoodsShelvesInformation 错误',
                timeout: 1000,
                showType: 'slide'
            });
        }
    })
}

function  GoodsShelvesInformationDelete() {
    var row=$("#GoodsShelvesInformation_table").datagrid("getSelected");
    if (row == null || row.length == 0) {

        $.messager.show({
            title: '温馨提示',
            msg: "请选择一行数据",
            timeout:1000,
            showType: 'slide'
        });
        return ;
    }

    $.ajax({
        url: "role.html?act=Role_judge",
        success: function (data) {
            console.log(data.data.role.roleName)
            if (data.data.role.roleName == "admin") {
                $.ajax({
                    url: "goodsShelvesInformation.html?act=GoodsShelvesInformationDelete",
                    data: {
                        id:row.id
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
                    error: function () {
                        $.messager.show({
                            title: '温馨提示',
                            msg: "DeleteGoodsShelvesInformation(): 错误",
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

function exportGoodsShelvesInformation() {
    location.href = "goodsShelvesInformation.html?act=exportGoodsShelvesInformation";
}