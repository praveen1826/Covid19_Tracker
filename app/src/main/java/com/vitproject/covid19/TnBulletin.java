package com.vitproject.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TnBulletin extends AppCompatActivity {

    DatabaseReference bulletin_pdf1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tn_bulletin);

        // retrieve pdf url store it in a string and load the string
        bulletin_pdf1 = FirebaseDatabase.getInstance().getReference().child("bulletin");
        bulletin_pdf1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String url1 = dataSnapshot.child("pdf1").getValue().toString();
                findViewById(R.id.download1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clicked_btn(url1);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void clicked_btn(String Url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(Url));
        startActivity(intent);
    }

}