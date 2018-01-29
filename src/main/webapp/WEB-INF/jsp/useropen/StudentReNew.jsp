<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UIC网络服务平台</title>
    <link href="${pageContext.request.contextPath}/resource/css/userOpenUserRenew.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css" type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
</head>
<body>
<div  id="edit_area_div">
<div class="containers">
    <div class="search">
        <p class="sear">用户续费</p>
    </div>
    <div class="clear"></div>
    <div class="sear2">
        <form action="${pageContext.request.contextPath}/worker/findStudentReNewIndex.action" method="post">
            <div class="div2">
                <div class="input-group">
                    <input type="text" name="studentId" class="form-control">
                    <span class="input-group-btn">
						<button class="btn btn-default" type="submit">
                            查询
                        </button>
					</span>
                </div>
            </div>
        </form>
    </div>
</div>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>身份证号码</th>
        <th>房号</th>
        <th>联系方式</th>
        <th>宽带速率</th>
        <th>金额</th>
        <th>开通时间</th>
        <th>开通月份</th>
        <th>到期时间</th>
        <th>订单记录查询</th>
        <th>续费</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="studentDTOStrItem" items="${studentDTOStrList}">
        <tr>
            <td width="8%">${studentDTOStrItem.studentId}</td>
            <td width="8%">${studentDTOStrItem.name}</td>
            <td width="10%">${studentDTOStrItem.idCard}</td>
            <td width="8%">${studentDTOStrItem.dorm}</td>
            <td width="9%">${studentDTOStrItem.phone}</td>
            <td width="8%">${studentDTOStrItem.speed}</td>
            <td width="8%">${studentDTOStrItem.money}</td>
            <td width="9%">${studentDTOStrItem.startTime}</td>
            <td width="9%">${studentDTOStrItem.lastTime}</td>
            <td width="9%">${studentDTOStrItem.endTime}</td>
            <td width="7%"><button type="button"  class="btnb btn-info" onclick="window.location.href = '${pageContext.request.contextPath}/worker/findStudentOrderById.action?studentId=${studentDTOStrItem.studentId}';">订单记录查询</button></td>
            <td width="7%"><button type="button"  class="btnb btn-info" data-toggle="modal" data-target="#myModal" onclick="transfer('${studentDTOStrItem.studentId}','${studentDTOStrItem.speed}')">续费</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    请选择续费信息
                </h4>
            </div>
            <form action="${pageContext.request.contextPath}/worker/createBillByRenew.action" method="post" >
                <div class="modal-body btn1">
                    <input name="studentId" id="studentId" type="hidden" >
                    <input name="openSpeed" id="openSpeed" type="text" class="btn1 form-control" readonly >
                    <select class="form-control btn1" name="openMonth"><!--从数据库中读取-->
                        <option value="1">一个月</option>
                        <option value="2">两个月</option>
                        <option value="3">三个月</option>
                        <option value="4">四个月</option>
                        <option value="6">半年</option>
                        <option value="12">一年</option>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="submit" class="btn btn-primary">
                        生成账单
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
    <div id="page_div">
        <%@ include file="/WEB-INF/jsp/page.jsp"%>
    </div>
</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resource/js/jquery-1.12.0.min.js"></script>
<script type="text/javascript">function check() {
        if (confirm("确定打印?")) {
            window.location.href = "${pageContext.request.contextPath}/worker/createBill.action";
        }
        else {
            return false;
        }
    }
    function transfer(studentId,openSpeed){
        var studentId1 = document.getElementById("studentId");
        studentId1.value=studentId;
        var openSpeed1 = document.getElementById("openSpeed");
        openSpeed1.value=openSpeed;
    }
    function queryAllPerson(pageNum, pageSize) {
        $("#edit_area_div").load("${pageContext.request.contextPath}/worker/reNewIndex.action?pageNum=" + pageNum + "&pageSize=" + pageSize);
    }

</script>