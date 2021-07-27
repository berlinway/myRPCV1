package com.yb.myRPC2.common;

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
    private Object data;

    public static RPCRepose success(Object data){
        return RPCRepose.builder().data(data).code(200).build();
    }
    public static RPCRepose fail(Object data){
        return RPCRepose.builder().code(500).message("server error").build();
    }
}
