package com.example.android.dept;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.List;

public class AcademicsActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academics);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        AcademicsAdapter adapter = new AcademicsAdapter(this,getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem()==1)
        {
            List fragmentList = getSupportFragmentManager().getFragments();
            boolean handled = false;
            for(Object f : fragmentList) {
                if(f instanceof BaseFragment) {
                    handled = ((BaseFragment)f).onBackPressed();

                    if(handled) {
                        break;
                    }
                }
            }
            if(!handled) {
                super.onBackPressed();
            }
        }
        else
            super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    class AcademicsAdapter extends FragmentPagerAdapter {
        private Context cc;

        public AcademicsAdapter(Context c,FragmentManager fm) {
            super(fm);
            cc=c;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new QuestionPapersFragment();
            } else if (position == 1){
                return new SyllabusFragment();
            } else {
                return new TimeTableFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Question-Papers";
            } else if (position == 1) {
                return "Syllabus";
            } else  {
                return "Time Table";
            }
        }
    }
}
