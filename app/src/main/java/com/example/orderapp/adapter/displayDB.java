package com.example.orderapp.adapter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.GridView;

import com.example.orderapp.R;

import java.util.List;

public class displayDB extends AppCompatActivity {

    private GridView gridV;

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_display_db);

        getSupportActionBar().hide();

        gridV = (GridView) findViewById(R.id.GridView);

        TestAdapter dbAdapter = new TestAdapter(getApplicationContext());
        dbAdapter.createDatabase();
        dbAdapter.open();

        List<String> list = dbAdapter.getData();

        GridViewAdapter gridAdapter = new GridViewAdapter(getApplicationContext(), list);
        gridV.setAdapter(gridAdapter);

        dbAdapter.close();
    }
}