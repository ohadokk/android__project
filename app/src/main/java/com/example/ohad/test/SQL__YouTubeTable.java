package com.example.ohad.test;

public class SQL__YouTubeTable {

    int count;

    String[] id;
    String[] PlayList;
    String[] VideoId;
    String[] count_repetition;
    String[] Last_View_Date;

    public void YouTubeTableSize(int size){

        id = new String[size];
        PlayList= new String[size];
        VideoId = new String[size];
        count_repetition= new String[size];
        Last_View_Date= new String[size];
    }
    SQL__YouTubeTable(){
        YouTubeTableSize(1);
    }
    SQL__YouTubeTable(int size, String id, String PlayList, String VideoId, String count_repetition, String Last_View_Date){
        this.id = new String[size];
        this.PlayList= new String[size];
        this.VideoId = new String[size];
        this.count_repetition= new String[size];
        this.Last_View_Date= new String[size];
        this.id[0] = id;
        this.PlayList[0]= PlayList;
        this.VideoId[0] = VideoId;
        this.count_repetition[0]= count_repetition;
        this.Last_View_Date[0]= Last_View_Date;

    }
    //get the top rated due to the fact that the query
    //is ordering the result by count repetition
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public  String[] TopRated(int VidAmount){
        String[] result =new String[VidAmount];
        for(int i=0; i<VidAmount ; i++){
            result[i]=PlayList[i];
        }

        return result;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public int[] StringToInt(String[] input){
        int [] output = new int[count];
        for(int i=0; i<count ;i++){
            output[i]=Integer.parseInt(input[i]);
        }
        return output;
    }

}
