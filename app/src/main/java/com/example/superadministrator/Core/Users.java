package com.example.superadministrator.Core;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.superadministrator.Class.Entities.Accounts;
import com.example.superadministrator.Class.Entities.Categories;
import com.example.superadministrator.Class.Entities.Expenses;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Users {

    public Users(){}

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void createAccount(Accounts account,String UID){
        Accounts accounts = new Accounts("name","type",10000f/*balance*/);
        db.collection("Users").document(UID).collection("Accounts")
                .add(account)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                });
    }

    public void createExpense(Expenses expense, String UID, String AID){
        Expenses expenses = new Expenses("category","iconId",560.5f/*amount*/,true/*recurrent*/,1/*timeLapse*/);
        Map<String,Object> expenseArg = new HashMap<>();
        expenseArg.put("TimeStamp", FieldValue.serverTimestamp());
        expenseArg.put("Category",expenses.getCategory());
        expenseArg.put("Icon",expenses.getIcon());
        expenseArg.put("Amount",expense.getAmount());
        expenseArg.put("Recurrent",expense.isRecurrent());
        expenseArg.put("TimeLapse",expense.getTimeLapse());

        db.collection("Users").document(UID).collection("Accounts").document(AID).collection("Expenses")
                .add(expenseArg)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                });
    }

    public void createIncome() {

    }

}
