package com.linkedin.billing.livisitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            }
        }


    }
}