package edu.lewisu.cs.peterschilder.cookietracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class CookieFragment extends Fragment {
    private Cookie cookie;
    private EditText cookieNameET;
    private EditText cookieToppingET;
    private CheckBox cookieHealthy;


    public CookieFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        cookie = new Cookie();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       //return inflater.inflate(R.layout.fragment_cookie, container, false);
        View v = inflater.inflate(R.layout.fragment_cookie, container, false);
        cookieNameET = (EditText)v.findViewById(R.id.cookie_name);
        cookieToppingET = (EditText)v.findViewById(R.id.topping_et);
        cookieHealthy = (CheckBox)v.findViewById(R.id.cookieHealthy);

        cookieHealthy.setOnClickListener(new HealthyCheckListener());

        return v;
    }

    private class HealthyCheckListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            cookie.setHealthy(cookieHealthy.isChecked());
        }
    }

}
