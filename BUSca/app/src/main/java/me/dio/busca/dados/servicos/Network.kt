package me.dio.busca.dados.servicos

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.parcel.Parcelize
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Network {

    companion object {

        fun client(): OkHttpClient =
            OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

        fun gson(): Gson = GsonBuilder().create()


        fun retrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("https://aiko-olhovivo-proxy.aikodigital.io/")
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .client(client())
            .build()

        fun service(): EndPoint =
            retrofit().create(EndPoint::class.java)
    }

    }