package app.axon.test.ui.activity

import app.axon.test.api.services.ConnectionService
import app.axon.test.ui.base.BaseApiPresenter
import app.axon.test.utils.NoConnectivityException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val connectionService: ConnectionService) : BaseApiPresenter<MainContract.View>(), MainContract.Presenter {

    private var currentConnectionState = ConnectionService.ConnectionState.CONNECTED

    init {
        compositeDisposable.add(connectionService.isNetworkConnectionAvailableSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { state -> updateConnectionState(state) })
    }

    override fun onConnectionStateChanged() {
        connectionService.onConnectionStateChanged()
    }


    private fun updateConnectionState(connectionState: ConnectionService.ConnectionState) {
        if (connectionState == ConnectionService.ConnectionState.DISCONNECTED || currentConnectionState != connectionState) {
            currentConnectionState = connectionState
            view?.setConnectionState(currentConnectionState == ConnectionService.ConnectionState.CONNECTED)
        }
    }
}