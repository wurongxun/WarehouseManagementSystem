var localObj = window.location;

var contextPath = localObj.pathname.split("/")[1];

var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
var index; //父table 中被点击行的下标
$(function () {
    // 初始化表格
    var datagridDataOptions = {
        fit: true, //datagrid高度是否自适应
        nowrap: true, //是否只显示一行，即文本过多是否省略部分
        striped: true,
        border: false,
        pagination: true,
        fitColumns: true,
        pageSize: 15,
        pageList: [5, 15, 25, 35, 45, 55, 100, 500],
        pageNumber: 1,
        checkOnSelect: false,
        selectOnCheck: false,
        singleSelect: true,
        idField: "id",
        showPageList: true,
        toolbar: [
            {
                text: '申请出库', iconCls: 'icon-add', handler: function () {
                    AddDetailedListOutOfStockInformation();
                }
            },
           /* {
                text: '修改', iconCls: 'icon-bookmark-edit', handler: function () {
                    UpWarehouseInformation();
                }
            },*/
            {
                text: '删除', iconCls: 'icon-remove', handler: function () {
                    DeleteWarehouseInformation();
                }
            },
            {
                text: '刷新', iconCls: 'icon-reload', handler: function () {
                    findAction_OutOfStock();
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
            }, {
                title: "ID",
                field: "detailedListId",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
                hidden: 'true'
            }, {
                title: "ID",
                field: "staffId",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
                hidden: 'true'
            }, {
                title: "出库单编号",
                field: "detailedListCode",
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
                title: "出库单名称",
                field: "detailedListName",
                width: 70,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "负责人名",
                field: "staffName",
                width: 120,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "收货人名",
                field: "receivingDelivererPersonName",
                width: 80,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "收货人电话",
                field: "receivingDelivererPersonPhone",
                width: 80,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "收货人地址",
                field: "receivingDelivererPersonAddress",
                width: 160,
                align: "right",
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
                    if (value == 2) {
                        return "等待审核";
                    } else if (value == 7) {
                        return "出库成功";
                    } else if (value == 3) {
                        return "审核成功";
                    } else if (value == 4) {
                        return "等待出库";
                    } else if (value == 5) {
                        return "审核未通过";
                    }else if (value == 6) {
                        return "申请单已取消";
                    }  else {
                        return "数据存在错误";
                    }
                }
            }, {
                title: '操作',
                field: "operation",
                width: 120,
                align: 'center',
                formatter:function (value, row, index) {
                    var DetermineOutOfStock = '<a href="javascript:void(0)" name="viewDetermine" onclick="DetermineOutOfStock(\'' + index + '\')" >确定出库</a>';
                    var Cancel_Applicant_OutOfStock = '<a href="javascript:void(0)" color="red" name="Cancel_Applicant_OutOfStock" onclick="Cancel_Applicant_OutOfStock(\'' + index + '\')" >取消申请</a>';

                    if (row.status==3) {
                        return DetermineOutOfStock;
                    } else if (row.status == 2) {
                        return Cancel_Applicant_OutOfStock;
                    } else {
                        return '';
                    }
                }

            },
        ]],
        url: "DetailedListInformation.html?act=list&detailedListType=2",
        method: "GET",
        loadFilter: function (data) {
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows,
                "footer": data.state != 0 ? [] : data.data.footer
            }
        },
        onLoadSuccess: function (data) {
            $("a[name='viewDetermine']").linkbutton({
                plain: false,
                iconAlign: 'left',
                iconCls: 'icon-ok',
                height: '18px'
            });
            $("a[name='Cancel_Applicant_OutOfStock']").linkbutton({
                plain: false,
                iconAlign: 'left',
                iconCls: 'icon-tip',
                height: '18px'
            });
        },
    };
    $("#detailedList_OutOfStockInformation_table").datagrid(datagridDataOptions);
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
    var pager = $("#detailedList_OutOfStockInformation_table").datagrid("getPager");
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

//导出excel

