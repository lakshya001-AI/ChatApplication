package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signupactivity : AppCompatActivity() {

    private lateinit var edtemail1: EditText
    private lateinit var edtpasswordl2: EditText
    private lateinit var btnsignup: Button
    private lateinit var edtname: EditText

    private lateinit var auth: FirebaseAuth

    private lateinit var mDbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupactivity)

        supportActionBar?.hide()

        edtemail1 = findViewById(R.id.emailedittextsignup);
        edtpasswordl2 = findViewById(R.id.passwordedittextsignup);
        btnsignup = findViewById(R.id.signupbutton)
        edtname = findViewById(R.id.nameedittext)

        auth = FirebaseAuth.getInstance()


        btnsignup.setOnClickListener{
            val name = edtname.text.toString()
            val email1 = edtemail1.text.toString()
            val password1 = edtpasswordl2.text.toString()

            signUpUser(name,email1, password1)
        }

    }


    private fun signUpUser(name:String, email1: String, password1:String){
        auth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(this){task ->
            if(task.isSuccessful){
                addUserToDatabase(name, email1, auth.currentUser?.uid !!)
                val intent = Intent(this@signupactivity, mainpage::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this@signupactivity, "signup failed",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun addUserToDatabase(name: String, email1: String, uid : String){

        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name, email1, uid))



    }





}