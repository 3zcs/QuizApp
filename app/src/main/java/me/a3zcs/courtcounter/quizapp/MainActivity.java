package me.a3zcs.courtcounter.quizapp;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import me.a3zcs.courtcounter.quizapp.databinding.ActivityMainBinding;

import static android.R.attr.handle;
import static android.R.attr.mode;
import static me.a3zcs.courtcounter.quizapp.R.id.checkBox;

public class MainActivity extends AppCompatActivity {

    Model model;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        model = ViewModelProviders.of(this).get(Model.class);
        model.setQuiz(Quiz.getQuize(this));
        question(model.getQuiz().getQuestions().get(model.getNumberOfQuestion()));
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGrade(model.getQuiz().getQuestions().get(model.getNumberOfQuestion()));
                if (model.getNumberOfQuestion()< model.getQuiz().getQuestions().size()-1) {
                    model.setNumberOfQuestion(model.getNumberOfQuestion() + 1);
                    question(model.getQuiz().getQuestions().get(model.getNumberOfQuestion()));
                }else {
                    binding.nextButton.setText(R.string.score);
                    Toast.makeText(MainActivity.this,"Your grade is "+ model.getGrade(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setGrade(Question question) {
        int grade = 0;
        int truth = 0;
        if (question.isMultipleChoice()){
            grade = (binding.checkBox.isChecked())? +1 : grade;
            grade = (binding.checkBox.isChecked())? +2 : grade;
            grade = (binding.checkBox.isChecked())? +3 : grade;
        }else {
            grade = (binding.radio1.isChecked())? +1 : grade;
            grade = (binding.radio2.isChecked())? +1 : grade;
            grade = (binding.radio3.isChecked())? +1 : grade;
        }

        for (Integer sum : question.getTrueAnswer()){
            truth=+sum;
        }

        if (truth == grade)
            model.setGrade(model.getGrade()+1);
    }

    private void question(Question question) {
        setType(question.isMultipleChoice());
        setQuestion(question.getQuestion());
        setChoices(question.getAnswers(),question.isMultipleChoice());
    }

    private void setChoices(List<String> answers, boolean multipleChoice) {
        if (multipleChoice){
            binding.checkBox.setText(answers.get(0));
            binding.checkBox2.setText(answers.get(1));
            binding.checkBox3.setText(answers.get(2));
        }else {
            binding.radio1.setText(answers.get(0));
            binding.radio2.setText(answers.get(1));
            binding.radio3.setText(answers.get(2));
        }
    }

    private void setType(boolean multipleChoice) {
        if (multipleChoice) {
            binding.checkboxGroup.setVisibility(View.VISIBLE);
            binding.radioButtonGroup.setVisibility(View.GONE);
        }else {
            binding.checkboxGroup.setVisibility(View.GONE);
            binding.radioButtonGroup.setVisibility(View.VISIBLE);
        }
    }

    public void setQuestion(String question) {
        binding.question.setText(question);
    }
}
