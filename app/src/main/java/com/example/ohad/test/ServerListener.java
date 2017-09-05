package com.example.ohad.test;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//class for the phone that station on the robot
//the class is an AsyncTask that wait for the user phone
//to active drive mod and show the correct face while driving around
//it is used in the main activity
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
public class ServerListener extends AsyncTask<Void,Void,String> {
    private Socket socket;
    private ServerSocket serverSocket;

    String ServerInit() throws IOException {
        String stringData=null;
        try {
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            socket = serverSocket.accept();
            socket.getRemoteSocketAddress();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            stringData = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            socket.close();
            serverSocket.close();
        }

        return stringData;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            return ServerInit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
