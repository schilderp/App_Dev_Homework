package edu.lewisu.cs.peterschilder.cookietracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

public class CookieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            UUID cookieId= (UUID)getIntent().getSerializableExtra("id");
            fragment = CookieFragment.newInstance(cookieId);
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_container, fragment);
            ft.commit();
        }
    }
}
