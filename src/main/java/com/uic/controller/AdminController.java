package com.uic.controller;

import com.uic.po.User;
import com.uic.po.Worker;
import com.uic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 管理员控制层
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户url
     * @param user
     * @return
     * @throws Exception
     * /admin/insertUser.action
     */
    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public ModelAndView insertUser(User user)throws Exception {
        User u = userService.findUserByUserId(user.getUserId());
        ModelAndView modelAndView = new ModelAndView();
        if (u != null) {
            modelAndView.addObject("msg", "新增用户失败,用户ID已存在。");
        }else{
            userService.insertOrUpdateUser(user);
            if(!user.getUserRoleId().equals("1")){
                Worker worker = new Worker();
                worker.setWorkerId(user.getUserId());
                userService.insertWorker(worker);
            }
            modelAndView = this.queryUser(null);
        }
        return modelAndView;
    }

    /**
     * 遍历所有用户url
     * @return
     * @throws Exception
     * /admin/queryUser.action
     */
    @RequestMapping("/queryUser")
    public ModelAndView queryUser(User user)throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.queryUser(user);
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("userManage/userManagement");
        return modelAndView;
    }

    /**
     * 遍历所有职工url
     * @return
     * @throws Exception
     * /admin/queryWorker.action
     */
    @RequestMapping("/queryWorker")
    public ModelAndView queryWorker()throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Worker> workerList = userService.queryWorker();
        modelAndView.addObject("workerList", workerList);
        modelAndView.setViewName("userManage/employeeManagement");
        return modelAndView;
    }

    /**
     * 根据id修改职工信息
     * @param worker
     * @return
     * @throws Exception
     * /admin/updateWorkerInfo.action
     */
    @RequestMapping("/updateWorkerInfo")
    public ModelAndView updateWorkerInfo(Worker worker)throws Exception{
        userService.updateWorkerByWorkerId(worker);
        ModelAndView modelAndView = queryWorker();
        return modelAndView;
    }

    /**
     * 删除用户信息
     * @param userId
     * @return
     * @throws Exception
     * /admin/deleteUser.action
     */
    @RequestMapping("/deleteUser")
    public ModelAndView deleteUser(String userId, HttpSession session)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        if(session.getAttribute("userId").toString().equals(userId)){
            modelAndView = this.queryUser(null);
            modelAndView.addObject("msg","亲，您不能删除自己。");
            return modelAndView;
        }
        User user = userService.findUserByUserId(userId);
        userService.deleteUser(userId);
        if (!user.getUserRoleId().equals("1")) {
            userService.deleteWorker(userId);
        }
        modelAndView=this.queryUser(null);
        return modelAndView;
    }
}