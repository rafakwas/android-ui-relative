package com.example.ralphigi.relative;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.math.BigDecimal;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RelativeLayout mainLayout;

    Button randomButton;
    ToggleButton toggleButton;
    SeekBar redBar;
    SeekBar blueBar;
    SeekBar greenBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = findViewById(R.id.relativeLayout);

        toggleButton = findViewById(R.id.toggleButton);
        randomButton = findViewById(R.id.button);
        redBar = findViewById(R.id.redBar);
        greenBar = findViewById(R.id.greenBar);
        blueBar = findViewById(R.id.blueBar);

        makeWhite();

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggleButton.isChecked()) {
                    Toast.makeText(MainActivity.this, "Let's rock!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggleButton.isChecked()) {
                    Random random = new Random();
                    int color = Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256));
                    mainLayout.setBackgroundColor(color);
                }
            }
        });

        redBar.setOnSeekBarChangeListener(new ColorBarChangeListener());
        greenBar.setOnSeekBarChangeListener(new ColorBarChangeListener());
        blueBar.setOnSeekBarChangeListener(new ColorBarChangeListener());
    }

    private void updateBackground()
    {
        int red = BigDecimal.valueOf(redBar.getProgress() * 2.55).intValue();
        int green = BigDecimal.valueOf(greenBar.getProgress() * 2.55).intValue();
        int blue = BigDecimal.valueOf(blueBar.getProgress() * 2.55).intValue();
        mainLayout.setBackgroundColor(
                0xff000000
                        + red * 0x10000
                        + green * 0x100
                        + blue
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    private void makeWhite() {
        redBar.setProgress(100);
        blueBar.setProgress(100);
        greenBar.setProgress(100);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                mainLayout.setBackgroundColor(Color.argb(255,255,255,255));
                makeWhite();
                Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }
        return true;
    }

    private class ColorBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            updateBackground();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }
}
