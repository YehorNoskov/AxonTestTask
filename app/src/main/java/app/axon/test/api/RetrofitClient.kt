package app.axon.test.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    val client: Retrofit
        get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideOkHttpClient())
                .build()

    private fun provideOkHttpClient(): OkHttpClient {
        val interceptorLog = HttpLoggingInterceptor()
        interceptorLog.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClientBuilder = OkHttpClient.Builder()
                .addInterceptor(interceptorLog)
        return okHttpClientBuilder.build()
    }

    companion object {
        private const val BASE_URL = "https://randomuser.me/"
    }

}