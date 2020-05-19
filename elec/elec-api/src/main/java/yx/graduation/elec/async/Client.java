package yx.graduation.elec.async;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("47.93.10.179", 9988);
        List<String> list = new ArrayList<>();
        list.add("200407A81KCFHKKP");
        list.add("20012GADSAKPSDHK");
        String registryMsg = JSON.toJSONString(list);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        PrintWriter pw = new PrintWriter(bw);
        pw.print(registryMsg+"\n");
        pw.flush();
        Thread.sleep(5000);
    }
}
