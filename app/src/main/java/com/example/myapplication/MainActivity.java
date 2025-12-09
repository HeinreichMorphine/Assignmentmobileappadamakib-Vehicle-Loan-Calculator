package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText vehiclePrice, downPayment, interestRate, loanPeriod;
    private TextView loanAmountResult, totalInterestResult, totalPaymentResult, monthlyPaymentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        vehiclePrice = findViewById(R.id.vehiclePrice);
        downPayment = findViewById(R.id.downPayment);
        interestRate = findViewById(R.id.interestRate);
        loanPeriod = findViewById(R.id.loanPeriod);

        loanAmountResult = findViewById(R.id.loanAmountResult);
        totalInterestResult = findViewById(R.id.totalInterestResult);
        totalPaymentResult = findViewById(R.id.totalPaymentResult);
        monthlyPaymentResult = findViewById(R.id.monthlyPaymentResult);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(v -> calculateLoan());
    }

    private void calculateLoan() {
        vehiclePrice.setError(null);
        downPayment.setError(null);
        interestRate.setError(null);
        loanPeriod.setError(null);

        String priceStr = vehiclePrice.getText().toString();
        String downPaymentStr = downPayment.getText().toString();
        String rateStr = interestRate.getText().toString();
        String yearsStr = loanPeriod.getText().toString();

        boolean hasError = false;

        if (priceStr.isEmpty()) {
            vehiclePrice.setError("Field cannot be empty");
            hasError = true;
        }
        if (downPaymentStr.isEmpty()) {
            downPayment.setError("Field cannot be empty");
            hasError = true;
        }
        if (rateStr.isEmpty()) {
            interestRate.setError("Field cannot be empty");
            hasError = true;
        }
        if (yearsStr.isEmpty()) {
            loanPeriod.setError("Field cannot be empty");
            hasError = true;
        }

        if (hasError) {
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            double dp = Double.parseDouble(downPaymentStr);
            double rate = Double.parseDouble(rateStr);
            int years = Integer.parseInt(yearsStr);

            if (price <= 0) {
                Toast.makeText(this, "Price must be positive", Toast.LENGTH_SHORT).show();
                return;
            }
            if (dp < 0) {
                Toast.makeText(this, "Down payment cannot be negative", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dp > price) {
                Toast.makeText(this, "Down payment cannot be greater than the vehicle price", Toast.LENGTH_SHORT).show();
                return;
            }

            if (rate <= 0) {
                Toast.makeText(this, "Interest rate must be positive", Toast.LENGTH_SHORT).show();
                return;
            }

            if (years <= 0) {
                Toast.makeText(this, "Loan period must be positive", Toast.LENGTH_SHORT).show();
                return;
            }

            double loanAmount = price - dp;
            double totalInterest = loanAmount * (rate / 100) * years;
            double totalPayment = loanAmount + totalInterest;
            double monthlyPayment = totalPayment / (years * 12);

            DecimalFormat df = new DecimalFormat("#.00");

            loanAmountResult.setText("Loan Amount: RM" + df.format(loanAmount));
            totalInterestResult.setText("Total Interest: RM" + df.format(totalInterest));
            totalPaymentResult.setText("Total Payment: RM" + df.format(totalPayment));
            monthlyPaymentResult.setText("Monthly Payment: RM" + df.format(monthlyPayment));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (id == R.id.nav_home) {
            startActivity(new Intent(this, HomeActivity.class));
            return true;
        } else if (id == R.id.nav_calculation) {
            // Already here
            return true;
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
