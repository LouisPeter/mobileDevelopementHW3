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

class signUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        findViewById<ImageButton>(R.id.BackButton).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.buttonSignIn).setOnClickListener {
            val name = findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
            val email = findViewById<EditText>(R.id.editTextTextPersonEmail).text.toString()
            val pwd1  = findViewById<EditText>(R.id.editTextTextPassword).text.toString()
            val pwd2 = findViewById<EditText>(R.id.editTextTextPassword2).text.toString()

            if (name.isEmpty() || email.isEmpty() || pwd1.isEmpty() || pwd2.isEmpty())
                return@setOnClickListener
            if (pwd1.equals(pwd2) == false){
                Log.d("dbfirebase", "pwd: |$pwd1| |$pwd2|")
                Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val newUser : MutableMap<String, Any> = HashMap()

            newUser["name"] = name
            newUser["email"] = email
            newUser["password"] = pwd1
            val db = FirebaseFirestore.getInstance()
            db.collection("users").whereEqualTo("email", email)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        db.collection("users")
                            .add(newUser)
                            .addOnSuccessListener {
                                Log.d("dbfirebase", "save: $newUser")
                                Toast.makeText(this, "You successfully created an account into the awesome app", Toast.LENGTH_SHORT).show()
                                return@addOnSuccessListener
                            }
                            .addOnCanceledListener {
                                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                            }
                    }
                    Toast.makeText(this, "This account already exist", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents: ", exception)
                }
        }
    }
}