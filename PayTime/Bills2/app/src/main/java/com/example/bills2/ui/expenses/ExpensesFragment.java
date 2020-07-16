package com.example.bills2.ui.expenses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bills2.CategoryAdapter;
import com.example.bills2.CategoryItems;
import com.example.bills2.R;
import com.example.bills2.ui.home.HomeFragment;

import java.util.ArrayList;

public class ExpensesFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private ImageButton button_home;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    private ExpensesViewModel expensesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        expensesViewModel =
                ViewModelProviders.of(this).get(ExpensesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_expenses, container, false);
        ImageButton button_home=(ImageButton) root.findViewById(R.id.imageButton_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment homeFragment= new HomeFragment();
                FragmentManager fragmentManager= getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.expenses_layout, homeFragment,homeFragment.getTag()).commit();



            }
        });
        final TextView textView = root.findViewById(R.id.text_expenses);
        expensesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });
        return root;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = view.findViewById(R.id.spinner1);
        ArrayList<CategoryItems> categoryList = new ArrayList<>();
        categoryList.add(new CategoryItems("Rent", R.drawable.ic_rent));
        categoryList.add(new CategoryItems("Medical", R.drawable.ic_medical));
        categoryList.add(new CategoryItems("Childcare", R.drawable.ic_childcare));
        categoryList.add(new CategoryItems("Transport", R.drawable.ic_transport));
        categoryList.add(new CategoryItems("Grocery", R.drawable.ic_grocery));
        categoryList.add(new CategoryItems("Petcare", R.drawable.ic_petcare));
        categoryList.add(new CategoryItems("Vehicle", R.drawable.ic_vehicle));
        categoryList.add(new CategoryItems("Travel", R.drawable.ic_travel));
        categoryList.add(new CategoryItems("Food", R.drawable.ic_food));
        categoryList.add(new CategoryItems("Leisure", R.drawable.ic_leisure));
        categoryList.add(new CategoryItems("Shopping", R.drawable.ic_shopping));
        categoryList.add(new CategoryItems("Fuel", R.drawable.ic_fuel));
        categoryList.add(new CategoryItems("Entertainment", R.drawable.ic_entertainment));
        categoryList.add(new CategoryItems("Rent", R.drawable.ic_rent));
        categoryList.add(new CategoryItems("Gifts", R.drawable.ic_gifts));
        categoryList.add(new CategoryItems("Fitness", R.drawable.ic_fitness));
        categoryList.add(new CategoryItems("Home Repair", R.drawable.ic_repair));
        categoryList.add(new CategoryItems("Other", R.drawable.ic_other));

        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), categoryList);
        if (spinner != null) {
            spinner.setAdapter(categoryAdapter);
            spinner.setOnItemSelectedListener(this);
        }


    /*    adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getActivity(), R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.Category));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
String text=parent.getItemAtPosition(position).toString();
//Toast.makeText(parent.getContext(), text + " selected ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }*/

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CategoryItems items = (CategoryItems) parent.getSelectedItem();
        //Toast.makeText(parent.getContext(), items.getSpinnerText(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

