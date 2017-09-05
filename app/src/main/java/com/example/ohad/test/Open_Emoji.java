package com.example.ohad.test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.github.tbouron.shakedetector.library.ShakeDetector;

import java.util.Timer;
import java.util.TimerTask;


public class Open_Emoji extends Fragment {

    int change=0;

    Timer T1,T2;

    ImageView SmilyRobot;
    GlideDrawableImageViewTarget faceRobot;


    ShakeDetector shake,swing;
    ScaleGestureDetector scaleDetector;
    GestureDetector gestureDetector;

    public boolean MenuFragment=false , TamaActivity=false;

    View Open_Emoji_View;



    int FearLove=0;
    int HartWell=0;

    int SackFlag=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedinstanceState) {

        Open_Emoji_View = inflater.inflate(R.layout.open_emoji_layout, container, false);

        SmilyRobot=(ImageView)Open_Emoji_View.findViewById(R.id.smily);
        faceRobot = new GlideDrawableImageViewTarget(SmilyRobot);
        Glide.with(this).load(R.drawable.smily).into(faceRobot);

        gestureDetector = new GestureDetector(getActivity(), new SingleTapConfirm());
        scaleDetector = new ScaleGestureDetector(getActivity(),  new ScaleListener());

        T1=new Timer();
        T2=new Timer();


        //create detector for shakes and raze a flag of shack for function Shaker to deal with shake
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        shake.create(getActivity(), new ShakeDetector.OnShakeListener() {
            @Override
            public void OnShake() {
                if(SackFlag==0)
                    SackFlag=2;
                Shaker();
            }


        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //make shake object sensitive to the degree of 5 and active with 2 shakes
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        shake.updateConfiguration(5, 2);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //create detector for swing and raze a flag of swing for function Shaker to deal with swing
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        swing.create(getActivity(), new ShakeDetector.OnShakeListener() {
            @Override
            public void OnShake() {
                if(SackFlag==0)
                    SackFlag=1;
                Shaker();}
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //make swing object sensitive to the degree of 0.5 and active with 6 shakes
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        swing.updateConfiguration(0.05f, 6);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //attach gestureDetector and scaleDetector on the face
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        SmilyRobot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (gestureDetector.onTouchEvent(event)) {
                    // single tap
                    return true;
                }
                if (scaleDetector.onTouchEvent(event)) {
                    // single tap
                    return true;
                }
                return true;
            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        return Open_Emoji_View;
    }

    //function that determine ,by flags, if a shake or a swing has been detected
    //and increase or decrease the variables for the right emotions
    //furthermore give a timer a task to change the face for 5 sec
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public  void Shaker(){

        if (SackFlag==1) {
            try {
                change=5;
                smilyChanger();
                FearLove++;
                T1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                if(change != 0) {
                                    if(FearLove>=2)
                                        change=6;
                                    else
                                        change=0;
                                    smilyChanger();
                                    SackFlag=0;
                                }
                            }
                        });
                    }
                }, 5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (SackFlag==2) {
            try {

                change=4;
                smilyChanger();
                FearLove--;
                T1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                if(change != 0) {
                                    if(FearLove<=-2)
                                        change=7;
                                    else
                                        change=0;
                                    smilyChanger();
                                    SackFlag=0;
                                }
                            }
                        });
                    }
                }, 5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //class for detecting scale change
    //go to the correct next activity on minimum or maximum scale
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private float mScaleFactor = 1.f;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(0.7f, Math.min(mScaleFactor, 1.5f));

            SmilyRobot.setScaleX(mScaleFactor);
            SmilyRobot.setScaleY(mScaleFactor);

            if(mScaleFactor==1.5f) {
                TamaActivity = true;
                try {
                    ((MainActivity)getActivity()).SwitchFrag();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(mScaleFactor==0.7f) {
                MenuFragment = true;
                try {
                    ((MainActivity)getActivity()).SwitchFrag();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            return true;
        }
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //class for detecting simple gestures
    // on every detection an emotion variable is increase or decrease
    // a timer is been schedule for 5 sec to show emotions
    // and the change variable is change for smilyChanger function to deal with the
    // face image change
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            change=2;
            smilyChanger();
            HartWell++;
            T1.schedule(new TimerTask() {
                @Override
                public void run() {
                    getActivity().runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            if(change != 0) {
                                if(HartWell>=2)
                                    change=9;
                                else
                                    change=0;
                                smilyChanger();
                            }
                        }
                    });
                }
            }, 5000);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent event) {
            change=1;
            smilyChanger();
            HartWell--;
            T2.schedule(new TimerTask() {
                @Override
                public void run() {
                    getActivity().runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            if(change != 0) {
                                if(HartWell<=-2)
                                    change=8;
                                else
                                    change=0;
                                smilyChanger();
                            }
                        }
                    });
                }
            }, 5000);

        }
        @Override
        public boolean  onFling(MotionEvent event, MotionEvent e2, float velocityX, float velocityY){
            change=3;
            smilyChanger();
            FearLove++;
            T2.schedule(new TimerTask() {
                @Override
                public void run() {
                    getActivity().runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            if(change != 0) {
                                if(FearLove>=2)
                                    change=6;
                                else
                                    change=0;
                                smilyChanger();



                            }
                        }
                    });
                }
            }, 5000);
            return true;
        }

    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //change the image of the face to the correct emotion
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void smilyChanger (){

        switch (change){
            case 0:
                Glide.with(this).load(R.drawable.smily).into(faceRobot);
                change=1;
                break;
            case 1:
                Glide.with(this).load(R.drawable.moving).into(faceRobot);
                break;
            case 2:
                Glide.with(this).load(R.drawable.scream).into(faceRobot);
                break;
            case 3:
                Glide.with(this).load(R.drawable.loving).into(faceRobot);
                break;
            case 4:
                Glide.with(this).load(R.drawable.shaked).into(faceRobot);
                break;
            case 5:
                Glide.with(this).load(R.drawable.swing).into(faceRobot);
                break;
            case 6:
                Glide.with(this).load(R.drawable.exlove).into(faceRobot);
                break;
            case 7:
                Glide.with(this).load(R.drawable.exfear).into(faceRobot);
                break;
            case 8:
                Glide.with(this).load(R.drawable.exwell).into(faceRobot);
                break;
            case 9:
                Glide.with(this).load(R.drawable.exhart).into(faceRobot);
                break;
        }

    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}



