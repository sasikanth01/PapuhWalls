package com.alexcruz.papuhwalls;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;

import com.alexcruz.papuhwalls.Walls.AbsWalls;


public class Papuh extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(Papuh.this, MainActivity.class);
        AbsWalls.numColumns = 2;
        startActivity(intent);
    }
}

