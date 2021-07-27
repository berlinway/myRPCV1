package com.yb.myRPC2.server;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {

    private Map<String,Object> interfaceProvider;

    public ServiceProvider() {
        interfaceProvider = new HashMap<>();
    }

    public void provideServiceInterface(Object service){
        Class<?>[] interfaces = service.getClass().getInterfaces(); //表示一个类实现的所有接口类集合
        for(Class<?> clazz:interfaces){
            interfaceProvider.put(clazz.getName(),service);
        }
    }
    public Object getService(String interfaceName){
        return interfaceProvider.get(interfaceName);
    }
}
