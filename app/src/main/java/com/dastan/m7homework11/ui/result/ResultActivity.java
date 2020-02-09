package com.dastan.m7homework11.ui.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dastan.m7homework11.R;

public class ResultActivity extends AppCompatActivity {

    private static String RESULT_ID = "result_id";

    public static void start(Context context, Integer integer) {
        context.startActivity(new Intent(context, ResultActivity.class).putExtra(RESULT_ID, integer));
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }
}
