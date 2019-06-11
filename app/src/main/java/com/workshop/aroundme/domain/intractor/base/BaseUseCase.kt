package com.workshop.aroundme.domain.intractor.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseUseCase {
    private val disposable = CompositeDisposable()
    fun add(d: Disposable) {
        disposable.add(d)
    }

    fun dispose() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}