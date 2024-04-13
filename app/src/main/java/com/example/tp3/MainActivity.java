package com.example.tp3;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText messageEditText;
    private Button sendButton;
    private TextView receivedTextView;

    private static final int REQUEST_CODE = 1;
    private ActivityResultLauncher<Intent> launcher;

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
                sendMessage();
            }
        });

        launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        String responseMessage = data.getStringExtra("replyMessage");
                        if (responseMessage != null) {
                            receivedTextView.setText(responseMessage);
                        }
                    }
                }
            }
        );
    }

    private void sendMessage() {
        String message = messageEditText.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            // Display error message if message EditText is empty
            Toast.makeText(this, "Chaine Vide", Toast.LENGTH_SHORT).show();
        } else {
            // Send the message to Activity2 if not empty
            sendMessageToActivity2(message);
        }
    }

    private void sendMessageToActivity2(String message) {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("message", message);
        launcher.launch(intent);
        //startActivityForResult(intent, REQUEST_CODE);
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