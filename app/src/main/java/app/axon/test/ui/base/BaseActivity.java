package app.axon.test.ui.base;

import android.app.ProgressDialog;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import androidx.appcompat.app.AppCompatActivity;
import app.axon.test.R;
import app.axon.test.api.RetrofitClient;

public abstract class BaseActivity extends AppCompatActivity {

    private WeakReference<ProgressDialog> progressDialog;

    public RetrofitClient getRetrofitClient() {
        return new RetrofitClient(this);
    }

    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new WeakReference<>(new ProgressDialog(this));
            progressDialog.get().setTitle(getString(R.string.progress_title));
            progressDialog.get().setMessage(getString(R.string.progress_message));
            progressDialog.get().setCanceledOnTouchOutside(false);
            progressDialog.get().setCancelable(false);
        }

        progressDialog.get().show();
    }

    public void hideProgress() {
        if (this.isDestroyed() || this.isFinishing()) return;
        if (progressDialog != null && progressDialog.get() != null && progressDialog.get().isShowing()) {
            progressDialog.get().dismiss();
            progressDialog = null;
        }
    }

    public void showMessage(String message) {
        hideProgress();
        if (!this.isFinishing()) {
            Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    public void showMessage(int message) {
        hideProgress();
        if (!this.isFinishing()) {
            Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

}
