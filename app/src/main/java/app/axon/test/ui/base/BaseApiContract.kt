package app.axon.test.ui.base

class BaseApiContract : BaseContract() {
    interface Presenter<V : View> : BaseContract.Presenter<V> {
        fun subscribe()
        fun unsubscribe()
    }
}