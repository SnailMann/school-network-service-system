<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>点击用户名查看用户信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/repairUserInfermation.css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript">
        function rec(){
            if($("#a").val()!='')
            {
                var mychar="提交成功";
                alert(mychar);
            }
        }
    </script>
</head>
<body>
<div class="search">
    <p class="sear">个人报修信息</p>
</div>
<div class="content">
    <form action="${pageContext.request.contextPath}/worker/updateRepairRecordByStudentRepairContentYet.action"
          method="post">
        <ul>
            <li>
                <div class="top">
                    <table class="table table-bordered" width="100%">
                        <tr>
                            <th>姓名</th>
                            <th>学号</th>
                            <th>电话</th>
                            <th>宿舍</th>
                            <th>联系时间</th>
                            <th>录入时间</th>
                            <th>至今已（天）</th>
                        </tr>
                        <tbody id="hide_tbody">
                        <c:forEach var="repairRecordCustomsList" items="${repairRecordCustomStrList}">
                            <tr>
                                <td>${repairRecordCustomsList.name}</td>
                                <td>${repairRecordCustomsList.studentId}</td>
                                <td>${repairRecordCustomsList.phone}</td>
                                <td>${repairRecordCustomsList.dorm}</td>
                                <td>${repairRecordCustomsList.contactTime}</td>
                                <td>${repairRecordCustomsList.addTime}</td>
                                <td>${repairRecordCustomsList.toNow}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </li>
            <li>
                <label>内容</label>
                <c:forEach var="repairRecordCustomsList" items="${repairRecordCustomStrList}">
                    <textarea name="repairContent" cols="165" rows="6" placeholder=""
                              required>${repairRecordCustomsList.repairContent}</textarea>
                </c:forEach>
            </li>
            <li>
                <label>意见</label>
                <c:forEach var="repairRecordCustomsList" items="${repairRecordCustomStrList}">
                    <textarea id="a" name="advice" cols="165" rows="6" placeholder=""
                              required  oninvalid="setCustomValidity('请填写意见');" oninput="setCustomValidity('');">${repairRecordCustomsList.advice}</textarea>
                </c:forEach>
            </li>
            <li>
                <c:forEach var="repairRecordCustomsList" items="${repairRecordCustomStrList}">
                    <button class=" btn btn-default" type="submit" onclick="rec()">已处理</button>
                    <button class=" btn btn-default" type="button"
                            onclick=" window.history.back();">
                        返回
                    </button>
                </c:forEach>
            </li>
        </ul>
    </form>
</div>
</body>
</html>