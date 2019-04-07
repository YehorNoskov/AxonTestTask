package app.axon.test.ui.userlist;

import android.app.Activity;
import android.os.Bundle;

import app.axon.test.R;

public class UserListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);

        setContentView(R.layout.activity_user_list);
    }
}
