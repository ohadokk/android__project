package com.example.ohad.test;

import android.content.Context;
import android.util.Log;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Y__YoutubeHandler {

    private YouTube youtube;
    private YouTube.Search.List query;

    // the developer key
    public static final String KEY
            = Y__DeveloperKey.DEVELOPER_KEY;

    public Y__YoutubeHandler(Context context) {

        //establish connection to youtube
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        youtube = new YouTube.Builder(new NetHttpTransport(),
                new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest hr) throws IOException {}
        }).setApplicationName(context.getString(R.string.app_name)).build();
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        try{
            query = youtube.search().list("id,snippet");
            query.setKey(KEY);
            query.setType("video");
            query.setFields("items(id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)");
        }catch(IOException e){
            Log.d("YC", "Could not initialize: "+e);
        }
    }


    public List<Y__VideoItem> search(String keywords){
        query.setQ(keywords);
        try{
            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();

            //create list of Y__VideoItem and attach the results
            //to send to the layout for presentation
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            List<Y__VideoItem> items = new ArrayList<Y__VideoItem>();
            for(SearchResult result:results){
                Y__VideoItem item = new Y__VideoItem();
                item.setTitle(result.getSnippet().getTitle());
                item.setDescription(result.getSnippet().getDescription());
                item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setId(result.getId().getVideoId());
                items.add(item);
            }
            return items;
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        }catch(IOException e){
            Log.d("YC", "Could not search: "+e);
            return null;
        }
    }}