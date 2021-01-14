package com.yat.megaapp.entertainment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yat.megaapp.R;

public class Connect3 extends AppCompatActivity {

    TextView txWinner;
    Button btnPlayAgain;
    LinearLayout layoutWinner;
    GridLayout gridLayoutContainer;

    int player = 0;   // zero is means O    1 == X
    int gameEmptyPlaces[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int userWinningPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameIsActive = true;
    boolean someOneHasWon = false;
    boolean gameIsOver = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect3);
        txWinner = findViewById(R.id.txWinner);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        gridLayoutContainer = findViewById(R.id.gridLayoutContainer);
        layoutWinner = findViewById(R.id.linearLayoutWinner);

    }

    public void XOImage(View view) {
        ImageView xoImage = (ImageView) view;  //casting

        int tappedXO = Integer.parseInt(xoImage.getTag().toString()); //get image by Tag
        if (gameEmptyPlaces[tappedXO] == 2 && gameIsActive) { //check if there is place empty and the game is running
            gameEmptyPlaces[tappedXO] = player;  // put player in the place
            xoImage.setTranslationY(-1000f);  // transition
            if (player == 0) {
                xoImage.setImageResource(R.drawable.o);  //set O image
                player = 1;
            } else {
                xoImage.setImageResource(R.drawable.x); // set X image
                player = 0;
            }
            xoImage.animate().translationYBy(1000f).rotation(360).setDuration(1000); // animation
        }

        for (int[] winningPositions : userWinningPositions) {
            if (gameEmptyPlaces[winningPositions[0]] == gameEmptyPlaces[winningPositions[1]]
                    && gameEmptyPlaces[winningPositions[1]] == gameEmptyPlaces[winningPositions[2]]
                    && gameEmptyPlaces[winningPositions[0]] != 2) {  //check if someone has won
                gameIsActive = false;  // game is stopped
                someOneHasWon = true;  //some one has won


                //someone has won
                String winner = "x";

                if (gameEmptyPlaces[winningPositions[0]] == 0) {
                    winner = "O";
                }
                txWinner.setText("The winner is " + winner);  //display the winners

                layoutWinner.setVisibility(View.VISIBLE);
            } else {
                gameIsOver = true;  //game is over

                for (int state : gameEmptyPlaces) {
                    if (state == 2) // check if there is place available
                        gameIsOver = false;
                }
                if (gameIsOver && !someOneHasWon) { //check that the game is over and no one has won
                    txWinner.setText("It's Draw"); // display its draw

                    layoutWinner.setVisibility(View.VISIBLE);

                }


            }
        }
    }

    public void playAgain(View view) {

        gameIsActive = true;
        someOneHasWon = false;
        layoutWinner.setVisibility(View.INVISIBLE);
        player = 0;

        for (int i = 0; i < gameEmptyPlaces.length; i++) {
            gameEmptyPlaces[i] = 2;
        }


        for (int i = 0; i < gridLayoutContainer.getChildCount(); i++) {
            ((ImageView) gridLayoutContainer.getChildAt(i)).setImageResource(0);
        }
    }
}