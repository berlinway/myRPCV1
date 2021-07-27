package com.yb.myRPC6.client;


import com.yb.myRPC6.common.RPCRequest;
import com.yb.myRPC6.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
