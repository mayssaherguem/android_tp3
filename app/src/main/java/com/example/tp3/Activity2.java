package com.example.tp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {


    private TextView receivedTextView;
    private EditText replyEditText;
    private Button sendButton;

    private static final String REPLY_MESSAGE_KEY = "replyMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        receivedTextView = findViewById(R.id.received_text_view);
        replyEditText = findViewById(R.id.reply_edit_text);
        sendButton = findViewById(R.id.send_button);

        String receivedMessage = getIntent().getStringExtra("message");
        receivedTextView.setText(receivedMessage);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String replyMessage = replyEditText.getText().toString();
                sendReplyToMainActivity(replyMessage);
                finish(); // Close Activity2 after sending reply
            }
        });
    }

    private void sendReplyToMainActivity(String replyMessage) {
        Intent intent = new Intent();
        intent.putExtra(REPLY_MESSAGE_KEY, replyMessage);
        setResult(RESULT_OK, intent);
    }
}
