package app.axon.test.ui.userlist;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import app.axon.test.api.Api;
import app.axon.test.api.RetrofitClient;
import app.axon.test.api.response.UserResp;
import app.axon.test.api.response.UsersList;
import app.axon.test.ui.base.BaseApiPresenter;
import app.axon.test.ui.base.BaseContract;
import app.axon.test.ui.base.PaginationScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserListPresenter extends BaseApiPresenter implements UserListContract.Presenter {

    private UserListContract.View view;
    private int currentPage = 0;
    private boolean isLoading = false;
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
    public PaginationScrollListener getPagination(LinearLayoutManager llm) {
        return new PaginationScrollListener(llm) {
            @Override
            protected void loadMoreItems() {
                getJobsList(true);
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        };
    }

    @Override
    public void setPageValue() {
        currentPage = 0;
    }

    @Override
    public void getJobsList(boolean append) {
        view.showProgress();

        mRetrofitClient.getClient().create(Api.class).getUsersList(PER_PAGE_ITEMS_COUNT, currentPage).enqueue(new Callback<UsersList>() {
            @Override
            public void onResponse(Call<UsersList> call, Response<UsersList> response) {
                parseResponse(response.body().getResults(), append);
            }

            @Override
            public void onFailure(Call<UsersList> call, Throwable t) {
                parseError(t);
            }
        });
    }

    @Override
    public void refresh() {
        setPageValue();
        getJobsList(false);
    }

    private void parseResponse(List<UserResp> resp, boolean append) {
        isLoading = false;
        if (resp != null) {
            modelsMapping(resp, append);
        }
        currentPage += 1;
    }


    private void modelsMapping(List<UserResp> userRespList, boolean append) {

        List<UserModel> userModels = new ArrayList<>();
        for (UserResp userResp : userRespList) {
            userModels.add(new UserModel()
                    .setUserName(userResp.getName().getFirst().concat(" ").concat(userResp.getName().getLast()))
                    .setUserAvatarMedium(userResp.getPicture().getMedium())
                    .setUserAvatarLarge(userResp.getPicture().getLarge())
                    .setUserGender(userResp.getGender())
                    .setUserDob(userResp.getDob().getDate())
                    .setUserEmail(userResp.getEmail())
                    .setUserPhone(userResp.getPhone())
                    .setUserPhoneCell(userResp.getCell()));

        }

        view.showJobsList(userModels, append);
    }
}

