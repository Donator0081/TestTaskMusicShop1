package com.example.testtaskmusicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Your Order");
        Intent receiverOrderIntent = getIntent();
        String userName = receiverOrderIntent.getStringExtra("userName");
        String goodsName = receiverOrderIntent.getStringExtra("goodsName");
        int quantity = receiverOrderIntent.getIntExtra("quantity", 0);
        double orderPrice = receiverOrderIntent.getDoubleExtra("orderPrice", 0);
        double price = receiverOrderIntent.getDoubleExtra("price", 0);

        TextView textViewForOrder = findViewById(R.id.textViewForOrder);
        textViewForOrder.setText("Customer name: " + userName + "\n"
                + "Goods name: " + goodsName + "\n"
                + "Quantity: " + quantity + "\n"
                + "Price: " + price + "\n"
                + "Order price: " + orderPrice);
    }
}
