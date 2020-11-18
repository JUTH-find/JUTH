package com.example.authenticatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class group_join_activity extends AppCompatActivity {

    private String group_id,user_id,user_group_id;
    private TextView groupTextName,groupTextDesc;
    private Button joinGroupBtn;
    private DatabaseReference dataRef;
    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_join_activity);
        group_id = getIntent().getExtras().get("group_id").toString();
        groupTextName = (TextView) findViewById(R.id.group_intent_name);
        groupTextDesc = (TextView) findViewById(R.id.group_intent_desc);
        joinGroupBtn = (Button) findViewById(R.id.join_group_button);
        fAuth = FirebaseAuth.getInstance();
        user_id = fAuth.getCurrentUser().getUid();

        dataRef= FirebaseDatabase.getInstance().getReference();


        retrieveGroupInfo();


        if(group_id.equals(group_id)){

            joinGroupBtn.setEnabled(false);
            joinGroupBtn.setText(user_group_id);

        }


        joinGroupBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                joinGroup();
            }
        });


    }

    private void retrieveGroupInfo(){
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String groupName = snapshot.child("Groups").child(group_id).child("group_name").getValue().toString();
                String groupDesc = snapshot.child("Groups").child(group_id).child("group_description").getValue().toString();
                user_group_id = snapshot.child("Users").child(user_id).child("Group").getValue().toString();
                groupTextName.setText(groupName);
                groupTextDesc.setText(groupDesc);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void joinGroup(){

        dataRef.child("Users").child(user_id).child("Group").setValue(group_id);
        joinGroupBtn.setText("JOINED");
        joinGroupBtn.setEnabled(false);

    }

}