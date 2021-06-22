package com.example.orderapp.ui.dashboard;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private GridView gridV;

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_display_db, container, false);

        gridV = (GridView) root.findViewById(R.id.GridView);

        TestAdapter dbAdapter = new TestAdapter(getContext());
        dbAdapter.createDatabase();
        dbAdapter.open();

        List<String> list = dbAdapter.getData();

        GridViewAdapter gridAdapter = new GridViewAdapter(getContext(), list);
        gridV.setAdapter(gridAdapter);

        dbAdapter.close();

        return root;
    }
}