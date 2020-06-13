var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
$(function () {
    $("#user_table").datagrid({
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
        url: "userInformation.html?act=user_list",
        loadFilter: function (data) {
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows,
                "footer": data.state != 0 ? [] : data.data.footer
            }
        },
        toolbar: [    /*"#role-tool", */
            {
                text: '增加', iconCls: 'icon-add', handler: function () {
                    UserAdd();
                }
            },
            /*{ text: '修改', iconCls: 'icon-bookmark-edit', handler: function () { UpdateUser(); } },*/
            {
                text: '删除', iconCls: 'icon-remove', handler: function () {
                    deleteUser();
                }
            },
            {
                text: '分配角色', iconCls: 'icon-edit', handler: function () {
                    DistributionRole();
                }
            },
            {
                text: '刷新', iconCls: 'icon-reload', handler: function () {
                    doClearAction();
                }
            }, {
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
                hidden: 'true',
            }, {
                title: "用户名",
                field: "userName",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
            }, {
                title: "Email",
                field: "email",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
            }, {
                title: "年龄",
                field: "age",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
            }, {
                title: "性别",
                field: "sex",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
                formatter: function (value, row, index) {
                    /*  return "<a href='javascript:;' onclick='editRow(event)'>编辑</a>&nbsp;&nbsp;<a href='javascript:;' onclick='deleteRow(event)'>删除</a>";*/
                    if (value == 1) {
                        return "男"
                    } else if (value == 2) {
                        return "女";
                    } else {
                        return "存在错误";
                    }
                }
            }, {
                title: "工号",
                field: "jobNumber",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
            },/*{
                title: "部门",
                field: "department",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
            },*/{
                title: "电话",
                field: "phone",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
            }, {
                title: "地址",
                field: "address",
                width: 120,
                align: "right",
                sortable: true,
                order: "asc",
            },]]
    })
    // 初始化分页器
    var pager = $("#user_table").datagrid("getPager");
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
    location.href = "userInformation.html?act=exportUserInformation&find='find'"+
        "&userName=" + $("input[name$='userName']").val() +
        "&department=" + $("input[name$='department']").val() +
        "&jobNumber=" + $("input[name$='jobNumber']").val() +
        "&phone=" + $("input[name$='phone']").val() +
        "&age=" + $("input[name$='age']").val() +
        "&userCode=" + $("input[name$='userCode']").val() +
        "&email=" + $("input[name$='email']").val() +
        "&sex=" + $("input[name$='sex']").val() +
        "&status=" + $("input[name$='status']").val();

}
/*重新加载表格*/
function loadDatagrid_findActionUserInformation() {
    $("#user_table").datagrid("load", {
        userName: $("input[name$='userName']").val(),
        /* userRole: $("input[name$='userRole']").val(),*/
        department: $("input[name$='department']").val(),
        jobNumber: $("input[name$='jobNumber']").val(),
        phone: $("input[name$='phone']").val(),
        age: $("input[name$='age']").val(),
        userCode: $("input[name$='userCode']").val(),
        email: $("input[name$='email']").val(),
        sex: $("input[name$='sex']").val(),
        status: $("input[name$='status']").val(),
        find: "find",
    });
}

function loadDatagrid_findActionUserInformation2() {
    $("#user_table").datagrid("load", {});
}

//查询

function findActionUserInformation() {
    loadDatagrid_findActionUserInformation();
}

function doClearAction() {
    $('#detailedList_UserInformation_form_id').form('clear');
    loadDatagrid_findActionUserInformation2();
}

