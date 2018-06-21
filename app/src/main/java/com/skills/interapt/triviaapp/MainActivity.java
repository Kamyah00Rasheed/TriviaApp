package com.skills.interapt.triviaapp;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements QuestionCreatorFragment.Callback{

    private QuestionCreatorFragment questionCreatorFragment;
    private QuizFragment quizFragment;
    private List<Question> questionsList;
    public static final String QUESTIONS_LIST = "questions_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        questionsList = new ArrayList<>();
    }

    @OnClick(R.id.add_question_button)
    protected void addQuestionClicked(){
        questionCreatorFragment = QuestionCreatorFragment.newInstance();
        questionCreatorFragment.attachParent(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, questionCreatorFragment).commit();
    }

    @OnClick(R.id.take_quiz_button)
    protected void takeQuiz(){
    }

    @OnClick(R.id.delete_quiz_button)
    protected void deleteQuiz(){
    }

    @Override
    public void questionSaved(Question question) {
        //Takes the question object that was passed in and saves it to the questions Array
        questionsList.add(question);
        //Shows a Toast to the user that lets them know the question was saved
        Toast.makeText(this, "Question Saved! :)", Toast.LENGTH_SHORT).show();
        //Removes the fragment from the frameLayout
        getSupportFragmentManager().beginTransaction().remove(questionCreatorFragment).commit();
    }

    @OnClick(R.id.take_quiz_button)
    protected void takeQuizClicked(){

        if(questionsList.isEmpty()){
            //Handle toast for if there are no questions saved
            Toast.makeText(this, "You must create some questions first!", Toast.LENGTH_SHORT).show();
        } else {
            //Launch fragment, pass in Parcelable array
            quizFragment = QuizFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, quizFragment).commit();

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(QUESTIONS_LIST, (ArrayList<? extends Parcelable>) questionsList);

        }
    }

}
