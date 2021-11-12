package pl.edu.uwr.pum.physicsquizjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {


    private TextView textViewCheat;
    private String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);


        showAnswerButton = findViewById(R.id.showAnswerCheatActivityButton);

        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        textViewCheat = findViewById(R.id.textViewCheat);


    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void cheatActivityShowAnswer(View view) {
        MainActivity.cheated_answers += 1;
        showAnswerButton.setVisibility(View.INVISIBLE);
        textViewCheat.setText(message);

    }

}