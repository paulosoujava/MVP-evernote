package co.tiagoaguiar.evernotekt.add.presentation

import co.tiagoaguiar.evernotekt.model.Note

interface Add {
    interface View{
        fun displayError( message: String)
        fun displayNote( title: String, body: String)
        fun returnToHome()
    }
    interface Presenter{
        fun stop()
        fun getNote(noteId: Int)
        fun createNote(title: String, body: String)
    }
}