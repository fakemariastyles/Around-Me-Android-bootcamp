package com.workshop.aroundme.domain.executer

import io.reactivex.Scheduler

interface PostExecuterThread {
    val scheduler: Scheduler
}