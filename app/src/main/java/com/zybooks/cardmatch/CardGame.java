package com.zybooks.cardmatch;

import java.util.Random;

public class CardGame {
    public static int GRID_WIDTH = 3;
    public static int GRID_HEIGHT = 4;
    public static int[][] mCardGrid;
    public static int score = 0;
    public int selectedCardsCounter = 0;

    public CardGame() {
        mCardGrid = new int[GRID_WIDTH][GRID_HEIGHT];
    }

    public int SelectedColor = 0;

    public void newGame(int grid_X, int grid_Y) {
        Random randomNumGenerator = new Random();
        int n = (grid_X * grid_Y) / 2;
        for (int row = 0; row < grid_X; row++) {
            for (int col = 0; col < grid_Y; col++) {
                mCardGrid[row][col] = randomNumGenerator.nextInt(n) + 1;
            }
        }
    }

    public int getColorInt(int x, int y) {
        return mCardGrid[x][y];
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