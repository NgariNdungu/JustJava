package com.createchs.justjava;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int coffeeCups = 1;

    /* This method is called when order button is clicked*/
    public void submitOrder(View view) {
        boolean hasWhippedCream = ((CheckBox) findViewById(R.id.first_topping)).isChecked();
        boolean hasChocolate = ((CheckBox) findViewById(R.id.second_topping)).isChecked();
        float price = calculatePrice(hasWhippedCream,hasChocolate);
        String customerName = ((EditText) findViewById(R.id.name_edit_text)).getText().toString();
        String orderSummary = createOrderSummary(price, hasWhippedCream, hasChocolate, customerName);

        String[] recipient = new String[1];
        recipient[0] = "awesome@coffee.shop";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        intent.putExtra(Intent.EXTRA_EMAIL, recipient);
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.subject) + customerName);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private String createOrderSummary(float price, boolean hasWhippedCream, boolean hasChocolate, String customerName) {
        return getString(R.string.summary2, customerName,hasWhippedCream,hasChocolate,coffeeCups,price);
    }

    private float calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        float basePrice = 5; // price for a cup of coffee
        if (hasWhippedCream) {
            basePrice += 1;
        }
        if (hasChocolate) {
            basePrice += 2;
        }
        return coffeeCups * basePrice;
    }

    /* This method displays the given quantity on the screen*/
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.noOfCoffees);
        quantityTextView.setText(String.valueOf(number));
    }

    public void decrement(View view) {
        if (coffeeCups > 1) {
            coffeeCups -= 1;
        } else {
            Toast.makeText(this, getResources().getString(R.string.no_coffee),Toast.LENGTH_SHORT).show();
            return;
        }
        display(coffeeCups);
    }

    public void increment(View view) {
        if (coffeeCups < 100) {
            coffeeCups += 1;
        } else {
            Toast.makeText(this,getResources().getString(R.string.too_much),Toast.LENGTH_SHORT).show();
            return;
        }
        display(coffeeCups);
    }
}
