package com.yb.myRPC3.client;

import com.yb.myRPC3.common.RPCRepose;
import com.yb.myRPC3.common.RPCRequest;

public interface RPCClient {
    RPCRepose sendRequest(RPCRequest request);
}
