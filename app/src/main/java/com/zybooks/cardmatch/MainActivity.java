package com.zybooks.cardmatch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private CardGame mGame;
    private GridLayout mCardGrid;
    int mCardBackColor;
    // at its biggest of 4x5, we need 10 colors, nust be assigned in onCreate
    int[] mCardFaceColor = new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCardFaceColor[0] = ContextCompat.getColor(this, R.color.red);
        mCardFaceColor[1] = ContextCompat.getColor(this, R.color.light_orange);
        mCardFaceColor[2] = ContextCompat.getColor(this, R.color.yellow);
        mCardFaceColor[3] = ContextCompat.getColor(this, R.color.light_green);
        mCardFaceColor[4] = ContextCompat.getColor(this, R.color.blue);
        mCardFaceColor[5] = ContextCompat.getColor(this, R.color.purple);
        mCardFaceColor[6] = ContextCompat.getColor(this, R.color.pink);
        mCardFaceColor[7] = ContextCompat.getColor(this, R.color.dark_orange);
        mCardFaceColor[8] = ContextCompat.getColor(this, R.color.dark_green);
        mCardFaceColor[9] = ContextCompat.getColor(this, R.color.teal);
        mCardBackColor = ContextCompat.getColor(this, R.color.dark_teal);

        setContentView(R.layout.activity_main);
        mCardGrid = findViewById(R.id.light_grid);

        // Add the same click handler to all grid buttons
        for (int buttonIndex = 0; buttonIndex < mCardGrid.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) mCardGrid.getChildAt(buttonIndex);
            gridButton.setOnClickListener(this::onCardClick);
        }
        mGame = new CardGame();
        startGame();
    }

    private void startGame() {
        mGame.newGame(mGame.GRID_HEIGHT, mGame.GRID_WIDTH);
        setCardsFaceDown();
    }

    private void onCardClick(View view) {
        // Find the button's row and col
        int buttonIndex = mCardGrid.indexOfChild(view);
        int row = buttonIndex / mGame.GRID_WIDTH;
        int col = buttonIndex % mGame.GRID_WIDTH;
        // pass info to mGame
        mGame.selectCard(row, col);
        // view is the button
        flipToFace(view, row, col);
        /*
         * if (mGame.isGameOver()) {
         * Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show();
         * }
         */
    }

    void flipToFace(View view, int row, int col) {
        // match color value to grid color number randomly asssigned in CardGame
        int faceColor = mCardFaceColor[mGame.getColorInt(row, col)];
        view.setBackgroundColor(faceColor);
    }

    private void setCardsFaceDown() {
        for (int buttonIndex = 0; buttonIndex < mCardGrid.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) mCardGrid.getChildAt(buttonIndex);
            // Find the button's row and col
            int row = buttonIndex / mGame.GRID_WIDTH;
            int col = buttonIndex % mGame.GRID_WIDTH;
            gridButton.setBackgroundColor(mCardBackColor);
        }
    }

    public void onNewGameClick(View view) {
        startGame();
    }

    /*
     * public void onHelpClick(View view) {
     * Intent intent = new Intent(this, HelpActivity.class);
     * startActivity(intent);
     * }
     * 
     * public void onChangeColorClick(View view) {
     * // Send the current color ID to ColorActivity
     * Intent intent = new Intent(this, ColorActivity.class);
     * intent.putExtra(ColorActivity.EXTRA_COLOR, mLightOnColorId);
     * mColorResultLauncher.launch(intent);
     * // save
     * }
     * 
     * ActivityResultLauncher<Intent> mColorResultLauncher =
     * registerForActivityResult(
     * new ActivityResultContracts.StartActivityForResult(), new
     * ActivityResultCallback<ActivityResult>() {
     * 
     * @Override
     * public void onActivityResult(ActivityResult result) {
     * if (result.getResultCode() == Activity.RESULT_OK) {
     * Intent data = result.getData();
     * if (data != null) {
     * // Create the "on" button color from the chosen color ID from ColorActivity
     * mLightOnColorId = data.getIntExtra(ColorActivity.EXTRA_COLOR,
     * R.color.yellow);
     * mLightOnColor = ContextCompat.getColor(MainActivity.this, mLightOnColorId);
     * setButtonColors(mLightOnColor);
     * }
     * }
     * }
     * });
     */
}