package com.example.testtaskmusicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int count = 0;
    Spinner spinner;
    ArrayList<String> spinnerArray;
    ArrayAdapter arrayAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userName = findViewById(R.id.editTextName);
        createSpinner();
        createMap();
    }

    void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("Guitar", 500.0);
        goodsMap.put("Drums", 1500.0);
        goodsMap.put("Keyboard", 1000.0);
    }

    void createSpinner() {
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinnerForGuitars);
        spinner.setOnItemSelectedListener(this);

        spinnerArray = new ArrayList();
        spinnerArray.add("Guitar");
        spinnerArray.add("Drums");
        spinnerArray.add("Keyboard");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    public void setPlus(View view) {
        count = count + 1;
        TextView textViewQuantity = findViewById(R.id.textViewCount);
        textViewQuantity.setText("" + count);
        TextView textViewForDollars = findViewById(R.id.textViewForDollars);
        textViewForDollars.setText("" + count * price);
    }

    public void setMinus(View view) {
        if (count > 0) {
            count = count - 1;
        }
        TextView textViewQuantity = findViewById(R.id.textViewCount);
        textViewQuantity.setText("" + count);
        TextView textViewForDollars = findViewById(R.id.textViewForDollars);
        textViewForDollars.setText("" + count * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView textViewForDollars = findViewById(R.id.textViewForDollars);
        textViewForDollars.setText("" + count * price);
        ImageView goodsImageView = findViewById(R.id.goodsImageView);
        switch (goodsName) {
            case "Guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "Drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "Keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {

        Order order = new Order();
        order.goodsName = goodsName;
        order.orderPrice = price * count;
        order.quantity = count;
        if (userName != null) {
            order.userName = userName.getText().toString();
            Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
            startActivity(orderIntent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Введите имя", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
