package com.shadow.horizon.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadow.horizon.R;

import org.json.JSONArray;

import static android.R.id.message;

/**
 * A simple {@link Fragment} subclass.
 */
public class Test extends Fragment {


    private static final String Data_URL = "http://localhost:81/horizon/quiz.php";


    private static final String mt_id = "id";
    private static final String mt_category = "category";
    private static final String mt_question = "question";
    private static final String mt_possible_answers = "possible_answers";
    private static final String mt_correct_answer = "correct_answer";

    private static int id;
    private static String category;
    private static String question;
    private static String possible_answers;
    private static String correct_answer;







    public Test() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

}

 class mock_test extends AsyncTask<String, Void, String> {

     private AppCompatActivity activity;

     public mock_test(AppCompatActivity activity) {
         this.activity = activity;

     }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... strings) {
        //JSONArray json = jsonParser.getJSONFromUrlArr(Data_URL);
        return null;
    }


    @Override
    protected void onPostExecute(String message) {

        }

    @Override
    protected void onProgressUpdate(Void... values) {
    }
}