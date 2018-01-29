<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css"type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/tollManagement.css"type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/tollManagement.js"></script>
</head>
<body>
<div class="search">
    <p class="sear">个人信息</p >
    <div class="fun">
        <div class="input-group">
        </div>
    </div>
</div>
<div class="chargeList">
    <div class="chargeList02">
        <a href="${pageContext.request.contextPath}/user/jumpToModifyPasswordView.action"><button type="button" class="btn btn-default btn01" ><span class="icon-plus-square-o"></span>密码修改</button></a>
    </div>
    <table class="table table-hover text-center ">
        <thead>
        <tr>
            <th width="20%">工号</th>
            <th width="20%">姓名</th>
            <th width="20%">联系方式</th>
            <th width="20%">操作</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${worker.workerId}</td>
                <td>${worker.name}</td>
                <td>${worker.phone}</td>
                <td><div class="btn-group">
                    <button type="button" class="btn btn-default btn02">
                        <span class="icon-edit"></span> 修改
                    </button>
                </div></td>
            </tr>
        </tbody>
    </table>
    <div class="modal size01" id="mymodal">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/worker/updatePersonalInfo.action" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">信息修改</h4>
                    </div>
                    <div class="modal-body">
                        <table border="5" cellspacing="0" class="table table-bordered table-hover">
                            <tr>
                                <td>姓名：</td>
                                <td><input id="name02" name="name" type="text" value="${worker.name}" > </td>
                            </tr>
                            <tr>
                                <td>电话：</td>
                                <td><input id="phonenum02" name="phone" type="text" value="${worker.phone}" > </td>
                                <input name="workerId" type="hidden" value="${worker.workerId}"/>
                            </tr>
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
                $("#mymodal").modal("toggle");
            });
        });
    </script>
</body>
</html>