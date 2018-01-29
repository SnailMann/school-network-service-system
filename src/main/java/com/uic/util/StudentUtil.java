package com.uic.util;

import com.uic.po.*;
import com.uic.po.String.RepairRecordCustomStr;
import com.uic.po.String.StudentDTOStr;
import com.uic.po.String.StudentOrderStr;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于储存Student各类信息的工具方法
 */
public class StudentUtil {
    /**
     *用于Excel判断列数，用于赋值
     * @param value
     * @param num
     * @return
     * @throws ParseException
     */
    public static void ImportStudentDTO(StudentDTO studentDTO, User user, StudentBroadband studentBroadband, String value, int num) throws ParseException {
        System.out.println("ImportStudentDTO(String value,int num)--执行了");
        //如果为空值，则传入null
        switch (num+1){
            case 1:
                studentDTO.setStudentId(value);
                break;
            case 2:
                studentDTO.setName(value);
                break;
            case 3:
                studentDTO.setIdCard(value);
                break;
            case 4:
                studentDTO.setDorm(value);
                break;
            case 5:
                studentDTO.setPhone(value);
                break;
            case 6:
                if (!value.equals("")&&value!=null){
                    user.setUserPassword(value);
                    studentDTO.setUser(user);
                }else{
                    user.setUserPassword("");
                    studentDTO.setUser(user);
                }

                break;
            case 7:
                if (!value.equals("")&&value!=null){
                    studentBroadband.setAccessNumber(value);
                    studentDTO.setStudentBroadband(studentBroadband);
                }else{
                    studentBroadband.setAccessNumber("");
                    studentDTO.setStudentBroadband(studentBroadband);
                }

                break;
            case 8:
                if (!value.equals("")&&value!=null){
                    studentBroadband.setAccoutNumber(value);
                    studentDTO.setStudentBroadband(studentBroadband);
                }else{
                    studentBroadband.setAccoutNumber("");
                    studentDTO.setStudentBroadband(studentBroadband);
                }

                break;
            case 9:
                if (!value.equals("")&&value!=null){
                    studentBroadband.setSpeed((int)Double.parseDouble(value));
                    studentDTO.setStudentBroadband(studentBroadband);
                }else{
                    studentBroadband.setSpeed(0);
                    studentDTO.setStudentBroadband(studentBroadband);
                }

                break;
            case 10:
                if (!value.equals("")&&value!=null){
                    studentBroadband.setMoney(Double.parseDouble(value));
                    studentDTO.setStudentBroadband(studentBroadband);
                }else{
                    studentBroadband.setMoney(0);
                    studentDTO.setStudentBroadband(studentBroadband);
                }

                break;
            case 11:
                if (!value.equals("")&&value!=null){
                    studentBroadband.setStartTime(DateUtil.convertStringToDate("yyyy-MM-dd",value));
                    studentDTO.setStudentBroadband(studentBroadband);
                }else{
                    //如果表格中没有开通时间，则默认为2000-02-02，这是一个自定义的flag,如果要输出的时候则判断设置为null
                    studentBroadband.setStartTime(null);
                    studentDTO.setStudentBroadband(studentBroadband);
                }

                break;
            case 12:
                if (!value.equals("")&&value!=null){
                    studentBroadband.setLastTime((int)Double.parseDouble(value));
                    studentDTO.setStudentBroadband(studentBroadband);
                }else{
                    studentBroadband.setLastTime(0);
                    studentDTO.setStudentBroadband(studentBroadband);
                }

                break;
            case 13:
                if (!value.equals("")&&value!=null){
                    studentBroadband.setEndTime(DateUtil.convertStringToDate("yyyy-MM-dd",value));
                    studentDTO.setStudentBroadband(studentBroadband);
                }else{
                    ;
                    studentBroadband.setEndTime(null);
                    studentDTO.setStudentBroadband(studentBroadband);
                }

                break;

        }

    }

    /**
     * List<实体类> 转为List<Object>类型
     * http://blog.csdn.net/huqingpeng321/article/details/54616879
     * @param list
     * @param <E>
     * @return
     */
    static public <E> List<Object> toObject(List<E> list){
        List<Object> objlist = new ArrayList<Object>();
        for(Object e : list){
            Object obj = (Object)e;
            objlist.add(obj);
        }
        return objlist;
    }

