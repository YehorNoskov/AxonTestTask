package app.axon.test.ui.userlist;

import app.axon.test.api.Api;
import app.axon.test.api.RetrofitClient;
import app.axon.test.api.response.UsersList;
import app.axon.test.ui.base.BaseApiPresenter;
import app.axon.test.ui.base.BaseContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserListPresenter extends BaseApiPresenter implements UserListContract.Presenter {

    private UserListContract.View view;
    private int currentPage = 0;
    private final int PER_PAGE_ITEMS_COUNT = 20;
    private RetrofitClient mRetrofitClient;

    public UserListPresenter(RetrofitClient retrofitClient) {
        mRetrofitClient = retrofitClient;
    }

    @Override
    public void attach(UserListContract.View view) {
        this.view = view;
    }

    @Override
    protected BaseContract.View getView() {
        return view;
    }

    @Override
    public void getJobsList() {

        mRetrofitClient.getClient().create(Api.class).getUsersList(PER_PAGE_ITEMS_COUNT, currentPage).enqueue(new Callback<UsersList>() {
            @Override
            public void onResponse(Call<UsersList> call, Response<UsersList> response) {
            }

            @Override
            public void onFailure(Call<UsersList> call, Throwable t) {
                parseError(t);
            }
        });
    }
}
