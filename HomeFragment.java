package com.example.android.dept;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    String text;
    int position;
    private Typeface face;
    Class fragmentClass=Upcomingevents.class;
    Fragment fragment;
    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_home, container, false);
        position=getArguments().getInt("position");
        if(position==0)
            text="Upcoming\nEvents";
        else if(position==1)
            text="Faculty";
        else if(position==2)
            text="Student\nAchievements";
        else if(position==3)
            text="Infinity";
        else if(position==4)
            text="Gallery";
        CardView cardView=(CardView)rootView.findViewById(R.id.card_view);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0) {
                    fragmentClass = Upcomingevents.class;
                }else if(position==1)
                    fragmentClass = FacultyFragment.class;
                else if(position==2)
                    fragmentClass = StudentAchievementsFragment.class;
                else if(position==3)
                    fragmentClass = InfinityFragment.class;
                else if(position==4)
                    fragmentClass = GalleryFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                }catch (Exception e){}

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                       android.R.anim.fade_out);
               fragmentTransaction.replace(R.id.layout_for_fragment, fragment).commit();

            }
        });
        TextView textView=(TextView)rootView.findViewById(R.id.text);
        ImageView imageView=(ImageView) rootView.findViewById(R.id.image);
        if(position==0)
            imageView.setImageResource(R.drawable.upcoming_events);
        else if(position==1)
            imageView.setImageResource(R.drawable.faculty);
        else if(position==2)
            imageView.setImageResource(R.drawable.achievements);
        else if(position==3)
            imageView.setImageResource(R.drawable.fest);
        else if(position==4)
            imageView.setImageResource(R.drawable.gallery);
        face = Typeface.createFromAsset(getActivity().getAssets(), "Lobster.ttf");
        textView.setTypeface(face);
        textView.setText(text);
        return rootView;
    }

}
