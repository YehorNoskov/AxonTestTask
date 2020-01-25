package app.axon.test.ui.base

interface StandartDialogs {
    fun showToast(message: String)
    fun showToast(message: String, length: Int)
    fun showMessage(message: String)
    fun showMessage(message: Int)
}
