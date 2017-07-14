package com.pranay.kotlinretrofitapicall.rx

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Pranay on 7/15/2017.
 */
class RxAPICallHelper {

    fun <T> call(observable: Observable<T>?, rxAPICallback: RxAPICallback<T>?): Disposable {
        if (observable == null) {
            throw IllegalArgumentException("Observable must not be null.")
        }


        if (rxAPICallback == null) {
            throw IllegalArgumentException("Callback must not be null.")
        }

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t -> rxAPICallback.onSuccess(t) }, { throwable ->
                    rxAPICallback.onFailed(throwable)
                })

        // You can check Java code in bottom comment
        /*
        *
        * return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(@NonNull T t) throws Exception {
                        rxAPICallback.onSuccess(t);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (throwable != null) {
                            rxAPICallback.onFailed(throwable);
                        } else {
                            rxAPICallback.onFailed(new Exception("Error: Something went wrong in api call."));
                        }
                    }
                });
        * */



    }
}