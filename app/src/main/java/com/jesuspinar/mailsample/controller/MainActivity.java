package com.jesuspinar.mailsample.controller;

import android.os.Bundle;

import com.jesuspinar.mailsample.R;
import com.jesuspinar.mailsample.fragments.Newmail;
import com.jesuspinar.mailsample.fragments.Received;
import com.jesuspinar.mailsample.fragments.Emailed;
import com.jesuspinar.mailsample.fragments.Spam;
import com.jesuspinar.mailsample.fragments.Trash;
import com.jesuspinar.mailsample.fragments.Unread;
import com.google.android.material.navigation.NavigationView;
import com.jesuspinar.mailsample.model.Mail;

import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IOnClickListener, Emailed.IOnAttachListener, NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout drawer;
    private Mail[] mails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Obtenemos una referencia al header del Navigation para poder modificarlo en tiempo de ejecución
        View headerView = navigationView.getHeaderView(0);
        ImageView ivUser = headerView.findViewById(R.id.ivProfile);
        TextView tvUser = headerView.findViewById(R.id.tvUser);
        tvUser.setText(R.string.nav_header_title);
        TextView tvEmail = headerView.findViewById(R.id.tvEmail);
        tvEmail.setText(R.string.nav_header_subtitle);
        Parser p = new Parser(this);
        if (p.parse()){
            mails = p.getMails();
        }
    }

    @Override
    public void onBackPressed() {
        /*
         * Si el usuario pulsa el botón atrás mientras está mostrándose el menú del NavigationView,
         * hacemos que se cierre dicho menú, ya que el comportamiento por defecto es cerrar la
         * Activity.
         */
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Cargamos el menú de la ActionBar
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Se ha hecho click en algún item del menú de la ActionBar
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment f;
        // Se ha hecho click en algún item del NavigationView
        int id = item.getItemId();

        if (id == R.id.nav_received) {
            f = new Received();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, f)
                    .commit();
            setTitle(R.string.received);
        } else if (id == R.id.nav_emailed) {
            f = new Emailed();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, f)
                    .commit();
            setTitle(R.string.emailed);
        } else if (id == R.id.nav_unread) {
            f = new Unread();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, f)
                    .commit();
            setTitle(R.string.unread);
        } else if (id == R.id.nav_newmail) {
            f = new Newmail();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, f)
                    .commit();
            setTitle(R.string.newmail);
        }

        else if (id == R.id.nav_spam){
            f = new Spam();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, f)
                    .commit();
            setTitle(R.string.spam);
        } else if (id == R.id.nav_trash) {
            f = new Trash();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, f)
                    .commit();
            setTitle(R.string.trash);

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(int position) {
        //TODO: next implementation load other fragment with full view
    }

    @Override
    public Mail[] getMails() {
        return mails;
    }
}
