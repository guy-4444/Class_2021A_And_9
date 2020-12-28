package com.classy.class_2021a_and_9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Activity_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


        if (firebaseUser == null) {
            Intent myIntent = new Intent(this, Activity_Login.class);
            startActivity(myIntent);
            finish();
            return;
        }

        Log.d("pttt", "Uid = " + firebaseUser.getUid());
        Log.d("pttt", "DisplayName = " + firebaseUser.getDisplayName());
        Log.d("pttt", "Email = " + firebaseUser.getEmail());
        Log.d("pttt", "PhoneNumber = " + firebaseUser.getPhoneNumber());
        Log.d("pttt", "PhotoUrl = " + firebaseUser.getPhotoUrl());

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Aliyahu Cohen")
                .build();

        firebaseUser.updateProfile(profileUpdates);
        firebaseAuth.updateCurrentUser(firebaseUser);
    }
}