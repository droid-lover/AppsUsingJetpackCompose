package com.nativemobilebits.loginflow

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

fun main() {
    val numbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)

    /**
     * How to define an Observable
     */

    val stringObservable: Observable<String> = Observable.just("Hello Message")


    val intObservable = Observable.just(
        1, 2, 3, 4, 5, 6,
        7, 8, 9, 10
    )

    val numberObservable = Observable.fromArray(numbers)

    intObservable.subscribeBy(
        onNext = {
            println("Coming_Inside_onNext $it")
        },
        onError = {
            println("Coming_Inside_onError ${it.message}")
        },
        onComplete = {
            println("Coming_Inside_onComplete")
        }
    )
}