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
                text: '申请入库', iconCls: 'icon-add', handler: function () {
                    AddDetailedListWarehousingInformation();
                }
            },
          /*  {
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
                    findAction();
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
                title: "入库单编号",
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
                title: "入库单名称",
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
                title: "送货人名",
                field: "receivingDelivererPersonName",
                width: 80,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "送货人电话",
                field: "receivingDelivererPersonPhone",
                width: 80,
                align: "left",
                sortable: true,
                order: "asc"
            }, {
                title: "送货人地址",
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
                    } else if (value == 1) {
                        return "入库成功";
                    } else if (value == 3) {
                        return "审核成功";
                    } else if (value == 4) {
                        return "等待入库";
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
                        var DetermineWarehousing = '<a href="javascript:void(0)" name="view" onclick="DetermineWarehousing(\'' + index + '\')" >确定入库</a>';
                        var Cancel_Applicant = '<a href="javascript:void(0)" color="red" name="view2" onclick="Cancel_Applicant(\'' + index + '\')" >取消申请</a>';
                        if (row.status==3) {
                            return DetermineWarehousing;
                        } else if (row.status == 2) {
                            return Cancel_Applicant;
                        } else {
                            return '';
                        }
                    }

            },
        ]],
        url: "DetailedListInformation.html?act=list&detailedListType=1",
        method: "GET",
        loadFilter: function (data) {
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows,
                "footer": data.state != 0 ? [] : data.data.footer
            }
        },
        onLoadSuccess: function (data) {
            $("a[name='view']").linkbutton({
                plain: false,
                iconAlign: 'left',
                iconCls: 'icon-ok',
                height: '18px'
            });
            $("a[name='view2']").linkbutton({
                plain: false,
                iconAlign: 'left',
                iconCls: 'icon-tip',
                height: '18px'
            });
        },
    };
    $("#detailedList_WarehousingInformation_table").datagrid(datagridDataOptions);
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
    var pager = $("#detailedList_WarehousingInformation_table").datagrid("getPager");
    var pagerDataOptions = {
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
        buttons: [{
            iconCls: "fa fa-file-excel-o",
            handler: function () {
               /* exportExcel()*/
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
    location.href = "warehousingEntryInformation.html?act=exportWarehousingEntryInformation&detailedListType="+1+
        "&detailedListCode=" + $("input[name$='detailedListCode']").val() +
        "&detailedListName=" + $("input[name$='detailedListName']").val() +
        "&staffName=" + $("input[name$='staffName']").val() +
        "&receivingDelivererPersonName=" + $("input[name$='receivingDelivererPersonName']").val() +
        "&receivingDelivererPersonPhone=" + $("input[name$='receivingDelivererPersonPhone']").val() +
        "&receivingDelivererPersonAddress=" + $("input[name$='receivingDelivererPersonAddress']").val() +
        "&status=" + $("input[name$='status']").val();

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
function loadDatagrid_findActionWarehousingEntry() {
    $("#detailedList_WarehousingInformation_table").datagrid("load", {
        detailedListCode: $("input[name$='Warehousing_detailedListCode']").val(),
        detailedListName: $("input[name$='Warehousing_detailedListName']").val(),
        staffName: $("input[name$='Warehousing_staffName']").val(),
        receivingDelivererPersonName: $("input[name$='Warehousing_receivingDelivererPersonName']").val(),
        receivingDelivererPersonPhone: $("input[name$='Warehousing_receivingDelivererPersonPhone']").val(),
        receivingDelivererPersonAddress: $("input[name$='Warehousing_receivingDelivererPersonAddress']").val(),
        status: $("input[name$='Warehousing_status']").val()
    });
}

//查询

function findActionWarehousingEntry() {
    loadDatagrid_findActionWarehousingEntry();
}

function enter(event) {
    if (event.which == "13") {
        findAction();
    }
}


/* * 清空查询条件*/

function doClearAction() {
    $('#detailedList_WarehousingInformation_form_id').form('clear');
    loadDatagrid_findActionWarehousingEntry();
}

