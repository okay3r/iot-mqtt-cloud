package yx.graduation.elec.async;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yx.graduation.elec.pojo.vo.MessageVo;
import yx.graduation.elec.service.DataRecordService;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class DeviceHandler implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsynTaskBean.class);

    private Socket socket;

    private DataRecordService dataRecordService;

    public DeviceHandler(Socket socket, DataRecordService dataRecordService) {
        this.socket = socket;
        this.dataRecordService = dataRecordService;
    }

    @Override
    public void run() {
        String host = this.socket.getInetAddress().toString();
        BufferedReader br = null;
        String message = null;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            PrintWriter pw = new PrintWriter(bw);
            //接收到的第一个数据应该是 ["200402003BYR6R1P","20040200PC9F4A3C"]
            String firstMsg = br.readLine();
            if (StringUtils.isNotBlank(firstMsg)) {
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
                System.out.println("注册设备：" + firstMsg);
            }
        } catch (Exception e) {
            LOGGER.error("注册连接设备错误:" + host);
            AsynTaskBean.closeConn(host);
            return;
        }

        while (!socket.isClosed()) {
            try {
                message = br.readLine();
                if (message == null) {
                    //断开连接
                    AsynTaskBean.closeConn(socket.getInetAddress().toString());
                    return;
                }
                // {"deviceId":"20040200PC9F4A3C","msg":"35","parameter":"dianya"}
                System.out.println(host + "===" + message);
                MessageVo messageVo = JSON.parseObject(message, MessageVo.class);
                //TODO 报警
                messageVo.setHost(host);
                // this.dataRecordService.handleMessage(messageVo);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("存储消息异常：" + message);
            }
        }
    }

}
