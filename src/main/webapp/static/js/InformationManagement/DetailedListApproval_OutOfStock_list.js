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
        singleSelect: false,
        idField: "id",
        showPageList: true,
        toolbar: [
            {
                text: '刷新', iconCls: 'icon-reload', handler: function () {
                    findAction_ApprovalOutOfStock();
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
            }, {
                title: "detailedListId",
                field: "detailedListId",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
                hidden: 'true'
            }, {
                title: "申请人ID",
                field: "applicantId",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
                hidden: 'true'
            },{
                title: "staffId",
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
                width: 100,
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
                    } else {
                        return "数据存在错误";
                    }
                }
            }, {
                title: '操作',
                field: "operation",
                width: 120,
                align: 'center',
                formatter: function (value, row, index) {
                    var str = '<a  name="view" class="easyui-linkbutton"  onclick="Approval_OK_OutOfStock('
                        + index + ')">审核通过</a>'
                        + ' <a name="view2" class="easyui-linkbutton"  onclick="Approval_NO_OutOfStock(' + index + ')">审核不通过</a>';
                    return str;
                },
            }
        ]],
        url: "detailedListApprovalController.html?act=list&detailedListType=2&status=2",
        method: "GET",
        onClickRow: function () {
            $('#detailedListApproval_OutOfStock_table').datagrid('clearSelections');//禁止选中行
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
                iconCls: 'icon-no',
                height: '18px'
            });
        },
        loadFilter: function (data) {
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows,
                "footer": data.state != 0 ? [] : data.data.footer
            }
        }
    };
    $("#detailedListApproval_OutOfStock_table").datagrid(datagridDataOptions);
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
    var pager = $("#detailedListApproval_OutOfStock_table").datagrid("getPager");
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

/*重新加载表格*/

function loadDatagrid_ApprovalOutOfStock() {
    $("#detailedListApproval_OutOfStock_table").datagrid("load", {
        detailedListCode: $("input[name$='ApprovalOutOfStock_detailedListCode']").val(),
        detailedListName: $("input[name$='ApprovalOutOfStock_detailedListName']").val(),
        staffName: $("input[name$='ApprovalOutOfStock_staffName']").val(),
        receivingDelivererPersonName: $("input[name$='ApprovalOutOfStock_receivingDelivererPersonName']").val(),
        receivingDelivererPersonPhone: $("input[name$='ApprovalOutOfStock_receivingDelivererPersonPhone']").val(),
        receivingDelivererPersonAddress: $("input[name$='ApprovalOutOfStock_receivingDelivererPersonAddress']").val(),
    });
}

//查询

function findAction_ApprovalOutOfStock() {
    loadDatagrid_ApprovalOutOfStock();
}

function enter(event) {
    if (event.which == "13") {
        findAction_ApprovalOutOfStock();
    }
}


/* * 清空查询条件*/

function doClearApprovalOutOfStock() {
    $('#DetailedListApproval_ApprovalOutOfStock_list_form_id').form('clear');
    loadDatagrid_ApprovalOutOfStock();
}

function Approval_OK_OutOfStock(index) {
    var rows = $("#detailedListApproval_OutOfStock_table").datagrid('getRows');
    var DetailedListInformation = {};
    DetailedListInformation.id = rows[index].id;
    DetailedListInformation.detailedListId = rows[index].detailedListId;
    DetailedListInformation.applicantId = rows[index].applicantId;
    DetailedListInformation.status = 3,
        $.messager.confirm('提示', '确认审核通过?', function (r) {
            if (r) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    url: "detailedListApprovalController.html?act=detailedListApproval_OutOfStock",
                    data: JSON.stringify(DetailedListInformation),
                    success: function (json) {
                        if (json.state == 0) {
                            loadDatagrid_ApprovalOutOfStock();
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

function Approval_NO_OutOfStock(index) {
    var rows = $("#detailedListApproval_OutOfStock_table").datagrid('getRows');
    var DetailedListInformation = {};
    DetailedListInformation.id = rows[index].id;
    DetailedListInformation.detailedListId = rows[index].detailedListId;
    DetailedListInformation.applicantId = rows[index].applicantId;
    DetailedListInformation.status = 5,
        $.messager.confirm('提示', '确认审核不通过?', function (r) {
            if (r) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    url: "detailedListApprovalController.html?act=detailedListApproval_OutOfStock",
                    data: JSON.stringify(DetailedListInformation),
                    success: function (json) {
                        if (json.state == 0) {
                            loadDatagrid_ApprovalOutOfStock();
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

function MessagerShow(messager) {
    $.messager.show({
        title: "温馨提示",
        msg: messager,
        timeout: 4000,
        showType: 'slide'
    })
}

