package yx.graduation.elec.async;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import yx.graduation.elec.pojo.vo.MessageVo;
import yx.graduation.elec.service.DataRecordService;
import yx.graduation.elec.service.UserService;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

@Component
public class AsynTaskBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsynTaskBean.class);

    @Autowired
    private DataRecordService dataRecordService;

    private Map<String, Socket> connMap = new HashMap<>();

    @Async
    public void handleConn() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9988);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                InetAddress address = socket.getInetAddress();
                LOGGER.info(address + "建立连接");
                connMap.put(address.toString(), socket);
                this.doJob(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Async
    public void doJob(Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));// 获得输出流
        PrintWriter pw = new PrintWriter(bw);
        String message = null;
        while (true) {
            try {
                while (true) {
                    message = br.readLine();
                    MessageVo messageVo = JSON.parseObject(message, MessageVo.class);
                    this.dataRecordService.storeMessage(messageVo);
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("存储消息异常：" + message);
            }
        }
    }

    public Set<String> getCurrentConns() {
        return this.connMap.keySet();
    }

    public void closeConn(String address) {
        Socket socket = this.connMap.get(address);
        try {
            socket.close();
            this.connMap.remove(address);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        MessageVo messageVo = new MessageVo();
        messageVo.setMsg("999");
        messageVo.setDeviceId("20040102R6A5KH28");
        messageVo.setParameter("temperature");
        String s = JSON.toJSONString(messageVo);
        System.out.println(s);
        String to = "{\"deviceId\":\"dianji\",\"msg\":\"888\",\"parameter\":\"tem\"}\n";
        MessageVo vo = JSON.parseObject(to, MessageVo.class);
        System.out.println(vo);
    }
}
