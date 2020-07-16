package com.example.bills2.ui.reports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bills2.R;
import com.example.bills2.ui.home.HomeFragment;

public class ReportsFragment extends Fragment {
    private ImageButton button_home;

    private ReportsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ReportsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reports, container, false);
        ImageButton button_home=(ImageButton) root.findViewById(R.id.imageButton_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               HomeFragment homeFragment= new HomeFragment();
                FragmentManager fragmentManager= getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.reports_layout, homeFragment,homeFragment.getTag()).commit();



            }
        });
        final TextView textView = root.findViewById(R.id.text_reports);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}