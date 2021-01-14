package com.yat.megaapp.entertainment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.yat.megaapp.R;

public class RestaurantActivity extends Fragment {

    CheckBox chkPizza, chkBurger, chkChicken, chkVegetables, chkPepsi, chkTea, chkCoffee, chkAnise;
    Button btnAskForInvoice;
    TextView txOrderAndInvoice;
    static float invoice = 0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_restaurant, container, false);
        chkPizza = v.findViewById(R.id.chk_pizza);
        chkBurger = v.findViewById(R.id.chk_burger);
        chkChicken = v.findViewById(R.id.chk_chicken);
        chkVegetables = v.findViewById(R.id.chk_vegetables);
        chkPepsi = v.findViewById(R.id.chk_Pepsi);
        chkCoffee = v.findViewById(R.id.chk_Coffee);
        chkTea = v.findViewById(R.id.chk_Tea);
        chkAnise = v.findViewById(R.id.chk_Anise);
        btnAskForInvoice = v.findViewById(R.id.btn_invoice);
        txOrderAndInvoice = v.findViewById(R.id.tx_invoice);

        btnAskForInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invoice = 0;

                StringBuilder totalOrder = new StringBuilder();

                if (chkPizza.isChecked()) {
                    invoice += 30; // Add unit price
                    totalOrder.append(chkPizza.getText().toString() + " , "); //Add unit name
                }
                if (chkBurger.isChecked()) {
                    invoice += 20;
                    totalOrder.append(chkBurger.getText().toString() + " , "); //Add unit name
                }
                if (chkChicken.isChecked()) {
                    invoice += 35;
                    totalOrder.append(chkChicken.getText().toString() + " , "); //Add unit name
                }
                if (chkVegetables.isChecked()) {
                    invoice += 5;
                    totalOrder.append(chkVegetables.getText().toString() + " , "); //Add unit name
                }
                if (chkPepsi.isChecked()) {
                    invoice += 5;
                    totalOrder.append(chkPepsi.getText().toString() + " , "); //Add unit name
                }
                if (chkTea.isChecked()) {
                    invoice += 3;
                    totalOrder.append(chkTea.getText().toString() + " , "); //Add unit name
                }
                if (chkCoffee.isChecked()) {
                    invoice += 12;
                    totalOrder.append(chkCoffee.getText().toString() + " , "); //Add unit name
                }
                if (chkAnise.isChecked()) {
                    invoice += 3.5;
                    totalOrder.append(chkBurger.getText().toString() + " , "); //Add unit name
                }

                txOrderAndInvoice.setText("Your order is : " + totalOrder + "and your invoice is :" + invoice + "$");


            }
        });
        return v;
    }

}