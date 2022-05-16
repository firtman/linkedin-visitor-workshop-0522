package com.linkedin.billing.livisitor.data

object NotesProvider {
    private val notes = mutableListOf<String>()
    private val subscribers = mutableListOf<()->Unit>()

    init {
        for (i in 0..3) {
            notes.add("Note $i")
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