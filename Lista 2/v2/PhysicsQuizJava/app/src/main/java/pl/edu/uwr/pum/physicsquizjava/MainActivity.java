package pl.edu.uwr.pum.physicsquizjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "pl.edu.uwr.pum.physicsquizjava.MESSAGE";

    Button button_false;
    Button button_true;
    Button button_cheat;
    Button button_showAnswer;

    int quantity_of_answered_questions = 0;
    String answer;

    Context context;
    Toast toast;
    int duration = Toast.LENGTH_SHORT;



    private TextView textView;

    private final Question[] questions = new Question[]{
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, true),
            new Question(R.string.question4, true),
            new Question(R.string.question5, true),
    };

    Score score = new Score(questions);

    private int iterator = questions.length;
    private int actualId = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButtons();
        textView = findViewById(R.id.question_text);
        context = getApplicationContext();
        changeQuestion();
        if(savedInstanceState != null){
            quantity_of_answered_questions = savedInstanceState.getInt("quantity_of_answered_questions_state");
            iterator = savedInstanceState.getInt("iterator_state");
            actualId = savedInstanceState.getInt("actualId_state");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("quantity_of_answered_questions_state", quantity_of_answered_questions);
        outState.putInt("iterator_state", iterator);
        outState.putInt("actualId_state", actualId);

    }

    public void prevQuestion(View view) {
        iterator += 1;
        if (iterator > questions.length) { iterator = 1; }
        changeQuestion();
    }

    public void nextQuestion(View view) {
        iterator -= 1;
        if (iterator <= 0) { iterator = questions.length; }
        changeQuestion();
    }

    private void changeQuestion(){
        actualId = questions.length - iterator;

        if (questions[actualId].getAnswered())
        {
            button_true.setEnabled(false);
            button_false.setEnabled(false);
            button_cheat.setVisibility(View.INVISIBLE);
        }
        else{
            button_true.setEnabled(true);
            button_false.setEnabled(true);
            button_cheat.setVisibility(View.VISIBLE);
        }

        if (button_cheat.getVisibility() == View.VISIBLE) { button_showAnswer.setVisibility(View.INVISIBLE); }
        else { button_showAnswer.setVisibility(View.VISIBLE); }

        textView.setText((questions[questions.length - iterator].getTextId()));
    }


    public void onClickAnswer(View view) {
        answer = ((Button)view).getText().toString();
        questions[actualId].setAnswer(answer.equals("TRUE"));


        if(questions[actualId].getAnswer() == questions[actualId].getCorrectAnswer()){
            score.correct_answers += 1;
            makeToast("Correct answer!");

        }
        else {
            score.incorrect_answers += 1;
            makeToast("Wrong answer!");
        }

        changeQuestion();

        quantity_of_answered_questions += 1;
        checkEndOfGame();
    }


    public void checkEndOfGame(){
        if (quantity_of_answered_questions >= questions.length) { endOfGame(); }
    }

    @SuppressLint("SetTextI18n")
    private void endOfGame() {
        setContentView(R.layout.score);
        TextView textViewScore = findViewById(R.id.textViewScore);
        String result = score.showScore();
        textViewScore.setText(result);
    }

    @SuppressLint("SetTextI18n")
    public void onClickCheat(View view) {

        score.cheated_answers += 1;

        Intent intent = new Intent(this, CheatActivity.class);
        intent.putExtra(EXTRA_MESSAGE, questions[actualId].getCorrectAnswer());
        startActivity(intent);

        changeQuestion();

    }

    public void onClickReset(View view) {
        for (Question q : questions) {
            q.setAnswered(false);
        }
        score = new Score(questions);
        quantity_of_answered_questions = 0;
        actualId = 0;

        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.question_text);
        searchButtons();
        changeQuestion();
    }

    public void makeToast(String text_info){
        toast = Toast.makeText(context,
                text_info,
                duration);
        toast.show();
    }

    public void searchButtons() {
        button_true = findViewById(R.id.true_button);
        button_false = findViewById(R.id.false_button);
        button_cheat = findViewById(R.id.cheat_button);
        button_showAnswer = findViewById(R.id.button_showAnswer);
    }

    public void SearchAnswer(View view) {
        String searchTerm = textView.getText().toString();
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + searchTerm)));
    }

}

