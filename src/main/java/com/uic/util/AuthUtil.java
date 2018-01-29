package com.uic.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Gitto on 2017/7/26.
 */
public class AuthUtil {public static final String APPID = "wx46b790f82e798d2a";
    public static final String APPSECRET = "97ce1b99a459f29321ca8bb6ebe79404";
    public static JSONObject doGetJson(String url) throws IOException {
        JSONObject jsonObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response=client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSONObject.fromObject(result);
        }
        httpGet.releaseConnection();
        return jsonObject;
    }

    /**
     * 获取微信用户openId
     * @param code
     * @return
     * @throws Exception
     */
    public static String getOpenId(String code)throws Exception {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID +
                "&secret=" + APPSECRET +
                "&code=" + code +
                "&grant_type=authorization_code";
        JSONObject jsonObject = doGetJson(url);
        String openId = jsonObject.getString("openid");

        //获取微信用户信息
        /*String token = jsonObject.getString("access_token");
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token +
                "&openid=" + openId +
                "&lang=zh_CN";
        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);*/

        return openId;
    }
}
