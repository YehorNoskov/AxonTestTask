package app.axon.test.utills

import androidx.appcompat.widget.AppCompatEditText

fun AppCompatEditText?.setNoActive(){
    this?.keyListener = null
}