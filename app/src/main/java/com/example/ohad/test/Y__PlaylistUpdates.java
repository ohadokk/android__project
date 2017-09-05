package com.example.ohad.test;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemSnippet;
import com.google.api.services.youtube.model.PlaylistSnippet;
import com.google.api.services.youtube.model.PlaylistStatus;
import com.google.api.services.youtube.model.ResourceId;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

//import com.google.api.services.samples.youtube.cmdline.Auth;


public class Y__PlaylistUpdates extends AsyncTask <Object,Object,Object>{

    private static YouTube youtube;

    public Context con=null;

    public GoogleAccountCredential mCredential=null;

    public Y__AsyncResponse dlegate=null;

    public  String PlaylistNameOrId=null;

    public int Chooser;

    public  String VideoId=null;

    public Y__PlaylistUpdates(int Choose, String VideoId, String PlaylistNameOrId, GoogleAccountCredential Account , Context context , Y__AsyncResponse response ) {
        this.mCredential=Account;
        this.con=context;
        this.dlegate=response;
        this.PlaylistNameOrId=PlaylistNameOrId;
        this.Chooser=Choose;
        this.VideoId=VideoId;
    }


    HttpTransport transport = AndroidHttp.newCompatibleTransport();
    JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

    private static final String VIDEO_ID = "SZj6rAYkYOg";


    public Object Playlist(int Choose , String PlaylistId ,String VideoId ){
        // authenticated user's account.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");

        try {


            youtube = new YouTube.Builder(transport,jsonFactory, mCredential).setApplicationName(
                    "youtube-cmdline-uploadvideo-sample").build();


           if(Choose==1){
            // Create a new, private playlist in the authorized user's channel.
            String playlistId = insertPlaylist();

            // If a valid playlist was created, add a video to that playlist.
            insertPlaylistItem(playlistId, VIDEO_ID);}
            if(Choose==2){
                // If a valid playlist was created, add a video to that playlist.
                insertPlaylistItem(PlaylistId, VideoId);}


        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        } catch (Throwable t) {
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
        }
        return null;
    }


    private String insertPlaylist() throws IOException {

        // This code constructs the playlist resource that is being inserted.
        // It defines the playlist's title, description, and privacy status.
        PlaylistSnippet playlistSnippet = new PlaylistSnippet();
        playlistSnippet.setTitle(this.PlaylistNameOrId  + Calendar.getInstance().getTime());
        playlistSnippet.setDescription("A private playlist created with the YouTube API v3");
        PlaylistStatus playlistStatus = new PlaylistStatus();
        playlistStatus.setPrivacyStatus("private");

        Playlist youTubePlaylist = new Playlist();
        youTubePlaylist.setSnippet(playlistSnippet);
        youTubePlaylist.setStatus(playlistStatus);

        // Call the API to insert the new playlist. In the API call, the first
        // argument identifies the resource parts that the API response should
        // contain, and the second argument is the playlist being inserted.
        try {
            YouTube.Playlists.Insert playlistInsertCommand =
                    youtube.playlists().insert("snippet,status", youTubePlaylist);
            Playlist playlistInserted = playlistInsertCommand.execute();

            return playlistInserted.getId();
        } catch (Exception e) {
            e.getMessage();
        }

        // Print data from the API response and return the new playlist's
        // unique playlist ID.

        return null;
    }


    private static String insertPlaylistItem(String playlistId, String videoId) throws IOException {

        // Define a resourceId that identifies the video being added to the
        // playlist.
        ResourceId resourceId = new ResourceId();
        resourceId.setKind("youtube#video");
        resourceId.setVideoId(videoId);

        // Set fields included in the playlistItem resource's "snippet" part.
        PlaylistItemSnippet playlistItemSnippet = new PlaylistItemSnippet();
        playlistItemSnippet.setTitle("First video in the tamaguzi playlist");
        playlistItemSnippet.setPlaylistId(playlistId);
        playlistItemSnippet.setResourceId(resourceId);

        // Create the playlistItem resource and set its snippet to the
        // object created above.
        PlaylistItem playlistItem = new PlaylistItem();
        playlistItem.setSnippet(playlistItemSnippet);

        // Call the API to add the playlist item to the specified playlist.
        // In the API call, the first argument identifies the resource parts
        // that the API response should contain, and the second argument is
        // the playlist item being inserted.
       try {
           YouTube.PlaylistItems.Insert playlistItemsInsertCommand =
                   youtube.playlistItems().insert("snippet,contentDetails", playlistItem);
           PlaylistItem returnedPlaylistItem = playlistItemsInsertCommand.execute();
           return returnedPlaylistItem.getId();
       }
       catch (Exception e)
       {
           e.getMessage();
           e.getStackTrace();
       }
        return null;
    }


    @Override
    protected Object doInBackground(Object... params) {
        return Playlist(this.Chooser , this.PlaylistNameOrId ,this.VideoId);
    }

    @Override
    protected void onPostExecute( Object youtubeplaylist){
        dlegate.ProcessFinish( youtubeplaylist);
    }
}
