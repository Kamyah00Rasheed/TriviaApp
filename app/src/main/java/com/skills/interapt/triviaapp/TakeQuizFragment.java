package com.skills.interapt.triviaapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TakeQuizFragment extends Fragment {


    @BindView(R.id.question_take_quiz_textview)
    protected TextView question_take_quiz_textview;

    @BindView(R.id.first_answer_button)
    protected Button first_answer_button;

    @BindView(R.id.second_answer_button)
    protected Button second_answer_button;

    @BindView(R.id.third_answer_button)
    protected Button third_answer_button;

    @BindView(R.id.fourth_answer_button)
    protected Button fourth_answer_button;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_take_quiz, container, false); //always choose false for this

        ButterKnife.bind(this, view);

        return view;
    }


    public static TakeQuizFragment newInstance() {

        Bundle args = new Bundle();

        TakeQuizFragment fragment = new TakeQuizFragment();
        fragment.setArguments(args);
        return fragment;
    }





    @OnClick(R.id.first_answer_button)
    protected void buttonOneClicked(){

    }


    @OnClick(R.id.second_answer_button)
    protected void buttonTwoClicked(){

    }

    @OnClick(R.id.third_answer_button)
    protected void buttonThreeClicked(){

    }

    @OnClick(R.id.fourth_answer_button)
    protected void buttonFourClicked(){

    }

    @OnClick(R.id.next_button)
    protected void buttonNextClicked(){

    }



}
