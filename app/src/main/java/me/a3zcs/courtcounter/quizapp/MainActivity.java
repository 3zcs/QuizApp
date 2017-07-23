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
import static me.a3zcs.courtcounter.quizapp.R.id.question;

public class MainActivity extends AppCompatActivity {

    Model model;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        model = ViewModelProviders.of(this).get(Model.class);
        model.setQuiz(Quiz.getQuize(this));
        question(model.getQuiz().getQuestions().get(model.getNumberOfQuestion()));
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGrade(model.getQuiz().getQuestions().get(model.getNumberOfQuestion()));
                if (model.getNumberOfQuestion() < model.getQuiz().getQuestions().size() - 1) {
                    model.setNumberOfQuestion(model.getNumberOfQuestion() + 1);
                    question(model.getQuiz().getQuestions().get(model.getNumberOfQuestion()));
                    reset();
                } else {
                    binding.nextButton.setText(R.string.score);
                    Toast.makeText(MainActivity.this, getString(R.string.grade) + model.getGrade(), Toast.LENGTH_SHORT).show();
                    reset();
                }
            }
        });
    }

    private void reset() {
        binding.radioGroup.clearCheck();
        binding.checkBox.setSelected(false);
        binding.checkBox2.setSelected(false);
        binding.checkBox3.setSelected(false);
        binding.enterAnswer.setText("");
    }

    private void setGrade(Question question) {
        int grade = 0;
        int truth = 0;
        if (question.isMultipleChoice()) {
            grade = (binding.checkBox.isChecked()) ? +1 : grade;
            grade = (binding.checkBox.isChecked()) ? +2 : grade;
            grade = (binding.checkBox.isChecked()) ? +3 : grade;
        } else if (question.isDataEntry()) {
            grade = binding.enterAnswer.getText().toString().equalsIgnoreCase(question.getAnswers().get(0)) ? +1 : grade;
        } else {
            grade = (binding.radio1.isChecked()) ? +1 : grade;
            grade = (binding.radio2.isChecked()) ? +1 : grade;
            grade = (binding.radio3.isChecked()) ? +1 : grade;
        }

        for (Integer sum : question.getTrueAnswer()) {
            truth = +sum;
        }

        if (truth == grade)
            model.setGrade(model.getGrade() + 1);
    }

    private void question(Question question) {
        setType(question);
        setQuestion(question.getQuestion());
        setChoices(question.getAnswers(), question);
    }

    private void setChoices(List<String> answers, Question question) {
        if (question.isMultipleChoice()) {
            binding.checkBox.setText(answers.get(0));
            binding.checkBox2.setText(answers.get(1));
            binding.checkBox3.setText(answers.get(2));
        } else if (question.isDataEntry()){

        }
        else {

            binding.radio1.setText(answers.get(0));
            binding.radio2.setText(answers.get(1));
            binding.radio3.setText(answers.get(2));
        }
    }

    private void setType(Question question) {
        if (question.isMultipleChoice()) {
            binding.checkboxGroup.setVisibility(View.VISIBLE);
            binding.radioButtonGroup.setVisibility(View.GONE);
            binding.enterAnswer.setVisibility(View.GONE);

        } else if (question.isDataEntry()){
            binding.enterAnswer.setVisibility(View.VISIBLE);
            binding.checkboxGroup.setVisibility(View.GONE);
            binding.radioButtonGroup.setVisibility(View.GONE);
        }
        else {
            binding.enterAnswer.setVisibility(View.GONE);
            binding.checkboxGroup.setVisibility(View.GONE);
            binding.radioButtonGroup.setVisibility(View.VISIBLE);
        }
    }

    public void setQuestion(String question) {
        binding.question.setText(question);
    }
}
