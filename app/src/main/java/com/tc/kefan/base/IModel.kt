package com.tc.kefan.base

import io.reactivex.disposables.Disposable

/**
 * Created by tucai on 2018/8/18.
 */
interface IModel {

    fun addDisposable(disposable: Disposable?)

    fun onDetach()

}