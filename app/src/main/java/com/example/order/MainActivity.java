package com.example.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int numberOfCoffees=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * increment operation.
     */
    public void increment_coffee(View view){
        numberOfCoffees++;
        display(numberOfCoffees);
        displayPrice(numberOfCoffees*5);
    }

    /**
     * Decrement operation.
     */
    public void decrement_coffee(View view){
        if(numberOfCoffees==1) {
            display(numberOfCoffees);
        }else{
            numberOfCoffees--;
            display(numberOfCoffees);
            displayPrice(numberOfCoffees * 5);
        }
    }

    /**
     * This method is called when the order button is clicked.

    public void submitOrder(View view)
    {
        display(numberOfCoffees);
        displayPrice(numberOfCoffees*5);
    }
     * @return
     */
    String asd=null,qwe=null, poi=null, user_name = "No name entered", quantityDisplayer;

    public String quantity(int final_price){
        EditText name = (EditText) findViewById(R.id.name_input_id);
        user_name = name.getText().toString();
        //Log.v("MainActivity","Name: ", user_name);

        CheckBox whipped_cream = (CheckBox) findViewById(R.id.checkbox_whipped_cream);
        boolean hasWhippedCream = whipped_cream.isChecked();
        if(whipped_cream.isChecked())
        {
            asd = "Whipped Cream";
        }

        CheckBox extra_sugar = (CheckBox) findViewById(R.id.extra_sugar_checkbox);
        boolean hasExtraSugar = extra_sugar.isChecked();
        if(extra_sugar.isChecked())
        {
            qwe = "Extra Sugar";
        }

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolate.isChecked();
        if(chocolate.isChecked())
        {
            poi = "Chocolate";
        }

        int final_price2 = calc_price(hasWhippedCream, hasExtraSugar, hasChocolate);

        quantityDisplayer= "Name: " + user_name +
                "\n\nQuantity: "+ numberOfCoffees +
                "\nADD ONS: " + "\n\t" + asd +  "\n\t"+ qwe + "\n\t" + poi +
                "\n\nTotal amount is "+ final_price2 +
                "£\n" + "\nThank you!";
        //displayMessage(quantityDisplayer);
        return quantityDisplayer;


    }

    public void submitOrder(View view)
    {

        int final_price = numberOfCoffees*5;
        String priceMessage= quantity(final_price);
        displayMessage(priceMessage);
        asd = null;
        qwe = null;
        poi = null;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL  , new String[] { "afrid704@gmail.com" });

        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for " + user_name);
        intent.putExtra(Intent.EXTRA_TEXT, quantityDisplayer);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public int calc_price(boolean whippedcream, boolean extrasugar, boolean chocolate)
    {
        int base_price = 5;
        int order = numberOfCoffees * base_price;
        if(whippedcream)
        {
            order = order + 1;
        }

        if(extrasugar)
        {
            order = order + 2;
        }

        if(chocolate)
        {
            order = order + 3;
        }

        return order;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}