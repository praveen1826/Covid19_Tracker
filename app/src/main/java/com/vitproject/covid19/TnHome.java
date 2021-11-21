package com.vitproject.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TnHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView c;
    TextView a;
    TextView r;
    TextView d;
    DatabaseReference tnCases;
    NavigationView navigationView1;
    DrawerLayout drawer_layout1;
    Button menu1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tn_home);

        c = findViewById(R.id.confirmed1);
        a = findViewById(R.id.active1);
        r = findViewById(R.id.recovered1);
        d = findViewById(R.id.deceased1);
        navigationView1 = findViewById(R.id.navigation_view1);
        drawer_layout1 = findViewById(R.id.drawer_layout1);
        menu1 = findViewById(R.id.menu1);

        navigationDrawer();

        tnCases = FirebaseDatabase.getInstance().getReference().child("tnCases");
        tnCases.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String confirm = dataSnapshot.child("confirmed1").getValue().toString();
                String act = dataSnapshot.child("active1").getValue().toString();
                String recover = dataSnapshot.child("recovered1").getValue().toString();
                String decease = dataSnapshot.child("deceased1").getValue().toString();
                c.setText(confirm);
                a.setText(act);
                r.setText(recover);
                d.setText(decease);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void selection() {
        if (drawer_layout1.isDrawerVisible(GravityCompat.START)) {
            navigationView1.setCheckedItem(R.id.nav_home);
        }
    }


    private void navigationDrawer() {
        // navigation drawer
        navigationView1.bringToFront();
        navigationView1.setNavigationItemSelectedListener(this);
        navigationView1.setCheckedItem(R.id.nav_home);

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer_layout1.isDrawerVisible(GravityCompat.START)) {
                    drawer_layout1.closeDrawer(GravityCompat.START);
                } else drawer_layout1.openDrawer(GravityCompat.START);
                navigationView1.setCheckedItem(R.id.nav_home);
            }
        });
    }

    @Override
    public void onBackPressed() {
        navigationView1.setCheckedItem(R.id.nav_home);
        if (drawer_layout1.isDrawerVisible(GravityCompat.START)) {
            drawer_layout1.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_covid191:
                startActivity(new Intent(getApplicationContext(), Covid19Info.class));
                break;
            case R.id.nav_bulletin1:
                startActivity(new Intent(getApplicationContext(), TnBulletin.class));
                break;
            case R.id.nav_donate1:
                startActivity(new Intent(getApplicationContext(), DonateTn.class));
                break;
            case R.id.nav_testing_centers1:
                startActivity(new Intent(getApplicationContext(), TestCenters.class));
                break;
            case R.id.Vaccine1:
                startActivity(new Intent(getApplicationContext(), TnVaccine.class));
                break;
            case R.id.contact_us1:
                startActivity(new Intent(getApplicationContext(), Contact_Us.class));
                break;
        }
        return true;
    }
}