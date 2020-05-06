package co.tiagoaguiar.evernotekt

import co.tiagoaguiar.evernotekt.model.Note
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NoteTest {
    val note = Note( title = "Nota A", body = "NotaA conteudo", date="20/02/2020")

    @Test
    fun `test should format date pattern to month and year`(){

        assertEquals("Fev 2020", note.createdDate)
    }

    @Test
    fun `test should format date case empty`(){
        note.date = ""
        assertEquals("", note.createdDate)
    }

    @Test
    fun `test should format date case null`(){
        val n = Note( title = "Nota A", body = "NotaA conteudo")
        assertEquals("", n.createdDate)
    }
}
