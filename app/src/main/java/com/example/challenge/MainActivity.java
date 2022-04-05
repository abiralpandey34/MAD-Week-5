package com.example.challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> itemsList = new ArrayList<>();
    LinearLayout listContainer;
    FloatingActionButton addButton;
    public static final String EXTRA_MESSAGE = "com.example.twoactivity.extra.REPLY";
    public static final int MAIN_ACTIVITY_REQUEST= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addButton = findViewById(R.id.floatingActionButton);

        setContentView(R.layout.activity_main);

        listContainer = findViewById(R.id.list_container);
        this.renderList(this.itemsList);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MAIN_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(com.example.challenge.SecondActivity.EXTRA_REPLY);

                this.itemsList.add(reply);
                this.renderList(this.itemsList);
            }
        }
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, com.example.challenge.SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, TextUtils.join(",", this.itemsList));
        startActivityForResult(intent, MAIN_ACTIVITY_REQUEST);
    }


    private void renderList(List<String> shoppingItems){
        this.listContainer.removeAllViews();

        for (int i = 0; i < shoppingItems.size(); i++) {
            TextView listItem = new TextView(new ContextThemeWrapper(MainActivity.this, R.style.list_style));
            listItem.setText(shoppingItems.get(i));

            this.listContainer.addView(listItem);
        }
    }

}