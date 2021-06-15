package com.example.projetosemestralloja.ui;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.model.ItemDoCarrinho;
import com.google.android.material.navigation.NavigationView;

import static com.example.projetosemestralloja.ui.PaginaCarrinho.produtos;
import static com.example.projetosemestralloja.ui.PaginaCarrinho.vaiFinalizarCompra;

public class MenuDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SensorEventListener {

    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navView;
    ActionBarDrawerToggle toggle;
    public SensorManager sensorManager;
    public Sensor accelerometer1;
    private static final float SHAKE_THRESHOLD = 3.25f; // m/S**2
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 1000;
    private static final int MIN_TIME_BETWEEN_ATT_MILLISECS = 3000;
    private long mLastShakeTime;
    public long mLastAttTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer1 = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

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

    public void checkStartingItem() {
        for (int i = 0; i < navView.getMenu().size(); i++) {
            if (navView.getMenu().getItem(i).getTitle().equals(getSupportActionBar().getTitle())) {
                navView.setCheckedItem(navView.getMenu().getItem(i).getItemId());
            }

        }

    }

    public void revertDrawerState() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
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
                Log.d("drawerMain", "4");
                if (!(getClass().equals(MainActivity2.class))) {
                    startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                }
                break;
            case R.id.nav_login:
                if (!(getClass().equals(LoginScreen.class))) {
                    if (!vaiFinalizarCompra) {
                        produtos.clear();
                    }
                    vaiFinalizarCompra = false;
                    LoginScreen.setCpfNull();
                    LoginScreen.setEmailNull();
                    startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                }
                break;
        }

        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(Math.pow(x, 2) +
                        Math.pow(y, 2) +
                        Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
                Log.d("acelerometer", "Acceleration is " + acceleration + "m/s^2");

                if (acceleration > SHAKE_THRESHOLD) {
                    mLastShakeTime = curTime;
                    revertDrawerState();
                    Log.d("acelerometer", "Shake, Rattle, and Roll");
                }
            }

            curTime = System.currentTimeMillis();
            if ((curTime - mLastAttTime) > MIN_TIME_BETWEEN_ATT_MILLISECS) {
                if (getClass().equals(FinalizarCompra.class)) {
                    bindFinal();
                } else if (getClass().equals(PaginaCarrinho.class)) {
                    bindCarrinho();
                }

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //revertDrawerState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        }
        Sensor magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magneticField != null) {
            sensorManager.registerListener(this, magneticField,
                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public double getPrecoTotal() {
        double valorTotalDoCarrinho = 0;
        for (ItemDoCarrinho obj : produtos) {
            valorTotalDoCarrinho = valorTotalDoCarrinho + obj.getPrecototal();
        }
        return valorTotalDoCarrinho;
    }

    public int getQtdItensCarrinho() {
        return produtos.size();
    }

    public void bindCarrinho() {
        TextView tx = findViewById(R.id.qtd_tot_itens);
        tx.setText("" + getQtdItensCarrinho());
        tx = findViewById(R.id.preco_tot);
        tx.setText("" + getPrecoTotal());
        mLastAttTime = System.currentTimeMillis();
    }

    public void bindFinal() {
        TextView tx = findViewById(R.id.tv_valorTotal);
        tx.setText("R$ " + getPrecoTotal());
        mLastAttTime = System.currentTimeMillis();
    }
}