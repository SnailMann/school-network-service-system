<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>收费策略管理</title>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css"type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/tollManagement.css"type="text/css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/tollManagement.js"></script>
</head>
<body>
<div class="search">
    <p class="sear">收费信息</p >
    <div class="fun">
        <div class="input-group">
        </div>
    </div>
</div>
<div class="chargeList">
    <div class="chargeList02">
        <button type="button" class="btn btn-default btn01" ><span class="icon-plus-square-o"></span> 添加内容</button>
    </div>
    <table class="table table-hover text-center">
        <thead>
        <tr>
            <th width="10%">序号</th>
            <th width="10%">网速（M）</th>
            <th width="10%">价格/月</th>
            <th width="20%">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="strategy" items="${strategyList}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${strategy.speed}</td>
            <td>${strategy.price}</td>
            <td><div class="btn-group">
                <input type="button" class="btn btn-default btn02" onclick="transfer(${strategy.id},${strategy.speed},${strategy.price})" value="修改">
                <button type="button" class="btn btn-default btn03" onclick="change(${strategy.id})">
                    <span class="cor">删除</span>
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
            <form action="${pageContext.request.contextPath}/worker/updateChargeStrategy.action" method="post">
            <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">信息修改</h4>
			</div>
			<div class="modal-body">
				<table border="5" cellspacing="0" class="table table-bordered table-hover">
                    <tr>
                       <td>网速：</td>
                       <td><input id="strategySpeed" name="speed" type="text"> </td>
                    </tr>
                    <tr>
                       <td>价格：</td>
                       <td><input id="strategyPrice"  name="price" type="text"> </td>
                    </tr>
                    <input id="strategyId" name="id" type="hidden">
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
            <form action="${pageContext.request.contextPath}/worker/insertChargeStrategy.action" method="post">
            <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">添加信息</h4>
			</div>
			<div class="modal-body">
				<table border="5" cellspacing="0" class="table table-bordered table-hover">
                    <tr>
                       <td>网速：</td>
                       <td><input id="speed" name="speed" type="text" required="required"> </td>
                    </tr>
                    <tr>
                       <td>价格：</td>
                       <td><input id="price02" name="price" type="text" required="required"> </td>
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


    <div class="modal size01" id="mymodal03">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/worker/deleteChargeStrategyById.action" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">删除</h4>
                    </div>
                    <div class="modal-body">
                        确定删除此行信息？
                        <input id="key" name="id" type="hidden">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary">确定</button>
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
  function transfer(strategyId,strategySpeed,strategyPrice) {
    var id = document.getElementById("strategyId");
    id.value=strategyId;
    var speed = document.getElementById("strategySpeed");
    speed.value=strategySpeed;
    var price = document.getElementById("strategyPrice");
    price.value=strategyPrice;
  }
  function change(strategyId) {
      var id = document.getElementById("key");
      id.value=strategyId;
  }
  $(function(){
    $(".btn01").click(function(){
      $("#mymodal02").modal("toggle");
    });
  });
  $(function(){
      $(".btn03").click(function(){
          $("#mymodal03").modal("toggle");
      });
  });
</script>
</body>

</html>