<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" content="user-scalable=0;" name="viewport" />
    <title>小镇网络服务平台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/weui.css">
    <style>
        .imag{  width: 100%; height: 30%; }
        .title02{ font-size: 2.9rem;color: black; }
        .title03{ background-color: #F0F0F0; color: #3CC51F;width: 100%;position: relative;
            top:-0.4rem; font-size: 2.8rem;font-family: "宋体"; }
        .title04{font-size: 2rem;}
        .blank{width: 100%;font-size: 1rem;}
        .button00{width: 96%;margin: 0 auto; position: relative;left: 1.5%;}
        .button01{background-color: white;border: solid green 1px;  width: 31.5%; height: 7rem;
            font-size: 2.7rem; border-radius: 1rem;outline:none;}
        .color01{color: #10AEFF;}
        .submit{height: 6.2rem; font-size: 2em;}
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.12.0.min.js"></script>
    <script>
        var prepay_id ;
        var sign ;
        var appId ;
        var timeStamp ;
        var nonceStr ;
        var packageStr ;
        var signType ;

        function setCho(id) {
            $("#cho").val(id);
            pay();
        }

        function pay(){
            var url = '${pageContext.request.contextPath}/student/pay.action';
            $.ajax({
                type:"post",
                data:{
                    cho:$("#cho").val(),
                    openId:$("#openId").val()
                },
                url:url,
                dataType:"json",
                success:function(data) {
                    if(data.result_code == 'SUCCESS'){
                        appId = data.appid;
                        sign = data.sign;
                        timeStamp = data.timeStamp;
                        nonceStr = data.nonce_str;
                        packageStr = data.packageStr;
                        signType = data.signType;
                        //调起微信支付控件
                        callpay();
                    }else{
                        alert("统一下单失败");
                    }
                }
            });
        }

        function onBridgeReady(){
            WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId":appId,     //公众号名称，由商户传入
                    "paySign":sign,         //微信签名
                    "timeStamp":timeStamp, //时间戳，自1970年以来的秒数
                    "nonceStr":nonceStr , //随机串
                    "package":packageStr,  //预支付交易会话标识
                    "signType":signType     //微信签名方式
                },
                function(res){
                    /*alert(JSON.stringify(res));//出错可以在这里看看.....*/
                    if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                        //window.location.replace("index.html");
                        alert('支付成功');
                        JavaScript:WeixinJSBridge.call('closeWindow');
                    }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
                        /*alert('支付取消');*/
                    }else if(res.err_msg == "get_brand_wcpay_request:fail" ){
                        alert('支付失败');
                    }
                    //使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                }
            );
        }

        function callpay(){
            if (typeof WeixinJSBridge == "undefined"){
                if( document.addEventListener ){
                    document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
                }else if (document.attachEvent){
                    document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                    document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
                }
            }else{
                onBridgeReady();
            }
        }
    </script>
</head>
<body>

<div class="imag">
    <img src="${pageContext.request.contextPath}/resource/img/1.jpg" id="pic" height="100%" width="100%"/>
</div>
<div class="title03" align="center">
    <div class="blank">&nbsp;</div>
    网络续费
    <div class="blank">&nbsp;</div>
</div><br>

<div class="weui-cell">
    <div class="title02">网络速度&nbsp;&nbsp;<span class="color01">${chargeStrategy.speed}/M</span></div>
</div><br><br>

<div class="  button00">
    <button id="1" class="button01" onClick="change(this.id);setCho(this.id);">1个月/<span>${chargeStrategy.price}元</span></button>&nbsp;
    <button id="2" class="button01" onClick="change(this.id);setCho(this.id);">2个月/<span>${chargeStrategy.price*2}元</span></button>&nbsp;
    <button id="3" class="button01" onClick="change(this.id);setCho(this.id);">3个月/<span>${chargeStrategy.price*3}元</span></button><br><br><br>
    <button id="4" class="button01" onClick="change(this.id);setCho(this.id);">4个月/<span>${chargeStrategy.price*4}元</span></button>&nbsp;
    <button id="5" class="button01" onClick="change(this.id);setCho(this.id);">半年/<span>${chargeStrategy.price*5}元</span></button>&nbsp;
    <button id="6" class="button01" onClick="change(this.id);setCho(this.id);">1年/<span>${chargeStrategy.price*10}元</span></button><br><br><br>
    <input type="hidden" id="cho">
    <input type="hidden" id="openId" value="${openId}">
</div>

<div class="blank">&nbsp;</div>
<div class="blank">&nbsp;</div>

<div class="weui-cells weui-cells_form ">
    <%--<div class="page__bd page__bd_spacing ">
        <input type="submit"  value="提交" class="weui-btn weui-btn_primary submit" >
    </div>--%>

    <div class="blank">&nbsp;</div>

    <div class="page">
        <div class="page__bd page__bd_spacing">
            <div class="weui-footer">
                <p class="weui-footer__text title04">小镇网络服务平台</p>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    var a = 1;
    function changeImg()
    {
        var imgset = document.getElementById("pic");
        imgset.src = "${pageContext.request.contextPath}/resource/img/"+ a + ".jpg";
        a++;
        if(a==3)
        {
            a=1;
        }
    }
    setInterval("changeImg()", 3000);

    function change (id_) {
        var button = document.getElementById(id_);
        button.style.border = "red 2px solid";
        var buttons = document.getElementsByTagName("button");
        for (var i = 0; i < buttons.length; i++) {
            var button_ = buttons[i];
            if (id_ != button_.id) {
                button_.style.border = "green 1px solid";
            }
        }
    }
</script>

</body>
</html>
