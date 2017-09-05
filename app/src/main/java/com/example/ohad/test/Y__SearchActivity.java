package com.example.ohad.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class Y__SearchActivity extends Activity {

    private TextView searchInput;
    private ListView videosFound;

    private Handler handler;

    Y__VideoItem searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.y__activity_search);

        searchInput = (TextView) findViewById(R.id.search_input);
        videosFound = (ListView)findViewById(R.id.videos_found);

        handler = new Handler();

        //when the activity is created from Y_MainActivity
        //the search word is taken from the intent and search
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        searchOnYoutube(getIntent().getStringExtra("search"));
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //if a new word is entered and the user click finish on the keyboard
        // a new search start by this function
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    searchOnYoutube(v.getText().toString());
                    return false;
                }

                return true;
            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }

    //list to contain the results
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private List<Y__VideoItem> searchResults;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //use Y__YoutubeHandler to search the search word
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void searchOnYoutube(final String keywords){
        new Thread(){
            public void run(){
                Y__YoutubeHandler yc = new Y__YoutubeHandler(Y__SearchActivity.this);
                searchResults = yc.search(keywords);
                handler.post(new Runnable(){
                    public void run(){
                        updateVideosFound();
                    }
                });
            }
        }.start();
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //attach the results found to the layout
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void updateVideosFound(){
        ArrayAdapter<Y__VideoItem> adapter = new ArrayAdapter<Y__VideoItem>(getApplicationContext(), R.layout.y__video_item, searchResults){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.y__video_item, parent, false);
                }
                ImageView thumbnail = (ImageView)convertView.findViewById(R.id.video_thumbnail);
                TextView title = (TextView)convertView.findViewById(R.id.video_title);
                TextView description = (TextView)convertView.findViewById(R.id.video_description);

                 searchResult = searchResults.get(position);

                Picasso.with(getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
                title.setText(searchResult.getTitle());
                description.setText(searchResult.getDescription());
                return convertView;
            }
        };

        videosFound.setAdapter(adapter);
        addClickListener();
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //attach listener to the results seen in the layout
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void addClickListener(){
        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,long id) {
                final Intent intent = new Intent(getApplicationContext(), Y__PlayerActivity.class);

                intent.putExtra("VIDEO_ID", searchResults.get(pos).getId());
                startActivity(intent);

            }

        });
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}