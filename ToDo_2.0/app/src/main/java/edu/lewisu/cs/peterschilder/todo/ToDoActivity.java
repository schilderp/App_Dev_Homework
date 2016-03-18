package edu.lewisu.cs.peterschilder.todo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

public class ToDoActivity extends FragmentActivity {
    private ViewPager viewPager;
    private List<ToDo> toDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        toDos = ToDoList.get().getToDos();
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        FragmentManager fm = getSupportFragmentManager();
        PageAdapter pageAdapter = new PageAdapter(fm);
        viewPager.setAdapter(pageAdapter);

        UUID toDoID = (UUID)getIntent().getSerializableExtra("id");
        for (int i=0; i<toDos.size(); i++){
            viewPager.setCurrentItem(i);
            break;
        }

        /*Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            UUID toDoID = (UUID)getIntent().getSerializableExtra("id");
            fragment = ToDoFragment.newInstance(toDoID);
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_container, fragment);
            ft.commit();
        }*/
    }

    private class PageAdapter extends FragmentPagerAdapter{
        public PageAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ToDo toDo= toDos.get(position);
            ToDoFragment toDoFragment = ToDoFragment.newInstance(toDo.getId());
            return toDoFragment;
        }

        @Override
        public int getCount() {
            return toDos.size();
        }
    }
}
