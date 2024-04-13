package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText messageEditText;
    private Button sendButton;
    private TextView receivedTextView;

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageEditText = findViewById(R.id.message_edit_text);
        sendButton = findViewById(R.id.send_button);
        receivedTextView = findViewById(R.id.received_text_view);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                sendMessageToActivity2(message);
            }
        });
    }

    private void sendMessageToActivity2(String message) {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("message", message);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data.hasExtra("replyMessage")) {
                String replyMessage = data.getStringExtra("replyMessage");
                receivedTextView.setText(replyMessage);
            } else {
                receivedTextView.setText("No message received.");
            }
        }
    }
}