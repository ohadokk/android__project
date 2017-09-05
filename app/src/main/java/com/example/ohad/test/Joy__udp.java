package com.example.ohad.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Joy__udp extends Thread {

    private static final int UDP_SERVER_PORT = 7;
    int power;
    String moveTo;
    Joy__udp(String move, int send){
        power=send;
        moveTo =move;
    }
   @Override
    public void run()  {
        String udpMsg = moveTo + power;
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
            InetAddress serverAddr = InetAddress.getByName("192.168.43.94");
            DatagramPacket dp;
            dp = new DatagramPacket(udpMsg.getBytes(), udpMsg.length(), serverAddr, UDP_SERVER_PORT);
            ds.send(dp);
        } catch (SocketException e) {
            e.printStackTrace();
        }catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }
}