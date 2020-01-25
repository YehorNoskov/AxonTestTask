package app.axon.test.ui.userdetails

import android.os.Bundle
import app.axon.test.api.constants.BundleKeys
import app.axon.test.ui.base.BaseApiPresenter
import app.axon.test.ui.userlist.UserModel

class UserDetailsPresenter :
        BaseApiPresenter<UserDetailsContract.View>(),
        UserDetailsContract.Presenter {

    override fun setBundle(bundle: Bundle?) {
        if (bundle != null) {
            val userModel = bundle.getParcelable(BundleKeys.USER_INFO) as UserModel?
            if (userModel != null) {
                userModel.userAvatarMedium?.let { view?.showUserAvatar(it) }
                userModel.userName?.let { view?.showUserName(it) }
                userModel.userGender?.let { view?.showUserGender(it) }
                userModel.userDob?.let { view?.showUserDob(it) }
                userModel.userPhoneCell?.let { view?.showUserCell(it) }
                userModel.userPhone?.let { view?.showUserPhone(it) }
            }
        }
    }

}
