package com.zybooks.cardmatch;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class GameFragment extends Fragment {
    private CardGame mGame;
    private GridLayout mCardGrid;
    //private TextView mScore;
    int mCardBackColor;
    // at its biggest of 4x5, we need 10 colors, must be assigned in onCreate
    int[] mCardFaceColor = new int[10];
    View[] selectedViewsPair = new View[2];
    int selectedViewsCounter;

    public GameFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_game, container, false);

        mCardFaceColor[0] = ContextCompat.getColor(this.requireActivity(), R.color.red);
        mCardFaceColor[1] = ContextCompat.getColor(this.requireActivity(), R.color.light_orange);
        mCardFaceColor[2] = ContextCompat.getColor(this.requireActivity(), R.color.yellow);
        mCardFaceColor[3] = ContextCompat.getColor(this.requireActivity(), R.color.light_green);
        mCardFaceColor[4] = ContextCompat.getColor(this.requireActivity(), R.color.blue);
        mCardFaceColor[5] = ContextCompat.getColor(this.requireActivity(), R.color.purple);
        mCardFaceColor[6] = ContextCompat.getColor(this.requireActivity(), R.color.pink);
        mCardFaceColor[7] = ContextCompat.getColor(this.requireActivity(), R.color.dark_orange);
        mCardFaceColor[8] = ContextCompat.getColor(this.requireActivity(), R.color.dark_green);
        mCardFaceColor[9] = ContextCompat.getColor(this.requireActivity(), R.color.teal);
        mCardBackColor = ContextCompat.getColor(this.requireActivity(), R.color.dark_teal);

        selectedViewsCounter = 0;

        // Add the same click handler to all grid buttons
        mCardGrid = parentView.findViewById(R.id.light_grid);
        for (int buttonIndex = 0; buttonIndex < mCardGrid.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) mCardGrid.getChildAt(buttonIndex);
            gridButton.setOnClickListener(this::onCardClick);
        }

        Button newGameBtn = parentView.findViewById(R.id.new_game_button);
        newGameBtn.setOnClickListener(v -> onNewGameClick(parentView));

        ImageView helpIcon = parentView.findViewById(R.id.btn_search);
        helpIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment helpMessage = new HelpDialog();
                helpMessage.show(getParentFragmentManager(), "help");
            }
        });

        mGame = new CardGame(3, 4);
        startGame();

        return parentView;
    }
/*
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE, mGame.getState());
    }

 */

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
                setScore("SCORE: " + mGame.getScore());
                if(mGame.isGameOver()){
                    Toast toast = Toast.makeText(getContext(), R.string.congrats, Toast.LENGTH_SHORT);
                    toast.show();
                }
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
        //mScore = findViewById(R.id.score_text);
        //mScore.setText("Score: 0");
        setScore("SCORE: 0");
        startGame();
    }

    public void setScore(String text){
        TextView textView = (TextView) getView().findViewById(R.id.score_text);
        textView.setText(text);
    }
}
