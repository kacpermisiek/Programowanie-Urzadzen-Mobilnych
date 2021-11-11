package pl.edu.uwr.pum.physicsquizjava;

import android.annotation.SuppressLint;

public class Score {

    public int correct_answers = 0;
    public int incorrect_answers = 0;
    public int cheated_answers = 0;
    private final Question[] questions;

    public Score(Question[] questions) {
        this.questions = questions;
    }



    private float getScore(){
        int n = this.questions.length;
        float score;
        if (n < 1){ score = 0; }

        else {
            score = ((this.correct_answers / (float) n) * 100) - (15 * this.cheated_answers);
        }
        if (score < 0) { score = 0; }

        return score;
    }

    @SuppressLint("DefaultLocale")
    public String showScore(){

        @SuppressLint("DefaultLocale") String info = String.format("Correct answer: %d \nIncorrect answer: %d \nCheated answer: %d \n Score:%.0f percent",
                correct_answers,
                incorrect_answers,
                cheated_answers,
                this.getScore());


        return info;
    }

}
