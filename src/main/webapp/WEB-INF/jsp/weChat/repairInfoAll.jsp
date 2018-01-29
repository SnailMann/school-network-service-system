<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>小镇网络服务平台</title>
    <style>

        #outline {
            width: 42rem;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/weui.css">
</head>
<body>

<form>
    <c:forEach var="repairRecordCustomStrList" items="${repairRecordCustomStrList}">
        <div>
            <a href="${pageContext.request.contextPath}/student/findRepairRecordByStudentIdAndrepairContent.action?studentId=${repairRecordCustomStrList.studentId}&repairContent=${repairRecordCustomStrList.repairContent}">
                <div class="weui-cell weui-cell_access">
                    <div class="weui-cell__hd" style="position: relative;margin-right: 10px;">
                        <img src="${pageContext.request.contextPath}/resource/img/repair.jpg"
                             style="width: 150px;display: block"/>
                    </div>
                    <div class="weui-cell__bd">
                        <p style="font-size: 3rem;color: #0D0D0D;" id="outline" name="repairContent"
                           value="">${repairRecordCustomStrList.repairContent}</p>
                        <p style="font-size: 2rem;color: #888888;" name="addTime"
                           value="">${repairRecordCustomStrList.addTime}</p>
                    </div>
                    <div class="weui-cell__ft"></div>
                </div>
            </a>
        </div>
    </c:forEach>
</form>
</body>
</html>