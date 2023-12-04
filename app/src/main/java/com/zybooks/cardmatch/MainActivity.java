package com.zybooks.cardmatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private CardGame mGame;
    private GridLayout mCardGrid;
    private int mBackColor;

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_main);
        mCardGrid = findViewById(R.id.light_grid);

        // Add the same click handler to all grid buttons
        for (int buttonIndex = 0; buttonIndex < mCardGrid.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) mCardGrid.getChildAt(buttonIndex);
            gridButton.setOnClickListener(this::onLightButtonClick);
        }

        mBackColor = ContextCompat.getColor(this, R.color.dark_teal);
        mGame = new LightsOutGame();
    }

    private void startGame() {
        mGame.newGame();
        setCardColors();
    }

    private void onLightButtonClick(View view) {

        // Find the button's row and col
        int buttonIndex = mCardGrid.indexOfChild(view);
        int row = buttonIndex / LightsOutGame.GRID_SIZE;
        int col = buttonIndex % LightsOutGame.GRID_SIZE;

        mGame.selectLight(row, col);
        setButtonColors(mLightOnColor);

        // Congratulate the user if the game is over
        if (mGame.isGameOver()) {
            Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show();
        }
    }

    private void setCardColors(int[] mLightOnColor) {

        for (int buttonIndex = 0; buttonIndex < mCardGrid.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) mCardGrid.getChildAt(buttonIndex);

            // Find the button's row and col
            int row = buttonIndex / LightsOutGame.GRID_WIDTH;
            int col = buttonIndex / LightsOutGame.GRID_HEIGHT;

            switch (mGame.getSelectedColorNumber(row, col)) {
                case 0:
                    gridButton.setBackgroundColor(R.color.red);
                    break;
                case 1:
                    gridButton.setBackgroundColor(R.color.dark_orange);
                    break;
                case 2:
                    gridButton.setBackgroundColor(R.color.light_orange);
                    break;
                case 3:
                    gridButton.setBackgroundColor(R.color.yellow);
                    break;
                case 4:
                    gridButton.setBackgroundColor(R.color.light_green);
                    break;
                case 5:
                    gridButton.setBackgroundColor(R.color.dark_green);
                    break;
                case 6:
                    gridButton.setBackgroundColor(R.color.teal);
                    break;
                case 7:
                    gridButton.setBackgroundColor(R.color.blue);
                    break;
                case 8:
                    gridButton.setBackgroundColor(R.color.purple);
                    break;
                case 9:
                    gridButton.setBackgroundColor(R.color.pink);
                    break;
                default:
                    gridButton.setBackgroundColor(R.color.light_grey);
                    break;
            }
        }
    }

    public void onNewGameClick(View view) {
        startGame();
    }

    public void onHelpClick(View view) {
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
            });
}