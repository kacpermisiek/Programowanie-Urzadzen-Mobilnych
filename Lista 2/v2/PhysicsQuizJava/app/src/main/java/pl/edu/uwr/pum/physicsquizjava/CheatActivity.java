package pl.edu.uwr.pum.physicsquizjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {


    private TextView textViewCheat;
    private String message;

    private Button showAnswerButton;
    private boolean showed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        showed = false;

        showAnswerButton = findViewById(R.id.showAnswerCheatActivityButton);

        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        textViewCheat = findViewById(R.id.textViewCheat);


        if(savedInstanceState != null){
            message = savedInstanceState.getString("message_state");
            showed = savedInstanceState.getBoolean("showed_state");
            if (showed) { textViewCheat.setText(message); }
        }

        if (showed){ showAnswerButton.setVisibility(View.INVISIBLE); }
        else { showAnswerButton.setVisibility(View.VISIBLE); }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message_state", message);
        outState.putBoolean("showed_state", showed);
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
        showed = true;
        MainActivity.cheated_answers += 1;
        showAnswerButton.setVisibility(View.INVISIBLE);
        textViewCheat.setText(message);

    }

}