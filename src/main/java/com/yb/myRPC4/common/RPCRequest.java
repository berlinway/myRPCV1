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
public class RPCRequest implements Serializable {
    private String intefaceName;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramsTypes;
}
