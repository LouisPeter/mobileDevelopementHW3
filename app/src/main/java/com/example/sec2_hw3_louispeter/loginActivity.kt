package com.example.sec2_hw3_louispeter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<ImageButton>(R.id.BackButton).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.buttonSignIn).setOnClickListener {
            val email = findViewById<EditText>(R.id.editTextTextPersonEmailLogin).text.toString()
            val pwd =  findViewById<EditText>(R.id.editTextTextPasswordLogin).text.toString()

            if ( email.isEmpty() || pwd.isEmpty())
                return@setOnClickListener

            val db = FirebaseFirestore.getInstance()
            db.collection("users").whereEqualTo("email", email).whereEqualTo("password",  pwd)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty){
                        Toast.makeText(this, "Either your id or password is incorrect", Toast.LENGTH_SHORT).show()
                        return@addOnSuccessListener
                    }
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        return@addOnSuccessListener

                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents: ", exception)
                }
        }
    }
}