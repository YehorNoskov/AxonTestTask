package app.axon.test.api.services

import app.axon.test.api.Api
import app.axon.test.api.RetrofitClient
import app.axon.test.api.response.UsersList
import io.reactivex.Single

class NetworkService {
    val api: Api
    get() {
       return RetrofitClient().client.create(Api::class.java)
    }

    internal fun getUsersList(result: Int, page: Int): Single<UsersList> {
        return api.getUsersList(result, page)
    }
}