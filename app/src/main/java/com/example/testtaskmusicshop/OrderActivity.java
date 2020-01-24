package com.example.testtaskmusicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    String[] addresses = {"iliya00817@gmail.com"};
    String subject = "Order from Music shop";
    String emailText;

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

        emailText="Customer name: " + userName + "\n"
                + "Goods name: " + goodsName + "\n"
                + "Quantity: " + quantity + "\n"
                + "Price: " + price + "\n"
                + "Order price: " + orderPrice;
        TextView textViewForOrder = findViewById(R.id.textViewForOrder);
        textViewForOrder.setText(emailText);
    }

    public void sendToEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
