package com.yb.myRPC5.register;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.net.InetSocketAddress;
import java.util.List;

public class ZkServiceRegister implements ServiceRegister {
    //curator zookeeper client
    private CuratorFramework client;
    //zookeeper   root node
    private static final String ROOT_PATH = "MyRPC";

    //init zookeeper client ,, connect   to server


    public ZkServiceRegister() {
        //retry
        RetryPolicy policy = new ExponentialBackoffRetry(1000,3);
        //zookeeper   地址固定，消费者和服务提供者都要进行连接
        //sessionTimeOutMs与zoo.cfg中的tickTime有关系
        //zk还会根据minSessionTimeOut  与maxSessionTimeOut两个参数重新调整最后的超时值，默认为tickTime 的2倍和20倍
        //使用心跳监听状态
        this.client = CuratorFrameworkFactory.builder().connectString("vs.scuimage.xyz:2181")
                .sessionTimeoutMs(40000).retryPolicy(policy).namespace(ROOT_PATH).build();
        this.client.start();
        System.out.println("zookeeper conn success!!!");
    }

    @Override
    public void register(String serviceName, InetSocketAddress serverAdress) {
        // serviceName创建成永久节点，服务提供者下线时，不删服务名，只删地址
        try {
            if(client.checkExists().forPath("/"+serviceName)==null){
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/"+serviceName);
            }
            //路径地址，一个/代表一个节点
            String path = "/"+serviceName+"/"+getServiceAdress(serverAdress);
            //临时节点 服务器下线删除节点
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("service exist! ");
        }
    }



    @Override
    public InetSocketAddress serviceDiscovery(String serviceName) {
        try {
            List<String> strings = client.getChildren().forPath("/"+serviceName);
            String string = strings.get(0);
            return parseAdress(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getServiceAdress(InetSocketAddress serverAdress) {
        return serverAdress.getHostName()+":"+serverAdress.getPort();
    }
    private InetSocketAddress parseAdress(String address){
        String[] res = address.split(":");
        return new InetSocketAddress(res[0],Integer.parseInt(res[1]));
    }
}
