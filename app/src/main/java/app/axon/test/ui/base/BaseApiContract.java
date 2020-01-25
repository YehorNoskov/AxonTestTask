package app.axon.test.ui.base;

public class BaseApiContract extends BaseContract {

    public interface Presenter<V extends View> extends BaseContract.Presenter<V> {
        void subscribe();

        void unsubscribe();
    }
}
