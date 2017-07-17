package me.a3zcs.courtcounter.quizapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 17/07/17.
 */

public class Quiz {
    private List<Question> questions = new ArrayList<>();
    private static Quiz quiz;
    private Context context;

    private Quiz(){}
    private void setQuiz(Context context){
        this.context = context;
        Question q1 = new Question();
        q1.setQuestion(context.getString(R.string.question_1));
        List<String> answerQ1 = Arrays.asList(
                context.getString(R.string.answer1_1),
                context.getString(R.string.answer1_2),
                context.getString(R.string.answer1_3));
        q1.setAnswers(answerQ1);
        q1.setTrueAnswer(Arrays.asList(1));
        q1.setMultipleChoice(false);

        Question q2 = new Question();
        q2.setQuestion(context.getString(R.string.question_2));
        List<String> answerQ2 = Arrays.asList(
                context.getString(R.string.answer2_1),
                context.getString(R.string.answer2_2),
                context.getString(R.string.answer2_3));
        q2.setAnswers(answerQ2);
        q2.setTrueAnswer(Arrays.asList(2));
        q2.setMultipleChoice(false);


        Question q3 = new Question();
        q3.setQuestion(context.getString(R.string.question_3));
        List<String> answerQ3 = Arrays.asList(
                context.getString(R.string.answer3_1),
                context.getString(R.string.answer3_2),
                context.getString(R.string.answer3_3));
        q3.setAnswers(answerQ3);
        q3.setTrueAnswer(Arrays.asList(2));
        q3.setMultipleChoice(false);


        Question q4 = new Question();
        q4.setQuestion(context.getString(R.string.question_4));
        List<String> answerQ4 = Arrays.asList(
                context.getString(R.string.answer4_1),
                context.getString(R.string.answer4_2),
                context.getString(R.string.answer4_3));
        q4.setAnswers(answerQ4);
        q4.setTrueAnswer(Arrays.asList(1,2));
        q4.setMultipleChoice(true);
        quiz.setQuestions(Arrays.asList(q1,q2,q3,q4));
    }

    public static Quiz getQuize(Context context) {
        if (quiz == null) {
            quiz = new Quiz();
            quiz.setQuiz(context);
        }

        return quiz;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    private void setQuestions(List<Question>questions){
        this.questions.addAll(questions);
    }


}
