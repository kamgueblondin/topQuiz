package com.example.topquiz.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz.R;
import com.example.topquiz.modele.BanqueQuestions;
import com.example.topquiz.modele.Questions;

import java.util.Arrays;

public class JeuxActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mtvQuestion;
    private Button bQ1;
    private Button bQ2;
    private Button bQ3;
    private Button bQ4;
    private BanqueQuestions banqueQuestions;
    private Questions mCurrentQuestion;
    private int nombreDeQuestion;
    private int score;
    private boolean activerLeTouch;

    public static final String BUNDLE_SCORE = "score courant";
    public static final String BUNDLE_QUESTION = "Question courante";

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeux);
        mtvQuestion=(TextView) findViewById(R.id.questionTv);
        bQ1=(Button) findViewById(R.id.boutonQ1);
        bQ2=(Button) findViewById(R.id.boutonQ2);
        bQ3=(Button) findViewById(R.id.boutonQ3);
        bQ4=(Button) findViewById(R.id.boutonQ4);

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(BUNDLE_SCORE);
            nombreDeQuestion = savedInstanceState.getInt(BUNDLE_QUESTION);
        } else {
            nombreDeQuestion=4;
            score=0;
        }
        banqueQuestions = this.generateQuestions();
        bQ1.setTag(0);
        bQ2.setTag(1);
        bQ3.setTag(2);
        bQ4.setTag(3);

        bQ1.setOnClickListener(this);
        bQ2.setOnClickListener(this);
        bQ3.setOnClickListener(this);
        bQ4.setOnClickListener(this);
        activerLeTouch=true;
        mCurrentQuestion = generateQuestions().getQuestion();
        this.afficherQuestion(mCurrentQuestion);
    }
    private BanqueQuestions generateQuestions() {
        Questions question1 = new Questions("What is the name of the current french president?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1);

        Questions question2 = new Questions("How many countries are there in the European Union?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        Questions question3 = new Questions("Who is the creator of the Android operating system?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Questions question4 = new Questions("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Questions question5 = new Questions("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Questions question6 = new Questions("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Questions question7 = new Questions("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Questions question8 = new Questions("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Questions question9 = new Questions("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        return new BanqueQuestions(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9));
    }
    private void afficherQuestion(final Questions questions){
        mCurrentQuestion=questions;
        this.mtvQuestion.setText(questions.getQuestion());
        this.bQ1.setText(questions.getReponses().get(0));
        this.bQ2.setText(questions.getReponses().get(1));
        this.bQ3.setText(questions.getReponses().get(2));
        this.bQ4.setText(questions.getReponses().get(3));
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();
        if(responseIndex==mCurrentQuestion.getIndexBonneReponse()){
            score++;
            Toast.makeText(this, "Bonne réponse", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Mauvaise réponse", Toast.LENGTH_SHORT).show();
        }
        activerLeTouch=false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activerLeTouch=true;
                if (--nombreDeQuestion == 0) {
                    finDuJeu();
                } else {
                    mCurrentQuestion = banqueQuestions.getQuestion();
                    afficherQuestion(mCurrentQuestion);
                }
            }
        }, 2000);
    }
    private void finDuJeu(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Jeux terminer!")
                .setMessage("Votre score est " + score)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, score);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return activerLeTouch && super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    public JeuxActivity() {
        super();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(BUNDLE_SCORE, score);
        outState.putInt(BUNDLE_QUESTION, nombreDeQuestion);

        super.onSaveInstanceState(outState);
    }
}
