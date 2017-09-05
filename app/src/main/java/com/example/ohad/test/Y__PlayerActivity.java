package com.example.ohad.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Y__PlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView playerView;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.y__activity_player);

        playerView = (YouTubePlayerView)findViewById(R.id.player_view);
        playerView.initialize(Y__YoutubeHandler.KEY, this);

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

            //get the video id to play on the player
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            player.cueVideo(getIntent().getStringExtra("VIDEO_ID"));
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            //use for video end
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
                //if video is finish the function send the video id to Y__MainActivity by intent
                //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                @Override
                public void onVideoEnded() {
                    Intent j=new Intent(Y__PlayerActivity.this, Y__MainActivity.class);
                    j.putExtra("VIDEO_FOR_UPLOAD",getIntent().getStringExtra("VIDEO_ID"));
                    startActivity(j);


                }
                //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


                @Override
                public void onError(YouTubePlayer.ErrorReason errorReason) {

                }
            });
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        }
    }
}