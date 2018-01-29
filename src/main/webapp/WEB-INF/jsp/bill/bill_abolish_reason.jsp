<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %><!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resource/css/bill_abolish.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery-1.12.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
</head>
<body>
<div class="search"></div>
<div class="header">
    <table class="table table-bordered">
        <tr class="active">
            <th>订单号</th>
            <th>学号</th>
            <th>姓名</th>
            <th>房号</th>
            <th>宽带速率</th>
            <th>开通月份</th>
            <th>开通时间</th>
            <th>到期时间</th>
            <th>金额</th>
        </tr>
        <tr>
            <td>${studentOrderStr.orderId}</td>
            <td>${studentOrderStr.studentId}</td>
            <td>${studentOrderStr.name}</td>
            <td>${studentOrderStr.dorm}</td>
            <td>${studentOrderStr.speed}</td>
            <td>${studentOrderStr.lastTime}</td>
            <td>${studentOrderStr.printTime}</td>
            <td>${studentOrderStr.endTime}</td>
            <td>${studentOrderStr.money}</td>
        </tr>
    </table>
</div>
<div class="content">
    <div>作废原因:</div>
    <textarea class="form-control" cols="120" rows="60">${cancelReason}</textarea>
    <a href="${pageContext.request.contextPath}/admin/queryWorker.action"><button class="btn btn-primary">确定</button></a>
</div>
</body>
</html>