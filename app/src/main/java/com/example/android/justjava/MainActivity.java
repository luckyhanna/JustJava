package com.example.android.justjava;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    float plainCoffeePrice = 5f;
    float whippedCreamPrice = 1f;
    float chocolatePrice = 2f;
    boolean hasWhippedCream = false;
    boolean hasChocolate = false;
    String myName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButtonEnabled(R.id.minus_button, false);
        setButtonEnabled(R.id.order_button, false);
        displayQuantity(quantity);
        // update price too
//        displayPrice(quantity * plainCoffeePrice);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        hasChocolate = chocolateCheckBox.isChecked();
        EditText nameInput = (EditText) findViewById(R.id.name_input);
        myName = nameInput.getText().toString();

        float price = calculatePrice();
        String orderSummary = createOrderSummary(price);
        displayMessage(orderSummary);
    }

    /**
     * This method creates the order summary text.
     *
     * @param price is the totl price of the order
     * @return the order summary
     */
    private String createOrderSummary(float price) {
        String message = "Name: " + myName;
        message += "\nAdded whipped cream? " + (hasWhippedCream ? "Yes" : "No");
        message += "\nAdded chocolate? " + (hasChocolate ? "Yes" : "No");
        message += "\nQuantity: " + quantity;
        message += "\nTotal: $" + price;
        message += "\nThank you!";
        return message;
    }

    /**
     * This method calculates the price for the current order
     * @return the total price for the given input parameters
     */
    public float calculatePrice() {
        float basePrice = plainCoffeePrice;
        if (hasWhippedCream) {
            basePrice += whippedCreamPrice;
        }
        if (hasChocolate) {
            basePrice += chocolatePrice;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given text on the screen
     * @param message - the text to show
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void incrementQuantity(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
        // update price too
//        displayPrice(quantity * plainCoffeePrice);
        if (quantity == 1) {
            setButtonEnabled(R.id.minus_button, true);
            setButtonEnabled(R.id.order_button, true);
        } else if (quantity == 5) {
            setButtonEnabled(R.id.plus_button, false);
            Toast toast = Toast.makeText(this, "Staaaahp! No more coffee for you!",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrementQuantity(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
        // update price too
//        displayPrice(quantity * plainCoffeePrice);
        if (quantity == 0) {
            setButtonEnabled(R.id.minus_button, false);
            setButtonEnabled(R.id.order_button, false);
            Toast toast = Toast.makeText(this, "Get some coffee!",Toast.LENGTH_SHORT);
            toast.show();
        } else if (quantity == 4) {
            setButtonEnabled(R.id.plus_button, true);
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