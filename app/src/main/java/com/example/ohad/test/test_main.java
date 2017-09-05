package com.example.ohad.test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.concurrent.ExecutionException;

/**
 * Created by ohad on 6/15/2017.
 */
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//class for the station phone to switch between black screen and driver face
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
public class test_main extends Activity {

    GlideDrawableImageViewTarget faceRobot;
    ImageView SmilyRobot;
    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);
        Change();

    }
    void Change(){
            connect();
            if (msg.equals("A")) {
                SmilyRobot=(ImageView)findViewById(R.id.Driver);
                faceRobot = new GlideDrawableImageViewTarget(SmilyRobot);
                Glide.with(this).load(R.drawable.exwell).into(faceRobot);

             }
           if(msg.equals("B")){
                SmilyRobot.setVisibility(View.INVISIBLE);
           }
    }

    void connect(){
        try {
            msg=new ServerListener().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
