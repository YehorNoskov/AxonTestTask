package app.axon.test.ui.base

import androidx.fragment.app.Fragment
import app.axon.test.R
import app.axon.test.ui.activity.MainActivity


open class BaseFragment<P : BaseContract.Presenter<*>> : Fragment(), StandartDialogs,
        BaseContract.View {

    internal var presenter: P? = null

    internal val parentActivity: MainActivity?
        get() = activity as MainActivity?


    override fun onDestroyView() {
        super.onDestroyView()
        if (presenter is BaseApiContract.Presenter<*>) {
            val baseApiPresenter = presenter as BaseApiPresenter<*>
            baseApiPresenter.unsubscribe()
        }
        presenter?.detach()
    }

    override fun onDetach() {
        super.onDetach()
        presenter = null
    }

    override fun showToast(message: String) {
        parentActivity?.showToast(message)
    }

    override fun showToast(message: String, length: Int) {
        parentActivity?.showToast(message, length)
    }

    override fun showMessage(message: String) {
        hideProgress()
        parentActivity?.showMessage(message)
    }

    override fun showMessage(message: Int) {
        hideProgress()
        parentActivity?.showMessage(message)
    }

    override fun showError() {
        showMessage(R.string.error_default)
    }

    override fun showNoInternetConnection() {
        parentActivity?.setConnectionState(false)
    }

}