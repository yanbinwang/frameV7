package com.example.common.http.factory

import com.example.common.BuildConfig

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author: wyb
 * date: 2019/7/30.
 * retrofit单例
 */
class RetrofitFactory private constructor() {
    private val retrofit: Retrofit = Retrofit.Builder()
            .client(OkHttpFactory.getInstance().okHttpClient)
            .baseUrl(BuildConfig.LOCALHOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    companion object {
        private var instance: RetrofitFactory? = null

        @Synchronized
        fun getInstance(): RetrofitFactory {
            if (instance == null) {
                instance = RetrofitFactory()
            }
            return instance!!
        }
    }

    //获取一个请求API
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    //发起一个请求
    @Synchronized
    fun <T> subscribeWith(flowable: Flowable<T>, resourceSubscriber: ResourceSubscriber<T>): Disposable {
        return flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(resourceSubscriber)
    }

}