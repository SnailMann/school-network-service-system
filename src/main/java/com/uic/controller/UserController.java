package com.uic.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.uic.po.Role;
import com.uic.po.User;
import com.uic.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 用户管理控制层
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private WorkerController workerController;
    @Autowired
    private AdminController adminController;

    /**
     * 用户登录url
     * @param userId
     * @param password
     * @param session
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(String userId, String password, HttpSession session)throws Exception {
        User user = userService.findUserByUserId(userId);
        JSONObject jsonObject = new JSONObject();
        if (user == null) {
            jsonObject.put("state", "noUser");
        }else{
            if(user.getFlag()!=0){
                if (!password.equals(user.getUserPassword())) {
                    jsonObject.put("state", "errorPassword");
                }else{
                    Role role = userService.findRoleByRoleId(user.getUserRoleId());
                    if(!role.getRoleName().equals("学生")){
                        session.setAttribute("userId",user.getUserId());
                        session.setAttribute("userRole",role.getRoleName());
                        if (role.getRoleName().equals("职工")) {
                            jsonObject.put("character", "worker");
                        }
                        if (role.getRoleName().equals("管理员")) {
                            jsonObject.put("character", "admin");
                        }
                    }else{
                        jsonObject.put("state", "errorRole");
                    }
                }
            }else {
                jsonObject.put("state", "noUser");
            }
        }
        return jsonObject.toString();
    }

    /**
     * 跳转至职工主页
     * @return
     * @throws Exception
     */
    @RequestMapping("/toWork")
    public ModelAndView toWorker() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPageWork");
        return modelAndView;
    }

    /**
     * 跳转至管理员主页
     * @return
     * @throws Exception
     */
    @RequestMapping("/toAdmin")
    public ModelAndView toAdmin() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPageAdmin");
        return modelAndView;
    }

    /**
     * 用户退出url
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session)throws Exception {
        session.invalidate();
        return ("redirect:/login.jsp");
    }

    /**
     * 修改用户密码url
     * @param session
     * @param user
     * @return
     * @throws Exception
     * /user/updatePassword.action
     */
    @RequestMapping("/updatePassword")
    public ModelAndView  updatePassword(HttpSession session,User user,String oldPassword)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        if(session.getAttribute("userRole").toString().equals("管理员") && !session.getAttribute("userId").toString().equals(user.getUserId())){
            userService.updateUserPasswordByUserId(user.getUserPassword(),user.getUserId());
            modelAndView = adminController.queryUser(null);
        }else{
            User u = userService.findUserByUserId(session.getAttribute("userId").toString());
            if (u.getUserPassword().equals(oldPassword)) {
                modelAndView=workerController.queryInfo(session);
                userService.updateUserPasswordByUserId(user.getUserPassword(), session.getAttribute("userId").toString());
            } else {
                modelAndView.addObject("msg", "旧密码不正确。");
                modelAndView.setViewName("userManage/modifyPassword");
            }
        }
        return modelAndView;
    }

    /**
     * 跳转至个人密码修改页面url
     * @param session
     * @return
     * @throws Exception
     * /user/jumpToModifyPasswordView.action
     */
    @RequestMapping("/jumpToModifyPasswordView")
    public ModelAndView jumpToModifyPasswordView(HttpSession session)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        String id = session.getAttribute("userId").toString();
        modelAndView.addObject("userId", id);
        modelAndView.setViewName("/userManage/modifyPassword");
        return modelAndView;
    }
}
