package com.sx.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author sunxu93@163.com
 * @date 19/7/21/021 12:59
 */

public class BioClient {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8080;
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket(HOST,PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("我是客户端");
            String string = in.readLine();
            System.err.println("服务器返回的响应时间是:"+string);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {{
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (out != null) {
                out.close();
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        }
    }
}
