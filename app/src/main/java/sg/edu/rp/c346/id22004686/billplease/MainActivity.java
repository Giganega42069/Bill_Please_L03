package sg.edu.rp.c346.id22004686.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText people;
    ToggleButton service;
    ToggleButton gst;
    EditText discount;
    RadioGroup payment;
    Button split;
    Button reset;
    TextView total;
    TextView receipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editAmount);
        people = findViewById(R.id.editPeople);
        service = findViewById(R.id.Service);
        gst = findViewById(R.id.GST);
        discount = findViewById(R.id.editDiscount);
        payment = findViewById(R.id.Payment);
        split = findViewById(R.id.Split);
        reset = findViewById(R.id.Reset);
        total = findViewById(R.id.Bill);
        receipt = findViewById(R.id.Receipt);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double amt = 0.0;

                    if (amount.getText().toString() !="" && people.getText().toString() !=""){
                        if (service.isChecked() && gst.isChecked()) {
                            amt = Double.parseDouble(amount.getText().toString()) * 1.17;
                        } else if (service.isChecked() && !gst.isChecked()){
                            amt = Double.parseDouble(amount.getText().toString()) * 1.10;
                        } else if (!service.isChecked() && gst.isChecked()){
                            amt = Double.parseDouble(amount.getText().toString()) * 1.07;
                        } else {
                            amt = Double.parseDouble(amount.getText().toString());
                        }



                if (discount.getText().toString() != "0" && discount.getText().toString() != ""){
                    amt = amt * (1 - (Double.parseDouble(discount.getText().toString())/100));
                }

                int paymentz = payment.getCheckedRadioButtonId();
                String payments;
                if (paymentz == R.id.Cash){
                    payments = " in cash";
                } else {
                    payments = " via PayNow to 921345678";
                }
                total.setText("Total Bill: $"+String.format("%.2f",amt));
                int peoples = Integer.parseInt(people.getText().toString());
                if (people.getText().toString() != "1") {
                    receipt.setText("Each Pays: $"+String.format("%.2f",(amt/peoples))+payments);
                } else {
                    receipt.setText("Each Pays: $"+String.format("%.2f",amt)+payments);
                }
                    }else{
                        total.setText("ERROR");
                        receipt.setText("ERROR");
                    }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                people.setText("");
                service.setChecked(true);
                gst.setChecked(true);
                discount.setText("");
                payment.clearCheck();
            }
        });

    }
}