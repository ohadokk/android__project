package com.example.ohad.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ohad on 5/15/2017.
 */

public class Activity_Tamaguzi extends Activity {

    GlideDrawableImageViewTarget show_test;

    Timer T;

    EditText Sick,  Hungry , Happy , Active ;

    ImageView tamaguzi;
    ImageView SickP,HungryP,HappyP,ActiveP;

    TamaguziGui GuiBar;

    GestureDetector DoubleDetector;

    String CheckForFaceChange;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gui_activity_main);

        GuiBar = (TamaguziGui) getFragmentManager().findFragmentById(R.id.guiMainActivityLayout);

        //timer for receiving information about bars
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        T = new Timer();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //variables for the information about bars parentage
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Sick    = (EditText) findViewById(R.id.Sick)   ;
        SickP   = (ImageView) findViewById(R.id.SickP)  ;
        Hungry  = (EditText) findViewById(R.id.Hungry) ;
        HungryP = (ImageView) findViewById(R.id.HungryP);
        Happy   = (EditText) findViewById(R.id.Happy)  ;
        HappyP  = (ImageView) findViewById(R.id.HappyP) ;
        Active  = (EditText) findViewById(R.id.Active) ;
        ActiveP = (ImageView) findViewById(R.id.ActiveP);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //attach the gif on the layout and activate the animation
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        tamaguzi = (ImageView) findViewById(R.id.test_love);
        show_test = new GlideDrawableImageViewTarget(tamaguzi);
        Glide.with(this).load(R.drawable.happy4).into(show_test);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //variable for gif change
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        CheckForFaceChange="happy4";
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //detector of double tap on screen
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        DoubleDetector =new GestureDetector(this,new DoubleTapConfirm());
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //attach DoubleDetector on the gif
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        tamaguzi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (DoubleDetector.onTouchEvent(event)) {
                    // double tap
                    return true;
                }
                return true;
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //inishilize the timer
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        T.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      MyTimeing();
                                  }
                });
            }
        },0, 500);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }

    //insert information of bars to there variable
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void MyTimeing(){
            Sick.setText("Sick "+Integer.toString(GuiBar.emoji.Sick)+"%");
             BarSwitch(GuiBar.emoji.SickP,SickP);
            Hungry.setText("Hungry "+Integer.toString(GuiBar.emoji.Hungry)+"%");
            BarSwitch(GuiBar.emoji.HungryP,HungryP);
            Happy.setText("Happy "+Integer.toString(GuiBar.emoji.Happy)+"%");
            BarSwitch(GuiBar.emoji.HappyP,HappyP);
            Active.setText("Active "+Integer.toString(GuiBar.emoji.Active)+"%");
            BarSwitch(GuiBar.emoji.ActiveP,ActiveP);

            if(!CheckForFaceChange.equals(GuiBar.emoji.CurrentEmoji.toString())) {
                CheckForFaceChange = GuiBar.emoji.CurrentEmoji.toString();
                FaceSwitch();
            }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //change bar image
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     synchronized void BarSwitch(int BarP , ImageView BarI ){
        switch(BarP){
            case 5:
                BarI.setImageResource(R.drawable.bar6);
                break;
            case 4:
                BarI.setImageResource(R.drawable.bar5);
                break;
            case 3:
                BarI.setImageResource(R.drawable.bar4);
                break;
            case 2:
                BarI.setImageResource(R.drawable.bar3);
                break;
            case 1:
                BarI.setImageResource(R.drawable.bar2);
                break;
            case 0:
                BarI.setImageResource(R.drawable.bar1);
                break;

        }


    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //change face image
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void FaceSwitch(){
        if(CheckForFaceChange.equals("Sick3")){
            Glide.with(this).load(R.drawable.sick3).into(show_test);
        }
        else if(CheckForFaceChange.equals("Sick2")){
            Glide.with(this).load(R.drawable.sick2).into(show_test);
        }
        else if(CheckForFaceChange.equals("Sick1")){
            Glide.with(this).load(R.drawable.sick1).into(show_test);
        }
        else if(CheckForFaceChange.equals("Hungry3")){
            Glide.with(this).load(R.drawable.hungry3).into(show_test);
        }
        else if(CheckForFaceChange.equals("Hungry2")){
            Glide.with(this).load(R.drawable.hungry2).into(show_test);
        }
        else if(CheckForFaceChange.equals("Hungry1")){
            Glide.with(this).load(R.drawable.hungry1).into(show_test);
        }
        else if(CheckForFaceChange.equals("Happy3")){
            Glide.with(this).load(R.drawable.happy3).into(show_test);
        }
        else if(CheckForFaceChange.equals("Happy2")){
            Glide.with(this).load(R.drawable.happy2).into(show_test);
        }
        else if(CheckForFaceChange.equals("Happy1")){
            Glide.with(this).load(R.drawable.happy1).into(show_test);
        }
        else if(CheckForFaceChange.equals("Active3")){
            Glide.with(this).load(R.drawable.active3).into(show_test);
        }
        else if(CheckForFaceChange.equals("Active2")){
            Glide.with(this).load(R.drawable.active2).into(show_test);
        }
        else if(CheckForFaceChange.equals("Active1")){
            Glide.with(this).load(R.drawable.active1).into(show_test);
        }
        else
            Glide.with(this).load(R.drawable.happy4).into(show_test);

    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //implement double tap detector (go back to main face)
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private class DoubleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDoubleTapEvent(MotionEvent event) {
           getFragmentManager().beginTransaction().remove(GuiBar).commit();
            Intent i = new Intent(Activity_Tamaguzi.this, MainActivity.class);

            startActivity(i);
            finish();
            return true;
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

}
