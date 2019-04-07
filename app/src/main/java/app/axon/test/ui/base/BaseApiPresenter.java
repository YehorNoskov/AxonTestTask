package app.axon.test.ui.base;

public abstract class BaseApiPresenter {

    protected abstract BaseContract.View getView();

    protected void parseError(Throwable throwable) {

    }

}
