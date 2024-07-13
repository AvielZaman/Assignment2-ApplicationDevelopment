package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.assignment1.Models.Record;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class LoseActivity extends AppCompatActivity {

    public static final String KEY_SCORE = "KEY_SCORE";
    public static final String KEY_LATITUDE = "KEY_LATITUDE";
    public static final String KEY_LONGITUDE = "KEY_LONGITUDE";
    private Record record;
    private MaterialTextView score_LBL_status;
    private MaterialButton lose_SCORES_TABLE_BTN;
    private EditText name_text;
    private String userName= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        findViews();
        initViews();
    }

    private void initViews() {
        Intent previousActivity = getIntent();
        record = new Record();
        record.setScoreRecord(previousActivity.getIntExtra(KEY_SCORE, 0));
        record.setLatitude(previousActivity.getDoubleExtra(KEY_LATITUDE, 0));
        record.setLongitude(previousActivity.getDoubleExtra(KEY_LONGITUDE, 0));

        score_LBL_status.setText("GAME OVER! 😭\n" + record.getScoreRecord());
        lose_SCORES_TABLE_BTN.setOnClickListener(view -> changeToScoreMapActivity());
    }

    private void findViews() {
        score_LBL_status = findViewById(R.id.score_LBL_status);
        lose_SCORES_TABLE_BTN = findViewById(R.id.lose_SCORES_TABLE_BTN);
        name_text= findViewById(R.id.name_text);
    }

    private void changeToScoreMapActivity() {
        userName = name_text.getText().toString();
        Intent scoreIntent = new Intent(this, ScoreMapActivity.class);
        scoreIntent.putExtra(ScoreMapActivity.KEY_SCORE_SCORES, record.getScoreRecord());
        scoreIntent.putExtra(ScoreMapActivity.KEY_LATITUDE_SCORES, record.getLatitude());
        scoreIntent.putExtra(ScoreMapActivity.KEY_LONGITUDE_SCORES, record.getLongitude());
        scoreIntent.putExtra(ScoreMapActivity.KEY_NAME_SCORES, userName);
        startActivity(scoreIntent);
        finish();
    }
}

