package com.example.pr20_lyapynova_pr_21_101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<User> listTemp;

    private DatabaseReference myDataBase;
    private String USER_KEY = "User";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        listView = findViewById(R.id.listView);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        myDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        getDataFromDB();
        setOnClickItem();
    }
    private void getDataFromDB(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(listData.size() > 0)listData.clear();
                if(listTemp.size() > 0)listTemp.clear();
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    User user = ds.getValue(User.class);
                    assert user != null;
                    listData.add(user.name);
                    listTemp.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myDataBase.addValueEventListener(vListener);

    }
    private void setOnClickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = listTemp.get(position);
                Intent i = new Intent(ReadActivity.this, ShowActivity.class);
                i.putExtra(Constant.USER_NAME,user.name);
                i.putExtra(Constant.USER_S_NAME,user.sec_name);
                i.putExtra(Constant.USER_EMAIL,user.email);
                startActivity(i);

            }
        });

    }
}