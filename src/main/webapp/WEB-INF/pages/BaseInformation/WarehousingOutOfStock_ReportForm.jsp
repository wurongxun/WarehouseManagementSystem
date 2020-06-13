<%--
  Created by IntelliJ IDEA.
  User: WuRongXun
  Date: 2020/4/2
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript"
        src="<%=basePath%>/static/js/InformationManagement/WarehousingOutOfStock_ReportForm.js"></script>
<style>
    .class1{float: left;}
</style>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div class="class1" id="WarehousingOutOfStock_ReportForm" style="width:100%;height:100%;"></div>
<%--<div class="class1"  id="main2" style="width:50%;height:100%;"></div>--%>

