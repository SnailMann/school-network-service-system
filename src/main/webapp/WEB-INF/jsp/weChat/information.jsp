<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" content="user-scalable=0;" name="viewport" />
    <title>小镇网络服务平台</title>

    <style>
        #imag {
            width: 100%;
            height: 30%;
        }

        .info {
            font-size: 2.7rem;
            color: black;
            width: 46%;
            margin: 0 2%
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

        .infos {
            font-size: 2.7rem;
            color: black;
            width: 90%;
            margin: 0 2%
        }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/weui.css">
</head>
<body>

<div id="imag">
    <img src="${pageContext.request.contextPath}/resource/img/uic.jpg" id="pic" height="50%" width="100%"/>
</div>

<div class="title" align="center">
    <div class="blank">&nbsp;</div>
    个人信息
    <div class="blank">&nbsp;</div>
</div><br>

<div class="content">

    <div class="weui-cell">
        <div class="info">姓名:<span class="color" name="" value="">${studentDTO.name}</span></div>
        <div class="info">学号:<span class="color" name="" value="">${studentDTO.studentId}</span></div>
    </div>
    <br>
    <div class="weui-cell">
        <div class="info">房号:<span class="color" name="" value="">${studentDTO.dorm}</span></div>
        <div class="info">电话:<span class="color" name="" value="">${studentDTO.phone}</span></div>
    </div>
    <br>
    <div class="weui-cell">
        <div class="info">宽带速度:<span class="color" name="" value="">${studentDTO.speed}</span></div>
        <div class="info">金额:<span class="color" name="" value="">${studentDTO.money}</span></div>
    </div>
    <br>
    <div class="weui-cell">
        <div class="infos">开通时间:<span class="color" name="" value="">${studentDTO.startTime} </span></div>
    </div><br>
    <div class="weui-cell">
        <div class="infos">到期时间:<span class="color" name="" value="">${studentDTO.endTime} </span></div>
    </div><br>

</div>
</body>
</html>


