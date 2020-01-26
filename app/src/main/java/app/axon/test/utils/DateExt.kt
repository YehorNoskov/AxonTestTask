package app.axon.test.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

fun String?.setDobFormat(): String{
    return DateTime(this).toString(DateTimeFormat.forPattern("yyyy-MM-dd"))
}