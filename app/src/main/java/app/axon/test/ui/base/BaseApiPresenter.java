package app.axon.test.ui.base;

import android.text.TextUtils;

import androidx.annotation.StringRes;
import app.axon.test.R;
import app.axon.test.api.error.ErrorHelper;
import app.axon.test.utills.NoConnectivityException;
import retrofit2.HttpException;


public abstract class BaseApiPresenter {

    protected abstract BaseContract.View getView();

    protected void parseError(Throwable throwable) {

        if (throwable instanceof HttpException) {
            String error = ErrorHelper.parseSimpleError(throwable);
            if (TextUtils.isEmpty(error)) {
                notifyError(R.string.error_default);
            } else {
                notifyError(error);
            }
        } else if (throwable instanceof NoConnectivityException) {
            if (getView() != null) {
                getView().showSnackbar();
            }
        } else {
            notifyError(throwable.getMessage());
        }
    }

    private void notifyError(String msg) {
        if (getView() != null) {
            getView().showMessage(msg);
        }
    }

    private void notifyError(@StringRes int msg) {
        if (getView() != null) {
            getView().showMessage(msg);
        }
    }

}
