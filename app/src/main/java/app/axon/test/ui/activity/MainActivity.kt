package app.axon.test.ui.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import app.axon.test.R
import app.axon.test.ui.base.BaseActivity



class MainActivity : BaseActivity() {

    private var navController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    internal fun getNavigationController(): NavController? {
        return navController
    }
}