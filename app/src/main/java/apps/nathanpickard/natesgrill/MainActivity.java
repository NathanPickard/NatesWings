package apps.nathanpickard.natesgrill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if(quantity == 100) {
            // Show an error message as toast
            Toast.makeText(this, "You cannot have more than 100 wings per order",
                    Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 2;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */

    public void decrement(View view) {
        if(quantity == 0) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 2 wing", Toast.LENGTH_SHORT).show();
            // Exit this method because there is nothing left to do
            return;
        }
        quantity = quantity - 2;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        // Find the user's name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();

        // Figure out if the user wants hickory BBQ sauce
        CheckBox hickoryBBQCheckBox = (CheckBox) findViewById(R.id.hickory_bbq_checkbox);
        boolean hasHickoryBBQ = hickoryBBQCheckBox.isChecked();

        // Figure out if the user wants honey chipotle sauce
        CheckBox honeyChipotleCheckBox = (CheckBox) findViewById(R.id.honey_chipotle_checkbox);
        boolean hasHoneyChipotle = honeyChipotleCheckBox.isChecked();

        // Figure out if the user wants teriyaki sauce
        CheckBox teriyakiCheckBox = (CheckBox) findViewById(R.id.teriyaki_checkbox);
        boolean hasTeriyaki = teriyakiCheckBox.isChecked();

        // Figure out if the user wants buffalo sauce
        CheckBox buffaloCheckBox = (CheckBox) findViewById(R.id.buffalo_checkbox);
        boolean hasBuffalo = buffaloCheckBox.isChecked();

        // Calculate the price
        int price = calculatePrice(hasHickoryBBQ, hasHoneyChipotle, hasTeriyaki, hasBuffalo);

        // Display the order summary on the screen
        String message = createOrderSummary(name, price, hasHickoryBBQ, hasHoneyChipotle,
                hasTeriyaki, hasBuffalo);



        Intent intent = new Intent(this, OrderSummaryActivity.class);
        intent.putExtra(getString(R.string.key_order_summary), message);
        startActivity(intent);



        // Intent to launch an email app.
        // Send the order summary in the email body.
        /*Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // Only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Nate's Wings order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        */

    }

    /**
     * Calculates the price of the order.
     *
     * @param addHickoryBBQ is whether or not the user wants hickory BBQ sauce
     * @param addHoneyChipotle is whether or not the user wants honey chipotle sauce
     * @param addTeriyaki is whether or not the user wants teriyaki sauce
     * @param addBuffalo is whether or not the user wants buffalo sauce
     * @return total price
     */
    private int calculatePrice(boolean addHickoryBBQ, boolean addHoneyChipotle,
                               boolean addTeriyaki, boolean addBuffalo) {
        // Price of 2 wings
        int basePrice = 1;
        int sauceTotal = 0;

        // Add $2 if the user wants sauces
        if (addHickoryBBQ) {
            sauceTotal = sauceTotal + 2;
        }

        if (addHoneyChipotle) {
            sauceTotal = sauceTotal + 2;
        }

        if (addTeriyaki) {
            sauceTotal = sauceTotal + 2;
        }

        if (addBuffalo) {
            sauceTotal = sauceTotal + 2;
        }

        // Calculate the total order price by multiplying by quantity
        return ((quantity / 2) * basePrice) + sauceTotal;
    }

    /**
     * Create order summary
     *
     * @param name of the customer
     * @param price of the order
     * @param addHickoryBBQ is whether or not the user wants hickory BBQ sauce
     * @param addHoneyChipotle is whether or not the user wants honey chipotle sauce
     * @param addTeriyaki is whether or not the user wants teriyaki sauce
     * @param addBuffalo is whether or not the user wants buffalo sauce
     * @return text summary
     */

    private String createOrderSummary(String name, int price, boolean addHickoryBBQ,
                                      boolean addHoneyChipotle, boolean addTeriyaki,
                                      boolean addBuffalo) {
        String priceMessage = "Name: " + name;
        if (addHickoryBBQ == true || addHoneyChipotle == true || addTeriyaki == true || addBuffalo == true) {
            priceMessage += "\nSauces chosen:";
        }
        else {
            priceMessage += "\nSauces chosen: None";
        }
        if (addHickoryBBQ == true) {
            priceMessage += "\nHickory BBQ";
        }
        if (addHoneyChipotle == true) {
            priceMessage += "\nHoney Chipotle? " + addHoneyChipotle;
        }
        if (addTeriyaki == true) {
            priceMessage += "\nTeriyaki? " + addTeriyaki;
        }
        if (addBuffalo == true) {
            priceMessage += "\nBuffalo? " + addBuffalo;
        }
        priceMessage += "\n# of wings: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfWings) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfWings);
    }

}
