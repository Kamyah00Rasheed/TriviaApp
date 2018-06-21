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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.skills.interapt.triviaapp.MainActivity.QUESTIONS_LIST;

public class QuizFragment extends Fragment {


    @BindView(R.id.quiz_question_textview)
    protected TextView quizQuestion;

    @BindView(R.id.first_answer_button)
    protected Button firstAnswerButton;

    @BindView(R.id.second_answer_button)
    protected Button secondAnswerButton;

    @BindView(R.id.third_answer_button)
    protected Button thirdAnswerButton;

    @BindView(R.id.fourth_answer_button)
    protected Button fourthAnswerButton;

    private List<Question> questionsList;
    private Question question;
    private int questionListPosition =0;
    private int correctAnswers = 0;
    private QuizCallback quizCallback;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false); //always choose false for this

        ButterKnife.bind(this, view);

        return view;
    }


    public static QuizFragment newInstance() {

        Bundle args = new Bundle();

        QuizFragment fragment = new QuizFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onStart() {
        super.onStart();

        questionsList=getArguments().getParcelableArrayList(QUESTIONS_LIST);

        populateQuizContent();
    }

    private void populateQuizContent() {
        question = questionsList.get(questionListPosition);
        quizQuestion.setText(question.getQuestion());

        List<Button> buttonList = new ArrayList<>();
        buttonList.add(firstAnswerButton);
        buttonList.add(secondAnswerButton);
        buttonList.add(thirdAnswerButton);
        buttonList.add(fourthAnswerButton);

        //Just like with the buttons, this arrayList will take all of the possible answers
        //and allows us to access them
        List<String> possibleAnswersList = new ArrayList<>();
        possibleAnswersList.add(question.getCorrectAnswer());
        possibleAnswersList.add(question.getWrongAnswerOne());
        possibleAnswersList.add(question.getWrongAnswerTwo());
        possibleAnswersList.add(question.getWrongAnswerThree());

        //This For Each loop takes the arrayLists we made and actually allow us to randomize
        //what question goes on which button
        for (Button button : buttonList){
            //Creates a random number that will allow us to pick an answer from our arrayList
            int random = (int) Math.ceil(Math.random() * (possibleAnswersList.size() -1));
            //Using the random number above we will set the text of the button by getting that item
            //from the possible answers list
            button.setText(possibleAnswersList.get(random));
            //To make sure we don't use the same answer twice we remove the possible answer from
            //the list
            possibleAnswersList.remove(random);
        }
    }

    private void checkAnswer(String answer){
        disableAnswerButtons();
        //Increments questionListPosition so we can go to the next question
        questionListPosition++;

        if(question.getCorrectAnswer().equals(answer)) {
            //Sets the textView to show the user they were correct
            quizQuestion.setText(R.string.correct);
            //Increments the correct answers the user has gotten
            correctAnswers++;
        } else {
            quizQuestion.setText(getString(R.string.wrong_answer_text, question.getCorrectAnswer()));

        }

    }

    @OnClick(R.id.first_answer_button)
    protected void buttonOneClicked(){

        checkAnswer(firstAnswerButton.getText().toString());

    }


    @OnClick(R.id.second_answer_button)
    protected void buttonTwoClicked(){

        checkAnswer(secondAnswerButton.getText().toString());

    }

    @OnClick(R.id.third_answer_button)
    protected void buttonThreeClicked(){

        checkAnswer(thirdAnswerButton.getText().toString());

    }

    @OnClick(R.id.fourth_answer_button)
    protected void buttonFourClicked(){

        checkAnswer(fourthAnswerButton.getText().toString());

    }

    @OnClick(R.id.next_button)
    protected void buttonNextClicked(){
        enableAnswerButtons();
        if(questionListPosition <= questionsList.size() -1) {
            populateQuizContent();
        } else {
            //Handling no more questions, taking user back to MainActivity
            quizCallback.quizFinished(correctAnswers);

        }

    }

    private void disableAnswerButtons(){
        firstAnswerButton.setEnabled(false);
        secondAnswerButton.setEnabled(false);
        thirdAnswerButton.setEnabled(false);
        fourthAnswerButton.setEnabled(false);

    }

    private void enableAnswerButtons(){
        firstAnswerButton.setEnabled(true);
        secondAnswerButton.setEnabled(true);
        thirdAnswerButton.setEnabled(true);
        fourthAnswerButton.setEnabled(true);


    }

    public void attachParent(QuizCallback quizCallback){

        this.quizCallback = quizCallback;

    }

    public interface QuizCallback {
        void quizFinished(int correctAnswers);
    }



}
