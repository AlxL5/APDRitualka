package com.alxl5.apdritualka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alxl5.apdritualka.data.DBUser;
import com.alxl5.apdritualka.models.User;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    //private Button buttonRegistration;

    private DBUser dbUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppDefaultTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        dbUser = new DBUser(this);

        Toast.makeText(getApplicationContext(), "count: " + dbUser.getCount(), Toast.LENGTH_SHORT).show();

        if(dbUser.getCount() < 1) {
            intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }

        initToolbar();

        initNavigationView();
/*
        buttonRegistration = (Button) findViewById(R.id.buttonRegistration);
        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });*/
    }

    /*
    //@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    */

    private void initNavigationView() {
        //DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.layout.navigation_header);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.resetDB:
                        dbUser.truncateUser();
                        Toast.makeText(getApplicationContext(), "Reset DB", Toast.LENGTH_LONG).show();
                        intent = new Intent(MainActivity.this, RegistrationActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu);
    }
}
