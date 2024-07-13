package com.example.assignment1.Logic;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

public class GameManager {

    private int numOfCrushes = 0;
    private int lifeCount;
    private int score = 0;
    private static final int HAMBURGER_POINTS = 1;

    public GameManager(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    public int getNumOfCrushes() {
        return numOfCrushes;
    }

    public void setNumOfCrushes(int numOfCrushes) {
        this.numOfCrushes = numOfCrushes;
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameLost (){
        return getLifeCount() == getNumOfCrushes();
    }

    public boolean checkIfCrushed(AppCompatImageView [] poosLastRow, AppCompatImageView[] bikerRow) {
        // check if the biker is at the same spot with one of the last row poos
        for (int i = 0; i < poosLastRow.length ; i++) {
            if (poosLastRow[i].getVisibility() == View.VISIBLE &&
                    bikerRow[i].getVisibility() == View.VISIBLE)
            {
                numOfCrushes++;
                return true;
            }
        }
        return false;
    }

    public void checkIfGotHamburger(AppCompatImageView [] hamburgersLastRow, AppCompatImageView[] bikerRow) {
        // check if the biker is at the same spot with one of the last row hamburgers
        for (int i = 0; i < hamburgersLastRow.length ; i++) {
            if (hamburgersLastRow[i].getVisibility() == View.VISIBLE &&
                    bikerRow[i].getVisibility() == View.VISIBLE) {
                score += HAMBURGER_POINTS;
                hamburgersLastRow[i].setVisibility(View.INVISIBLE);
            }

        }
    }


}
