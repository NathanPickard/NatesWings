package apps.nathanpickard.natesgrill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderSummaryActivity extends AppCompatActivity {

    private String mOrderMessage;
    private TextView mOrderSummaryTextView;
    private Button mConfirmOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        mOrderSummaryTextView = (TextView) findViewById(R.id.orderSummary);
        mConfirmOrderButton = (Button) findViewById(R.id.confirmOrderButton);

        Intent intent = getIntent();
        mOrderMessage = intent.getStringExtra(getString(R.string.key_order_summary));

        mOrderSummaryTextView.setText(String.valueOf(mOrderMessage));

        mConfirmOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderSummaryActivity.this, "Order sent!", Toast.LENGTH_LONG).show();
            }
        });

    }



}
