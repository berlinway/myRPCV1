package com.yb.myRPC2.server;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.util.Map;

public class SimpleRPCServer  implements RPCServer{

    private ServiceProvider serviceProvide;

    public SimpleRPCServer(ServiceProvider serviceProvide) {
        this.serviceProvide = serviceProvide;
    }

    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("server start !!!");
            //bio
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket,serviceProvide)).start();


            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("server start fail");
        }
    }

    @Override
    public void stop() {

    }
}
