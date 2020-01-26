package app.axon.test.ui.base

import io.reactivex.disposables.CompositeDisposable
import java.net.ConnectException
import java.net.UnknownHostException

abstract class BaseApiPresenter<V : BaseContract.View> : BasePresenter<V>(), BaseApiContract.Presenter<V> {

    private var disposables = CompositeDisposable()


    override fun subscribe() {}

    override fun unsubscribe() {
        clearDisposables()
    }

    protected val compositeDisposable: CompositeDisposable
        get() {
            if (disposables.isDisposed) {
                disposables = CompositeDisposable()
            }
            return disposables
        }

    private fun clearDisposables() {
        if (!disposables.isDisposed) {
            disposables.dispose()
            view?.hideProgress()
        }
    }

    protected fun parseError(throwable: Throwable) {
        throwable.printStackTrace()
        if (throwable is ConnectException || throwable is UnknownHostException) {
            view?.showNoInternetConnection()
        } else {
            view?.showError()
        }
    }
}
