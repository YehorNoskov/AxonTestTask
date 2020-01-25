package app.axon.test.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Street {
    @SerializedName("number")
    @Expose
    var number: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}