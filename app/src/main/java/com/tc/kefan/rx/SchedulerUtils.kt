package com.tc.kefan.rx

import com.tc.kefan.rx.scheduler.IoMainScheduler

/**
 * Created by tucai on 2018/4/21.
 */
object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }

}