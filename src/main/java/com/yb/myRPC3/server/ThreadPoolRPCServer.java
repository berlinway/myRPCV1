package com.yb.myRPC3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRPCServer implements RPCServer {
    private final ThreadPoolExecutor threadPool;
    private ServiceProvider serviceProvide;

    public ThreadPoolRPCServer(ServiceProvider serviceProvide) {
        threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),1000,60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(100));
        this.serviceProvide = serviceProvide;
    }

    public ThreadPoolRPCServer(ServiceProvider serviceProvide,
                               int corePoolSize,
                               int maxPoolSize,
                               long keepAliveTime,
                               TimeUnit unit,
                               BlockingDeque<Runnable> workQueue) {
        //创建线程池
        threadPool = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime, unit,workQueue);
        this.serviceProvide = serviceProvide;
    }


    @Override
    public void start(int port) {
        System.out.println("server start!!!");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Socket socket = serverSocket.accept();
                threadPool.execute(new WorkThread(socket,serviceProvide));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
