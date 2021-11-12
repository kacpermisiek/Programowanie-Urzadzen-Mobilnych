package pl.edu.uwr.pum.physicsquizjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "pl.edu.uwr.pum.physicsquizjava.MESSAGE";

    Button button_false;
    Button button_true;
    Button button_cheat;
    Button button_showAnswer;

    int quantity_of_answered_questions;
    String answer;

    Context context;
    Toast toast;
    int duration = Toast.LENGTH_SHORT;

    public static int correct_answers, incorrect_answers, cheated_answers;



    private TextView textView;

    private final static Question[] questions = new Question[]{
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, true),
            new Question(R.string.question4, true),
            new Question(R.string.question5, true),
    };

    ArrayList<Integer> answeredQuestions = new ArrayList<>(questions.length);

    private int iterator;
    private int actualId;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity_of_answered_questions = 0;
        iterator = questions.length;
        actualId = 0;


        if(savedInstanceState != null){
            quantity_of_answered_questions = savedInstanceState.getInt("quantity_of_answered_questions_state");
            iterator = savedInstanceState.getInt("iterator_state");
            actualId = savedInstanceState.getInt("actualId_state");
            correct_answers = savedInstanceState.getInt("correctAnswers_state");
            incorrect_answers = savedInstanceState.getInt("incorrectAnswers_state");
            cheated_answers = savedInstanceState.getInt("cheatedAnswers_state");
            answeredQuestions = savedInstanceState.getIntegerArrayList("answeredQuestions_state");
        }

        searchButtons();
        textView = findViewById(R.id.question_text);
        context = getApplicationContext();
        changeQuestion();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("quantity_of_answered_questions_state", quantity_of_answered_questions);
        outState.putInt("iterator_state", iterator);
        outState.putInt("actualId_state", actualId);
        outState.putInt("correctAnswers_state", correct_answers);
        outState.putInt("incorrectAnswers_state", incorrect_answers);
        outState.putInt("cheatedAnswers_state", cheated_answers);
        outState.putIntegerArrayList("answeredQuestions_state", answeredQuestions);
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

        if (answeredQuestions.contains(actualId))
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
            correct_answers += 1;
            makeToast("Correct answer!");
        }
        else {
            incorrect_answers += 1;
            makeToast("Wrong answer!");
        }

        answeredQuestions.add(actualId);
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
        String result = showScore();
        textViewScore.setText(result);
    }

    public void onClickCheat(View view) {
        String messageToSend = Boolean.toString(questions[actualId].getCorrectAnswer());
        Intent intent = new Intent(this, CheatActivity.class);
        intent.putExtra(EXTRA_MESSAGE, messageToSend);
        startActivity(intent);
    }

    public void onClickReset(View view) {
        answeredQuestions.clear();
        cheated_answers = 0;
        correct_answers = 0;
        incorrect_answers = 0;
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

    public void onShowCorrectAnswer(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Correct Answer");
        builder.setMessage(Boolean.toString(questions[actualId].getCorrectAnswer()));

        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();
    }


    private float getScore(){
        float score;
        if (questions.length < 1){ score = 0; }
        else {
            score = ((correct_answers / (float) questions.length) * 100) - (15 * cheated_answers);
        }
        if (score < 0) { score = 0; }

        return score;
    }


    public String showScore(){

        @SuppressLint("DefaultLocale") String info = String.format("Correct answer: %d \nIncorrect answer: %d \nCheated answer: %d \n Score:%.0f percent",
                correct_answers,
                incorrect_answers,
                cheated_answers,
                getScore());
        return info;
    }
}

