package com.example.ohad.test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class TamaguziGui extends Fragment{

    //variables for moving the animations
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    ObjectAnimator x11,y11,x22,y22,x33,y33,x44,y44,x1,y1,x2,y2,x3,y3,x4,y4;
    AnimatorSet animSetXY11, animSetXY22, animSetXY33, animSetXY44, animSetXY1, animSetXY2, animSetXY3, animSetXY4;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    Animation move;

    ImageView imgview1,imgview2,imgview3,imgview4,imgview5;
    ImageView buttonView;

    EditText search;

    int Click=0,LongClick=1;

    View guiMainActivityView;

    //an object to process the information about the tamaguzi
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public emoji_Bars emoji;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedinstanceState) {
        guiMainActivityView = inflater.inflate(R.layout.gui_main_activity_layout, container, false);

        emoji=new emoji_Bars();

        imgview1 = (ImageView)guiMainActivityView.findViewById(R.id.SickBar);
        imgview2 = (ImageView)guiMainActivityView.findViewById(R.id.HungryBar);
        imgview3 = (ImageView)guiMainActivityView.findViewById(R.id.HappyBar);
        imgview4 = (ImageView)guiMainActivityView.findViewById(R.id.ActiveBar);

        buttonView = (ImageView) guiMainActivityView.findViewById(R.id.Bar);

        setAnimation();

        //make gif active
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Glide.with(this).load(R.drawable.plaster).into(new GlideDrawableImageViewTarget( imgview1));
        Glide.with(this).load(R.drawable.pankake).into(new GlideDrawableImageViewTarget( imgview2));
        Glide.with(this).load(R.drawable.coffe).into(new GlideDrawableImageViewTarget( imgview3));
        Glide.with(this).load(R.drawable.sleep).into(new GlideDrawableImageViewTarget( imgview4));
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //give points to the currect bar
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        imgview1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                emoji.AddSubtractPoints(2,"Sick");
            }});
        imgview2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) { emoji.AddSubtractPoints(2,"Hungry");}});
        imgview3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                emoji.AddSubtractPoints(2,"Happy");
            }});
        imgview4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {emoji.AddSubtractPoints(2,"Active");
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //activate animation
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShortClick();
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        return guiMainActivityView;
    }

    public TamaguziGui() {

        animSetXY1 = new AnimatorSet();
        animSetXY2 = new AnimatorSet();
        animSetXY3 = new AnimatorSet();
        animSetXY4 = new AnimatorSet();

        animSetXY11 = new AnimatorSet();
        animSetXY22 = new AnimatorSet();
        animSetXY33 = new AnimatorSet();
        animSetXY44 = new AnimatorSet();


    }

    void MoveImage(){
        if(Click==0){

            animSetXY1.start();
            animSetXY2.start();
            animSetXY3.start();
            animSetXY4.start();
            Click=1;

        }
        else{

            animSetXY11.start();
            animSetXY22.start();
            animSetXY33.start();
            animSetXY44.start();
            Click=0;


        }





    }

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

    void ShortClick(){
        if(Click==0 && LongClick==0)
            SearchBar();
        MoveImage();
    }

    boolean LondClick(){
        if(LongClick==1 && Click==1)
            MoveImage();
        SearchBar();
        return true;
    }


    void setAnimation(){


        x1 = ObjectAnimator.ofFloat(imgview1,"x",20);
        y1 = ObjectAnimator.ofFloat(imgview1,"y",20);

        x2 = ObjectAnimator.ofFloat(imgview2,"x",120);
        y2 = ObjectAnimator.ofFloat(imgview2,"y",20);

        x3 = ObjectAnimator.ofFloat(imgview3,"x",220);
        y3 = ObjectAnimator.ofFloat(imgview3,"y",20);

        x4 = ObjectAnimator.ofFloat(imgview4,"x",320);
        y4 = ObjectAnimator.ofFloat(imgview4,"y",20);


        animSetXY1.playTogether(x1, y1);
        animSetXY1.setDuration(900);

        animSetXY2.playTogether(x2, y2);
        animSetXY2.setDuration(900);

        animSetXY3.playTogether(x3, y3);
        animSetXY3.setDuration(900);


        animSetXY4.playTogether(x4, y4);
        animSetXY4.setDuration(900);


        x11 = ObjectAnimator.ofFloat(imgview1,"x",460);
        y11 = ObjectAnimator.ofFloat(imgview1,"y",20);

        x22 = ObjectAnimator.ofFloat(imgview2,"x",460);
        y22 = ObjectAnimator.ofFloat(imgview2,"y",20);

        x33 = ObjectAnimator.ofFloat(imgview3,"x",460);
        y33 = ObjectAnimator.ofFloat(imgview3,"y",20);

        x44 = ObjectAnimator.ofFloat(imgview4,"x",460);
        y44 = ObjectAnimator.ofFloat(imgview4,"y",20);


        animSetXY11.playTogether(x11, y11);
        animSetXY11.setDuration(900);

        animSetXY22.playTogether(x22, y22);
        animSetXY22.setDuration(900);

        animSetXY33.playTogether(x33, y33);
        animSetXY33.setDuration(900);

        animSetXY44.playTogether(x44, y44);
        animSetXY44.setDuration(900);

    }
}