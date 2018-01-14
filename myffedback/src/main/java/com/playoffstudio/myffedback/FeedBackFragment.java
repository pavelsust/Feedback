package com.playoffstudio.myffedback;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by android on 1/14/2018.
 */

public class FeedBackFragment extends Fragment {

    private EditText feedbackInputField;
    private EditText nameInputField;
    private EditText emailInputField;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_feedback_fragment , container , false);


        collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing);
        collapsingToolbar.setTitle("Your Feedback");

        //toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        /*
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        */

        feedbackInputField = (EditText) view.findViewById(R.id.feedback_input);
        nameInputField = (EditText) view.findViewById(R.id.name_input);
        emailInputField = (EditText) view.findViewById(R.id.email_input);

        view.findViewById(R.id.submit_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        validateInput();
                    }
                }
        );
        return view;
    }




    private void validateInput() { // Validate text input

        if (feedbackInputField.getText().toString().trim().length() == 0 && nameInputField.getText().toString().trim().length() == 0 && emailInputField.getText().toString().trim().length() == 0) {
            feedbackInputField.setError("Enter your feedback!");
            nameInputField.setError("Enter a valid name!");
            emailInputField.setError("Enter a valid email!");
            Toast.makeText(getActivity(), "Please fill in the required fields!", Toast.LENGTH_LONG).show();
        } else {
            sendData();
        }
    }

    private void sendData() { // Send feedback to Google Spreadsheet if text input is valid

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/") // Your spreadsheet URL
                .build();
        final SpreadsheetWebService spreadsheetWebService = retrofit.create(SpreadsheetWebService.class);

        String feedbackInput = feedbackInputField.getText().toString();
        String nameInput = nameInputField.getText().toString();
        String emailInput = emailInputField.getText().toString();

        Call<Void> feedbackCall = spreadsheetWebService.feedbackSend(feedbackInput, nameInput, emailInput);
        feedbackCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("XXX", "Submitted. " + response);
                Toast.makeText(getActivity(),"Your feedback was submitted!", Toast.LENGTH_LONG).show();
                getActivity().finish();
                // Clear all fields after submitting
                feedbackInputField.setText("");
                nameInputField.setText("");
                emailInputField.setText("");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("XXX", "Failed", t);
                Toast.makeText(getActivity(),"There was an error!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
