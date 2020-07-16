package com.example.bills2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LogOut extends AppCompatActivity {
    private Button btn_sign_out;
    private Button btn_go_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);
        btn_go_back = (Button) findViewById(R.id.button_return);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogOut.this, Main2ActivityNav.class);
                startActivity(intent);


            }
        });
        btn_sign_out = (Button) findViewById(R.id.button_logout);
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogOut.this,LoginActivity.class);
                startActivity(intent);


            }
        });

    }
}
