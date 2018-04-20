package com.example.android.dept;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.dept.Utils.Utils;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    FragmentManager fragmentManager;
    private Handler mHandler;
    private TextView name,text;
    private TextView rollno;
    private Typeface face;
    Fragment fragment;
    Class fragmentClass;
    boolean isHome=true;
    Utils session;
    private EditText et_old_password,et_new_password,et_new_password_confirm;
    private TextView tv_message;
    private AlertDialog dialog;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text=(TextView)findViewById(R.id.mytext);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        face = Typeface.createFromAsset(getAssets(), "Lobster.ttf");
        name=(TextView)headerView.findViewById(R.id.name);
        rollno=(TextView)headerView.findViewById(R.id.rollno);
        session = new Utils(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String getname = user.get(Utils.KEY_NAME);
        String getrollno = user.get(Utils.KEY_ROLLNO);
        Menu menu = navigationView.getMenu();
        if(!session.isLoggedIn()) {
                name.setVisibility(View.GONE);
                rollno.setVisibility(View.GONE);
            menu.findItem(R.id.nav_log).setTitle("Log In");
        }
        else{
            name.setVisibility(View.VISIBLE);
            rollno.setVisibility(View.VISIBLE);
            menu.findItem(R.id.nav_log).setTitle("Log Out");
            if(getname.equals(""))
                name.setVisibility(View.GONE);
        }
        name.setText(getname);
        rollno.setText(getrollno);
        name.setTypeface(face);
        rollno.setTypeface(face);
        navigationView.setNavigationItemSelectedListener(this);
        int i=0;
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            i = extras.getInt("event");
        }
        Bundle bundle = new Bundle();
        if(i==1)
            bundle.putInt("position",3);
        else
            bundle.putInt("position",0);
        fragment = null;
        fragmentClass = null;
        ScreenSlidePagerActivity home = new ScreenSlidePagerActivity();
        home.setArguments(bundle);
        fragmentClass = ScreenSlidePagerActivity.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Closing drawer on item click
        drawer.closeDrawers();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layout_for_fragment, home).commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (fragmentClass != ScreenSlidePagerActivity.class) {
                Bundle bundle = new Bundle();
                bundle.putInt("position",0);
                ScreenSlidePagerActivity home = new ScreenSlidePagerActivity();
                home.setArguments(bundle);
                fragmentClass = ScreenSlidePagerActivity.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                }catch (Exception e){}
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.layout_for_fragment, home).commit();
                navigationView.getMenu().getItem(0).setChecked(true);
                return;
            }
            else
            {
                List fragmentList = getSupportFragmentManager().getFragments();

                boolean handled = false;
                for(Object f : fragmentList) {
                    if(f instanceof BaseFragment) {
                        handled = ((BaseFragment)f).onBackPressed();

                        if(handled) {
                            break;
                        }
                    }
                }

                if(!handled) {
                    Intent a = new Intent(Intent.ACTION_MAIN);
                    a.addCategory(Intent.CATEGORY_HOME);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(a);
                }
            }

        //super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(session.isLoggedIn())
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.change_password) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.change_password, null);
                et_old_password = (EditText)view.findViewById(R.id.et_old_password);
                et_new_password = (EditText)view.findViewById(R.id.et_new_password);
                et_new_password_confirm = (EditText)view.findViewById(R.id.et_new_password_confirm);
                tv_message = (TextView)view.findViewById(R.id.tv_message);
                builder.setView(view);
                builder.setTitle("Change Password");
                builder.setPositiveButton("Change Password", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog = builder.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String old_password = et_old_password.getText().toString();
                        String new_password = et_new_password.getText().toString();
                        String new_password_confirm = et_new_password_confirm.getText().toString();
                        if(old_password.isEmpty() || new_password.isEmpty() || new_password_confirm.isEmpty()){
                            tv_message.setVisibility(View.VISIBLE);
                            tv_message.setText("Fields are empty");
                        }else if(!new_password.equals(new_password_confirm)){
                            tv_message.setVisibility(View.VISIBLE);
                            tv_message.setText("New password and Confirm new password donot match");
                        }else{
                            dialog.dismiss();
                        }
                    }
                });
            return true;
        }
        else if (id == R.id.update_profile) {
            Toast.makeText(getApplicationContext(), "Update profile", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        selectDrawerItem(item);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }

    public void selectDrawerItem(MenuItem item) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        fragment = null;
        fragmentClass = null;
        int id = item.getItemId();


        if (id == R.id.nav_acad) {
            fragmentClass = AcademicsFragment.class;
            isHome=false;
        } else if (id == R.id.nav_eres) {
            fragmentClass = EResourcesFragment.class;
            isHome=false;
        } else if (id == R.id.nav_alumni) {
            fragmentClass = AlumniFragment.class;
            isHome=false;
        } else if (id == R.id.nav_placements) {
            fragmentClass = PlacementsFragment.class;
            isHome=false;
        } else if (id == R.id.nav_qa) {
            fragmentClass =ForumFragment.class;
            isHome=false;
        }
        else if (id == R.id.nav_aboutus) {
            fragmentClass = AboutFragment.class;
            isHome=false;
        }
        else if (id == R.id.nav_log) {
            if(session.isLoggedIn()) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage("Are you sure you want to log out?");
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        session.logoutUser();
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
            else
                session.logoutUser();
        }

        try {
            if(fragmentClass==null || fragmentClass == ScreenSlidePagerActivity.class ) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", 0);
                ScreenSlidePagerActivity fragment = new ScreenSlidePagerActivity();
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.layout_for_fragment, fragment).commit();
            }
            else {
                fragment = (Fragment) fragmentClass.newInstance();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.layout_for_fragment, fragment).commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        item.setChecked(true);
        // Set action bar title
        setTitle(item.getTitle());
        // Close the navigation drawer


    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        text.setText(title);
    }


}
