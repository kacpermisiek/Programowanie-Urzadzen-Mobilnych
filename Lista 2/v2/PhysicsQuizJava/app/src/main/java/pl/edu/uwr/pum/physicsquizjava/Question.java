package pl.edu.uwr.pum.physicsquizjava;

public class Question {
    private final int textId;
    private boolean answer;
    private boolean answered = false;
    private final boolean correct_answer;



    public Question(int textId, boolean correct_answer) {
        this.textId = textId;
        this.correct_answer = correct_answer;
    }

    public int getTextId() {
        return textId;
    }
    public boolean getAnswer() { return answer ;}
    public boolean getCorrectAnswer() { return correct_answer; }
    public boolean getAnswered() { return answered ;}



    public void setAnswer(boolean answer) {
        this.answer = answer;
        this.answered = true;
        // this.answered_whenever = true;
    }

    public void setAnswered(boolean answered){
        this.answered = answered;
    }

}
