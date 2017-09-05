package com.example.ohad.test;/*
package com.example.ohad.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

*
 * Created by ohad on 5/15/2017.



public class SQL__AddProducts extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
 String test;
    TextView ViewTest0,ViewTest1,ViewTest2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        ViewTest0 = (TextView)findViewById(R.id.textView0);
        ViewTest1 = (TextView)findViewById(R.id.textView1);
        ViewTest2 = (TextView)findViewById(R.id.textView2);
        test = getIntent().getExtras().getString("r0");
        ViewTest0.setText(test);
        test = getIntent().getExtras().getString("r1");
        ViewTest1.setText(test);
        test = getIntent().getExtras().getString("r2");
        ViewTest2.setText(test);
    }

    private YouTubePlayerView playerView1,playerView2,playerView3;

    Button first,second,third;
    String result;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.layout);


        first=(Button)findViewById(R.id.button);
        second=(Button)findViewById(R.id.button2);
        third=(Button)findViewById(R.id.button3);
        result=null;
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result="r3";
                playerView1=(YouTubePlayerView)findViewById(R.id.player_view);
                playerView1.initialize("AIzaSyAqCOeOjixbq8CUHYmvefmFuSyiCDZdbBc", SQL__AddProducts.this);//api key
            }});
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result="r2";
                playerView2=(YouTubePlayerView)findViewById(R.id.player_view);
                playerView2.initialize("AIzaSyAqCOeOjixbq8CUHYmvefmFuSyiCDZdbBc", SQL__AddProducts.this);//api key
            }});
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result="r1";
                playerView3=(YouTubePlayerView)findViewById(R.id.player_view);
                playerView3.initialize("AIzaSyAqCOeOjixbq8CUHYmvefmFuSyiCDZdbBc", SQL__AddProducts.this);//api key
            }});


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult result) {
        Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean restored) {
        if(!restored){

            player.cuePlaylist(getIntent().getStringExtra(result));
            player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                @Override
                public void onLoading() {

                }

                @Override
                public void onLoaded(String s) {

                }

                @Override
                public void onAdStarted() {

                }

                @Override
                public void onVideoStarted() {

                }

                @Override
                public void onVideoEnded() {

                }



                @Override
                public void onError(YouTubePlayer.ErrorReason errorReason) {

                }
            });

        }
    }
}

*/
