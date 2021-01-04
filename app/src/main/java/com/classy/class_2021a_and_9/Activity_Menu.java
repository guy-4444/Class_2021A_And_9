package com.classy.class_2021a_and_9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Activity_Menu extends AppCompatActivity {

    private MaterialButton menu_BTN_updateUser;
    private MaterialButton menu_BTN_updateOmami;
    private MaterialButton menu_BTN_updateSunset;
    private MaterialButton menu_BTN_updateCalifornia;

    private HashMap<String, Sushi> sushiMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        validateUser();
        findViews();
        initViews();
        loadSushi();


        readUserData();
        readSushiData("S0002");
    }

    private void readSushiData(String sushiKey) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sushi");

        myRef.child(sushiKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Sushi s = dataSnapshot.getValue(Sushi.class);
                Log.d("pttt", "Value is: " + s.getName() + ": " + s.getPrice());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("pttt", "Failed to read value.", error.toException());
            }
        });
    }

    private void readUserData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        String uid = "kkkk2222";
        myRef.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User value = dataSnapshot.getValue(User.class);
                Log.d("pttt", "Value is: " + value.getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("pttt", "Failed to read value.", error.toException());
            }
        });
    }

    private void loadSushi() {

        Sushi s1 = new Sushi()
                .setKey("S0001")
                .setName("Omami")
                .setNumOfPieces(8)
                .setPrice(14.0)
                .setVegan(false);
        s1.getIngredients().add("Salmon");
        s1.getIngredients().add("Yam");
        s1.getIngredients().add("Broccoli");

        Sushi s2 = new Sushi()
                .setKey("S0002")
                .setName("Sunset")
                .setNumOfPieces(8)
                .setPrice(34.0)
                .setVegan(false);
        s2.getIngredients().add("Tuna");
        s2.getIngredients().add("Broccoli");
        s2.getIngredients().add("Cucumber");

        Sushi s3 = new Sushi()
                .setKey("S0003")
                .setName("California")
                .setNumOfPieces(8)
                .setPrice(44.0)
                .setVegan(false);
        s3.getIngredients().add("Salmon");
        s3.getIngredients().add("Yam");
        s3.getIngredients().add("Chives");

        sushiMap.put(s1.getKey(), s1);
        sushiMap.put(s2.getKey(), s2);
        sushiMap.put(s3.getKey(), s3);
    }

    private void updateUser(Sushi sushi) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();
        String phone = firebaseUser.getPhoneNumber();
        String name = "Ido Zada";

        User ido = new User()
                .setUid(uid)
                .setPhone(firebaseUser.getPhoneNumber())
                .setName(name)
                .setFavoriteSushi(sushi.getKey());


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        myRef.child(ido.getUid()).setValue(ido);
    }

    private void uploadSushiToServer(Sushi sushi) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sushi");
        myRef.child(sushi.getKey()).setValue(sushi);
    }

    private void updateCalifornia() {
        uploadSushiToServer(sushiMap.get("S0001"));
    }

    private void updateSunset() {
        uploadSushiToServer(sushiMap.get("S0002"));
    }

    private void updateOmami() {
        uploadSushiToServer(sushiMap.get("S0003"));
    }

    private void validateUser() {
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
    }

    private void updateDisplayName(String name) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
        firebaseUser.updateProfile(profileUpdates);
        firebaseAuth.updateCurrentUser(firebaseUser);
    }

    private void initViews() {
        menu_BTN_updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sushi sushi = sushiMap.get("S0002");
                updateUser(sushi);
            }
        });

        menu_BTN_updateOmami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOmami();
            }
        });

        menu_BTN_updateSunset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSunset();
            }
        });

        menu_BTN_updateCalifornia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCalifornia();
            }
        });
    }

    private void findViews() {
        menu_BTN_updateUser = findViewById(R.id.menu_BTN_updateUser);
        menu_BTN_updateOmami = findViewById(R.id.menu_BTN_updateOmami);
        menu_BTN_updateSunset = findViewById(R.id.menu_BTN_updateSunset);
        menu_BTN_updateCalifornia = findViewById(R.id.menu_BTN_updateCalifornia);
    }
}