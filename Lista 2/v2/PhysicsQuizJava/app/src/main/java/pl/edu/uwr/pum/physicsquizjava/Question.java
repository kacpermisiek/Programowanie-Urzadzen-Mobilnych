package pl.edu.uwr.pum.physicsquizjava;

public class Question {
    private final int textId;
    private boolean answer;
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



    public void setAnswer(boolean answer) {
        this.answer = answer;
        // this.answered_whenever = true;
    }

}
