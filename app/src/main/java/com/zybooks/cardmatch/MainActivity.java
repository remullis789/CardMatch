package com.zybooks.cardmatch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();

            AppBarConfiguration appBarConfig = new AppBarConfiguration.Builder(
                    R.id.navigation_game, R.id.navigation_difficulty, R.id.navigation_help)
                    .build();

            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
            NavigationUI.setupWithNavController(navView, navController);
        }

    }



    /*
    private void startGame() {
        mGame.newGame(mGame.GRID_HEIGHT, mGame.GRID_WIDTH);
        setCardsFaceDown();
    }

    private void onCardClick(View view) {
        // Find the button's row and col
        int buttonIndex = mCardGrid.indexOfChild(view);
        int row = buttonIndex / mGame.GRID_WIDTH;
        int col = buttonIndex % mGame.GRID_WIDTH;
        if(mGame.isNotMatched(row, col) && mGame.isNotSelected(row, col)) {
            selectedViewsPair[selectedViewsCounter] = view;
            selectedViewsCounter++;
            // pass selected info to mGame, checks if selected card was a match
            boolean wasMatch = mGame.selectCard(row, col);
            flipToFace(view, row, col);
            if ((selectedViewsCounter == 2) && (!wasMatch)) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        flipPairToBack(selectedViewsPair);
                        selectedViewsCounter = 0;
                    }
                }, 500);

            } else if ((selectedViewsCounter == 2) && (wasMatch)) {
                selectedViewsCounter = 0;
                mScore = findViewById(R.id.score_text);
                mScore.setText("Score: " + mGame.getScore());
            }
        }
    }

    private void flipPairToBack(View[] viewPair) {
        viewPair[0].setBackgroundColor(mCardBackColor);
        viewPair[1].setBackgroundColor(mCardBackColor);
    }

    void flipToFace(View view, int row, int col) {
        // match color value to grid color number randomly assigned in CardGame
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
        mScore = findViewById(R.id.score_text);
        mScore.setText("Score: 0");
        startGame();
    }

     */

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