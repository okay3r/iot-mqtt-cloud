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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

@Component
public class AsynTaskBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsynTaskBean.class);

    @Autowired
    private DataRecordService dataRecordService;

    @Autowired
    private DoMsgJob doMsgJob;

    /**
     * host = socket
     */
    private static Map<String, Socket> connMap = new HashMap<>();

    /**
     * deviceId = writer
     */
    private static Map<String, PrintWriter> deviceWriterMap = new HashMap<>();

    /**
     * host = deviceIdList
     */
    private static Map<String, List<String>> hostDeviceMap = new HashMap<>();


    /**
     * 处理新的连接
     */
    @Async("connTaskExecutor")
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

                //开启线程处理
                // new Thread(new DeviceHandler(socket, dataRecordService)).start();
                doMsgJob.doJob(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





    public static Set<String> getCurrentConns() {
        return connMap.keySet();
    }

    public static String closeConn(String host) {
        Socket socket = connMap.get(host);
        if (socket == null) {
            return "host不存在";
        }
        try {
            socket.close();
            connMap.remove(host);
            List<String> deviceIdList = hostDeviceMap.get(host);
            if (deviceIdList != null) {
                for (String deviceId : deviceIdList) {
                    deviceWriterMap.remove(deviceId);
                }
            }
            hostDeviceMap.remove(host);

            LOGGER.info(host + "断开连接");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "断开连接成功";
    }

    public static void putConn(String host, Socket socket) {
        connMap.put(host, socket);
    }

    public static void putDeviceWriter(String deviceId, PrintWriter printWriter) {
        deviceWriterMap.put(deviceId, printWriter);
    }

    public static void putHostDeviceIdList(String host, List<String> deviceIdList) {
        hostDeviceMap.put(host, deviceIdList);
    }

    public static Map<String, List<String>> getHostDeviceMap() {
        return hostDeviceMap;
    }

    public static Map<String, Socket> getConnMap() {
        return connMap;
    }

    public static Map<String, PrintWriter> getDeviceWriterMap() {
        return deviceWriterMap;
    }

    /**
     * 向设备发送消息
     */
    public static void writeTo(String deviceId, String msg) {
        PrintWriter printWriter = deviceWriterMap.get(deviceId);
        printWriter.write(msg);
        printWriter.flush();
    }

    @Async("msgTaskExecutor")
    public void doTest() {
        for (int i = 0; i < 5; i++) {
            LOGGER.info(i + "");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
