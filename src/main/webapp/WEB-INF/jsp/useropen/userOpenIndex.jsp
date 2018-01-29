<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UIC网络服务平台</title>
    <link href="${pageContext.request.contextPath}/resource/css/userOpenInformationProcessing.css" rel="stylesheet"
          type="text/css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css" type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>

</head>
<body>
<div id="edit_area_div">
    <div class="containers">
        <div class="search">
            <p class="sear">信息处理</p>
            <div class="sear2">
                <form action="${pageContext.request.contextPath}/worker/findStudentIndex.action" method="post">
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
            <form action="${pageContext.request.contextPath}/worker/uploadFile.action" method="post"
                  enctype="multipart/form-data" class="btn6">
                <div class="btn1">
                    <input id="lefile" type="file" name="file" style="display:none">
                    <div class="input-append">
                        <input id="photoCover" class="input-large" type="text" style="height:30px;">
                        <a class="btn btn-default" onclick="$('input[id=lefile]').click();">上传文件</a>
                    </div>
                </div>
                <button class="btn btn-default" type="submit">确定上传</button>
            </form>
            <form action="${pageContext.request.contextPath}/worker/downloadExcel.action" method="post"
                  enctype="multipart/form-data">
                <button class="btn btn2 btn-default" type="submit">导出文件</button>
            </form>
            <form action="${pageContext.request.contextPath}/worker/downloadModel.action" method="post"
                  enctype="multipart/form-data">
                <button class="btn btn2 btn-default" type="submit">导出模板</button>
            </form>
            <button type="button" class="btn btn-default btn3" data-toggle="modal" data-target="#myModal1">信息录入</button>
            <button type="button" class="btn btn-default btn3" data-toggle="modal" data-target="#myModal2">批量删除</button>
        </div>
    </div>
    <table class="table table-bordered" width="100%" style="word-wrap: break-word; word-break: break-all;">
        <thead>
        <tr class="active">
            <th>学号</th>
            <th>姓名</th>
            <th>身份证号码</th>
            <th>房号</th>
            <th>电话</th>
            <th>接入号</th>
            <th>账号</th>
            <th>宽带速率</th>
            <th>开通月份</th>
            <th>开通时间</th>
            <th>到期时间</th>
            <th>金额</th>
            <th>修改</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="studentDTOStrItem" items="${studentDTOStrList}">
            <tr>
                <td style="width: 7%">${studentDTOStrItem.studentId}</td>
                <td style="width: 6%">${studentDTOStrItem.name}</td>
                <td style="width: 10%">${studentDTOStrItem.idCard}</td>
                <td style="width: 6%">${studentDTOStrItem.dorm}</td>
                <td style="width: 9%">${studentDTOStrItem.phone}</td>
                <td style="width: 9%">${studentDTOStrItem.accessNumber}</td>
                <td style="width: 12%">${studentDTOStrItem.accoutNumber}</td>
                <td style="width: 5%">${studentDTOStrItem.speed}</td>
                <td style="width: 5%">${studentDTOStrItem.lastTime}</td>
                <td style="width: 6%">
                    <div class="date1">${studentDTOStrItem.startTime}</div>
                </td>
                <td style="width: 6%">
                    <div class="date2">${studentDTOStrItem.endTime}</div>
                </td>
                <td style="width: 5%">${studentDTOStrItem.money}</td>
                <td style="width: 7%">
                    <input type="button"
                           onclick="transfer('${studentDTOStrItem.studentId}','${studentDTOStrItem.name}','${studentDTOStrItem.idCard}'
                                   ,'${studentDTOStrItem.dorm}','${studentDTOStrItem.phone}','${studentDTOStrItem.accessNumber}','${studentDTOStrItem.accoutNumber}'
                                   ,'${studentDTOStrItem.speed}','${studentDTOStrItem.lastTime}','${studentDTOStrItem.startTime}','${studentDTOStrItem.endTime}'
                                   ,'${studentDTOStrItem.money}')" class="btn-warning btnb" data-toggle="modal"
                           data-target="#myModal" value="修改">
                </td>
                <td style="width: 7%">
                    <a style="text-decoration: none"
                       href="${pageContext.request.contextPath}/worker/deleteStudentIndex.action?studentId=${studentDTOStrItem.studentId}"
                       id="links" class="btn-danger btna" role="button"
                       onclick="if(confirm('确定删除?')==false)return false;">删除</a>
                </td>
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
                        学生信息修改
                    </h4>
                </div>
                <form action="${pageContext.request.contextPath}/worker/updateStudentIndex.action" method="post"
                      onsubmit="return Are1()">
                    <div class="modal-body">
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">学号</span>
                            <input id="number" type="text" class="form-control" name="studentId" readonly>
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">姓名</span>
                            <input id="name" type="text" class="form-control" name="name">
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">身份证号码</span>
                            <input id="idNumber" type="text" class="form-control" name="idCard">
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">房号</span>
                            <input id="roomNumber" type="text" class="form-control" name="dorm">
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">电话</span>
                            <input id="telephone" type="text" class="form-control" name="phone">
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">接入号</span>
                            <input id="num1" type="text" class="form-control" name="studentBroadband.accessNumber">
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">账号</span>
                            <input id="num2" type="text" class="form-control" name="studentBroadband.accoutNumber">
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">带宽速率</span>
                            <select class="form-control" id="num3" name="speed"><!--从数据库中读取-->
                                <c:forEach var="strategyItem" items="${strategyList}">
                                    <option value="${strategyItem.speed}">${strategyItem.speed}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">开通月份</span>
                            <select id="openMonth" class="form-control" name="lastTime"><!--从数据库中读取-->
                                <option value="1">一个月</option>
                                <option value="2">两个月</option>
                                <option value="3">三个月</option>
                                <option value="4">四个月</option>
                                <option value="6">半年</option>
                                <option value="12">一年</option>
                            </select>
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">开通时间</span>
                            <input id="openTime" type="date" class="form-control" name="startTime">
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">到期时间</span>
                            <input id="endTime" type="date" class="form-control" name="endTime">
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">金额</span>
                            <input id="cost" type="text" class="form-control" name="money">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button type="submit" class="btn btn-default">修改</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel1">
                        学生信息录入
                    </h4>
                </div>
                <form action="${pageContext.request.contextPath}/worker/insertStudentIndex.action" method="post">
                    <div class="modal-body">
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">学号</span>
                            <input type="text" class="form-control" name="studentId">
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">姓名</span>
                            <input type="text" class="form-control" name="name">
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">身份证号码</span>
                            <input type="text" class="form-control" name="idCard">
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">房号</span>
                            <input type="text" class="form-control" name="dorm">
                        </div>
                        <div class="input-group btn5">
                            <span class="input-group-addon" style="width: 95px">联系电话</span>
                            <input type="text" class="form-control" name="phone">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                            <button type="submit" class="btn btn-default">确定</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel2">
                        批量信息删除
                    </h4>
                </div>
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/worker/deleteAllInfoById.action" method="post" name="myForm" onsubmit="return check();">
                        <div class="input-group btn7">
                            <span class="input-group-addon">请输入年级</span>
                            <input type="number" class="form-control" name="id" id="delete">
                        </div>
                        <p id="idea" class="warn"></p>
                        <div class="clear"></div>
                        <br>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                            <button type="submit" class="btn btn-default">确定</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div id="page_div">
        <%@ include file="/WEB-INF/jsp/page.jsp" %>
    </div>
