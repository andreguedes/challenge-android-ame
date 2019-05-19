package br.com.andreguedes.alodjinha.data.source

import br.com.andreguedes.alodjinha.ALodjinhaApplication
import br.com.andreguedes.alodjinha.BuildConfig
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaAPI
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitClient {

    private var retrofit: Retrofit? = null

    fun getClient(): ALodjinhaAPI {
        val cache = Cache(ALodjinhaApplication.getFileCacheDir(), 10 * 1024 * 1024)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (ALodjinhaApplication.isNetworkAvailable()) {
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
                } else {
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                }
                chain.proceed(request)
            }
            .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        }
        return retrofit!!.create(ALodjinhaAPI::class.java)
    }

}