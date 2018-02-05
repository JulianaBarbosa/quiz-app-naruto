package com.example.android.narutoquiz;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //To keep the user's answers
    String[] userAnswers = new String[10];

    //That is the right answers
    String[] quizAnswers = {"t", "f", "f", "t", "t", "f", "f", "f", "t", "t"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**Receive the user's answers and put them in the userAnswers array
    */
    public void addAnswer (View v){

        String tag = v.getTag().toString();
        int question = Integer.parseInt(tag.split(",")[0]);
        String answer = tag.split(",")[1];

        userAnswers[question-1] = answer;

    }

    /**This method:
     * - gets the user's name and verify the checkBox
     * - compare the user's answers with the right answers and keep the number of right answers in a variable
     * - check the user's hits and choose an appropriate message
     * - displays a message with the user's name, if he checks the checkBox and a message according to his score
     * - calls the method that displays the user's score with a Toast
     * - calls the method that change the color of the right answers
     */
    public void checkAnswers (View v) {

        int rightCount = 0;
        String finalMessage;

        // Figure out if the user watched all seasons
        CheckBox watchAllSeasons = (CheckBox) findViewById(R.id.watch_all_seasons);
        boolean hasWatched = watchAllSeasons.isChecked();

        //gets the name that the user typed
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        //Compare the user's answers with the right answers and counts his hits
        for (int i=0; i<quizAnswers.length; i++){
            if (quizAnswers[i].equals(userAnswers[i])){
                rightCount++;
            }
        }

        //Check the hits and choose an appropriate message
        if (rightCount >= 0 && rightCount <= 3){
            if (hasWatched){
                finalMessage = "FINAL RESULTS:" + "\n" + name + "\n" + "Has watched all seasons? "
                        + hasWatched + "\n" + "You need to watch Naruto again!";
            }else{
                finalMessage = "FINAL RESULTS:" + "\n" + name + "\n" + "Has watched all seasons? "
                        + hasWatched + "\n" + "You need to watch more Naruto!";
            }
        }else if (rightCount > 3 && rightCount <= 7){
            if (hasWatched){
                finalMessage = "FINAL RESULTS:" + "\n" + name + "\n" + "Has watched all seasons? "
                        + hasWatched + "\n" + "You know a lot about Naruto! But you forgot some things...";
            }else{
                finalMessage = "FINAL RESULTS:" + "\n" + name + "\n" + "Has watched all seasons? "
                        + hasWatched + "\n" + "You know a lot about Naruto! It's time to finish the anime!";
            }
        }else {
            if (hasWatched){
                finalMessage = "FINAL RESULTS:" + "\n" + name + "\n" + "Has watched all seasons? "
                        + hasWatched + "\n" + "You really are a big fan of Naruto! It's time to rewatch the anime!";
            }else{
                finalMessage = "FINAL RESULTS:" + "\n" + name + "\n" + "Has watched all seasons? "
                        + hasWatched + "\n" + "You really are a big fan of Naruto! " +
                        "Are you sure you did not watch all the seasons?";
            }
        }

        showResult(rightCount);
        displayMessage(finalMessage);
        showAllQuestionsRight();
    }

    /**Displays a message when the user press the submit button with his score
     *
     * @param rightCount is the hit of the user
     */
    public void showResult (int rightCount){
        Context context = getApplicationContext();
        CharSequence text = "You answered " + rightCount + " question(s) right!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    //Change the colors of the right answers when the user submit the quiz
    public void showAllQuestionsRight (){

        RadioButton button = (RadioButton) findViewById(R.id.question_one_true);
        button.setTextColor(Color.parseColor("#00C853"));

        button = (RadioButton) findViewById(R.id.question_two_false);
        button.setTextColor(Color.parseColor("#00C853"));

        button = (RadioButton) findViewById(R.id.question_three_false);
        button.setTextColor(Color.parseColor("#00C853"));

        button = (RadioButton) findViewById(R.id.question_four_true);
        button.setTextColor(Color.parseColor("#00C853"));

        button = (RadioButton) findViewById(R.id.question_five_true);
        button.setTextColor(Color.parseColor("#00C853"));

        button = (RadioButton) findViewById(R.id.question_six_false);
        button.setTextColor(Color.parseColor("#00C853"));

        button = (RadioButton) findViewById(R.id.question_seven_false);
        button.setTextColor(Color.parseColor("#00C853"));

        button = (RadioButton) findViewById(R.id.question_eight_false);
        button.setTextColor(Color.parseColor("#00C853"));

        button = (RadioButton) findViewById(R.id.question_nine_true);
        button.setTextColor(Color.parseColor("#00C853"));

        button = (RadioButton) findViewById(R.id.question_ten_true);
        button.setTextColor(Color.parseColor("#00C853"));

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView finalMessage = (TextView) findViewById(R.id.message);
        finalMessage.setText(message);
    }

}
