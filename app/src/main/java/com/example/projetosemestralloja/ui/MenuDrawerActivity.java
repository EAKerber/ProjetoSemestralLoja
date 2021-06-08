package com.example.projetosemestralloja.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.projetosemestralloja.R;
import com.google.android.material.navigation.NavigationView;

public class MenuDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navView;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer);

        toolbar = findViewById(R.id.toolbar);
        navView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        navView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    public void setActivityTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void checkStartingItem(){
        for (int i = 0; i < navView.getMenu().size(); i++){
            if(navView.getMenu().getItem(i).getTitle().equals(getSupportActionBar().getTitle())){
                navView.setCheckedItem(navView.getMenu().getItem(i).getItemId());
            }

        }

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!(getClass().equals(MainActivity2.class))) {
                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_carrinho:
                if (!(getClass().equals(PaginaCarrinho.class))) {
                    startActivity(new Intent(getApplicationContext(), PaginaCarrinho.class));
                }
                break;
            case R.id.nav_home:
                Log.d("drawerMain","4");
                if (!(getClass().equals(MainActivity2.class))) {
                    startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                }
                break;
            case R.id.nav_login:
                if (!(getClass().equals(LoginScreen.class))) {
                    startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                }
                break;
        }

        return true;
    }
}