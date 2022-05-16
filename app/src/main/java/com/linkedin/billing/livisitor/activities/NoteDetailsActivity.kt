package com.linkedin.billing.livisitor.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linkedin.billing.livisitor.R
import com.linkedin.billing.livisitor.data.NotesProvider
import com.linkedin.billing.livisitor.databinding.ActivityNoteDetailsBinding
import com.linkedin.billing.livisitor.databinding.ActivityNotesBinding

class NoteDetailsActivity : AppCompatActivity() {
    companion object {
        const val ARGUMENT_INDEX = "index"
    }


    private lateinit var binding: ActivityNoteDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val index = intent.getIntExtra(ARGUMENT_INDEX, -1)

        title = NotesProvider.getNotes()[index]
    }
}