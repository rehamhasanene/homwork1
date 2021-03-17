package com.example.homwork1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextNamber;
    EditText editTextAddres;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName=findViewById(R.id.phonName);
        editTextNamber=findViewById(R.id.phonNamber);
        editTextAddres=findViewById(R.id.phonAddres);

    }
    public void saveToFirebase(View v){
        String TextName=editTextName.getText().toString();
        String TextNamber=editTextNamber.getText().toString();
        String TextAddres=editTextAddres.getText().toString();

        Map<String, Object> phone = new HashMap<>();
        phone.put("phon_Name", TextName);
        phone.put("phon_Namber", TextNamber);
        phone.put("phon_Addres", TextAddres);

// Add a new document with a generated ID
        db.collection("phone")
                .add(phone)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });

    }
}