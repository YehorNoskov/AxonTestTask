package app.axon.test.ui.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.axon.test.api.RetrofitClient


abstract class BaseActivity : AppCompatActivity(), StandartDialogs {


    fun getRetrofitClient(): RetrofitClient {
        return RetrofitClient(baseContext)
    }

    override fun showToast(message: String) {
        if (!this.isFinishing) {
            Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showToast(message: String, length: Int) {
        if (!this.isFinishing) {
            Toast.makeText(baseContext, message, length).show()
        }
    }

    override fun showMessage(message: String) {
        showToast(message, Toast.LENGTH_LONG)
    }

    override fun showMessage(message: Int) {
        showToast(getString(message), Toast.LENGTH_LONG)
    }
}
