<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UIC网络服务平台</title>
    <link href="${pageContext.request.contextPath}/resource/css/mainPage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css" type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script>
</head>
<body>
<div class="containers">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#example-navbar-collapse">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand ">UIC网络服务平台</a>
            </div>
            <div class="collapse navbar-collapse navbar-right" id="example-navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a>工号:${userId}</a></li>
                    <li><a>角色:${userRole}</a></li>
                    <li><a style="cursor: pointer" onclick="window.location.href='${pageContext.request.contextPath}/user/logout.action'">退出</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="clear"></div>
    <div class="left">
        <div class="div1">
            <div class="left_top"><img src="${pageContext.request.contextPath}/resource/img/bbb_01.jpg"><img src="${pageContext.request.contextPath}/resource/img/bbb_02.jpg" id="2"><img
                    src="${pageContext.request.contextPath}/resource/img/bbb_03.jpg"><img src="${pageContext.request.contextPath}/resource/img/bbb_04.jpg"></div>
            <div class="div2 box">
                <div class="pic1"></div>
                用户开通
            </div>
            <div class="div3">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/worker/index.action" target="listIframe"> 信息处理</a></li>
                    <li><a href="${pageContext.request.contextPath}/worker/openIndex.action" target="listIframe"> 新生开网</a></li>
                    <li><a href="${pageContext.request.contextPath}/worker/reNewIndex.action" target="listIframe"> 用户续费</a></li>
                </ul>
            </div>
            <div class="div2">
                <div class="pic2"></div>
                报修管理
            </div>
            <div class="div3">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/worker/toinsertRepairRecord.action" target="listIframe"> 报修录入</a></li>
                    <li><a href="${pageContext.request.contextPath}/worker/queryNoDealRepairRecord.action" target="listIframe"> 报修查询</a></li>
                </ul>
            </div>
            <div class="div2">
                <div class="pic2"></div>
                收费策略
            </div>
            <div class="div3">
                <ul>
                    <li> <a href="${pageContext.request.contextPath}/worker/queryChargeStrategy.action" target="listIframe">收费信息</a></li>
                </ul>
            </div>
            <div class="div2">
                <div class="pic2"></div>
                营帐管理
            </div>
            <div class="div3">
                <ul>
                    <li> <a href="${pageContext.request.contextPath}/worker/inputBillIndex.action" target="listIframe"> 特殊账目录入</a></li>
                    <li> <a href="${pageContext.request.contextPath}/worker/searchBillIndex.action.action" target="listIframe"> 账单查询</a></li>
                    <li> <a href="${pageContext.request.contextPath}/worker/orderCancelIndex.action" target="listIframe"> 作废订单查询</a></li>
                </ul>
            </div>
            <div class="div2">
                <div class="pic2"></div>
                个人信息
            </div>
            <div class="div3">
                <ul>
                    <li> <a href="${pageContext.request.contextPath}/worker/queryInfo.action" target="listIframe"> 个人信息</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="right">
        <iframe src="${pageContext.request.contextPath}/mainPageIframe.jsp" name="listIframe" width=100% height=100% marginheight=0
                marginwidth=0 scrolling=auto  frameborder="0">
        </iframe>
    </div>
    <div class="footer">
        <div class="foot">@版权所有版本号</div>

    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function () {
        $(".div2").click(function () {
            $(this).next("div").slideToggle("fast")
                .siblings(".div3:visible").slideUp("fast");
        });
    });
</script>