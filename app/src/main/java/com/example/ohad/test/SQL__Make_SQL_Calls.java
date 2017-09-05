package com.example.ohad.test;

import android.os.AsyncTask;

/**
 * Created by ohad on 5/15/2017.
 */

public class SQL__Make_SQL_Calls extends AsyncTask<String,String,Object>
{

    Boolean isSuccess = false;

    String id;

    //if needed this is the table to be send to the sql table
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    SQL__YouTubeTable YouTubeTable= new SQL__YouTubeTable();
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //after the class task is finished this is the variable that through it the result is returned
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public SQL__AsyncResponse dlegate=null;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //the sql query numbers to commit
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public int[] MakeCalls;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public SQL__Make_SQL_Calls(SQL__AsyncResponse response, int[] Calls, SQL__YouTubeTable Table){
        MakeCalls = new int[Calls.length];
        MakeCalls = Calls;
        dlegate = response;
        YouTubeTable=Table;
    }
    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(Object r) {
        if(isSuccess) {
           //where the good staff happens
            dlegate.ProcessFinish(r);
        }

    }
    //send to SQL__QueryHandler the sql query order (MakeCalls variable)
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    protected Object doInBackground(String... params) {
            SQL__YouTubeTable youtube=new SQL__YouTubeTable();
            try {
             {
                        if (MakeCalls[0] != 2) {// 2 is the insert number in the Query handler
                            SQL__QueryHandler Q = new SQL__QueryHandler(MakeCalls, youtube);
                            youtube = ((SQL__YouTubeTable) Q.SendData);
                            isSuccess = true;

                        } else {
                            youtube.YouTubeTableSize(1);
                            youtube.PlayList[0] = "PL-A3xBsJKHzHg-uDRMdM-OQsvLYtjrht2";
                            youtube.count = 5;
                            youtube.VideoId[0] = "0KYroBErj1g";
                            youtube.id[0] = "8";
                            youtube.Last_View_Date[0] = "04-04-2017";
                            SQL__QueryHandler Q = new SQL__QueryHandler(MakeCalls, YouTubeTable);
                            isSuccess = true;
                            return null;
                        }
                }
            }
            catch (Exception ex)
            {
                isSuccess = false;
                id = null;
            }
        return youtube;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}