/*确定入库*/
function DetermineWarehousing(index) {
    var row=$("#detailedList_WarehousingInformation_table").datagrid('getRows');
    console.log("index; " + row[index].detailedListId);
    var DetailedListInformation = {};
    DetailedListInformation.id = row[index].id;
    DetailedListInformation.detailedListId =row[index].detailedListId;
    DetailedListInformation.status = 1,
        $.messager.confirm('提示', '确认入库?', function (r) {
            if (r) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    url: "warehousingEntryInformation.html?act=determineWarehousing",
                    data: JSON.stringify(DetailedListInformation),
                    success: function (json) {
                        if (json.state == 0) {
                            loadDatagrid_findActionWarehousingEntry();
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
function Cancel_Applicant(index) {
    var row=$("#detailedList_WarehousingInformation_table").datagrid('getRows');
    console.log("index; " + row[index].detailedListId);
    var DetailedListInformation = {};
    DetailedListInformation.id = row[index].id;
    DetailedListInformation.detailedListId =row[index].detailedListId;
    DetailedListInformation.status = 6,
        $.messager.confirm('提示', '确认取消申请单?', function (r) {
            if (r) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    url: "warehousingEntryInformation.html?act=CancelApplicationForm",
                    data: JSON.stringify(DetailedListInformation),
                    success: function (json) {
                        if (json.state == 0) {
                            loadDatagrid_findActionWarehousingEntry();
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

$obj = $("#father_CommodityInformationWarehousing_list_table");
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
    onDblClickRow: AddCommunityInformation_sun_dialog,//双击
    /*onClickRow: ,*/
    queryParams: { //在请求数据是发送的额外参数，如果没有则不用写
    },
    onLoadSuccess: function (data) {
    },
    rowStyler: function (index, row) {
    }
});

function AddDetailedListWarehousingInformation() {
    $("#detailedListWarehousing-dialog-from-id").form('clear');
    $('#father_CommodityInformationWarehousing_list_table').datagrid('loadData', {total: 0, rows: []});
    $("#applicant_detailedListWarehousing-dialog").dialog("open")
}

//清空
function doClearActionDetailedListWarehousingDialog() {
    $('#applicant_detailedListWarehousing-dialog-from-id').form('clear');
    $('#father_CommodityInformationWarehousing_list_table').datagrid('loadData', {total: 0, rows: []});
    /*$('#applicant_detailedListWarehousing-dialog').datagrid('close')*/
}

//取消
function CloseDetailedListWarehousingDialog() {
    $('#applicant_detailedListWarehousing-dialog-from-id').form('clear');
    $('#father_CommodityInformationWarehousing_list_table').datagrid('loadData', {total: 0, rows: []});
    $('#applicant_detailedListWarehousing-dialog').datagrid('close')
}


// 添加 一行商品
function addWarehousingInformation() {
    $('#father_CommodityInformationWarehousing_list_table').datagrid('appendRow', {
        /*  commodityName: '小米手机',*/
    });
    var rows = $("#father_CommodityInformationWarehousing_list_table").datagrid('getRows');
    AddCommunityInformation_sun_dialog(rows.length - 1, rows)
}

//统计商品数量
function CountCommodityQuantity() {
    var Count = 0;
    var rows = $("#father_CommodityInformationWarehousing_list_table").datagrid('getRows');
    for (var i = 0; i < rows.length; i++) {
        Count = Count + parseInt(rows[i].count);
    }
    return Count;
}

//计算清单总价
function TotalSum() {
    var totalSum = new bigDecimal('0');
    var rows = $("#father_CommodityInformationWarehousing_list_table").datagrid('getRows');
    for (var i = 0; i < rows.length; i++) {
        var n1 = new bigDecimal(rows[i].count.toString());
        var n2 = new bigDecimal(rows[i].commodityUnitPrice.toString());
        var product = n1.multiply(n2);
        totalSum = totalSum.add(product);
    }
    console.log("计算清单总价:  " + totalSum.getPrettyValue())
    return totalSum.getPrettyValue();
}

// 删除
function removeit() {
    //获取所选中行
    var row = $("#father_CommodityInformationWarehousing_list_table").datagrid("getSelected");
    if (row == null || row.length == 0) {
        MessagerShow("请选中一行数据.....");
        return;
    } else {
        $.messager.confirm('提示', '确认删除?', function (r) {
            if (r) {
                $obj.datagrid('deleteRow', $("#father_CommodityInformationWarehousing_list_table").datagrid("getRowIndex", row));
            }
        });
    }
}

function submitAddDetaildListWarehousing() {
    var rows = $("#father_CommodityInformationWarehousing_list_table").datagrid('getRows');
    //先清除为空的 行
    for (var i = rows.length - 1; i >= 0; i--) {
        var index = $('#father_CommodityInformationWarehousing_list_table').datagrid('getRowIndex', rows[i]);
        if (rows[i].commodityId == null) {
            $('#father_CommodityInformationWarehousing_list_table').datagrid('deleteRow', index);
        }
    }
    if ($("#father_CommodityInformationWarehousing_list_table").datagrid('getRows').length > 0
        && $('#detailedListName').val() != null && $('#detailedListName').val() != undefined && $('#detailedListName').val() != ""
        && $('#receivingDelivererPersonName').val() != null && $('#receivingDelivererPersonName').val() != undefined && $('#receivingDelivererPersonName').val() != ""
        && $('#receivingDelivererPersonPhone').val() != null && $('#receivingDelivererPersonPhone').val() != undefined && $('#receivingDelivererPersonPhone').val() != ""
        && $('#receivingDelivererPersonAddress').val() != null && $('#receivingDelivererPersonAddress').val() != undefined && $('#receivingDelivererPersonAddress').val() != ""
    ) {
        /*  var totalSum = new bigDecimal(TotaSum());*/
        //封装成一个对象 方便传输
        var detailedListCommodityInformationBean = {
            commodityDetaidLikeBeanList: $('#father_CommodityInformationWarehousing_list_table').datagrid("getRows"),
            detailedListName: $('#detailedListName').val(),
            receivingDelivererPersonName: $('#receivingDelivererPersonName').val(),
            receivingDelivererPersonPhone: $('#receivingDelivererPersonPhone').val(),
            receivingDelivererPersonAddress: $('#receivingDelivererPersonAddress').val(),
            totalQuantity: parseInt(CountCommodityQuantity()),
            totalSum: TotalSum(),
        };
        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "DetailedListInformation.html?act=ApplicantWarehousingEntryInformation",
            data: JSON.stringify(detailedListCommodityInformationBean),
            success: function (json) {
                if (!json.state) {

                    MessagerShow(json.message);
                    $('#applicant_detailedListWarehousing-dialog-from-id').form('clear');
                    $('#father_CommodityInformationWarehousing_list_table').datagrid('loadData', {total: 0, rows: []});
                    $('#applicant_detailedListWarehousing-dialog').window('close');
                    loadDatagrid_findActionWarehousingEntry();
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

function AddCommunityInformation_sun_dialog(rowIndex, rowData) {
    //清空 子弹窗 CommodityInformation 表数据
    /*  $('#Sun_CommodityInformation_table_id').datagrid('loadData',{total:0,rows:[]});*/
    // 初始化表格 显示子 dialog
    Sun_CommodityInformation_dialog(rowIndex, rowData);
    Sun_doClearAction();
}


/*弹出框 开始 Sun_CommodityInformation_dialog */
function Sun_CommodityInformation_dialog(rowIndex, rowData) {
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
            }
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
    $("#Sun_CommodityInformation_table_id").datagrid(datagridDataOptions);
    $.parser.parse($('.needParse').parent());

    var pager = $("#Sun_CommodityInformation_table_id").datagrid("getPager");
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
    $("#Sun_CommodityInformation_dialog").data("rowIndex", rowIndex, "rowData", rowData).dialog("open");
}


/* 子弹出框开始 选择商品弹窗  点击确定*/
function submitSun_CommodityInformation_dialog() {
    var row = $("#Sun_CommodityInformation_table_id").datagrid("getSelected");//在子选中一行数据
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
    var length = $("#father_CommodityInformationWarehousing_list_table").datagrid('getRows').length;
    //总共有多少数据
    var total = $("#father_CommodityInformationWarehousing_list_table").datagrid('getData').total;

    if (!Judge_FatherTable_HaveCommodity($("#father_CommodityInformationWarehousing_list_table").datagrid('getRows'), length, row.commodityId)) {
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
                //返回相同存储类型数量
                var number1 = parseInt(IdenticalCommodityStorageTypeNumber($("#father_CommodityInformationWarehousing_list_table").datagrid('getRows'), length, row.commodityStorageType));
                var TotalQuantity = parseInt(count) + number1;
                console.log("总数   " + TotalQuantity)

                //访问后台储位是否足够
                $.ajax({
                    type: 'GET',
                    url: "shelfPositionInformation.html?act=find_ShelfPosition_Matching_Number&commodityStorageType=" + row.commodityStorageType,
                    success: function (json) {
                        if (json.data > TotalQuantity) {
                            $('#father_CommodityInformationWarehousing_list_table').datagrid('updateRow', {
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
                            $("#Sun_CommodityInformation_dialog").dialog("close")
                            $('#find_Sun_CommodityInformation_form_id').form('clear');
                        } else {
                            console.log("parseInt(json.data)  " + parseInt(json.data))
                            MessagerShow("仓库架位数量不够。。。 剩余架位： " + (parseInt(json.data) - number1) + " 位")
                        }
                    },
                    error: function () {
                        MessagerShow("访问后台储位 无响应")
                    }
                })
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

//返回同存储类型 一被选数量
function IdenticalCommodityStorageTypeNumber(rows, length, commodityStorageType) {
    var number = 0;
    for (var i = 0; i < length; i++) {
        if (rows[i].commodityStorageType == commodityStorageType && i != index) {
            console.log("rows[i].count " + rows[i].count)
            number = number + Number(rows[i].count);
        }
    }
    return parseInt(number);
}

function findAction_find_Sun_CommodityInformation_form() {
    loadDatagrid_Sun_CommodityInformation_form();
}

/*重新加载表格*/
function loadDatagrid_Sun_CommodityInformation_form() {
    $("#Sun_CommodityInformation_table_id").datagrid({
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

