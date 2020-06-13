var localObj = window.location;

var contextPath = localObj.pathname.split("/")[1];

var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

function isNull( str ){
    if ( str == "" ) return true;
    var regu = "^[ ]+$";
    var re = new RegExp(regu);
    return re.test(str);
}
//修改密码
function validate() {
    var determinePassword = $("#determinePassword").val();
    var newPassword = $("#newPassword").val();
    console.log("newPassword "+newPassword+" oldPassword"+determinePassword);
    <!-- 对比两次输入的密码 -->
    if (newPassword == determinePassword && !(isNull(determinePassword)||isNull(newPassword))) {
        $("#tishi").html("两次密码相同");
        $("#tishi").css("color", "green");
        $("#submitUpPassword").show();
    }else if (isNull(determinePassword)||isNull(newPassword)){
        $("#tishi").html("密码不能为空");
        $("#tishi").css("color", "red")
        $("#submitUpPassword").hide();
    }
    else {
        $("#tishi").html("两次密码不相同");
        $("#tishi").css("color", "red")
        $("#submitUpPassword").hide();
    }
}
function submitUpPassword() {
    var userid = $("#user_id").val();
    var password = $("#newPassword").val();
    $.ajax({
        url:basePath+"/user.html?act=up_password",
        data:{
            userid: userid,
            password: password
        },
        method: "post",
        success: function (data) {
            if (data.status) {
                $.messager.show({
                    title:'提示',
                    msg:'密码修改成功',
                    timeout:5000,
                    showType:'slide'
                });
                Passwor_dialog_Close();
            } else {
                alert(data.message);
            }
        },
        error: function () {
            alert("异常！");
        }
    })
}
function upPassword() {
    $("#UpPassword-dialog").dialog("open")
}

function Passwor_dialog_Close() {
    $("#UpPassword-dialog").dialog("close")
}

//菜单
$(function () {
    $("#leftMenu").tree({
        onClick: function (node) {
            if ($("#leftMenu").tree("isLeaf", node.target)) {
                var tabs = $("#tabs");
                var tab = tabs.tabs("getTab", node.text)
                if (tab) {
                    tabs.tabs("select", node.text)
                } else {
                    tabs.tabs("add", {
                        title: node.text,
                        iconCls: node.iconCls,
                        href: node.url,
                        closable: true
                    })
                }

            }
        },
    })
})

function createFrame(url) {
    var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
    return s;
}

function tabClose() {
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function () { //jQuery双击方法
        var subtitle = $(this).text();
        $('#tabs').tabs('close', subtitle);
    })
    /*为选项卡绑定右键*/
    $(".tabs-inner").bind('contextmenu', function (e) { //e这个参数对象封装鼠标坐标值
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle = $(this).text();

        $('#mm').data("currtab", subtitle); //jQuery方法，向被选元素附加数据
        $('#tabs').tabs('select', subtitle);
        return false;
    });
}

function tabCloseEven() {
    //刷新
    $('#mm-tabupdate').click(function () {
        var currTab = $('#tabs').tabs('getSelected');
        var url = $(currTab.panel('options').content).attr('src');
        if (url != undefined && currTab.panel('options').title != 'Home') {
            $('#tabs').tabs('update', {
                tab: currTab,
                options: {
                    content: createFrame(url)
                }
            })
        }
    })
    //关闭当前
    $('#mm-tabclose').click(function () {
        var currtab_title = $('#mm').data("currtab");
        $('#tabs').tabs('close', currtab_title);
    })
    //全部关闭
    $('#mm-tabcloseall').click(function () {
        $('.tabs-inner span').each(function (i, n) {  //i是循环index位置，n相当于this
            var t = $(n).text();
            if (t != 'Home') {
                $('#tabs').tabs('close', t);
            }
        });
    });
    //关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function () {
        var prevall = $('.tabs-selected').prevAll(); //jQuery遍历方法，prevAll() 获得当前匹配元素集合中每个元素的前面的同胞元素
        var nextall = $('.tabs-selected').nextAll();
        if (prevall.length > 0) {
            prevall.each(function (i, n) {  //i是当前循环次数
                var t = $(n).text();
                if (t != 'Home') {
                    $('#tabs').tabs('close', t);
                }
            });
        }
        if (nextall.length > 0) {
            nextall.each(function (i, n) {
                var t = $(n).text();
                if (t != 'Home') {
                    $('#tabs').tabs('close', t);
                }
            });
        }
        return false;
    });
    //关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function () {
        var nextall = $('.tabs-selected').nextAll();
        if (nextall.length == 0) {
            alert('后边没有啦~~');
            return false;
        }
        nextall.each(function (i, n) {
            var t = $(n).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });
    //关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function () {
        var prevall = $('.tabs-selected').prevAll();
        if (prevall.length == 0) {
            alert('到头了，前边没有啦~~');
            return false;
        }
        prevall.each(function (i, n) {
            var t = $(n).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });

    //退出
    $("#mm-exit").click(function () {
        $('#mm').menu('hide');  //隐藏menu菜单
    })
}

window.onload = function () {
    var div = document.querySelector("body");
    document.querySelector("#full").onclick = function () {
        //使用Chrome浏览器需要加上webkit
         div.webkitRequestFullScreen();
         div.requestFullscreen();
        //使用FireFox浏览器需要加上moz
         div.mozRequestFullScreen();

        //    所有浏览器都可以全屏，使用能力测试
        if (div.requestFullscreen) {
            div.requestFullscreen();
        } else if (div.webkitRequestFullScreen) {
            div.webkitRequestFullScreen();
        } else if (div.mozRequestFullScreen) {
            div.mozRequestFullScreen();
        } else if (div.msRequestFullScreen) {
            div.msRequestFullScreen();
        }
    }
    //    退出全屏,只能使用document实现
    document.querySelector("#cancelFull").onclick = function () {
        if (document.cancelFullscreen) {
            document.cancelFullscreen();
        } else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if (document.msCancelFullScreen) {
            document.msCancelFullScreen();
        }
    }
    //是否是全屏状态 也是使用document
   /* document.querySelector("#isFull").onclick = function () {
        if (document.fullscreenElement || document.webkitFullscreenElement || document.mozFullScreenElement || document.msFullscreenElement) {
            alert(true);
        } else {
            alert(false);
        }
    }*/

}
function MessagerShow(messager) {
    $.messager.show({
        title: "温馨提示",
        msg: messager,
        timeout: 4000,
        showType: 'slide'
    })
}