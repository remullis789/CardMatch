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
    public boolean[][] selectedCards = new boolean[GRID_HEIGHT][GRID_WIDTH];
    public boolean[][] matchedCards = new boolean[GRID_HEIGHT][GRID_WIDTH];

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

    public boolean selectCard(int row, int col) {
        if(!matchedCards[row][col] && !selectedCards[row][col]) {
            selectedCardsCounter = selectedCardsCounter++;
            selectedCards[row][col] = true;
            if (selectedCardsCounter == 2) {
                if (matchingPair()) {
                    increaseScore();
                    return true;
                } else {
                    resetSelectedData();
                }
            }
        }
        return false;
    }

    public void increaseScore() {
    }

    public boolean matchingPair() {
        int cardOneRow = -1;
        int cardOneCol = -1;
        int cardTwoRow = -1;
        int cardTwoCol = -1;

        for(int i = 0; i < GRID_HEIGHT; i++){
            for(int j = 0; i < GRID_WIDTH; j++){
                if(selectedCards[i][j]){
                    if (cardOneRow == -1) {
                        cardOneRow = i;
                        cardOneCol = j;
                    } else {
                        cardTwoRow = i;
                        cardTwoCol = j;
                    }
                }
            }
        }

        if(getColorInt(cardOneRow, cardOneCol) == getColorInt(cardTwoRow, cardTwoCol)){
            matchedCards[cardOneRow][cardOneCol] = true;
            matchedCards[cardTwoRow][cardTwoCol] = true;
            selectedCards[cardOneRow][cardOneCol] = false;
            selectedCards[cardTwoRow][cardTwoCol] = false;
            selectedCardsCounter = 0;
            return true;
        }
        selectedCards[cardOneRow][cardOneCol] = false;
        selectedCards[cardTwoRow][cardTwoCol] = false;
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