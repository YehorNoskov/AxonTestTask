package app.axon.test.ui.userlist;

import app.axon.test.ui.base.BaseContract;

public class UserListContract {

    interface View extends BaseContract.View {

    }

    public interface Presenter extends BaseContract.Presenter<View> {

        void getJobsList();
    }
}
