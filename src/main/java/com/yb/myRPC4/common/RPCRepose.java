package com.yb.myRPC4.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RPCRepose  implements Serializable {
    private int code;
    private String message;
    // 更新,这里我们需要加入这个，不然用其它序列化方式（除了java Serialize）得不到data的type
    private Class<?> dataType;
    private Object data;

    public static RPCRepose success(Object data){
        return RPCRepose.builder().data(data).code(200).build();
    }
    public static RPCRepose fail(){
        return RPCRepose.builder().code(500).message("server error").build();
    }
}
