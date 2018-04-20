package com.example.android.dept;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends BaseFragment {

    Class fragmentClass;
    Fragment fragment;
    private RecyclerView recyclerView;
    private AlbumAdapter adapter;
    private List<Album> albumList;

    public FacultyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_faculty, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        ((MainActivity) getActivity()).setActionBarTitle("Faculty");

        albumList = new ArrayList<>();
        adapter = new AlbumAdapter(getActivity(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        return rootView;
    }
    @Override
    public boolean onBackPressed() {
        Bundle bundle = new Bundle();
        bundle.putInt("position",1);
        ScreenSlidePagerActivity home = new ScreenSlidePagerActivity();
        home.setArguments(bundle);
        fragmentClass = ScreenSlidePagerActivity.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        }catch (Exception e){}

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.layout_for_fragment, home).commit();
        return true;
    }
    /**
     * Adding few albums for testing
     */
    private void prepareAlbums(){
        int[] covers = new int[]{
                R.drawable.album1

        };

        Album a = new Album("Prof. K. Shyamala","HOD",covers[0]);
        albumList.add(a);
        Album b = new Album("Prof. S. Ramachandram","Professor",covers[0]);
        albumList.add(b);
        Album c = new Album("Prof. P. Premchand","Professor",covers[0]);
        albumList.add(c);
        Album d = new Album("Prof. S. Sameen Fatima","Professor",covers[0]);
        albumList.add(d);
        Album f = new Album("M. Venkat Das","Associate Professor",covers[0]);
        albumList.add(f);
        Album g = new Album("S. Ram Babu","Associate Professor",covers[0]);
        albumList.add(g);
        Album h = new Album("S. Srinivas Rao","Associate Professor",covers[0]);
        albumList.add(h);
        Album i = new Album("L. K. Suresh Kumar","Associate Professor",covers[0]);
        albumList.add(i);
        Album j = new Album("Dr. P. V. Sudha","Associate Professor",covers[0]);
        albumList.add(j);
        Album k = new Album("Dr. V. B. Narsimha","Assistant Professor",covers[0]);
        albumList.add(k);
        Album l = new Album("Dr. B. Sujatha","Assistant Professor",covers[0]);
        albumList.add(l);
        Album m = new Album("Dr. M.A.Hameed","Assistant Professor",covers[0]);
        albumList.add(m);
        Album n = new Album("E. Pragnavi","Assistant Professor",covers[0]);
        albumList.add(n);
        Album o = new Album("V. Sukanya","Assistant Professor",covers[0]);
        albumList.add(o);
        Album p = new Album("K. Srinivasa Reddy","Assistant Professor",covers[0]);
        albumList.add(p);
        Album q = new Album("K. Pranitha Kumari","Assistant Professor",covers[0]);
        albumList.add(q);
        Album r = new Album("I. Govardhana Rao","Assistant Professor",covers[0]);
        albumList.add(r);
        Album s = new Album("A. Gayathri","Assistant Professor",covers[0]);
        albumList.add(s);
        Album t = new Album("S. Radha Rani","Assistant Professor",covers[0]);
        albumList.add(t);
        Album u = new Album("M. Narender Reddy","Assistant Professor",covers[0]);
        albumList.add(u);
        Album v = new Album("K. Satya Narayana","Assistant Professor",covers[0]);
        albumList.add(v);
        Album w = new Album("C. Vani","Assistant Professor",covers[0]);
        albumList.add(w);
        Album x = new Album("K. Jaya","Assistant Professor",covers[0]);
        albumList.add(x);
        Album y = new Album("M. Tirupathi","Assistant Professor",covers[0]);
        albumList.add(y);

    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

        private Context mContext;
        private List<Album> albumList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title,des;
            public ImageView thumbnail,overflow;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.title);
                des = (TextView) view.findViewById(R.id.des);
                thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
                overflow = (ImageView) view.findViewById(R.id.overflow);
            }
        }
        public AlbumAdapter(Context mContext, List<Album> albumList) {
            this.mContext = mContext;
            this.albumList = albumList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.album_card, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            Album album = albumList.get(position);
            holder.title.setText(album.getName());
            holder.des.setText(album.getPost());

            // loading album cover using Glide library
            Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

            holder.overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(holder.overflow);
                }
            });
        }

        /**
         * Showing popup menu when tapping on 3 dots
         */
        private void showPopupMenu(View view) {
            // inflate menu
            PopupMenu popup = new PopupMenu(mContext, view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu_album, popup.getMenu());
            popup.setOnMenuItemClickListener(new AlbumAdapter.MyMenuItemClickListener());
            popup.show();
        }
        /**
         * Click listener for popup menu items
         */
        class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

            public MyMenuItemClickListener() {
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_add_favourite:
//                        Intent intent = new Intent( mContext, AluminiActivity.class );
//
//                        mContext.startActivity(intent);
                        //Toast.makeText(mContext, "Opening details", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                }
                return false;
            }
        }
        @Override
        public int getItemCount() {
            return albumList.size();
        }
    }
}

