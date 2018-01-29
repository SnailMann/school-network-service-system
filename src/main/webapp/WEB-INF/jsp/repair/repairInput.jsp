<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>报修录入</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/repairInput.css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function rec(){
            if($("#a").val()!=''&& $("#b").val()!='' && $("#c").val()!='' && $("#d").val()!='' && $("#e").val()!='' && $("#f").val()!='')
            {
                var mychar="提交成功";
                alert(mychar);
            }
        }
    </script>
</head>
<body>
<div class="search">
    <p class="sear">报修录入</p>
    <div class="fun">
        <form action="${pageContext.request.contextPath}/worker/findrepairStudent.action" method="post">
            <div class="input-group" >
                <div class="input-group-addon">学号</div>
                <input type="text" class="form-control" name="studentId">
                <span class="input-group-btn">
                <button class="btn btn-default" type="submit">查询</button>
                </span>
            </div>
        </form>
    </div>
</div>
<div class="contact">
    <form class="contact_form" action="${pageContext.request.contextPath}/worker/insertRepairRecord.action"
          method="post" name="contact_form">
        <ul>
            <li>
                <label>学号:</label>
                <input id="a" type="text" name="studentId" placeholder="" required  oninvalid="setCustomValidity('请填写学号');" oninput="setCustomValidity('');" id="studentId" value="${student.studentId}">
            </li>
            <li>
                <label>姓名:</label>
                <input id="b" type="text" name="name" placeholder="" required  oninvalid="setCustomValidity('请填写姓名');" oninput="setCustomValidity('');" id="name" value="${student.name}"/>
            </li>
            <li>
                <label>电话:</label>
                <input id="c" type="text" name="phone" placeholder="" required  oninvalid="setCustomValidity('请填写电话');" oninput="setCustomValidity('');" id="phone" value="${student.phone}"/>
            </li>
            <li>
                <label>宿舍:</label>
                <input id="d" type="text" name="dorm" placeholder="" required  oninvalid="setCustomValidity('请填写宿舍');" oninput="setCustomValidity('');" id="dorm" value="${student.dorm}"/>
            </li>
            <li>
                <label>联系时间:</label>
                <input id="e" type="text" name="contactTime" placeholder="" required  oninvalid="setCustomValidity('请填写联系时间');" oninput="setCustomValidity('');" id="contactTime"/>
            </li>
            <li>
                <label>内容:</label>
                <textarea id="f" name="repairContent" cols="40" rows="6" placeholder="" required  oninvalid="setCustomValidity('请填写内容');" oninput="setCustomValidity('');" id="repairContent"></textarea>
            </li>
            <li>
                <button class="submit btn btn-default" type="submit" onclick="rec()">提交</button>
                <button class="reset btn btn-default" type="reset">重置</button>
            </li>
        </ul>
    </form>
</div>
</body>
</html>

