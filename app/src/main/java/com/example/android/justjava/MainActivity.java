package com.example.android.justjava;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    float plainCoffeePrice = 5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButtonEnabled(R.id.minus_button, false);
        display(quantity);
        displayPrice(quantity * plainCoffeePrice);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
//        display(quantity);
//        displayPrice(quantity * plainCoffeePrice);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the price for the selected quantity
     */
    private void displayPrice(float number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText("$" + number);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void incrementQuantity(View view) {
        quantity = quantity + 1;
        display(quantity);
        displayPrice(quantity * plainCoffeePrice);
        if (quantity == 1) {
            setButtonEnabled(R.id.minus_button, true);
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrementQuantity(View view) {
        quantity = quantity - 1;
        display(quantity);
        displayPrice(quantity * plainCoffeePrice);
        if (quantity == 0) {
            setButtonEnabled(R.id.minus_button, false);
        }
    }

    /**
     * This method enables/disables a button
     * @param id - id of the button
     * @param enabled - the state you want to put the button in
     */
    private void setButtonEnabled(@IdRes int id, boolean enabled) {
        Button button = (Button) findViewById(id);
        button.setEnabled(enabled);
    }
}