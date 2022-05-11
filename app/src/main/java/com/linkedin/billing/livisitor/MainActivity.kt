package com.linkedin.billing.livisitor

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // Snippets
//        val prefs = getSharedPreferences("login",
//            Context.MODE_PRIVATE);
//        val value = prefs.getString("name", "unnamed")
//        prefs.edit {
//            putString("name", "Max")
//        }


        val editUsername = findViewById<EditText>(R.id.editUsername)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = editUsername.text.toString()
            val password = editPassword.text.toString()

            API.login(username, password) { ok ->
                val message = "Message: ${getString(R.string.login_result_ko)}"

                Toast.makeText(this,
                    if (ok) R.string.login_result_ok else R.string.login_result_ko,
                    Toast.LENGTH_LONG).show()

                if (ok) {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        }


    }
}