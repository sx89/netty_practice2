package com.sx.echo;

/**
 * @author sunxu93@163.com
 * @date 19/7/21/021 18:54
 */
public class EchoServer {
    private int port ;

    public EchoServer(int port) {
        this.port = port;
    }

    public void run() {


    }
    public static void main(String[] args) {
        System.out.println();
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new EchoServer(port).run();
    }
}
