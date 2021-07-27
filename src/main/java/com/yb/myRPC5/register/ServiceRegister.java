package com.yb.myRPC5.register;

import java.net.InetSocketAddress;

public interface ServiceRegister {
    void register(String serviceName, InetSocketAddress serverAdress);
    InetSocketAddress serviceDiscovery(String serviceName);
}
