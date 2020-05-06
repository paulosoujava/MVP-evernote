package co.tiagoaguiar.evernotekt

import co.tiagoaguiar.evernotekt.add.presentation.Add
import co.tiagoaguiar.evernotekt.add.presentation.AddPresenter
import co.tiagoaguiar.evernotekt.home.presentation.HomePresenter
import co.tiagoaguiar.evernotekt.model.Note
import co.tiagoaguiar.evernotekt.model.RemoteDataSource
import com.nhaarman.mockito_kotlin.anyOrNull
import io.reactivex.Observable
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner



@RunWith(MockitoJUnitRunner::class)
class AddPresenterTest : BaseTest(){

    @Rule
    @JvmField
    var testSchedulerRule = RxScheduleRule()

    @Mock
    private lateinit var  mockView: Add.View
    @Mock
    private lateinit var mockDataSource: RemoteDataSource

    @Captor
    private lateinit var noteArgCaptor: ArgumentCaptor<Note>
    lateinit var AddPresenter: AddPresenter


    @Before
    fun setup(){
        AddPresenter = AddPresenter(mockView, mockDataSource)
    }

    @Test
    fun `test must not add note with empty body`(){

        //WHEN
        AddPresenter.createNote("" ,"")
        //THEN
        Mockito.verify(mockView).displayError("Título e corpo  da nota deve ser informado ")
    }
    @Test
    fun `test must add note`(){
        //GIVEN (dado esta nota com estas propriedades, quando o
        // mock do datasource chamar o metodo create note capture os params e colque no capt, para verificar se os
        // dados chamados foi igual ao passado)
        val note = Note( title =  "TItulo A", body = "Body A")
        Mockito.doReturn(Observable.just(note)).`when`(mockDataSource).createNote(captureArg(noteArgCaptor))
        //WHEN (f)
        AddPresenter.createNote("TItulo A" ,"Body A")

        //THEN  ( captura os argumentos)
        Mockito.verify(mockDataSource).createNote(captureArg(noteArgCaptor))

        //eh igual ??
        Assert.assertThat( noteArgCaptor.value.title, CoreMatchers.equalTo("TItulo A"))
        Assert.assertThat( noteArgCaptor.value.body, CoreMatchers.equalTo("Body A"))
        //ta voltando pra home?
        Mockito.verify(mockView).returnToHome()
    }

    @Test
    fun `test must show error message when creation failure`(){
        Mockito.doReturn(Observable.error<Throwable>(Throwable("server error"))).`when`(
            mockDataSource
        ).createNote(anyOrNull())
        AddPresenter.createNote("TItulo A" ,"Body A")
        Mockito.verify(mockDataSource).createNote(captureArg(noteArgCaptor))
        Assert.assertThat( noteArgCaptor.value.title, CoreMatchers.equalTo("TItulo A"))
        Assert.assertThat( noteArgCaptor.value.body, CoreMatchers.equalTo("Body A"))
        Mockito.verify(mockView).displayError("Erro ao criar nota")
    }
}
