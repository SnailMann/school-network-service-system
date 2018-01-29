<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>员工信息管理</title>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css"type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/tollManagement.css"type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/tollManagement.js"></script>
</head>
<body>
<div class="search">
    <p class="sear">员工管理</p >
    <div class="fun">
        <div class="input-group">
        </div>
    </div>
</div>
<div class="chargeList">
    <table class="table table-hover text-center">
        <thead>
        <tr>
            <th width="20%">序号</th>
            <th width="20%">工号（M）</th>
            <th width="20%">姓名</th>
            <th width="20%">联系方式</th>
            <th width="20%">操作</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="worker" items="${workerList}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${worker.workerId}</td>
                <td>${worker.name}</td>
                <td>${worker.phone}</td>
                <td><div class="btn-group">
                    <input type="button" class="btn btn-default btn02"
                           onclick="transfer(${worker.workerId})" value="修改">
                </div></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="blank"></div>
    <div class="modal size01" id="mymodal001">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/admin/updateWorkerInfo.action" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">信息修改</h4>
                    </div>
                    <div class="modal-body">
                        <table border="5" cellspacing="0" class="table table-bordered table-hover">
                            <tr>
                                <td>姓名：</td>
                                <td><input id="workerName" name="name" type="text"> </td>
                            </tr>
                            <tr>
                                <td>联系方式：</td>
                                <td><input id="workerPhone"  name="phone" type="text"> </td>
                            </tr>
                            <input id="workerId" name="workerId" type="hidden">
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary">保存</button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-transition.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-modal.js"></script>
<script>
    $(function(){
        $(".btn02").click(function(){
            $("#mymodal001").modal("toggle");
        });
    });
    function transfer(id) {
        var i = document.getElementById("workerId");
        i.value=id;
    }
    $(function(){
        $(".btn01").click(function(){
            $("#mymodal002").modal("toggle");
        });
    });
</script>
</body>

</html>