<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UIC网络服务平台</title>
    <link href="${pageContext.request.contextPath}/resource/css/userOpenOrderQuery.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resourse/css/bootstrap.css" type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>

</head>
<body>
<div class="containers">
    <div class="search">
        <p class="sear">订单记录</p>
        <div class="btn1">
            <button type="button" class="btn btn-default" onclick="check()">返回</button>
        </div>
    </div>
</div>
<table class="table table-bordered" width="100%">
    <thead>
    <tr class="active">
        <th>学号</th>
        <th>订单号</th>
        <th>姓名</th>
        <th>房号</th>
        <th>宽带速率</th>
        <th>开通月份</th>
        <th>开通时间</th>
        <th>到期时间</th>
        <th>金额</th>



    </tr>
    </thead>
    <tbody>
    <c:forEach var="studentOrderStrItem" items="${studentOrderStrList}">
    <tr>
        <td>${studentOrderStrItem.studentId}</td>
        <td>${studentOrderStrItem.orderId}</td>
        <td>${studentOrderStrItem.name}</td>
        <td>${studentOrderStrItem.dorm}</td>
        <td>${studentOrderStrItem.speed}</td>
        <td>${studentOrderStrItem.lastTime}</td>
        <td>${studentOrderStrItem.printTime}</td>
        <td>${studentOrderStrItem.endTime}</td>
        <td>${studentOrderStrItem.money}</td>

    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
<script type="text/javascript">
    function check() {
        window.location.href = "${pageContext.request.contextPath}/worker/reNewIndex.action";
    }
</script>