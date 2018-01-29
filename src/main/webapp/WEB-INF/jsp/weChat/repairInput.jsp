<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"  content="user-scalable=0"  name="viewport" />
    <title>小镇网络服务平台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/weui.css">
    <style>
        .content {
            margin-top: -6.4%;
        }

        footer {
            position: fixed;
        }

        .submit, .reset {
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
            border: 1px solid transparent;
            border-radius: 4px;
            background-color: #1AAD19;
            color: #FFFFFF;
            width: 28%;
            height: 100px;
            font-size: 3.2rem;
            margin: 3% 10%;
        }

        button {
            border-radius: 1rem !important;
        }

        .blank {
            width: 100%;
            font-size: 1rem;
        }

        .title {
            background-color: #F0F0F0;
            color: #3CC51F;
            width: 100%;
            position: relative;
            top: -0.4rem;
            font-size: 3.2rem;
            font-family: "宋体";
            margin-top: -2%;
        }

        .textarea {
            margin-top: -0.0%;
        }
    </style>
</head>

<body>
<div class="img">
    <img src="${pageContext.request.contextPath}/resource/img/infoimg.jpg" height="35%" width="100%"/>
</div>

<div class="title" align="center">
    <div class="blank">&nbsp;</div>
    报修录入
    <div class="blank">&nbsp;</div>
</div>

<div class="content">
    <form action="${pageContext.request.contextPath}/student/insertRepairRecord.action" method="post">
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">学号</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="number" pattern="[0-9]*" placeholder="请输入学号" name="studentId" required  oninvalid="setCustomValidity('请填写学号');" oninput="setCustomValidity('');" value="${student.studentId}"/>
                </div>
            </div>

            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">联系时间</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="text"  placeholder="请输入联系时间" name="contactTime" required  oninvalid="setCustomValidity('请填写联系时间');" oninput="setCustomValidity('');" value=""/>
                </div>
            </div>
        </div>

        <div class="weui-cells weui-cells_form textarea">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" placeholder="请描述报修的具体内容" rows="3" name="repairContent" required  oninvalid="setCustomValidity('请填写内容');" oninput="setCustomValidity('');" value=""></textarea>
                    <div class="weui-textarea-counter"><span>0</span>/100</div>
                </div>
            </div>
        </div>

        <div class="anniu">
            <button class="reset btn btn-default" type="reset">重置</button>
            <button class="submit  btn btn-default" type="submit">确定</button></a>
        </div>
    </form>
</div>

</body>
</html>