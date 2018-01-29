<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>小镇网络服务平台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/weui.css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery-1.12.0.min.js"></script>
    <style>
        *{
            font-size: 40px;
            font-family: 宋体;
        }
        .page{
            font-size: 15px;
        }
        .imag{
            width: 100%;
        }
        img{
            width: 100%;
            height: 32%;
        }
        .page{
            margin-top: 7%;
        }
    </style>
    <script>
        function checkId(){
            $.ajax({
                url:"${pageContext.request.contextPath}/student/checkId.action",
                data:{
                    studentId:$("#uid").val(),
                },
                type:"POST",
                dataType:"JSON",
                success: function(data){
                    if(data.state=="ok")
                    {
                        $("#ts").html("验证通过");
                        $("#ts").css("color","green");
                    }
                    else
                    {
                        if(data.state=="fail"){
                            $("#ts").html("学号不存在");
                            $("#ts").css("color","red");
                        }
                        if(data.state=="bind"){
                            $("#ts").html("学号已绑定");
                            $("#ts").css("color","red");
                        }
                    }
                }
            });
        }
        function checkPassword(){
            $.ajax({
                url:"${pageContext.request.contextPath}/student/checkPassword.action",
                data:{
                    studentId:$("#uid").val(),
                    password:$("#ups").val(),
                },
                type:"POST",
                dataType:"JSON",
                success: function(data){
                    if(data.state=="ok")
                    {
                        $("#tx").html("验证通过");
                        $("#tx").css("color","green");
                        checkBind();
                    }
                    else
                    {
                        $("#tx").html("密码错误");
                        $("#ups").focus();
                        $("#tx").css("color","red");
                    }
                }
            });
        }
        function checkBind(){
            $.ajax({
                url:"${pageContext.request.contextPath}/student/bindOpenId.action",
                data:{
                    studentId:$("#uid").val(),
                    openId:$("#openId").val(),
                },
                type:"POST",
                dataType:"JSON",
                success: function(data){
                    if(data.state=="ok")
                    {
                        alert("绑定成功。");
                        JavaScript:WeixinJSBridge.call('closeWindow');
                    }
                    else
                    {
                        alert("发生未知错误，绑定失败。");
                    }
                }
            });
        }
    </script>
</head>
<body>
<div class="imag">
    <img src="${pageContext.request.contextPath}/resource/img/UIC.png" height="113" width="333"/></div>
<div class="weui-cells__title">身份绑定</div>
<div class="weui-cells weui-cells_form">
    <form>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label  class="weui-label">学号</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" id="uid" type="number" value="" name="studentId" placeholder="请输入学号" onblur="checkId()"/>
        </div>
        <div id="ts"></div>
    </div>

    <div class="weui-cell">
        <div class="weui-cell__hd"><label  class="weui-label">密码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" id="ups" type="password" value="" name="password" placeholder="请输入密码"/>
            <input type="hidden" id="openId" name="openId" value="${openId}">
        </div>
        <div id="tx"></div>
    </div>

        <div class="page__bd page__bd_spacing">
            <input class="weui-btn weui-btn_primary" id="tj" type="button" value="提交" onclick="checkPassword()">
        </div>
    </form>
</div>

<div class="weui-cells__tips">使用本服务平台您必须进行绑定以及身份验证通过</div>

<div class="page">
    <div class="page__bd page__bd_spacing">
        <div class="weui-footer">
            <p class="weui-footer__text">Copyright &copy; 2017 小镇网络服务平台</p>
        </div>
    </div>
</div>

</body>
</html>