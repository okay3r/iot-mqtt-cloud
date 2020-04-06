package yx.graduation.elec.config;

import com.alibaba.fastjson.JSON;
import yx.graduation.elec.pojo.vo.MessageVo;

import java.util.ArrayList;
import java.util.List;

public class CreateJson {
    public static void main(String[] args) {
        MessageVo messageVo = new MessageVo();
        messageVo.setMsg("150");
        messageVo.setDeviceId("200406B1N7SY344H");
        messageVo.setParameter("dianliu");
        String s = JSON.toJSONString(messageVo);
        System.out.println(s);
        String to = "{\"deviceId\":\"dianji\",\"msg\":\"888\",\"parameter\":\"tem\"}\n";
        MessageVo vo = JSON.parseObject(to, MessageVo.class);
        System.out.println(vo);

        List<String> list = new ArrayList<>();
        list.add("200406B1N7SY344H");

        System.out.println(JSON.toJSONString(list));
    }
}
