package app.axon.test.ui.base

open class BaseContract {

    interface Presenter<T : View> {
        fun attach(view: T)

        fun detach()
    }

    interface View {

        fun showMessage(message: String)

        fun showMessage(message: Int)

        fun showProgress(){}

        fun hideProgress(){}

        fun showNoInternetConnection(){}

        fun showError() {}

    }

}