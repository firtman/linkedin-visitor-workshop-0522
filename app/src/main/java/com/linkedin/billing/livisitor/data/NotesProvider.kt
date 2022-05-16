package com.linkedin.billing.livisitor.data

import android.content.Context
import androidx.core.content.edit

object NotesProvider {
    private var notes = mutableListOf<String>()
    private val subscribers = mutableListOf<()->Unit>() // observers, listeners

    fun loadFromPreferences(context: Context) {
        val prefs = context.getSharedPreferences("notes", Context.MODE_PRIVATE)
        val savedNotes = prefs.getStringSet("notes", null) ?: setOf()
        notes = savedNotes.toMutableList()

        notifySubscribers()
    }

    fun saveToPreferences(context: Context) {
        val prefs = context.getSharedPreferences("notes", Context.MODE_PRIVATE)
        prefs.edit {
            putStringSet("notes", notes.toSet())
        }
    }

    fun onDataSetChanged(subscriber: ()->Unit) {
        subscribers.add(subscriber)
    }

    fun addNote(note: String) {
        notes.add(note)
        // we changed the data, let's notify the subscribers
        notifySubscribers()
    }

    // Observer - Publish-Subscribe
    fun notifySubscribers() = subscribers.forEach { it.invoke() }

    fun getNotes(): List<String> = notes


}