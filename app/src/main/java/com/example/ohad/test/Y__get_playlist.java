package com.example.ohad.test;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ArrayMap;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.google.api.services.youtube.model.PlaylistLocalization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

//import com.google.api.services.samples.youtube.cmdline.Auth;


public class Y__get_playlist extends AsyncTask< List<Playlist>,Void, List<Playlist> > {

    private static YouTube youtube;

    public Context con=null;

    public GoogleAccountCredential mCredential=null;

    public Y__AsyncResponse dlegate=null;

    public  String PlaylistName=null;
    public  String action=null;

    public Y__get_playlist(String action, String PlaylistName, GoogleAccountCredential Account , Context context , Y__AsyncResponse response ) {
        this.mCredential=Account;
        this.con=context;
        this.dlegate=response;
        this.PlaylistName=PlaylistName;
        this.action=action;
    }


    HttpTransport transport = AndroidHttp.newCompatibleTransport();
    JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();



    public   List<Playlist> Playlist() {
        List<Playlist> playlistreturn=null;
        try {


            youtube = new YouTube.Builder(transport, jsonFactory, mCredential).setApplicationName(
                    "youtube-connection").build();


                if(action.equals("SET")) {
                    setPlaylist(getId("playlist"), getDefaultLanguage(),
                            getLanguage(), getMetadata("title"), getMetadata("description"));
                    }
                if(action.equals("GET")){
                    playlistreturn = getPlaylistLocalization(getId("playlist"), getLanguage());
                }
                if(action.equals("LIST")){
                    listPlaylistLocalizations(getId("playlist"));
                    }

        } catch (GoogleJsonResponseException e) {
            System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode()
                    + " : " + e.getDetails().getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        } catch (Throwable t) {
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
        }
        return playlistreturn;
    }


    /**
     * Updates a playlist's default language and sets its localized metadata.
     *
     * @param playlistId The id parameter specifies the playlist ID for the resource
     * that is being updated.
     * @param defaultLanguage The language of the playlist's default metadata
     * @param language The language of the localized metadata
     * @param title The localized title to be set
     * @param description The localized description to be set
     * @throws IOException
     */
    private static void setPlaylist(String playlistId, String defaultLanguage,
                                    String language, String title, String description) throws IOException {
        // Call the YouTube Data API's playlists.list method to retrieve playlists.
        PlaylistListResponse playlistListResponse = youtube.playlists().
                list("snippet,localizations").setId(playlistId).execute();

        // Since the API request specified a unique playlist ID, the API
        // response should return exactly one playlist. If the response does
        // not contain a playlist, then the specified playlist ID was not found.
        List<Playlist> playlistList = playlistListResponse.getItems();

        Playlist playlist = playlistList.get(0);

        // Modify playlist's default language and localizations properties.
        // Ensure that a value is set for the resource's snippet.defaultLanguage property.
        playlist.getSnippet().setDefaultLanguage(defaultLanguage);

        // Preserve any localizations already associated with the playlist. If the
        // playlist does not have any localizations, create a new array. Append the
        // provided localization to the list of localizations associated with the playlist.
        Map<String, PlaylistLocalization> localizations = playlist.getLocalizations();
        if (localizations == null) {
            localizations = new ArrayMap<String, PlaylistLocalization>();
            playlist.setLocalizations(localizations);
        }
        PlaylistLocalization playlistLocalization = new PlaylistLocalization();
        playlistLocalization.setTitle(title);
        playlistLocalization.setDescription(description);
        localizations.put(language, playlistLocalization);




    }

    private static  List<Playlist> getPlaylistLocalization(String playlistId, String language) throws IOException {
        // Call the YouTube Data API's playlists.list method to retrieve playlists.
        PlaylistListResponse playlistListResponse = youtube.playlists().
                list("snippet,contentDetails").setMaxResults((long) 25).setChannelId("UC89PGgOzE0rzyKo_tyj6xLg").set("hl", language).execute();

        // Since the API request specified a unique playlist ID, the API
        // response should return exactly one playlist. If the response does
        // not contain a playlist, then the specified playlist ID was not found.
        List<Playlist> playlistList = playlistListResponse.getItems();
        if (playlistList.isEmpty()) {
            System.out.println("Can't find a playlist with ID: " + playlistId);
            return  playlistList;
        }

        return playlistList;
    }


    private static void listPlaylistLocalizations(String playlistId) throws IOException {
        // Call the YouTube Data API's playlists.list method to retrieve playlists.
        PlaylistListResponse playlistListResponse = youtube.playlists().
                list("snippet,localizations").setId(playlistId).execute();

        // Since the API request specified a unique playlist ID, the API
        // response should return exactly one playlist. If the response does
        // not contain a playlist, then the specified playlist ID was not found.
        List<Playlist> playlistList = playlistListResponse.getItems();
        if (playlistList.isEmpty()) {
            System.out.println("Can't find a playlist with ID: " + playlistId);
            return;
        }




    }

    /*
       * Prompt the user to enter a resource ID. Then return the ID.
       */
    private static String getId(String resource) throws IOException {

        String id = "PL-A3xBsJKHzHg-uDRMdM-OQsvLYtjrht2";

        return id;
    }

    /*
       * Prompt the user to enter a language for the localized metadata. Then return the language.
       */
    private static String getLanguage() throws IOException {

        String language = "";

       /* System.out.print("Please enter the localized metadata language: ");
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        language = bReader.readLine();
        */
        if (language.length() < 1) {
            // If nothing is entered, defaults to "de".
            language = "en";
        }

        return language;
    }


    private static String getDefaultLanguage() throws IOException {

        String defaultlanguage = "";

        System.out.print("Please enter the language for the resource's default metadata: ");
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        defaultlanguage = bReader.readLine();

        if (defaultlanguage.length() < 1) {
            // If nothing is entered, defaults to "en".
            defaultlanguage = "en";
        }

        System.out.println("You chose " + defaultlanguage +
                " as the language for the resource's default metadata.");
        return defaultlanguage;
    }

    private static String getMetadata(String type) throws IOException {

        String metadata = "";

        System.out.print("Please enter a localized " + type + ": ");
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        metadata = bReader.readLine();

        if (metadata.length() < 1) {
            // If nothing is entered, defaults to type.
            metadata = type + "(localized)";
        }

        System.out.println("You chose " + metadata + " as localized "+ type + ".");
        return metadata;
    }


    @Override
    protected List<Playlist> doInBackground(List<Playlist>... params) {

        return Playlist();
    }

    @Override
    protected void onPostExecute( List<Playlist> youtubeplaylist){
        dlegate.ProcessFinish( youtubeplaylist);
    }


}
