package com.example.cs437;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{

    private int port;
    private ServerSocket serverSocket;
    RecyclerView recyclerView;
    ArrayList<FoodItem> foodItems;
    Context context;
    private String intToIp(int ip) {
        return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 24) & 0xFF);
    }

    public Server(int port, ArrayList<FoodItem> foodItems, RecyclerView recyclerView, Context context){
        this.port = port;
        this.recyclerView = recyclerView;
        this.foodItems = foodItems;
        System.out.println("Initial size is " + foodItems.size());

        this.context = context;
        try {
            this.serverSocket = new ServerSocket(port, 0, InetAddress.getByName("0.0.0.0"));
            WifiManager wifiManager = (WifiManager) this.context.getSystemService(Context.WIFI_SERVICE);

            // Get the Wi-Fi info for the current connection
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();

            // Get the IP address as a string and display it in a text view
            String ipAddress = intToIp(wifiInfo.getIpAddress());

            System.out.println("The IP address is " + ipAddress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void run(){
        while (true){
            try {
                Socket socket = serverSocket.accept();
                SocketHandler s = new SocketHandler(socket);
                s.start();
                s.join();
                FoodItem foodItem = s.getFoodItems();
                System.out.println("The size is " + foodItems.size());
                this.foodItems.add(foodItem);
                this.updateUI();
                System.out.println("After updating The size is " + foodItems.size());


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateUI(){
        Activity activity = (Activity) this.context;
        activity.runOnUiThread(new Runnable() {
           @Override
           public void run() {
               recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
               FoodItemAdapter eventsAdapter = new FoodItemAdapter(foodItems, context);
               recyclerView.setAdapter(eventsAdapter);
           }
       });

    }
}
