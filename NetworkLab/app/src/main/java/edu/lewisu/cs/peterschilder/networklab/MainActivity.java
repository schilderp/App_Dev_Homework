package edu.lewisu.cs.peterschilder.networklab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView txtDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtDisplay = (TextView)findViewById(R.id.txtDisplay);

    }

    public void  goButtonClick(View v){
        String urlString = "http://cs.lewisu.edu/~howardcy/php/books1.php";
        String jsonData = "";
        try
        {
            //all statements here
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = "";
            line = bufferedReader.readLine();
            while (line != null ){
                jsonData += line;
                line = bufferedReader.readLine();
            }

            urlConnection.disconnect();
            txtDisplay.setText(jsonData);
        }
        catch (Exception e)
        {
            Log.e("error", e.getMessage().toString());
        }
    }

    private class  DownloadData extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}
