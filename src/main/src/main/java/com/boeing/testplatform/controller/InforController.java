package com.boeing.testplatform.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boeing.testplatform.service.InfoService;
import com.boeing.testplatform.service.impl.InfoServiceImpl;
import com.boeing.testplatform.utils.PostUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 主要是飞机信息及检测记录方面的控制器，提供接口服务
 */
@Controller
public class InforController {

    private Logger logger = Logger.getLogger(InforController.class);

    @Autowired
    InfoService infoService;

    /**
     * 接收react native传来的查看飞机信息的请求，并返回相关数据
     *
     * @return
     */
    @RequestMapping(value = "/api/homeInfo")
    @ResponseBody
    public JSONObject userLogin() {
        logger.info("===================get information from react native===================");
        //logger.info(userId);

        return infoService.getPlaneInfo(111);

        //JSONObject Data = JSONObject.parseObject(userId);

        //logger.info(Data.get("firstParam"));

    }

}
