package com.example.orderapp.ui.orders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.orderapp.R;
import com.example.orderapp.database.DatabaseHelper;

public class OrdersFragment extends Fragment implements View.OnClickListener {

    private OrdersViewModel ordersViewModel;
    public DatabaseHelper databaseHelper;
    private SQLiteDatabase mDb;

    Button submitCredentials;
    EditText userNameView;
    EditText passwordView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ordersViewModel =
                new ViewModelProvider(this).get(OrdersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        ordersViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

//        submitCredentials = (Button)root.findViewById(R.id.button_submit);
//        submitCredentials.setOnClickListener((View.OnClickListener) this);

        databaseHelper = new DatabaseHelper(getContext());

//        userNameView = (EditText) getActivity().findViewById(R.id.editText_username);
//        userNameView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus){
//                    hideKeyboard(v);
//                }
//            }
//        });
//
//        passwordView = (EditText)getActivity().findViewById(R.id.editText_password);
//        passwordView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus){
//                    hideKeyboard(v);
//                }
//            }
//        });
        return root;
    }

    @Override
    public void onClick(View v) {
//        AlphaAnimation animation1 = new AlphaAnimation(0.1f, 1.0f);
//        animation1.setDuration(50);
//        animation1.setStartOffset(100);
//        animation1.setFillAfter(true);
//
//        switch(v.getId()){
//            case R.id.button_submit:
//                v.startAnimation(animation1);
//                String username = userNameView.getText().toString();
//                String password = passwordView.getText().toString();
//
//                boolean userModel = databaseHelper.getUser(username, password);
//                Snackbar make = Snackbar.make(getActivity().findViewById(android.R.id.content), "User does not exist", BaseTransientBottomBar.LENGTH_LONG);
//                Snackbar make2 = Snackbar.make(getActivity().findViewById(android.R.id.content), "User exists", BaseTransientBottomBar.LENGTH_LONG);
//
//                if (userModel == false){
//                    make.show();
//                    make2.dismiss();
//                    Log.i("ERROR", "User does not exist");
//                }
//                else {
//                    Log.i("ERROR", "User exists");
//                    make.dismiss();
//                    make2.show();
//                }
//                break;
//
////            case R.id.button_openDB:
////                Intent displayDB_Content = new Intent(v.getContext(), displayDB.class);
////                startActivity(displayDB_Content);
////                break;
//
//            default:
//                break;
//        }

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}