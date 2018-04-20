package com.example.android.dept;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AcademicsFragment extends Fragment {

    private View rootView;
    private Animator mCurrentAnimator;

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private int mShortAnimationDuration;
    String[] year = {"BE 1/4", "BE 1/4", "BE 2/4", "BE 2/4", "BE 3/4", "BE 3/4", "BE 4/4", "BE 4/4"};
    String[] sem = {"SEM-I", "SEM-II", "SEM-I", "SEM-II", "SEM-I", "SEM-II", "SEM-I", "SEM-II"};

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public AcademicsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_academics, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Academics");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.academics);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AcademicsAdapter();
        recyclerView.setAdapter(adapter);
        return rootView;
    }

class AcademicsAdapter extends RecyclerView.Adapter<AcademicsAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView nameTextView;
        public TextView numberTextView;
        LinearLayout layout;

        @Override
        public void onClick(View v) {

        }

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView)itemView.findViewById(R.id.lay1);
            numberTextView = (TextView)itemView.findViewById(R.id.lay2);
            layout=(LinearLayout)itemView.findViewById(R.id.layout);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.academics_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nameTextView.setText(year[position]);
            holder.numberTextView.setText(sem[position]);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AcademicsActivity.class);
                intent.putExtra("year",position);
                v.getContext().startActivity(intent);


            }
        });
    }
    @Override
    public int getItemCount() {
        return 8;
    }

}

}
