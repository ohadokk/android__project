package com.example.ohad.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.erz.joysticklibrary.JoyStick;

import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;

public class Joy__MainActivity extends AppCompatActivity implements JoyStick.JoyStickListener {
    public static final int UDP_SERVER_PORT = 7;
    private Joy__GameView joyGameView;
    TextView Txt ;
    int f=0;
    com.example.ohad.test.Joy__udp Joy__udp;
    int tmpPower,tmpAngle;
    String TXTAngle="start";
    Button write,read;
    EditText fileName;
    int Rflag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joy_activity_main);
        joyGameView = (Joy__GameView) findViewById(R.id.game);
        JoyStick joy1 = (JoyStick) findViewById(R.id.joy1);
        write=(Button) findViewById(R.id.write);
        read=(Button) findViewById(R.id.read);
        fileName=(EditText)findViewById(R.id.filename);
        joy1.setListener(this);
        joy1.setPadColor(Color.parseColor("#55ffffff"));
        joy1.setButtonColor(Color.parseColor("#55ff0000"));
         Txt = (TextView) findViewById(R.id.txt);
        JoyStick joy2 = (JoyStick) findViewById(R.id.joy2);
        joy2.setListener(this);
        joy2.setPadBackground(R.drawable.pad);
        joy2.setButtonDrawable(R.drawable.button);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Rflag==0) {
                    Rflag=1;
                    Joy__udp = new Joy__udp("m",1);
                    Joy__udp.start();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Joy__udp = new Joy__udp(fileName.getText().toString(),1);
                    Joy__udp.start();
                } else {
                    Rflag=0;
                    Joy__udp = new Joy__udp("e",1);
                    Joy__udp.start();
                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Joy__udp = new Joy__udp("q",1);
                Joy__udp.start();
                for(int i=0;i<1000;i++){}
                Joy__udp = new Joy__udp(fileName.getText().toString(),1);
                Joy__udp.start();
            }
        });
    }
   /* @Override
    public void onBackPressed() {
        new TcpClient("B");
    }*/
    @Override
    public void onMove(JoyStick joyStick, double angle, double power, int direction) {
        switch (joyStick.getId()) {
            case R.id.joy1:
                Txt.setText("");
                joyGameView.move(angle, power);

                power(power,angle);
                if(f==0) {
                    f=1;
                    Joy__udp = new Joy__udp(TXTAngle,tmpPower/10);
                    Joy__udp.start();

                }
                break;
            case R.id.joy2:

                angle(angle*10);
                if(f==0) {
                    f=1;
                    Joy__udp = new Joy__udp(TXTAngle,tmpAngle);
                    Joy__udp.start();

                }
                joyGameView.rotate(angle);

                break;
        }
    }

    @Override
    public void onTap() {}

    @Override
    public void onDoubleTap() {}


public void  power(Double pow,Double angle){


        if((tmpPower + 30) < pow.intValue() || (tmpPower-30)>pow.intValue()) {
            tmpPower = pow.intValue();
            f=0;
        }
        if(abs(angle) > 2)
            TXTAngle="b";
        else
            TXTAngle="f";

        Txt.setText(TXTAngle+":"+tmpPower);

}
public void angle(Double ang){



        if(ang.intValue()<0 && !TXTAngle.equals("l")) {
            TXTAngle = "l";
            tmpAngle =2;
            f=0;
        }
        if(ang.intValue()>0 && !TXTAngle.equals("r")) {
            TXTAngle = "r";
            tmpAngle =2;
            f=0;
        }
        if(ang.intValue()==0 ){
            tmpAngle =0;
            TXTAngle = "n";
            f=0;
        }
        Txt.setText(TXTAngle+":"+tmpAngle);



}

}