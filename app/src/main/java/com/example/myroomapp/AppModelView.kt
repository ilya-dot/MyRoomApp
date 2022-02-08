package com.example.myroomapp

import androidx.databinding.ObservableField
import kotlinx.coroutines.*

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AppModelView {

    private val elementManager by lazy {ElementManager()}
    private val df = SimpleDateFormat(MainActivity.context.getString(R.string.dateFormat))
    val fieldTitle = ObservableField("")
    val fieldBody = ObservableField("")
    val fieldDate = ObservableField(df.format(Date()))
    val adapter = ObservableField(RvAdapterElement(elementManager))

    var title get() =fieldTitle.get().toString(); set(value) =fieldTitle.set(value)
    var body get() = fieldBody.get().toString() ;set(value)=fieldBody.set(value)
    var date
        get() = try{df.parse(fieldDate.get().toString())?: Date()
        }catch (_: Exception){
            Date()
        }
        set(value) =fieldDate.set(df.format(value))

    fun addNote() {
        val note =Element().apply {
            title = this@AppModelView.title.ifEmpty { "Заметка" }
            this@AppModelView.title=""
            body = this@AppModelView.body
            this@AppModelView.body=""
            date = this@AppModelView.date
        }
        elementManager addNote note
        adapter.set( RvAdapterElement(elementManager))
    }

}