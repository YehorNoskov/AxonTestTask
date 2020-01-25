package app.axon.test.ui.userlist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserModel (
    val userName: String?,
    val userAvatarMedium: String,
    val userAvatarLarge: String?,
    val userGender: String?,
    val userDob: String?,
    val userEmail: String?,
    val userPhone: String?,
    val userPhoneCell: String
): Parcelable