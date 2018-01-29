<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <title>login</title>
        <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css"type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/login.css">
        <script src="${pageContext.request.contextPath}/resource/js/jquery-1.12.0.min.js"></script>
        <script>
            function forgiveNum() {
                alert("请联系管理员重置密码");
            }
            function login(){
                $.ajax({
                    url:"${pageContext.request.contextPath}/user/login.action",
                    data:{
                        userId:$("#userId").val(),
                        password:$("#password").val(),
                    },
                    type:"POST",
                    dataType:"JSON",
                    success: function(data){
                        if(data.state=="noUser")
                        {
                            alert("用户名不存在。");
                        }
                        if(data.state=="errorPassword")
                        {
                            alert("密码错误。");
                        }
                        if(data.state=="errorRole")
                        {
                            alert("当前用户无登录权限。");
                        }
                        if(data.character=="worker")
                        {
                            window.location.replace("${pageContext.request.contextPath}/user/toWork.action")
                        }
                        if(data.character=="admin")
                        {
                            window.location.replace("${pageContext.request.contextPath}/user/toAdmin.action")
                        }
                    }
                });
            }
        </script>
    </head>
    <body>
        <div class="wrapper">
            <form class="form-signin">
                <h2 class="form-signin-heading " align="center">UIC网络服务平台</h2>
                <input type="text" class="tip-one form-control" id="userId" name="userId" placeholder="Username" required  oninvalid="setCustomValidity('请输入账号');" oninput="setCustomValidity('');" autofocus="" /><br>
                <input type="password" class="tip-two form-control" id="password" name="password" placeholder="Password" required  oninvalid="setCustomValidity('请输入密码');" oninput="setCustomValidity('');"/>
                <span class="forgive" onclick="forgiveNum()"><a>忘记密码？</a></span>
                <div class="blank">
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="button" onclick="login()">登录</button>
            </form>
        </div>
        <div style="width: 200px;margin: auto;margin-top: 450px;">
            <a target="_blank" href="http://www.miitbeian.gov.cn">粤ICP备16116309号-1</a></div>
    </body>
</html>
