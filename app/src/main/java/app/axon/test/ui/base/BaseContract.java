package app.axon.test.ui.base;

import androidx.annotation.StringRes;

public class BaseContract {


    public interface Presenter<T> {
        void attach(T view);

    }

    public interface View {

        void showMessage(String message);

        void showMessage(@StringRes int text);

        void showProgress();

        void hideProgress();

        void showSnackbar();
    }

}