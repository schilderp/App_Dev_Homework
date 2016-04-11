package edu.lewisu.cs.peterschilder.implicitintents;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openWebsite(View v){
        Uri uri = Uri.parse("http://gizmag.com");
        Intent myIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(myIntent);
    }

    public void openContacts(View v){
        Uri uri = Uri.parse("content://contacts/people");
        Intent myIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(myIntent);
    }

    public void searchWeb(View view){
        Intent myIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        myIntent.putExtra(SearchManager.QUERY, "ccleaner");
        startActivity(myIntent);
    }

    public void callHome(View view){
        Uri uri = Uri.parse("tel:9109391138");
        Intent myIntent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(myIntent);

    }

}
