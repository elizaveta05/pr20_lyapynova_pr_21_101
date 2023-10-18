package com.example.pr20_lyapynova_pr_21_101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edName, edSName, edMail;
    Button btnSave, btnRead;
    private DatabaseReference myDataBase;
    final String USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edMail);
        edSName = findViewById(R.id.edSName);
        edMail = findViewById(R.id.edMail);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        btnRead = findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        myDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                String id = myDataBase.getKey();
                String name = edName.getText().toString();
                String sec_name = edSName.getText().toString();
                String email = edMail.getText().toString();
                User newUser = new User(id,name,sec_name,email);
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sec_name) && !TextUtils.isEmpty(email) )
                {
                    myDataBase.push().setValue(newUser);
                    Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
                }
            case R.id.btnRead:
                Intent i = new Intent(MainActivity.this, ReadActivity.class);
                startActivity(i);




        }
    }
}