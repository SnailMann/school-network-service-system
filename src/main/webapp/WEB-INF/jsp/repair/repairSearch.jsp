<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>报修查询</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/repairSearchh.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery-1.12.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
    <script>
        window.onload = function () {
            var now = new Date();
            var reg = /^\s*([1-9]\d{3})\-(\d{1,2})\-(\d{1,2})\s*$/;
            var _end1 = document.getElementById("end1").innerHTML;
            if (!reg.test(_end1)) {
                throw new Error("Date Format Is Error !");
                return;
            }
            var end1 = new Date(_end1.replace(reg, "$1"), parseInt(_end1.replace(reg, "$2")) - 1, _end1.replace(reg,

                "$3"));
            document.getElementById("end2").innerHTML = Math.round((now - end1) / (1000 * 60 * 60 * 24));
        }
    </script>
</head>
<body onload="load()">
<div class="search">
    <p class="sear">报修查询</p>
    <div class="fun">
        <div class="chargeList02">
            <form class="bs-example bs-example-form" role="form"
                  action="${pageContext.request.contextPath}/worker/findRepairRecordByStudentIdAndresultFlag.action"
                  method="post">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="input-group" id="search">
                            <div class="input-group-btn" id="xialist">
                                <div class="form-group">
                                    <select class="form-control datalist" name="resultFlag">
                                        <option value="0">未处理</option>
                                        <option value="1">已处理</option>
                                        <option value="2">全部</option>
                                    </select>
                                </div>
                            </div><!-- /btn-group -->
                            <div class="input-group-addon ">学号</div>
                            <input type="text" class="form-control" name="studentId" id="text">
                            <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit">查询</button>
                            </span>
                        </div><!-- /input-group -->
                    </div><!-- /.col-lg-6 -->
                </div><!-- /.row -->
            </form>
        </div>
    </div>
</div>
<div id="btn">
    <div class="btn-group">
        <button class="btn btn-default" type="button" value="" name="resultFlag"
                onclick="window.location.href='${pageContext.request.contextPath}/worker/queryNoDealRepairRecord.action'">
            未处理
        </button>
        <button class="btn btn-default" type="button" value="" name="resultFlag"
                onclick="window.location.href='${pageContext.request.contextPath}/worker/queryDealRepairRecord.action'">
            已处理
        </button>
        <button class="btn btn-default" type="button" value="" name="resultFlag"
                onclick="window.location.href='${pageContext.request.contextPath}/worker/queryRepairRecord.action'">全部
        </button>
    </div>
</div>

<div class="tab-content">
    <div class="tab-pane active" id="tab-all">
        <div class="row feature">
            <div class="chargeList">
                <table class="table table-bordered" id="responseText">
                    <tr>
                        <th>姓名</th>
                        <th>学号</th>
                        <th>电话</th>
                        <th>宿舍</th>
                        <th>联系时间</th>
                        <th>内容</th>
                        <th>录入时间</th>
                        <th>至今已（天）</th>
                    </tr>
                    <tbody>
                    <c:forEach var="repairRecordCustomStrList" items="${repairRecordCustomStrList}">
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/worker/findRepairRecordByStudentIdAndrepairContent.action?studentId=${repairRecordCustomStrList.studentId}&repairContent=${repairRecordCustomStrList.repairContent}">${repairRecordCustomStrList.name}</a>
                            </td>
                            <td>${repairRecordCustomStrList.studentId}</td>
                            <td>${repairRecordCustomStrList.phone}</td>
                            <td>${repairRecordCustomStrList.dorm}</td>
                            <td>${repairRecordCustomStrList.contactTime}</td>
                            <t3d>${repairRecordCustomStrList.repairContent}</t3
                                d>
                            <td>${repairRecordCustomStrList.addTime}</td>
                            <td>${repairRecordCustomStrList.toNow}天</td>
                            <td></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>