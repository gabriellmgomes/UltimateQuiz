package libanthedev.ultimatequiz;

/**
 * Created by libanmohamed on 9/6/14.
 */
public class TrueFalse {

    private boolean mTrueQuestion;
    private int mQuestion;

    public  TrueFalse (int question, boolean TrueQuestion) {
        mQuestion = question;
        mTrueQuestion = TrueQuestion;

    }

    public int getQuestion() {
        return mQuestion;
    }
    public void setQuestion (int question) {
        mQuestion = question;
    }
    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }
    public void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }

}
