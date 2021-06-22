package com.example.orderapp.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.orderapp.R;
import com.example.orderapp.adapter.displayDB;
import com.example.orderapp.database.DatabaseHelper;
import com.example.orderapp.models.UserModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private LoginViewModel loginViewModel;
    public DatabaseHelper databaseHelper;
    private SQLiteDatabase mDb;
    Button submitCredentials;
    EditText userNameView;
    EditText passwordView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        loginViewModel =
                new ViewModelProvider(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        final TextView textView = root.findViewById(R.id.text_login);
        loginViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //create a DatabaseHelper object to access our database
        databaseHelper = new DatabaseHelper(getContext());

        //define xml design components
        Button create_usr_btn = (Button)root.findViewById(R.id.button_submit);
        create_usr_btn.setOnClickListener((View.OnClickListener) this);

        submitCredentials = (Button)root.findViewById(R.id.button_submit);
        submitCredentials.setOnClickListener((View.OnClickListener) this);

        userNameView = (EditText) root.findViewById(R.id.editText_username);
        passwordView = (EditText)root.findViewById(R.id.editText_password);

        return root;
    }

    @Override
    public void onClick(View v) {
        //animate button when clicked
        AlphaAnimation animation1 = new AlphaAnimation(0.1f, 1.0f);
        animation1.setDuration(50);
        animation1.setStartOffset(100);
        animation1.setFillAfter(true);


        switch(v.getId()){
            case R.id.button_submit:
                v.startAnimation(animation1);
                String username = userNameView.getText().toString();
                String password = passwordView.getText().toString();

                //compare user input with database
                if (databaseHelper.userExists(username, password)){
                    Log.i("ERROR", "User exists");
                    UserModel userModel = databaseHelper.getUser(username, password);
                    if (userModel != null){
                        Log.i("ERROR", "Login Successful");
                        Navigation.findNavController(v).navigate(R.id.navigation_home);

                    }
                    else {
                        Log.i("ERROR", "Incorrect credentials");
                    }
                }
                else {
                    Log.i("ERROR", "User does not exist");
                }


                break;

            default:
                break;
        }
    }

}