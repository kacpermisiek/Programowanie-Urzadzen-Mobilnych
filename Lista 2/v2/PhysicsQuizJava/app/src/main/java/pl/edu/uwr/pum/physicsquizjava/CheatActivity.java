package pl.edu.uwr.pum.physicsquizjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    Button cheat_button;
    TextView textViewCheat;
    boolean correct_answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        cheat_button = findViewById(R.id.showAnswerCheatActivity);

        textViewCheat = findViewById(R.id.textViewCheat);

        Intent intent = getIntent();
        correct_answer = intent.getBooleanExtra(MainActivity.EXTRA_MESSAGE, false);



    }

    @SuppressLint("SetTextI18n")
    public void cheatActivityShowAnswer(View view) {
        textViewCheat.setText("Correct answer is: " + correct_answer);
    }
}