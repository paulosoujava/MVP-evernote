package co.tiagoaguiar.evernotekt.add.presentation

import co.tiagoaguiar.evernotekt.model.Note
import co.tiagoaguiar.evernotekt.model.RemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class AddPresenter(
    private val view: Add.View,
    private val dataSource: RemoteDataSource
) : Add.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun getNote(noteId: Int) {
        val disposable =   dataSource.getNote(noteId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith( getNoteObserver)

        compositeDisposable.add(disposable)
    }

    override fun createNote(title: String, body: String) {

        if( title.isEmpty() || body.isEmpty() ){
            view.displayError("Título e corpo  da nota deve ser informado ")
            return
        }
        val note = Note( title = title, body = body)
        val disposable =   dataSource.createNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith( createNoteObserver)

        compositeDisposable.add(disposable)
    }

    private val createNoteObserver: DisposableObserver<Note>
        get() = object : DisposableObserver<Note>() {
            override fun onComplete() {
                print("Completed")
            }
            override fun onNext(note: Note) {
                view.returnToHome()
            }
            override fun onError(e: Throwable) {
               // e.printStackTrace()
                view.displayError("Erro ao criar nota")
            }
        }
    private val getNoteObserver: DisposableObserver<Note>
        get() = object : DisposableObserver<Note>() {
            override fun onComplete() {
                print("Completed")
            }
            override fun onNext(note: Note) {
                    view.displayNote(note.title ?: "", note.body ?: "")
            }
            override fun onError(e: Throwable) {
                e.printStackTrace()
                view.displayError("Erro ao carregar nota")
            }

        }



}