<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>报修查询-全部</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/repairSearchh.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery-1.12.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            var a="0";
//            一个页面固定10条数据
            for(var i=0;i<10;i++) {
                if($(".abc:eq("+i+")").text()==a){
                    $(".abc:eq("+i+")").next().find("button").css({"background":"#d9534f","color":"white"});
                    $(".abc:eq("+i+")").next().find("button").text('未处理');
                }else {
                    $(".abc:eq("+i+")").next().find("button").css({"background":"#5cb85c","color":"white"});
                    $(".abc:eq("+i+")").next().find("button").text('已处理');
                }
            }
        })

        function queryAllPerson(pageNum, pageSize) {
            $("#edit_area_div").load("${pageContext.request.contextPath}/worker/queryRepairRecord.action?pageNum=" + pageNum + "&pageSize=" + pageSize);
        }
    </script>
</head>
<body>
<div  id="edit_area_div">
    <div class="search">
        <p class="sear">报修查询-全部</p>
        <div class="fun">
            <form class="bs-example bs-example-form" role="form"
                  action="${pageContext.request.contextPath}/worker/findRepairRecordByStudentId.action"
                  method="post">
                <div class="input-group" id="search">
                    <div class="input-group-addon ">学号</div>
                    <input type="text" class="form-control" name="studentId" id="text">
                    <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit">查询</button>
                            </span>
                </div>
            </form>
        </div>
    </div>
    <div id="btn">
        <div class="btn-group">
            <button class="btn btn-default" type="button"
                    onclick="window.location.href='${pageContext.request.contextPath}/worker/queryNoDealRepairRecord.action'">
                未处理
            </button>
            <button class="btn btn-default" type="button"
                    onclick="window.location.href='${pageContext.request.contextPath}/worker/queryDealRepairRecord.action'">
                已处理
            </button>
            <button class="btn btn-default" type="button"
                    onclick="window.location.href='${pageContext.request.contextPath}/worker/queryRepairRecord.action'">
                全部
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
                            <th>处理情况</th>
                        </tr>
                        <tbody>
                        <c:forEach var="repairRecordCustomStrList" items="${repairRecordCustomStrList}">
                            <tr onload="load()">
                                <td>
                                    <a href="${pageContext.request.contextPath}/worker/findRepairRecordByStudentIdAndrepairContentAll.action?studentId=${repairRecordCustomStrList.studentId}&repairContent=${repairRecordCustomStrList.repairContent}">${repairRecordCustomStrList.name}</a>
                                </td>
                                <td>${repairRecordCustomStrList.studentId}</td>
                                <td>${repairRecordCustomStrList.phone}</td>
                                <td>${repairRecordCustomStrList.dorm}</td>
                                <td>${repairRecordCustomStrList.contactTime}</td>
                                <td>${repairRecordCustomStrList.repairContent}</td>
                                <td>${repairRecordCustomStrList.addTime}</td>
                                <td>${repairRecordCustomStrList.toNow}天</td>
                                <td class="abc" style="display: none;">${repairRecordCustomStrList.resultFlag}</td>
                                <td>
                                    <button class="btn btn-defaultv aaa"type="button" value="${repairRecordCustomStrList.resultFlag}" onclick="window.location.href='${pageContext.request.contextPath}/worker/findRepairRecordByStudentIdAndrepairContentAll.action?studentId=${repairRecordCustomStrList.studentId}&repairContent=${repairRecordCustomStrList.repairContent}'">
                                        处理
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="page_div">
        <%@ include file="/WEB-INF/jsp/page.jsp"%>
    </div>
</div>
</div>
</body>
</html>
