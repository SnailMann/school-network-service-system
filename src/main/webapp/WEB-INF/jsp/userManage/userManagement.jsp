<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户信息管理</title>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css"type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/tollManagement.css"type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/tollManagement.js"></script>
</head>
<body >
<div class="search">
    <p class="sear">用户管理</p >
    <div class="fun">
        <div class="input-group">
        </div>
    </div>
</div>
<div class="chargeList">
    <div class="chargeList02">
        <button type="button" class="btn btn-default btn01" >添加用户</button>
        <button type="button" class="btn btn-default btn04 screen" > 筛选</button>
    </div>

    <table class="table table-hover text-center">
        <thead>
        <tr>
            <th width="25%">序号</th>
            <th width="25%">用户ID</th>
            <th width="25%">角色</th>
            <th width="25%">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${user.userId}</td>
                <td>${user.userRoleId}</td>
                <td><div class="btn-group">
                    <button type="button" class="btn btn-default btn02" onclick="change(${user.userId})">
                        修改密码
                    </button>
                    <button type="button" class="btn btn-default btn03" onclick="transfer(${user.userId})">
                        删除
                    </button>
                </div></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="modal size01" id="mymodal01">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/admin/insertUser.action" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">添加信息</h4>
                    </div>
                    <div class="modal-body">
                        <table border="5" cellspacing="0" class="table table-bordered table-hover">
                            <tr>
                                <td>用户ID：</td>
                                <td><input id="num01" name="userId" type="text" value="" placeholder="" required  oninvalid="setCustomValidity('请输入用户ID');" oninput="setCustomValidity('');"> </td>
                            </tr>
                            <tr>
                                <td>密码：</td>
                                <td><input id="mm003" name="userPassword" type="text" value="" placeholder="" required  oninvalid="setCustomValidity('请填写密码');" oninput="setCustomValidity('');"> </td>
                            </tr>
                            <tr>
                                <td>角色：</td>
                                <td>
                                    <input id="x1"  type="radio"  name="userRoleId" value="3">管理员&nbsp;&nbsp;
                                    <input id="x2"  type="radio"  name="userRoleId" value="2">职工&nbsp;&nbsp;
                                    <input id="x3"  type="radio"  name="userRoleId" value="1" checked="checked">学生
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary">添加</button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- 添加信息 -->

    <div class="modal size01" id="mymodal02">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/user/updatePassword.action" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">密码修改</h4>
                    </div>
                    <div class="modal-body">
                        <table border="5" cellspacing="0" class="table table-bordered table-hover">
                            <tr>
                                <td>新密码：</td>
                                <td><input id="num01" type="password" name="userPassword" required="required">
                                <input type="hidden" id="uId" name="userId"> </td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary">确定</button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- 密码修改 -->

    <div class="modal size01" id="mymodal03">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/admin/deleteUser.action" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">删除</h4>
                    </div>
                    <div class="modal-body">
                        确定删除此行信息？
                        <input id="key" name="userId" type="hidden">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary">确定</button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- 删除 -->

    <div class="modal size01" id="mymodal04">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/admin/queryUser.action" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">信息</h4>
                    </div>
                    <div class="modal-body">
                        <table border="5" cellspacing="0" class="table table-bordered table-hover">
                            <tr>
                                <td>角色：</td>
                                <td id="test">
                                    <input id="x1"  type="checkbox"  name="userRoleId" value="3">管理员&nbsp;&nbsp;
                                    <input id="x2"  type="checkbox"  name="userRoleId" value="2">职工&nbsp;&nbsp;
                                    <input id="x3"  type="checkbox"  name="userRoleId" value="1">学生
                                </td>
                            </tr>
                            <tr>
                                <td>用户ID：</td>
                                <td><input id="num01" name="userId" type="text"  > </td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary">提交</button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- 信息筛选 -->
</div>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-transition.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-modal.js"></script>
<script>
    $(function(){
        $(".btn01").click(function(){
            $("#mymodal01").modal("toggle");
        });
    });
    $(function(){
        $(".btn02").click(function(){
            $("#mymodal02").modal("toggle");
        });
    });
    function transfer(userId) {
        var id = document.getElementById("key");
        id.value=userId;
    }
    function change(userId) {
        var id = document.getElementById("uId");
        id.value=userId;
    }
    $(function(){
        $(".btn03").click(function(){
            $("#mymodal03").modal("toggle");
        });
    });
    $(function(){
        $(".btn04").click(function(){
            $("#mymodal04").modal("toggle");
        });
    });


    $(document).ready(function(){
        $('#test').find('input[type=checkbox]').bind('click', function(){
            $('#test').find('input[type=checkbox]').not(this).attr("checked", false);
        });
    });


</script>
</body>

</html>