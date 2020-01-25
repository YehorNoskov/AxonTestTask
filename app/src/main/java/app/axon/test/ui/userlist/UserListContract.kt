package app.axon.test.ui.userlist

import app.axon.test.ui.base.BaseApiContract
import app.axon.test.ui.base.BaseContract


class UserListContract {

    interface View : BaseContract.View {
        fun setJobsList(userModelList: List<UserModel>)

        fun appendJobsList(userModelList: List<UserModel>)

        fun hideRefresh()

    }

    interface Presenter : BaseApiContract.Presenter<View> {
        fun getJobsList(append: Boolean)

        fun refresh()
    }
}