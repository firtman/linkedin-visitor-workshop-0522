package com.linkedin.billing.livisitor.data

import android.content.Context
import androidx.core.content.edit

object NotesProvider {
    private var notes = mutableListOf<String>()
    private val subscribers = mutableListOf<()->Unit>()

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

    fun onDataSetChanged(observer: ()->Unit) {
        subscribers.add(observer)
    }

    fun addNote(note: String) {
        notes.add(note)
        notifySubscribers()
    }

    fun notifySubscribers() = subscribers.forEach { it.invoke() }

    fun getNotes(): List<String> = notes

}