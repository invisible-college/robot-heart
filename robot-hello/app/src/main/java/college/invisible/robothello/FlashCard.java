package college.invisible.robothello;

/**
 * A flash card for studying, with a question and an answer
 * Created by ppham on 12/2/15.
 */
public class FlashCard {

    enum Side { QUESTION, ANSWER };

    protected String question;
    protected String correctAnswer;
    protected Side side;

    public FlashCard(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.side = Side.QUESTION;
    }

    public void showAnswer() {
        this.side = Side.ANSWER;
    }

    public void hideAnswer() {
        this.side = Side.QUESTION;
    }

    public boolean isAnswerVisible() {
        return (this.side == Side.ANSWER);
    }

    public boolean isAnswerCorrect(String userAnswer) {
        return this.correctAnswer.equals(userAnswer);
    }
}
