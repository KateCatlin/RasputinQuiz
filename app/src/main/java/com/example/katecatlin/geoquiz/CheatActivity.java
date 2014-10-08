package com.example.katecatlin.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

/**
 * Created by katecatlin on 10/8/14.
 */
public class CheatActivity extends Activity {
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.example.katecatlin.geoquiz.answer_is_true";

    public static final String EXTRA_ANSWER_SHOWN =
            "com.example.katecatlin.geoquiz.answer_shown";

    private static final String TAG = "CheatActivity";

    private void setAnswerShownResult(boolean isAnswerShown)  {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate of cheat (Bundle) called");
        setContentView(R.layout.activity_cheat);

        setAnswerShownResult(false);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }
}
