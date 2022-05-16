package com.linkedin.billing.livisitor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.linkedin.billing.livisitor.R
import com.linkedin.billing.livisitor.data.NotesProvider

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    var selectedListener: (Int)->Unit = { }

    init {
        // I'm subscribing to data changes
        NotesProvider.onDataSetChanged {
            notifyDataSetChanged() // superclass - Android SDK API
        }
    }

    override fun getItemCount(): Int = NotesProvider.getNotes().size

//    executed by the RecyclerView when it needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val vhView = inflater.inflate(R.layout.item_note, parent, false)
        return NotesViewHolder(vhView)
    }

//    executed by the RecyclerView when it needs to "fill data in" a view holder
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.setOnClickListener { 
            selectedListener(position)
        }
        holder.bind(NotesProvider.getNotes()[position])
    }

    fun onSelectListener(listener: (Int) -> Unit) {
        selectedListener = listener
    }

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: String) {
            itemView.findViewById<TextView>(R.id.txtNoteText).text = note
        }
    }
}
