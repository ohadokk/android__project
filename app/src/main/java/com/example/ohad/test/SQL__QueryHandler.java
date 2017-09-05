package com.example.ohad.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQL__QueryHandler {

    //amount of sql orders
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private static final int HandlerSize = 3;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //the sql order numbers
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private static final int YouTubeSelect = 0;
    private static final int YouTubeCount  = 1;
    private static final int YoTubeInsert  = 2;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // to make ne query we need to make new const uptick the HandlerSize
    // and put the query in Querys function

    SQL__ConnectionClass SQLConnectionClass = new SQL__ConnectionClass();

    String[] query;
    Statement[] stmt;
    ResultSet[] result;

    Object SendData,GetData;

    int[] queryToExecute;

    //the builder get an array of the numbers of the query numbers needed to be execute
    // and a youtube table as an object to send to the server
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    SQL__QueryHandler(int[] QueryNum , Object Receive) throws SQLException {

        query = new String[HandlerSize];
        stmt = new Statement[HandlerSize];
        result =new ResultSet[HandlerSize];

        SendData = new Object();
        GetData = Receive;

        queryToExecute =QueryNum;

        //create the sql query that is needed
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        for(int i=0;i<QueryNum.length;i++){
            query[QueryNum[i]]=Querys(QueryNum[i]);
        }
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        ExecuteQuery(QueryNum);
        resiveData(QueryNum);

    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //the querys needed is prefix in this function
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    String Querys(int QueryNum){
        if(QueryNum==YouTubeSelect)
            return "select * from dbo.YouTubeRepetition ORDER BY count_repetition DESC";
        if(QueryNum==YouTubeCount){
          return "SELECT COUNT(*) from dbo.YouTubeRepetition";
        }
        if(QueryNum==YoTubeInsert) {
            /*return "INSERT INTO dbo.YouTubeRepetition(id,PlayListId,videoId,count_repetition,Last_View_Date)"+
                   "VALUES ("+((SQL__YouTubeTable)GetData).id[0]+",'"+((SQL__YouTubeTable)GetData).PlayList[0]+"','"+((SQL__YouTubeTable)GetData).VideoId[0]+"',"+Integer.toString(((SQL__YouTubeTable) GetData).count)+",'"+((SQL__YouTubeTable)GetData).Last_View_Date[0] +"');";
*/            return "if not exists (select id from dbo.YouTubeRepetition where videoId='"+((SQL__YouTubeTable)GetData).VideoId[0]+"')"+
                    "begin"+
                    " INSERT INTO  dbo.YouTubeRepetition (id,PlayListId,videoId,count_repetition,Last_View_Date) " +
                    " VALUES ("+((SQL__YouTubeTable)GetData).id[0]+",'"+((SQL__YouTubeTable)GetData).PlayList[0]+"','"+((SQL__YouTubeTable)GetData).VideoId[0]+"',"+Integer.toString(((SQL__YouTubeTable) GetData).count)+",'"+((SQL__YouTubeTable)GetData).Last_View_Date[0] +"')" +
                    "end;" +
                    " else " +
                    " begin " +
                    "UPDATE dbo.YouTubeRepetition" +
                    " SET count_repetition = count_repetition + 1 " +
                    " WHERE videoId ='"+((SQL__YouTubeTable)GetData).VideoId[0]+"'; " +
                    "end;";
        }
        return "";
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //the function execute the query's using statement variable for each query
    //if needed for each get request from the server has its own
    //result variable to be inserted with result
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void ExecuteQuery(int[] QueryNum) throws SQLException {
        Connection con = SQLConnectionClass.CONN();
       int i=0;
        try {
        for(i=0;i<QueryNum.length;i++){
            stmt[QueryNum[i]] = con.createStatement();
            if(QueryNum[i]!=2){
                result[QueryNum[i]] = stmt[QueryNum[i]].executeQuery(query[QueryNum[i]]);}
            else
                stmt[QueryNum[i]].execute(query[QueryNum[i]]);}//insert statement  required different execute (thus the if)
        }
         catch (Exception ex)
            {
                ex.getMessage();
            }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    void resiveData(int[] QueryNum) throws SQLException {
        for(int i=0;i<QueryNum.length;i++){
            if(QueryNum[i]==YouTubeSelect){
                SendData =  GetYouTubeData();
            }
        }

    }

    //covert the server result to youtube table object
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    SQL__YouTubeTable GetYouTubeData() throws SQLException {
        SQL__YouTubeTable You =new SQL__YouTubeTable();
        result[YouTubeCount].next();
        int Size= Integer.parseInt(result[YouTubeCount].getString(1));
        You.YouTubeTableSize(Size);
        You.count=Size;
        int count=0;
        while(result[YouTubeSelect].next())
        {
            You.id[count]=result[YouTubeSelect].getString(1);
            You.PlayList[count]=result[YouTubeSelect].getString(2);
            You.VideoId[count]=result[YouTubeSelect].getString(3);
            You.count_repetition[count]=result[YouTubeSelect].getString(4);
            You.Last_View_Date[count]=result[YouTubeSelect].getString(5);

            count++;

        }
        return You;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
