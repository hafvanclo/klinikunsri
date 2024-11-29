package com.example.unsrihealthzone;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unsrihealthzone.ui.call.ChatAdapter;
import com.example.unsrihealthzone.ui.call.ChatMessage;

import java.util.ArrayList;
import java.util.List;



public class Chating extends AppCompatActivity {

    private RecyclerView recyclerViewChat;
    private EditText editTextMessage;
    private ImageButton buttonSend;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating);  // Inflate layout using setContentView

        // Inisialisasi UI components
        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.sendButton);

        // Set up the chat messages list and adapter
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages, this);
        recyclerViewChat.setAdapter(chatAdapter);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        ImageView backButton = findViewById(R.id.backButton);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        // Send message when button is clicked
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    // Add sent message to the list
                    chatMessages.add(new ChatMessage(message, true));
                    chatAdapter.notifyItemInserted(chatMessages.size() - 1);

                    // Clear the input field
                    editTextMessage.setText("");

                    // Simulate receiving a reply (optional)
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            chatMessages.add(new ChatMessage("Ini adalah balasan otomatis.", false));
                            chatAdapter.notifyItemInserted(chatMessages.size() - 1);
                        }
                    }, 1000);
                }
            }
        });
    }
}
