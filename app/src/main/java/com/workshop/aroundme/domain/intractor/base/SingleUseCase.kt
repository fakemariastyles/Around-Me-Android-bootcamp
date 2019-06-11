package com.workshop.aroundme.domain.intractor.base

import com.workshop.aroundme.domain.executer.PostExecuterThread
import com.workshop.aroundme.domain.executer.UseCaseExecuterThread
import io.reactivex.Single

abstract class SingleUseCase<in Params, Result>
    (
    private val postExecuterThread: PostExecuterThread
    , private val useCaseExecuterThread: UseCaseExecuterThread
) : BaseUseCase() {
    abstract fun buildSingle(params: Params): Single<Result>
    fun execute(
        params: Params
        , success: (Result) -> Unit
        , failure: (Throwable) -> Unit
    ) {
        buildSingle(params)
            .subscribeOn(useCaseExecuterThread.scheduler)
            .observeOn(postExecuterThread.scheduler)
            .subscribe({
                success(it)
            }, {
                failure(it)
            }).also {
                add(it)
            }
    }

}