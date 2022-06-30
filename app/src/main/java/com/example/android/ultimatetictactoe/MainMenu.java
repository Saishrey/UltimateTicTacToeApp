package com.example.android.ultimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final Button easyComputerButton = findViewById(R.id.play_against_ai_easy);
        final Button hardComputerButton = findViewById(R.id.play_against_ai_hard);
        final Button multiplayerButton = findViewById(R.id.multiplayer_button);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String playAs = sharedPrefs.getString(
                getString(R.string.settings_play_as_key),
                getString(R.string.settings_play_as_default_value));

        Log.e("MAIN_MENU", playAs + " - play as");

        easyComputerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent easyComputerActivityIntent = new Intent(MainMenu.this, EasyComputerActivity.class);
                easyComputerActivityIntent.putExtra("PLAY_AS", playAs);
                startActivity(easyComputerActivityIntent);
            }
        });

        hardComputerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hardComputerActivityIntent = new Intent(MainMenu.this, HardComputerActivity.class);
                hardComputerActivityIntent.putExtra("PLAY_AS", playAs);
                startActivity(hardComputerActivityIntent);
            }
        });

        multiplayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent multiplayerActivityIntent = new Intent(MainMenu.this, MultiplayerActivity.class);
                startActivity(multiplayerActivityIntent);
            }
        });
    }



    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press BACK again to exit.", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}