function exportExcel() {
    location.href = "outOfStockInformation.html?act=exportOutOfStockInformation&detailedListType="+2+
        "&detailedListCode=" + $("input[name$='OutOfStock_detailedListCode']").val() +
        "&detailedListName=" + $("input[name$='OutOfStock_detailedListName']").val() +
        "&staffName=" + $("input[name$='OutOfStock_staffName']").val() +
        "&receivingDelivererPersonName=" + $("input[name$='OutOfStock_receivingDelivererPersonName']").val() +
        "&receivingDelivererPersonPhone=" + $("input[name$='OutOfStock_receivingDelivererPersonPhone']").val() +
        "&receivingDelivererPersonAddress=" + $("input[name$='OutOfStock_receivingDelivererPersonAddress']").val() +
        "&status=" + $("input[name$='OutOfStock_status']").val();

}
/*重新加载表格*/

function loadDatagrid_OutOfStock() {
    $("#detailedList_OutOfStockInformation_table").datagrid("load", {
        detailedListCode: $("input[name$='OutOfStock_detailedListCode']").val(),
        detailedListName: $("input[name$='OutOfStock_detailedListName']").val(),
        staffName: $("input[name$='OutOfStock_staffName']").val(),
        receivingDelivererPersonName: $("input[name$='OutOfStock_receivingDelivererPersonName']").val(),
        receivingDelivererPersonPhone: $("input[name$='OutOfStock_receivingDelivererPersonPhone']").val(),
        receivingDelivererPersonAddress: $("input[name$='OutOfStock_receivingDelivererPersonAddress']").val(),
        status: $("input[name$='OutOfStock_status']").val()
    });
}

//查询

function findAction_OutOfStock() {
    loadDatagrid_OutOfStock();
}

function enter(event) {
    if (event.which == "13") {
        findAction();
    }
}


/* * 清空查询条件*/

function doClearAction_OutOfStock() {
    $('#detailedList_OutOfStockInformation_form_id').form('clear');
    loadDatagrid_OutOfStock();
}

/*确定出库*/
function DetermineOutOfStock(index) {
    var row=$("#detailedList_OutOfStockInformation_table").datagrid('getRows');
    console.log("index; " + row[index].detailedListId);
    var DetailedListInformation = {};
    DetailedListInformation.id = row[index].id;
    DetailedListInformation.detailedListId =row[index].detailedListId;
    DetailedListInformation.status = 7,
        $.messager.confirm('提示', '确认出库?', function (r) {
            if (r) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    url: "outOfStockInformation.html?act=determineOutOfStock",
                    data: JSON.stringify(DetailedListInformation),
                    success: function (json) {
                        if (json.state == 0) {
                            loadDatagrid_OutOfStock();
                            MessagerShow(json.message)
                        } else {
                            MessagerShow(json.message)
                        }
                    },
                    error: function () {
                        MessagerShow("服务器无响应")
                    }
                })
            }
        })
}

//取消入库
function Cancel_Applicant_OutOfStock(index) {
    var row=$("#detailedList_OutOfStockInformation_table").datagrid('getRows');
    console.log("index; " + row[index].detailedListId);
    var DetailedListInformation = {};
    DetailedListInformation.id = row[index].id;
    DetailedListInformation.detailedListId =row[index].detailedListId;
    DetailedListInformation.status = 6,
        $.messager.confirm('提示', '确认取消出库申请单?', function (r) {
            if (r) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    url: "outOfStockInformation.html?act=CancelApplicationForm",
                    data: JSON.stringify(DetailedListInformation),
                    success: function (json) {
                        if (json.state == 0) {
                            loadDatagrid_OutOfStock();
                            MessagerShow(json.message)
                        } else {
                            MessagerShow(json.message)
                        }
                    },
                    error: function () {
                        MessagerShow("服务器无响应")
                    }
                })
            }
        })
}


/* 父弹窗开始*/

