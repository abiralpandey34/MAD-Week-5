package com.example.challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.challenge.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    String[] allItems = {"Cheese", "Rice", "Apple", "Banana", "Mushroom", "Maple", "Syrup", "Bacon", "Egg", "Pepper"};
    List<String> addedItems;

    public static final String EXTRA_REPLY = "com.example.twoactivity.extra.REPLY";
    LinearLayout itemsContainer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        itemsContainer = findViewById(R.id.items_container);
        this.renderButtons();

        Intent intent = getIntent();
        String items = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        this.addedItems = Arrays.asList(items.split(","));
    }

    private void renderButtons(){
        int itemsCount = allItems.length;

        for(int i=0; i<itemsCount; i++){
            Button button = new Button(this);
            button.setText(allItems[i]);

            itemsContainer.addView(button);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        String text = clickedButton.getText().toString();

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, text);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}