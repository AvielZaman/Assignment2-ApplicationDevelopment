package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class MenuActivity extends AppCompatActivity {

    private SwitchMaterial menu_sensorButtons_switch;
    private SwitchMaterial menu_slowFast_switch;
    private MaterialButton menu_EnterGame_BTN;
    private MaterialButton menu_SCORES_TABLE_BTN;

    private String[] modes = new String[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViews();
        initViews();

    }

    private void initViews() {
        modes[0] = "button";
        modes[1] = "slow";
        menu_sensorButtons_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(Objects.equals(modes[0], "button"))
                    modes[0] = "sensor";
                else
                    modes[0] = "button";
            }
        });
        menu_slowFast_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(Objects.equals(modes[1], "slow"))
                    modes[1] = "fast";
                else
                    modes[1] = "slow";
            }
        });
        menu_EnterGame_BTN.setOnClickListener(view -> changeToMainActivity(modes));
        menu_SCORES_TABLE_BTN.setOnClickListener(view -> changeToScoresActivity());


    }

    private void changeToScoresActivity() {
        Intent scoreIntent = new Intent(this, ScoreMapActivity.class);
        startActivity(scoreIntent);
        finish();
    }


    private void changeToMainActivity(String[] modes) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra(MainActivity.KEY_MODES, modes);
        startActivity(mainIntent);
        finish();
    }

    private void findViews() {
        menu_sensorButtons_switch = findViewById(R.id.menu_sensorButtons_switch);
        menu_slowFast_switch = findViewById(R.id.menu_slowFast_switch);
        menu_EnterGame_BTN = findViewById(R.id.menu_EnterGame_BTN);
        menu_SCORES_TABLE_BTN = findViewById(R.id.menu_SCORES_TABLE_BTN);
    }
}