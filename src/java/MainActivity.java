package com.example.randomquotesgeneratorcollector;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    protected Vector<String> quotes = new Vector<>();
    protected TextView textViewQuote, textViewTitle, textViewTitle2, textViewTitle3;
    protected View view;
    protected EditText editTextRandomName;
    protected Button btn_background;
    protected Button btn_random_action, btn_name_enter;
    protected Button btn_enter_quote, btn_toast_quote, btn_bottom_quote, btn_all_quotes;
    protected Button btn_reset_alpha;
    protected Switch btnSwitch;
    protected String[] colorsTable = {"Black", "White","Red","Lime","Blue","Yellow","Cyan","Fuchsia",
                                        "Silver","Gray","Maroon","Olive","Green","Purple","Teal","Gold",
                                        "Coral","Khaki","Turquoise","Pink","Lavender","Honeydew","SteelBlue"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = this.getWindow().getDecorView();
        //Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show();

        textViewQuote = findViewById(R.id.editText_showQuote);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewTitle2 = findViewById(R.id.textViewTitle2);
        textViewTitle3 = findViewById(R.id.textView_generateQuote);
        btn_background = findViewById(R.id.btn_random_background);
        btn_random_action = findViewById(R.id.btn_do_random_something);
        btn_enter_quote = findViewById(R.id.btn_enterQuote);
        btn_toast_quote = findViewById(R.id.btn_generateToastQuote);
        btn_bottom_quote = findViewById(R.id.btn_generateBottomQuote);
        btn_all_quotes = findViewById(R.id.btn_showAllQuotes);
        btn_reset_alpha = findViewById(R.id.btn_reset_alpha);
        btn_name_enter = findViewById(R.id.btn_random_enter_name);
        editTextRandomName = findViewById(R.id.editText_random_name_enter);
        btnSwitch = findViewById(R.id.btnSwitch);

        btn_name_enter.setEnabled(false);
        btn_name_enter.setAlpha(0.0f);
        editTextRandomName.setEnabled(false);
        editTextRandomName.setAlpha(0.0f);

        btn_reset_alpha.setAlpha(0.0f);

        view.setBackgroundResource(R.color.DefaultColor);

        btn_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Size is: " + colorsTable.length, Toast.LENGTH_SHORT).show();
                generateRandomBackground();
            }
        });

        // Helping The Generating
        String bottomQuote = getIntent().getStringExtra("QUOTE_TO_SHOW");
        if (bottomQuote != null){
            textViewQuote.setText(bottomQuote);
        }

        btn_name_enter.setOnClickListener(this);

        // Setting the random function
        btn_random_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int random = rand.nextInt(8);
                switch (random){
                    case 0: // generateRandomBackground
                        generateRandomBackground();
                        btn_name_enter.setAlpha(0.0f);
                        editTextRandomName.setAlpha(0.0f);
                        btn_name_enter.setEnabled(false);
                        editTextRandomName.setEnabled(false);
                        break;
                    case 1: // enterQuote
                        Intent goToEnterQuote = new Intent(MainActivity.this, EnterQuoteActivity.class);
                        startActivity(goToEnterQuote);
                        finish();
                        break;
                    case 2: // generateQuote - Toast
                        Intent goToNextToastQuote = new Intent(MainActivity.this, StorageActivity.class);
                        goToNextToastQuote.putExtra("WHERE_TO","GenerateRandom");
                        startActivity(goToNextToastQuote);
                        finish();
                        break;
                    case 3: // generateQuote - Bottom
                        Intent goToNextBottomQuote = new Intent(MainActivity.this, StorageActivity.class);
                        goToNextBottomQuote.putExtra("WHERE_TO","BOTTOM_QUOTE");
                        startActivity(goToNextBottomQuote);
                        finish();
                        break;
                    case 4: // showAllQuotes
                        Intent goToNextCheck = new Intent(MainActivity.this, StorageActivity.class);
                        goToNextCheck.putExtra("WHERE_TO","No");
                        startActivity(goToNextCheck);
                        finish();
                        break;
                    case 5: // Lidor Compliment And Worship demand
                        Toast.makeText(MainActivity.this, "Lidor Is The Best App Developer Ever!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "You Should Worship Him!", Toast.LENGTH_SHORT).show();
                        btn_name_enter.setAlpha(0.0f);
                        btn_name_enter.setEnabled(false);
                        editTextRandomName.setAlpha(0.0f);
                        editTextRandomName.setEnabled(false);
                        break;
                    case 6: // disappear
                        Random rand2 = new Random();
                        int random2 = rand.nextInt(6);
                        switch (random2){
                            case 0:
                                Toast.makeText(MainActivity.this, "#Poof!", Toast.LENGTH_SHORT).show();
                                view.setAlpha(0.0f);
                                btn_random_action.setEnabled(false);
                                textViewQuote.setAlpha(0.0f);
                                btn_name_enter.setAlpha(0.0f);
                                btn_name_enter.setEnabled(false);
                                editTextRandomName.setAlpha(0.0f);
                                editTextRandomName.setEnabled(false);
                                break;
                            case 1:
                                Toast.makeText(MainActivity.this, "#Poof!", Toast.LENGTH_SHORT).show();
                                textViewQuote.setAlpha(0.0f);
                                btn_background.setAlpha(0.0f);
                                btn_background.setEnabled(false);
                                btnSwitch.setAlpha(0.0f);
                                btnSwitch.setEnabled(false);
                                btn_name_enter.setAlpha(0.0f);
                                btn_name_enter.setEnabled(false);
                                editTextRandomName.setAlpha(0.0f);
                                editTextRandomName.setEnabled(false);
                                break;
                            case 2:
                                Toast.makeText(MainActivity.this, "#Poof!", Toast.LENGTH_SHORT).show();
                                btn_all_quotes.setAlpha(0.0f);
                                btn_all_quotes.setEnabled(false);
                                btn_enter_quote.setAlpha(0.0f);
                                btn_enter_quote.setEnabled(false);
                                textViewTitle.setAlpha(0.0f);
                                textViewQuote.setAlpha(0.0f);
                                btn_name_enter.setAlpha(0.0f);
                                btn_name_enter.setEnabled(false);
                                editTextRandomName.setAlpha(0.0f);
                                editTextRandomName.setEnabled(false);
                                break;
                            case 3:
                                Toast.makeText(MainActivity.this, "#Poof!", Toast.LENGTH_SHORT).show();
                                textViewQuote.setAlpha(0.0f);
                                textViewTitle2.setAlpha(0.0f);
                                btn_bottom_quote.setAlpha(0.0f);
                                btn_bottom_quote.setEnabled(false);
                                btn_background.setAlpha(0.0f);
                                btn_background.setEnabled(false);
                                btnSwitch.setAlpha(0.0f);
                                btnSwitch.setEnabled(false);
                                btn_name_enter.setAlpha(0.0f);
                                btn_name_enter.setEnabled(false);
                                editTextRandomName.setAlpha(0.0f);
                                editTextRandomName.setEnabled(false);
                                break;
                            case 4:
                                Toast.makeText(MainActivity.this, "#Poof!", Toast.LENGTH_SHORT).show();
                                textViewTitle3.setAlpha(0.0f);
                                view.setAlpha(0.0f);
                                btn_random_action.setEnabled(false);
                                textViewTitle2.setAlpha(0.0f);
                                btn_toast_quote.setAlpha(0.0f);
                                btn_toast_quote.setEnabled(false);
                                btn_enter_quote.setAlpha(0.0f);
                                btn_enter_quote.setEnabled(false);
                                btn_background.setAlpha(0.0f);
                                btn_background.setEnabled(false);
                                btn_name_enter.setAlpha(0.0f);
                                btn_name_enter.setEnabled(false);
                                editTextRandomName.setAlpha(0.0f);
                                editTextRandomName.setEnabled(false);
                                break;
                            case 5:
                                Toast.makeText(MainActivity.this, "#Poof!", Toast.LENGTH_SHORT).show();
                                textViewTitle3.setAlpha(0.0f);
                                view.setAlpha(0.0f);
                                btn_random_action.setEnabled(false);
                                textViewTitle2.setAlpha(0.0f);
                                btn_toast_quote.setAlpha(0.0f);
                                btn_toast_quote.setEnabled(false);
                                btn_enter_quote.setAlpha(0.0f);
                                btn_enter_quote.setEnabled(false);
                                btn_background.setAlpha(0.0f);
                                btn_background.setEnabled(false);
                                textViewTitle.setAlpha(0.0f);
                                btn_bottom_quote.setAlpha(0.0f);
                                btn_bottom_quote.setEnabled(false);
                                btn_all_quotes.setAlpha(0.0f);
                                btn_all_quotes.setEnabled(false);
                                textViewQuote.setAlpha(0.0f);
                                btnSwitch.setAlpha(0.0f);
                                btnSwitch.setEnabled(false);
                                btn_name_enter.setAlpha(0.0f);
                                btn_name_enter.setEnabled(false);
                                editTextRandomName.setAlpha(0.0f);
                                editTextRandomName.setEnabled(false);
                                break;
                        }
                        break;
                    case 7: // Enter Name For Self Compliment
                        btn_name_enter.setAlpha(1.0f);
                        editTextRandomName.setAlpha(1.0f);
                        btn_name_enter.setEnabled(true);
                        editTextRandomName.setEnabled(true);
                        break;
                }
            }
        });

        // Setting Up The Switch Button
        String boolean_state = getIntent().getStringExtra("BOOLEAN_STATE");
        if (boolean_state == null){
            boolean_state = "false";
        }
        btnSwitch.setChecked(Boolean.valueOf(boolean_state));
        final boolean switchState = btnSwitch.isChecked();
        if (switchState == false){
            textViewTitle3.setAlpha(0.0f);
            btn_random_action.setAlpha(0.0f);
            btn_random_action.setEnabled(false);
            textViewTitle2.setAlpha(0.0f);
            btn_toast_quote.setAlpha(0.0f);
            btn_toast_quote.setEnabled(false);
            btn_enter_quote.setAlpha(0.0f);
            btn_enter_quote.setEnabled(false);
            btn_background.setAlpha(0.0f);
            btn_background.setEnabled(false);
            textViewTitle.setAlpha(0.0f);
            btn_bottom_quote.setAlpha(0.0f);
            btn_bottom_quote.setEnabled(false);
            btn_all_quotes.setAlpha(0.0f);
            btn_all_quotes.setEnabled(false);
            textViewQuote.setAlpha(0.0f);
            btn_name_enter.setAlpha(0.0f);
            btn_name_enter.setEnabled(false);
            editTextRandomName.setAlpha(0.0f);
            editTextRandomName.setEnabled(false);
            btn_reset_alpha.setAlpha(0.0f);
            btn_reset_alpha.setEnabled(false);
        }
        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean switchState = btnSwitch.isChecked();
                if (switchState){
                    Toast.makeText(MainActivity.this, "Online", Toast.LENGTH_SHORT).show();
                    textViewTitle3.setAlpha(1.0f);
                    btn_random_action.setAlpha(1.0f);
                    btn_random_action.setEnabled(true);
                    textViewTitle2.setAlpha(1.0f);
                    btn_toast_quote.setAlpha(1.0f);
                    btn_toast_quote.setEnabled(true);
                    btn_enter_quote.setAlpha(1.0f);
                    btn_enter_quote.setEnabled(true);
                    btn_background.setAlpha(1.0f);
                    btn_background.setEnabled(true);
                    textViewTitle.setAlpha(1.0f);
                    btn_bottom_quote.setAlpha(1.0f);
                    btn_bottom_quote.setEnabled(true);
                    btn_all_quotes.setAlpha(1.0f);
                    btn_all_quotes.setEnabled(true);
                    textViewQuote.setAlpha(1.0f);
                    btn_reset_alpha.setEnabled(true);
                }else {
                    Toast.makeText(MainActivity.this, "Offline", Toast.LENGTH_SHORT).show();
                    textViewTitle3.setAlpha(0.0f);
                    btn_random_action.setAlpha(0.0f);
                    btn_random_action.setEnabled(false);
                    textViewTitle2.setAlpha(0.0f);
                    btn_toast_quote.setAlpha(0.0f);
                    btn_toast_quote.setEnabled(false);
                    btn_enter_quote.setAlpha(0.0f);
                    btn_enter_quote.setEnabled(false);
                    btn_background.setAlpha(0.0f);
                    btn_background.setEnabled(false);
                    textViewTitle.setAlpha(0.0f);
                    btn_bottom_quote.setAlpha(0.0f);
                    btn_bottom_quote.setEnabled(false);
                    btn_all_quotes.setAlpha(0.0f);
                    btn_all_quotes.setEnabled(false);
                    textViewQuote.setAlpha(0.0f);
                    btn_name_enter.setAlpha(0.0f);
                    editTextRandomName.setAlpha(0.0f);
                    btn_name_enter.setEnabled(false);
                    editTextRandomName.setEnabled(false);
                    btn_reset_alpha.setEnabled(false);
                }
            }
        });

    }

    private void generateRandomBackground() {//0
        try {
            Random rand = new Random();
            int random = rand.nextInt((colorsTable.length + 1));
            String backgroundID = "color" + colorsTable[random];
            int resID = getResources().getIdentifier(backgroundID, "color", getPackageName());
            //In order to show which color it is
            //Toast.makeText(this, "" + backgroundID, Toast.LENGTH_SHORT).show();
            if (backgroundID.equals("colorBlack")){
                textViewTitle.setTextColor(Color.parseColor("#FFFFFF"));
                textViewTitle2.setTextColor(Color.parseColor("#FFFFFF"));
                textViewTitle3.setTextColor(Color.parseColor("#FFFFFF"));
                textViewQuote.setTextColor(Color.parseColor("#FFFFFF"));
            }else {
                textViewTitle.setTextColor(Color.parseColor("#000000"));
                textViewTitle2.setTextColor(Color.parseColor("#000000"));
                textViewTitle3.setTextColor(Color.parseColor("#000000"));
                textViewQuote.setTextColor(Color.parseColor("#000000"));
            }
            view.setBackgroundResource(resID);
        }catch (Exception e){
            view.setBackgroundResource(R.color.DefaultColor);
        }
    }

    public void enterQuote(View view) {//1
        //Toast.makeText(this, "Enter Quote Got Pressed!", Toast.LENGTH_SHORT).show();
        Intent goToEnterQuote = new Intent(MainActivity.this, EnterQuoteActivity.class);
        startActivity(goToEnterQuote);
        finish();
    }

    public void generateQuote(View view) {//2-3
        switch (view.getId()){
            case R.id.btn_generateToastQuote:
                //Toast.makeText(this, "Generate Toast Quote Got Pressed!", Toast.LENGTH_SHORT).show();
                Intent goToNextToastQuote = new Intent(MainActivity.this, StorageActivity.class);
                goToNextToastQuote.putExtra("WHERE_TO","GenerateRandom");
                startActivity(goToNextToastQuote);
                finish();
                break;
            case R.id.btn_generateBottomQuote:
                //Toast.makeText(this, "Generate Bottom Quote Got Pressed!", Toast.LENGTH_SHORT).show();
                Intent goToNextBottomQuote = new Intent(MainActivity.this, StorageActivity.class);
                goToNextBottomQuote.putExtra("WHERE_TO","BOTTOM_QUOTE");
                startActivity(goToNextBottomQuote);
                finish();
                break;
        }
    }

    public void showAllQuotes(View view) {//4
        //Toast.makeText(this, "Show All Quotes Got Pressed!", Toast.LENGTH_SHORT).show();
        Intent goToNextCheck = new Intent(MainActivity.this, StorageActivity.class);
        goToNextCheck.putExtra("WHERE_TO","No");
        startActivity(goToNextCheck);
        finish();
    }

    public void onClickResetAlpha(View view) {
        Toast.makeText(this, "Alpha Restored", Toast.LENGTH_SHORT).show();
        btn_random_action.setAlpha(1.0f);
        btn_random_action.setEnabled(true);
        textViewTitle3.setAlpha(1.0f);
        textViewTitle2.setAlpha(1.0f);
        btn_toast_quote.setAlpha(1.0f);
        btn_toast_quote.setEnabled(true);
        btn_enter_quote.setAlpha(1.0f);
        btn_enter_quote.setEnabled(true);
        btn_background.setAlpha(1.0f);
        btn_background.setEnabled(true);
        textViewTitle.setAlpha(1.0f);
        btn_bottom_quote.setAlpha(1.0f);
        btn_bottom_quote.setEnabled(true);
        btn_all_quotes.setAlpha(1.0f);
        btn_all_quotes.setEnabled(true);
        textViewQuote.setAlpha(1.0f);
        btnSwitch.setAlpha(1.0f);
        btnSwitch.setChecked(true);
        btnSwitch.setEnabled(true);
        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.DefaultColor);
        btn_name_enter.setAlpha(0.0f);
        btn_name_enter.setEnabled(false);
        editTextRandomName.setAlpha(0.0f);
        editTextRandomName.setEnabled(false);
    }

    @Override
    public void onClick(View view) {
        String name = editTextRandomName.getText().toString();
        if(name.equals("") || name == null){
            Toast.makeText(this, "Please Enter Your Name Sir / Ma'am", Toast.LENGTH_SHORT).show();
        }else {
            if (name.equalsIgnoreCase("Lidor")){
                Toast.makeText(MainActivity.this, "Lidor You're The Best App Developer In The World!", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Everyone Should Worship You!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Hello " + name + ", You Should Worship Lidor", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "And His Programming Skills!", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Plus Go And Buy Him Some Food!", Toast.LENGTH_SHORT).show();
            }
            editTextRandomName.setText("");
            editTextRandomName.setAlpha(0.0f);
            btn_name_enter.setAlpha(0.0f);
            btn_name_enter.setEnabled(false);
            editTextRandomName.setEnabled(false);
        }
    }

}
