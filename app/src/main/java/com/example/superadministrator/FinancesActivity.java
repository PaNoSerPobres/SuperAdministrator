package com.example.superadministrator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FinancesActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Dialog popUpCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finances);

        if(user==null){
            goLoginScreen();
        }
        else
        {
            //region Menu
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setSelectedItemId(R.id.navigation_finances);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.navigation_finances:
                            startActivity(new Intent(getApplicationContext(), FinancesActivity.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.navigation_home:
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            overridePendingTransition(0,0);
                            return true;
                    }
                    return false;
                }
            });

            //endregion Menu

            //region ButtonAdd
            FloatingActionButton AddFinance = (FloatingActionButton)findViewById(R.id.AddFinance);
            AddFinance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ref = "ic_food";
                    String context = getPackageName() + ":drawable/" + ref;
                    int id = getResources().getIdentifier(context, null, null);

                }
            });
            //endregion ButtonAdd
        }

    }

    private void ShowCategoriesPopUp(View v)
    {
        popUpCategories.setContentView(R.layout.popup_categorias);
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
