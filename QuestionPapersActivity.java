package com.example.android.dept;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class QuestionPapersActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    Context context;

    RecyclerView.Adapter recyclerView_Adapter;

    Boolean isChecked=false;


    RecyclerView.LayoutManager recyclerViewLayoutManager;
    private int[] mThumbIds = {
                R.drawable.ic_menu_camera,
                R.drawable.ic_menu_gallery,
                R.drawable.ic_menu_manage,
                R.drawable.ic_menu_send,
                R.drawable.ic_menu_share,
                R.drawable.ic_menu_slideshow,
                R.drawable.qa_img,
                R.drawable.gallery,
                R.drawable.album1,
                R.drawable.achievements,
                R.drawable.dept,
                R.mipmap.about_img,
                R.mipmap.abt_img,
                R.mipmap.acad_img,
                R.mipmap.adobe,
                R.mipmap.devendra,
                R.mipmap.events_img,
                R.drawable.faculty,
                R.drawable.fest,
                R.drawable.ou_logo

        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_papers);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Change 2 to your choice because here 2 is the number of Grid layout Columns in each row.
        recyclerViewLayoutManager = new GridLayoutManager(getApplicationContext(), 3);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerView_Adapter = new RecyclerViewAdapter(getApplicationContext(),mThumbIds);

        recyclerView.setAdapter(recyclerView_Adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, final int position) {
                Intent intent=new Intent(getApplicationContext(),PhotoActivity.class);
                intent.putExtra("image",position);
                intent.putExtra("images",mThumbIds);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                isChecked=true;
                recyclerView.setAdapter(recyclerView_Adapter);
            }

        }));

    }
    @Override
    public void onBackPressed() {
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
class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    int[] values;
    Context context1;

    public RecyclerViewAdapter(Context context2,int[] values2){

        values = values2;

        context1 = context2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        CheckBox checkBox;

        public ViewHolder(View v){

            super(v);

            imageView = (ImageView) v.findViewById(R.id.img1);
           // checkBox=(CheckBox)v.findViewById(R.id.check);


        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view1 = LayoutInflater.from(context1).inflate(R.layout.question_papers_grid,parent,false);
        ViewHolder viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Vholder, final int position){

        Vholder.imageView.setImageResource(mThumbIds[position]);
        Vholder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        if(isChecked)
//        Vholder.checkBox.setVisibility(View.VISIBLE);
//        else{
//            Vholder.checkBox.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount(){

        return values.length;
    }
}
}
