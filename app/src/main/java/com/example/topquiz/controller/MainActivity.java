package com.example.topquiz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.modele.User;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {
    private TextView mtextView;
    private EditText meditText;
    private Button mbutton;
    private User muser;
    private static final int jeux_activity_code_de_requete = 42;
    //SharedPreferences preferences=getPreferences(MODE_PRIVATE);

    public static final String PREF_SCORE = "PREF_SCORE";
    public static final String PREF_PRENOM = "PREF_PRENOM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtextView=(TextView) findViewById(R.id.edT);
        meditText=(EditText) findViewById(R.id.edT);
        mbutton=(Button) findViewById(R.id.btn);
        mbutton.setEnabled(false);
        muser=new User();
        meditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            mbutton.setEnabled(s.toString().length()!=0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muser.setmPrenom(meditText.getText().toString());
                //preferences.edit().putString(PREF_PRENOM, muser.getmPrenom()).apply();

                Intent jeuxActivity=new Intent(MainActivity.this,JeuxActivity.class);
                startActivityForResult(jeuxActivity,jeux_activity_code_de_requete);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (jeux_activity_code_de_requete == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int score = data.getIntExtra(JeuxActivity.BUNDLE_EXTRA_SCORE, 0);
            //preferences.edit().putInt(PREF_SCORE, score).apply();

            greetUser();
        }
    }
    private void greetUser() {
        /*String firstname = preferences.getString(PREF_PRENOM, null);

        if (null != firstname) {
            int score = preferences.getInt(PREF_PRENOM, 0);

            String fulltext = "Bienvenue, " + firstname
                    + "!\nVotre dernier score est " + score
                    + ", Nous esperons que vous faciez mieu?";
            mtextView.setText(fulltext);
            meditText.setText(firstname);
            meditText.setSelection(firstname.length());
            mbutton.setEnabled(true);
        }*/
    }

    @Override
    protected void onStart() {
        super.onStart();

        out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        out.println("MainActivity::onDestroy()");
    }
}
