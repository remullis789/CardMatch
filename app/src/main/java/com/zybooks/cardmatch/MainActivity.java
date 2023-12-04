package com.zybooks.cardmatch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private CardGame mGame;
    private GridLayout mCardGrid;
    int mCardBackColor = ContextCompat.getColor(this, R.color.dark_teal);
    int[] mCardFaceColor = { ContextCompat.getColor(this, R.color.red),
            ContextCompat.getColor(this, R.color.dark_orange),
            ContextCompat.getColor(this, R.color.light_orange),
            ContextCompat.getColor(this, R.color.yellow),
            ContextCompat.getColor(this, R.color.light_green),
            ContextCompat.getColor(this, R.color.dark_green),
            ContextCompat.getColor(this, R.color.teal),
            ContextCompat.getColor(this, R.color.blue),
            ContextCompat.getColor(this, R.color.purple),
            ContextCompat.getColor(this, R.color.pink) };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mCardGrid = findViewById(R.id.light_grid);

        // Add the same click handler to all grid buttons
        for (int buttonIndex = 0; buttonIndex < mCardGrid.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) mCardGrid.getChildAt(buttonIndex);
            gridButton.setOnClickListener(this::onCardClick);
        }
        mGame = new CardGame();
    }

    private void startGame() {
        mGame.newGame(mGame.GRID_WIDTH, mGame.GRID_HEIGHT);
        setCardsFaceDown();
    }

    private void onCardClick(View view) {

        // Find the button's row and col
        int buttonIndex = mCardGrid.indexOfChild(view);
        int row = buttonIndex / CardGame.GRID_WIDTH;
        int col = buttonIndex % CardGame.GRID_HEIGHT;

        mGame.selectCard(row, col);
        if (cardNotFlipped(view, row, col)) {
            flipToFace(view, row, col);
        }

        // Congratulate the user if the game is over
        if (mGame.isGameOver()) {
            Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean cardNotFlipped(View view, int row, int col) {
        ColorDrawable background = (ColorDrawable) view.getBackground();
        int color = background.getColor();
        return color != mCardBackColor;
    }

    void flipToFace(View view, int row, int col) {
        int faceColor = mCardFaceColor[CardGame.mCardGrid[row][col]];
        view.setBackgroundColor(faceColor);
    }

    private void setCardsFaceDown() {

        for (int buttonIndex = 0; buttonIndex < mCardGrid.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) mCardGrid.getChildAt(buttonIndex);

            // Find the button's row and col
            int row = buttonIndex / CardGame.GRID_WIDTH;
            int col = buttonIndex % CardGame.GRID_HEIGHT;

            gridButton.setBackgroundColor(mCardBackColor);
        }
    }

    public void onNewGameClick(View view) {
        startGame();
    }

    /*public void onHelpClick(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void onChangeColorClick(View view) {
        // Send the current color ID to ColorActivity
        Intent intent = new Intent(this, ColorActivity.class);
        intent.putExtra(ColorActivity.EXTRA_COLOR, mLightOnColorId);
        mColorResultLauncher.launch(intent);
        // save
    }

    ActivityResultLauncher<Intent> mColorResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            // Create the "on" button color from the chosen color ID from ColorActivity
                            mLightOnColorId = data.getIntExtra(ColorActivity.EXTRA_COLOR, R.color.yellow);
                            mLightOnColor = ContextCompat.getColor(MainActivity.this, mLightOnColorId);
                            setButtonColors(mLightOnColor);
                        }
                    }
                }
            });*/
}