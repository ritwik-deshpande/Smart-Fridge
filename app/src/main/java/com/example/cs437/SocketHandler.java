package com.example.cs437;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketHandler extends Thread{

    private Socket socket;
    private FoodItem foodItem;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Gson gson;
    public SocketHandler(Socket socket){
        this.socket = socket;
    }

    public void run(){
        System.out.print("Client Accepted" + socket.getPort());
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            gson = new GsonBuilder().setLenient().create();

            String line = "";
            System.out.println("Start read");
            while(!line.equals("exit")){
                System.out.println("Reading");
                line = dataInputStream.readLine();
                System.out.println(line);
                if (!line.equals("exit")) {
                    System.out.println("the length of received string is " + line.length());
                    foodItem = gson.fromJson(line, FoodItem.class);
                }
            }
            System.out.println(foodItem.getFoodImage());
            System.out.println(foodItem.getName());
            socket.close();
            dataInputStream.close();
            dataOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public FoodItem getFoodItems(){
        return foodItem;
    }
}
