package com.example.superadministrator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.superadministrator.Class.Entities.*;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 777;
    List<AuthUI.IdpConfig> providers;
    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String TAG = "Login Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build()
        );
        showSignInOptions();
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.mipmap.logo)
                        .setTheme(R.style.LoginTheme)
                        .build(), RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                user = FirebaseAuth.getInstance().getCurrentUser();
                userExist();
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                Toast.makeText(this, R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void userExist() {
        DocumentReference docRef = db.collection("Users").document(user.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        goMainScreen();
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        createProfileUser();
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

    private void createProfileUser() {
        String name = user.getDisplayName();
        String email = user.getEmail();
        String image = user.getPhotoUrl().toString();
        List<String> friends = Arrays.asList();
        Users ProfileData = new Users(name,email,image,friends);
        final List<Categories> lstDefaultCategories = assignCategories();


        db.collection("Users").document(user.getUid())
                .set(ProfileData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        for ( Categories c : lstDefaultCategories){
                            Map<String, Object> category = new HashMap<>();
                            Map<String, Object> info = new HashMap<>();
                            info.put("IsExpense", c.isExpense());
                            info.put("Image", c.getImage());
                            category.put(c.getType(),info);
                            db.collection("Users").document(user.getUid()).collection("Categories").document("DefaultCategories")
                                    .set(category, SetOptions.merge());
                        }
                        goMainScreen();
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }

    private List<Categories> assignCategories() {
        List<Categories> defaultCategories = new ArrayList<>();
        Categories food = new Categories("Food","ic_food", true);
        defaultCategories.add(food);
        Categories transport = new Categories("Transport","ic_transport", true);
        defaultCategories.add(transport);
        Categories bank = new Categories("Bank","ic_bank", true);
        defaultCategories.add(bank);
        Categories cash = new Categories("Cash","ic_cash", false);
        defaultCategories.add(cash);
        Categories clothes = new Categories("Clothes","ic_clothes", true);
        defaultCategories.add(clothes);
        Categories creditcard = new Categories("Credit Card","ic_creditcard", false);
        defaultCategories.add(creditcard);
        Categories hobbies = new Categories("Hobbies","ic_hobbies", true);
        defaultCategories.add(hobbies);
        Categories home = new Categories("Home","ic_home", true);
        defaultCategories.add(home);
        Categories saves = new Categories("Saves","ic_saves", false);
        defaultCategories.add(saves);
        Categories payroll = new Categories("Payroll","ic_payroll", false);
        defaultCategories.add(payroll);
        Categories shopping = new Categories("Shopping","ic_shopping", true);
        defaultCategories.add(shopping);
        Categories freetime = new Categories("Free Time","ic_freetime", true);
        defaultCategories.add(freetime);

        return defaultCategories;
    }

    private void goMainScreen() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
