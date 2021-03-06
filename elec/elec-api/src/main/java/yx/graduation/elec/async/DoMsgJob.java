package yx.graduation.elec.async;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import yx.graduation.elec.pojo.vo.MessageVo;
import yx.graduation.elec.service.DataRecordService;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * 接收消息
 */
@Component
public class DoMsgJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsynTaskBean.class);

    @Autowired
    private MessageHandler messageHandler;


    @Async("msgTaskExecutor")
    public void doJob(Socket socket) {
        String host = socket.getInetAddress().toString();
        BufferedReader br = null;
        String message = null;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            PrintWriter pw = new PrintWriter(bw);
            //接收到的第一个数据应该是 ["200402003BYR6R1P","20040200PC9F4A3C"]
            String firstMsg = br.readLine();
            System.out.println("第一条数据：  " + firstMsg);
            if (StringUtils.isNotBlank(firstMsg)) {
                firstMsg = "[" + firstMsg + "]";
                List<String> deviceIdList = JSON.parseArray(firstMsg, String.class);
                if (deviceIdList.isEmpty() || deviceIdList.size() == 0) {
                    return;
                }
                //将此连接的设备id列表添加到map中
                AsynTaskBean.putConn(host, socket);
                AsynTaskBean.putHostDeviceIdList(host, deviceIdList);
                for (String deviceId : deviceIdList) {
                    //将此设备对应的writer添加到map中
                    AsynTaskBean.putDeviceWriter(deviceId, pw);
                }
                LOGGER.info("注册设备：" + firstMsg);
            }
        } catch (Exception e) {
            LOGGER.error("注册连接设备错误:" + host);
            AsynTaskBean.closeConn(host);
            return;
        }
        // {"deviceId":"2005117YT26HKKP0","msg":56","parameter":"guangzhaoqiangdu"}
        while (!socket.isClosed()) {
            try {
                message = br.readLine();
                if (message == null) {
                    //断开连接
                    AsynTaskBean.closeConn(socket.getInetAddress().toString());
                    return;
                }
                // {"deviceId":"20040200PC9F4A3C","msg":"35","parameter":"dianya"}
                message = fixMsg(message);

                LOGGER.info(host + "===" + message);

                MessageVo messageVo = JSON.parseObject(message, MessageVo.class);
                messageVo.setHost(host);

                this.messageHandler.handleMsg(messageVo);
            } catch (Exception e) {
                // e.printStackTrace();
                LOGGER.error("存储消息异常：" + message);
            }
        }
    }

    /**
     * 硬件端msg数据前缺个"，所以在这里加上
     */
    private String fixMsg(String message) {
        String[] parts = message.split(",");
        String msgPart = parts[1];
        String[] cutMsgPart = msgPart.split("\"");
        String value = cutMsgPart[cutMsgPart.length - 1];
        value = value.substring(1, value.length());

        StringBuilder stringBuilder = new StringBuilder();
        msgPart = "\"msg\":\"" + value + "\"";
        stringBuilder.append(parts[0] + ",");
        stringBuilder.append(msgPart + ",");
        stringBuilder.append(parts[2]);
        message = stringBuilder.toString();

        return message;
    }

}
