package com.yb.myRPCVersion4.client;


import com.yb.myRPCVersion4.common.RPCRequest;
import com.yb.myRPCVersion4.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
