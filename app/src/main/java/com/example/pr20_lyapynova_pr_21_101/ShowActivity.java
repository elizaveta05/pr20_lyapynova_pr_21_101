package com.example.pr20_lyapynova_pr_21_101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
    private TextView tvName, tvSName, tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        tvName = findViewById(R.id.tvName);
        tvSName = findViewById(R.id.tvSName);
        tvEmail = findViewById(R.id.tvEmail);
        getIntentMain();
    }

    private void getIntentMain()
    {
        Intent i = getIntent();
        if(i != null)
        {
            tvName.setText(i.getStringExtra(Constant.USER_NAME));
            tvSName.setText(i.getStringExtra(Constant.USER_S_NAME));
            tvEmail.setText(i.getStringExtra(Constant.USER_EMAIL));
        }
    }
}