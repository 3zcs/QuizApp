 package me.a3zcs.courtcounter.quizapp;

import android.arch.lifecycle.ViewModel;

/**
 * Created by root on 17/07/17.
 */

public class Model extends ViewModel {
    private Quiz quiz;
    private int grade;
    private int numberOfQuestion;

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }
}
