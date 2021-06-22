package com.example.orderapp.ui.insert;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
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

public class InsertFragment extends Fragment implements View.OnClickListener {

    private InsertViewModel insertViewModel;

    Button addButton;
    EditText orderDateView;
    EditText amountView;
    EditText orderDescriptionView;
    DatabaseHelper databaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        insertViewModel =
                new ViewModelProvider(this).get(InsertViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_order, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        insertViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        addButton = (Button) root.findViewById(R.id.button_add);
        addButton.setOnClickListener((View.OnClickListener) this);

        databaseHelper = new DatabaseHelper(getContext());
        orderDateView = (EditText) root.findViewById(R.id.edit_order_date);
        amountView = (EditText) root.findViewById(R.id.edit_amount);
        orderDescriptionView = (EditText) root.findViewById(R.id.edit_description);

        return root;
    }


    @Override
    public void onClick(View v) {
        AlphaAnimation animation1 = new AlphaAnimation(0.1f, 1.0f);
        animation1.setDuration(50);
        animation1.setStartOffset(100);
        animation1.setFillAfter(true);

        switch(v.getId()){
            case R.id.button_add:
                v.startAnimation(animation1);
                String orderDate = orderDateView.getText().toString();
                String orderAmount = amountView.getText().toString();
                String orderDescription = orderDescriptionView.getText().toString();

                //insert user data into database
                databaseHelper.addEntry(orderDate, orderAmount, orderDescription);

                break;

            default:
                break;
        }
    }
}