package com.example.task_dmrank;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationView extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        textView = findViewById(R.id.titleNotificationTxt);
        //getting the notification message
        String message = getIntent().getStringExtra("message");
        textView.setText(message);
    }
}