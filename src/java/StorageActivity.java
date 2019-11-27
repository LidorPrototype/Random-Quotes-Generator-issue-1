package com.example.randomquotesgeneratorcollector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class StorageActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    protected String strQuote;
    protected ListView itemQuoteList;
    protected ArrayList<String> quotesStorage = new ArrayList<>();
    protected ArrayAdapter<String> adapter;
    protected Button btnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        String whereTo = getIntent().getStringExtra("WHERE_TO");
        //Toast.makeText(this, "whereTo is: " + whereTo, Toast.LENGTH_SHORT).show();
        itemQuoteList = findViewById(R.id.list_item_quote);

        if (whereTo.equals("Yes")){
            strQuote = getIntent().getStringExtra("QUOTES");
            if (strQuote != null){
                //In Order To show the quote that has been entered:
                //Toast.makeText(this, "Quote is: " + strQuote, Toast.LENGTH_SHORT).show();
                quotesStorage.add(strQuote);
                quotesStorage = FileHelper.readData(this);
                adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, quotesStorage);
                itemQuoteList.setAdapter(adapter);
                adapter.add(strQuote);
                adapter.notifyDataSetChanged();
                FileHelper.writeData(quotesStorage, this);
                itemQuoteList.onSaveInstanceState();
                adapter.notifyDataSetChanged();
            }else{
                itemQuoteList = findViewById(R.id.list_item_quote);
                quotesStorage = FileHelper.readData(this);
                adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, quotesStorage);
                itemQuoteList.setAdapter(adapter);
                itemQuoteList.onSaveInstanceState();
                adapter.notifyDataSetChanged();
            }
            //Toast.makeText(this, "Need To Go Straight To Main Activity? " + whereTo, Toast.LENGTH_SHORT).show();
            goToMainActivity();
        }else if (whereTo.equals("No")){
            strQuote = getIntent().getStringExtra("QUOTES");
            if (strQuote != null){
                Toast.makeText(this, "Quote is: " + strQuote, Toast.LENGTH_SHORT).show();
                quotesStorage.add(strQuote);
                quotesStorage = FileHelper.readData(this);
                adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, quotesStorage);
                itemQuoteList.setAdapter(adapter);
                adapter.add(strQuote);
                adapter.notifyDataSetChanged();
                FileHelper.writeData(quotesStorage, this);
                itemQuoteList.onSaveInstanceState();
                adapter.notifyDataSetChanged();
            }else{
                //Toast.makeText(this, "No New Quote Has Been Entered", Toast.LENGTH_SHORT).show();
                quotesStorage = FileHelper.readData(this);
                adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, quotesStorage);
                itemQuoteList.setAdapter(adapter);
                itemQuoteList.onSaveInstanceState();
                adapter.notifyDataSetChanged();
            }
        }else if (whereTo.equals("GenerateRandom")){
            if (itemQuoteList == null){
                Toast.makeText(this, "No Quotes yet!", Toast.LENGTH_SHORT).show();
            }else {
                quotesStorage = FileHelper.readData(this);
                adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, quotesStorage);
                itemQuoteList.setAdapter(adapter);
                itemQuoteList.onSaveInstanceState();
                adapter.notifyDataSetChanged();
                String quote = generateRandomQuote();
                Toast.makeText(StorageActivity.this, "Quote is: \n" + quote, Toast.LENGTH_LONG).show();
            }
            goToMainActivity();
        }else if (whereTo.equals("BOTTOM_QUOTE")){
            Intent goToMainWithQuoteExtra = new Intent(StorageActivity.this, MainActivity.class);
            if (itemQuoteList == null){
                goToMainWithQuoteExtra.putExtra("QUOTE_TO_SHOW","No Quotes Yet!");
            }else {
                quotesStorage = FileHelper.readData(this);
                adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, quotesStorage);
                itemQuoteList.setAdapter(adapter);
                itemQuoteList.onSaveInstanceState();
                adapter.notifyDataSetChanged();
                String quote = generateRandomQuote();
                goToMainWithQuoteExtra.putExtra("QUOTE_TO_SHOW","" + quote);
                goToMainWithQuoteExtra.putExtra("BOOLEAN_STATE", "true");
            }
            startActivity(goToMainWithQuoteExtra);
            finish();
        }else if (whereTo.equals("ReturnEnterQuote")){
            strQuote = getIntent().getStringExtra("QUOTES");
            if (strQuote != null){
                quotesStorage.add(strQuote);
                quotesStorage = FileHelper.readData(this);
                adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, quotesStorage);
                itemQuoteList.setAdapter(adapter);
                adapter.add(strQuote);
                adapter.notifyDataSetChanged();
                FileHelper.writeData(quotesStorage, this);
                itemQuoteList.onSaveInstanceState();
                adapter.notifyDataSetChanged();
            }else{
                itemQuoteList = findViewById(R.id.list_item_quote);
                quotesStorage = FileHelper.readData(this);
                adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, quotesStorage);
                itemQuoteList.setAdapter(adapter);
                itemQuoteList.onSaveInstanceState();
                adapter.notifyDataSetChanged();
            }
            goToEnterQuoteActivity();
        }

        btnMainMenu = findViewById(R.id.btn_main_manu);
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });
        itemQuoteList.setOnItemLongClickListener(this);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(this, "Quote: " + quotesStorage.get(position) + " Has Been Removed", Toast.LENGTH_SHORT).show();
        quotesStorage.remove(position);
        adapter.notifyDataSetChanged();
        FileHelper.writeData(quotesStorage, this);
        itemQuoteList.onSaveInstanceState();
        return false;
    }

    public String generateRandomQuote(){
        Random rand = new Random();
        int random = rand.nextInt(quotesStorage.size());
        String randomQuotes = quotesStorage.get(random);
        return randomQuotes;
    }

    private void goToMainActivity() {
        Intent goToBack = new Intent(StorageActivity.this, MainActivity.class);
        goToBack.putExtra("BOOLEAN_STATE", "true");
        startActivity(goToBack);
        finish();
    }

    private void goToEnterQuoteActivity() {
        Intent goToEnterQuote = new Intent(StorageActivity.this, EnterQuoteActivity.class);
        startActivity(goToEnterQuote);
        finish();
    }
}
