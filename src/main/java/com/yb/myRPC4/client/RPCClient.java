package com.yb.myRPC4.client;

import com.yb.myRPC4.common.RPCRepose;
import com.yb.myRPC4.common.RPCRequest;

public interface RPCClient {
    RPCRepose sendRequest(RPCRequest request);
}
