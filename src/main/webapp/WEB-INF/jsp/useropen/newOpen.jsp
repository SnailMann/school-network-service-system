<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UIC网络服务平台</title>
    <link href="${pageContext.request.contextPath}/resource/css/userOpenFreshmanNet.css" rel="stylesheet"
          type="text/css">
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css" type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script>
</head>
<body>
<div id="edit_area_div">
    <div class="containers">
        <div class="search">
            <p class="sear">新生宽带开通</p>
        </div>
        <div class="sear2">
            <div class="div2">
                <form action="${pageContext.request.contextPath}/worker/findStudentOpenIndex.action" method="post"
                      id="form1">
                    <div class="input-group">
                        <input type="text" name="studentId" class="form-control">
                        <span class="input-group-btn">
                    <button class="btn btn-default" type="submit">
                            查询
                    </button>
                </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <table class="table table-bordered" width="100%" id="tab">
        <thead>
        <tr class="active">
            <th>学号</th>
            <th>姓名</th>
            <th>身份证号码</th>
            <th>房号</th>
            <th>联系电话</th>
            <th>账号</th>
            <th>接入号</th>
            <th>宽带速率</th>
            <th>开通月份</th>
            <th>开通</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="studentDTOItem" items="${studentDTOList}">
            <tr>
                <td width="10%">${studentDTOItem.studentId}</td>
                <td width="10%">${studentDTOItem.name}</td>
                <td width="13%">${studentDTOItem.idCard}</td>
                <td width="10%">${studentDTOItem.dorm}</td>
                <td width="10%">${studentDTOItem.phone}</td>
                <td width="10%">${studentDTOItem.studentBroadband.accoutNumber}</td>
                <td width="10%">${studentDTOItem.studentBroadband.accessNumber}</td>
                <td width="10%">
                    <select class="form-control" id="sel" name="openSpeed" style="height: 30px"><!--从数据库中读取-->
                        <c:forEach var="strategyItem" items="${strategyList}">
                            <option value="${strategyItem.speed}">${strategyItem.speed}</option>
                        </c:forEach>
                    </select>
                </td>
                <td width="10%">
                    <select class="sel form-control" name="openMonth" style="height: 30px;padding-top: 3px">
                        <!--从数据库中读取-->
                        <option value="1">1个月</option>
                        <option value="2">2个月</option>
                        <option value="3">3个月</option>
                        <option value="4">4个月</option>
                        <option value="6">半年</option>
                        <option value="12">一年</option>
                    </select>
                </td>
                <td width="7%" class="abc">
                    <button type="button" onclick="transfer(${studentDTOItem.studentId})" class="btn-info" style="width: 100%;height: 30px ;border-radius: 4px">开通</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form action="${pageContext.request.contextPath}/worker/createBillBynewopen.action" method="post" id="myForm">
        <input name="studentId" id="id" type="hidden">
        <input name="openSpeed" id="sec1" type="hidden">
        <input name="openMonth" id="sec2" type="hidden">
    </form>
    <div id="page_div">
        <%@ include file="/WEB-INF/jsp/page.jsp" %>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    function queryAllPerson(pageNum, pageSize) {
        $("#edit_area_div").load("${pageContext.request.contextPath}/worker/openIndex.action?pageNum=" + pageNum + "&pageSize=" + pageSize);
    }
    function transfer(id) {
        var id1 = document.getElementById("id");
        id1.value = id;
    }
    $(".btn-info").click(function () {
        var sec2=$(this).parent().prev().find("select").find("option:selected").val();
        document.getElementById("sec2").value=sec2;
        var sec1=$(this).parent().prev().prev().find("select").find("option:selected").val();
        document.getElementById("sec1").value=sec1;
        document.getElementById("myForm").submit();
    })

</script>