package com.linkedin.billing.livisitor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import com.linkedin.billing.livisitor.data.API
import com.linkedin.billing.livisitor.R
import com.linkedin.billing.livisitor.data.NotesProvider

class LoginActivity : AppCompatActivity() {

    companion object {
        const val PREFS_LOGIN = "linkedin_logged_in"
        const val KEY_LOGIN = "logged_in"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if the user is logged in
        val prefs = getSharedPreferences(PREFS_LOGIN, Context.MODE_PRIVATE)
        if (prefs.getBoolean(KEY_LOGIN, false)) {
            // the user is logged in
            goHome()
        }


        setContentView(R.layout.activity_login)

        val editUsername = findViewById<EditText>(R.id.editUsername)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = editUsername.text.toString()
            val password = editPassword.text.toString()

            API.login(username, password) { ok ->
                val message = "Message: ${getString(R.string.login_result_ko)}"

                Toast.makeText(
                    this,
                    if (ok) R.string.login_result_ok else R.string.login_result_ko,
                    Toast.LENGTH_LONG
                ).show()

                if (ok) {
                    goHome()

                    // Save a flag, we are logged in
                    val prefs = getSharedPreferences(PREFS_LOGIN, MODE_PRIVATE)
                    prefs.edit {
                        putBoolean(KEY_LOGIN, true)
                    }
                }
            }
        }


    }

    private fun goHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish() // kill current activity from stack
    }
}