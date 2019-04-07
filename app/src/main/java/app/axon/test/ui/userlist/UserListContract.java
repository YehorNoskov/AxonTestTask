package app.axon.test.ui.userlist;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import app.axon.test.ui.base.BaseContract;
import app.axon.test.ui.base.PaginationScrollListener;

public class UserListContract {

    interface View extends BaseContract.View {

        void showJobsList(List<UserModel> userModelList, boolean append);

    }

    public interface Presenter extends BaseContract.Presenter<View> {

        PaginationScrollListener getPagination(LinearLayoutManager llm);

        void setPageValue();

        void getJobsList(boolean append);

        void refresh();
    }
}
