package app.axon.test.ui.userlist;

import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.axon.test.R;
import app.axon.test.databinding.ActivityUserListBinding;
import app.axon.test.ui.base.BaseActivity;

import static app.axon.test.utills.SnackBar.showSnackBar;

public class UserListActivity extends BaseActivity implements UserListContract.View {

    private UserListPresenter presenter;
    private ActivityUserListBinding layout;
    private UsersAdapter mUsersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        layout = DataBindingUtil.setContentView(this, R.layout.activity_user_list);

        Fresco.initialize(this);
        getSupportActionBar().setTitle(getString(R.string.title_home));

        presenter = new UserListPresenter(getRetrofitClient());
        presenter.attach(this);

        mUsersAdapter = new UsersAdapter();

        layout.rvUsers.setAdapter(mUsersAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        layout.rvUsers.setLayoutManager(llm);
        layout.rvUsers.addOnScrollListener(presenter.getPagination(llm));

        layout.swipeContainer.setColorSchemeResources(
                R.color.colorPrimary,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);

        presenter.getJobsList(false);

        layout.swipeContainer.setOnRefreshListener(() -> {
            presenter.refresh();
        });

    }

    @Override
    public void showJobsList(List<UserModel> userModelList, boolean append) {

        hideRefresh();
        if (append) {
            mUsersAdapter.appendItems(userModelList);
        } else {
            mUsersAdapter.setItems(userModelList);
        }

        hideProgress();
    }

    @Override
    public void showSnackbar() {
        hideProgress();
        hideRefresh();
        showSnackBar(layout.linearLayout);
    }


    private void hideRefresh() {
        if (layout.swipeContainer.isRefreshing()) {
            layout.swipeContainer.setRefreshing(false);
        }
    }
}
