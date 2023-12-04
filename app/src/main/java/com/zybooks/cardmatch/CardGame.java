package com.zybooks.cardmatch;

import java.util.Random;

public class CardGame {
    public int GRID_WIDTH = 3;
    public int GRID_HEIGHT = 4;
    private int[][] mCardGrid;
    // selectedCardsData{number of selected cards, 1st card, 2nd card}
    // 1st/2nd card{color, x value, y value}
    public int selectedCardsData[][] = { { -1, -1, -1 }, { -1, -1, -1 } };
    public int selectedCardsCounter = 0;

    public CardGame() {
        mCardGrid = new int[GRID_WIDTH][GRID_HEIGHT];
    }

    public int SelectedColor = 0;

    public void newGame(int grid_X, int grid_Y) {
        Random randomNumGenerator = new Random();
        int n = (grid_X * grid_Y) / 2;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                mCardGrid[row][col] = randomNumGenerator.nextInt(n) + 1;
            }
        }
    }

    public int getSelectedColorNumber(int x, int y) {
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

    public boolean matchingPair() {
        return (selectedCardsData[1][0] == selectedCardsData[0][0]);
    }

    public void resetSelectedData() {
        selectedCardsCounter = 0;
        selectedCardsData[0][0] = -1;
        selectedCardsData[0][1] = -1;
        selectedCardsData[0][2] = -1;
        selectedCardsData[1][0] = -1;
        selectedCardsData[1][1] = -1;
        selectedCardsData[1][2] = -1;
    }
}
