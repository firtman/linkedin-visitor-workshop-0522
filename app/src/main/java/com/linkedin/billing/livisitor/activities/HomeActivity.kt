package com.linkedin.billing.livisitor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import androidx.core.content.edit
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.linkedin.billing.livisitor.R
import com.linkedin.billing.livisitor.data.NotesProvider

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<Button>(R.id.btnNotes).setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnVisitor).setOnClickListener {
            val intent = Intent(this, VisitorActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId== R.id.action_logout) {
            logout()
        }
        return super.onOptionsItemSelected(item)
    }

    fun logout() {
        // 1: Open Login Activity
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        // 2: Get rid off this activity from the stack
        finish()
        // 3: Save that log out flag
        // TODO: move all the shared preferences behavior to a new class
        val prefs = getSharedPreferences(LoginActivity.PREFS_LOGIN, Context.MODE_PRIVATE)
        prefs.edit {
            putBoolean(LoginActivity.KEY_LOGIN, false)
        }
    }
}