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
<div class="search">
    <p class="sear">已作废订单</p>
    <div class="fun">
        <div class="form-group">
            <form action="${pageContext.request.contextPath}/worker/choosefindByIdOrOrderId.action" method="post">
                <select class="form-control datalist" name="chooseFlag">
                    <option value="1">单号</option>
                    <option value="0">学号</option>
                </select>
                <input type="text" name="id" class="form-control text"/>
                <button type="submit" class="btn btn-default">查询</button>
            </form>
        </div>
    </div>
</div>
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
        <c:forEach var="studentOrderStrItem" items="${studentOrderStrList}">
            <tr>
                <td><a class="mouse" href="${pageContext.request.contextPath}/worker/findOrderCancelReasonByOrderId.action?orderId=${studentOrderStrItem.orderId}">${studentOrderStrItem.orderId}</a></td>
                <td>${studentOrderStrItem.studentId}</td>
                <td>${studentOrderStrItem.name}</td>
                <td>${studentOrderStrItem.dorm}</td>
                <td>${studentOrderStrItem.speed}</td>
                <td>${studentOrderStrItem.lastTime}</td>
                <td>${studentOrderStrItem.printTime}</td>
                <td>${studentOrderStrItem.endTime}</td>
                <td>${studentOrderStrItem.money}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>