package com.yat.megaapp.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.yat.megaapp.R;

import java.util.HashMap;

public class ShowBioDetails extends AppCompatActivity {

    TextView txShowBioDetails;
    HashMap<String ,String > ronaldoObject , trikaObject ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bio_details);
        txShowBioDetails=findViewById(R.id.txShowBioDetails);

        ronaldoObject =  (HashMap<String,String>)getIntent().getExtras().get("ronaldo");
        trikaObject =  (HashMap<String,String>)getIntent().getExtras().get("trika");


        if (ronaldoObject!=null){
            showBioDetails(ronaldoObject);
        }
        if (trikaObject !=null){
            showBioDetails(trikaObject);

        }


    }

    public void showBioDetails(HashMap<String, String> object){

        object.get("bio");
        txShowBioDetails.setText(" "+object.get("bio"));
    }

}