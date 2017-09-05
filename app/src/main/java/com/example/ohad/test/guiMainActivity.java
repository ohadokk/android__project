package com.example.ohad.test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;

public class guiMainActivity {

    //variables for the animation movment
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    ObjectAnimator x11,y11,x22,y22,x33,y33,x44,y44,x55,y55,x1,y1,x2,y2,x3,y3,x4,y4,x5,y5;
    AnimatorSet animSetXY11, animSetXY22, animSetXY33, animSetXY44, animSetXY55,animSetXY1, animSetXY2, animSetXY3, animSetXY4, animSetXY5;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    Animation move;

    //the object that moves
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    ImageView imgview1,imgview2,imgview3,imgview4,imgview5;
    ImageView buttonView;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //search bar
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    EditText search;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //indicators for long click and short click
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    int Click=0,LongClick=1;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    public guiMainActivity () {

        animSetXY1 = new AnimatorSet();
        animSetXY2 = new AnimatorSet();
        animSetXY3 = new AnimatorSet();
        animSetXY4 = new AnimatorSet();
        animSetXY5 = new AnimatorSet();
        animSetXY11 = new AnimatorSet();
        animSetXY22 = new AnimatorSet();
        animSetXY33 = new AnimatorSet();
        animSetXY44 = new AnimatorSet();
        animSetXY55 = new AnimatorSet();

    }

    //function to move the objects corresponded to the long and short click
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void MoveImage(){
        if(Click==0){

            animSetXY1.start();
            animSetXY2.start();
            animSetXY3.start();
            animSetXY4.start();
            animSetXY5.start();
            Click=1;

        }
        else{

            animSetXY11.start();
            animSetXY22.start();
            animSetXY33.start();
            animSetXY44.start();
            animSetXY55.start();
            Click=0;


        }


    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //function for dealing with visibility of options (playlist/search edit)
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void SearchBar(){
        if(LongClick==1){
            search.setVisibility(View.VISIBLE);
            LongClick = 0;

        }
        else
        {
            search.setVisibility(View.INVISIBLE);
            LongClick = 1;
        }
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //impalement the short click for outer class use
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void ShortClick(){
        if(Click==0 && LongClick==0)
            SearchBar();
        MoveImage();
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //impalement the long click for outer class use
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    boolean LondClick(){
        if(LongClick==1 && Click==1)
            MoveImage();
        SearchBar();
        return true;
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //function to give instructions to the objects where to move
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void setAnimation(){


        x1 = ObjectAnimator.ofFloat(imgview1,"x",218);
        y1 = ObjectAnimator.ofFloat(imgview1,"y",158);

        x2 = ObjectAnimator.ofFloat(imgview2,"x",354);
        y2 = ObjectAnimator.ofFloat(imgview2,"y",264);

        x3 = ObjectAnimator.ofFloat(imgview3,"x",304);
        y3 = ObjectAnimator.ofFloat(imgview3,"y",464);

        x4 = ObjectAnimator.ofFloat(imgview4,"x",134);
        y4 = ObjectAnimator.ofFloat(imgview4,"y",464);

        x5 = ObjectAnimator.ofFloat(imgview5,"x",84);
        y5 = ObjectAnimator.ofFloat(imgview5,"y",264);

        animSetXY1.playTogether(x1, y1);
        animSetXY1.setDuration(900);

        animSetXY2.playTogether(x2, y2);
        animSetXY2.setDuration(900);

        animSetXY3.playTogether(x3, y3);
        animSetXY3.setDuration(900);


        animSetXY4.playTogether(x4, y4);
        animSetXY4.setDuration(900);


        animSetXY5.playTogether(x5, y5);
        animSetXY5.setDuration(900);

        x11 = ObjectAnimator.ofFloat(imgview1,"x",218);
        y11 = ObjectAnimator.ofFloat(imgview1,"y",343);

        x22 = ObjectAnimator.ofFloat(imgview2,"x",218);
        y22 = ObjectAnimator.ofFloat(imgview2,"y",343);

        x33 = ObjectAnimator.ofFloat(imgview3,"x",218);
        y33 = ObjectAnimator.ofFloat(imgview3,"y",343);

        x44 = ObjectAnimator.ofFloat(imgview4,"x",218);
        y44 = ObjectAnimator.ofFloat(imgview4,"y",343);

        x55 = ObjectAnimator.ofFloat(imgview5,"x",218);
        y55 = ObjectAnimator.ofFloat(imgview5,"y",343);

        animSetXY11.playTogether(x11, y11);
        animSetXY11.setDuration(900);

        animSetXY22.playTogether(x22, y22);
        animSetXY22.setDuration(900);

        animSetXY33.playTogether(x33, y33);
        animSetXY33.setDuration(900);

        animSetXY44.playTogether(x44, y44);
        animSetXY44.setDuration(900);

        animSetXY55.playTogether(x55, y55);
        animSetXY55.setDuration(900);
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}