package com.example.katecatlin.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by katecatlin on 10/8/14.
 */
public class CheatActivity extends Activity {

    private static final String TAG = "CheatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate of cheat (Bundle) called");
        setContentView(R.layout.activity_cheat);
    }
}
