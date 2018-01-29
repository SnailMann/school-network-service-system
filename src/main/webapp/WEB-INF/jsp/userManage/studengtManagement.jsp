<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生信息管理</title>
    <link href="../../resourse/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="../../resourse/css/bootstrap.css"type="text/css">
    <link rel="stylesheet" href="../../resourse/css/tollManagement.css"type="text/css">
    <script src="../../resourse/js/jquery.min.js"></script>
    <script src="../../resourse/js/jquery.js"></script>
    <script src="../../resourse/js/tollManagement.js"></script>
</head>
<body>
<div class="search">
    <p class="sear">学生信息管理</p >
    <div class="fun">
        <div class="input-group">
            <input type="text" class="form-control">
            <span class="input-group-btn">
                  <button class="btn btn-default" type="button">
                            搜索
                        </button>
            </span>
        </div>
    </div>
</div>
<div class="chargeList">
    <div class="chargeList02">
        <button type="button" class="btn btn-default btn01" onclick=""><span class="icon-plus-square-o"></span>添加学生</button>
    </div>
    <table class="table table-hover text-center " >
        <thead>
        <tr>
            <th width="13%">序号</th>
            <th width="13%">学号</th>
            <th width="13%">姓名</th>
            <th width="13%">房号</th>
            <th width="13%">电话</th>
            <th width="15%">身份证</th>
            <th width="20%">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${gradeCustoms}">
            <tr>
                <td>1</td>
                <td>123</td>
                <td>张三</td>
                <td>a101</td>
                <td>112233</td>
                <td>333333</td>
                <td><div class="btn-group">
                    <button type="button" class="btn btn-default btn02">
                        <span class="icon-edit"></span> 修改
                    </button>
                    <button type="button" class="btn btn-default btn03">
                        <a class="" href="javascript:void(0)" onclick="return del(1,1)"><span class=""></span><span class="cor">删除</span></a>
                    </button>
                </div></td>
            </tr>
            <tr>
                <td>2</td>
                <td>124</td>
                <td>李四</td>
                <td>112333</td>
                <td>222222</td>
                <td>333333</td>
                <td><div class="btn-group">
                    <button type="button" class="btn btn-default btn02">
                        <span class="icon-edit"></span>修改
                    </button>
                    <button type="button" class="btn btn-default btn03">
                        <a class="" href="javascript:void(0)" onclick="return del(1,1)"><span class=""></span> <span class="cor">删除</span></a>
                    </button>
                </div></td>
            </tr>
            <tr>
                <td>3</td>
                <td>125</td>
                <td>王五</td>
                <td>14563</td>
                <td>333333</td>
                <td>333333</td>
                <td><div class="btn-group">
                    <button type="button" class="btn btn-default btn02">
                        <span class="icon-edit"></span> 修改
                    </button>
                    <button type="button" class="btn btn-default btn03">
                        <a class="" href="javascript:void(0)" onclick="return del(1,1)"><span class=""></span> <span class="cor">删除</span></a>
                    </button>
                </div></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="blank"></div>
    <div class="modal size01" id="mymodal">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="#" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">信息修改</h4>
                    </div>
                    <div class="modal-body">
                        <table border="5" cellspacing="0" class="table table-bordered table-hover">
                            <tr>
                                <td>联系方式：</td>
                                <td><input id="phonenum02" type="text" value="" placeholder=""> </td>
                            </tr>
                            <tr>
                                <td>房号：</td>
                                <td><input id="mm05" type="text" value="" placeholder=""> </td>
                            </tr>
                            <tr>
                                <td>密码：</td>
                                <td><input id="mm07" type="text" value="" placeholder=""> </td>
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

    <div class="modal size01" id="mymodal02">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="#" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">添加信息</h4>
                    </div>
                    <div class="modal-body">
                        <table border="5" cellspacing="0" class="table table-bordered table-hover">
                            <tr>
                                <td>学号：</td>
                                <td><input id="num01" type="text" value="" placeholder=""> </td>
                            </tr>
                            <tr>
                                <td>姓名：</td>
                                <td><input id="name02" type="text" value="" placeholder=""> </td>
                            </tr>
                            <tr>
                                <td>房号：</td>
                                <td><input id="home01" type="text" value="" placeholder=""> </td>
                            </tr>
                            <tr>
                                <td>电话：</td>
                                <td><input id="phonenum04" type="text" value="" placeholder=""> </td>
                            </tr>
                            <tr>
                                <td>身份证：</td>
                                <td><input id="idcard01" type="text" value="" placeholder=""> </td>
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
    function transfer(studentId) {
        var id = document.getElementById("studentId");
        id.value=studentId;
    }
    $(function(){
        $(".btn01").click(function(){
            $("#mymodal02").modal("toggle");
        });
    });
</script>
</body>

</html>