package com.example.android.dept;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionPapersFragment extends Fragment {


    private View rootView;
    ArrayList<String> SUB;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public QuestionPapersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_question_papers, container, false);
        SUB = new ArrayList<>();

        SUB.add("Software Engineering");
        SUB.add("Web Programming");
        SUB.add("Design Analysis of Algorithms");
        SUB.add("Computer Network Programming");
        SUB.add("Graph Theory");
        SUB.add("Disaster Management");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.questionpapers);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Questionpapersadapter();
        recyclerView.setAdapter(adapter);

//        Questionpapersadapter SUBJECT = new Questionpapersadapter(getActivity(), SUB);
//
//        // Get a reference to the ListView, and attach the adapter to the listView.
//        ListView listView = (ListView) rootView.findViewById(R.id.questionpapers);
//        listView.setAdapter(SUBJECT);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent x = new Intent(view.getContext(), QuestionPapersActivity.class);
//                startActivity(x);
//
//
//            }
//        });

        return rootView;
    }

//    class Questionpapersadapter extends ArrayAdapter<String> {
//
//        ArrayList<String> arrayList;
//
//        public Questionpapersadapter(Activity context, ArrayList<String> arrayList) {
//
//            super(context, 0, arrayList);
//            this.arrayList = arrayList;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            View listItemView = convertView;
//            if (listItemView == null) {
//                listItemView = LayoutInflater.from(getContext()).inflate(
//                        R.layout.question_paper_list, parent, false);
//            }
//
//            // Get the {@link AndroidFlavor} object located at this position in the list
//
//            // Find the TextView in the list_item.xml layout with the ID version_name
//            TextView nameTextView = (TextView) listItemView.findViewById(R.id.qtext);
//            // Get the version name from the current AndroidFlavor object and
//            // set this text on the name TextView
//            nameTextView.setText(arrayList.get(position));
//
//
//            return listItemView;
//        }
//
//
//    }
class Questionpapersadapter extends RecyclerView.Adapter<Questionpapersadapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView nameTextView;
        LinearLayout layout;

        @Override
        public void onClick(View v) {

        }

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView)itemView.findViewById(R.id.qtext);
            layout=(LinearLayout)itemView.findViewById(R.id.layout);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.question_paper_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nameTextView.setText(SUB.get(position));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QuestionPapersActivity.class);
                intent.putExtra("subject",SUB.get(position));
                v.getContext().startActivity(intent);


            }
        });
    }
    @Override
    public int getItemCount() {
        return SUB.size();
    }

}

}
