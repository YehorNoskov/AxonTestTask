package app.axon.test.ui.base


abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    var view: V? = null

    override fun attach(view: V) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }
}
