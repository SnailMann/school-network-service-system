<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML>
<!--简体中文-->
<html lang="zh-cmn-Hans">
<head>
    <meta charset="utf-8">
    <title></title>
    <link href="${pageContext.request.contextPath}/resource/css/bill_print_preview.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery-1.12.0.min.js"></script>
</head>
<body>
<div class="search">
    <p class="sear">打印账单</p>
    <form action="${pageContext.request.contextPath}/worker/insertStudentOrderBySpecial.action" method="post" id="myform">
        <input id="name" type="hidden" class="form-control" value="${name}" name="name">
        <input id="orderId" type="hidden" class="form-control" value="${orderId}" name="orderId">
        <input id="lastTime" type="hidden" class="form-control" value="${studentId}" name="studentId">
        <input id="speed" type="hidden" class="form-control" value="${money}" name="money">
        <input id="userId" type="hidden" class="form-control" value="${workerId}" name="workerId">
        <input id="userPassword" type="hidden" class="form-control" value="${printTimeStr}" name="printTimeStr">
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
        <div class="username">${name}</div>
        <div class="number">${orderId}</div>
    </div>
    <div class="clear"></div>
    <div class="div2">
        <div class="payObject">
            <c:forEach var="chargeItem" items="${chargeDTOList}">
                ${chargeItem.name}<br />
            </c:forEach>
        </div>
        <div class="cost" id="cost">
            <c:forEach var="chargeItem" items="${chargeDTOList}">
                ${chargeItem.value}<br />
            </c:forEach>
        </div>
        <div class="userInformation">
            <c:forEach var="attributeDTOItem" items="${attributeDTOList}">
                <label class="label1">${attributeDTOItem.name}：</label>
                <label>${attributeDTOItem.value}</label><br>
            </c:forEach>
        </div>
    </div>
    <div class="clear"></div>
    <div class="div3">
        <div class="cost1"></div><p id="cost1" style="display: none">${money}</p>
        <div class="dealer">${chargeType}<br />${printTimeStr}<br /><br />${workerId}</div>
        <div class="date"></div>
    </div>
</div>
<!--打印内容结束-->
<!--endprint1-->
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

    $(".cost1").text(DX($("#cost1").text())+"("+$("#cost1").text()+")");
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
</body>
</html>