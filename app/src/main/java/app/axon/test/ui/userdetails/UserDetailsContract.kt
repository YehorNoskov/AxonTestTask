package app.axon.test.ui.userdetails

import android.os.Bundle
import app.axon.test.ui.base.BaseApiContract
import app.axon.test.ui.base.BaseContract
import app.axon.test.ui.userlist.UserModel
import java.util.*


class UserDetailsContract {

    interface View : BaseContract.View {
        fun showUserAvatar(link : String)

        fun showUserName(name: String)

        fun showUserGender(gender: String)

        fun showUserDob(dob:String)

        fun showUserCell(cell: String)

        fun showUserPhone(phone: String)

    }

    interface Presenter : BaseApiContract.Presenter<View> {
        fun setBundle(bundle: Bundle?)
    }
}