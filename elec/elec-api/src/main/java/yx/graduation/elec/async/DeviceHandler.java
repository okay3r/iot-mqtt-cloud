package yx.graduation.elec.async;

import com.alibaba.fastjson.JSON;
import yx.graduation.elec.pojo.vo.MessageVo;

import java.io.*;
import java.net.Socket;

public class DeviceHandler implements Runnable {

    private Socket socket;

    public DeviceHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            InputStream inputStream = socket.getInputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));// 获得输出流
            PrintWriter pw = new PrintWriter(bw);

            while (true) {
                String message = br.readLine();
                MessageVo messageVo = JSON.parseObject(message, MessageVo.class);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
