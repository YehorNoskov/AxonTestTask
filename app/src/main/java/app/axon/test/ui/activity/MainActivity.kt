package app.axon.test.ui.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import app.axon.test.R
import app.axon.test.api.services.ConnectionService
import app.axon.test.constants.Actions
import app.axon.test.ui.base.StandartDialogs
import app.axon.test.utils.setVisibility
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainContract.View, StandartDialogs {

    private var navController: NavController? = null
    private lateinit var connectionReceiver: BroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = MainPresenter(ConnectionService(applicationContext))
        presenter.attach(this)

        connectionReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent?) {

                if (intent?.action == Actions.CONNECTIVITY_CHANGE) {
                    presenter.onConnectionStateChanged()
                }
            }
        }

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(Actions.CONNECTIVITY_CHANGE)
        registerReceiver(connectionReceiver, filter)
    }

    override fun setConnectionState(isConnected: Boolean) {
        tvNoInternet.setVisibility(!isConnected)
    }

    override fun showToast(message: String) {
        if (!this.isFinishing) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showToast(message: String, length: Int) {
        if (!this.isFinishing) {
            Toast.makeText(this, message, length).show()
        }
    }

    override fun showMessage(message: String) {
        showToast(message, Toast.LENGTH_LONG)
    }

    override fun showMessage(message: Int) {
        showToast(getString(message), Toast.LENGTH_LONG)
    }

    internal fun getNavigationController(): NavController? {
        return navController
    }

}