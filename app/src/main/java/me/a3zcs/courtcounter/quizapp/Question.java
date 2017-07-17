package me.a3zcs.courtcounter.quizapp;

import java.util.List;

/**
 * Created by root on 17/07/17.
 */

public class Question {
    private String question;
    private List<String> answers;
    private List<Integer> trueAnswer;
    private boolean isMultipleChoice;

    public boolean isMultipleChoice() {
        return isMultipleChoice;
    }

    public void setMultipleChoice(boolean multipleChoice) {
        isMultipleChoice = multipleChoice;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public List<Integer> getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(List<Integer> trueAnswer) {
        this.trueAnswer = trueAnswer;
    }


}
