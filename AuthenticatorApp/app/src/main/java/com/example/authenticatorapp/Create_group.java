package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Create_group extends AppCompatActivity {
    private EditText groupName,groupDescription;

    private Toolbar mToolbar;
    private Button createBtn;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        groupName = findViewById(R.id.group_name);
        groupDescription = findViewById(R.id.group_description);
        createBtn = findViewById(R.id.create_group_button);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gName = groupName.getText().toString();
                String gDesc = groupDescription.getText().toString();
                String gKey = myRef.push().getKey();

                myRef.child("Groups").child(gKey).child("group_name").setValue(gName);
                myRef.child("Groups").child(gKey).child("group_description").setValue(gDesc);

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();



            }
        });


        mToolbar = (Toolbar) findViewById(R.id.create_group_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Create Group");

    }





}