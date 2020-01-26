package app.axon.test.ui.userlist

import app.axon.test.api.response.UserResp
import app.axon.test.api.services.NetworkService
import app.axon.test.constants.Pagination
import app.axon.test.ui.base.BaseApiPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class UserListPresenter(
        private val networkService: NetworkService
) :
        BaseApiPresenter<UserListContract.View>(),
        UserListContract.Presenter {
    private var currentPage = 0
    private var isLoading = false

    override fun getJobsList(append: Boolean) {
        view?.showProgress()
        compositeDisposable.add(networkService.getUsersList(Pagination.PER_PAGE_ITEMS_COUNT, currentPage)
                .map { r -> r.results?.let { modelsMapping(it) } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    view?.hideProgress()
                    view?.hideRefresh()
                }
                .subscribe({ resp ->
                    if (resp != null) {
                        parseResponse(resp, append)
                    }
                }, { error -> parseError(error) })
        )
    }

    override fun refresh() {
        currentPage = 0
        getJobsList(false)
    }

    private fun parseResponse(resp: List<UserModel>, append: Boolean) {
        isLoading = false
        currentPage += 1
        if (append) {
            view?.appendJobsList(resp)
        } else view?.setJobsList(resp)
    }

    private fun modelsMapping(userRespList: List<UserResp>): List<UserModel> {
        val userModels: MutableList<UserModel> = ArrayList()
        for (userResp in userRespList) {

            userModels.add(UserModel(userResp.name?.first + " " + userResp.name?.last,
                    userResp.picture?.medium,
                    userResp.picture?.large,
                    userResp.gender,
                    userResp.dob?.date,
                    userResp.email,
                    userResp.phone,
                    userResp.cell))
        }
        return userModels
    }
}
