<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户密码修改</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/modifyPassword.css"type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css"type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/modifyPassword.js"></script>
    <script>
        function modifypass() {
            var msg = "${msg}";
            if (msg.length != 0) {
                alert(msg);
            }
        }
    </script>
</head>
<body onload="modifypass()">
<div class="search">
    <p class="sear">密码修改</p >
    <div class="fun">
        <div class="input-group">
        </div>
    </div>
</div>

<div class="boder01">
    <div class="top_left">
        <form action="${pageContext.request.contextPath}/user/updatePassword.action" method="post">
            <div class="input-group">
                <div class="input-group-addon"><span>&nbsp;&nbsp;旧&nbsp;&nbsp;密&nbsp;&nbsp;码&nbsp;</span></div>
                <input type="password" class="form-control" id="mm1" name="oldPassword"   placeholder="请输入原始密码" required oninvalid="setCustomValidity('请填写密码');" oninput="setCustomValidity('');" >
            </div>
            <div class="blank">
            </div>
            <div class="input-group">
                <div class="input-group-addon"><span>&nbsp;&nbsp;新&nbsp;&nbsp;密&nbsp;&nbsp;码&nbsp;</span></div>
                <input type="password" class="form-control" id="mm2"  placeholder="输入新密码" required  oninvalid="setCustomValidity('请填写新密码');" oninput="setCustomValidity('');">
            </div>
            <div class="blank">
            </div>
            <div class="input-group">
                <div class="input-group-addon"><span>确认新密码</span></div>
                <input type="hidden" name="userId" value="${userId}">
                <input type="password" class="form-control" id="mm3" name="userPassword"  placeholder="请再次输入新密码"  required="required"required oninvalid="setCustomValidity('请再次填写新密码');"
                       oninput="setCustomValidity('');" onblur="check03()">
            </div>
            <div class="blank">
            </div>
            <div class="blank">
            </div>
            <div class="divbutton" align="center">
                <input type="submit" class="button11" id="button12" value="提交">
            </div>
        </form>
    </div>
</div>
</body>
</html>