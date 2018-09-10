import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import com.boeing.testplatform.utils.PostUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class jsonTest {


    @Test
    public void jsonParse() {

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
        json2.put("class", "3");
        json2.put("height", "0.1");


        array.add(json);
        array.add(json2);

        System.out.println(array);


    }




    @Test
    public void planeJson() {
        JSONObject result = new JSONObject();

        JSONArray plane = new JSONArray();

        JSONObject item1 = new JSONObject();
        item1.put("type", "747");
        item1.put("record", "okay");
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

        System.out.println(result.toString());

    }

}
