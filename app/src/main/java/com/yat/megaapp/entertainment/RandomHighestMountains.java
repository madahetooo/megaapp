package com.yat.megaapp.entertainment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yat.megaapp.R;

import java.util.Random;

public class RandomHighestMountains extends Fragment implements View.OnClickListener {

    ImageView ivHome;
    TextView tvRandomMountains;
    Button btnGenerateRandomHighestMountains;
    int Index;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_random_highest_mountains, container, false);
        ivHome = v.findViewById(R.id.iv_home);
        tvRandomMountains = v.findViewById(R.id.tv_randomMountains);
        btnGenerateRandomHighestMountains = v.findViewById(R.id.btn_generateRandomHighestMountains);
        ivHome.setOnClickListener(this);
        btnGenerateRandomHighestMountains.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_generateRandomHighestMountains:

                //Create Array of String
                String mountains[] = {"Everest", "Colorado", "Mousa", "Nibal", "Saint Catrine",
                        "Himalaya", "Gapl Tarek", "El Mo2atamm", "Safaga"};

                //Initialize Random Object
                Random randomMountains = new Random();

                //Pick up random index of the mountains length
                Index = randomMountains.nextInt(mountains.length);

                //Show the random mountains
                tvRandomMountains.setText(mountains[Index]);
                break;


        }

    }
}