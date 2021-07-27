package com.yb.myRPC4.client;

import com.yb.myRPC4.common.RPCRepose;
import com.yb.myRPC4.common.RPCRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {
    public static RPCRepose sendRequest(String host, int port, RPCRequest request){
        try {
            Socket socket = new Socket(host,port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//            System.out.println(request);
            oos.writeObject(request);
            oos.flush();
            RPCRepose repose = (RPCRepose) ois.readObject();
            return repose;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
