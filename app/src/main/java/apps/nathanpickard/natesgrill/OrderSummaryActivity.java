package apps.nathanpickard.natesgrill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OrderSummaryActivity extends AppCompatActivity {

    private String mOrderMessage;
    private TextView mOrderSummaryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        mOrderSummaryTextView = (TextView) findViewById(R.id.orderSummary);

        Intent intent = getIntent();
        mOrderMessage = intent.getStringExtra(getString(R.string.key_order_summary));

        mOrderSummaryTextView.setText(String.valueOf(mOrderMessage));

    }
}
