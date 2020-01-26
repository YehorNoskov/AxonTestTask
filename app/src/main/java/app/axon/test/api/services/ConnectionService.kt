package app.axon.test.api.services

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import io.reactivex.subjects.BehaviorSubject

class ConnectionService(private val context: Context) {

    enum class ConnectionState {
        UNKNOWN,
        CONNECTED,
        DISCONNECTED
    }

    private var connectionState: ConnectionState = ConnectionState.UNKNOWN
    val isNetworkConnectionAvailableSubject: BehaviorSubject<ConnectionState> = BehaviorSubject.create()
    private var connectivityManager: ConnectivityManager? = null


    internal fun onConnectionStateChanged() {
        val newConnectionState = if (isNetworkEnabled()) ConnectionState.CONNECTED else ConnectionState.DISCONNECTED

        if (connectionState != newConnectionState) {
            connectionState = newConnectionState
            isNetworkConnectionAvailableSubject.onNext(connectionState)
        }
    }

    private fun isNetworkEnabled(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getConnectionManager()?.getNetworkCapabilities(getConnectionManager()?.activeNetwork)?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true ||
                    getConnectionManager()?.getNetworkCapabilities(getConnectionManager()?.activeNetwork)?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true
        } else {
            getConnectionManager()?.activeNetworkInfo?.isConnected == true
        }
    }

    private fun getConnectionManager(): ConnectivityManager? {
        if (connectivityManager == null) {
            connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        }
        return connectivityManager
    }


}