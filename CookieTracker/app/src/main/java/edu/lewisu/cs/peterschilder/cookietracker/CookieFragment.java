package edu.lewisu.cs.peterschilder.cookietracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class CookieFragment extends Fragment {
    private Cookie cookie;
    private EditText cookieNameET;
    private EditText cookieToppingET;
    private CheckBox cookieHealthyCheck;

    public static CookieFragment newInstance(UUID id){
        Bundle args = new Bundle();
        args.putSerializable("id", id);
        CookieFragment fragment = new CookieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CookieFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID cookieId = (UUID)getArguments().getSerializable("id");

        cookie =  CookieDB.get().getCookie(cookieId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       //return inflater.inflate(R.layout.fragment_cookie, container, false);
        View v = inflater.inflate(R.layout.fragment_cookie, container, false);
        cookieNameET = (EditText)v.findViewById(R.id.cookie_name);
        cookieNameET.addTextChangedListener(new NameTextListener());

        cookieToppingET = (EditText)v.findViewById(R.id.topping_et);
        cookieToppingET.addTextChangedListener(new ToppingTextListener());

        cookieHealthyCheck = (CheckBox)v.findViewById(R.id.cookieHealthy);
        cookieHealthyCheck.setOnClickListener(new HealthyCheckListener());

        //Set components to display detail information
        cookieNameET.setText(cookie.getName());
        cookieToppingET.setText(cookie.getTopping());
        cookieHealthyCheck.setChecked(cookie.isHealthy());

        return v;
    }

    private class HealthyCheckListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            cookie.setHealthy(cookieHealthyCheck.isChecked());
        }
    }

    private class NameTextListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            cookie.setName(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class ToppingTextListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            cookie.setTopping(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
