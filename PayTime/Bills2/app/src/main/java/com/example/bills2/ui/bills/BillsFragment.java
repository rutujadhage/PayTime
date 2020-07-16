package com.example.bills2.ui.bills;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bills2.R;
import com.example.bills2.TaskHome;

public class BillsFragment extends Fragment {
    private BillsViewModel billsViewModel;
    private Button button_bills;

    public BillsFragment() {
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        billsViewModel =
                ViewModelProviders.of(this).get(BillsViewModel.class);
        View view= inflater.inflate(R.layout.fragment_bills, container, false);
       //final Button button_bills= (Button) view.findViewById(R.id.button_bills);
           Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(BillsFragment.this.getActivity(), TaskHome.class);
                startActivity(intent);
                getActivity().finish();

            }
        }, 0000);
        final TextView textView = view.findViewById(R.id.text_bills);
      /* button_bills.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), TaskHome.class);
               startActivity(intent);
           }
       });
        final TextView textView = view.findViewById(R.id.text_bills);
       /* Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(BillsFragment.this.getActivity(), NewBills.class);
                startActivity(intent);
                getActivity().finish();

            }
        }, 1500);*/
        billsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);


            }
        });
        return view;

    }
}
