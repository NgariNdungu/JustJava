package com.createchs.justjava;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int coffeeCups = 1;

    /* This method is called when order button is clicked*/
    public void submitOrder(View view) {
        float price = calculatePrice();
        String orderSummary = createOrderSummary(price);
        displayMessage(orderSummary);
    }

    private String createOrderSummary(float price) {
        String name = "Customer";
        return "Name: " + name +
                "\n Quantity: " + coffeeCups +
                "\n Total: " + price +
                "\n Thank you!";
    }

    private float calculatePrice() {
        float price = coffeeCups * 5;
        return price;
    }

    /* This method displays the given quantity on the screen*/
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.noOfCoffees);
        quantityTextView.setText(String.valueOf(number));
    }

    private void displayMessage(String summary) {
        // findViewById returns a View, hence the type casting
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(summary);
    }

    public void decrement(View view) {
        if (coffeeCups >= 1) {
            coffeeCups -= 1;
        }
        display(coffeeCups);
    }

    public void increment(View view) {
        coffeeCups += 1;
        display(coffeeCups);
    }
}
