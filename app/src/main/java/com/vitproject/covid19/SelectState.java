package com.vitproject.covid19;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SelectState extends AppCompatActivity implements MyAdapter.OnNoteListener {

    RecyclerView rv;
    String st[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_state);
        rv = findViewById(R.id.rv);
        st = getResources().getStringArray(R.array.states);

        MyAdapter myAdapter = new MyAdapter(this,st,this);
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked"+position);
        if(position==0){
            Intent intent = new Intent(this, ApHome.class);
            startActivity(intent);
        }
        if(position==1){
            Intent intent = new Intent(this, TnHome.class);
            startActivity(intent);
        }
    }
}