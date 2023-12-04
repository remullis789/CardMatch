package com.zybooks.cardmatch;

import android.util.Log;

import java.util.Random;

public class CardGame {
    public int GRID_WIDTH = 3;
    public int GRID_HEIGHT = 4;
    public static int[][] mCardGrid;
    public static int score = 0;
    public int selectedCardsCounter = 0;

    public CardGame() {
        mCardGrid = new int[GRID_HEIGHT][GRID_WIDTH];
    }

    public int SelectedColor = 0;

    public void newGame(int height, int width) {
        Random rng = new Random();
        int numCards = height * width;
        // Fill the temporary array with pairs of numbers from 0 to half of total number
        // of cards
        int[] pairList = new int[numCards];
        for (int i = 0; i < numCards; i++) {
            pairList[i] = (i / 2);
        }
        // Fisher-Yates shuffle
        for (int i = numCards - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            int temp = pairList[i];
            pairList[i] = pairList[j];
            pairList[j] = temp;
        }
        // populate grid with the random numbers that will represent colors
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                mCardGrid[row][col] = pairList[row * width + col];
            }
        }
    }

    // get color value from grid
    public int getColorInt(int height, int width) {
        Log.d("CREATION", String.valueOf(mCardGrid[height][width]));
        Log.d("CREATION", height + ":" + width);
        return mCardGrid[height][width];
    }

    // incomplete----------------------------------------------------
    public void selectCard(int row, int col) {
        selectedCardsCounter = selectedCardsCounter++;
        if (selectedCardsCounter == 2) {
            if (matchingPair()) {
                increaseScore();
            } else {
                resetSelectedData();
            }
        }
    }

    public void increaseScore() {
    }

    public boolean matchingPair() {
        return false;
    }

    public void resetSelectedData() {
        selectedCardsCounter = 0;
    }

    public boolean isGameOver() {
        return (score == ((GRID_HEIGHT * GRID_WIDTH) / 2));
    }
    // incomplete----------------------------------------------------
}