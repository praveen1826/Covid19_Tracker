package com.vitproject.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Vaccination extends AppCompatActivity {

    DatabaseReference vaccines;
    TextView fd;
    TextView sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);

        fd = findViewById(R.id.fd);
        sd = findViewById(R.id.sd);

        vaccines = FirebaseDatabase.getInstance().getReference().child("vaccine");
        vaccines.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String FD = dataSnapshot.child("firstdose").getValue().toString();
                String SD = dataSnapshot.child("seconddose").getValue().toString();
                fd.setText(FD);
                sd.setText(SD);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}