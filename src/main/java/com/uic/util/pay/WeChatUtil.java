package com.uic.util.pay;

import com.uic.util.AuthUtil;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Gitto on 2017/8/3.
 */
public class WeChatUtil {
    private static final Logger logger = Logger.getLogger(WeChatUtil.class);
    private static final String MCH_ID = "1432177402";
    private static final String TRADE_TYPE = "JSAPI";
    private static final String KEY = "w50tSU2cEsOHonmj460FQsIMkAKqRoPG";

    public static String getMchId() {
        return MCH_ID;
    }

    public static String getTradeType() {
        return TRADE_TYPE;
    }

    public static String getKEY() {
        return KEY;
    }

    /**
     * 统一下单
     * 获得PrePayId
     *
     * @param body         商品或支付单简要描述
     * @param out_trade_no 商户系统内部的订单号,32个字符内、可包含字母
     * @param total_fee    订单总金额，单位为分
     * @param notify_url   接收微信支付异步通知回调地址
     * @throws IOException
     */
    public static String unifiedorder(JSONObject object, String body, String out_trade_no, Integer total_fee, String ip, String notify_url, String openId) throws IOException {
        /**
         * 设置访问路径
         */
        HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
        String nonce_str = getNonceStr();//随机数据
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        /**
         * 组装请求参数
         * 按照ASCII排序
         */
        parameters.put("appid", AuthUtil.APPID);
        parameters.put("attach", object.toString());
        parameters.put("body", body);
        parameters.put("mch_id", MCH_ID);
        parameters.put("nonce_str", nonce_str);
        parameters.put("out_trade_no", out_trade_no);
        parameters.put("notify_url", notify_url);
        parameters.put("spbill_create_ip", ip);
        parameters.put("total_fee", total_fee.toString());
        parameters.put("trade_type", TRADE_TYPE);
        parameters.put("openid", openId);

        String sign = WxSign.createSign(parameters, KEY);

        /**
         * 组装XML
         */
        StringBuilder sb = new StringBuilder("");
        sb.append("<xml>");
        setXmlKV(sb, "appid", AuthUtil.APPID);
        setXmlKV(sb, "attach", object.toString());
        setXmlKV(sb, "body", body);
        setXmlKV(sb, "mch_id", MCH_ID);
        setXmlKV(sb, "nonce_str", nonce_str);
        setXmlKV(sb, "notify_url", notify_url);
        setXmlKV(sb, "out_trade_no", out_trade_no);
        setXmlKV(sb, "spbill_create_ip", ip);
        setXmlKV(sb, "total_fee", total_fee.toString());
        setXmlKV(sb, "trade_type", TRADE_TYPE);
        setXmlKV(sb, "sign", sign);
        setXmlKV(sb, "openid", openId);
        sb.append("</xml>");

        StringEntity reqEntity = new StringEntity(new String(sb.toString().getBytes("UTF-8"), "ISO8859-1"));//这个处理是为了防止传中文的时候出现签名错误
        httppost.setEntity(reqEntity);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httppost);
        String strResult = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));

        return strResult;

    }


    //获得随机字符串
    public static String getNonceStr() {
        Random random = new Random();
        return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
    }

    //插入XML标签
    public static StringBuilder setXmlKV(StringBuilder sb, String Key, String value) {
        sb.append("<");
        sb.append(Key);
        sb.append(">");

        sb.append(value);

        sb.append("</");
        sb.append(Key);
        sb.append(">");

        return sb;
    }

    //解析XML  获得 PrePayId
    public static String getPrePayId(String xml) {
        int start = xml.indexOf("<prepay_id>");
        int end = xml.indexOf("</prepay_id>");
        if (start < 0 && end < 0) {
            return null;
        }
        return xml.substring(start + "<prepay_id>".length(), end).replace("<![CDATA[", "").replace("]]>", "");
    }

    //商户订单号
    public static String getOut_trade_no() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return df.format(new Date()) + RandomUtil.generateString(3);
    }

    //时间戳
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    //随机4位数字
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    public static String inputStream2String(InputStream inStream, String encoding) {
        String result = null;
        try {
            if (inStream != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[1024];
                int count = -1;
                while ((count = inStream.read(tempBytes, 0, 1024)) != -1) {
                    outStream.write(tempBytes, 0, count);
                }
                tempBytes = null;
                outStream.flush();
                result = new String(outStream.toByteArray(), encoding);
            }
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public static void main(String[] args) {
        /*System.out.println(getOut_trade_no());*/
    }
}
