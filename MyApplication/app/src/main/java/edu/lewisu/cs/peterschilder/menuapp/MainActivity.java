package edu.lewisu.cs.peterschilder.menuapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {
    private int defaultRating;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        defaultRating = preferences.getInt("Rating", 0);
        ratingBar.setRating(defaultRating);
       // ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("rating", defaultRating);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getGroupId();
        switch (id){
            case R.id.settings:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.default_choice);
                builder.setItems(R.array.values, new OnAlertClickListener());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                //Toast.makeText(getApplicationContext(), item.getTitle(),Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private class OnAlertClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            defaultRating = which + 1;
            ratingBar.setRating(defaultRating);
        }
    }
}
