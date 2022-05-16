package com.linkedin.billing.livisitor.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linkedin.billing.livisitor.R
import com.linkedin.billing.livisitor.data.NotesProvider
import com.linkedin.billing.livisitor.databinding.ActivityNoteAddBinding
import com.linkedin.billing.livisitor.databinding.ActivityNotesBinding

class NoteAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val note = binding.editNoteText.text.toString()
            if (note.length>1) {
                NotesProvider.addNote(note)
                finish()
            }
        }
    }
}