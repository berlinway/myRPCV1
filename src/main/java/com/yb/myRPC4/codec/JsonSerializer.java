package com.yb.myRPC4.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yb.myRPC4.common.RPCRepose;
import com.yb.myRPC4.common.RPCRequest;

public class JsonSerializer  implements Serializer{
    @Override
    public byte[] serialize(Object obj) {
        byte[] bytes = JSONObject.toJSONBytes(obj);
        return bytes;
    }

    @Override
    public Object deserialize(byte[] bytes, int messgeType) {
        Object obj= null;
        // 0： request  1：response
        switch(messgeType){
            case 0:
                RPCRequest request  = JSON.parseObject(bytes,RPCRequest.class);
                if(request.getParams()==null) return request;
                Object[] objects = new Object[request.getParams().length];
                // 把json字串转化成对应的对象， fastjson可以读出基本数据类型，不用转化
                for(int i=0;i< objects.length;i++){
                    Class<?> paramsType = request.getParamsTypes()[i];
                    if(!paramsType.isAssignableFrom(request.getParams()[i].getClass())){
                        objects[i] = JSONObject.toJavaObject((JSONObject) request.getParams()[i],request.getParamsTypes()[i]);
                    }else{
                        objects[i] = request.getParams()[i];
                    }
                }
                request.setParams(objects);
                obj = request;
                break;
            case 1:
                RPCRepose repose = JSON.parseObject(bytes,RPCRepose.class);
                Class<?> dataType = repose.getDataType();
                if(! dataType.isAssignableFrom(repose.getData().getClass())){
                    repose.setData(JSONObject.toJavaObject((JSONObject) repose.getData(),dataType));
                }
                obj = repose;
                break;
            default:
                System.out.println("not support now!");
                throw new RuntimeException();
        }
        return obj;
    }

    //1 for fastjson serialize
    @Override
    public int getType() {
        return 1;
    }
}
