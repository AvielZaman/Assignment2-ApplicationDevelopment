package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.assignment1.Fragments.MapFragment;
import com.example.assignment1.Fragments.ScoresFragment;
import com.example.assignment1.Models.Record;
import com.google.android.material.button.MaterialButton;

public class ScoreMapActivity extends AppCompatActivity {
    public static final String KEY_SCORE_SCORES = "KEY_SCORE_SCORES";
    public static final String KEY_LATITUDE_SCORES = "KEY_LATITUDE_SCORES";
    public static final String KEY_LONGITUDE_SCORES = "KEY_LONGITUDE_SCORES";
    public static final String KEY_NAME_SCORES = "KEY_NAME_SCORES";
    private Record record;
    private FrameLayout main_FRAME_scores;
    private FrameLayout main_FRAME_map;
    private MaterialButton score_TO_MENU_BTN;


    private ScoresFragment scoresFragment;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_map);

        findViews();
        initViews();


    }


    private void findViews() {
        main_FRAME_scores = findViewById(R.id.main_FRAME_scores);
        main_FRAME_map = findViewById(R.id.main_FRAME_map);
        score_TO_MENU_BTN= findViewById(R.id.score_TO_MENU_BTN);
    }

    private void initViews() {
        score_TO_MENU_BTN.setOnClickListener(view -> changeToMenuActivity());
        Intent previousActivity = getIntent();
        Record recordForFragment = new Record();
        recordForFragment.setScoreRecord(previousActivity.getIntExtra(KEY_SCORE_SCORES, 0));
        recordForFragment.setLatitude(previousActivity.getDoubleExtra(KEY_LATITUDE_SCORES, 0));
        recordForFragment.setLongitude(previousActivity.getDoubleExtra(KEY_LONGITUDE_SCORES, 0));
        recordForFragment.setName(previousActivity.getStringExtra(KEY_NAME_SCORES));

        scoresFragment = new ScoresFragment();
        scoresFragment.setCallbackScoresItemClicked(record ->
        {mapFragment.changeCameraPosition(record.getLatitude(), record.getLongitude());
        });
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_scores, scoresFragment).commit();
        scoresFragment.setData(recordForFragment);

        mapFragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_map, mapFragment).commit();
    }

    private void changeToMenuActivity() {
        Intent menuIntent = new Intent(this, MenuActivity.class);
        startActivity(menuIntent);
        finish();
    }

}