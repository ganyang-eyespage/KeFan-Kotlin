package com.tc.kefan.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by tucai on 2018/4/21.
 */
class SingleMainScheduler<T> private constructor() : BaseScheduler<T>(Schedulers.single(), AndroidSchedulers.mainThread())
