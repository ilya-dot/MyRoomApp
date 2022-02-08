package com.example.myroomapp

import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ElementManager {
    private val elements by lazy {
        val deferred = MainActivity.scope.async { elementDao.getAll() }
        while (deferred.isActive);
        if (deferred.isCancelled) return@lazy arrayListOf<Element>()
        arrayListOf(*deferred.getCompleted().toTypedArray())
    }
    val scope by lazy { MainActivity.scope }
    val elementDao = AppDb.instance!!.elementDao
    infix fun addNote(element: Element): Boolean {
        val deferred = MainActivity.scope.async { elementDao.insert(element) }
        while (deferred.isActive);
        if (!deferred.isCancelled) element.id = deferred.getCompleted().toInt()
        return elements.add(element)
    }

    infix fun deleteNote(index: Int): Element {
        val id = elements[index].id
        MainActivity.scope.launch {
            elementDao.deleteById(id)
        }
        return elements.removeAt(index)
    }

    infix fun getNote(index: Int): Element = elements.get(index)
    val count: Int get() = elements.size
    override fun toString(): String = "[${elements.joinToString { it.toString() }}]"
}