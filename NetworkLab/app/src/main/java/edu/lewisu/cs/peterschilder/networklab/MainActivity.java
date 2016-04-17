package edu.lewisu.cs.peterschilder.networklab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

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

        DownloadData downloadData = new DownloadData();
        downloadData.execute(urlString);

    }

    private class  DownloadData extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {
            String jasonData = "";
            try{

                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line ="";
                line = bufferedReader.readLine();
                while (line != null ){
                    jasonData += line;
                    line = bufferedReader.readLine();
                }

                urlConnection.disconnect();

                String title;
                String isbn;
                String results = "";

                JSONArray jsonArray = new JSONArray(jasonData);
                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    title = jsonObject.getString("title");
                    isbn = jsonObject.getString("isbn");
                    results = results + isbn + "\t" + title + "\n";

                }
                return results;

            }catch (Exception e){
                Log.e("error", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            txtDisplay.setText(s);
        }
    }
}
