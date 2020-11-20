package com.example.authenticatorapp;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Member {
    String name;
    String key;
    FirebaseDatabase database;
    FirebaseAuth fAuth;

    public Member(){}

    public Member(String id) {
        fAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        this.key = key;

        database.getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dss) {
                name = dss.child(id).child("Name").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
