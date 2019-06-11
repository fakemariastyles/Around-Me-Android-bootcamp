package com.workshop.aroundme.domain.executer

import io.reactivex.Scheduler

interface UseCaseExecuterThread {
    val scheduler: Scheduler
}