package com.example.orderapp.display;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.orderapp.R;
import com.example.orderapp.adapter.GridViewAdapter;
import com.example.orderapp.adapter.TestAdapter;
import com.example.orderapp.database.DatabaseHelper;

import java.util.List;

public class DisplayFragment extends Fragment {

    private DisplayViewModel displayViewModel;
    public DatabaseHelper databaseHelper;
    private SQLiteDatabase mDb;
    Button submitCredentials;
    EditText userNameView;
    EditText passwordView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        displayViewModel =
                new ViewModelProvider(this).get(DisplayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_display_db, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        displayViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });




        return root;
    }

}