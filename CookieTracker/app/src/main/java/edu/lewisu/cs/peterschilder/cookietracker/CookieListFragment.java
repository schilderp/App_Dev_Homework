package edu.lewisu.cs.peterschilder.cookietracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CookieListFragment extends Fragment {
    private RecyclerView recyclerView;
    private CookieAdapter cookieAdapter;
    private CookieDB cookieDB;
    private List<Cookie> cookies;

    public CookieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cookie_list, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);

        cookieDB = CookieDB.get();
        cookies = cookieDB.getCookies();

        cookieAdapter = new CookieAdapter(cookies);
        recyclerView.setAdapter(cookieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(cookieAdapter != null){
            cookieAdapter.notifyDataSetChanged();
        }
    }

    private class CookieHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private TextView cookieNameTV;
        Cookie cookie;

        public CookieHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cookieNameTV = (TextView)itemView.findViewById(R.id.cookie_name_tv);
        }

        public void bindCookie(Cookie c){
            cookie = c;
            cookieNameTV.setText(c.getName());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), CookieActivity.class);
            intent.putExtra("id",cookie.getId());
            startActivity(intent);
        }
    }

    private class CookieAdapter extends RecyclerView.Adapter<CookieHolder>{
        private List<Cookie> cookies;
        CookieHolder holder;

        public CookieAdapter(List<Cookie> cookies){
            this.cookies = cookies;
        }

        @Override
        public CookieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(getActivity());
            View view = li.inflate(R.layout.cookie_list_item,parent,false);
            holder = new CookieHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(CookieHolder holder, int position) {
            Cookie cookie = cookies.get(position);
            holder.bindCookie(cookie);
        }

        @Override
        public int getItemCount() {
            return cookies.size();
        }
    }
}
