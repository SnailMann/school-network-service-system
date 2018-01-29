<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bill_input.css" type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery-1.12.0.min.js"></script>
</head>
<body>
<div class="search">
    <p class="sear">账目录入</p >
</div>
<div class="container">
    <div class="header">
        <div class="top">定制表格</div>
        <div class="input-group">
            <div class="input-group-addon">用户信息项目</div>
            <input class="form-control" id="info_num" type="text"/>
            <div class="input-group-addon">项</div>
        </div>
        <div class="input-group g2">
            <div class="input-group-addon">收费项目</div>
            <input class="form-control" id="charge_num" type="text"/>
            <div class="input-group-addon">项</div>
        </div>
        <div class="mt">
            <button id="mt_btn" class="btn btn-default" type="button" onclick="mt()">制表</button>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/worker/createBillBySpecial.action" method="post">
        <div class="content" style="display: none;">
            <ul>
                <li class="s_info">
                    <div class="name">
                        <label class="name_lab">客户姓名：</label>
                        <input class="form-control" name="name" id="name_text" type="text" required="required" />
                    </div>
                    <%--<form action="${pageContext.request.contextPath}/worker/findrepairStudent.action" method="post">--%>
                        <div class="sid">
                            <label class="sid_lab">学号：</label>
                            <input class="form-control" name="studentId" id="sid_text" type="text" required="required"/>
                        </div>
                        <%--<div class="sid_search">
                            <button type="submit" class="btn btn-default" id="sid_search">查询</button>
                        </div>--%>
                    <%--</form>--%>
                </li>
                <li class="type">
                    <label class="type_lab">业务类型：</label>
                    <select class="form-control" id="type" name="chargeType">
                        <option value="网络开户">网络开户</option>
                        <option value="网络续费">网络续费</option>
                        <option value="网络退费">网络退费</option>
                    </select>
                </li>
            </ul>
            <div class="tb">
                <table class="table" id="table1">
                    <caption>用户信息：</caption>
                    <tbody id="hide1">
                    <tr style="display: none">
                        <td>
                            <div class="lab">属性1名称：</div>
                            <input class="form-control" id="info_name" />
                        </td>
                        <td>
                            <div class="lab">值=</div>
                            <input class="form-control" id="info_value" type="text"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <table class="table" id="table2">
                    <caption>收费项目：</caption>
                    <tbody id="hide2">
                    <tr style="display: none">
                        <td>
                            <div class="lab">项目1名称：</div>
                            <input class="form-control" id="charge_name" type="text"/>
                        </td>
                        <td>
                            <div class="lab">值=</div>
                            <input class="form-control" id="charge_value" type="text"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="footer"style="display: none;">
            <div class="sub">
                <input class="btn btn-default" type="submit" value="提交"/>
            </div>
            <div class="res">
                <input class="btn btn-default" type="reset" value="重置"/>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    function mt(){
        var infoNum = /^\d+$/;
        var infoTr = '';
        var chargeNum = /^\d+$/;
        var chargeTr = '';
        if(!infoNum.test($("#info_num").val())){
            return false;
        }
        else if(!chargeNum.test($("#charge_num").val())){
            return false;
        }
        $(".content").show();
        $(".footer").show();
        $('table tr td').remove();

        for(var i=0; i<$("#info_num").val(); i++){
            infoTr += '<tr><td class="userInfo"><div class="lab">属性1名称：</div><input class="form-control" id="info_name" name="attributeName"/></td><td><div class="lab">值=</div><input class="form-control" id="info_value" name="attributeValue" type="text"/></td></tr>';
        }
        $("#table1").append(infoTr);

        for(var i=0; i<$("#charge_num").val(); i++){
            chargeTr += '<tr><td><div class="lab">项目1名称：</div><input class="form-control" id="charge_name" name="chargeName" type="text"/></td><td> <div class="lab">值=</div> <input class="form-control" id="charge_value" name="chargeValue" type="text"/> </td></tr>';
        }
        $("#table2").append(chargeTr);


    }
    /*$(document).ready(function () {
        $("#sid_search").click(function () {
            $.ajax({
                type: "POST",
                url: "",
                dataType: "json",
                data:{ name:$("#sid_text").val(),},
                success: function (data) {
                    $("#name_text").val(data);
                }
            });
        });
    })*/
    </script>
</body>
</html>