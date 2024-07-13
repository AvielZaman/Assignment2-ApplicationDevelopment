package com.example.assignment1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.assignment1.Logic.GameManager;


import com.example.assignment1.Models.Record;
import com.example.assignment1.Utilities.MoveDetector;
import com.example.assignment1.Utilities.SignalManager;
import com.example.assignment1.Utilities.SoundPlayer;
import com.example.assignment1.interfaces.LocationCallback;
import com.example.assignment1.interfaces.MoveCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static long delay;
    private static final long SLOW_DELAY = 1000L;
    private static final long FAST_DELAY = 800L;
    private static final int REQUEST_CODE = 100;

    public static final String KEY_MODES = "KEY_MODES";

    private MaterialTextView main_LBL_score;

    //HEARTS
    private AppCompatImageView main_IMG_heart1;
    private AppCompatImageView main_IMG_heart2;
    private AppCompatImageView main_IMG_heart3;
    private AppCompatImageView[] main_IMG_hearts;

    //buttons
    private ExtendedFloatingActionButton main_BTN_left;
    private ExtendedFloatingActionButton main_BTN_right;

    //BIKERS
    AppCompatImageView main_IMG_biker1;
    AppCompatImageView main_IMG_biker2;
    AppCompatImageView main_IMG_biker3;
    AppCompatImageView main_IMG_biker4;
    AppCompatImageView main_IMG_biker5;
    private AppCompatImageView[] main_IMG_bikers;

    //POOS
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
    AppCompatImageView main_IMG_poo13;
    AppCompatImageView main_IMG_poo14;
    AppCompatImageView main_IMG_poo15;
    AppCompatImageView main_IMG_poo16;
    AppCompatImageView main_IMG_poo17;
    AppCompatImageView main_IMG_poo18;
    AppCompatImageView main_IMG_poo19;
    AppCompatImageView main_IMG_poo20;
    AppCompatImageView main_IMG_poo21;
    AppCompatImageView main_IMG_poo22;
    AppCompatImageView main_IMG_poo23;
    AppCompatImageView main_IMG_poo24;
    AppCompatImageView main_IMG_poo25;

    private AppCompatImageView[] main_IMG_poos_row0;
    private AppCompatImageView[] main_IMG_poos_row1;
    private AppCompatImageView[] main_IMG_poos_row2;
    private AppCompatImageView[] main_IMG_poos_row3;
    private AppCompatImageView[] main_IMG_poos_row4;
    private AppCompatImageView[][] main_IMG_poos_mat;

    //HAMBURGERS
    AppCompatImageView main_IMG_hamburger1;
    AppCompatImageView main_IMG_hamburger2;
    AppCompatImageView main_IMG_hamburger3;
    AppCompatImageView main_IMG_hamburger4;
    AppCompatImageView main_IMG_hamburger5;
    AppCompatImageView main_IMG_hamburger6;
    AppCompatImageView main_IMG_hamburger7;
    AppCompatImageView main_IMG_hamburger8;
    AppCompatImageView main_IMG_hamburger9;
    AppCompatImageView main_IMG_hamburger10;
    AppCompatImageView main_IMG_hamburger11;
    AppCompatImageView main_IMG_hamburger12;
    AppCompatImageView main_IMG_hamburger13;
    AppCompatImageView main_IMG_hamburger14;
    AppCompatImageView main_IMG_hamburger15;
    AppCompatImageView main_IMG_hamburger16;
    AppCompatImageView main_IMG_hamburger17;
    AppCompatImageView main_IMG_hamburger18;
    AppCompatImageView main_IMG_hamburger19;
    AppCompatImageView main_IMG_hamburger20;
    AppCompatImageView main_IMG_hamburger21;
    AppCompatImageView main_IMG_hamburger22;
    AppCompatImageView main_IMG_hamburger23;
    AppCompatImageView main_IMG_hamburger24;
    AppCompatImageView main_IMG_hamburger25;

    private AppCompatImageView[] main_IMG_hamburgers_row0;
    private AppCompatImageView[] main_IMG_hamburgers_row1;
    private AppCompatImageView[] main_IMG_hamburgers_row2;
    private AppCompatImageView[] main_IMG_hamburgers_row3;
    private AppCompatImageView[] main_IMG_hamburgers_row4;
    private AppCompatImageView[][] main_IMG_hamburgers_mat;


    private boolean timerOn = false;
    private CountDownTimer countDownTimer;
    private GameManager gameManager;
    private SoundPlayer soundPlayer = new SoundPlayer(this);
    private MoveDetector moveDetector;

    private AppCompatImageView main_IMG_background;

    String playMode = "";
    String speedMode = "";

    private Record record;

    private FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        gameManager = new GameManager(main_IMG_hearts.length);
        record = new Record();
        initViews();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        startTimer();
    }


    private void findViews() {
        main_BTN_left = findViewById(R.id.main_BTN_left);
        main_BTN_right = findViewById(R.id.main_BTN_right);
        main_LBL_score = findViewById(R.id.main_LBL_score);
        main_IMG_hearts = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)
        };
        main_IMG_bikers = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_biker1),
                findViewById(R.id.main_IMG_biker2),
                findViewById(R.id.main_IMG_biker3),
                findViewById(R.id.main_IMG_biker4),
                findViewById(R.id.main_IMG_biker5)
        };
        main_IMG_poos_row0 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_poo1),
                findViewById(R.id.main_IMG_poo6),
                findViewById(R.id.main_IMG_poo11),
                findViewById(R.id.main_IMG_poo16),
                findViewById(R.id.main_IMG_poo21)
        };
        main_IMG_poos_row1 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_poo2),
                findViewById(R.id.main_IMG_poo7),
                findViewById(R.id.main_IMG_poo12),
                findViewById(R.id.main_IMG_poo17),
                findViewById(R.id.main_IMG_poo22)
        };
        main_IMG_poos_row2 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_poo3),
                findViewById(R.id.main_IMG_poo8),
                findViewById(R.id.main_IMG_poo13),
                findViewById(R.id.main_IMG_poo18),
                findViewById(R.id.main_IMG_poo23)
        };
        main_IMG_poos_row3 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_poo4),
                findViewById(R.id.main_IMG_poo9),
                findViewById(R.id.main_IMG_poo14),
                findViewById(R.id.main_IMG_poo19),
                findViewById(R.id.main_IMG_poo24)
        };
        main_IMG_poos_row4 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_poo5),
                findViewById(R.id.main_IMG_poo10),
                findViewById(R.id.main_IMG_poo15),
                findViewById(R.id.main_IMG_poo20),
                findViewById(R.id.main_IMG_poo25)
        };

        main_IMG_poos_mat = new AppCompatImageView[][]{main_IMG_poos_row0, main_IMG_poos_row1,
                main_IMG_poos_row2, main_IMG_poos_row3, main_IMG_poos_row4};

        main_IMG_hamburgers_row0 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_hamburger1),
                findViewById(R.id.main_IMG_hamburger6),
                findViewById(R.id.main_IMG_hamburger11),
                findViewById(R.id.main_IMG_hamburger16),
                findViewById(R.id.main_IMG_hamburger21)
        };
        main_IMG_hamburgers_row1 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_hamburger2),
                findViewById(R.id.main_IMG_hamburger7),
                findViewById(R.id.main_IMG_hamburger12),
                findViewById(R.id.main_IMG_hamburger17),
                findViewById(R.id.main_IMG_hamburger22)
        };
        main_IMG_hamburgers_row2 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_hamburger3),
                findViewById(R.id.main_IMG_hamburger8),
                findViewById(R.id.main_IMG_hamburger13),
                findViewById(R.id.main_IMG_hamburger18),
                findViewById(R.id.main_IMG_hamburger23)
        };
        main_IMG_hamburgers_row3 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_hamburger4),
                findViewById(R.id.main_IMG_hamburger9),
                findViewById(R.id.main_IMG_hamburger14),
                findViewById(R.id.main_IMG_hamburger19),
                findViewById(R.id.main_IMG_hamburger24)
        };
        main_IMG_hamburgers_row4 = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_hamburger5),
                findViewById(R.id.main_IMG_hamburger10),
                findViewById(R.id.main_IMG_hamburger15),
                findViewById(R.id.main_IMG_hamburger20),
                findViewById(R.id.main_IMG_hamburger25)
        };

        main_IMG_hamburgers_mat = new AppCompatImageView[][]{main_IMG_hamburgers_row0, main_IMG_hamburgers_row1,
                main_IMG_hamburgers_row2, main_IMG_hamburgers_row3, main_IMG_hamburgers_row4};

        main_IMG_background = findViewById(R.id.main_IMG_background);
    }

    private void initViews() {
        Glide
                .with(this)
                .load(R.drawable.five_lane_road)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(main_IMG_background);

        Intent previousActivity = getIntent();
        String[] modes = previousActivity.getStringArrayExtra(KEY_MODES);
        playMode = modes[0];
        speedMode = modes[1];

        main_LBL_score.setText(String.valueOf(gameManager.getScore()));
        initBikersViewAtStart();
        // init all the matrix at launch
        for (int row = 0; row < main_IMG_poos_mat.length; row++)
            initRowPoosAndHamburgers(main_IMG_poos_mat, main_IMG_hamburgers_mat, row);

        // check if the chosen mode is buttons/sensor.
        // if chosen mode is null, then default mode = button
        if (Objects.equals(playMode, "button") || playMode == null) {
            main_BTN_left.setOnClickListener(view -> leftButtonClicked());
            main_BTN_right.setOnClickListener(view -> rightButtonClicked());
        } else {// sensor mode
            initMoveDetector();
            main_BTN_left.setVisibility(View.INVISIBLE);
            main_BTN_right.setVisibility(View.INVISIBLE);
        }

        // check if the speed mode is slow\fast.
        // if speed mode is null, then default speed = slow
        if (Objects.equals(speedMode, "slow") || speedMode == null)
            delay = SLOW_DELAY;
        else
            delay = FAST_DELAY;


        refreshUI(); // update the view
    }

    private void initMoveDetector() {
        moveDetector = new MoveDetector(this,
                new MoveCallback() {
                    @Override
                    public void moveRight() {
                        rightButtonClicked();
                    }

                    @Override
                    public void moveLeft() {
                        leftButtonClicked();
                    }

                    @Override
                    public void moveY() {
                       //pass
                    }
                }
        );

    }

    private void initBikersViewAtStart() {
        main_IMG_bikers[0].setVisibility(View.INVISIBLE);
        main_IMG_bikers[1].setVisibility(View.INVISIBLE);
        main_IMG_bikers[2].setVisibility(View.VISIBLE);
        main_IMG_bikers[3].setVisibility(View.INVISIBLE);
        main_IMG_bikers[4].setVisibility(View.INVISIBLE);
    }

    private void initRowPoosAndHamburgers(AppCompatImageView[][] main_IMG_poos_mat, AppCompatImageView[][] main_IMG_hamburgers_mat, int row) {
        for (int col = 0; col < main_IMG_poos_mat[row].length; col++) {
            main_IMG_poos_mat[row][col].setVisibility(View.INVISIBLE);
            main_IMG_hamburgers_mat[row][col].setVisibility(View.INVISIBLE);
        }
    }

    private void rightButtonClicked() {
        // check if the middle biker is shown
        if (main_IMG_bikers[2].getVisibility() == View.VISIBLE) {
            main_IMG_bikers[2].setVisibility(View.INVISIBLE);
            main_IMG_bikers[3].setVisibility(View.VISIBLE);
        }
        // check if the leftest biker is shown
        else if (main_IMG_bikers[0].getVisibility() == View.VISIBLE) {
            main_IMG_bikers[0].setVisibility(View.INVISIBLE);
            main_IMG_bikers[1].setVisibility(View.VISIBLE);
        }
        // check if the second biker from left is shown
        else if (main_IMG_bikers[1].getVisibility() == View.VISIBLE) {
            main_IMG_bikers[1].setVisibility(View.INVISIBLE);
            main_IMG_bikers[2].setVisibility(View.VISIBLE);
        }
        // check if the second biker from right is shown
        else if (main_IMG_bikers[3].getVisibility() == View.VISIBLE) {
            main_IMG_bikers[3].setVisibility(View.INVISIBLE);
            main_IMG_bikers[4].setVisibility(View.VISIBLE);
        }
        // if it didn't enter if's, the rightest biker is already shown, so do nothing
        refreshUI();
    }

    private void leftButtonClicked() {
        // check if the middle biker is shown
        if (main_IMG_bikers[2].getVisibility() == View.VISIBLE) {
            main_IMG_bikers[2].setVisibility(View.INVISIBLE);
            main_IMG_bikers[1].setVisibility(View.VISIBLE);
        }
        // check if the rightest biker is shown
        else if (main_IMG_bikers[4].getVisibility() == View.VISIBLE) {
            main_IMG_bikers[4].setVisibility(View.INVISIBLE);
            main_IMG_bikers[3].setVisibility(View.VISIBLE);
        }
        // check if the second biker from left is shown
        else if (main_IMG_bikers[1].getVisibility() == View.VISIBLE) {
            main_IMG_bikers[1].setVisibility(View.INVISIBLE);
            main_IMG_bikers[0].setVisibility(View.VISIBLE);
        }
        // check if the second biker from right is shown
        else if (main_IMG_bikers[3].getVisibility() == View.VISIBLE) {
            main_IMG_bikers[3].setVisibility(View.INVISIBLE);
            main_IMG_bikers[2].setVisibility(View.VISIBLE);
        }
        // if it didn't enter if's, the leftest biker is already shown, so do nothing
        refreshUI();
    }

    private void refreshUI() {
        //lost:
        if (gameManager.isGameLost()) {
            changeToLoseActivity(gameManager.getScore());
        }
        //game still on:
        else {
            main_LBL_score.setText(String.valueOf(gameManager.getScore()));
            if (gameManager.getNumOfCrushes() != 0) {
                main_IMG_hearts[main_IMG_hearts.length - gameManager.getNumOfCrushes()]
                        .setVisibility(View.INVISIBLE);
            }
        }
    }

    private void startTimer() {
        if (!timerOn) {
            timerOn = true;
            countDownTimer = new CountDownTimer(9999999, delay) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateObstaclesUI();
                    if (gameManager.checkIfCrushed(main_IMG_poos_mat[4], main_IMG_bikers)) {
                        soundPlayer.playSound(R.raw.poosound);
                        SignalManager.getInstance().toast("Ouch! Be Aware!");
                        SignalManager.getInstance().vibrate(500);
                    }
                    soundPlayer.stopSound();
                    gameManager.checkIfGotHamburger(main_IMG_hamburgers_mat[4], main_IMG_bikers);
                    refreshUI();
                }

                @Override
                public void onFinish() {
                    timerOn = false;
                }
            }.start();
        }
    }

    protected void onPause() {
        super.onPause();
        if (countDownTimer != null)
            countDownTimer.onFinish();

        if (moveDetector != null) {
            moveDetector.stop();
        }
    }

    protected void onResume() {
        super.onResume();
        if (countDownTimer != null)
            timerOn = true;

        if (moveDetector != null) {
            moveDetector.start();
        }
    }

    private void copyPoosToRow(AppCompatImageView[][] main_IMG_poos_mat, int previousRow) {
        for (int col = 0; col < main_IMG_poos_mat[previousRow].length; col++) {
            if (main_IMG_poos_mat[previousRow][col].getVisibility() == (View.VISIBLE)) {
                main_IMG_poos_mat[previousRow + 1][col].setVisibility(View.VISIBLE);
                return;
            }
        }
    }

    private void copyHamburgersToRow(AppCompatImageView[][] main_IMG_hamburgers_mat, int previousRow) {
        for (int col = 0; col < main_IMG_hamburgers_mat[previousRow].length; col++) {
            if (main_IMG_hamburgers_mat[previousRow][col].getVisibility() == (View.VISIBLE)) {
                main_IMG_hamburgers_mat[previousRow + 1][col].setVisibility(View.VISIBLE);
                return;
            }
        }
    }

    private void updateObstaclesUI() {
        Random random_poos = new Random();
        int randomColPoos = random_poos.nextInt(main_IMG_poos_row0.length);

        Random random_hamburgers = new Random();
        int randomColHamburgers = random_hamburgers.nextInt(main_IMG_poos_row0.length);

        // first initial the rows from down to top,
        // then copy the view's from upper row to the one below
        for (int row = main_IMG_poos_mat.length - 1; row >= 1; row--) {
            initRowPoosAndHamburgers(main_IMG_poos_mat, main_IMG_hamburgers_mat, row);
            copyPoosToRow(main_IMG_poos_mat, row - 1);
            copyHamburgersToRow(main_IMG_hamburgers_mat, row - 1);
        }
        initRowPoosAndHamburgers(main_IMG_poos_mat, main_IMG_hamburgers_mat, 0);
        main_IMG_poos_row0[randomColPoos].setVisibility(View.VISIBLE);

        // poos and hamburgers can't be at same spot-
        // check if the random poo position is the same as the random hamburger position-
        // if it does, don't put a hamburger
        if (randomColHamburgers != randomColPoos)
            main_IMG_hamburgers_row0[randomColHamburgers].setVisibility(View.VISIBLE);
    }


    private void changeToLoseActivity(int score) {
        countDownTimer.cancel();
        if (moveDetector != null) {
            moveDetector.stop();
        }

        getLastLocation(new LocationCallback() {
            @Override
            public void onLocationResult() {
                Intent loseIntent = new Intent(MainActivity.this, LoseActivity.class);
                loseIntent.putExtra(LoseActivity.KEY_SCORE, score);
                loseIntent.putExtra(LoseActivity.KEY_LATITUDE, record.getLatitude());
                loseIntent.putExtra(LoseActivity.KEY_LONGITUDE, record.getLongitude());
                startActivity(loseIntent);
                finish();
            }
        });

    }

    private void getLastLocation(LocationCallback callback) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    record.setLatitude(addresses.get(0).getLatitude())
                                            .setLongitude(addresses.get(0).getLongitude());
                                    // Call the callback method after setting the location data
                                    callback.onLocationResult();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            }else {
                                // Handle the case where location is null
                                callback.onLocationResult();
                            }
                        }
                    });
        } else {
            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation(new LocationCallback() {
                    @Override
                    public void onLocationResult() {
                        // Do nothing here, as this is the callback from the permission request
                    }
                });
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}