</div>
</body>
</html>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-transition.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-modal.js"></script>
<script type="text/javascript">
    /*点击修改按钮确认*/
    function transfer(number, name, idNumber, roomNumber, telephone, num1, num2, num3, openMonth, openTime, endTime, cost) {
        var number1 = document.getElementById("number");
        number1.value = number;
        var name1 = document.getElementById("name");
        name1.value = name;
        var idNumber1 = document.getElementById("idNumber");
        idNumber1.value = idNumber;
        var roomNumber1 = document.getElementById("roomNumber");
        roomNumber1.value = roomNumber;
        var telephone1 = document.getElementById("telephone");
        telephone1.value = telephone;
        var num11 = document.getElementById("num1");
        num11.value = num1;
        var num21 = document.getElementById("num2");
        num21.value = num2;
        var num31 = document.getElementById("num3");
        num31.value = num3;
        var openMonth1 = document.getElementById("openMonth");
        openMonth1.value = openMonth;
        var openTime1 = document.getElementById("openTime");
        openTime1.value = openTime;
        var endTime1 = document.getElementById("endTime");
        endTime1.value = endTime;
        var cost1 = document.getElementById("cost");
        cost1.value = cost;
    }
    /*将上传文件路径转换为文件名*/
    var file = $('#lefile');
    var aim = $('#photoCover');
    file.on('change', function (e) {
        //e.currentTarget.files 是一个数组，如果支持多个文件，则需要遍历
        var name = e.currentTarget.files[0].name;
        aim.val(name);
    });
    function queryAllPerson(pageNum, pageSize) {
        $("#edit_area_div").load("${pageContext.request.contextPath}/worker/index.action?pageNum=" + pageNum + "&pageSize=" + pageSize);
    }
    /*$("#delete").blur(function(){
     if($("#delete").val()=='') {
     document.getElementById("idea").innerHTML = "<font color='red'>*输入的值为空</font>";//账号不为空
     }
     var str = document.getElementById("delete").value.trim();
     if(str.length!=0){
     reg=/[0-9]{2}/;
     if(!reg.test(str)){
     document.getElementById("idea").innerHTML = "<font color='red'>*请正确输入级数</font>";
     }
     }
     $("#delete").focus(function(){//出现红色提示的时候，聚焦至任意输入框，红色输入框消失
     $("#idea").empty();
     });
     });*/
    function check() {
        var reg = /^\d$/;
        if ($("#delete").val() == '') {
            document.getElementById("idea").innerHTML = "<font color='red'>*输入的值为空</font>";//输入不为空
            return false;
        }
        if ($("#delete").val().length != 2){
            document.getElementById("idea").innerHTML = "<font color='red'>*请输入两位数字</font>";//判断是否为两位数字
            return false;
        }
        else{
            return true;
        }
    }
    $("#delete").focus(function () {//出现红色提示的时候，聚焦至任意输入框，红色输入框消失
        $("#idea,#delete").empty();
    });
</script>