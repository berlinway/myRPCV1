package com.yb.myRPC6.register;

import java.net.InetSocketAddress;

public interface ServiceRegister {
    void register(String serviceName, InetSocketAddress serverAdress);
    InetSocketAddress serviceDiscovery(String serviceName);
}