function DistributionRole() {
    var row = $("#user_table").datagrid("getSelected");//选中一行数据
    if (row == null || row.length == 0) {
        MessagerShow("请选中一行数据");
        return;
    }
    /*  $('#role_table').datagrid('loadData', {total: 0, rows: []});*/
    $("#role_table").datagrid({
        url: "role.html?act=list",
        /*checkOnSelect: false,
        selectOnCheck: false,*/
        title: "",
        fit: true,
        border: true,
        singleSelect: true,
        fitColumns: true,
        pagination: true,
        striped: true,
        pageSize: 10,
        pageNumber: 1,
        pageList: [5, 10, 20, 30, 40, 50, 100, 500],
        idField: "id",
        showPageList: true,
        loadMsg: '数据正在加载,请耐心的等待...',
        columns: [[
            {title: '全选', field: 'ck', checkbox: true, sortable: false},  //添加checkbox
            {title: 'ID ', field: 'id', hidden: 'true', sortable: false},
            {title: '角色名 ', field: 'roleName', sortable: false},
            {title: '备注 ', field: 'remark', sortable: false},
            {
                title: '状态', field: 'status', sortable: false, formatter: function (val, row) {
                    if (val == 0) {
                        return "未启用";
                    }
                    if (val == 1) {
                        return "启用";
                    } else {
                        return "异常";
                    }
                }
            }
        ]],
        loadFilter: function (data) {
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows,
                "footer": data.state != 0 ? [] : data.data.footer
            }
        },
        onLoadSuccess: function (node, data) {
            var rowData = data;
            $.each(rowData, function (idx, val) {
                /*  for(var i=0;i<dataMenuId.length;i++){
                      if (val.id=dataMenuId[i]) {
                          $("#role_table").datagrid("selectRow",true );
                      }else {
                          $("#role_table").datagrid("selectRow",false );
                      }
                  }*/

            });

        }

    })
    $("#role-user-dialog").dialog("open");
}


/*角色选择弹窗*/
function RoledialogClose() {
    $("#role-user-dialog").dialog("close")
}

/*提交用户分配的角色*/
function submitRole() {
    var rowUser = $("#user_table").datagrid("getSelected");//拿到被选中的用户
    var rowRole = $("#role_table").datagrid("getSelected");//拿到被选中的角色
    /*ajax 提交*/
    $.ajax({
        url: basePath + "/user.html?act=assign_role",
        data: {
            userId: rowUser["id"],
            roleId: rowRole["id"]
        },
        method: "post",
        success: function (data) {
            //$("#role_table").dataTable().fnClearTable();
            if (!data.state) {

                MessagerShow(data.message);
                RoledialogClose();

            } else {
                MessagerShow(data.message);
            }
        },
        error: function () {
            MessagerShow("后台无响应");
        }
    })
}


function UserAdd() {
    document.getElementById("adduser_form").reset();
    validateUsername();
    $("#adduser_buttonemail_log").hide();
    $("#adduser_buttons_log").hide();
    $("#adduser_buttonsuser_log").hide();
    $("#adduser-dialog").dialog("open")
}

function isEmail() {
    var strEmail = $("#email").val();
    if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1) {
        $("#tishiemail").html("输入正确");
        $("#adduser_buttonemail_log").hide();
        return true;
    } else if (isNull(strEmail)) {
        $("#tishiemail").html("Email不能为空");
        $("#tishiemail").css("color", "red")
        $("#adduser_buttonemail_log").show();
        $("#adduser_buttons_submit").hide();
        return false;
    } else {
        $("#tishiemail").html("Email填写错误");
        $("#tishiemail").css("color", "red")
        $("#adduser_buttonemail_log").show();
        $("#adduser_buttons_submit").hide();
        return false;
    }
}

function validateUsername() {
    var username = $("#userName").val();
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

function user() {
    if (validate() && validateUsername() && isEmail()) {
        $("#adduser_buttons_submit").show();
    }
}

function submitAddUser() {

    var email = $("#email").val();
    var userName = $("#userName").val();
    var password = $("#password").val();
    console.log("   email" + email + "   password" + password);
    if (!isNull(userName) && !isNull(email) && !isNull(password)) {
        console.log("   email" + email + "   password" + password);
        $.ajax({
            url: basePath + "/user.html?act=go_adduser",
            data: {
                userName: $("#userName").val(),
                email: email,
                password: password,
                age: $("#age").val(),
                sex: $("#sex").val(),
                phone: $("#phone").val(),
                address: $("#address").val(),
            },
            success: function (data) {
                //$("#role_table").dataTable().fnClearTable();
                if (!data.state) {
                    MessagerShow(data.message);
                    doClearAction();
                    $("#adduser-dialog").dialog("close")
                } else {
                    MessagerShow(data.message);
                }
            },
            error: function () {
                alert("异常！");
            }
        })
    } else {
        alert("异常！輸入含有空值");
    }
}


/*删除用户*/
function deleteUser() {
    var row = $("#user_table").datagrid("getSelected");//选中一行数据
    if (row == null || row.length == 0) {
        MessagerShow("请选中一行数据")
        return;
    }

    $.ajax({
        url: "userInformation.html?act=deleteUser",
        data: {
            id: row.id,
        },
        success: function (data) {
            if (!data.state) {
                MessagerShow(data.message);
                doClearAction();
            } else {
                MessagerShow(data.message);
            }
        },
        error: function () {
            MessagerShow("服务器无响应");
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
