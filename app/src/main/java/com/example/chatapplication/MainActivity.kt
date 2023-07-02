package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var edtemail: EditText
    private lateinit var edtpasswordl: EditText
    private lateinit var btnlogin: Button
    private lateinit var signuptextview: TextView

    private lateinit var auth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        edtemail = findViewById(R.id.emailedittext)
        edtpasswordl = findViewById(R.id.passwordedittext)
        btnlogin = findViewById(R.id.loginbutton)
        signuptextview = findViewById(R.id.textviewsignup)

        auth = FirebaseAuth.getInstance()



        btnlogin.setOnClickListener{
            val email2 = edtemail.text.toString()
            val password2 = edtpasswordl.text.toString()

            login(email2, password2)
        }
        signuptextview.setOnClickListener{
            val intent = Intent(this, signupactivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun login(email2: String, password2: String) {

        auth.signInWithEmailAndPassword(email2, password2).addOnCompleteListener(this) {task->
            if(task.isSuccessful){
                val intent = Intent(this@MainActivity, mainpage::class.java)
                startActivity(intent)
                finish()

            }
            else{
                Toast.makeText(this@MainActivity, "User does not exist",Toast.LENGTH_SHORT).show()
            }


        }
    }
}