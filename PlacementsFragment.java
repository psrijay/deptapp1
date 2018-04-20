package com.example.android.dept;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlacementsFragment extends Fragment {

View rootView;
    public PlacementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_placements, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Placements");
        return rootView;
    }

}
