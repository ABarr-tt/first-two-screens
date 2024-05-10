package com.example.barrettsbudgettracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Transaction> transaction;
    private Adapter adapter;
    private RecyclerView recyclerView;
    private TextView balanceTextView;
    private TextView budgetTextView;
    private TextView expenseTextView;
    private FloatingActionButton addBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views from XML
        recyclerView = findViewById(R.id.recyclerView);
        balanceTextView = findViewById(R.id.balance);
        budgetTextView = findViewById(R.id.budget);
        expenseTextView = findViewById(R.id.expenses);
        addBtn = findViewById(R.id.addBtn); // Initialize addBtn

        transaction = new ArrayList<>();
        transaction.add(new Transaction("Weekly budget", 500.00));
        transaction.add(new Transaction("Coffee", -20.00));
        transaction.add(new Transaction("Gas", -45.00));
        transaction.add(new Transaction("Parking", -60.00));
        transaction.add(new Transaction("Lunch", -80.00));
        transaction.add(new Transaction("Snacks", -10.00));
        transaction.add(new Transaction("Art supplies", -30.00));
        transaction.add(new Transaction("Clothes", -50.00));
        transaction.add(new Transaction("Date", -50.00));

        adapter = new Adapter(transaction);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateDashboard();

        addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
            startActivity(intent);
        });
    }

    private void updateDashboard() {
        double totalAmount = 0;
        double budgetAmount = 0;
        for (Transaction t : transaction) {
            totalAmount += t.getAmount();
            if (t.getAmount() > 0) {
                budgetAmount += t.getAmount();
            }
        }

        double expenseAmount = totalAmount - budgetAmount;

        balanceTextView.setText(String.format("$%.2f", totalAmount));
        budgetTextView.setText(String.format("$%.2f", budgetAmount));
        expenseTextView.setText(String.format("$%.2f", expenseAmount));
    }
}