$obj = $("#father_CommodityInformationOutOfStock_list_table");
$obj.datagrid({
    columns: [
        [ //显示的列
            {
                field: 'id',
                title: '序号',
                width: 100,
                sortable: true,
                checkbox: true
            }, {
            field: ' commodityId',
            title: '商品ID',
            width: 100,
            sortable: true,
            hidden: 'true'
        }, {
            field: ' commodityStorageType',
            title: '存储类型',
            width: 100,
            sortable: true,
            hidden: 'true'
        }, {
            field: 'commodityUnitPrice',
            title: '商品价格',
            width: 100,
            sortable: true,
            hidden: 'true'
        }, {
            field: 'commodityCode',
            title: '商品编号',
            width: 100,
            align: 'center',
            sortable: true,
        }, {
            field: 'commodityName',
            title: '商品名称',
            width: 100,
            align: 'center',
        }, {
            field: 'count',
            title: '商品数量',
            width: 100,
            align: 'center',

        },
        ]
    ],
    toolbar: '#tb', //表格菜单
    fit: true,
    fitColumns: true,
    loadMsg: '加载中...', //加载提示
    rownumbers: true, //显示行号列
    singleSelect: true, //是允许选择一行
    onDblClickRow: AddCommunityInformation_sun_OutOfStock_dialog,//双击
    /*onClickRow: ,*/
    queryParams: { //在请求数据是发送的额外参数，如果没有则不用写
    },
    onLoadSuccess: function (data) {
    },
    rowStyler: function (index, row) {
    }
});

function AddDetailedListOutOfStockInformation() {
    $("#detailedListOutOfStock-dialog-from-id").form('clear');
    $('#father_CommodityInformationOutOfStock_list_table').datagrid('loadData', {total: 0, rows: []});
    $("#Add_detailedListOutOfStock-dialog").dialog("open")
}

//清空
function doClearActionDetailedListOutOfStockDialog() {
    $('#Add_detailedListOutOfStock-dialog-from-id').form('clear');
    $('#father_CommodityInformationOutOfStock_list_table').datagrid('loadData', {total: 0, rows: []});
}

//取消
function CloseDetailedListOutOfStockDialog() {
    $('#applicant-detailedListOutOfStock-dialog-from-id').form('clear');
    $('#father_CommodityInformationOutOfStock_list_table').datagrid('loadData', {total: 0, rows: []});
    $('#Add_detailedListOutOfStock-dialog').window('close')
}


// 添加 一行商品
function addCommodityInformationInformation_OutOfStock() {
    $('#father_CommodityInformationOutOfStock_list_table').datagrid('appendRow', {
    });
    var rows = $("#father_CommodityInformationOutOfStock_list_table").datagrid('getRows');
    AddCommunityInformation_sun_OutOfStock_dialog(rows.length - 1, rows)
}

//统计商品数量
function CountCommodityQuantity_OutOfStock() {
    var Count = 0;
    var rows = $("#father_CommodityInformationOutOfStock_list_table").datagrid('getRows');
    for (var i = 0; i < rows.length; i++) {
        Count = Count + parseInt(rows[i].count);
    }
    return Count;
}

//计算清单总价
function TotalSum_OutOfStock() {
    var totalSum = new bigDecimal('0');
    var rows = $("#father_CommodityInformationOutOfStock_list_table").datagrid('getRows');
    for (var i = 0; i < rows.length; i++) {
        var n1 = new bigDecimal(rows[i].count);
        var n2 = new bigDecimal(rows[i].commodityUnitPrice);
        var product = n1.multiply(n2);
        totalSum = totalSum.add(product);
    }
    console.log("计算清单总价:  " + totalSum.getPrettyValue())
    return totalSum.getPrettyValue();
}

// 删除
function removeit() {
    //获取所选中行
    var row = $("#father_CommodityInformationOutOfStock_list_table").datagrid("getSelected");
    if (row == null || row.length == 0) {
        MessagerShow("请选中一行数据.....");
        return;
    } else {
        $.messager.confirm('提示', '确认删除?', function (r) {
            if (r) {
                $obj.datagrid('deleteRow', $("#father_CommodityInformationOutOfStock_list_table").datagrid("getRowIndex", row));
            }
        });
    }
}

