package com.example.ohad.test;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

/**
 * Created by ohad on 6/15/2017.
 */

public class Emoji_Menu_Bar extends Fragment{

    //image variable to construct the figure on the menu bar
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    ImageView waving_hand1,waving_hand2,star1,star2,star3,star4,star5,star6;
    ImageView SmilyApp;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //use to make the face gif move
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    GlideDrawableImageViewTarget faceApp;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //use to double tap the face and return to to main face
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    GestureDetector DoubleDetector;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //use as a flag for the main activity to decide if this fragment activated the double tap
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public boolean OpenEmoji = false;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //use as the variable that contain the layout in this instant
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    View EmojiBarMenu;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedinstanceState) {
        //connect the variable EmojiBarMenu to the xml layout emoji_menu
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        EmojiBarMenu = inflater.inflate(R.layout.emoji_menu, container, false);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //initialize the variable DoubleDetector as the DoubleTapConfirm GestureDetector
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        DoubleDetector =new GestureDetector(getActivity(),new DoubleTapConfirm());
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //connect the variables to the layout variables
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        SmilyApp = (ImageView) EmojiBarMenu.findViewById(R.id.smily1);

        waving_hand1 = (ImageView) EmojiBarMenu.findViewById(R.id.waving_hand1);
        waving_hand2 = (ImageView) EmojiBarMenu.findViewById(R.id.waving_hand2);

        star1 = (ImageView) EmojiBarMenu.findViewById(R.id.star1);
        star2 = (ImageView) EmojiBarMenu.findViewById(R.id.star2);
        star3 = (ImageView) EmojiBarMenu.findViewById(R.id.star3);
        star4 = (ImageView) EmojiBarMenu.findViewById(R.id.star4);
        star5 = (ImageView) EmojiBarMenu.findViewById(R.id.star5);
        star6 = (ImageView) EmojiBarMenu.findViewById(R.id.star6);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //by clicking star 2 the program direct , by intent , to Y__MainActivity
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        star2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent test = new Intent(getActivity(), Y__MainActivity.class);
                startActivityForResult(test, 1);

            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //send an activation order to the robot and direct to Joy__MainActivity Activity
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        star6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TcpClient("A");
                Intent i=new Intent(getActivity(),Joy__MainActivity.class);
                startActivity(i);

            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



        //make hands and face move
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        faceApp = new GlideDrawableImageViewTarget(SmilyApp);
        Glide.with(this).load(R.drawable.smily).into(faceApp);

        GlideDrawableImageViewTarget glide_waving_hand1 = new GlideDrawableImageViewTarget(waving_hand1);
        Glide.with(this).load(R.drawable.left_hand).into(glide_waving_hand1);

        GlideDrawableImageViewTarget glide_waving_hand2 = new GlideDrawableImageViewTarget(waving_hand2);
        Glide.with(this).load(R.drawable.right_hand).into(glide_waving_hand2);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //on double tap return to main face layout from main activity
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        SmilyApp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (DoubleDetector.onTouchEvent(event)) {
                    // double tap
                    return true;
                }
                return true;
            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        return EmojiBarMenu;
    }

    //class to detect double tap and switch a flag on main activity in order to announce fragment switch
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private class DoubleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDoubleTapEvent(MotionEvent event) {
            OpenEmoji=true;
            try {
                ((MainActivity)getActivity()).SwitchFrag();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }


    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
