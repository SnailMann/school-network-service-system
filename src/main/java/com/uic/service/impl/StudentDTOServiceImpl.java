package com.uic.service.impl;

import com.uic.mapper.StudentBroadbandMapper;
import com.uic.mapper.StudentDTOMapper;
import com.uic.mapper.StudentMapper;
import com.uic.mapper.UserMapper;
import com.uic.po.Student;
import com.uic.po.StudentBroadband;
import com.uic.po.StudentDTO;
import com.uic.po.User;
import com.uic.service.StudentDTOService;
import com.uic.util.ExcelInUtil;
import com.uic.util.ExcelOutUtil;
import com.uic.util.StudentUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDTO业务层实现类
 * 实现学生基本信息与宽带信息的操作
 */
public class StudentDTOServiceImpl implements StudentDTOService{

    @Autowired
    private StudentDTOMapper studentDTOMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentBroadbandMapper studentBroadbandMapper;
    @Autowired
    private UserMapper userMapper;


    /**
     * 上传Excel文件，读取数据
     * 通过插入Excel录入学生基本信息和宽带信息和密码信息
     * @param filePath
     * @throws Exception
     */
    @Transactional
    @Override
    public void uploadExcel(String filePath) throws Exception {
        List<StudentDTO> studentDTOList=new ArrayList<StudentDTO>();
        List<Student>studentList=new ArrayList<Student>();
        List<StudentBroadband> studentBroadbandList=new ArrayList<StudentBroadband>();
        List<User> userList=new ArrayList<User>();
        List<Row> rowList = new ArrayList<Row>();
        Row row;
        StudentDTO studentDTO;
        Student student;
        StudentBroadband studentBroadband;
        User user;
        try {
            ExcelInUtil eu = new ExcelInUtil();
            eu.setExcelPath(filePath);
            System.out.println("=======测试Excel 默认 读取========");
            rowList=eu.readExcel();
            for (int i = 1; i <rowList.size() ; i++) {
                row=rowList.get(i);
                studentDTO=new StudentDTO();
                studentBroadband=new StudentBroadband();
                user=new User();
                if (row != null) {
                    for (int j = 0; j <row.getLastCellNum();j++) {
                        String value=eu.getCellValue(row.getCell(j));
                        try {
                            StudentUtil.ImportStudentDTO(studentDTO,user,studentBroadband,value,j);//赋值
                        }catch (ParseException e){
                            e.getStackTrace();
                        }
                    }
                    //得到studentDTOList
                    studentDTOList.add(studentDTO);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //得到studentDTOList,拆分学生表、宽带信息表、用户表
        for (int i = 0; i <studentDTOList.size() ; i++) {
            studentDTO=new StudentDTO();
            student=new Student();
            studentBroadband=new StudentBroadband();
            user=new User();
            studentDTO=studentDTOList.get(i);
            if (studentDTO!=null){//如果studentId不等于空，则继续
                student.setStudentId(studentDTO.getStudentId());
                student.setName(studentDTO.getName());
                student.setIdCard(studentDTO.getIdCard());
                student.setDorm(studentDTO.getDorm());
                student.setPhone(studentDTO.getPhone());
                studentBroadband=studentDTO.getStudentBroadband();
                studentBroadband.setStudentId(studentDTO.getStudentId());
                user=studentDTO.getUser();
                user.setUserId(studentDTO.getStudentId());
                user.setUserRoleId("1");

                studentList.add(student);
                studentBroadbandList.add(studentBroadband);
                userList.add(user);
            }

        }
        for (StudentDTO s:studentDTOList){
            System.out.println("Controller:"+s);
        }
        for (Student s:studentList){
            studentMapper.insertOrUpdateStudent(s);
            System.out.println("Controller:"+s);
        }
        for (StudentBroadband s:studentBroadbandList) {
            studentBroadbandMapper.insertOrUpdateStudentBroadband(s);
            System.out.println("Controller:"+s);
        }
        for (User u:userList){
            userMapper.insertOrUpdateUser(u);
            System.out.println("Controller:"+u);
        }



    }

    /**
     * 导出Excel文件到服务器
     * 从服务器下载文件到本地
     * @param fileName
     * @return
     * @throws Exception
     */
    @Override
    public void downloadExcel(String fileName,String filePath) throws Exception {
        String sheetName="学生信息表";
        String sheetTitle="";
        StudentDTO studentDTO;
        Student student;
        StudentBroadband studentBroadband;
        User user ;
        List<String> columnNames=new ArrayList<String>();
        columnNames.add("学号");columnNames.add("姓名");columnNames.add("身份证号码");
        columnNames.add("房号");columnNames.add("电话");columnNames.add("密码");
        columnNames.add("接入号");columnNames.add("账号");columnNames.add("宽带速度");
        columnNames.add("金额");columnNames.add("开通时间");columnNames.add("开通月份");columnNames.add("到期时间");
        List<StudentDTO> studentDTOList=new ArrayList<StudentDTO>();
        studentDTOList=studentDTOMapper.queryStudentAllInfo();
        List<List<Object>> objects=new ArrayList<>();
        List<Object> object1;
        for (int i = 0; i <studentDTOList.size() ; i++) {
            studentDTO=new StudentDTO();
            studentBroadband=new StudentBroadband();
            user=new User();
            object1=new ArrayList<>();
            //将List所有的属性都拆分出来，分别作为一个单元
            studentDTO=studentDTOList.get(i);
            object1.add(studentDTO.getStudentId());
            object1.add(studentDTO.getName());
            object1.add(studentDTO.getIdCard());
            object1.add(studentDTO.getDorm());
            object1.add(studentDTO.getPhone());
            user=studentDTO.getUser();
            object1.add(user.getUserPassword());
            studentBroadband=studentDTO.getStudentBroadband();
            object1.add(studentBroadband.getAccessNumber());
            object1.add(studentBroadband.getAccoutNumber());
            object1.add(studentBroadband.getSpeed());
            object1.add(studentBroadband.getMoney());
            object1.add(studentBroadband.getStartTime());
            object1.add(studentBroadband.getLastTime());
            object1.add(studentBroadband.getEndTime());

            objects.add(object1);

        }
        try {
            //直接写入数据--第一种方式
            ExcelOutUtil.writeExcel(filePath, fileName, sheetName, columnNames, sheetTitle, objects, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据学号判断该生的联合信息是否已经存在
     * 存在就返回true
     * 不存在就返回false
     * @param studentId
     * @return
     * @throws Exception
     */
    @Override
    public boolean findIsStudentDTOById(String studentId) throws Exception {
        boolean isFlag=false;
        if (studentMapper.findStudentCountById(studentId)>0&&studentBroadbandMapper.findStudentBroadbandCountById(studentId)>0){
            isFlag=true;
        }
        if (studentMapper.findStudentCountById(studentId)==0&&studentBroadbandMapper.findStudentBroadbandCountById(studentId)==0){
            isFlag=false;
        }
        return isFlag;
    }

    /**
     * 遍历学生信息与宽带信息（两表）
     * student、studentBroadband
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentDTO> queryStudentAndBroadband() throws Exception {
        return studentDTOMapper.queryStudentAndBroadband();
    }

    /**
     * 根据学生学号遍历学生信息与宽带信息（两表）
     * student、studentBroadband
     * @param studentId
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentDTO> findStudentAndBroadbandById(String studentId) throws Exception {
        return studentDTOMapper.findStudentAndBroadbandById(studentId);
    }

    /**
     * 嵌套查询,只能查询到订单表存在的学号的学生信息和宽带信息（两表）
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentDTO> queryStudentAndBroadbandByOrder() throws Exception {
        return studentDTOMapper.queryStudentAndBroadbandByInOrder();
    }

    /**
     * 根据学号嵌套查询,只能查询到订单表存在的学号的学生信息和宽带信息（两表）
     * @param studentId
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentDTO> findStudentAndBroadbandByInOrder(String studentId) throws Exception {
        return studentDTOMapper.findStudentAndBroadbandByInOrder(studentId);
    }

    /**
     * 嵌套查询,只能查询到订单表不存在的学号的学生信息和宽带信息（两表）
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentDTO> queryStudentAndBroadbandByNotInOrder() throws Exception {
        return studentDTOMapper.queryStudentAndBroadbandByNotInOrder();
    }

    /**
     * 根据学号嵌套查询,只能查询到订单表不存在的学号的学生信息和宽带信息（两表）
     * @param studentId
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentDTO> findStudentAndBroadbandByNotInOrder(String studentId) throws Exception {
        return studentDTOMapper.findStudentAndBroadbandByNotInOrder(studentId);
    }

    /**
     * 遍历学生与宽带的联合信息
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentDTO> queryStudentAllInfo() throws Exception {

        return studentDTOMapper.queryStudentAllInfo();
    }

    /**
     * 根据学号来遍历学生与宽带的联合信息
     * @param studentId
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentDTO> findStudentAllInfoById(String studentId) throws Exception {
        return studentDTOMapper.findStudentAllInfoById(studentId);
    }

    /**
     * 录入学生基本信息和宽带信息
     * @param student
     * @param studentBroadband
     * @throws Exception
     */
    @Override
    public void insertStudentAllInfo(Student student, StudentBroadband studentBroadband) throws Exception {
        studentMapper.insertStudent(student);
        studentBroadbandMapper.insertStudentBroadband(studentBroadband);


    }

    /**
     * 根据学生学号来修改学生的基本信息和宽带信息
     * @param studentId
     * @param studentDTO
     * @throws Exception
     */
    @Override
    public void updateStudentAllInfoById(String studentId, StudentDTO studentDTO) throws Exception {
        studentDTOMapper.updateStudentAllInfoById(studentId,studentDTO);
    }

    /**
     * 根据学生学号来删除学生的基本信息和宽带信息
     * @param studentId
     * @throws Exception
     */
    @Override
    public void deleteStudentAllInfoById(String studentId) throws Exception {
        studentDTOMapper.deleteStudentAllInfoById(studentId);
    }
}
