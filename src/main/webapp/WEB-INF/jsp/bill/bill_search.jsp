<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resource/css/bill_search.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery-1.12.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
</head>
<body>
<div  id="edit_area_div">
<div class="search">
    <p class="sear">营帐查询</p >
    <div class="fun">
        <button type="button" class="btn btn-default" id="bill_search" onclick="billId()">号码查询</button>
        <button type="button" class="btn btn-default" id="work_search" onclick="workId()">管理员工号查询</button>
    </div>
</div>
<div class="cont">
    <form action="${pageContext.request.contextPath}/worker/choosefindBillByFlag.action" method="post">
        <div class="bill" style="display: none;">
            <div class="bill_top">号码查询</div>
            <div class="bid">
                <select class="form-control" id="bid_lab" name="flag">
                    <option value="0">单号</option>
                    <option value="1">学号</option>
                </select>
                <input type="text" name="id" class="form-control" id="bid_text"/>
                <button type="submit" class="btn btn-default" id="aid_btn">查询</button>
            </div>
        </div>
    </form>
    <form action="${pageContext.request.contextPath}/worker/findBillByDate.action" method="post">
        <div class="adm" style="display: none;">
            <div class="adm_top">管理员工号查询</div>
            <div class="aid">
                <label class="aid_lab">管理员工号：</label>
                <input type="text" name="workerId" class="form-control" id="aid_text" />
            </div>
            <div class="time">
                <strong class="date_lab">查询时间段：</strong>
                <div class="date">
                    <label class="lab">从</label>
                    <input type="date" name="startDate" class="form-control" id="date_start" />
                    <label class="lab">到</label>
                    <input type="date" name="endDate" class="form-control" id="date_end" />
                </div>
            </div>
            <div class="operation">
                <div class="sub">
                    <button type="submit" class="btn btn-default">查询</button>
                </div>
                <div class="res">
                    <button type="reset" class="btn btn-default">重置</button>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="foot">
    <div class="data">
        <div class="all_money" style="color: red;"><strong>总金额:${money}元</strong></div>
        <%--<div class="all_bill">总共续费1单，开通0单，退费0单</div>--%>
        <table class="table table-bordered">
            <tr class="active">
                <th>订单号</th>
                <th>学号</th>
                <th>姓名</th>
                <th>房号</th>
                <th>接入号</th>
                <th>宽带速率</th>
                <th>开通月份</th>
                <th>开通时间</th>
                <th>到期时间</th>
                <th>处理工号</th>
                <th>金额</th>
                <th style="width: 6%;"></th>
                <th style="width: 6%;"></th>
            </tr>
            <c:forEach var="studentOrderStrItem" items="${studentOrderStrList}">
                <tr>
                    <td>${studentOrderStrItem.orderId}</td>
                    <td>${studentOrderStrItem.studentId}</td>
                    <td>${studentOrderStrItem.name}</td>
                    <td>${studentOrderStrItem.dorm}</td>
                    <td>${studentOrderStrItem.accessNumber}</td>
                    <td>${studentOrderStrItem.speed}</td>
                    <td>${studentOrderStrItem.lastTime}</td>
                    <td>${studentOrderStrItem.printTime}</td>
                    <td>${studentOrderStrItem.endTime}</td>
                    <td>${studentOrderStrItem.workerId}</td>
                    <td>${studentOrderStrItem.money}</td>
                    <td style="width: 6%;">
                        <a href="${pageContext.request.contextPath}/worker/creatBillAgain.action?orderId=${studentOrderStrItem.orderId}"><button type="button" class="btn btn-primary">补打</button></a>
                    </td>
                    <td style="width: 6%;">
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal" onclick="transfer('${studentOrderStrItem.orderId}')">作废</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="page_div">
        <%@ include file="/WEB-INF/jsp/page.jsp"%>
    </div>
</div>
</div>

<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    作废理由
                </h4>
            </div>
            <form action="${pageContext.request.contextPath}/worker/updateBillCancel.action" method="post">
                <div class="modal-body">
                    <input name="orderId" id="orderId" type="hidden" >
                    <textarea name="cancelReason" class="reason" placeholder="请填写作废理由（120字以内）" cols="70" rows="10"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </form>
        </div>
    </div>

</div>
<script type="text/javascript">
function transfer(orderId){
    var orderId1 = document.getElementById("orderId");
    orderId1.value=orderId;
}
function billId(){
    $(".bill").css("display","block");
    $(".adm").css("display","none");
}
function workId(){
    $(".adm").css("display","block");
    $(".bill").css("display","none");
}
function queryAllPerson(pageNum, pageSize) {
    $("#edit_area_div").load("${pageContext.request.contextPath}/worker/searchBillIndex.action?pageNum=" + pageNum + "&pageSize=" + pageSize);
}
</script>
</body>
</html>