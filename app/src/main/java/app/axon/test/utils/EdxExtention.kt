package app.axon.test.utils

import androidx.appcompat.widget.AppCompatEditText

fun AppCompatEditText?.setNoActive(){
    this?.keyListener = null
}