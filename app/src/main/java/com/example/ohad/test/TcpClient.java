package com.example.ohad.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ohad.test.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// the class is orignly for the user phone to send the station phone a tcp massage
// for the diving request
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
public class TcpClient {

    public static final String SERVER_IP = "192.168.43.1"; //server IP address
    public static final int SERVER_PORT = 8080; //server port

    // message to send to the server
    private String mServerMessage;

    // sends message received notifications
    // while this is true, the server will continue running
    private boolean mRun = false;

    // used to send messages
    private PrintWriter mBufferOut;

    // used to read messages from the server
    private BufferedReader mBufferIn;

    Thread trd;


    public TcpClient(final String Flag){
        final String flag=Flag;
        trd = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        mRun = true;
                        try {
                            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                            Socket socket = new Socket(serverAddr, SERVER_PORT);

                            try {
                                mBufferOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                                sendMessage(flag);
                                mBufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                            } catch (Exception e) {
                               e.getMessage();
                            } finally {
                                socket.close();
                                stopClient();

                            }

                        } catch (Exception e) {
                           e.getMessage();
                        }}
                });
                trd.start();
            }


    public void sendMessage(String message) {
        if (mBufferOut != null && !mBufferOut.checkError()) {
            mBufferOut.println(message);
            mBufferOut.flush();
        }
    }

    public void stopClient() {

        mRun = false;

        if (mBufferOut != null) {
            mBufferOut.flush();
            mBufferOut.close();
        }
        mBufferIn = null;
        mBufferOut = null;
        mServerMessage = null;
    }

}