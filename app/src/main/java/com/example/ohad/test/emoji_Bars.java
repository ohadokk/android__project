package com.example.ohad.test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ohad on 5/26/2017.
 */

public class emoji_Bars {

    //variables of bars percentage and bounds
    //++++++++++++++++++++++++++++++++++++++++++++++
    int Sick, SickP;
    int Hungry, HungryP;
    int Happy, HappyP;
    int Active, ActiveP;
    //++++++++++++++++++++++++++++++++++++++++++++++

    //timers to lower parsentege points
    //++++++++++++++++++++++++++++++++++++++++++++++
    Timer Tsick, Thungry, Thappy, Tactive,T;
    //++++++++++++++++++++++++++++++++++++++++++++++

    //indicator for the current gif on screen
    //++++++++++++++++++++++++++++++++++++++++++++++
    String CurrentEmoji;
    //++++++++++++++++++++++++++++++++++++++++++++++

    public void setSick(int sick) {
        Sick = sick;
    }

    public void setSickP(int sickP) {
        SickP = sickP;
    }

    public void setHungry(int hungry) {
        Hungry = hungry;
    }

    public void setHungryP(int hungryP) {
        HungryP = hungryP;
    }

    public void setHappy(int happy) {
        Happy = happy;
    }

    public void setHappyP(int happyP) {
        HappyP = happyP;
    }

    public void setActive(int active) {
        Active = active;
    }

    public void setActiveP(int activeP) {
        ActiveP = activeP;
    }

    public void setCurrentEmoji(String currentEmoji) {
        CurrentEmoji = currentEmoji;
    }



    emoji_Bars(){

        setSick(100);
        setSickP(5);
        setHungry(100);
        setHungryP(5);
        setHappy(100);
        setHappyP(5);
        setActive(100);
        setActiveP(5);
        setCurrentEmoji("happy");


        Tsick = new Timer();
        Thungry = new Timer();
        Thappy = new Timer();
        Tactive = new Timer();

        //initialize clock in a different periods and delays
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Tsick.schedule(new TimerTask() {
            @Override
            public void run() {
                AddSubtractPoints(-2, "Sick");
            }
        }, 5000, 4100);
        Thungry.schedule(new TimerTask() {
            @Override
            public void run() {
                AddSubtractPoints(-2, "Hungry");
            }
        }, 5000, 3400);
        Thappy.schedule(new TimerTask() {
            @Override
            public void run() {
                AddSubtractPoints(-2, "Happy");
            }
        }, 5000, 2700);
        Tactive.schedule(new TimerTask() {
            @Override
            public void run() {
                AddSubtractPoints(-2, "Active");
            }
        }, 5000, 2000);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    }

    //function to determent 5 levels of parentage down rate
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    int BarPercentage(int Percentage) {
        if (Percentage >= 0 && Percentage < 20) {
            return 0;
        }
        if (Percentage >= 0 && Percentage < 40) {
            return 1;
        }
        if (Percentage >= 40 && Percentage < 60) {
            return 2;
        }
        if (Percentage >= 60 && Percentage < 80) {
            return 3;
        }
        if (Percentage >= 80 && Percentage <= 90) {
            return 4;
        }
        if (Percentage >= 90 && Percentage <= 100) {
            return 5;
        }
        return 5;
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //function to determent if the face needs to be switched
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void FaceSwitch(int Percentage, String Bar) {

        if (!CurrentEmoji.equals(Bar + Integer.toString(Percentage))) {
            CurrentEmoji = Bar + Integer.toString(Percentage);
        }

    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //function to switch a face (use BarChoose function)
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void ChooseFace() {
       String Face = BarChoose();
        if(Face.equals("Sick")){
            FaceSwitch(SickP, "Sick");
        }
        if(Face.equals("Hungry")){
            FaceSwitch(HungryP, "Hungry");
        }
        if(Face.equals("Happy")){
            FaceSwitch(HappyP, "Happy");
        }
        if(Face.equals("Active")){
            FaceSwitch(ActiveP, "Active");
        }
        if(Face.equals("Great")){
            FaceSwitch(4, "happy");
        }

    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //function to determent witch face should appear on screen
    //by hierarchy from the most important (Sick) to list (Active)
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    String BarChoose() {
        if (SickP < HungryP && SickP < HappyP && SickP < ActiveP) {
            return "Sick";
        } else if (HungryP < HappyP && HungryP < ActiveP && HungryP < SickP) {
            return "Hungry";
        } else if (HappyP < ActiveP && HappyP < HungryP && HappyP < SickP) {
            return "Happy";
        } else if (ActiveP < HappyP && ActiveP < HungryP && ActiveP < SickP) {
            return "Active";
        }
        else if ((SickP == HungryP || SickP == HappyP || SickP == ActiveP) &&
            (!(SickP > HungryP) && !(SickP > HappyP) && !(SickP > ActiveP)) && SickP != 4 ) {
            return "Sick";}
        else if ((HungryP == SickP || HungryP == HappyP || HungryP == ActiveP) &&
                (!(HungryP > SickP) && !(HungryP > HappyP) && !(HungryP > ActiveP)) && HungryP != 4 ) {
            return "Hungry";}
        else if ((HappyP == SickP || HappyP == HungryP || HappyP == ActiveP) &&
                (!(HappyP > SickP) && !(HappyP > HungryP) && !(HappyP > ActiveP)) && HappyP != 4 ) {
            return "Happy";}
        else if ((ActiveP == SickP || ActiveP == HungryP || ActiveP == HappyP) &&
                (!(ActiveP > SickP) && !(ActiveP > HungryP) && !(ActiveP > HappyP)) && ActiveP != 4 ) {
            return "Active";}
        else return "Great";
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //function to add and subtract points
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void AddSubtractPoints(int Points, String Bar) {
        int temp;
        if (Bar == "Sick") {
            temp = Sick + Points;
            if (temp <= 100 && temp >= 0){
                Sick = temp;
                SickP = BarPercentage(Sick);
                ChooseFace ();}
        }
        if (Bar == "Hungry") {
            temp = Hungry + Points;
            if (temp <= 100 && temp >= 0){
                Hungry = temp;
                HungryP = BarPercentage(Hungry);
                ChooseFace ();}
        }
        if (Bar == "Happy") {
            temp = Happy + Points;
            if (temp <= 100 && temp >= 0){
                Happy = temp;
                HappyP = BarPercentage(Happy);
                ChooseFace ();}
        }
        if (Bar == "Active") {
            temp = Active + Points;
            if (temp <= 100 && temp >= 0){
                Active = temp;
                ActiveP = BarPercentage(Active);
                ChooseFace ();}
        }

    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

}

