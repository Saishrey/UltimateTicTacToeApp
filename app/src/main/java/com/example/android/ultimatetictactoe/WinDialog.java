package com.example.android.ultimatetictactoe;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class WinDialog extends Dialog {

    private final String message;
    private MultiplayerActivity multiplayerActivity;
    private EasyComputerActivity easyComputerActivity;
    private HardComputerActivity hardComputerActivity;
    private int flag;

    public WinDialog(@NonNull Context context, String message, MultiplayerActivity multiplayerActivity) {
        super(context);
        this.message = message;
        this.multiplayerActivity = multiplayerActivity;
        this.flag = 1;
    }

    public WinDialog(@NonNull Context context, String message, EasyComputerActivity easyComputerActivity) {
        super(context);
        this.message = message;
        this.easyComputerActivity = easyComputerActivity;
        this.flag = 2;
    }

    public WinDialog(@NonNull Context context, String message, HardComputerActivity hardComputerActivity) {
        super(context);
        this.message = message;
        this.hardComputerActivity = hardComputerActivity;
        this.flag = 3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.win_dialog_layout);

        final TextView messageText = findViewById(R.id.message_text);
        final Button startAgainButton = findViewById(R.id.start_again_button);
        final Button mainMenuButton = findViewById(R.id.main_menu_button);

        messageText.setText(message);

        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 1) {
                    multiplayerActivity.startGame();
                }
                else if(flag == 2) {
                    easyComputerActivity.startGame();
                }
                else {
                    hardComputerActivity.startGame();
                }

                dismiss();
            }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 1) {
                    multiplayerActivity.goToMenu();
                }
                else if(flag == 2) {
                    easyComputerActivity.goToMenu();
                }
                else {
                    hardComputerActivity.goToMenu();
                }
                dismiss();
            }
        });

    }
}
