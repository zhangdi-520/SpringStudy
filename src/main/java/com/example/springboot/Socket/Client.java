package com.example.springboot.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-05-07 17:16
 **/
public class Client extends Thread {

    Socket socket = null;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        new sendMessThread().start();
        super.run();

        try {
            InputStream s = socket.getInputStream();
            byte[] buf = new byte[1024];
            int len=0;
            while ((len=s.read(buf))!=-1){
                System.out.println(getdate() + "  服务器说：  "+new String(buf, 0, len,"UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class sendMessThread extends Thread {
        @Override
        public void run() {
            super.run();
            Scanner scanner = null;
            OutputStream os = null;
            try {
                scanner = new Scanner(System.in);
                os = socket.getOutputStream();
                String in = "";
                do {
                    in = scanner.next();
                    os.write(("" + in).getBytes());
                    os.flush();
                } while (!in.equals("bye"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String getdate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String result = format.format(date);
        return result;
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1",1234);
        client.start();
    }
}
