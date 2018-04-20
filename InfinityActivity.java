package com.example.android.dept;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

public class InfinityActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("event",1);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infinity_main);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        // Create an adapter that knows which fragment should be shown on each page
        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        // Find the tab layout that shows the tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.mipmap.abt_img);
        tabLayout.getTabAt(1).setIcon(R.mipmap.events_img);
        tabLayout.getTabAt(2).setIcon(R.mipmap.spons_img);

    }
    class CategoryAdapter extends FragmentPagerAdapter {

        private Context mContext;

        public CategoryAdapter(Context context, FragmentManager fm){
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position){
            if(position==0){
                return new AbtFragment();
            }
            else if (position==1){
                return new EventsFragment();
            }
            else {
                return new SponsorFragment();
            }
        }
        /**
         * Return the total number of pages.
         */
        @Override
        public int getCount(){ return  3; }

        @Override
        public  CharSequence getPageTitle(int position){
            if(position==0){
                return null;
                //return mContext.getString(R.string.category_about);
            }
            else if(position==1){
                return null;
                //return mContext.getString(R.string.category_events);
            }
            else {
                return null;
                //return mContext.getString(R.string.category_sponsors);
            }
        }
    }

}
