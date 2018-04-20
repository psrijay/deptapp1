package com.example.android.dept;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EResourcesFragment extends Fragment {

    private View rootView;
    String[]  listItems = {"IEEE", "ACM", "LSC", "ASCE", "ASME", "NPTEL"};
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public EResourcesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_eresources, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("E-Resources");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.eresources);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EresourcesAdapter();
        recyclerView.setAdapter(adapter);
        return rootView;
    }
class EresourcesAdapter extends RecyclerView.Adapter<EresourcesAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView nameTextView;
        LinearLayout layout;

        @Override
        public void onClick(View v) {

        }

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView)itemView.findViewById(R.id.name);
            layout=(LinearLayout)itemView.findViewById(R.id.layout);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.eresources_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nameTextView.setText(listItems[position]);
        holder.layout.setOnClickListener(onClickListener(position));
    }
    @Override
    public int getItemCount() {
        return listItems.length;
    }
    private View.OnClickListener onClickListener(final int position){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//you won't need yes no buttons because it is already containing in your layout
                    View view = LayoutInflater.from(getContext())
                            .inflate(R.layout.eresources_dialog, null, false);
                    final AlertDialog alert=builder.create();
                    alert.setView(view,0,0,0,0);
                    Button back=(Button)view.findViewById(R.id.back);
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alert.dismiss();
                        }
                    });

                    alert.show();
                    TextView t2 = (TextView) view.findViewById(R.id.website);
                    TextView head = (TextView) view.findViewById(R.id.name);
                    head.setText(listItems[position]);
                    TextView text = (TextView) view.findViewById(R.id.text);
                if(position==0)
                    text.setText(R.string.ieee);
                else if(position==1)
                    text.setText(R.string.acm);
                else
                    text.setText(R.string.ieee);
                    t2.setMovementMethod(LinkMovementMethod.getInstance());
                    t2.setText(Html.fromHtml(getResources().getString(R.string.link)));
            }
        };
    }
}
}
