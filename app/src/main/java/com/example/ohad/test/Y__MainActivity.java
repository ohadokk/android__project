package com.example.ohad.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.youtube.model.Playlist;

import java.util.Arrays;
import java.util.List;

import static android.view.View.INVISIBLE;

public class Y__MainActivity extends AppCompatActivity {

    //string required for authentication
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private static final String[] SCOPES = { "https://www.googleapis.com/auth/youtube"};
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    Playlist Playlist_1=null,Playlist_2=null,Playlist_3=null,Playlist_4=null;

    //when a video end the id of the video is inserted to the variable
    //by intent
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    String Upload=null;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //use for the layout of the class
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    guiMainActivity Gui;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //get the top rated playlist
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    String[] result;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Gui =new guiMainActivity();

        //if the class is created after a video is watched to the end
        //the intent will inhabit the video id
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Upload=getIntent().getStringExtra("VIDEO_FOR_UPLOAD");
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //connect to layout
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        setContentView(R.layout.y__gui_activity_main);
        Gui.imgview1 = (ImageView)findViewById(R.id.imageView1);
        GlideDrawableImageViewTarget y__music = new GlideDrawableImageViewTarget(Gui.imgview1);
        Glide.with(this).load(R.drawable.y__music).into(y__music);
        Gui.imgview2 = (ImageView)findViewById(R.id.imageView2);
        GlideDrawableImageViewTarget y__study = new GlideDrawableImageViewTarget(Gui.imgview2);
        Glide.with(this).load(R.drawable.y__study1).into(y__study);
        Gui.imgview3 = (ImageView)findViewById(R.id.imageView3);
        GlideDrawableImageViewTarget y__sleep = new GlideDrawableImageViewTarget(Gui.imgview3);
        Glide.with(this).load(R.drawable.y__sleep).into(y__sleep);
        Gui.imgview4 = (ImageView)findViewById(R.id.imageView4);
        GlideDrawableImageViewTarget number1 = new GlideDrawableImageViewTarget(Gui.imgview4);
        Glide.with(this).load(R.drawable.number1).into(number1);
        Gui.imgview5 = (ImageView)findViewById(R.id.imageView5);
        Gui.buttonView = (ImageView) findViewById(R.id.button1);
        Gui.search =(EditText) findViewById(R.id.searchBar);
        Gui.search.setVisibility(INVISIBLE);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //initialize animation
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Gui.setAnimation();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //on button click the function GetSetPlaylist is getting all
        // playlist's from youtube channel
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Gui.buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetSetPlaylist("we dont use that");
                Gui.ShortClick();
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //on click animation change
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Gui.buttonView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return Gui.LondClick();
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //set text
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Gui.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gui.search.setText(null);
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //when click finish on the keyboard the function direct to Y__SearchActivity
        //by intent and send the search word with it
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Gui.search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Intent j=new Intent(Y__MainActivity.this,Y__SearchActivity.class);
                    j.putExtra("search", Gui.search.getText().toString());
                    startActivity(j);
                }
                return false;
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //if the intent from the video came back with the id of the video
        //it is directed to the function uploadVideo
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        if(Upload!=null){
            uploadVideo(Upload);
        }
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //direct to the correct playlist
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Gui.imgview1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (Playlist_1!=null) {
                    final Intent  i = new Intent(getApplicationContext(),Y__Playlist_Player.class) ;
                    i .putExtra("PlaylistId",Playlist_1.getId().toString());
                    startActivity(i);
                }
            }});
        Gui.imgview2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (Playlist_2!=null) {
                    final Intent  i = new Intent(getApplicationContext(),Y__Playlist_Player.class) ;
                    i .putExtra("PlaylistId",Playlist_2.getId().toString());
                    startActivity(i);
                }
            }});
        Gui.imgview3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (Playlist_3!=null) {
                    final Intent  i = new Intent(getApplicationContext(),Y__Playlist_Player.class) ;
                    i .putExtra("PlaylistId",Playlist_3.getId().toString());
                    startActivity(i);
                }
            }});
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //get the top rated palylist from the sql table
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Gui.imgview4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                SQL__Make_SQL_Calls makeSQLCalls = new SQL__Make_SQL_Calls(new SQL__AsyncResponse() {
                    @Override
                    public void ProcessFinish(Object output) {
                        if (output != null) {
                            result = ((SQL__YouTubeTable) output).TopRated(3);

                            final Intent  i = new Intent(getApplicationContext(),Y__Playlist_Player.class) ;
                            i .putExtra("PlaylistId",result[2].toString());
                            startActivity(i);

                        }
                    }
                }, new int[]{0,1},new SQL__YouTubeTable());
                makeSQLCalls.execute("");
            }});
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++




    }

    //upload videos using Y__PlaylistUpdates Class
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void uploadVideo(String Upload) {

        //get access to write to the playlist
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        GoogleAccountCredential mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());
        mCredential.setSelectedAccountName("ohadokk@gmail.com");
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        Y__PlaylistUpdates PU;
        PU = new Y__PlaylistUpdates(2, Upload, "PL-A3xBsJKHzGJl1t2X7SI2-ezkF6XSnqJ", mCredential, getApplicationContext(), new Y__AsyncResponse() {

            @Override
            public void ProcessFinish(List<Playlist> output) {

            }

            @Override
            public void ProcessFinish(Object output) {
                Context context = getApplicationContext();
               //test for success upload
                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            }

        });
        PU.execute();
    }
    public void layout( List<Playlist> layout) {

        Playlist_1=layout.get(2);
        Playlist_2=layout.get(1);
        Playlist_3=layout.get(0);
        Playlist_4=layout.get(3);



    }
    public void GetSetPlaylist(String PlaylistName) {
        //get access to write to the playlist
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        GoogleAccountCredential mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());
        mCredential.setSelectedAccountName("ohadokk@gmail.com");
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        Y__get_playlist getplaylist;

        getplaylist=new Y__get_playlist("GET",PlaylistName ,mCredential, getApplicationContext(), new Y__AsyncResponse() {

            @Override
            public void ProcessFinish( List<Playlist> output) {
                layout(output);

            }

            @Override
            public void ProcessFinish(Object output) {

            }

        });
        getplaylist.execute();

    }


}

