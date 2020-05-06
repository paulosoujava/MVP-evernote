package co.tiagoaguiar.evernotekt

import co.tiagoaguiar.evernotekt.home.presentation.Home
import co.tiagoaguiar.evernotekt.home.presentation.HomePresenter
import co.tiagoaguiar.evernotekt.model.Note
import co.tiagoaguiar.evernotekt.model.RemoteDataSource
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner



@RunWith(MockitoJUnitRunner::class)
class HomePresenterTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxScheduleRule()

    @Mock
    private lateinit var  mockView: Home.View
    @Mock
    private lateinit var mockDataSource: RemoteDataSource
    lateinit var homePresenter: HomePresenter

    private val fakeAllNotes: List<Note>
    get() = arrayListOf(
        Note( 1, "Nota1", "Nota 1 desc", "01/12/2020", "body nota 1"),
        Note( 2, "Nota2", "Nota 2 desc", "03/12/2020", "body nota 2"),
        Note( 3, "Nota3", "Nota 3 desc", "04/12/2020", "body nota 3")
    )

    @Before
    fun setup(){
        homePresenter = HomePresenter(mockView, mockDataSource)
    }

    @Test
    fun `test must get all notes`(){
        // GIVE
        Mockito.doReturn(Observable.just(fakeAllNotes)).`when`(mockDataSource).listNotes()
        //WHEN
        homePresenter.getAllNotes()
        //THEN
        Mockito.verify(mockDataSource).listNotes()
        Mockito.verify(mockView).displayNotes(fakeAllNotes)
    }

    @Test
    fun `test must show empty notes`(){
        // GIVE
        Mockito.doReturn(Observable.just(arrayListOf<Note >())).`when`(mockDataSource).listNotes()
        //WHEN
        homePresenter.getAllNotes()
        //THEN
        Mockito.verify(mockDataSource).listNotes()
        Mockito.verify(mockView).displayEmptyNotes()
    }
}
