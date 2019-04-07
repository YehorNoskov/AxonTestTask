package app.axon.test.ui.userlist;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {

    private String userName;
    private String userAvatarMedium;
    private String userAvatarLarge;
    private String userGender;
    private String userDob;
    private String userEmail;
    private String userPhone;
    private String userPhoneCell;


    public String getUserName() {
        return userName;
    }

    public UserModel setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserAvatarMedium() {
        return userAvatarMedium;
    }

    public UserModel setUserAvatarMedium(String userAvatarMedium) {
        this.userAvatarMedium = userAvatarMedium;
        return this;
    }

    public String getUserAvatarLarge() {
        return userAvatarLarge;
    }

    public UserModel setUserAvatarLarge(String userAvatarLarge) {
        this.userAvatarLarge = userAvatarLarge;
        return this;
    }

    public String getUserGender() {
        return userGender;
    }

    public UserModel setUserGender(String userGender) {
        this.userGender = userGender;
        return this;
    }

    public String getUserDob() {
        return userDob;
    }

    public UserModel setUserDob(String userDob) {
        this.userDob = userDob;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserModel setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public UserModel setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public String getUserPhoneCell() {
        return userPhoneCell;
    }

    public UserModel setUserPhoneCell(String userPhoneCell) {
        this.userPhoneCell = userPhoneCell;
        return this;
    }
    public UserModel(){}

    public UserModel(Parcel in) {
        String[] data = new String[8];
        in.readStringArray(data);
        userName = data[0];
        userAvatarMedium = data[1];
        userAvatarLarge = data[2];
        userGender = data[3];
        userDob = data[4];
        userEmail = data[5];
        userPhone = data[6];
        userPhoneCell = data[7];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{userName, userAvatarMedium, userAvatarLarge, userGender,
                userDob, userEmail, userPhone, userPhoneCell});
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {

        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
