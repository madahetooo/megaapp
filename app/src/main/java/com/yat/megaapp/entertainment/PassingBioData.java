package com.yat.megaapp.entertainment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.yat.megaapp.R;
import com.yat.megaapp.adapter.ShowBioDetails;

import java.util.HashMap;

public class PassingBioData extends AppCompatActivity implements View.OnClickListener {

    String ronaldoBio = "Cristiano Ronaldo dos Santos Aveiro" +
            " (Portuguese pronunciation: born 5 February 1985)" +
            " is a Portuguese professional footballer who plays as " +
            "a forward for Serie A club Juventus and captains the Portugal" +
            " national team. Often considered the best player in the world and" +
            " widely regarded as one of the greatest players of all time, Ronaldo" +
            " has won five Ballon d'Or awards and four European Golden Shoes, both " +
            "of which are records for a European player.";

    String trikaBio = "Mohamed Mohamed Mohamed Aboutrika " +
            "(Arabic: محمد محمد محمد أبو تريكة; born 7" +
            " November 1978) is an Egyptian retired professional" +
            " footballer who played as an attacking midfielder and a" +
            " forward. He is often considered the best Egyptian player in" +
            " history and is widely regarded as one of the greatest African " +
            "footballers of all time. He also came second in the African Footballer" +
            " of the Year award in 2008 after Emmanuel Adebayor and was one of five" +
            " nominees for the 2006 award, and one of the ten nominated for the 2013" +
            " award.";

    ImageView ivRonaldo, ivTrika;

    HashMap<String ,String > ronaldoHashMap , trikaHashMap ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_bio_data);
        ivRonaldo = findViewById(R.id.ivRonaldo);
        ivTrika=findViewById(R.id.ivTrika);
        ivRonaldo.setOnClickListener(this);
        ivTrika.setOnClickListener(this);

        ronaldoHashMap = new HashMap<>();
        trikaHashMap = new HashMap<>();

        ronaldoHashMap.put("bio",ronaldoBio);
        trikaHashMap.put("bio",trikaBio);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivRonaldo:

                Intent goToBioDetails = new Intent(getApplicationContext(), ShowBioDetails.class);
                  goToBioDetails.putExtra("ronaldo",ronaldoHashMap);
                startActivity(goToBioDetails);

                break;
            case R.id.ivTrika:

                Intent goToBioDetails2 = new Intent(getApplicationContext(),ShowBioDetails.class);
                goToBioDetails2.putExtra("trika",trikaHashMap);
                startActivity(goToBioDetails2);

                break;
        }

    }
}