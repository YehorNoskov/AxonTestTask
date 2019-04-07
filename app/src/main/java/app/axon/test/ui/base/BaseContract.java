package app.axon.test.ui.base;

public class BaseContract {


    public interface Presenter<T> {
        void attach(T view);

    }

    public interface View {

    }

}