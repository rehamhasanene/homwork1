package com.example.homwork1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class pagshow extends AppCompatActivity {
    TextView phonename;
    TextView phonenumber;
    TextView phoneaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagshow);

        phonename = (TextView)findViewById(R.id.ph_name);
        phonenumber =(TextView) findViewById(R.id.ph_num);
        phoneaddress = (TextView)findViewById(R.id.ph_addre);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("phone")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String PhoneName = document.getString("PhoneName");
                                String PhoneNum = document.getString( "PhoneNum");
                                String PhoneAddress = document.getString( "PhoneAddress");
                                phonename.setText(PhoneName);
                                phonenumber.setText(PhoneNum);
                                phoneaddress.setText(PhoneAddress);
                            }
                        } else {
                            Log.d("TAG", "error  ", task.getException());
                        }
                    }
                });
    }
}