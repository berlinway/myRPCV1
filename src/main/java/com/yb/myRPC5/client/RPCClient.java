package com.yb.myRPC5.client;


import com.yb.myRPC5.common.RPCRequest;
import com.yb.myRPC5.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