function submitAddDetaildListOutOfStock() {
    var rows = $("#father_CommodityInformationOutOfStock_list_table").datagrid('getRows');
    //先清除为空的 行
    for (var i = rows.length - 1; i >= 0; i--) {
        var index = $('#father_CommodityInformationOutOfStock_list_table').datagrid('getRowIndex', rows[i]);
        if (rows[i].commodityId == null) {
            $('#father_CommodityInformationOutOfStock_list_table').datagrid('deleteRow', index);
        }
    }
    if ($("#father_CommodityInformationOutOfStock_list_table").datagrid('getRows').length > 0
        && $('#OutOfStock_detailedListName').val() != null && $('#OutOfStock_detailedListName').val() != undefined && $('#OutOfStock_detailedListName').val() != ""
        && $('#OutOfStock_receivingDelivererPersonName').val() != null && $('#OutOfStock_receivingDelivererPersonName').val() != undefined && $('#OutOfStock_receivingDelivererPersonName').val() != ""
        && $('#OutOfStock_receivingDelivererPersonPhone').val() != null && $('#OutOfStock_receivingDelivererPersonPhone').val() != undefined && $('#OutOfStock_receivingDelivererPersonPhone').val() != ""
        && $('#OutOfStock_receivingDelivererPersonAddress').val() != null && $('#OutOfStock_receivingDelivererPersonAddress').val() != undefined && $('#OutOfStock_receivingDelivererPersonAddress').val() != ""
    ) {
        /*  var totalSum = new bigDecimal(TotaSum());*/
        //封装成一个对象 方便传输
        var detailedListCommodityInformationBean = {
            commodityDetaidLikeBeanList: $('#father_CommodityInformationOutOfStock_list_table').datagrid("getRows"),
            detailedListName: $('#OutOfStock_detailedListName').val(),
            receivingDelivererPersonName: $('#OutOfStock_receivingDelivererPersonName').val(),
            receivingDelivererPersonPhone: $('#OutOfStock_receivingDelivererPersonPhone').val(),
            receivingDelivererPersonAddress: $('#OutOfStock_receivingDelivererPersonAddress').val(),
            totalQuantity: parseInt(CountCommodityQuantity_OutOfStock()),
            totalSum: TotalSum_OutOfStock(),
        };
        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "DetailedListInformation.html?act=ApplicantOutOfStockEntryInformation",
            data: JSON.stringify(detailedListCommodityInformationBean),
            success: function (json) {
                if (!json.state) {
                    MessagerShow(json.message);
                    loadDatagrid_OutOfStock()
                    $('#applicant-detailedListOutOfStock-dialog-from-id').form('clear');
                    $('#father_CommodityInformationOutOfStock_list_table').datagrid('loadData', {total: 0, rows: []});
                    $('#Add_detailedListOutOfStock-dialog').window('close')
                } else {
                    MessagerShow(json.message);
                }
            },
            error: function () {
                MessagerShow("后台无响应");
            }
        })
    } else {
        MessagerShow("还有未填项");
    }
}

/*父弹出框结束*/


/*子弹窗 开始 Sun CommodityInformation */

function AddCommunityInformation_sun_OutOfStock_dialog(rowIndex, rowData) {
    //清空 子弹窗 CommodityInformation 表数据
    /*  $('#Sun_CommodityInformation_table_id').datagrid('loadData',{total:0,rows:[]});*/
    // 初始化表格 显示子 dialog
    Sun_CommodityInformation__OutOfStock_dialog(rowIndex, rowData);
    Sun_doClearAction();
}


/*弹出框 开始 Sun_CommodityInformation_dialog */
function Sun_CommodityInformation__OutOfStock_dialog(rowIndex, rowData) {
    index = rowIndex;
    var datagridDataOptions = {
        title: "",
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
        idField: "id",
        showPageList: true,
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
                title: "存储类型",
                field: "storageTemperature",
                width: 80,
                align: "right",
                hidden: 'true',
                sortable: true,
                order: "asc"
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
                width: 70,
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
                title: "商品型号",
                field: "commodityMn",
                width: 120,
                align: "right"
            }, {
                title: "商品价格",
                field: "commodityUnitPrice",
                width: 120,
                align: "right"
            },{
                title: "库存量",
                field: "count",
                width: 120,
                align: "right"
            }
        ]],
        url: "outOfStockInformation.html?act=CommodityInformation",
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
    $("#Sun_CommodityInformationOutOfStock_table_id").datagrid(datagridDataOptions);
    $.parser.parse($('.needParse').parent());

    var pager = $("#Sun_CommodityInformationOutOfStock_table_id").datagrid("getPager");
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
    $("#Sun_CommodityInformationOutOfStock_dialog").data("rowIndex", rowIndex, "rowData", rowData).dialog("open");
}


