package app.axon.test.ui.userlist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import app.axon.test.R;
import app.axon.test.api.RetrofitClient;

public class UserListActivity extends AppCompatActivity implements UserListContract.View {

    private UserListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_user_list);

        RetrofitClient retrofitClient = new RetrofitClient(this);
        presenter = new UserListPresenter(retrofitClient);
        presenter.attach(this);

        presenter.getJobsList();
    }
}
