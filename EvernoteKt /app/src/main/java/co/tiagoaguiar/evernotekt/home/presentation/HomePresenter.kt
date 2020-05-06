package co.tiagoaguiar.evernotekt.home.presentation

import co.tiagoaguiar.evernotekt.model.Note
import co.tiagoaguiar.evernotekt.model.RemoteDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class HomePresenter(
    private val view: Home.View,
    private val dataSource: RemoteDataSource
) : Home.Presenter {

    private val compositeDisposable = CompositeDisposable()

    companion object{
        private val TAG = "HomePresenter"
    }

    override fun getAllNotes() {
        val disposable = notesObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith( notesObserver)

        compositeDisposable.add(disposable)
    }

    override fun stop() {
        compositeDisposable.clear()
    }

    private val  notesObservable: Observable<List<Note>>
    get() = dataSource.listNotes()

    private val notesObserver: DisposableObserver<List<Note>>
        get() = object : DisposableObserver<List<Note>>() {

            override fun onComplete() {
                println("Completed" )
            }

            override fun onNext(notes: List<Note>) {
                if( notes.isNotEmpty())
                    view.displayNotes(notes)
                else
                    view.displayEmptyNotes()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                view.displayError(e.message.toString())
            }

        }

}