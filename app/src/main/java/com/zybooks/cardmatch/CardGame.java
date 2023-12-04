package com.zybooks.cardmatch;

import android.util.Log;

import java.util.Random;

public class CardGame {
    public static int GRID_WIDTH = 3;
    public static int GRID_HEIGHT = 4;
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
        int[] pairList = new int[numCards];
        // Fill the array with pairs of numbers
        for (int i = 0; i < numCards; i++) {
            pairList[i] = (i / 2);
        }
        // Shuffle the array
        for (int i = numCards - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            int temp = pairList[i];
            pairList[i] = pairList[j];
            pairList[j] = temp;
        }
        // Step 3: Assign the numbers to the grid
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                mCardGrid[row][col] = pairList[row * width + col];
            }
        }
    }

    public static int getColorInt(int height, int width) {
        Log.d("CREATION", String.valueOf(mCardGrid[height][width]));
        Log.d("CREATION", height + ":" + width);
        return mCardGrid[height][width];
    }

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
}