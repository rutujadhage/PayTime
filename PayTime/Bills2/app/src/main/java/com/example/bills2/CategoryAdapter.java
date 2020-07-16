package com.example.bills2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter <CategoryItems>{
    public CategoryAdapter(@NonNull Context context, ArrayList<CategoryItems> categoryList) {
        super(context, 0, categoryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return categoryView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return categoryView(position, convertView, parent);
    }

    public  View categoryView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.category_spinner_layout, parent, false);
        }
        CategoryItems items=getItem(position);
        ImageView spinnerImage=convertView.findViewById(R.id.ivCategorySpinner);
        TextView spinnerName=convertView.findViewById(R.id.tvCategorySpinner);
        if(items!=null)
        {
            spinnerImage.setImageResource(items.getSpinnerImage());
            spinnerName.setText(items.getSpinnerText());
        }
        return convertView;
    }

}

