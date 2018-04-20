package com.example.android.dept;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InfinityFragment extends BaseFragment {

    Class fragmentClass;
    Fragment fragment;
    public InfinityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootview=(ViewGroup)inflater.inflate(R.layout.fragment_infinity, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                getActivity().startActivity(new Intent(getActivity(), InfinityActivity.class));
                getActivity().finish();
            }
        }, 1000);
        return rootview;
    }
    @Override
    public boolean onBackPressed() {
        Bundle bundle = new Bundle();
        bundle.putInt("position",3);
        ScreenSlidePagerActivity home = new ScreenSlidePagerActivity();
        home.setArguments(bundle);
        fragmentClass = ScreenSlidePagerActivity.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        }catch (Exception e){}

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.layout_for_fragment,home).commit();
        return true;
    }

}
