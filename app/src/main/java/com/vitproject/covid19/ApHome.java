package com.vitproject.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ApHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView home;
    TextView confirmed;
    TextView active;
    TextView recovered;
    TextView deceased;

    ImageView menuIcon;
    DatabaseReference cases;


    // drawer
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        home = findViewById(R.id.home);
        confirmed = findViewById(R.id.confirmed);
        active = findViewById(R.id.active);
        recovered = findViewById(R.id.recovered);
        deceased = findViewById(R.id.deceased);

        //menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);


        navigationDrawer();
        selection();




        //retrieve cases from database

        cases = FirebaseDatabase.getInstance().getReference().child("cases");
        cases.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String confirm = dataSnapshot.child("confirmed").getValue().toString();
                String act = dataSnapshot.child("active").getValue().toString();
                String recover = dataSnapshot.child("recovered").getValue().toString();
                String decease = dataSnapshot.child("deceased").getValue().toString();
                confirmed.setText(confirm);
                active.setText(act);
                recovered.setText(recover);
                deceased.setText(decease);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void selection() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }


    private void navigationDrawer() {
        // navigation drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else drawerLayout.openDrawer(GravityCompat.START);
                navigationView.setCheckedItem(R.id.nav_home);
            }
        });
    }

    @Override
    public void onBackPressed() {
        navigationView.setCheckedItem(R.id.nav_home);
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_covid19:
                startActivity(new Intent(getApplicationContext(), Covid19Info.class));
                break;
            case R.id.nav_illegal_billing:
                startActivity(new Intent(getApplicationContext(),Illegal_Billing.class));
                break;
            case R.id.nav_bulletin:
                startActivity(new Intent(getApplicationContext(),Bulletin.class));
                break;
            case R.id.nav_about_us:
                startActivity(new Intent(getApplicationContext(),About_Us.class));
                break;
            case R.id.nav_donate:
                startActivity(new Intent(getApplicationContext(),Donate.class));
                break;
            case R.id.nav_testing_centers:
                startActivity(new Intent(getApplicationContext(),TestCenters.class));
                break;
        }
        return true;
    }
}