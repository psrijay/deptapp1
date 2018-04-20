package com.example.android.dept;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlumniFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    public AlumniFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_alumni, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Alumni");
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapterAlumini();
        recyclerView.setAdapter(adapter);
        return rootview;

    }
    class RecyclerAdapterAlumini extends RecyclerView.Adapter<RecyclerAdapterAlumini.ViewHolder>{
        private String[] titles = {"Chapter One",
                "Chapter Two",
                "Chapter Three",
                "Chapter Four",
                "Chapter Five",
                "Chapter Six",
                "Chapter Seven",
                "Chapter Eight"};

        private String[] details = {"Item one details",
                "Item two details", "Item three details",
                "Item four details", "Item file details",
                "Item six details", "Item seven details",
                "Item eight details"};

        private int[] images = { R.drawable.album1,
                R.drawable.album1,
                R.drawable.album1,
                R.drawable.album1,
                R.drawable.album1,
                R.drawable.album1,
                R.drawable.album1,
                R.drawable.album1 };

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
        {

            public int currentItem;
            public ImageView itemImage;
            public TextView itemTitle;
            public TextView itemDetail;
            public Button buttonid;

            @Override
            public void onClick(View v) {

            }

            public ViewHolder(View itemView) {
                super(itemView);
                itemImage = (ImageView)itemView.findViewById(R.id.item_image);
                itemTitle = (TextView)itemView.findViewById(R.id.item_title);
                itemDetail = (TextView)itemView.findViewById(R.id.item_detail);
                buttonid=(Button)itemView.findViewById(R.id.button_id);

            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item_alumini, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            viewHolder.itemTitle.setText(titles[i]);
            viewHolder.itemDetail.setText(details[i]);
            viewHolder.itemImage.setImageResource(images[i]);
            viewHolder.buttonid.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent( v.getContext(), AluminiActivity.class );
                    intent.putExtra("img",images[i]);
                    intent.putExtra("name",titles[i]);
                    intent.putExtra("dept",details[i]);
                    v.getContext().startActivity(intent);


                }
            });
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }

    }
}
