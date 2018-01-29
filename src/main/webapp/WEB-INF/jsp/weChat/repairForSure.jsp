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

        .submit, .back {
            display: inline-block;
            padding: 6px 12px;
            font-weight: normal;
            line-height: 1.42857143;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            -ms-touch-action: manipulation;
            touch-action: manipulation;
            cursor: pointer;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            background-image: none;
            border-radius: 4px;
            background-color: #1AAD19;
            color: #FFFFFF;
            border-color: #ccc;
            width: 28%;
            height: 100px;
            font-size: 3.2rem;
            margin: 3% 10%;
        }

        button {
            border-radius: 1rem !important;
        }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/weui.css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery-1.12.0.min.js"></script>
</head>
<body>

<div class="imag">
    <img src="${pageContext.request.contextPath}/resource/img/uic.jpg" id="pic" height="100%" width="100%"/>
</div>

<div class="title" align="center">
    <div class="blank">&nbsp;</div>
    确认报修信息
    <div class="blank">&nbsp;</div>
</div>
<br>
<form>
        <div class="content">

            <div class="weui-cell">
                <div class="info">姓名:<span class="color" name="name" value="">${repairRecordCustomStr.name}</span>
                </div>
                <div class="info">学号:<span class="color" name="studentId"
                                           value="">${repairRecordCustomStr.studentId}</span></div>
            </div>
            <br>
            <div class="weui-cell">
                <div class="info">电话:<span class="color" name="phone" value="">${repairRecordCustomStr.phone}</span>
                </div>
                <div class="info">宿舍:<span class="color" name="dorm" value="">${repairRecordCustomStr.dorm}</span>
                </div>
            </div>
            <br>
            <div class="weui-cell">
                <div class="infos">录入时间:<span class="color" name="addTime"
                                              value="">${repairRecordCustomStr.addTime}</span></div>
            </div>
            <br>
            <div class="weui-cell">
                <div class="infos">联系时间:<span class="color" name="contactTime"
                                              value="">${repairRecordCustomStr.contactTime}</span></div>
            </div>
            <br>
            <div class="weui-cell">
                <div class="infos">报修内容:<span class="color" name="repairContent"
                                              value="">${repairRecordCustomStr.repairContent}</span></div>
            </div>
            <br>
        </div>

        <div class="anniu">
            <button class="back btn btn-default" type="button"
                    onclick="window.location.href='${pageContext.request.contextPath}/student/deleteRepairRecord.action?studentId=${repairRecordCustomStr.studentId}&repairContent=${repairRecordCustomStr.repairContent}'">
                取消
            </button>
            <button class="submit  btn btn-default" type="button" onclick="window.location.href='${pageContext.request.contextPath}/student/weChatLogin.action?function=findRepairRecordBystudentId'">确认</button>
        </div>
</form>
</body>
</html>
