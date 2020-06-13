<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/" ;
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title >仓库管理系统</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.7.0/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.7.0/css/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/login/login.css">
    <style>
        body {
            background-size: 100% 100%;
            font-family: "华文细黑";
            background: url('<%=basePath%>static/images/bg5.jpg')  no-repeat fixed center ;
            opacity:1;
        }
    </style>
    <script src="<%=basePath%>jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript">
        function changeCode() {
            var img = document.getElementById("_img");
            img.src = "getVerifyCode?" + new Date().getTime();
        }
    </script>
    <script type="text/javascript">
        window.onload = function () {
            var name = document.getElementById("Email");
            var pswd = document.getElementById("Password");
            var oForm = document.getElementById("myForm");
            var rember = document.getElementById("rember");
            //  var cuser = document.cookie = "user"+"="+name.value+";";
            //   var cpswd = document.cookie = "password"+"="+pswd.value+";";
            //页面初始化时，如果帐号密码cookie存在则填充
            if (getCookie('Email') && getCookie('Password')) {
                name.value = getCookie('Email');
                pswd.value = getCookie('Password');
                rember.checked = true;
            }
            //复选框勾选状态发生改变时，如果未勾选则清除cookie
            rember.onchange = function () {
                if (!this.checked) {
                    delCookie('Email');
                    delCookie('Password');
                }
                // else{
                //     tips.style.display = "black";
                // }
            }
            //表单提交事件触发时，如果复选框是勾选状态则保存cookie
            oForm.onsubmit = function () {
                if (rember.checked) {

                    setCookie('Email', name.value, 2); //保存帐号到cookie，有效期2天
                    setCookie('Password', pswd.value, 2); //保存密码到cookie，有效期2天
                }
            }
        };

        //设置cookie
        function setCookie(name, value, day) {
            var date = new Date();
            date.setDate(date.getDate() + day);
            document.cookie = name + '=' + value + ';expires=' + date;
        };

        //获取cookie
        function getCookie(name) {
            var reg = RegExp(name + '=([^;]+)');
            var arr = document.cookie.match(reg);
            if (arr) {
                return arr[1];
            } else {
                return '';
            }
        };

        //删除cookie
        function delCookie(name) {
            setCookie(name, null, -1);
        }
    </script>

</head>

<body>

<div class="container wrap1" style="height:450px;">
    <h2 class="mg-b20 text-center" style="color: #d43f3a">仓库管理系统V1.01</h2>
    <div class="col-sm-8 col-md-5 center-auto pd-sm-50 pd-xs-20 main_content">
        <p class="text-center font16"  style="color: #d43f3a">用户登录</p>
        <form action="<%=basePath%>login.html" method="POST" id="myForm">
            <div class="form-group mg-t20">
                <font color="red">${error}</font>
                <i class="icon-user icon_font"></i>
                <!-- <input type="email" name="userName" class="login_input" id="Email1" placeholder="请输入用户名" /> -->
                <input type="email" name="email" class="login_input" id="Email" placeholder="默认账号boos@qq.com"/>
            </div>
            <div class="form-group mg-t20">
                <i class="icon-lock icon_font"></i>
                <input type="password" name="password" class="login_input" id="Password" placeholder="默认密码123"/>
            </div>
            <%-- <img src="<%=basePath%>/ValidateColorServlet" onclick="this.src=this.src+'?'+Math.random()" id="img"> <a href="#" onclick="document.getElementById('img').onclick()">看不清换一张</a><br/>
             <input type="text" name="yanzhenma" class="login_input"  id="Password2" placeholder="请输入验证码(不区分大小写)" />
 --%>
            <div class="checkbox mg-b25">
                <label>
                    <input type="checkbox" name="rember" id="rember"/>记住我的登录信息
                </label>
            </div>
            <button class="login_btn" id="dl">登 录</button>
        </form>
    </div><!--row end-->
</div><!--container end-->
<div class="fixed" style="text-align:center;">
    <p><a class="aaa" id="jgwab" target="_blank" style="color: red" href="" >备案号:&nbsp; 黔ICP备案/许可编号19009911号</a>&nbsp;&nbsp;
    <a class="aaa" id="jgab" target="_blank"  href="https://106.54.109.49/svn/Rongxun/Shiro04">查看我的项目代码</a>
</p>
</div>
</body>
</html>