/* 子弹出框开始 选择商品弹窗  点击确定*/
function submitSun_CommodityInformationOutOfStock_dialog() {
    var row = $("#Sun_CommodityInformationOutOfStock_table_id").datagrid("getSelected");//在子选中一行数据
    if (row == null || row.length == 0) {
        $.messager.show({
            title: "温馨提示",
            msg: "请选中一行数据",
            timeout: 4000,
            showType: 'slide'
        });
        return;
    }
    var re = /^[0-9]+.?[0-9]*$/;   //判断字符串是否为数字
    //当前页里的数据行数 1开始
    var length = $("#father_CommodityInformationOutOfStock_list_table").datagrid('getRows').length;
    //总共有多少数据
    var total = $("#father_CommodityInformationOutOfStock_list_table").datagrid('getData').total;

    /*首先判断父table 中是否已经有此商品 Judge_FatherTable_HaveCommodity*/
    if (!Judge_FatherTable_HaveCommodity($("#father_CommodityInformationOutOfStock_list_table").datagrid('getRows'), length, row.commodityId)) {
        $.messager.prompt('温馨提示', '请输入存入数量:', function (count) {
            console.log("输入数量：  " + count)
            if (count) {
                if (!re.test(count)) {
                    $.messager.show({
                        title: "温馨提示",
                        msg: "请输入数字, 选中一行后再次点击确定",
                        timeout: 4000,
                        showType: 'slide',
                    });
                    return;
                }
                //查看库存是否足够

                if (row.count>=count) {
                    $('#father_CommodityInformationOutOfStock_list_table').datagrid('updateRow', {
                        index: index,
                        row: {
                            "id": row.id,
                            "commodityCode": row.commodityCode,
                            "commodityId": row.commodityId,
                            "commodityName": row.commodityName,
                            "commodityStorageType": row.commodityStorageType,
                            "count": count,
                            "commodityUnitPrice": row.commodityUnitPrice,
                        }
                    });
                    $("#Sun_CommodityInformationOutOfStock_dialog").dialog("close")
                    $('#find_Sun_CommodityInformation_form_id').form('clear');
                } else {
                    MessagerShow("仓库存储数量不够。。。 剩余商品数量： " + row.count + " 件")
                }


                //访问后台储位是否足够

            } else {
                MessagerShow("请您输入需要入库的数量.")
            }

        });
    } else {
        MessagerShow("商品已经被选中过")
        return;
    }
}

//判断父table中是否已经用此商品
function Judge_FatherTable_HaveCommodity(rows, length, commodityId) {
    for (var i = 0; i < length; i++) {
        if (rows[i].commodityId == commodityId) {
            return true;
        }
    }
    return false;
}


function findAction_find_Sun_CommodityInformation_form() {
    loadDatagrid_Sun_CommodityInformation_form();
}

/*重新加载表格*/
function loadDatagrid_Sun_CommodityInformation_form() {
    $("#Sun_CommodityInformationOutOfStock_table_id").datagrid({
        queryParams: {
            commodityName: $("input[name$='Sun_commodityName']").val(),
            supplierName: $("input[name$='Sun_supplierName']").val(),
            supplierPhone: $("input[name$='Sun_supplierPhone']").val(),
            commodityMn: $("input[name$='Sun_commodityMn']").val(),
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

/*清空搜索框*/
function Sun_doClearAction() {
    $("#find_Sun_CommodityInformation_form_id").form('clear');
    loadDatagrid_Sun_CommodityInformation_form();
}

/*子 弹出框结束*/

/*公共 方法*/

//提示
function MessagerShow(messager) {
    $.messager.show({
        title: "温馨提示",
        msg: messager,
        timeout: 4000,
        showType: 'slide'
    })
}

