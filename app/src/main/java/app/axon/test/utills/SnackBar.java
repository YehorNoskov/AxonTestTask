package app.axon.test.utills;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import app.axon.test.R;

public class SnackBar {

    public static void showSnackBar(View view){
        Context context = view.getContext();
        Snackbar.make(view, context.getString(R.string.message_no_internet), Snackbar.LENGTH_LONG)
                .setAction(context.getString(R.string.title_wireless_settings), view1 -> {
                    Intent myIntent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    context.startActivity(myIntent);
                }).show();

    }
}
