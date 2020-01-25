package app.axon.test.ui.base

import android.content.Intent
import android.provider.Settings
import android.view.View
import androidx.fragment.app.Fragment
import app.axon.test.R
import app.axon.test.ui.activity.MainActivity
import com.google.android.material.snackbar.Snackbar


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
        parentActivity!!.showToast(message, length)
    }

    override fun showMessage(message: String) {
        hideProgress()
        parentActivity?.showMessage(message)
    }

    override fun showMessage(text: Int) {
        hideProgress()
        parentActivity?.showMessage(text)
    }

    override fun showError() {
        showMessage(R.string.error_default)
    }

    override fun showNoInternetConnection() {
        Snackbar.make(view!!, getString(R.string.message_no_internet), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.title_wireless_settings)) { view1: View? ->
                    val myIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                    startActivity(myIntent)
                }.show()
    }

}