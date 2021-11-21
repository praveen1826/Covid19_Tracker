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

public class TnVaccine extends AppCompatActivity {

    DatabaseReference vaccines1;
    TextView fd1;
    TextView sd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tn_vaccine);

        fd1 = findViewById(R.id.fd1);
        sd1 = findViewById(R.id.sd1);

        vaccines1 = FirebaseDatabase.getInstance().getReference().child("vaccine");
        vaccines1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String FD = dataSnapshot.child("firstdose1").getValue().toString();
                String SD = dataSnapshot.child("seconddose1").getValue().toString();
                fd1.setText(FD);
                sd1.setText(SD);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}