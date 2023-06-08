package com.nativemobilebits.loginflow

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

fun main() {

    val compositeDisposable = CompositeDisposable()

    val numbers = arrayOf(1, 2, 3, 4, 5)
    val vowels = listOf<Char>('a', 'e', 'i', 'o', 'u')

    val stringObservable: Observable<String> = Observable.just("Hello Message")
    val intObservable = Observable.just(1, 2, 3, 4)
    val numberObservable = Observable.fromArray(numbers)
    val vowelsObservable = Observable.fromIterable(vowels)

    val customObservable = Observable.create { emitter ->
        emitter.onNext("Hello")
        emitter.onNext("World")
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(true)
        emitter.onComplete()
    }

    val rangeObservable = Observable.range(1, 10)


    compositeDisposable.add(
        rangeObservable.subscribeBy {
            println("Inside_rangeObservable $it")
        }
    )

    compositeDisposable.add(
        customObservable.subscribeBy {
            println("Inside_customObservable $it ")
        }
    )


    compositeDisposable.add(
        vowelsObservable.subscribeBy {
            println("Inside_vowelsObservable $it")
        }
    )

    compositeDisposable.add(
        intObservable.subscribeBy {
            println("Inside_intObservable $it")
        }
    )


    closeSubscriptions(compositeDisposable)

}

fun closeSubscriptions(compositeDisposable: CompositeDisposable){
    compositeDisposable.dispose()
    println("Inside_closeSubscriptions")
}