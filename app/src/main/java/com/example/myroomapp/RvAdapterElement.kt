package com.example.myroomapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class RvAdapterElement (var elementManager: ElementManager) :
    RecyclerView.Adapter<RvAdapterElement.ElementViewHolder>() {

    var df = SimpleDateFormat("d-M-y")

    class ElementViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var editTextViewElementName: EditText = itemView.findViewById(R.id.editTextElementName)
        var editTextElementBody: EditText = itemView.findViewById(R.id.editTextElementBody)
        var editTextElementDate: EditText = itemView.findViewById(R.id.editTextElementDate)
        var buttonElementDelete: Button = itemView.findViewById(R.id.buttonElementDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.r_element, parent, false)

        return ElementViewHolder(v)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        var element = elementManager.getNote(position)
        with(holder) {
            editTextViewElementName.setText(element.title)
            editTextElementBody.setText(element.id.toString())
            editTextElementDate.setText(df.format(element.date))
            buttonElementDelete.setOnClickListener {
                elementManager.deleteNote(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, elementManager.count)
            }
        }

    }

    override fun getItemCount(): Int {
        return elementManager.count
    }
}