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

import java.util.ArrayList;

public class activity_detail extends AppCompatActivity {

    EditText name,phone,age;
    Button update,delete;
    ArrayList<user> user_list_data;
    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        name=(EditText) findViewById(R.id.editText_name1);
        age=(EditText) findViewById(R.id.editText_age1);
        phone=(EditText) findViewById(R.id.editText_phone1);
        update=(Button) findViewById(R.id.button_update);
        delete=(Button) findViewById(R.id.button_delete);
        user_list_data= (ArrayList<user>) getIntent().getSerializableExtra("user_list_data");
        user_id=getIntent().getIntExtra("user_id",0);
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();

        name.setText(user_list_data.get(user_id).getName());
        age.setText(String.valueOf(user_list_data.get(user_id).getAge()) );
        phone.setText(user_list_data.get(user_id).getPhone());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_list_data.remove(user_id);
                db.child("user").setValue(user_list_data);
                Intent intent=new Intent(activity_detail.this,MainActivity.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name_user=name.getText().toString();
                String age_user=age.getText().toString();
                String phone_user=phone.getText().toString();
                if(name_user.replaceAll(" ", "").isEmpty()||age_user.replaceAll(" ", "").isEmpty()||phone_user.replaceAll(" ", "").isEmpty())
                {
                    Toast.makeText(activity_detail.this,"Điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    user_list_data.get(user_id).setName(name_user);
                    user_list_data.get(user_id).setAge(Integer.valueOf(age_user));
                    user_list_data.get(user_id).setPhone(phone_user);
                    DatabaseReference db= FirebaseDatabase.getInstance().getReference();
                    db.child("user").setValue(user_list_data);
                    Intent intent=new Intent(activity_detail.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}