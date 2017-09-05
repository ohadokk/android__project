package com.example.ohad.test;

/**
 * Created by ohad on 4/8/2017.
 */

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class Y__Playlist_Player extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    public static final String API_KEY = Y__DeveloperKey.DEVELOPER_KEY;

    public  String PlayList_ID ;
    public String CurrentVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.y__playlist_player);
/** Initializing YouTube Player View **/
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);

    }
    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {

        PlayList_ID =  getIntent().getStringExtra("PlaylistId");

/** add listeners to YouTubePlayer instance **/

        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

/** Start buffering **/
        if (!wasRestored) {
            player.cuePlaylist(PlayList_ID);
        }

        player.setPlayerStateChangeListener(new PlayerStateChangeListener() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(String s) {
                CurrentVideo=s;
            }

            @Override
            public void onAdStarted() {

            }

            @Override
            public void onVideoStarted() {

            }
            //when video end the function send to the sql table an order to uptick the repetition counter
            //or to create a new row about the video
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            @Override
            public void onVideoEnded() {
                SQL__Make_SQL_Calls makeSQLCalls = new SQL__Make_SQL_Calls(new SQL__AsyncResponse() {
                    @Override
                    public void ProcessFinish(Object output) {
                        if (output != null) {

                        }
                    }
                }, new int[]{2},new SQL__YouTubeTable(1,"1",PlayList_ID,CurrentVideo,"1","4-4-2018"));
                makeSQLCalls.execute("");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


            @Override
            public void onError(ErrorReason errorReason) {

            }
        });
    }

    private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {
        }
    };

    private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
        }
        @Override
        public void onVideoStarted() {
        }
    };
}