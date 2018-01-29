package com.uic.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uic.po.*;
import com.uic.po.String.RepairRecordCustomStr;
import com.uic.po.String.StudentDTOStr;
import com.uic.po.String.StudentOrderStr;
import com.uic.service.*;
import com.uic.util.DateUtil;
import com.uic.util.StringUtil;
import com.uic.util.StudentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * 基于职工角色的Controller
 */
@Controller
@RequestMapping("/worker")
public class WorkerController {
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
    private StudentOpenIdService studentOpenIdService;


    /****************************************************用户开通管理模块***********************************************/
    /**********************************Excel表批量导入导出管理**********************************/

    /**
     * 上传Excel文件到服务器
     * 读取数据，导入数据库
     *
     * @param multipartFile
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request, HttpSession session) throws Exception {
        String oldfileName = multipartFile.getOriginalFilename();
        System.out.println("原始文件名:" + oldfileName);
        String newFileName = UUID.randomUUID() + oldfileName;//随机命名新名字
        //String filePath = "E:\\temp\\upload\\";
        String filePath= "C:\\excel\\upload\\";
        File file = new File(filePath + newFileName);
        multipartFile.transferTo(file);
        studentDTOService.uploadExcel(filePath + newFileName);
        //将开通的信息导入订单表中（studentOrder）
        List<StudentDTO> studentDTOList = studentDTOService.queryStudentAndBroadband();
        StudentOrder studentOrder;
        StudentBroadband studentBroadband;
        for (StudentDTO studentDTO : studentDTOList) {
            studentBroadband = new StudentBroadband();
            studentBroadband = studentDTO.getStudentBroadband();
            if (studentBroadband != null) {
                //如果这些信息不存在，则不录入订单表中。
                String startTime = DateUtil.convertDateToString(studentBroadband.getStartTime());
                String endTime = DateUtil.convertDateToString(studentBroadband.getEndTime());
                if ((!studentBroadband.getAccessNumber().equals("") && studentBroadband.getAccessNumber() != null)
                        && (!studentBroadband.getAccoutNumber().equals("") && studentBroadband.getAccoutNumber() != null)
                        && (studentBroadband.getSpeed() != 0)
                        && (studentBroadband.getMoney() != 0.0)
                        && (studentBroadband.getLastTime() != 0)
                        ) {
                    studentOrder = new StudentOrder();
                    studentOrder.setStudentId(studentDTO.getStudentId());
                    studentOrder.setName(studentDTO.getName());
                    studentOrder.setDorm(studentDTO.getDorm());
                    studentOrder.setUserId(studentDTO.getStudentId());
                    //从身份证号中获取初始密码
                    //4415259887 56027895
                    String idCard = studentDTO.getIdCard();
                    String password = idCard.substring(idCard.length() - 8, idCard.length());
                    studentOrder.setUserPassword(password);
                    studentOrder.setSpeed(studentDTO.getStudentBroadband().getSpeed());
                    studentOrder.setPrintTime(studentDTO.getStudentBroadband().getStartTime());
                    studentOrder.setLastTime(studentDTO.getStudentBroadband().getLastTime());
                    studentOrder.setEndTime(studentDTO.getStudentBroadband().getEndTime());
                    studentOrder.setMoney(studentDTO.getStudentBroadband().getMoney());

                    //生成订单号
                    //yyyy-mm-dd
                    //与上面的判断，再判断一次，如果时间为空，则不作订单号生成操作，并且不插入订单表中
                    if (studentOrder.getPrintTime() != null && !studentOrder.getPrintTime().equals("") && studentOrder.getEndTime() != null && !studentOrder.getEndTime().equals("")) {
                        String workerId = (String) session.getAttribute("userId");
                        String dateStr = DateUtil.convertDateToString(studentDTO.getStudentBroadband().getStartTime());
                        String year = dateStr.substring(0, 4);
                        String month = dateStr.substring(5, 7);
                        String day = dateStr.substring(8, 10);
                        String orderId = studentOrderService.createOrderId(dateStr, year, month, day, workerId);
                        studentOrder.setWorkerId(workerId);
                        studentOrder.setOrderId(orderId);
                        //插入订单表中，新生批量开通
                        studentOrderService.insertStudentOrder(studentOrder);
                        //插入订单作废表中
                        AccoutCancel accoutCancel = new AccoutCancel();
                        accoutCancel.setStudentId(studentOrder.getStudentId());
                        accoutCancel.setOrderId(orderId);
                        accoutCancel.setResultFlag(0);
                        accoutCancelService.insertAccoutCancel(accoutCancel);

                    }
                }
            }
        }


        //导入数据库后，就可以删除excel表了
        /*if (file.exists()&&file.isFile()){
            if (file.delete()){
                System.out.println("删除文件成功");
            }else {
                System.out.println("删除文件失败");
            }
        }else {
            System.out.println("找不到文件");
        }*/

