package com.example.bills2;

public class CategoryItems {
    private  String spinnerText;
    private  int spinnerImage;

    public CategoryItems(String spinnerText, int spinnerImage){
        this.spinnerImage=spinnerImage;
        this.spinnerText=spinnerText;
    }

    public String getSpinnerText(){
        return spinnerText;
    }
    public int getSpinnerImage(){
        return spinnerImage;
    }

}
