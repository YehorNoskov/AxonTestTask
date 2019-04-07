package app.axon.test.ui.userdetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.imagepipeline.request.ImageRequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.databinding.DataBindingUtil;
import app.axon.test.R;
import app.axon.test.databinding.ActivityUserDetailsBinding;
import app.axon.test.ui.userlist.UserModel;

import static app.axon.test.ui.userlist.UserListActivity.EXTRA_USER_INFO;
import static app.axon.test.utills.DateUtils.getDateString;

public class UserDetailsActivity extends AppCompatActivity {

    private ActivityUserDetailsBinding layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = DataBindingUtil.setContentView(this, R.layout.activity_user_details);

        getSupportActionBar().setTitle(getString(R.string.title_profile));

        UserModel userData;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                userData = null;
            } else {
                userData = extras.getParcelable(EXTRA_USER_INFO);
            }
        } else {
            userData = savedInstanceState.getParcelable(EXTRA_USER_INFO);
        }

        showProfile(userData);
        initClickListeners();
    }

    public void showProfile(UserModel userModel) {
        notActiveEdtx(layout.edtxDob);
        notActiveEdtx(layout.edtxCell);
        notActiveEdtx(layout.edtxPhone);

        layout.ivAvatar.setImageRequest(ImageRequest.fromUri(userModel.getUserAvatarLarge()));
        layout.tvUserName.setText(userModel.getUserName());
        layout.tvUserGender.setText(userModel.getUserGender());
        layout.edtxDob.setText(getDateString(userModel.getUserDob()));
        layout.edtxCell.setText(userModel.getUserPhoneCell());
        layout.edtxPhone.setText(userModel.getUserPhone());
    }

    private void notActiveEdtx(AppCompatEditText editText) {
        editText.setKeyListener(null);
    }

    private void initClickListeners() {
        layout.edtxCell.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                callIntent(layout.edtxCell.getText().toString());
            }
        });

        layout.edtxPhone.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                callIntent(layout.edtxPhone.getText().toString());
            }
        });
    }

    private void callIntent(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
        startActivity(intent);
    }
}
