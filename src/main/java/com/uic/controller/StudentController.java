package com.uic.controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.uic.po.*;
import com.uic.po.String.RepairRecordCustomStr;
import com.uic.po.String.StudentDTOStr;
import com.uic.po.pay.WxPaySendData;
import com.uic.service.*;
import com.uic.util.AmountUtils;
import com.uic.util.AuthUtil;
import com.uic.util.StudentUtil;
import com.uic.util.DateUtil;
import com.uic.util.StudentUtil;
import com.uic.util.pay.HttpUtil;
import com.uic.util.pay.JsonUtil;
import com.uic.util.pay.WeChatUtil;
import com.uic.util.pay.WxSign;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * 学生端控制层
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private ChargeStrategyService chargeStrategyService;
    @Autowired
    private StudentDTOService studentDTOService;
    @Autowired
    private StudentOrderService studentOrderService;
    @Autowired
    private StudentRepairService studentRepairService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentBroadbandService studentBroadbandService;
    @Autowired
    private AccoutCancelService accoutCancelService;
    @Autowired
    private WeChatBindService weChatBindService;


    /*******************************************微信第三方业务********************************************************/
    /**
     * 用户授权
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping(value = "/weChatLogin",method = RequestMethod.GET)
    public void weChatLogin(HttpServletRequest req, HttpServletResponse resp)throws Exception {
        String backUrl = "http://www.shcvnet.cn/student/callBack.action";
        //获取请求方法索引
        String function = req.getParameter("function");
        //根据索引选择回调方法url
        switch (function) {
            case "personalInfo":
                backUrl = "http://www.shcvnet.cn/student/personalInfo.action";
                break;
            case "toinsertRepairRecord":
                backUrl = "http://www.shcvnet.cn/student/toinsertRepairRecord.action";
                break;
            case "findRepairRecordBystudentId":
                backUrl = "http://www.shcvnet.cn/student/findRepairRecordBystudentId.action";
                break;
            case "renewalFee":
                backUrl = "http://www.shcvnet.cn/student/renewalFee.action";
            default:
                break;
        }
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + AuthUtil.APPID +
                "&redirect_uri=" + URLEncoder.encode(backUrl) +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        resp.sendRedirect(url);
    }

    /**
     * 授权回调
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/callBack", method = RequestMethod.GET)
    public ModelAndView callBack(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        String code = req.getParameter("code");
        String openId = AuthUtil.getOpenId(code);
        return modelAndView;
    }

    /********************************************微信第三方绑定***********************************************************/

    /**
     * 绑定学号与openid
     * @param studentId
     * @param openId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/bindOpenId",method = RequestMethod.POST)
    public String bindOpenId(String studentId, String openId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            weChatBindService.BindOpenId(studentId, openId);
            jsonObject.put("state", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("state", "fail");
        }
        return jsonObject.toString();
    }

    /**
     * 验证学号密码
     * @param studentId
     * @param password
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/checkPassword",produces = "text/html;charset=UTF-8")
    public String checkPassword(String studentId,String password)throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (weChatBindService.checkUserInfo(studentId, password)) {
            jsonObject.put("state", "ok");
        } else {
            jsonObject.put("state", "fail");
        }
        return jsonObject.toString();
    }

    /**
     * 验证学号
     * @param studentId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/checkId",produces = "text/html;charset=UTF-8")
    public String checkStudentId(String studentId)throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (weChatBindService.checkStudentIdExist(studentId) == null) {
            jsonObject.put("state", "fail");
        } else {
            if (weChatBindService.checkStudentIdBindExist(studentId)) {
                jsonObject.put("state", "bind");
            } else {
                jsonObject.put("state", "ok");
            }
        }
        return jsonObject.toString();
    }

    /********************************************学生个人信息**********************************************************/

    /**
     * 学生个人信息
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/personalInfo", method = RequestMethod.GET)
    public ModelAndView personalInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //获取code
        String code = req.getParameter("code");
        //获取openid
        String openId = AuthUtil.getOpenId(code);
        try {
            StudentOpenId studentOpenId = weChatBindService.findStudentOpenId(openId);
            if (!studentOpenId.equals(null)) {
                StudentDTO studentDTO = studentDTOService.findStudentAndBroadbandById(studentOpenId.getUserId()).get(0);
                System.out.println("!!!!!"+studentDTO.toString());
                StudentDTOStr studentDTOStr=new StudentDTOStr();
                studentDTOStr = StudentUtil.studentDTOToStr(studentDTO);
                System.out.println("!!!!!!!!!"+studentDTOStr.toString());
                modelAndView.addObject("studentDTO", studentDTOStr);
                modelAndView.setViewName("/weChat/information");
            /*已绑定*/
            }
        } catch (Exception e) {
            e.printStackTrace();
            /*未绑定*/
            modelAndView.addObject("openId", openId);
            modelAndView.setViewName("/weChat/weixin-login");
        }
        return modelAndView;
    }

    /*******************************************微信公众号支付JSAPI******************************************************/

    /**
     * 用户进入支付页面
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping("/renewalFee")
    public ModelAndView renawalFee(HttpServletRequest req, HttpServletResponse resp)throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //获取code
        String code = req.getParameter("code");
        //获取openid
        String openId = AuthUtil.getOpenId(code);
        try {
            StudentOpenId studentOpenId = weChatBindService.findStudentOpenId(openId);
            if (!studentOpenId.equals(null)) {
                /*已绑定*/
                StudentDTO studentDTO = studentDTOService.findStudentAndBroadbandById(studentOpenId.getUserId()).get(0);
                if (studentOrderService.hasBeenOpen(studentOpenId.getUserId())) {
                    ChargeStrategy chargeStrategy = chargeStrategyService.findChargeStrategyBySpeed(studentDTO.getStudentBroadband().getSpeed());
                    modelAndView.addObject("chargeStrategy", chargeStrategy);
                    modelAndView.addObject("openId", openId);
                    modelAndView.setViewName("/weChat/payPage");
                } else {
                    modelAndView.setViewName("error");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            /*未绑定*/
            modelAndView.addObject("openId", openId);
            modelAndView.setViewName("/weChat/weixin-login");
        }
        return modelAndView;
    }

    /**
     * ajax 向微信发送统一下单请求
     * @param request
     * @param openId
     * @param cho
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/pay",produces = "text/html;charset=UTF-8")
    public String prePay(HttpServletRequest request, String openId, String cho) throws Exception {
        WxPaySendData result = new WxPaySendData();
        try {
            //商户订单号
            /*//我修改过
            String nowDate=DateUtil.getDate();
            Date topDate= DateUtil.addStartTime(DateUtil.convertStringToDate(nowDate));
            Date buttomDate=DateUtil.addEndTime(DateUtil.convertStringToDate(nowDate));
            //查找当天存在多少天订单数据，订单号尾号则在这条数据上加1
            int numToDay=studentOrderService.findStudentOrderCountByDate(topDate,buttomDate);*/
            String out_trade_no = WeChatUtil.getOut_trade_no();
            //产品价格,单位：分
            StudentOpenId studentOpenId = weChatBindService.findStudentOpenId(openId);
            StudentDTO studentDTO = studentDTOService.findStudentAndBroadbandById(studentOpenId.getUserId()).get(0);
            ChargeStrategy chargeStrategy = chargeStrategyService.findChargeStrategyBySpeed(studentDTO.getStudentBroadband().getSpeed());
            double fee = 1;
            Integer month = 0;
            switch (cho) {
                case "1":
                    fee = chargeStrategy.getPrice() * 100 * 1;
                    month = 1;
                    break;
                case "2":
                    fee = chargeStrategy.getPrice() * 100 * 2;
                    month = 2;
                    break;
                case "3":
                    fee = chargeStrategy.getPrice() * 100 * 3;
                    month = 3;
                    break;
                case "4":
                    fee = chargeStrategy.getPrice() * 100 *4;
                    month = 4;
                    break;
                case "5":
                    fee = chargeStrategy.getPrice() * 100 * 5;
                    month = 10;
                    break;
                case "6":
                    fee = chargeStrategy.getPrice() * 100 * 10;
                    month = 12;
                    break;
                default:
                    break;
            }
            Integer total_fee = (new Double(fee)).intValue();
            //客户端ip
            String ip = HttpUtil.getIpAddress(request);
            //支付成功后回调的url地址
            String notify_url = "http://www.shcvnet.cn/student/payCallBack.action";
            //微信返回的支付详细
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fee", fee);
            jsonObject.put("studentId", studentOpenId.getUserId());
            jsonObject.put("month", month);
            jsonObject.put("out_trade_no", out_trade_no);
            jsonObject.put("time", df.format(new Date()));
            //统一下单
            String strResult = WeChatUtil.unifiedorder(jsonObject,"wx-pay", out_trade_no, total_fee, ip, notify_url, openId);
            //解析xml
            XStream stream = new XStream(new DomDriver());
            stream.alias("xml", WxPaySendData.class);
            WxPaySendData wxReturnData = (WxPaySendData)stream.fromXML(strResult);
            //两者都为SUCCESS才能获取prepay_id
            if (wxReturnData.getResult_code().equals("SUCCESS") && wxReturnData.getReturn_code().equals("SUCCESS")) {
                //业务逻辑
                String timeStamp = WeChatUtil.getTimeStamp();//时间戳
                String nonce_str = WeChatUtil.getNonceStr();//随机字符串

                result.setResult_code(wxReturnData.getResult_code());
                result.setAppid(AuthUtil.APPID);
                result.setTimeStamp(timeStamp);
                result.setNonce_str(nonce_str);
                result.setPackageStr("prepay_id=" + wxReturnData.getPrepay_id());
                result.setSignType("MD5");

                //第二次签名,将微信返回的数据再进行签名
                SortedMap<Object, Object> signMap = new TreeMap<Object, Object>();
                signMap.put("appId", AuthUtil.APPID);
                signMap.put("timeStamp", timeStamp);
                signMap.put("nonceStr", nonce_str);
                signMap.put("package", "prepay_id=" + wxReturnData.getPrepay_id());
                signMap.put("signType", "MD5");
                //支付签名
                String paySign = WxSign.createSign(signMap, WeChatUtil.getKEY());

                result.setSign(paySign);
            } else {
                result.setResult_code("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JsonUtil.objectToJson(result));
        return JsonUtil.objectToJson(result);
    }

    @RequestMapping("/payCallBack")
    public void payCallBack(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>this is callBack");

        response.setContentType("text/xml;charset=UTF-8");
        try {
            InputStream is = request.getInputStream();
            String result = IOUtils.toString(is, "UTF-8");
            if ("".equals(result)) {
                response.getWriter().write("<xm><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[参数错误！]]></return_msg></xml>");
                return;
            }

            //解析xml
            XStream stream = new XStream(new DomDriver());
            stream.alias("xml", WxPaySendData.class);
            WxPaySendData wxPaySendData = (WxPaySendData) stream.fromXML(result);

            String appid = wxPaySendData.getAppid();
            String attach = wxPaySendData.getAttach();
            String mch_id = wxPaySendData.getMch_id();
            String nonce_str = wxPaySendData.getNonce_str();
            String out_trade_no = wxPaySendData.getOut_trade_no();
            String total_fee = wxPaySendData.getTotal_fee();
            //double money = DBUtil.getDBDouble(DBUtil.getDBInt(wxPaySendData.getTotal_fee())/100.0);
            String trade_type = wxPaySendData.getTrade_type();
            String openid = wxPaySendData.getOpenid();
            String return_code = wxPaySendData.getReturn_code();
            String result_code = wxPaySendData.getResult_code();
            String bank_type = wxPaySendData.getBank_type();
            Integer cash_fee = wxPaySendData.getCash_fee();
            String fee_type = wxPaySendData.getFee_type();
            String is_subscribe = wxPaySendData.getIs_subscribe();
            String time_end = wxPaySendData.getTime_end();
            String transaction_id = wxPaySendData.getTransaction_id();
            String sign = wxPaySendData.getSign();

            //签名验证
            SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
            parameters.put("appid", appid);
            parameters.put("attach", attach);
            parameters.put("mch_id", mch_id);
            parameters.put("nonce_str", nonce_str);
            parameters.put("out_trade_no", out_trade_no);
            parameters.put("total_fee", total_fee);
            parameters.put("trade_type", trade_type);
            parameters.put("openid", openid);
            parameters.put("return_code", return_code);
            parameters.put("result_code", result_code);
            parameters.put("bank_type", bank_type);
            parameters.put("cash_fee", cash_fee);
            parameters.put("fee_type", fee_type);
            parameters.put("is_subscribe", is_subscribe);
            parameters.put("time_end", time_end);
            parameters.put("transaction_id", transaction_id);

            String sign2 = WxSign.createSign(parameters, WeChatUtil.getKEY());
            if (sign.equals(sign2)) {
                if (return_code.equals("SUCCESS") && result_code.equals("SUCCESS")) {
                    //业务逻辑

                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+attach);
                    JSONObject jsonObject = JSONObject.fromObject(attach);

                    //获得学号
                    String studentId=jsonObject.getString("studentId");
                    //获得订单号
                    String orderId=jsonObject.getString("out_trade_no");
                    //获得续费月份
                    int openMonth=Integer.valueOf(jsonObject.getString("month"));
                    //获得开通时间
                    String printDate=jsonObject.getString("time");
                    //获得到期时间
                    StudentOrder studentOrderTop=studentOrderService.findStudentOrderTopById(studentId);//获得该学生最近的一条订单记录，叠加到期时间
                    String endDate=DateUtil.addMonth(DateUtil.convertDateToString(studentOrderTop.getEndTime()),Integer.valueOf(openMonth));
                    //获得费用
                    double money=Double.valueOf(AmountUtils.changeF2Y(jsonObject.getString("fee")));

                    //插入订单信息表中
                    List<StudentDTO> studentDTOList=studentDTOService.findStudentAllInfoById(studentId);
                    StudentDTO studentDTO=studentDTOList.get(0);
                    StudentOrder studentOrder=new StudentOrder();
                    studentOrder.setStudentId(studentId);
                    studentOrder.setName(studentDTO.getName());
                    studentOrder.setDorm(studentDTO.getDorm());
                    studentOrder.setUserId(studentId);
                    String idCard=studentDTO.getIdCard();
                    String password=idCard.substring(idCard.length()-8,idCard.length());
                    studentOrder.setUserPassword(password);
                    studentOrder.setSpeed(studentDTO.getStudentBroadband().getSpeed());
                    studentOrder.setOrderId(orderId);
                    studentOrder.setWorkerId("wx");
                    studentOrder.setMoney(money);
                    studentOrder.setLastTime(openMonth);
                    studentOrder.setPrintTime(DateUtil.convertStringToDate("yyyy-MM-dd",printDate));
                    studentOrder.setEndTime(DateUtil.convertStringToDate("yyyy-MM-dd",endDate));
                    studentOrderService.insertStudentOrder(studentOrder);
                    //插入订单作废状态表中
                    AccoutCancel accoutCancel=new AccoutCancel();
                    accoutCancel.setStudentId(studentOrder.getStudentId());
                    accoutCancel.setOrderId(studentOrder.getOrderId());
                    accoutCancel.setResultFlag(0);
                    accoutCancelService.insertAccoutCancel(accoutCancel);
                    //更新宽带信息表
                    StudentBroadband studentBroadband=new StudentBroadband();
                    studentBroadband.setAccoutNumber(studentDTO.getStudentBroadband().getAccoutNumber());
                    studentBroadband.setAccessNumber(studentDTO.getStudentBroadband().getAccessNumber());
                    studentBroadband.setStudentId(studentOrder.getStudentId());
                    studentBroadband.setSpeed(studentDTO.getStudentBroadband().getSpeed());
                    studentBroadband.setLastTime(Integer.valueOf(openMonth));
                    studentBroadband.setStartTime(DateUtil.convertStringToDate("yyyy-MM-dd",printDate));
                    studentBroadband.setEndTime(DateUtil.convertStringToDate("yyyy-MM-dd",endDate));
                    studentBroadband.setMoney(studentOrder.getMoney());
                    studentBroadbandService.insertOrUpdateStudentBroadband(studentBroadband);
                    System.out.println("!!!!!!!!!!!"+studentDTO.toString());
                    /*************************************************************************************************************************/
                    //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                    response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
                } else {
                    System.out.println("交易失败");
                    response.getWriter().write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[交易失败]]></return_msg></xml>");
                }
            } else {
                System.out.println("签名错误");
                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                response.getWriter().write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名校验失败]]></return_msg></xml>");
            }
            response.getWriter().flush();
            response.getWriter().close();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /******************************************学生端报修模块***********************************************************/
    /******************************************报修信息录入***********************************************************/

    /**
     * 跳转报修信息插入界面
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/toinsertRepairRecord")
    public ModelAndView toinsertRepairRecord(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //获取code
        String code = req.getParameter("code");
        //获取openid
        String openId = AuthUtil.getOpenId(code);
        try {
            StudentOpenId studentOpenId = weChatBindService.findStudentOpenId(openId);
            if (!studentOpenId.equals(null)) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>微信报修模块中>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                Student student = studentService.findrepairStudent(studentOpenId.getUserId());
                modelAndView.addObject("student", student);
                modelAndView.setViewName("/weChat/repairInput");
            /*已绑定*/
            }
        } catch (Exception e) {
            e.printStackTrace();
            /*未绑定*/
            modelAndView.addObject("openId", openId);
            modelAndView.setViewName("/weChat/weixin-login");
        }
        return modelAndView;
    }

    /**
     * 插入学生报修信息
     * @param repairRecord
     * @return
     * @throws Exception
     */
    @RequestMapping("/insertRepairRecord")
    public ModelAndView insertRepairRecord(RepairRecord repairRecord) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        String nowTime = DateUtil.getDateTime();
        Date startDate = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", nowTime);
        repairRecord.setAddTime(startDate);
        studentRepairService.insertRepairRecord(repairRecord);

        RepairRecordCustom repairRecordCustom= studentRepairService.findRepairRecordByStudentIdAndrepairContent(repairRecord.getStudentId(),repairRecord.getRepairContent()).get(0);
        RepairRecordCustomStr repairRecordCustomStr=StudentUtil.repairRecordCustomToStr(repairRecordCustom);
        modelAndView.addObject("repairRecordCustomStr",repairRecordCustomStr);
        modelAndView.setViewName("/weChat/repairForSure");
        return modelAndView;
    }

    /**
     * 根据studentID 与报修内容删除对应报修信息
     * @param studentId
     * @param repairContent
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteRepairRecord")
    public ModelAndView deleteRepairRecord(String studentId,String repairContent)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        studentRepairService.deleteRepairRecordByStudentIdAndrepairContent(studentId,repairContent);
        modelAndView.setViewName("/weChat/repairInput");
        return modelAndView;
    }

    /******************************************报修信息修改***********************************************************/

    /**
     * 查询学生全部个人报修信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRepairRecordBystudentId")
    public ModelAndView findRepairRecordBystudentId(HttpServletRequest req, HttpServletResponse resp)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        //获取code
        String code = req.getParameter("code");
        //获取openid
        String openId = AuthUtil.getOpenId(code);

        try {
            StudentOpenId studentOpenId = weChatBindService.findStudentOpenId(openId);
            if (!studentOpenId.equals(null)) {
                List<RepairRecordCustom> repairRecordCustomsList = new ArrayList<RepairRecordCustom>();
                repairRecordCustomsList = studentRepairService.findRepairRecordByStudentId(studentOpenId.getUserId());
                List<RepairRecordCustomStr> repairRecordCustomStrList=new ArrayList<RepairRecordCustomStr>();
                RepairRecordCustomStr repairRecordCustomStr;

                for (RepairRecordCustom repairRecordCustom : repairRecordCustomsList) {
                    repairRecordCustomStr=new RepairRecordCustomStr();
                    repairRecordCustomStr= StudentUtil.repairRecordCustomToStr(repairRecordCustom);
                    repairRecordCustomStrList.add(repairRecordCustomStr);
                    System.out.println(repairRecordCustom);
                }
                modelAndView.addObject("repairRecordCustomStrList", repairRecordCustomStrList);
                modelAndView.setViewName("/weChat/repairInfoAll");
            /*已绑定*/
            }
        } catch (Exception e) {
            e.printStackTrace();
            /*未绑定*/
            modelAndView.addObject("openId", openId);
            modelAndView.setViewName("/weChat/weixin-login");
        }
        return modelAndView;
    }

    /**
     * 根据学生Id与报修内容查询唯一一条记录
     * @param studentId
     * @param repairContent
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRepairRecordByStudentIdAndrepairContent")
    public ModelAndView findRepairRecordByStudentIdAndrepairContent(String studentId,String repairContent)throws Exception{
        ModelAndView modelAndView = new ModelAndView();

        RepairRecordCustom repairRecordCustom = studentRepairService.findRepairRecordByStudentIdAndrepairContent(studentId,repairContent).get(0);
        RepairRecordCustomStr repairRecordCustomStr = StudentUtil.repairRecordCustomToStr(repairRecordCustom);

        modelAndView.addObject("repairRecordCustomStr", repairRecordCustomStr);
        modelAndView.setViewName("/weChat/repairInfo");
        return modelAndView;
    }
}
