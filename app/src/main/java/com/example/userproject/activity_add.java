package com.example.userproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_add extends AppCompatActivity {

    EditText name,age,phone;
    Button add;
    int user_list_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name=(EditText) findViewById(R.id.editText_name);
        age=(EditText) findViewById(R.id.editText_age);
        phone=(EditText) findViewById(R.id.editText_phone);
        add=(Button) findViewById(R.id.button_addnew);
        user_list_size=getIntent().getIntExtra("user_list_size",0);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_user=name.getText().toString();
                String age_user=age.getText().toString();
                String phone_user=phone.getText().toString();
                if(name_user.replaceAll(" ", "").isEmpty()||age_user.replaceAll(" ", "").isEmpty()||phone_user.replaceAll(" ", "").isEmpty())
                {
                    Toast.makeText(activity_add.this,"Điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DatabaseReference db= FirebaseDatabase.getInstance().getReference();
                    db.child("user").child(String.valueOf(user_list_size)).setValue(new user(name_user,Integer.valueOf(age_user),phone_user));
                    Intent intent=new Intent(activity_add.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}