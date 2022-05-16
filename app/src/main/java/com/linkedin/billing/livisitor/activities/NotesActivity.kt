package com.linkedin.billing.livisitor.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.linkedin.billing.livisitor.adapter.NotesAdapter
import com.linkedin.billing.livisitor.data.NotesProvider
import com.linkedin.billing.livisitor.databinding.ActivityNoteAddBinding
import com.linkedin.billing.livisitor.databinding.ActivityNotesBinding

class NotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup the binding for View Binding
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Notes persistence
        NotesProvider.loadFromPreferences(this)
        NotesProvider.onDataSetChanged {
            NotesProvider.saveToPreferences(this)
        }

        // Setup the Recyclerview
        val recycler = binding.content.recyclerNotes
        recycler.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        val adapter = NotesAdapter()
        recycler.adapter = adapter
        adapter.onSelectListener { index ->
            println("You clicked on $index")
            val detailsIntent = Intent(this, NoteDetailsActivity::class.java)
            detailsIntent.putExtra(NoteDetailsActivity.ARGUMENT_INDEX, index)
            startActivity(detailsIntent)
        }

        // FAButton for creating a new note
        binding.fabAddNote.setOnClickListener {
            startActivity(
                Intent(this, NoteAddActivity::class.java)
            )
        }
        // Replaces the Android Action bar with the new Material Toolbar
        setSupportActionBar(binding.toolbar)
    }

    override fun onPause() {
        NotesProvider.saveToPreferences(this)

        super.onPause()
    }

}