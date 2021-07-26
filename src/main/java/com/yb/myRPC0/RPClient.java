package com.yb.myRPC0;

import com.yb.myRPC0.pojo.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class RPClient {
    public static void main(String[] args) {
        try {
            //build socket
            Socket socket = new Socket("127.0.0.1", 8899);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //send server id
            oos.writeInt(new Random().nextInt());
            oos.flush();
            User user = (User) ois.readObject();
            System.out.println("服务器返回user： " + user);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}