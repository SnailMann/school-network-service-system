<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" content="user-scalable=0;" name="viewport"/>
    <title>小镇网络服务平台</title>

    <style>
        .imag {
            width: 100%;
            height: 30%;
        }

        .info {
            font-size: 2.7rem;
            color: black;
            width: 45%;
            margin: 0 2%
        }

        .infos {
            font-size: 2.7rem;
            color: black;
            width: 90%;
            margin: 3rem 2%

        }

        .title {
            background-color: #F0F0F0;
            color: #3CC51F;
            width: 100%;
            position: relative;
            top: -0.4rem;
            font-size: 3.2rem;
            font-family: "宋体";
        }

        .blank {
            width: 100%;
            font-size: 1rem;
        }

        .content {
            width: 96%;
            margin: 0 auto;
            position: relative;
            left: 1.5%;
        }

        .color {
            color: #10AEFF;
        }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/weui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/js/jquery-1.12.0.min.js">
    <script src="${pageContext.request.contextPath}/resource/js/jquery-1.12.0.min.js"></script>
</head>
<body>

<div class="imag">
    <img src="${pageContext.request.contextPath}/resource/img/uic.jpg" id="pic" height="100%" width="100%"/>
</div>

<div class="title" align="center">
    <div class="blank">&nbsp;</div>
    报修信息
    <div class="blank">&nbsp;</div>
</div>
<br><br>

<form>
        <div class="content">
            <div class="weui-cell">
                <div class="info" >录入时间:<span class="color" name="addTime" value="">${repairRecordCustomStr.addTime}</span></div>
            </div>
            <div class="weui-cell">
                <div class="info" >处理情况:<span class="color" name="resultFlag" value="" id="set">${repairRecordCustomStr.resultFlag}</span></div>
            </div>
            <br>
            <div class="weui-cell">
                <div class="infos">报修内容:<span class="color" name="repairContent" value="">${repairRecordCustomStr.repairContent}</span></div>
            </div>
            <br>
        </div>
</form>
</body>
</html>
<script>
    $(document).ready(function () {
        var a="0";
        if($('#set').html()==a){

            $('#set').html('未处理');
        }else {

            $('#set').html('已处理');
        }
    })
</script>
