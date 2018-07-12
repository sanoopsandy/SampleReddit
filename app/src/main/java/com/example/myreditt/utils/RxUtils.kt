package com.example.myreditt.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import com.example.myreditt.core.networking.DataResult
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}


fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}

/*
* Extension for Observable objects
* */
fun <T> Observable<T>.doOnBackOutOnMain(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

/*
* Extension for Flowable objects
* */
fun <T> Flowable<T>.doOnBackOutOnMain(): Flowable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

/*
* Extension for Completable objects
* */
fun Completable.doOnBack(): Completable {
    return this.subscribeOn(Schedulers.io())
}

/*
* Convert a Subject to liveData to observe in activity
* */
fun <T> PublishSubject<T>.toLiveData(compositeDisposable: CompositeDisposable): LiveData<T> {
    val data = MutableLiveData<T>()
    compositeDisposable.add(this.subscribe({ t: T -> data.value = t }))
    return data
}

/**
 * Function to handle loading logic and push the value forward
 * */
fun <T> PublishSubject<DataResult<T>>.failure(e: Throwable) {
    with(this) {
        loading(false)
        onNext(DataResult.failure(e))
    }
}

/**
 * Function to handle loading logic and push the value forward
 * */
fun <T> PublishSubject<DataResult<T>>.success(t: T) {
    with(this) {
        loading(false)
        onNext(DataResult.success(t))
    }
}

/**
 * Function to push the loading status to the observing outcome
 * */
fun <T> PublishSubject<DataResult<T>>.loading(isLoading: Boolean) {
    this.onNext(DataResult.loading(isLoading))
}

/**
 * Extension function to add a Disposable to a CompositeDisposable
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

/*
* Progress visibikity toggle
* */
fun ProgressBar.visibilityToggle(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}