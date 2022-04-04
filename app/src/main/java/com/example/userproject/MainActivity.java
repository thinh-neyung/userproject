package com.example.userproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView user_list_view;
    ArrayList<user> user_list_data;
    adapter_user adapter_user;
    DatabaseReference data;
    Button add_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_list_view=(RecyclerView) findViewById(R.id.user_list);
        data= FirebaseDatabase.getInstance().getReference();
        add_user=(Button)findViewById(R.id.button_add);

        data.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user_list_data = new ArrayList<>();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    user user=postSnapshot.getValue(com.example.userproject.user.class);
                    user_list_data.add(user);
                }
                adapter_user=new adapter_user(user_list_data,MainActivity.this);
                user_list_view.setAdapter(adapter_user);
                user_list_view.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                add_user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(MainActivity.this,activity_add.class);
                        intent.putExtra("user_list_size",user_list_data.size());
                        startActivity(intent);
                    }
                });
                ItemClickSupport.addTo(user_list_view).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(MainActivity.this,activity_detail.class);
                        intent.putExtra("user_list_data", user_list_data);
                        intent.putExtra("user_id",position);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//        ItemClickSupport.addTo(user_list_view).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//            @Override
//            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                Intent intent=new Intent(MainActivity.this,profile_activity.class);
//                startActivity(intent);
//            }
//        });
    }
}