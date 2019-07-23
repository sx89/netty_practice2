package com.sx.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket server = null;

        try {
            server = new ServerSocket(PORT);
            System.out.println("服务器启动,端口为:" + PORT);
            Socket socket = null;

            while (true) {
                socket = server.accept();
                //拿到数据之后,通过handler来处理
                new Thread(new TimeServerHandler(socket)).run();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                System.out.println("服务器关闭");
                server.close();
            }
        }
    }
}
