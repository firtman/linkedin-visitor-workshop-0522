package com.linkedin.billing.livisitor.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.linkedin.billing.livisitor.adapter.NotesAdapter
import com.linkedin.billing.livisitor.data.NotesProvider
import com.linkedin.billing.livisitor.databinding.ActivityNotesBinding

class NotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup the binding for View Binding
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup the Recyclerview
//        val recycler = findViewById<RecyclerView>(R.id.recyclerNotes)
        val recycler = binding.content.recyclerNotes
        recycler.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        recycler.adapter = NotesAdapter()

        // FAButton for creating a new note
        binding.fabAddNote.setOnClickListener {
            NotesProvider.addNote("NEW NOTE")
        }
        // Replaces the Android Action bar with the new Material Toolbar
        setSupportActionBar(binding.toolbar)
    }

}