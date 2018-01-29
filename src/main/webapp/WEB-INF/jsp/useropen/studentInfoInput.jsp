<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UIC网络服务平台</title>
    <link href="${pageContext.request.contextPath}/resource/css/userOpenFreshmanInput.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resourse/css/bootstrap.css" type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script>
</head>
<body>
<div class="containers">
    <div class="search">
        <p class="sear">学生信息录入</p>
    </div>

    <div class="inf">
        <div class="box">
            <form action="${pageContext.request.contextPath}/worker/insertStudentAllInfo.action" method="post">
                <div class="input-group btn1">
                    <span class="input-group-addon">学号</span>
                    <input type="text" class="form-control" name="studentId"  >
                </div>
                <div class="input-group btn1">
                    <span class="input-group-addon">姓名</span>
                    <input type="text" class="form-control" name="name"  >
                </div>
                <div class="input-group btn1">
                    <span class="input-group-addon">身份证号码</span>
                    <input type="text" class="form-control" name="idCard"  >
                </div>
                <div class="input-group btn1">
                    <span class="input-group-addon">房号</span>
                    <input type="text" class="form-control" name="dorm"  >
                </div>
                <div class="input-group btn1">
                    <span class="input-group-addon">联系电话</span>
                    <input type="text" class="form-control" name="phone"  >
                </div>
                <div class="input-group btn1">
                    <span class="input-group-addon">密码</span>
                    <input type="text" class="form-control" name="user.userPassword" >
                </div>
                <div class="input-group btn1">
                    <span class="input-group-addon">接入号</span>
                    <input type="text" class="form-control" name="studentBroadband.accessNumber"  >
                </div>
                <div class="input-group btn1">
                    <span class="input-group-addon">账号</span>
                    <input type="text" class="form-control" name="studentBroadband.accoutNumber"  >
                </div>
                <div class="input-group btn1">
                    <span class="input-group-addon">带宽速率</span>
                    <input type="text" class="form-control" name="studentBroadband.speed"  >
                </div>
                <div class="input-group btn1">
                    <span class="input-group-addon">金额</span>
                    <input type="text" class="form-control" name="studentBroadband.money"  >
                </div>
                <div class="input-group btn1">
                    <span class="input-group-addon">开通时间</span>
                    <input type="text" class="form-control" name="studentBroadband.startTime"  >
                </div>
                <div class="input-group btn1">
                    <span class="input-group-addon">开通月份</span>
                    <input type="text" class="form-control" name="studentBroadband.lastTime"  placeholder="请输入开通月份">
                </div>
                <div class="input-group btn1">
                    <span class="input-group-addon">到期时间</span>
                    <input type="text" class="form-control" name="studentBroadband.endTime"  placeholder="请输入到期时间">
                </div>

                <button class="btn btn-primary btn-lg btn-block  btn1" onclick="check()" type="submit">
                    提交
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    function check() {
        if (confirm("确定录入?")) {
            window.location.href = "userOpenFreshmanNet.html";
        }
        else {
            return false;
        }
    }
</script>