    /**
     * StudentDTO转换成StudentDTOStr
     * @param studentDTO
     * @return
     * @throws Exception
     */
    public static StudentDTOStr studentDTOToStr(StudentDTO studentDTO)throws Exception{
        StudentDTOStr studentDTOStr=new StudentDTOStr();
        studentDTOStr.setStudentId(studentDTO.getStudentId());
        studentDTOStr.setName(studentDTO.getName());
        studentDTOStr.setIdCard(studentDTO.getIdCard());
        studentDTOStr.setDorm(studentDTO.getDorm());
        studentDTOStr.setPhone(studentDTO.getPhone());
        studentDTOStr.setAccessNumber(studentDTO.getStudentBroadband().getAccessNumber());
        studentDTOStr.setAccoutNumber(studentDTO.getStudentBroadband().getAccoutNumber());
        studentDTOStr.setSpeed(String.valueOf(studentDTO.getStudentBroadband().getSpeed()));
        studentDTOStr.setMoney(String.valueOf(studentDTO.getStudentBroadband().getMoney()));
        studentDTOStr.setLastTime(String.valueOf(studentDTO.getStudentBroadband().getLastTime()));
        studentDTOStr.setStartTime(DateUtil.convertDateToString(studentDTO.getStudentBroadband().getStartTime()));
        studentDTOStr.setEndTime(DateUtil.convertDateToString(studentDTO.getStudentBroadband().getEndTime()));


        return studentDTOStr;
    }

    /**
     * studentOrder转换为studentOrderStr
     * @param studentOrder
     * @return
     * @throws Exception
     */
    public static StudentOrderStr studentOrderToStr(StudentOrder studentOrder)throws Exception{
        StudentOrderStr studentOrderStr=new StudentOrderStr();
        studentOrderStr.setStudentId(studentOrder.getStudentId());
        studentOrderStr.setName(studentOrder.getName());
        studentOrderStr.setDorm(studentOrder.getDorm());
        studentOrderStr.setUserId(studentOrder.getUserId());
        studentOrderStr.setUserPassword(studentOrder.getUserPassword());
        studentOrderStr.setLastTime(String.valueOf(studentOrder.getLastTime()));
        studentOrderStr.setSpeed(String.valueOf(studentOrder.getSpeed()));
        studentOrderStr.setMoney(String.valueOf(studentOrder.getMoney()));
        studentOrderStr.setPrintTime(DateUtil.convertDateToString(studentOrder.getPrintTime()));
        studentOrderStr.setEndTime(DateUtil.convertDateToString(studentOrder.getEndTime()));
        studentOrderStr.setWorkerId(studentOrder.getWorkerId());
        studentOrderStr.setOrderId(studentOrder.getOrderId());
        studentOrderStr.setAccessNumber(studentOrder.getAccessNumber());

        return studentOrderStr;
    }

    /**
     * repairRecordCustom转换为repairRecordCustomStr
     * @param repairRecordCustom
     * @return
     * @throws Exception
     */
    public static RepairRecordCustomStr repairRecordCustomToStr(RepairRecordCustom repairRecordCustom)throws Exception{

        RepairRecordCustomStr repairRecordCustomStr=new RepairRecordCustomStr();
        repairRecordCustomStr.setRepairContent(repairRecordCustom.getRepairContent());
        repairRecordCustomStr.setStudentId(repairRecordCustom.getStudentId());
        repairRecordCustomStr.setAddTime(DateUtil.convertDateToString(repairRecordCustom.getAddTime()));
        repairRecordCustomStr.setContactTime(repairRecordCustom.getContactTime());
        repairRecordCustomStr.setAdvice(repairRecordCustom.getAdvice());
        repairRecordCustomStr.setResultFlag(String.valueOf(repairRecordCustom.getResultFlag()));
        repairRecordCustomStr.setName(repairRecordCustom.getName());
        repairRecordCustomStr.setPhone(repairRecordCustom.getPhone());
        repairRecordCustomStr.setDorm(repairRecordCustom.getDorm());

        //获得至今天多少天
        int ToNow=DateUtil.getMargin(DateUtil.getDate(),repairRecordCustomStr.getAddTime());
        repairRecordCustomStr.setToNow(String.valueOf(ToNow));

        return repairRecordCustomStr;
    }
}
