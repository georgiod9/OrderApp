package com.example.orderapp.models;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.orderapp.R;

public class ProfileFragment extends Fragment {
    private UserViewModel userViewModel;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        final NavController navController = Navigation.findNavController(view);

//        userViewModel.getViewLifecycleOwner(), (Observer<UserModel>) user -> {
//            if (user != null) {
//                showWelcomeMessage();
//            } else {
//                navController.navigate(R.id.navigation_session);
//            }
//        });
    }

    private void showWelcomeMessage() {

    }

}