package com.example.android.ultimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiplayerActivity extends AppCompatActivity {


    private final List<int[]> combinationsList = new ArrayList<>();

    private volatile int[] boxPositions;

    private volatile int playerTurn = 1;

    private volatile int totalSelectedBoxes = 1;

    private LinearLayout playerOneLayout, playerTwoLayout;
    private TextView playerOneName,  playerTwoName, scoreCard;
    private volatile ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    private volatile int playerOneScore = 0, playerTwoScore = 0;

    private Vibrator myVib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.multiplayer_title);
        setTheme(R.style.MPAppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String player1TitleCardName = "Player 1";
        String player2TitleCardName = "Player 2";

        playerOneName = findViewById(R.id.player_one_name);
        playerTwoName = findViewById(R.id.player_two_name);

        playerOneName.setText(player1TitleCardName);
        playerTwoName.setText(player2TitleCardName);

        scoreCard = findViewById(R.id.score_card);

        playerOneLayout = findViewById(R.id.player_one_layout);
        playerTwoLayout = findViewById(R.id.player_two_layout);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);

        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{0, 4, 8});
        combinationsList.add(new int[]{2, 4, 6});

        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        startGame();

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(0)) {
                    myVib.vibrate(50);
                    performAction((ImageView)view, 0);
                }
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(1)) {
                    myVib.vibrate(50);
                    performAction((ImageView)view, 1);
                }
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(2)) {
                    myVib.vibrate(50);
                    performAction((ImageView)view, 2);
                }
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(3)) {
                    myVib.vibrate(50);
                    performAction((ImageView)view, 3);
                }
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(4)) {
                    myVib.vibrate(50);
                    performAction((ImageView)view, 4);
                }
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(5)) {
                    myVib.vibrate(50);
                    performAction((ImageView)view, 5);
                }
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(6)) {
                    myVib.vibrate(50);
                    performAction((ImageView)view, 6);
                }
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(7)) {
                    myVib.vibrate(50);
                    performAction((ImageView)view, 7);
                }
            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(8)) {
                    myVib.vibrate(50);
                    performAction((ImageView)view, 8);
                }
            }
        });
    }

    private void performAction(ImageView imageView, int selectBoxPosition) {
        boxPositions[selectBoxPosition] = playerTurn;
        if(playerTurn == 1) {
            imageView.setImageResource(R.drawable.cross_icon);

            if(checkPlayerWin()) {
                playerOneScore++;
                setScore();
                WinDialog winDialog = new WinDialog(MultiplayerActivity.this, "X wins.", MultiplayerActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else if(totalSelectedBoxes == 9) {
                WinDialog winDialog = new WinDialog(MultiplayerActivity.this, "Match Draw.", MultiplayerActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else {
                changePlayerTurn(2);

                totalSelectedBoxes++;
            }
        }
        else {
            imageView.setImageResource(R.drawable.zero_icon);

            if(checkPlayerWin()) {
                playerTwoScore++;
                setScore();
                WinDialog winDialog = new WinDialog(MultiplayerActivity.this, "O wins.", MultiplayerActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else if(selectBoxPosition == 9) {
                WinDialog winDialog = new WinDialog(MultiplayerActivity.this, "Match Draw.", MultiplayerActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;

        if(playerTurn == 1) {
            playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
        else {
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
    }

    private boolean checkPlayerWin() {
        for(int i = 0; i < combinationsList.size(); i++) {
            final int[] combination = combinationsList.get(i);

            if(boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn && boxPositions[combination[2]] == playerTurn) {
                return true;
            }
        }

        return false;
    }

    private boolean isBoxSelectable(int boxPosition) {
        return boxPositions[boxPosition] == 0;
    }

    public void startGame() {
        boxPositions = new int[9];

        playerTurn = 1;

        totalSelectedBoxes = 1;

        image1.setImageResource(R.drawable.transparent_background);
        image2.setImageResource(R.drawable.transparent_background);
        image3.setImageResource(R.drawable.transparent_background);
        image4.setImageResource(R.drawable.transparent_background);
        image5.setImageResource(R.drawable.transparent_background);
        image6.setImageResource(R.drawable.transparent_background);
        image7.setImageResource(R.drawable.transparent_background);
        image8.setImageResource(R.drawable.transparent_background);
        image9.setImageResource(R.drawable.transparent_background);

        playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border);
        playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue);

    }

    private void setScore() {
        scoreCard.setText(playerOneScore + " - " + playerTwoScore);
    }



    public void goToMenu() {
//        Intent intent = new Intent(MultiplayerActivity.this, MainMenu.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//        if(Build.VERSION.SDK_INT>=16 && Build.VERSION.SDK_INT<21){
//            finishAffinity();
//        } else if(Build.VERSION.SDK_INT>=21){
//            finishAndRemoveTask();
//        }
        finish();
    }

}