package com.example.assignment1;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.assignment1.Logic.GameManager;


import com.example.assignment1.Utilities.SignalManager;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final long DELAY = 1000L;

    //hearts
    private AppCompatImageView main_IMG_heart1;
    private AppCompatImageView main_IMG_heart2;
    private AppCompatImageView main_IMG_heart3;
    private AppCompatImageView[] main_IMG_hearts;

    //buttons
    private ExtendedFloatingActionButton main_BTN_left;
    private ExtendedFloatingActionButton main_BTN_right;

    //bikers
    AppCompatImageView main_IMG_biker1;
    AppCompatImageView main_IMG_biker2;
    AppCompatImageView main_IMG_biker3;
    private AppCompatImageView[] main_IMG_bikers;

    //poos

    AppCompatImageView main_IMG_poo1;
    AppCompatImageView main_IMG_poo2;
    AppCompatImageView main_IMG_poo3;
    AppCompatImageView main_IMG_poo4;
    AppCompatImageView main_IMG_poo5;
    AppCompatImageView main_IMG_poo6;
    AppCompatImageView main_IMG_poo7;
    AppCompatImageView main_IMG_poo8;
    AppCompatImageView main_IMG_poo9;
    AppCompatImageView main_IMG_poo10;
    AppCompatImageView main_IMG_poo11;
    AppCompatImageView main_IMG_poo12;
    private AppCompatImageView[] main_IMG_poos_row0;
    private AppCompatImageView[] main_IMG_poos_row1;
    private AppCompatImageView[] main_IMG_poos_row2;
    private AppCompatImageView[] main_IMG_poos_row3;
    private AppCompatImageView[][] main_IMG_poos_mat;


    private boolean timerOn = false;
    private CountDownTimer countDownTimer;
    private GameManager gameManager;

    private AppCompatImageView main_IMG_background;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        gameManager = new GameManager(main_IMG_hearts.length);
        initViews();
        startTimer();
    }



    private void findViews() {
        main_BTN_left = findViewById(R.id.main_BTN_left);
        main_BTN_right = findViewById(R.id.main_BTN_right);
        main_IMG_hearts = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)
        };
        main_IMG_bikers = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_biker1),
                findViewById(R.id.main_IMG_biker2),
                findViewById(R.id.main_IMG_biker3)
        };
        main_IMG_poos_row0 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_poo1),
                findViewById(R.id.main_IMG_poo5),
                findViewById(R.id.main_IMG_poo9)
        };
        main_IMG_poos_row1 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_poo2),
                findViewById(R.id.main_IMG_poo6),
                findViewById(R.id.main_IMG_poo10)
        };
        main_IMG_poos_row2 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_poo3),
                findViewById(R.id.main_IMG_poo7),
                findViewById(R.id.main_IMG_poo11)
        };
        main_IMG_poos_row3 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_poo4),
                findViewById(R.id.main_IMG_poo8),
                findViewById(R.id.main_IMG_poo12)
        };
        main_IMG_poos_mat = new AppCompatImageView[][] {main_IMG_poos_row0, main_IMG_poos_row1,
                main_IMG_poos_row2, main_IMG_poos_row3};
        main_IMG_background =findViewById(R.id.main_IMG_background);
    }

    private void initViews() {
        Glide
                .with(this)
                .load(R.drawable.road)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(main_IMG_background);

        initBikersViewAtStart();
        initAllPoosViewAtStart();
        main_BTN_left.setOnClickListener(view -> leftButtonClicked());
        main_BTN_right.setOnClickListener(view -> rightButtonClicked());
        refreshUI(); // update the view
    }

    private void initBikersViewAtStart()
    {
        main_IMG_bikers[0].setVisibility(View.INVISIBLE);
        main_IMG_bikers[1].setVisibility(View.VISIBLE);
        main_IMG_bikers[2].setVisibility(View.INVISIBLE);
    }

    private void initAllPoosViewAtStart()
    {
        for (int i = 0; i < 3 ; i++) {
            main_IMG_poos_row0[i].setVisibility(View.INVISIBLE);
            main_IMG_poos_row1[i].setVisibility(View.INVISIBLE);
            main_IMG_poos_row2[i].setVisibility(View.INVISIBLE);
            main_IMG_poos_row3[i].setVisibility(View.INVISIBLE);
        }
    }

    private void initRowPoos(AppCompatImageView [][] main_IMG_poos_mat, int row)
    {
        main_IMG_poos_mat[row][0].setVisibility(View.INVISIBLE);
        main_IMG_poos_mat[row][1].setVisibility(View.INVISIBLE);
        main_IMG_poos_mat[row][2].setVisibility(View.INVISIBLE);
    }

    private void rightButtonClicked() {
        // check if the middle biker is shown
        if (main_IMG_bikers[1].getVisibility() == View.VISIBLE) {
            main_IMG_bikers[1].setVisibility(View.INVISIBLE);
            main_IMG_bikers[2].setVisibility(View.VISIBLE);
        }
        // check if the leftest biker is shown
        else if (main_IMG_bikers[0].getVisibility() == View.VISIBLE){
                main_IMG_bikers[0].setVisibility(View.INVISIBLE);
                main_IMG_bikers[1].setVisibility(View.VISIBLE);
        }
        // if it didn't enter if's, the rightest biker is already shown, so do nothing
        refreshUI();
    }

    private void leftButtonClicked() {
        // check if the middle biker is shown
        if (main_IMG_bikers[1].getVisibility() == View.VISIBLE) {
                main_IMG_bikers[1].setVisibility(View.INVISIBLE);
                main_IMG_bikers[0].setVisibility(View.VISIBLE);
        }
        // check if the rightest biker is shown
        else if (main_IMG_bikers[2].getVisibility() == View.VISIBLE){
            main_IMG_bikers[2].setVisibility(View.INVISIBLE);
            main_IMG_bikers[1].setVisibility(View.VISIBLE);
        }
        // if it didn't enter if's, the leftest biker is already shown,so do nothing
        refreshUI();
    }

    private void refreshUI() {
        //lost:
        if(gameManager.isGameLost()) {
            gameManager.setLifeCount(3);
            gameManager.setNumOfCrushes(0);
            for (int i = 0; i < main_IMG_hearts.length; i++)
                main_IMG_hearts[i].setVisibility(View.VISIBLE);
        }
        //game still on:
        else {
            if(gameManager.getNumOfCrushes() != 0) {
                main_IMG_hearts[main_IMG_hearts.length - gameManager.getNumOfCrushes()]
                        .setVisibility(View.INVISIBLE);
            }
        }
    }

    private void startTimer() {
        if(!timerOn) {
            timerOn = true;
            countDownTimer = new CountDownTimer(9999999, DELAY) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateObstaclesUI();
                    if(gameManager.checkIfCrushed(main_IMG_poos_mat[3], main_IMG_bikers)) {
                        SignalManager.getInstance().toast("Ouch! Be Aware!");
                        SignalManager.getInstance().vibrate(500);
                    }
                    refreshUI();
                }

                @Override
                public void onFinish() {
                    timerOn=false;
                }
            }.start();
        }
    }



    private void copyPoosToRow(AppCompatImageView [][] main_IMG_poos_mat, int previousRow){
        for(int col = 0; col <3 ; col++) {
            if (main_IMG_poos_mat[previousRow][col].getVisibility() == (View.VISIBLE)) {
                main_IMG_poos_mat[previousRow + 1][col].setVisibility(View.VISIBLE);
                return;
            }
        }
    }

    private void updateObstaclesUI() {
        Random random = new Random();
        int randomCol = random.nextInt(3);

        // first initial the rows from down to top
        // then copy the view's from upper row to the one below
        for (int row = 3; row >=1; row--){
            initRowPoos(main_IMG_poos_mat, row);
            copyPoosToRow(main_IMG_poos_mat, row-1);
        }
        initRowPoos(main_IMG_poos_mat,0);
        main_IMG_poos_row0[randomCol].setVisibility(View.VISIBLE);
    }


}