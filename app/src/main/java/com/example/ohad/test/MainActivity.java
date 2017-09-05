package com.example.ohad.test;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity{

    //two fragments for two layouts
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    Fragment OpenEmoji,EmojiMenuBar;
    FragmentManager Fragment_Manager;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing_fragment_replace);

        OpenEmoji = new Open_Emoji();
        EmojiMenuBar = new Emoji_Menu_Bar();

        //get android system fragment manager
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Fragment_Manager = getFragmentManager();
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        // use fragment manager to set open emoji as first fragment to appear
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        FragmentTransaction fragmentTransaction = Fragment_Manager.beginTransaction();
        fragmentTransaction.replace(R.id.main2,OpenEmoji );
        fragmentTransaction.commit();
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        ChackMsg();

    }
    //function is not used . originally used for the phone on the robot for face change when
    //activated from joystick
    // the server listen for the order "A" for drive and "B" for wait on the test_main activity
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void ChackMsg(){
        new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    if ((new ServerListener().execute().get()).equals("B")) {
                        startActivity(new Intent(MainActivity.this, test_main.class));

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }});}
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //function to switch fragment in the correct order
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void SwitchFrag(){
        if(((Open_Emoji)OpenEmoji).TamaActivity){
            try {
                OpenEmoji = new Open_Emoji();
                Intent i = new Intent(MainActivity.this, Activity_Tamaguzi.class);

                startActivity(i);
                finish();
                ((Open_Emoji)OpenEmoji).MenuFragment = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(((Open_Emoji)OpenEmoji).MenuFragment){

            try {
                EmojiMenuBar = new Emoji_Menu_Bar();

                FragmentTransaction fragmentTransaction = Fragment_Manager.beginTransaction();
                fragmentTransaction.replace(R.id.main2,EmojiMenuBar );
                fragmentTransaction.commit();
                ((Open_Emoji)OpenEmoji).MenuFragment = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(((Emoji_Menu_Bar)EmojiMenuBar).OpenEmoji){
            try {
                OpenEmoji = new Open_Emoji();

                FragmentTransaction fragmentTransaction = Fragment_Manager.beginTransaction();
                fragmentTransaction.replace(R.id.main2,OpenEmoji );
                fragmentTransaction.commit();
                ((Emoji_Menu_Bar)EmojiMenuBar).OpenEmoji =false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
