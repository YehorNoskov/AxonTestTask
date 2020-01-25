package app.axon.test.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UsersList {
    @SerializedName("results")
    @Expose
    var results: List<UserResp>? = null
    @SerializedName("info")
    @Expose
    var info: Info? = null

}