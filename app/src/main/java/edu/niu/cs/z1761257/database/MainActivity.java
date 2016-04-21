package edu.niu.cs.z1761257.database;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    DBAdapter myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DBAdapter(this);
        myDB.open();
    }//end of onCreate

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDB.close();
    }

    private void displayText( String message ){
        TextView dbContents = (TextView)findViewById(R.id.dbTextView);
        dbContents.setText(message);
    }//end of displayText

    public void addRecord(View view){
        long newID = myDB.insertRow("Pravin", 43);
        displayText("Clicked add button - add id: " + newID);
    }//end of addRecord

    public void clearAll(View view){
        displayText("Clicked clear button");
        myDB.deleteAll();
    }//end of clearAll

    public void displayDB(View view){
        displayText("Clicked display button");
        Cursor cursor = myDB.getAllRows();
        if(cursor.moveToFirst()){
            String message = "";
            boolean isData = cursor.moveToFirst();
            while(isData){
                int id = cursor.getInt(DBAdapter.COL_ROWID),
                        studNum = cursor.getInt(DBAdapter.COL_STUDENTNUM);
                String name = cursor.getString(DBAdapter.COL_NAME);

                message += "id: " + id + ", name: " + name + ", number: " + studNum + "\n";

                isData = cursor.moveToNext();
            }
            displayText(message);
        }
        cursor.close();
    }//end of displayDB
}//end of MainActivity