        return "redirect:/worker/index.action";
    }

    /**
     * 导出Excel文件到服务器
     * 从服务器下载文件到本地
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/downloadExcel")
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = "C:\\excel\\download";
        String encoding = "utf-8";

        Date date = new Date();
        String fileName = DateUtil.getDateTime();
        fileName = StringUtil.convertToh(fileName);

        studentDTOService.downloadExcel(fileName, filePath);
        File file = new File(filePath + fileName);
        String filePathName = filePath + "\\" + fileName + ".xlsx";
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(filePathName)));
        //转码，免得文件名中文乱码
        fileName = URLEncoder.encode(fileName, "UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while ((len = bis.read()) != -1) {
            out.write(len);
            out.flush();
        }
        out.close();

    }

    /**
     * excel模板导出
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/downloadModel")
    public void downloadModel(HttpServletRequest request, HttpServletResponse response)throws Exception{

        String filePath = "C:\\excel\\model";
        String encoding = "utf-8";

        String fileName = "导入模板";
        String filePathName = filePath + "\\" + fileName + ".xlsx";
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(filePathName)));
        //转码，免得文件名中文乱码
        fileName = URLEncoder.encode(fileName, "UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while ((len = bis.read()) != -1) {
            out.write(len);
            out.flush();
        }
        out.close();

    }


    /**********************************学生基本信息和宽带信息管理**********************************/

    /**
     * 根据学号的前缀删除该届学生的所有信息，比如14
     * 不是逻辑删除
     * @param id
     * @return
     * @throws Exception
     * /worker/deleteAllInfoById.action
     */
    @RequestMapping("deleteAllInfoById")
    public String deleteAllInfoById(String id)throws Exception{
        //删除学生基本信息
        studentService.deleteStudentTrue(id);
        //删除学生宽带信息
        studentBroadbandService.deleteStudentBroadbandTrue(id);
        //删除用户信息
        userService.deleteUserTrue(id);
        //删除学生订单信息
        studentOrderService.deleteStudentOrderTrue(id);
        //删除学生订单作废表信息
        accoutCancelService.deleteAccoutCancelTrue(id);
        //删除用户报修记录信息
        studentRepairService.deleteRepairRecordTrue(id);
        //删除用户的openId
        studentOpenIdService.deleteStudentOpenIdTrue(id);



        return "redirect:/worker/index.action";
    }

    /**
     * 信息处理首页
     * 遍历学生的信息（基本信息、宽带信息）
     *
     * @return
     * @throws Exception /worker/index.action
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //分页
        String pageNum = request.getParameter("pageNum");
        String pageSize =request.getParameter("pageSize");
        //默认起始页是第一页，每页10条数据
        int num = 1;
        int size = 10;
        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        PageHelper.startPage(num, size);

        //获得studentDTOList
        List<StudentDTO> studentDTOList = new ArrayList<StudentDTO>();
        studentDTOList = studentDTOService.queryStudentAndBroadband();
        //获得studentDTO的分页数据
        PageInfo<StudentDTO> pagehelper = new PageInfo<StudentDTO>(studentDTOList);
        //获得studentDTOStrList
        //控制台报错aDate is null，应该是遍历返回的结果中user为null，又赋值给StudentDTO的List,所以报错，但是不影响结果
        //解决办法是创建一个新的dto类,只有student表和studentBroadband表的信息，没有user
        List<StudentDTOStr> studentDTOStrList = new ArrayList<StudentDTOStr>();
        StudentDTOStr studentDTOStr;
        List<StudentDTO> studentDTOPage=pagehelper.getList();
        for (StudentDTO studentDTO : studentDTOPage) {

            System.out.println(studentDTO.toString());
            studentDTOStr = new StudentDTOStr();
            studentDTOStr = StudentUtil.studentDTOToStr(studentDTO);
            studentDTOStrList.add(studentDTOStr);
        }
        //获得收费策略列表
        List<ChargeStrategy> strategyList=chargeStrategyService.queryChargeStrategy();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("strategyList",strategyList);
        //用于显示数据
        modelAndView.addObject("studentDTOStrList", studentDTOStrList);
        //用于分页查询
        modelAndView.addObject("pagehelper",pagehelper);
        modelAndView.setViewName("useropen/userOpenIndex");

        return modelAndView;

    }

    /**
     * 信息处理
     * 根据学生ID来查询学生的所有信息（基本信息、宽带信息）
     *
     * @param studentId
     * @return
     * @throws Exception /worker/findStudentIndex.action
     */
    @RequestMapping("/findStudentIndex")
    public ModelAndView findStudentIndex(String studentId) throws Exception {
        List<StudentDTO> studentDTOList = new ArrayList<StudentDTO>();
        List<StudentDTOStr> studentDTOStrList = new ArrayList<StudentDTOStr>();
        StudentDTOStr studentDTOStr;

        studentDTOList = studentDTOService.findStudentAndBroadbandById(studentId);
        //测试
        for (StudentDTO studentDTO : studentDTOList) {
            System.out.println(studentDTO.toString());
            studentDTOStr = new StudentDTOStr();
            studentDTOStr = StudentUtil.studentDTOToStr(studentDTO);
            studentDTOStrList.add(studentDTOStr);
        }

        //获得收费策略列表
        List<ChargeStrategy> strategyList=chargeStrategyService.queryChargeStrategy();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("strategyList",strategyList);
        modelAndView.addObject("studentDTOStrList", studentDTOStrList);
        modelAndView.setViewName("useropen/userOpenIndex");

        return modelAndView;
    }

    /**
     * 信息处理
     * 单次插入学生基本信息（学生表）
     *
     * @param student
     * @return
     * @throws Exception /worker/insertStudentIndex.action
     */
    @RequestMapping("/insertStudentIndex")
    public String insertStudentIndex(Student student) throws Exception {
        //TODO 修改好了，如果存在则不作为，但是最好就给一个flag给前端，弹出错误页面
        StudentBroadband studentBroadband = new StudentBroadband();
        int flag=studentService.findStudentCountById(student.getStudentId());
        if (flag==0){
            studentService.insertStudent(student);
            studentBroadband.setStudentId(student.getStudentId());
            studentBroadbandService.insertStudentBroadband(studentBroadband);
        }

        return "redirect:/worker/index.action";
    }

    /**
     * 信息处理
     * 根据学生的学号更新学生的基本信息和宽带信息（不涉及密码）
     *
     * @param id
     * @param studentDTO
     * @return
     * @throws Exception /worker/updateStudentIndex.action
     */
    @RequestMapping("/updateStudentIndex")
    public String updateStudentIndex(String id, String speed, String money, String lastTime,
                                     String startTime, String endTime, StudentDTO studentDTO) throws Exception {
        //更新学生表信息和宽带信息表的信息
        id = studentDTO.getStudentId();
        StudentBroadband studentBroadband = studentDTO.getStudentBroadband();
        //如果为空则不插入数据
        if (StringUtil.isNotEmpty(speed)) {
            studentBroadband.setSpeed(Integer.valueOf(speed));
        }
        if (StringUtil.isNotEmpty(money)) {
            studentBroadband.setMoney(Double.valueOf(money));
        }
        if (StringUtil.isNotEmpty(lastTime)) {
            studentBroadband.setLastTime(Integer.valueOf(lastTime));
        }
        if (StringUtil.isNotEmpty(startTime)) {
            studentBroadband.setStartTime(DateUtil.convertStringToDate(startTime));
        }
        if (StringUtil.isNotEmpty(endTime)) {
            studentBroadband.setEndTime(DateUtil.convertStringToDate(endTime));
        }
        studentDTO.setStudentBroadband(studentBroadband);
        studentDTOService.updateStudentAllInfoById(id, studentDTO);

        //更新订单表的姓名和房号信息（当初设计不当）
        StudentOrder studentOrder=new StudentOrder();
        studentOrder.setName(studentDTO.getName());
        studentOrder.setDorm(studentDTO.getDorm());
        studentOrderService.updateStudentOrderSomeById(id,studentOrder);



        return "redirect:/worker/findStudentIndex.action?studentId="+id;
    }

    /**
     * 信息处理
     * 根据学生id来删除学生基本信息和宽带信息
     * （涉及到student和studentBroadband、studentOrder、accountCancel四个个表）
     * 同步删除
     * @param studentId
     * @return
     * @throws Exception /worker/deleteStudentIndex.action
     */
    @RequestMapping("/deleteStudentIndex")
    public String deleteStudentIndex(String studentId) throws Exception {

        studentDTOService.deleteStudentAllInfoById(studentId);
        studentOrderService.deleteStudentOrderById(studentId);
        accoutCancelService.deleteAccoutCancelById(studentId);

        return "redirect:/worker/index.action";
    }

    /**
     * 新生开通首页
     * 遍历学生表、宽带信息表（两表）
     *
     * @return
     * @throws Exception
     * /worker/openIndex.action
     */
    @RequestMapping("/openIndex")
    public ModelAndView openIndex(HttpServletRequest request,HttpServletResponse response) throws Exception {

        //分页
        String pageNum = request.getParameter("pageNum");
        String pageSize =request.getParameter("pageSize");
        //默认起始页是第一页，每页10条数据
        int num = 1;
        int size = 10;
        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        PageHelper.startPage(num, size);

        //获得studentDTOList
        List<StudentDTO> studentDTOList = new ArrayList<StudentDTO>();
        studentDTOList = studentDTOService.queryStudentAndBroadbandByNotInOrder();
        //获得studentDTO的分页数据
        PageInfo<StudentDTO> pagehelper = new PageInfo<StudentDTO>(studentDTOList);

        //得到收费策略
        List<ChargeStrategy> strategyList = chargeStrategyService.queryChargeStrategy();
        //测试
        for (StudentDTO sList : studentDTOList) {
            System.out.println(sList.toString());
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("studentDTOList", studentDTOList);
        modelAndView.addObject("pagehelper", pagehelper);
        modelAndView.addObject("strategyList", strategyList);
        modelAndView.setViewName("useropen/newOpen");

        return modelAndView;

    }

    /**
     * 新生开通
     * 根据学号遍历学生表、宽带信息表（两表）
     *
     * @param studentId
     * @return
     * @throws Exception /worker/findStudentOpenIndex.action
     */
    @RequestMapping("/findStudentOpenIndex")
    public ModelAndView findStudentOpenIndex(HttpServletRequest request,String studentId)throws Exception{
        List<StudentDTO> studentDTOList=new ArrayList<StudentDTO>();

        studentDTOList=studentDTOService.findStudentAndBroadbandByNotInOrder(studentId);
        //获得studentDTO的分页数据
        PageInfo<StudentDTO> pagehelper = new PageInfo<StudentDTO>(studentDTOList);

        //得到收费策略
        List<ChargeStrategy> strategyList = chargeStrategyService.queryChargeStrategy();

        //测试
        for (StudentDTO sList : studentDTOList) {
            System.out.println(sList.toString());
        }
        for (ChargeStrategy c : strategyList) {
            System.out.println(c);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pagehelper",pagehelper);
        modelAndView.addObject("studentDTOList", studentDTOList);
        modelAndView.addObject("strategyList", strategyList);
        modelAndView.setViewName("useropen/newOpen");

        return modelAndView;

    }

    /**
     * 新生开通
     * 生成订单
     *
     * @return
     * @throws Exception /worker/createBillBynewopen.action
     */
    @RequestMapping("/createBillBynewopen")
    public ModelAndView createBillBynewopen(HttpSession session,String studentId,String openMonth,String openSpeed)throws Exception{
        //studentId="1530016015";
        //openMonth="5";
        //openSpeed="20";
        List<StudentOrder> studentOrderList = new ArrayList<StudentOrder>();
        StudentOrder studentOrder = new StudentOrder();
        //根据学号从学生表获取姓名、房号、用户名、初始密码等数据
        Student student = studentService.findStudentById(studentId);
        studentOrder.setStudentId(student.getStudentId());
        studentOrder.setName(student.getName());
        studentOrder.setDorm(student.getDorm());
        //获取用户名和初始密码
        studentOrder.setUserId(student.getStudentId());
        String idCard = student.getIdCard();
        String password = idCard.substring(idCard.length() - 8, idCard.length());
        studentOrder.setUserPassword(password);

        //得到工号
        String workerId = (String) session.getAttribute("userId");
        studentOrder.setWorkerId(workerId);

        //得到订单号
        String dateStr = DateUtil.getDate();
        String year = DateUtil.getYear();
        String month = DateUtil.getMonth();
        String day = DateUtil.getDay();
        String orderId = studentOrderService.createOrderId(dateStr, year, month, day, workerId);
        studentOrder.setOrderId(orderId);

        //获取开通速度和包月时间
        studentOrder.setLastTime(Integer.valueOf(openMonth));
        studentOrder.setSpeed(Integer.valueOf(openSpeed));

        //获取开通时间（打印时间）、和到期时间
        String printTimeStr = DateUtil.getDate();
        String endTimeStr = DateUtil.addMonth(printTimeStr, Integer.valueOf(openMonth));
        Date printTime = DateUtil.convertStringToDate(printTimeStr);
        Date endTime = DateUtil.convertStringToDate(endTimeStr);
        studentOrder.setPrintTime(printTime);
        studentOrder.setEndTime(endTime);


        //获取金额
        ChargeStrategy chargeStrategy = chargeStrategyService.findChargeStrategyBySpeed(Integer.valueOf(openSpeed));
        Double price = chargeStrategy.getPrice();//获取价格
        Double money = price * Integer.valueOf(openMonth);//得到总金额
        studentOrder.setMoney(money);

        studentOrderList.add(studentOrder);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("studentOrder", studentOrder);
        modelAndView.addObject("printTimeStr", printTimeStr);
        modelAndView.addObject("endTimeStr", endTimeStr);
        modelAndView.setViewName("useropen/newOpenPrintBill");
        return modelAndView;


    }

    /**
     * 用户开通
     * 点击打印，插入订单数据,返回用户开通
     * @param studentOrder
     * @return
     * @throws Exception
     * /worker/insertStudentOrderByUserOpen.action
     */
    @RequestMapping("/insertStudentOrderByUserOpen")
    public String insertStudentOrderByUserOpen(StudentOrder studentOrder,String endDate,String lastDate,String printDate,String openSpeed)throws Exception{
        studentOrder.setEndTime(DateUtil.convertStringToDate("yyyy-MM-dd",endDate));
        studentOrder.setPrintTime(DateUtil.convertStringToDate("yyyy-MM-dd",printDate));
        studentOrder.setLastTime(Integer.valueOf(lastDate));
        studentOrder.setSpeed(Integer.valueOf(openSpeed));
        //插入订单信息表中
        studentOrderService.insertStudentOrder(studentOrder);
        //插入订单作废状态表中
        AccoutCancel accoutCancel=new AccoutCancel();
        accoutCancel.setStudentId(studentOrder.getStudentId());
        accoutCancel.setOrderId(studentOrder.getOrderId());
        accoutCancel.setResultFlag(0);
        accoutCancelService.insertAccoutCancel(accoutCancel);
        //更新宽带信息表
        StudentBroadband studentBroadband=new StudentBroadband();
        List<StudentDTO> studentDTOList=studentDTOService.findStudentAllInfoById(studentOrder.getStudentId());
        StudentDTO studentDTO=studentDTOList.get(0);
        studentBroadband.setAccoutNumber(studentDTO.getStudentBroadband().getAccoutNumber());
        studentBroadband.setAccessNumber(studentDTO.getStudentBroadband().getAccessNumber());
        studentBroadband.setStudentId(studentOrder.getStudentId());
        studentBroadband.setSpeed(Integer.valueOf(openSpeed));
        studentBroadband.setLastTime(Integer.valueOf(lastDate));
        studentBroadband.setStartTime(DateUtil.convertStringToDate("yyyy-MM-dd",printDate));
        studentBroadband.setEndTime(DateUtil.convertStringToDate("yyyy-MM-dd",endDate));
        studentBroadband.setMoney(studentOrder.getMoney());
        studentBroadbandService.insertOrUpdateStudentBroadband(studentBroadband);
        return "redirect:/worker/openIndex.action";

    }

    /**
     * 用户续费首页
     * 遍历学生表与宽带表的信息（两表）
     *
     * @return
     * @throws Exception
     * /worker/reNewIndex.action
     */
    @RequestMapping("/reNewIndex")
    public ModelAndView reNewIndex(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //分页
        String pageNum = request.getParameter("pageNum");
        String pageSize =request.getParameter("pageSize");
        //默认起始页是第一页，每页10条数据
        int num = 1;
        int size = 10;
        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        PageHelper.startPage(num, size);
        //得到学生与宽带联合信息,获得studentDTO
        List<StudentDTO> studentDTOList = new ArrayList<StudentDTO>();
        studentDTOList=studentDTOService.queryStudentAndBroadbandByOrder();
        //获得studentDTO的分页数据
        PageInfo<StudentDTO> pagehelper = new PageInfo<StudentDTO>(studentDTOList);

        //获得studentDTOStrList
        List<StudentDTOStr> studentDTOStrList = new ArrayList<StudentDTOStr>();
        StudentDTOStr studentDTOStr;
        List<StudentDTO> studentDTOPage=pagehelper.getList();

        for (StudentDTO studentDTO : studentDTOPage) {
            System.out.println(studentDTO.toString());
            studentDTOStr = new StudentDTOStr();
            studentDTOStr = StudentUtil.studentDTOToStr(studentDTO);
            studentDTOStrList.add(studentDTOStr);
        }

        //得到收费策略
        List<ChargeStrategy> strategyList=chargeStrategyService.queryChargeStrategy();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("strategyList",strategyList);
        modelAndView.addObject("studentDTOStrList",studentDTOStrList);
        modelAndView.addObject("pagehelper",pagehelper);
        modelAndView.setViewName("useropen/StudentReNew");

        return modelAndView;

    }

    /**
     * 用户续费
     * 根据学生学号遍历学生表和宽带表的信息（两表）
     *
     * @param studentId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findStudentReNewIndex")
    public ModelAndView findStudentAndBroadbandById(String studentId) throws Exception {
        List<StudentDTO> studentDTOList = new ArrayList<StudentDTO>();
        List<StudentDTOStr> studentDTOStrList = new ArrayList<StudentDTOStr>();
        StudentDTOStr studentDTOStr;

        studentDTOList = studentDTOService.findStudentAndBroadbandByInOrder(studentId);
        //测试
        for (StudentDTO studentDTO : studentDTOList) {
            System.out.println(studentDTO.toString());
            studentDTOStr = new StudentDTOStr();
            studentDTOStr = StudentUtil.studentDTOToStr(studentDTO);
            studentDTOStrList.add(studentDTOStr);
        }
        ModelAndView modelAndView = new ModelAndView();
        // modelAndView.addObject("studentDTOList",studentDTOList);
        modelAndView.addObject("studentDTOStrList", studentDTOStrList);
        modelAndView.setViewName("useropen/StudentReNew");

        return modelAndView;

    }

    /**
     * 用户续费
     * 生成订单
     * @return
     * @throws Exception /worker/createBillByRenew.action
     */
    @RequestMapping("/createBillByRenew")
    public ModelAndView createBillByRenew(HttpSession session,String studentId,String openMonth,String openSpeed)throws Exception{

        List<StudentOrder> studentOrderList=new ArrayList<StudentOrder>();
        StudentOrder studentOrder=new StudentOrder();
        //根据学号从学生表获取姓名、房号、用户名、初始密码等数据
        Student student=studentService.findStudentById(studentId);
        studentOrder.setStudentId(student.getStudentId());
        studentOrder.setName(student.getName());
        studentOrder.setDorm(student.getDorm());
        //获取用户名和初始密码
        studentOrder.setUserId(student.getStudentId());
        String idCard=student.getIdCard();
        String password=idCard.substring(idCard.length()-8,idCard.length());
        studentOrder.setUserPassword(password);

        //得到工号
        String workerId=(String)session.getAttribute("userId");
        studentOrder.setWorkerId(workerId);

        //得到订单号
        String dateStr=DateUtil.getDate();
        String year=DateUtil.getYear();
        String month=DateUtil.getMonth();
        String day=DateUtil.getDay();
        String orderId=studentOrderService.createOrderId(dateStr,year,month,day,workerId);
        studentOrder.setOrderId(orderId);

        //获取开通速度和包月时间
        studentOrder.setLastTime(Integer.valueOf(openMonth));
        studentOrder.setSpeed(Integer.valueOf(openSpeed));

        //获取开通时间（打印时间）、和到期时间
        //从最新的订单中获取到期时间，然后叠加上去
        String printTimeStr=DateUtil.getDate();
        //获得该学号目前最近一条订单记录
        StudentOrder studentOrderTop=studentOrderService.findStudentOrderTopById(studentId);
        String endTimeStr=DateUtil.addMonth(DateUtil.convertDateToString(studentOrderTop.getEndTime()),Integer.valueOf(openMonth));
        Date printTime=DateUtil.convertStringToDate(printTimeStr);
        Date endTime=DateUtil.convertStringToDate(endTimeStr);
        studentOrder.setPrintTime(printTime);
        studentOrder.setEndTime(endTime);


        //获取金额
        ChargeStrategy chargeStrategy=chargeStrategyService.findChargeStrategyBySpeed(Integer.valueOf(openSpeed));
        Double price=chargeStrategy.getPrice();//获取价格
        Double money=price*Integer.valueOf(openMonth);//得到总金额
        studentOrder.setMoney(money);

        studentOrderList.add(studentOrder);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("studentOrder",studentOrder);
        modelAndView.addObject("printTimeStr",printTimeStr);
        modelAndView.addObject("endTimeStr",endTimeStr);
        modelAndView.setViewName("useropen/reNewPrintBill");
        return modelAndView;
    }

    /**
     * 用户续费
     * 用户续费打印账单，插入订单数据，跳转回续费页面
     * @param studentOrder
     * @param endDate
     * @param lastDate
     * @param printDate
     * @param openSpeed
     * @return
     * @throws Exception
     */
    @RequestMapping("/insertStudentOrderByReNew")
    public String insertStudentOrderByReNew(StudentOrder studentOrder,String endDate,String lastDate,String printDate,String openSpeed)throws Exception{
        studentOrder.setEndTime(DateUtil.convertStringToDate("yyyy-MM-dd",endDate));
        studentOrder.setPrintTime(DateUtil.convertStringToDate("yyyy-MM-dd",printDate));
        studentOrder.setLastTime(Integer.valueOf(lastDate));
        studentOrder.setSpeed(Integer.valueOf(openSpeed));
        //插入订单信息表中
        studentOrderService.insertStudentOrder(studentOrder);
        //插入订单作废状态表中
        AccoutCancel accoutCancel=new AccoutCancel();
        accoutCancel.setStudentId(studentOrder.getStudentId());
        accoutCancel.setOrderId(studentOrder.getOrderId());
        accoutCancel.setResultFlag(0);
        accoutCancelService.insertAccoutCancel(accoutCancel);
        //更新宽带信息表
        StudentBroadband studentBroadband=new StudentBroadband();
        List<StudentDTO> studentDTOList=studentDTOService.findStudentAllInfoById(studentOrder.getStudentId());
        StudentDTO studentDTO=studentDTOList.get(0);
        studentBroadband.setAccoutNumber(studentDTO.getStudentBroadband().getAccoutNumber());
        studentBroadband.setAccessNumber(studentDTO.getStudentBroadband().getAccessNumber());
        studentBroadband.setStudentId(studentOrder.getStudentId());
        studentBroadband.setSpeed(Integer.valueOf(openSpeed));
        studentBroadband.setLastTime(Integer.valueOf(lastDate));
        studentBroadband.setStartTime(DateUtil.convertStringToDate("yyyy-MM-dd",printDate));
        studentBroadband.setEndTime(DateUtil.convertStringToDate("yyyy-MM-dd",endDate));
        studentBroadband.setMoney(studentOrder.getMoney());
        studentBroadbandService.insertOrUpdateStudentBroadband(studentBroadband);
        return "redirect:/worker/reNewIndex.action";


    }

    /**
     * 插入学生与宽带的联合信息
     *
     * @param studentDTO
     * @return
     * @throws Exception /worker/insertStudentAllInfo.action
     */
    @RequestMapping("/insertStudentAllInfo")
    public String insertStudentAllInfo(StudentDTO studentDTO) throws Exception {
        //测试

        String nowTime = DateUtil.getDateTime();
        Date startDate = DateUtil.convertStringToDate(nowTime);
        Date endDate = DateUtil.convertStringToDate(DateUtil.addMonth(nowTime, 2));

        StudentBroadband studentBroadbandTest = new StudentBroadband();
        studentDTO.setStudentId("1401010056");
        studentDTO.setName("插入测试");
        studentDTO.setIdCard("0056");
        studentDTO.setDorm("08");
        studentDTO.setPhone("123456777");
        studentBroadbandTest.setStudentId(studentDTO.getStudentId());
        studentBroadbandTest.setAccessNumber("");
        studentBroadbandTest.setAccoutNumber("");
        studentBroadbandTest.setSpeed(10);
        studentBroadbandTest.setMoney(100);
        studentBroadbandTest.setStartTime(startDate);
        studentBroadbandTest.setLastTime(2);
        studentBroadbandTest.setEndTime(endDate);
        studentDTO.setStudentBroadband(studentBroadbandTest);

        Student student = new Student();
        StudentBroadband studentBroadband = new StudentBroadband();
        student.setStudentId(studentDTO.getStudentId());
        student.setName(studentDTO.getName());
        student.setIdCard(studentDTO.getIdCard());
        student.setDorm(studentDTO.getPhone());
        student.setPhone(studentDTO.getDorm());
        studentBroadband = studentDTO.getStudentBroadband();
        studentBroadband.setStudentId(studentDTO.getStudentId());
        studentDTOService.insertStudentAllInfo(student, studentBroadband);

        return "redirect:/worker/queryStudentAllInfo.action";
    }


    /**********************************学生订单信息管理**********************************/


    /**
     * 遍历所有学生的订单信息
     *
     * @return
     * @throws Exception /worker/queryStudentOrder.action
     */
    @RequestMapping("/queryStudentOrder")
    public ModelAndView queryStudentOrder() throws Exception {
        List<StudentOrder> studentOrderList = new ArrayList<StudentOrder>();
        List<StudentOrderStr> studentOrderStrList = new ArrayList<StudentOrderStr>();
        StudentOrderStr studentOrderStr;
        studentOrderList = studentOrderService.queryStudentOrder();

        for (StudentOrder studentOrder : studentOrderList) {
            System.out.println(studentOrder);
            studentOrderStr = new StudentOrderStr();
            studentOrderStr = StudentUtil.studentOrderToStr(studentOrder);

            studentOrderStrList.add(studentOrderStr);
        }
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("studentOrderList",studentOrderList);
        modelAndView.addObject("studentOrderStrList", studentOrderStrList);
        modelAndView.setViewName("useropen/order");
        return modelAndView;
    }

    /**
     * 根据学号来查找该学生的所有订单记录
     *
     * @param studentId
     * @return
     * @throws Exception /worker/findStudentOrderById.action
     */
    @RequestMapping("/findStudentOrderById")
    public ModelAndView findStudentOrderById(String studentId) throws Exception {
        List<StudentOrder> studentOrderList = new ArrayList<StudentOrder>();
        List<StudentOrderStr> studentOrderStrList = new ArrayList<StudentOrderStr>();
        StudentOrderStr studentOrderStr;
        studentOrderList = studentOrderService.findStudentOrderById(studentId);
        //测试
        for (StudentOrder studentOrder : studentOrderList) {
            System.out.println(studentOrder);
            studentOrderStr = new StudentOrderStr();
            studentOrderStr = StudentUtil.studentOrderToStr(studentOrder);

            studentOrderStrList.add(studentOrderStr);
        }
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("studentOrderList",studentOrderList);
        modelAndView.addObject("studentOrderStrList", studentOrderStrList);
        modelAndView.setViewName("useropen/order");
        return modelAndView;

    }

    /**
     * 根据日期来查询当天的订单记录
     * 根据日期升序列排列
     *
     * @param date
     * @return
     * @throws Exception /worker/findStudentOrderByDate.action
     */
    @RequestMapping("/findStudentOrderByDate")
    public ModelAndView findStudentOrderByDate(Date date) throws Exception {
        //TODO 时间问题没解决完
        List<StudentOrder> studentOrderList = new ArrayList<StudentOrder>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.parse("1998-08-02");
        String dateStr = DateUtil.convertDateToString(date);

        Date topDate = dateFormat.parse(dateStr);
        Date buttomDate = dateFormat.parse(dateStr);
        topDate = DateUtil.addStartTime(topDate);//添加00:00:00
        buttomDate = DateUtil.addEndTime(buttomDate);//添加23:59:59
        studentOrderList = studentOrderService.findStudentOrderByDate(topDate, buttomDate);
        for (StudentOrder s : studentOrderList) {
            System.out.println(s);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("studentOrderList", studentOrderList);
        modelAndView.setViewName("success");
        return modelAndView;
    }



    /****************************************************营帐收费管理模块***********************************************/
    /**
     * 特殊账目录入首页
     * 跳转作用
     * @return
     * @throws Exception
     * /worker/inputBillIndex.action
     */
    @RequestMapping("inputBillIndex")
    public ModelAndView inputBillIndex()throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("bill/bill_input");
        return modelAndView;

    }

    /**
     * 特殊账单的录入
     * 生成订单
     * @param session
     * @param studentId
     * @return
     * /worker/createBillBySpecial.action
     */
    @RequestMapping("/createBillBySpecial")
    public ModelAndView createBillBySpecial(HttpSession session,String chargeType, String studentId,String name,
                                            String[] attributeName, String[] attributeValue,
                                            String[] chargeName,String[] chargeValue) throws Exception {

        //获得属性名称和值
        List<AttributeDTO> attributeDTOList=new ArrayList<AttributeDTO>();
        AttributeDTO attributeDTO;
        for (int i = 0; i <attributeName.length ; i++) {
            attributeDTO=new AttributeDTO();
            attributeDTO.setName(attributeName[i]);
            attributeDTO.setValue(attributeValue[i]);
            attributeDTOList.add(attributeDTO);
        }

        //获得收费项目名称和值
        //获得收费总金额
        List<ChargeDTO> chargeDTOList=new ArrayList<ChargeDTO>();
        ChargeDTO chargeDTO;
        Double money=0.0;
        for (int i = 0; i <chargeName.length ; i++) {
            chargeDTO=new ChargeDTO();
            chargeDTO.setName(chargeName[i]);
            chargeDTO.setValue(chargeValue[i]);
            money+=Double.valueOf(chargeValue[i]);
            chargeDTOList.add(chargeDTO);
        }
        System.out.println("金额:"+money);

        //得到工号
        String workerId = (String) session.getAttribute("userId");

        //得到订单号
        String dateStr = DateUtil.getDate();
        String year = DateUtil.getYear();
        String month = DateUtil.getMonth();
        String day = DateUtil.getDay();
        String orderId = studentOrderService.createOrderId(dateStr, year, month, day, workerId);

        //获取开通时间（打印时间）
        String printTimeStr = DateUtil.getDate();
        Date printTime = DateUtil.convertStringToDate(printTimeStr);


        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("name", name);
        modelAndView.addObject("studentId",studentId);
        modelAndView.addObject("orderId", orderId);
        modelAndView.addObject("money", money);
        modelAndView.addObject("workerId",workerId);
        modelAndView.addObject("printTimeStr", printTimeStr);

        modelAndView.addObject("chargeType", chargeType);

        modelAndView.addObject("attributeDTOList", attributeDTOList);
        modelAndView.addObject("chargeDTOList", chargeDTOList);


        modelAndView.setViewName("bill/bill_print_preview");
        return modelAndView;
    }

    /**
     * 特殊账户录入
     * 点击打印，插入订单数据,返回特殊账户录入
     * @return
     * @throws Exception
     * /worker/insertStudentOrderBySpecial.action
     */
    @RequestMapping("/insertStudentOrderBySpecial")
    public String insertStudentOrderBySpecial(String studentId,String name,String workerId,String orderId,String printTimeStr,String money)throws Exception{
        StudentOrder studentOrder=new StudentOrder();
        studentOrder.setPrintTime(DateUtil.convertStringToDate("yyyy-MM-dd",printTimeStr));
        studentOrder.setStudentId(studentId);
        studentOrder.setName(name);
        studentOrder.setWorkerId(workerId);
        studentOrder.setOrderId(orderId);
        studentOrder.setMoney(Double.valueOf(money));
        //插入订单信息表中
        studentOrderService.insertStudentOrder(studentOrder);
        //插入订单作废状态表中
        AccoutCancel accoutCancel=new AccoutCancel();
        accoutCancel.setStudentId(studentOrder.getStudentId());
        accoutCancel.setOrderId(studentOrder.getOrderId());
        accoutCancel.setResultFlag(0);
        accoutCancelService.insertAccoutCancel(accoutCancel);
        return "redirect:/worker/inputBillIndex.action";

    }

    /**
     * 营帐查询首页
     * 遍历该登录工号今天所操作的所有没有被作废的订单信息
     * @return
     * @throws Exception
     * /worker/searchBillIndex.action
     */
    @RequestMapping("/searchBillIndex")
    public ModelAndView searchBillIndex(HttpSession session,HttpServletRequest request,HttpServletResponse response)throws Exception{
        //分页
        String pageNum = request.getParameter("pageNum");
        String pageSize =request.getParameter("pageSize");
        //默认起始页是第一页，每页10条数据
        int num = 1;
        int size = 10;
        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        PageHelper.startPage(num, size);
        //获得当前登录的工号
        String workerId=(String) session.getAttribute("userId");
        String nowDate=DateUtil.getDate();
        //获取StudentOrder的List
        List<StudentOrder> studentOrderList=studentOrderService.findStudentOrderByDateAndWorkerId(workerId,nowDate,nowDate);
        //获得studentOrder的分页数据
        PageInfo<StudentOrder> pagehelper = new PageInfo<StudentOrder>(studentOrderList);

        //获得StudentOrder数据
        List<StudentOrderStr> studentOrderStrList=new ArrayList<StudentOrderStr>();
        StudentOrderStr studentOrderStr;
        List<StudentOrder> studentOrderPage=pagehelper.getList();

        //得到订单表po类相对应的String输出po类
        for(StudentOrder studentOrder:studentOrderPage){
            System.out.println(studentOrder);
            studentOrderStr=new StudentOrderStr();
            studentOrderStr=StudentUtil.studentOrderToStr(studentOrder);
            studentOrderStrList.add(studentOrderStr);
        }

        //获得总金额Money
        Double money=0.0;
        for (int i = 1; i <=pagehelper.getPages(); i++) {
            PageHelper.startPage(i, 10);
            List<StudentOrder> moneyList=studentOrderService.queryStudentOrder();
            for (StudentOrder studentOrder:moneyList ){
                money+=studentOrder.getMoney();
            }
        }

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("money",money);
        modelAndView.addObject("studentOrderStrList",studentOrderStrList);
        modelAndView.addObject("pagehelper",pagehelper);
        modelAndView.setViewName("bill/bill_search");

        return  modelAndView;

    }

    /**
     * 根据前端下拉框的flag来跳转action
     * 学号or订单号
     * @param flag
     * @param id
     * @return
     * @throws Exception
     * /worker/choosefindBillByFlag.action
     */
    @RequestMapping("/choosefindBillByFlag")
    public String choosefindBillByFlag(String flag,String id)throws Exception{
        if (flag.equals("0"))
        {return "redirect:/worker/findBillByOrderId.action?orderId="+id;}
        if (flag.equals("1"))
        {return "redirect:/worker/findBillById.action?studentId="+id;}
        return "error";
    }

    /**
     * 营帐查询
     * 根据学号来搜索没有被作废的订单信息
     * @param studentId
     * @return
     * @throws Exception
     * /worker/findBillById.action
     */
    @RequestMapping("/findBillById")
    public ModelAndView findBillById(String studentId)throws Exception{
        List<StudentOrder> studentOrderList=studentOrderService.findStudentOrderById(studentId);
        List<StudentOrderStr> studentOrderStrList=new ArrayList<StudentOrderStr>();
        StudentOrderStr studentOrderStr;

        //得到订单表po类相对应的String输出po类
        for(StudentOrder studentOrder:studentOrderList){
            studentOrderStr=new StudentOrderStr();
            studentOrderStr=StudentUtil.studentOrderToStr(studentOrder);
            studentOrderStrList.add(studentOrderStr);
            System.out.println(studentOrder);
        }

        //获得总金额Money
        Double money=0.0;
        for (StudentOrder studentOrder:studentOrderList){
            money+=studentOrder.getMoney();
        }

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("money",money);
        modelAndView.addObject("studentOrderStrList",studentOrderStrList);
        modelAndView.setViewName("bill/bill_search");

        return modelAndView;
    }

    /**
     * 营帐查询
     * 根据订单号来查询没有被作废的订单信息
     * @param orderId
     * @return
     * @throws Exception
     * /worker/findBillByOrderId.action
     */
    @RequestMapping("/findBillByOrderId")
    public ModelAndView findBillByOrderId(String orderId)throws Exception{

        List<StudentOrder> studentOrderList=studentOrderService.findStudentOrderByOrderId(orderId);
        List<StudentOrderStr> studentOrderStrList=new ArrayList<StudentOrderStr>();
        StudentOrderStr studentOrderStr;
        //得到订单表po类相对应的String输出po类
        for(StudentOrder studentOrder:studentOrderList){
            studentOrderStr=new StudentOrderStr();
            studentOrderStr=StudentUtil.studentOrderToStr(studentOrder);
            studentOrderStrList.add(studentOrderStr);
            System.out.println(studentOrder);
        }

        //获得总金额Money
        Double money=0.0;
        for (StudentOrder studentOrder:studentOrderList){
            money+=studentOrder.getMoney();
        }

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("money",money);
        modelAndView.addObject("studentOrderStrList",studentOrderStrList);
        modelAndView.setViewName("bill/bill_search");

        return  modelAndView;

    }

    /**
     * 营帐查询
     * 根据时间段、工号来查询没有被作废的订单信息
     * @param workerId
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     * /worker/findBillByDate.action
     */
    @RequestMapping("/findBillByDate")
    public ModelAndView findBillByDate(String workerId,String startDate,String endDate)throws Exception{

        List<StudentOrder> studentOrderList=studentOrderService.findStudentOrderByDateAndWorkerId(workerId,startDate,endDate);
        List<StudentOrderStr> studentOrderStrList=new ArrayList<StudentOrderStr>();
        StudentOrderStr studentOrderStr;
        //得到订单表po类相对应的String输出po类
        for(StudentOrder studentOrder:studentOrderList){
            studentOrderStr=new StudentOrderStr();
            studentOrderStr=StudentUtil.studentOrderToStr(studentOrder);
            studentOrderStrList.add(studentOrderStr);
            System.out.println(studentOrder);
        }

        //获得总金额Money
        Double money=0.0;
        for (StudentOrder studentOrder:studentOrderList){
            money+=studentOrder.getMoney();
        }

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("money",money);
        modelAndView.addObject("studentOrderStrList",studentOrderStrList);
        modelAndView.setViewName("bill/bill_search");

        return  modelAndView;
    }

    /**
     * 营帐查询
     * 根据订单号将订单作废，填写理由
     * 更新作废订单表数据
     * @param orderId
     * @param cancelReason
     * @return
     * @throws Exception
     * /worker/updateBillCancel.action
     */
    @RequestMapping("/updateBillCancel")
    public String updateBillCancel(String orderId,String cancelReason)throws Exception{
        accoutCancelService.updateAccoutCancelByOrderId(orderId,cancelReason);
        AccoutCancel accoutCancel=accoutCancelService.findAccoutCancelByOrderId(orderId);
        //查询该学生是否还存在订单，如果不存在，则删除该学生在订单表的数据，使得新生查询可以找的到该学生
        int flag=accoutCancelService.findAccoutNotCancelCountById(accoutCancel.getStudentId()) ;
        if (flag==0){
            //删除该学生在订单表的数据
            studentOrderService.deleteStudentOrderById(accoutCancel.getStudentId());
            //更新该学生在宽带信息表的数据为空，除了接入号和账号
            StudentBroadband studentBroadband=new StudentBroadband();
            studentBroadband.setSpeed(0);
            studentBroadband.setMoney(0.0);
            studentBroadband.setLastTime(0);
            studentBroadband.setStartTime(null);
            studentBroadband.setEndTime(null);
            studentBroadbandService.updateStudentBroadbandSomeById(accoutCancel.getStudentId(),studentBroadband);
        }

        return "redirect:/worker/searchBillIndex.action";

    }

    /**
     * 根据订单号来查询订单信息，然后生成订单打印页面
     * @param session
     * @param orderId
     * @return
     * @throws Exception
     * /worker/creatBillAgain.action
     */
    @RequestMapping("/creatBillAgain")
    public ModelAndView creatBillAgain(HttpSession session,String orderId)throws Exception{

        List<StudentOrder> studentOrderList=studentOrderService.findStudentOrderByOrderId(orderId);
        StudentOrder studentOrder=studentOrderList.get(0);
        String printTimeStr=DateUtil.convertDateToString(studentOrder.getPrintTime());
        String endTimeStr=DateUtil.convertDateToString(studentOrder.getEndTime());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("studentOrder", studentOrder);
        modelAndView.addObject("printTimeStr", printTimeStr);
        modelAndView.addObject("endTimeStr", endTimeStr);
        modelAndView.setViewName("bill/bill_print_again");
        return modelAndView;
    }


    /**
     * 订单作废
     * 订单作废首页，遍历所有作废的订单
     * @return
     * @throws Exception
     * /worker/orderCancelIndex.action
     */
    @RequestMapping("/orderCancelIndex")
    public ModelAndView orderCancelIndex()throws Exception{
        List<StudentOrder> studentOrderList=studentOrderService.queryStudentOrderCancel();
        List<StudentOrderStr> studentOrderStrList=new ArrayList<StudentOrderStr>();
        StudentOrderStr studentOrderStr;
        //得到订单表po类相对应的String输出po类
        for(StudentOrder studentOrder:studentOrderList){
            System.out.println(studentOrder);
            studentOrderStr=new StudentOrderStr();
            studentOrderStr=StudentUtil.studentOrderToStr(studentOrder);
            studentOrderStrList.add(studentOrderStr);
        }

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("studentOrderStrList",studentOrderStrList);
        modelAndView.setViewName("bill/bill_abolish");

        return  modelAndView;
    }

    /**
     * 订单作废
     * 根据前端返回的chooseFlag来判断
     * @param chooseFlag
     * @return
     * @throws Exception
     * /worker/choosefindByIdOrOrderId.action
     */
    @RequestMapping("/choosefindByIdOrOrderId")
    public String choosefindByIdOrOrderId(String chooseFlag,String id)throws Exception{

        //如果是0，则跳转根据学号查询作废订单信息
        if(chooseFlag.equals("0")){
            return "redirect:/worker/findOrderCancelById.action?studentId="+id;
        }
        //如果是1，则跳转根据学号查询作废订单信息
        if (chooseFlag.equals("1")){
            return "redirect:/worker/findOrderCancelByOrderId.action?orderId="+id;
        }
        return "error";

    }

    /**
     *订单作废
     * 根据学生学号来查询作废订单的订单信息
     * @param studentId
     * @return
     * @throws Exception
     * /worker/findOrderCancelById.action
     */
    @RequestMapping("/findOrderCancelById")
    public ModelAndView findOrderCancelById(String studentId)throws Exception{

        List<StudentOrderStr> studentOrderStrList=new ArrayList<StudentOrderStr>();
        StudentOrder studentOrder=studentOrderService.findStudentOrderCancelById(studentId);
        StudentOrderStr studentOrderStr=new StudentOrderStr();
        //防止工具类空指针异常
        if (studentOrder!=null){
            studentOrderStr=StudentUtil.studentOrderToStr(studentOrder);
            studentOrderStrList.add(studentOrderStr);
        }

        System.out.println(studentOrder);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("studentOrderStrList",studentOrderStrList);
        modelAndView.setViewName("bill/bill_abolish");
        return  modelAndView;

    }

    /**
     * 订单作废
     * 根据订单号在查询作废的订单信息
     * @param orderId
     * @return
     * @throws Exception
     * /worker/findOrderCancelByOrderId.action
     */
    @RequestMapping("/findOrderCancelByOrderId")
    public ModelAndView findOrderCancelByOrderId(String orderId)throws Exception{
        List<StudentOrderStr> studentOrderStrList=new ArrayList<StudentOrderStr>();
        StudentOrder studentOrder=studentOrderService.findStudentOrderCancelByOrderId(orderId);
        StudentOrderStr studentOrderStr=new StudentOrderStr();
        //防止工具类空指针异常
        if(studentOrder!=null){
            studentOrderStr=StudentUtil.studentOrderToStr(studentOrder);
            studentOrderStrList.add(studentOrderStr);
        }

        System.out.println(studentOrder);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("studentOrderStrList",studentOrderStrList);
        modelAndView.setViewName("bill/bill_abolish");
        return  modelAndView;

    }

    /**
     * 订单作废
     * 根据订单号查询作废的订单的信息和作废原因
     * @param orderId
     * @return
     * @throws Exception
     * /worker/findOrderCancelReasonByOrderId.action
     */
    @RequestMapping("/findOrderCancelReasonByOrderId")
    public ModelAndView findOrderCancelReasonByOrderId(String orderId)throws Exception{
        //得到作废的订单表信息
        StudentOrder studentOrder=studentOrderService.findStudentOrderCancelByOrderId(orderId);
        StudentOrderStr studentOrderStr=new StudentOrderStr();
        studentOrderStr=StudentUtil.studentOrderToStr(studentOrder);

        System.out.println(studentOrder);
        //得到作废的原因
        String cancelReason=accoutCancelService.findAccoutCancelReasonByOrderId(orderId);
        //传值到前端
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("studentOrderStr",studentOrderStr);
        modelAndView.addObject("cancelReason",cancelReason);
        modelAndView.setViewName("bill/bill_abolish_reason");
        return  modelAndView;


    }


    /****************************************************收费策略管理模块***********************************************/

    /**
     * 遍历所有收费策略信息
     *
     * @return
     * @throws Exception /worker/queryChargeStrategy.action
     */
    @RequestMapping("/queryChargeStrategy")
    public ModelAndView queryChargeStrategy() throws Exception {
        List<ChargeStrategy> strategyList = chargeStrategyService.queryChargeStrategy();
        for (ChargeStrategy cs : strategyList) {
            System.out.println(cs.toString());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("strategyList", strategyList);
        modelAndView.setViewName("chargeStrategy/chargeStrategy");
        return modelAndView;
    }

    /**
     * 修改收费策略
     *
     * @param chargeStrategy 修改内容及条件
     * @return
     * @throws Exception /worker/updateChargeStrategy.action
     */
    @RequestMapping("/updateChargeStrategy")
    public String updateChargeStrategy(ChargeStrategy chargeStrategy) throws Exception {

        chargeStrategyService.updateChargeStrategy(chargeStrategy);

        return "redirect:/worker/queryChargeStrategy.action";
    }

    /**
     * 逻辑删除收费策略
     *
     * @param id 按id为条件进行删除
     * @return
     * @throws Exception /worker/deleteChargeStrategyById.action
     */
    @RequestMapping("/deleteChargeStrategyById")
    public String deleteChargeStrategyById(String id) throws Exception {
        chargeStrategyService.deleteChargeStrategyById(id);
        return "redirect:/worker/queryChargeStrategy.action";
    }

    /**
     * 新增收费策略信息
     *
     * @param chargeStrategy 新增内容，无id，id在数据库自增
     * @return
     * @throws Exception /worker/insertChargeStrategy.action
     */
    @RequestMapping("/insertChargeStrategy")
    public String insertChargeStrategy(ChargeStrategy chargeStrategy) throws Exception {
        chargeStrategyService.insertChargeStrategy(chargeStrategy);
        return "redirect:/worker/queryChargeStrategy.action";
    }

    /***************************************学生报修管理模块***********************************************************/
    /***************************************学生报修信息录入***********************************************************/

    /**
     * 根据学生Id查询用户的基本报修信息
     *
     * @param studentId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findrepairStudent")
    public ModelAndView findrepairStudent(String studentId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        Student student = studentService.findrepairStudent(studentId);
        modelAndView.addObject("student", student);
        modelAndView.setViewName("repair/repairInput");
        return modelAndView;
    }

    /**
     * 跳转报修信息插入界面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/toinsertRepairRecord")
    public ModelAndView toinsertRepairRecord() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("repair/repairInput");
        return modelAndView;
    }

    /**
     * 插入学生的报修信息
     *
     * @param repairRecord
     * @return
     * @throws Exception
     */
    @RequestMapping("/insertRepairRecord")
    public ModelAndView insertRepairRecord(HttpSession session, RepairRecord repairRecord) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        String nowTime = DateUtil.getDateTime();
        Date startDate = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", nowTime);
        repairRecord.setAddTime(startDate);
        studentRepairService.insertRepairRecord(repairRecord);
        modelAndView.setViewName("repair/repairInput");
        return modelAndView;
    }

    /***************************************报修信息查询***********************************************************/
    /**
     * 遍历所有学生报修信息
     *
     * @return
     * @throws Exception
     * /worker/queryRepairRecord.action
     */
    @RequestMapping("/queryRepairRecord")
    public ModelAndView queryRepairRecord(HttpServletResponse response,HttpServletRequest request) throws Exception {

        //分页
        String pageNum = request.getParameter("pageNum");
        String pageSize =request.getParameter("pageSize");
        //默认起始页是第一页，每页8条数据
        int num = 1;
        int size =8;
        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        PageHelper.startPage(num, size);

        //获得RepairRecordCustom分页数据
        List<RepairRecordCustom> repairRecordCustomsList = new ArrayList<RepairRecordCustom>();
        repairRecordCustomsList = studentRepairService.queryRepairRecord();
        PageInfo<RepairRecordCustom> pagehelper = new PageInfo<RepairRecordCustom>(repairRecordCustomsList);
        //获得RepairRecordCustomStr分页数据
        List<RepairRecordCustomStr> repairRecordCustomStrList=new ArrayList<RepairRecordCustomStr>();
        RepairRecordCustomStr repairRecordCustomStr;
        List<RepairRecordCustom> RepairRecordCustomPage=pagehelper.getList();
        for (RepairRecordCustom repairRecordCustom : RepairRecordCustomPage) {
            repairRecordCustomStr=new RepairRecordCustomStr();
            repairRecordCustomStr=StudentUtil.repairRecordCustomToStr(repairRecordCustom);
            repairRecordCustomStrList.add(repairRecordCustomStr);
            System.out.println(repairRecordCustom);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("repairRecordCustomStrList", repairRecordCustomStrList);
        modelAndView.addObject("pagehelper",pagehelper);
        modelAndView.setViewName("repair/repairSearchAll");
        return modelAndView;
    }

    /**
     * 遍历所有未处理的学生维修信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryNoDealRepairRecord")
    public ModelAndView queryNoDealRepairRecord(HttpServletResponse response,HttpServletRequest request) throws Exception {

        //分页
        String pageNum = request.getParameter("pageNum");
        String pageSize =request.getParameter("pageSize");
        //默认起始页是第一页，每页8条数据
        int num = 1;
        int size =8;
        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        PageHelper.startPage(num, size);

        //获得RepairRecordCustom分页数据
        List<RepairRecordCustom> repairRecordCustomsList = new ArrayList<RepairRecordCustom>();
        repairRecordCustomsList = studentRepairService.queryNoDealRepairRecord();
        PageInfo<RepairRecordCustom> pagehelper = new PageInfo<RepairRecordCustom>(repairRecordCustomsList);
        //获得RepairRecordCustomStr分页数据
        List<RepairRecordCustomStr> repairRecordCustomStrList=new ArrayList<RepairRecordCustomStr>();
        RepairRecordCustomStr repairRecordCustomStr;
        List<RepairRecordCustom> RepairRecordCustomPage=pagehelper.getList();
        for (RepairRecordCustom repairRecordCustom : RepairRecordCustomPage) {
            repairRecordCustomStr=new RepairRecordCustomStr();
            repairRecordCustomStr=StudentUtil.repairRecordCustomToStr(repairRecordCustom);
            repairRecordCustomStrList.add(repairRecordCustomStr);
            System.out.println(repairRecordCustom);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("repairRecordCustomStrList", repairRecordCustomStrList);
        modelAndView.addObject("pagehelper",pagehelper);
        modelAndView.setViewName("repair/repairSearchNot");
        return modelAndView;
    }

    /**
     * 遍历所有已处理的学生维修信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryDealRepairRecord")
    public ModelAndView queryDealRepairRecord(HttpServletResponse response,HttpServletRequest request) throws Exception {
        //分页
        String pageNum = request.getParameter("pageNum");
        String pageSize =request.getParameter("pageSize");
        //默认起始页是第一页，每页8条数据
        int num = 1;
        int size =8;
        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        PageHelper.startPage(num, size);


        //获得RepairRecordCustom分页数据
        List<RepairRecordCustom> repairRecordCustomsList = new ArrayList<RepairRecordCustom>();
        repairRecordCustomsList = studentRepairService.queryDealRepairRecord();
        PageInfo<RepairRecordCustom> pagehelper = new PageInfo<RepairRecordCustom>(repairRecordCustomsList);
        //获得RepairRecordCustomStr分页数据
        List<RepairRecordCustomStr> repairRecordCustomStrList=new ArrayList<RepairRecordCustomStr>();
        RepairRecordCustomStr repairRecordCustomStr;
        List<RepairRecordCustom> RepairRecordCustomPage=pagehelper.getList();
        for (RepairRecordCustom repairRecordCustom : RepairRecordCustomPage) {
            repairRecordCustomStr=new RepairRecordCustomStr();
            repairRecordCustomStr=StudentUtil.repairRecordCustomToStr(repairRecordCustom);
            repairRecordCustomStrList.add(repairRecordCustomStr);
            System.out.println(repairRecordCustom);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("repairRecordCustomStrList", repairRecordCustomStrList);
        modelAndView.addObject("pagehelper",pagehelper);
        modelAndView.setViewName("repair/repairSearchYet");
        return modelAndView;
    }

    /**
     * 根据学生Id删除学生报修信息
     *
     * @param studentId
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteRepairRecordByStudentId")
    public ModelAndView deleteRepairRecordByStudentId(String studentId) throws Exception {
        //测试
        ModelAndView modelAndView = new ModelAndView();
        studentRepairService.deleteRepairRecordByStudentId(studentId);
        modelAndView.setViewName("repair/repairSearch");
        return modelAndView;
    }

    /**
     * 根据学生Id查询全部报修信息
     *
     * @param studentId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRepairRecordByStudentId")
    public ModelAndView findRepairRecordByStudentId(String studentId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<RepairRecordCustom> repairRecordCustomsList = new ArrayList<RepairRecordCustom>();
        repairRecordCustomsList = studentRepairService.findRepairRecordByStudentId(studentId);
        List<RepairRecordCustomStr> repairRecordCustomStrList=new ArrayList<RepairRecordCustomStr>();
        RepairRecordCustomStr repairRecordCustomStr;

        for (RepairRecordCustom repairRecordCustom : repairRecordCustomsList) {
            repairRecordCustomStr=new RepairRecordCustomStr();
            repairRecordCustomStr=StudentUtil.repairRecordCustomToStr(repairRecordCustom);
            repairRecordCustomStrList.add(repairRecordCustomStr);
            System.out.println(repairRecordCustom);
        }

        //modelAndView.addObject("repairRecordCustomsList", repairRecordCustomsList);
        modelAndView.addObject("repairRecordCustomStrList", repairRecordCustomStrList);
        modelAndView.setViewName("repair/repairSearchAll");
        return modelAndView;
    }

    /**
     * 根据studentId查询未处理的报修信息
     *
     * @param studentId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findNoDealRepairRecoreByStudentId")
    public ModelAndView findNoDealRepairRecoreByStudentId(String studentId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<RepairRecordCustom> repairRecordCustomsList = new ArrayList<RepairRecordCustom>();
        repairRecordCustomsList = studentRepairService.findNoDealRepairRecoreByStudentId(studentId);
        List<RepairRecordCustomStr> repairRecordCustomStrList=new ArrayList<RepairRecordCustomStr>();
        RepairRecordCustomStr repairRecordCustomStr;

        for (RepairRecordCustom repairRecordCustom : repairRecordCustomsList) {
            repairRecordCustomStr=new RepairRecordCustomStr();
            repairRecordCustomStr=StudentUtil.repairRecordCustomToStr(repairRecordCustom);
            repairRecordCustomStrList.add(repairRecordCustomStr);
            System.out.println(repairRecordCustom);
        }

        //modelAndView.addObject("repairRecordCustomsList", repairRecordCustomsList);
        modelAndView.addObject("repairRecordCustomStrList", repairRecordCustomStrList);
        modelAndView.setViewName("repair/repairSearchNot");
        return modelAndView;
    }

    /**
     * 根据studentId查询已处理的报修信息
     *
     * @param studentId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findDealRepairRecoreByStudentId")
    public ModelAndView findDealRepairRecoreByStudentId(String studentId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<RepairRecordCustom> repairRecordCustomsList = new ArrayList<RepairRecordCustom>();
        repairRecordCustomsList = studentRepairService.findDealRepairRecoreByStudentId(studentId);
        List<RepairRecordCustomStr> repairRecordCustomStrList=new ArrayList<RepairRecordCustomStr>();
        RepairRecordCustomStr repairRecordCustomStr;

        for (RepairRecordCustom repairRecordCustom : repairRecordCustomsList) {
            repairRecordCustomStr=new RepairRecordCustomStr();
            repairRecordCustomStr=StudentUtil.repairRecordCustomToStr(repairRecordCustom);
            repairRecordCustomStrList.add(repairRecordCustomStr);
            System.out.println(repairRecordCustom);
        }

        //modelAndView.addObject("repairRecordCustomsList1", repairRecordCustomsList);
        modelAndView.addObject("repairRecordCustomStrList", repairRecordCustomStrList);
        modelAndView.setViewName("repair/repairSearchYet");
        return modelAndView;
    }

    /**
     * 根据学生维修内容修改学生报修信息及结果录入(全部)
     *
     * @param repairContent
     * @param advice
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateRepairRecordByStudentRepairContentAll")
    public String updateRepairRecordByStudentRepairContentAll(String repairContent, String advice) throws Exception {
        studentRepairService.updateRepairRecordByStudentRepairContent(repairContent, advice);
        return "redirect:/worker/queryRepairRecord.action";
    }

    /**
     * 根据学生维修内容修改学生报修信息及结果录入(未处理)
     *
     * @param repairContent
     * @param advice
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateRepairRecordByStudentRepairContentNot")
    public String updateRepairRecordByStudentRepairContentNot(String repairContent, String advice) throws Exception {
        studentRepairService.updateRepairRecordByStudentRepairContent(repairContent, advice);
        return "redirect:/worker/queryNoDealRepairRecord.action";
    }

    /**
     * 根据学生维修内容修改学生报修信息及结果录入(已处理)
     *
     * @param repairContent
     * @param advice
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateRepairRecordByStudentRepairContentYet")
    public String updateRepairRecordByStudentRepairContentYet(String repairContent, String advice) throws Exception {
        studentRepairService.updateRepairRecordByStudentRepairContent(repairContent, advice);
        return "redirect:/worker/queryRepairRecord.action";
    }

    /**
     * 根据sudentId与repairContent来查找唯一报修记录(全部)
     *
     * @param studentId
     * @param repairContent
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRepairRecordByStudentIdAndrepairContentAll")
    public ModelAndView findRepairRecordByStudentIdAndrepairContentAll(String studentId, String repairContent) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<RepairRecordCustom> repairRecordCustomsList = new ArrayList<RepairRecordCustom>();
        repairRecordCustomsList = studentRepairService.findRepairRecordByStudentIdAndrepairContent(studentId, repairContent);

        List<RepairRecordCustomStr> repairRecordCustomStrList=new ArrayList<RepairRecordCustomStr>();
        RepairRecordCustomStr repairRecordCustomStr;
        repairRecordCustomStr=new RepairRecordCustomStr();
        repairRecordCustomStr=StudentUtil.repairRecordCustomToStr(repairRecordCustomsList.get(0));
        repairRecordCustomStrList.add(repairRecordCustomStr);

        modelAndView.addObject("repairRecordCustomStrList", repairRecordCustomStrList);
        modelAndView.setViewName("repair/repairUserInfermationAll");
        return modelAndView;
    }

    /**
     * 根据sudentId与repairContent来查找唯一报修记录(未处理)
     *
     * @param studentId
     * @param repairContent
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRepairRecordByStudentIdAndrepairContentNot")
    public ModelAndView findRepairRecordByStudentIdAndrepairContentNot(String studentId, String repairContent) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<RepairRecordCustom> repairRecordCustomsList = new ArrayList<RepairRecordCustom>();
        repairRecordCustomsList = studentRepairService.findRepairRecordByStudentIdAndrepairContent(studentId, repairContent);

        List<RepairRecordCustomStr> repairRecordCustomStrList=new ArrayList<RepairRecordCustomStr>();
        RepairRecordCustomStr repairRecordCustomStr;
        repairRecordCustomStr=new RepairRecordCustomStr();
        repairRecordCustomStr=StudentUtil.repairRecordCustomToStr(repairRecordCustomsList.get(0));
        repairRecordCustomStrList.add(repairRecordCustomStr);

        modelAndView.addObject("repairRecordCustomStrList", repairRecordCustomStrList);
        modelAndView.setViewName("repair/repairUserInfermationNot");
        return modelAndView;
    }

    /**
     * 根据sudentId与repairContent来查找唯一报修记录（已处理）
     *
     * @param studentId
     * @param repairContent
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRepairRecordByStudentIdAndrepairContentYet")
    public ModelAndView findRepairRecordByStudentIdAndrepairContentYet(String studentId, String repairContent) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<RepairRecordCustom> repairRecordCustomsList = new ArrayList<RepairRecordCustom>();
        repairRecordCustomsList = studentRepairService.findRepairRecordByStudentIdAndrepairContent(studentId, repairContent);

        List<RepairRecordCustomStr> repairRecordCustomStrList=new ArrayList<RepairRecordCustomStr>();
        RepairRecordCustomStr repairRecordCustomStr;
        repairRecordCustomStr=new RepairRecordCustomStr();
        repairRecordCustomStr=StudentUtil.repairRecordCustomToStr(repairRecordCustomsList.get(0));
        repairRecordCustomStrList.add(repairRecordCustomStr);

        modelAndView.addObject("repairRecordCustomStrList", repairRecordCustomStrList);
        modelAndView.setViewName("repair/repairUserInfermationYet");
        return modelAndView;
    }


    /********************************************个人信息模块****************************************************/
    /**
     * 查询当前个人信息
     *
     * @param session
     * @return
     * @throws Exception /worker/queryInfo.action
     */
    @RequestMapping("/queryInfo")
    public ModelAndView queryInfo(HttpSession session) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        String workerId = session.getAttribute("userId").toString();
        Worker worker = userService.findWorkerByWorkerId(workerId);
        modelAndView.addObject("worker", worker);
        modelAndView.setViewName("userManage/personalInformation");
        return modelAndView;
    }

    /**
     * 职工修改个人信息
     *
     * @param worker
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatePersonalInfo")
    public String updatePersonalInfo(Worker worker) throws Exception {
        userService.updateWorkerByWorkerId(worker);
        return "redirect:/worker/queryInfo.action";
    }


}
