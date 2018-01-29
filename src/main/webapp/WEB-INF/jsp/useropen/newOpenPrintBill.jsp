<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UIC网络服务平台</title>
    <link href="${pageContext.request.contextPath}/resource/css/userOpenPrintBill.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css" type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script>
</head>
<body>
<div class="search">
    <p class="sear">打印账单</p>
    <form action="${pageContext.request.contextPath}/worker/insertStudentOrderByUserOpen.action" method="post" id="myform">
        <input id="name" type="hidden" class="form-control" value="${studentOrder.name}" name="name">
        <input id="orderId" type="hidden" class="form-control" value="${studentOrder.orderId}" name="orderId">
        <input id="lastTime" type="hidden" class="form-control" value="${studentOrder.lastTime}" name="lastDate">
        <input id="speed" type="hidden" class="form-control" value="${studentOrder.speed}" name="openSpeed">
        <input id="userId" type="hidden" class="form-control" value="${studentOrder.userId}" name="userId">
        <input id="userPassword" type="hidden" class="form-control" value="${studentOrder.userPassword}" name="userPassword">
        <input id="studentId" type="hidden" class="form-control" value="${studentOrder.studentId}" name="studentId">
        <input id="dorm" type="hidden" class="form-control" value="${studentOrder.dorm}" name="dorm">
        <input id="endTime" type="hidden" class="form-control" value="${endTimeStr}" name="endDate">
        <input id="money" type="hidden" class="form-control" value="${studentOrder.money}" name="money">
        <input id="workerId" type="hidden" class="form-control"value="${studentOrder.workerId}" name="workerId">
        <input id="printTime" type="hidden" class="form-control" value="${printTimeStr}"name="printDate">
        <button type="button" class="btn btn1 btn-default"  onclick="preview(1)">
            打印账单
        </button>
    </form>
</div>
<br/>
<!--startprint1-->
<!--打印内容开始-->
<div class="print">
    <div class="div1">
        <div class="username">${studentOrder.name}</div>
        <div class="number">${studentOrder.orderId}</div>
    </div>
    <div class="clear"></div>
    <div class="div2">
        <div class="payObject">包${studentOrder.lastTime}个月<br>宽带速度：${studentOrder.speed}M</div>
        <div class="cost" id="cost">${studentOrder.money}</div>
        <div class="userInformation">
            用户名：${studentOrder.userId}<br/>
            初始密码:${studentOrder.userPassword}<br/>
            学号：${studentOrder.studentId}<br/>
            房间号：${studentOrder.dorm}<br/>
            到期时间：${endTimeStr}
        </div>
    </div>
    <div class="clear"></div>
    <div class="div3">
        <div class="cost1"><br>(${studentOrder.money})</div>
        <div class="dealer">${printTimeStr}<br /><br /><br>${studentOrder.workerId}</div>
        <div class="date"></div>
    </div>
</div>
<!--打印内容结束-->
<!--endprint1-->
</body>
<script type="text/javascript">
    function preview(oper) {
        if (oper < 10) {
            bdhtml = window.document.body.innerHTML;	//获取当前页的html代码
            sprnstr = "<!--startprint" + oper + "-->";	//设置打印开始区域
            eprnstr = "<!--endprint" + oper + "-->";	//设置打印结束区域
            prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 18); //从开始代码向后取html
            prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));//从结束代码向前取html
            window.document.body.innerHTML = prnhtml;
            window.print();
            window.document.body.innerHTML = bdhtml;
        } else {
            window.print();
        }
        document.getElementById("myform").submit();
    }
    function transfer(name,orderId, lastTime, speed, userId, userPassword, studentId, dorm, endTime, money, workerId, printTime) {
        alert("hhhhhhhhhh")
        var name1 = document.getElementById("name");
        name1.value = name;
        var orderId1 = document.getElementById("orderId");
        orderId1.value = orderId;
        var lastTime1 = document.getElementById("lastTime");
        lastTime1.value = lastTime;
        var speed1 = document.getElementById("speed");
        speed1.value = speed;
        var userId1 = document.getElementById("userId");
        userId1.value = userId;
        var userPassword1 = document.getElementById("userPassword");
        userPassword1.value = userPassword;
        var studentId1 = document.getElementById("studentId");
        studentId1.value = studentId;
        var openTime1 = document.getElementById("openTime");
        openTime1.value = openTime;
        var dorm1 = document.getElementById("dorm");
        dorm1.value = dorm;
        var endTime1 = document.getElementById("endTime");
        endTime1.value = endTime;
        var money1 = document.getElementById("money");
        money1.value = money;
        var workerId1 = document.getElementById("workerId");
        workerId1.value = workerId;
        var printTime1 = document.getElementById("printTime");
        printTime1.value = printTime;
        document.getElementById("myform").submit();
    }
    $(".cost1").text(DX($("#cost").text())+"("+($("#cost").text())+")");
    function DX(n) {
        if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n)) return "数据非法";
        var unit = "京亿万仟佰拾兆万仟佰拾亿仟佰拾万仟佰拾元角分", str = "";
        n += "00";
        var p = n.indexOf('.');
        if (p >= 0)
            n = n.substring(0, p) + n.substr(p+1, 2);
        unit = unit.substr(unit.length - n.length);
        for (var i=0; i < n.length; i++) str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
        return str.replace(/零(仟|佰|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(兆|万|亿|元)/g, "$1").replace(/(兆|亿)万/g, "$1").replace(/(京|兆)亿/g, "$1").replace(/(京)兆/g, "$1").replace(/(京|兆|亿|仟|佰|拾)(万?)(.)仟/g, "$1$2零$3仟").replace(/^元零?|零分/g, "").replace(/(元|角)$/g, "$1整");
    }
</script>