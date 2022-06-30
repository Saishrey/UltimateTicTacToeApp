package com.example.android.ultimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class EasyComputerActivity extends AppCompatActivity {

    private final List<int[]> combinationsList = new ArrayList<>();

    private volatile int[] boxPositions;

    private volatile int humanTurn, computerTurn, playerTurn;

    private volatile int totalSelectedBoxes = 1;

    private LinearLayout playerOneLayout, playerTwoLayout;
    private TextView playerOneName,  playerTwoName, scoreCard;
    private volatile ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    private volatile int humanScore = 0, computerScore = 0;

    private String humanPlayAs, computerPlayAs;

    private Vibrator myVib;

    private int humanImageResourceId, aiImageResourceId;
    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.ai_easy_title);
        setTheme(R.style.AIEasyAppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        humanPlayAs = extras.getString("PLAY_AS");

        String player1TitleCardName, player2TitleCardName;
        if(humanPlayAs.equals("X")) {
            humanTurn = 1;
            computerTurn = 2;
            computerPlayAs = "O";
            player1TitleCardName = "You";
            player2TitleCardName = "AI";
            humanImageResourceId = R.drawable.cross_icon;
            aiImageResourceId = R.drawable.zero_icon;
        }
        else {
            computerTurn = 1;
            humanTurn = 2;
            computerPlayAs = "X";
            player1TitleCardName = "AI";
            player2TitleCardName = "You";
            aiImageResourceId = R.drawable.cross_icon;
            humanImageResourceId = R.drawable.zero_icon;
        }


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
                    humansTurn(image1, 0);
                }
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(1)) {
                    myVib.vibrate(50);
                    humansTurn(image2, 1);
                }
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(2)) {
                    myVib.vibrate(50);
                    humansTurn(image3, 2);
                }
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(3)) {
                    myVib.vibrate(50);
                    humansTurn(image4, 3);
                }
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(4)) {
                    myVib.vibrate(50);
                    humansTurn(image5, 4);
                }
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(5)) {
                    myVib.vibrate(50);
                    humansTurn(image6, 5);
                }
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(6)) {
                    myVib.vibrate(50);
                    humansTurn(image7, 6);
                }
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(7)) {
                    myVib.vibrate(50);
                    humansTurn(image8, 7);
                }
            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(8)) {
                    myVib.vibrate(50);
                    humansTurn(image9, 8);
                }
            }
        });
    }

    public void startGame() {
        boxPositions = new int[9];

        totalSelectedBoxes = 1;

        playerTurn = 1;


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

        if(humanPlayAs.equals("O")) {
            mHandler.postDelayed(computersTurnEasy, 500);
        }
    }

    private boolean isBoxSelectable(int boxPosition) {
        return boxPositions[boxPosition] == 0;
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

    private void setScore() {
        if(humanPlayAs.equals("X")) {
            scoreCard.setText(humanScore + " - " + computerScore);
        }
        else {
            scoreCard.setText(computerScore + " - " + humanScore);
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

    private List<Integer> getAvailableMoves() {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            if(boxPositions[i] == 0) {
                res.add(i);
            }
        }
        return res;
    }

    private final Runnable computersTurnEasy = new Runnable() {
        @Override
        public void run() {
            List<Integer> moves = getAvailableMoves();
            Random rand = new Random();
            int randomMove = moves.get(rand.nextInt(moves.size()));

            boxPositions[randomMove] = playerTurn;

            switch(randomMove) {
                case 0:
                    image1.setImageResource(aiImageResourceId);
                    break;
                case 1:
                    image2.setImageResource(aiImageResourceId);
                    break;
                case 2:
                    image3.setImageResource(aiImageResourceId);
                    break;
                case 3:
                    image4.setImageResource(aiImageResourceId);
                    break;
                case 4:
                    image5.setImageResource(aiImageResourceId);
                    break;
                case 5:
                    image6.setImageResource(aiImageResourceId);
                    break;
                case 6:
                    image7.setImageResource(aiImageResourceId);
                    break;
                case 7:
                    image8.setImageResource(aiImageResourceId);
                    break;
                case 8:
                    image9.setImageResource(aiImageResourceId);
            }

            if(checkPlayerWin()) {
                computerScore++;
                setScore();
                WinDialog winDialog = new WinDialog(EasyComputerActivity.this, computerPlayAs + " wins.", EasyComputerActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else if(totalSelectedBoxes == 9) {
                WinDialog winDialog = new WinDialog(EasyComputerActivity.this, "Match Draw.", EasyComputerActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else {
                changePlayerTurn(humanTurn);
                totalSelectedBoxes++;
            }
        }
    };

    private void humansTurn(ImageView imageView, int selectBoxPosition) {
        boxPositions[selectBoxPosition] = playerTurn;
        imageView.setImageResource(humanImageResourceId);

        if(checkPlayerWin()) {
            humanScore++;
            setScore();
            WinDialog winDialog = new WinDialog(EasyComputerActivity.this, humanPlayAs + " wins.", EasyComputerActivity.this);
            winDialog.setCancelable(false);
            winDialog.show();
        }
        else if(totalSelectedBoxes == 9) {
            WinDialog winDialog = new WinDialog(EasyComputerActivity.this, "Match Draw.", EasyComputerActivity.this);
            winDialog.setCancelable(false);
            winDialog.show();
        }
        else {
            changePlayerTurn(computerTurn);
            totalSelectedBoxes++;

            mHandler.postDelayed(computersTurnEasy, 500);
        }
    }

    public void goToMenu() {
        finish();
    }

}