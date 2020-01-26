package app.axon.test.ui.activity

import app.axon.test.ui.base.BaseApiContract
import app.axon.test.ui.base.BaseContract

class MainContract {

    interface View : BaseContract.View {
        fun setConnectionState(isConnected: Boolean)
    }

    interface Presenter : BaseApiContract.Presenter<View> {
        fun onConnectionStateChanged()
    }

}