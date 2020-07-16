package com.example.bills2.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bills2.LoginActivity;
import com.example.bills2.R;
import com.example.bills2.ui.bills.BillsFragment;
import com.example.bills2.ui.expenses.ExpensesFragment;
import com.example.bills2.ui.reports.ReportsFragment;

public class HomeFragment extends Fragment {
    private ImageButton btn_bills;
    private ImageButton btn_expenses;
    private ImageButton btn_reports;
    private ImageButton btn_sign_out;
    private TextView textView_reports;
    private TextView textView_bills;
    private TextView textView_expenses;
private long backpressedtime;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TextView textView_reports= (TextView)root.findViewById(R.id.text_reports);
        ImageButton btn_bills=(ImageButton) root.findViewById(R.id.imageButton_bills);
        btn_bills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_layout, new BillsFragment());
                fragmentTransaction.addToBackStack(null).commit();
               /* BillsFragment billsFragment=new BillsFragment();
                FragmentManager fragmentManager= getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.home_layout, billsFragment, billsFragment.getTag()).commit();*/



            }
        });
     /*  btn_bills.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                       Intent intent = new Intent(HomeFragment.this.getActivity(), MainActivity.class);
                       startActivity(intent);


                   }
               });*/

        ImageButton btn_reports=(ImageButton) root.findViewById(R.id.imageButton_reports);
        btn_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_layout, new ReportsFragment());
                fragmentTransaction.addToBackStack(null).commit();
           /*   ReportsFragment reportsFragment=new ReportsFragment();
                FragmentManager fragmentManager= getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.home_layout, reportsFragment, reportsFragment.getTag()).commit();*/



            }
        });
        ImageButton btn_expenses=(ImageButton) root.findViewById(R.id.imageButton_expenses);
        btn_expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_layout, new ExpensesFragment());
                fragmentTransaction.addToBackStack(null).commit();
               /* ExpensesFragment expensesFragment= new ExpensesFragment();
                FragmentManager fragmentManager= getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.home_layout, expensesFragment, expensesFragment.getTag()).commit();*/



            }
        });
        
        ImageButton btn_sign_out=(ImageButton) root.findViewById(R.id.imageButton_sign_out);
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder= new AlertDialog.Builder(HomeFragment.this.getActivity());
                builder.setMessage("Do you want to sign out?");
                builder.setCancelable(true);
                builder.setNegativeButton("Sign Out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        getActivity().startActivity(intent);

                    }
                });
                builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();



                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();


            }
        });


        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;



    }


    }
