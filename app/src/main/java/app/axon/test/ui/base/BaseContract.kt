package app.axon.test.ui.base

import androidx.annotation.StringRes

open class BaseContract {

    interface Presenter<T : View> {
        fun attach(view: T)

        fun detach()
    }

    interface View {

        fun showMessage(message: String)

        fun showMessage(@StringRes text: Int)

        fun showProgress(){}

        fun hideProgress(){}

        fun showNoInternetConnection(){}

        fun showError() {}

    }

}