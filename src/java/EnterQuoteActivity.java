package com.example.randomquotesgeneratorcollector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EnterQuoteActivity extends AppCompatActivity implements View.OnClickListener {

    protected EditText editTextQuoteEntered;
    protected Button btnSubmitQuote;
    protected String quote = "";
    protected Intent GoToStorage;
    protected Button btnBack;
    protected Spinner spinnerDropDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_quote);

        GoToStorage = new Intent(EnterQuoteActivity.this, StorageActivity.class);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        editTextQuoteEntered = findViewById(R.id.et_quoteEnter);
        btnSubmitQuote = findViewById(R.id.btn_take_quote);
        btnBack = findViewById(R.id.btn_backMainMenu);
        spinnerDropDown = findViewById(R.id.spinner_drop_down);

        // Setting Up The Spinner Drop Down Menu
        String[] items = {"Yes", "No"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerDropDown.setAdapter(adapter);


        btnSubmitQuote.setOnClickListener(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });

    }

    @Override
    public void onClick(View view) {

        quote = editTextQuoteEntered.getText().toString();
        String str_Quote = "\"" + quote + "\"";
        if (quote != null && (!quote.equals(""))){
            // Saving The Quote
            GoToStorage.putExtra("QUOTES","" + String.valueOf(str_Quote));
            String spinnerSelctedItem = spinnerDropDown.getSelectedItem().toString();
            if (spinnerSelctedItem.equals("Yes")){
                GoToStorage.putExtra("WHERE_TO", "ReturnEnterQuote");
                startActivity(GoToStorage);
                finish();
            }else if (spinnerSelctedItem.equals("No")){
                GoToStorage.putExtra("WHERE_TO", "Yes");
                startActivity(GoToStorage);
                finish();
            }
        }else {
            Toast.makeText(EnterQuoteActivity.this, "Please Enter A Quote Sir / Ma'am", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToMainActivity() {
        Intent goToBack = new Intent(EnterQuoteActivity.this, MainActivity.class);
        goToBack.putExtra("BOOLEAN_STATE", "true");
        startActivity(goToBack);
        finish();
    }

}
