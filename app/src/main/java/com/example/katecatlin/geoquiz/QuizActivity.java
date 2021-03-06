package com.example.katecatlin.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends Activity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;
    private Button mCheatButton;
    private boolean mIsCheater;
    private int score = 0;
    private int mCurrentIndex = 0;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
        new TrueFalse(R.string.question_alex, false),
        new TrueFalse(R.string.question_literate, true),
        new TrueFalse(R.string.question_monk, false),
        new TrueFalse(R.string.question_death, true),
        new TrueFalse(R.string.question_poison, false )

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);


        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, Integer.toString(mCurrentIndex));
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent i = new Intent(QuizActivity.this, CheatActivity.class);
              boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
              i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
              startActivityForResult(i, 0);
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });


        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });



        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                       public void onClick(View v) {
                                               mIsCheater = false;
                                               updateQuestion();
                                           }

                                       });

        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                               updateQuestion();
                                           }

                                       }

        );

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }



        updateQuestion();
    }

    private void updateQuestion() {
        if (mCurrentIndex < 5) {
            int question = mQuestionBank[mCurrentIndex].getQuestion();
            mQuestionTextView.setText(question);
            mCurrentIndex = (mCurrentIndex + 1);
        }
        else endGame();
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResId = 0;

        if (mIsCheater) {
            messageResId = R.string.judgement_toast;
        } else {

            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
                score++;
                if (mCurrentIndex==5) {endGame();}
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    public void endGame() {
        String final_score = ("YOUR FINAL SCORE IS " + score + "/" + mQuestionBank.length + "!");
        Toast.makeText(this, R.string.game_over, Toast.LENGTH_LONG).show();
        Toast.makeText(this, final_score, Toast.LENGTH_LONG).show();
        mPreviousButton.setEnabled(false);
        mNextButton.setEnabled(false);
        mCheatButton.setEnabled(false);
        mTrueButton.setEnabled(false);
        mFalseButton.setEnabled(false);
    }

}


