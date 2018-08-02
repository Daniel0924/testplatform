package com.boeing.testplatform.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boeing.testplatform.bean.UserLogin;
import com.boeing.testplatform.service.UserService;
import com.boeing.testplatform.utils.ImageUtil;
import com.boeing.testplatform.utils.PostUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.io.*;


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
     *
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

    /**
     * 模拟钟灵的接收器，将来删除
     * 主要用于接收照片，然后返回照片的伤痕坐标。
     *
     * @param encodePhoto
     * @return
     */
    @RequestMapping(value = "/api/getZhongLingAxis", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getAxis(@RequestBody String encodePhoto, HttpServletRequest request) {

        JSONObject jsonPhoto = JSONObject.parseObject(encodePhoto);

        String content = (String) jsonPhoto.get("encodePhoto");

        logger.info("zhongling photo size: " + content.length());
        FileWriter imageWriter;
        try {

            imageWriter = new FileWriter("/Users/kiko/ydh/image.html");
            imageWriter.write(encodePhoto);
            imageWriter.flush();
            imageWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        ImageUtil.Base64ToImage(content, "/Users/kiko/ydh/ttt.jpg");
        JSONObject res = new JSONObject();
        JSONArray array = new JSONArray();

        double[] weight = {0.1, 0.15, 0.2};
        double[] pos = {-0.15, -0.1, -0.05, 0, 0.05, 0.1, 0.15, 0.2};
        JSONObject json = new JSONObject();
        json.put("info", " " + Math.random());
        json.put("cenx", "" + pos[(int) (Math.random() * pos.length)]);
        json.put("ceny", "" + pos[(int) (Math.random() * pos.length)]);
        json.put("width", "" + weight[(int) (Math.random() * weight.length)]);
        json.put("height", "" + weight[(int) (Math.random() * weight.length)]);
        json.put("class", "1");

        JSONObject json2 = new JSONObject();
        json2.put("info", " " + Math.random());
        json2.put("cenx", "0.1");
        json2.put("ceny", "0.0");
        json2.put("width", "0.1");
        json2.put("height", "0.1");
        json2.put("class", "3");

        array.add(json);
        array.add(json2);

        res.put("list", array);
        logger.info(res);
        return res;

    }


    /**
     * 接收unity传来的编码后的照片，并返回相应的伤痕坐标
     *
     * @param encodePhoto
     * @return
     */
    @RequestMapping(value = "/api/drawBorder", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject userLogin(String encodePhoto, String userName) {
        logger.info("===================the photo length is:" + encodePhoto.length() + "===================");
        logger.info("==================the userName is:" + userName + "==================");
        encodePhoto = encodePhoto.replace(' ', '+');
        logger.info("------------------begin to post photo to ZhongLing----------------");

        JSONObject jsonto = new JSONObject();
        jsonto.put("encodePhoto", encodePhoto);

        String url = "http://10.112.32.109:8088/api/getZhongLingAxis";

        logger.info("===================begin to draw border===================");

        return PostUtil.httpPostRequest(url, jsonto.toString(), false);

    }

}