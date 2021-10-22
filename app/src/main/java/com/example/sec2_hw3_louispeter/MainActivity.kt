package com.example.sec2_hw3_louispeter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.signInBtn).setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.SignUpBtn).setOnClickListener {
            val intent = Intent(this, signUpActivity::class.java)
            startActivity(intent)
        }
    }
}