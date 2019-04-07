package app.axon.test.api;

import android.content.Context;

import app.axon.test.utills.ConnectivityInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private Context mContext;
    private static final String BASE_URL = "https://randomuser.me/";


    public RetrofitClient(Context context) {
        mContext = context;
    }

    public Retrofit getClient() {

            return  new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(provideOkHttpClient())
                    .build();
    }

    private OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptorLog = new HttpLoggingInterceptor();
        interceptorLog.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(interceptorLog)
                .addInterceptor(new ConnectivityInterceptor(mContext));

        return okHttpClientBuilder.build();
    }
}
