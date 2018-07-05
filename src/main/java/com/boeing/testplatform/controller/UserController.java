package com.boeing.testplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.boeing.testplatform.bean.UserLogin;
import com.boeing.testplatform.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 调用service的接口，实现相应功能
 * 访问地址：http;//localhost:8088/getUserById
 */

@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserById")
    @ResponseBody
    public Object add() {

        logger.info("=============begin to get user by id==================");
        return userService.findByName();
    }

    /**
     * unity 用户登录
     * @param userLogin
     * @return
     */
    @RequestMapping(value = "/api/userlogin", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject userLogin(UserLogin userLogin) {
        logger.info("=============begin to login==================");
        String username = userLogin.getUsername();
        String password = userLogin.getPassword();
        System.out.println(username);
        JSONObject jsonObject = new JSONObject();

        if (username.equals("root") && password.equals("123")) {
            jsonObject.put("code", 200);
            jsonObject.put("success", true);
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("success", false);
        }

        return jsonObject;
    }


}