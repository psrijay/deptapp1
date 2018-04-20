package com.example.android.dept;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class TimeTableFragment extends Fragment {
    private View rootView;

    public TimeTableFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_time_table, container, false);
        final int[] mThumbIds={
                R.mipmap.acad_img
        };
        ImageView timetable = (ImageView) rootView.findViewById(R.id.timetable);
        timetable.setImageResource(R.mipmap.acad_img);
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PhotoActivity.class);
                intent.putExtra("image",0);
                intent.putExtra("images",mThumbIds);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
