package com.skills.interapt.triviaapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionCreatorFragment extends Fragment {

    @BindView(R.id.create_question_edittext)
    protected EditText question;

    @BindView(R.id.correct_asnwer_edittext)
    protected EditText correctAnswer;

    @BindView(R.id.first_wrong_answer_edittext)
    protected EditText firstWrongAnswer;

    @BindView(R.id.second_wrong_answer_edittext)
    protected EditText secondWrongAnswer;

    @BindView(R.id.third_wrong_answer_edittext)
    protected EditText thirdWrongAnswer;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_question_creator, container, false); //always choose false for this

        ButterKnife.bind(this, view);

        return view;


    }

    public static QuestionCreatorFragment newInstance() {

        Bundle args = new Bundle();

        QuestionCreatorFragment fragment = new QuestionCreatorFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @OnClick(R.id.save_question_button)
    protected void addQuestion(){

        String questionTitle = question.getText().toString();

    }


}
