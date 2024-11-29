package com.example.unsrihealthzone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LoginPage extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // Inisialisasi
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Login Button Listener
        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            // Cek validitas login
            if (username.equals("user") && password.equals("123")) {
                // Menyimpan sesi login
                SharedPreferences sharedPreferences = getSharedPreferences("LoginSession", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.apply();

                // Pindah ke MainActivity
                Intent intent = new Intent(LoginPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Jika login gagal
                Toast.makeText(LoginPage.this, "Username atau password salah!", Toast.LENGTH_SHORT).show();
            }

            // Simpan login ke database
            addLoginToDB(username, password);
        });
    }

    // Fungsi untuk menambahkan login ke database
    private void addLoginToDB(String username, String password) {
        // Membuat HashMap untuk data login
        HashMap<String, Object> loginHashmap = new HashMap<>();
        loginHashmap.put("username", username);
        loginHashmap.put("password", password);

        // Inisialisasi Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("login");

        // Menambahkan key unik pada setiap entri
        String key = myRef.push().getKey();
        loginHashmap.put("key", key);

        // Menyimpan data ke Firebase
        if (key != null) {
            myRef.child(key).setValue(loginHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginPage.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginPage.this, "Gagal menambahkan data", Toast.LENGTH_SHORT).show();
                    }
                    // Clear the input fields after the operation
                    editTextUsername.getText().clear();
                    editTextPassword.getText().clear();
                }
            });
        }
    }
}
