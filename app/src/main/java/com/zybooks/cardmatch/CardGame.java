package com.zybooks.cardmatch;

import android.util.Log;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class CardGame extends Fragment {
    public int GRID_WIDTH = 3;
    public int GRID_HEIGHT = 4;
    public static int[][] mCardGrid;
    public static int score = 0;
    public int selectedCardsCounter = 0;
    // {1st row, 1st col, 2nd row, 2nd col}
    public int[] selectedCards = {-1, -1, -1, -1};
    public boolean[][] matchedCards = new boolean[GRID_HEIGHT][GRID_WIDTH];

    public CardGame() {
        mCardGrid = new int[GRID_HEIGHT][GRID_WIDTH];
    }

    public int SelectedColor = 0;

    public void newGame(int height, int width) {
        score = 0;
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
                matchedCards[row][col] = false;
            }
        }

    }

    // get color value from grid
    public int getColorInt(int height, int width) {
        return mCardGrid[height][width];
    }

    public boolean isNotMatched(int row, int col){
        return !matchedCards[row][col];
    }

    public boolean isNotSelected(int row, int col){
        // If one card has been selected
        // check if the passed card has not been selected
        if(selectedCardsCounter == 1){
            if(row == selectedCards[0] && col == selectedCards[1])
                return false;
        }
        return true;
    }

    // incomplete----------------------------------------------------
    // does too much, need to split
    public boolean selectCard(int row, int col) {
        // only lets user interact with unmatched cards
        if(!matchedCards[row][col]) {
            selectedCardsCounter++;
            Log.d("CREATION", String.valueOf(selectedCardsCounter));
            // determine if user selected 1st or 2nd card, record
            if (selectedCardsCounter == 1) {
                selectedCards[0] = row;
                selectedCards[1] = col;
            } else if (selectedCardsCounter == 2) {
                selectedCards[2] = row;
                selectedCards[3] = col;

                if (matchingPair()) {
                    increaseScore();
                    resetSelectedData(); // Reset after handling a pair
                    return true;
                }
                resetSelectedData(); // Reset if not a pair
                return false;
            }
        }
        return false; // Return false if the card is already matched
    }

    public void increaseScore() {
        score++;
    }

    public int getScore(){
        return score;
    }

    public boolean matchingPair() {
        int row_1 = selectedCards[0];
        int col_1 = selectedCards[1];
        int row_2 = selectedCards[2];
        int col_2 = selectedCards[3];

        Log.d("CREATION", String.valueOf(mCardGrid[row_1][col_1] == mCardGrid[row_2][col_2]));
        if(mCardGrid[row_1][col_1] == mCardGrid[row_2][col_2]){
            matchedCards[row_1][col_1] = true;
            matchedCards[row_2][col_2] = true;
            return true;
        }
        return false;
    }

    private void resetSelectedData() {
        selectedCardsCounter = 0;
        selectedCards[0] = selectedCards[1] = selectedCards[2] = selectedCards[3] = -1;
    }


    public boolean isGameOver() {
        return (score == ((GRID_HEIGHT * GRID_WIDTH) / 2));
    }
    // incomplete----------------------------------------------------
}