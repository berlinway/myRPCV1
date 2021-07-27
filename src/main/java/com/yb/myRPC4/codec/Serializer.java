package com.yb.myRPC4.codec;



public interface Serializer {
    //将对象序列化为字节数组
    byte[] serialize(Object obj);
    //从字节数组反序列化为消息，使用java自带的序列化方式不用messageType
    //其他方式指定消息格式，再根据message转化成相应的对象
    Object deserialize(byte[] bytes,int meaasgeType);

    //返回使用的序列器
    //0：java 自带的序列化方式   1：json序列化方式
    int getType();
    static Serializer getSerializerByCode(int code){
        switch (code){
            case 0:
                return new ObjectSerializer();
            case 1:
                return new JsonSerializer();
            default:
                return null;
        }
    }


}
