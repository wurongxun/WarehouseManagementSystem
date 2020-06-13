$(function () {
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
        url: "role.html?act=list",
        toolbar: [    /*"#role-tool", */
            {
                text: '增加', iconCls: 'icon-add', handler: function () {
                    AddRole();
                }
            },
            {
                text: '修改', iconCls: 'icon-bookmark-edit', handler: function () {
                    RoleUpdate();
                }
            },
            {
                text: '删除', iconCls: 'icon-remove', handler: function () {
                    deleteRole();
                }
            },
          /*  {
                text: '分配菜单', iconCls: 'icon-application-cascade', handler: function () {
                    DistributionMenu();
                }
            },*/
            {
                text: '刷新', iconCls: 'icon-reload', handler: function () {
                    loadDatagrid_findActionRoleInformation();
                }
            },
            /*{
                text: '导出', iconCls: 'icon-save', handler: function () {
                    $(dg).treegrid('reload');
                }
            }, */'-'],
        columns: [[
            {
                title: "ID",
                field: "id",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
                hidden:true,
            }, {
                title: "角色名",
                field: "roleName",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
            }, {
                title: "备注",
                field: "remark",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
            }, {
                title: "状态",
                field: "status",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
                formatter: function (value, row, index) {
                    /*  return "<a href='javascript:;' onclick='editRow(event)'>编辑</a>&nbsp;&nbsp;<a href='javascript:;' onclick='deleteRow(event)'>删除</a>";*/
                    if (value == 1) {
                        return "启用"
                    } else if (value == 2) {
                        return "停用";
                    } else {
                        return "存在错误";
                    }
                }
            },]],
        loadFilter: function (data) {
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows,
                "footer": data.state != 0 ? [] : data.data.footer
            }
        }
    }
    $("#role_table").datagrid(datagridDataOptions)


    // 初始化分页器
    var pager = $("#role_table").datagrid("getPager");
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
function loadDatagrid_findActionRoleInformation() {
    $("#role_table").datagrid("load", {
        roleName: $("input[name$='roleName']").val(),
        status: $("input[name$='find_status']").val(),
        find: "find",
    });
}

function loadDatagrid_findActionRoleInformation2() {
    $("#role_table").datagrid("load", {});
}

//查询

function findActionRoleInformation() {
    loadDatagrid_findActionRoleInformation();
}

function doClearAction() {
    $('#detailedList_RoleInformation_form_id').form('clear');
    loadDatagrid_findActionRoleInformation2();
}
/*var pager = $("#role_table").datagrid("getPager");
var pagerDataOptions = {
    buttons : [{
        iconCls : "fa fa-file-excel-o",
        handler : function() {
            //exportExcel();
        }
    }, "-" ],
    layout : [ "list", "sep", "first", "prev", "links", "next", "last",
        "sep", "refresh" ]
};
pager.pagination(pagerDataOptions);*/
/*打开添加窗口*/
function AddRole() {
    $("#role-dialog").dialog("open")
}

/*提交按钮*/
function submitRoleFrom() {
    $.ajax({
        url: "role.html?act=edit",
        data: $("#role-from").serialize(),
        method: "post",
        success: function (data) {
            if (!data.state) {
                MessagerShow(data.message);
                $("#role-dialog").dialog("close");
                $("#role_table").datagrid("load", {});//重新加载表格*/
            } else {
                MessagerShow(data.message);
            }
        },
        error: function () {
            MessagerShow("发生异常");
        }
    })
}

/*关闭添加窗口*/
function AddRoleClose() {
    $("#role-dialog").dialog("close");
}


function RoleUpdate() {
    var row = $("#role_table").datagrid("getSelected");//选中一行数据
    if (row == null || row.length == 0) {
        return;
    }
    $("#role-dialog").dialog({
        onLoad: function () {
            $("#role-from").form("load", {
                id: row.id,
                roleName: row.roleName,
                remark: row.remark,
                status: row.status,
            })
        }
    }).dialog("open")
}

function DistributionMenu() {
    var row = $("#role_table").datagrid("getSelected");//选中一行数据
    if (row == null || row.length == 0) {
        MessagerShow("请选中一行数据");
        return;
    }

    /*展开树之前先查询当前用户所拥有的权限*/
    $.ajax({
        url: "role.html?act=role_menu",
        data: {
            roleId:row.id
        },
        success: function (dataMenuId) {
            /*加载树*/
            $("#role-menu-tree").tree({/*初始化树*/
                url: "role.html?act=menu_list",
                method: "post",
                checkbox: true,
                onLoadSuccess: function (node, data) {
                    for (var i = 0; i < dataMenuId.length; i++) {
                        /* 对树节点选择 但当选择父节点时 子节点也会被选上
                        var roMenu=$(#role-menu-tree).tree("find",dataMenuId[i])  */

                        /*解决办法*/
                        var nodeMenu = $("#role-menu-tree").tree("find", dataMenuId[i])
                        if ($("#role-menu-tree").tree("getChildren", nodeMenu.target).length < 1) {/*判断是否有叶子节点*/
                            $("#role-menu-tree").tree("check", nodeMenu.target);
                        }
                    }
                    $("#role-menu-dialog").dialog("open")
                }

            })
        }
    })


}

function MenuClose() {
    $("#role-menu-dialog").dialog("close")
}

function submitRoleMenu() {
    var row = $("#role_table").datagrid("getSelected");//拿到被选中的角色
    var menuIds = new Array();
    /*全选中的
    $("#role-menu-tree").tree("getChecked").each(function (o,node) {
        menuIds[i]=node["id"];
    })*/
    /*['checked','indetermina'  半选中  百度*/
    $($("#role-menu-tree").tree("getChecked", ['checked', 'indeterminate'])).each(function (i, node) {
        menuIds[i] = node["id"];
    })
    /*ajax 提交*/
    $.ajax({
        url: "role.html?act=assign_menu",
        data: {
            roleId: row["id"],
            menuIds: menuIds.join(",")},
        method: "post",
        success: function (data) {
            if (!data.state) {
                MessagerShow(data.message)
                MenuClose();
            } else {
                MessagerShow(data.message)
            }
        },
        error: function () {
            MessagerShow("服务器无响应");
        }
    })
}

function deleteRole() {
    var row = $("#role_table").datagrid("getSelected");//选中一行数据
    if (row == null || row.length == 0) {
        MessagerShow("请选中一行数据");
        return;
    }
    $.ajax({
        url:"RoleInformation.html?act=deleteRole",
        data:{
            id:row.id
        },
        success:function (data) {
            if (!data.state){
                doClearAction();
                MessagerShow(data.message)
            } else {
                MessagerShow(data.message)
            }

        },
        error:function () {
            MessagerShow("服务器无响应");
        }
    })
}