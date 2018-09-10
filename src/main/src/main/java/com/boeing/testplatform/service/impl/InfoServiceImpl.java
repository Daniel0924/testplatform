package com.boeing.testplatform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boeing.testplatform.mapper.UserMapper;
import com.boeing.testplatform.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InfoServiceImpl implements InfoService {


    @Override
    public JSONObject getPlaneInfo(int userId) {

        JSONObject result = new JSONObject();

        JSONArray plane = new JSONArray();

        JSONObject item1 = new JSONObject();
        item1.put("type", "747");
        item1.put("record", "okay:0.1,这是检测记录，希望可以继续保持噢，飞机性能正常，无明显伤痕，可以正常工作");
        item1.put("user", "Daniel");
        item1.put("date", "2018-08-08");

        JSONObject item2 = new JSONObject();
        item2.put("type", "747");
        item2.put("record", "okay");
        item2.put("user", "Daniel");
        item2.put("date", "2018-08-08");

        JSONObject item3 = new JSONObject();
        item3.put("type", "747");
        item3.put("record", "okay");
        item3.put("user", "Daniel");
        item3.put("date", "2018-08-08");

        JSONObject item4 = new JSONObject();
        item4.put("type", "747");
        item4.put("record", "okay");
        item4.put("user", "Daniel");
        item4.put("date", "2018-08-08");

        JSONObject item5 = new JSONObject();
        item5.put("type", "747");
        item5.put("record", "okay");
        item5.put("user", "Daniel");
        item5.put("date", "2018-08-08");

        JSONObject item6 = new JSONObject();
        item6.put("type", "747");
        item6.put("record", "okay");
        item6.put("user", "Daniel");
        item6.put("date", "2018-08-08");

        plane.add(item1);
        plane.add(item2);
        plane.add(item3);
        plane.add(item4);
        plane.add(item5);
        plane.add(item6);

        result.put("planeInfo", plane);
        return result;

    }

}
