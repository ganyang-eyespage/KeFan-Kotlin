package com.tc.kefan.mvp.contract

import com.tc.kefan.base.IModel
import com.tc.kefan.base.IPresenter
import com.tc.kefan.base.IView
import com.tc.kefan.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * Created by tucai on 2018/6/10.
 */
interface CommonContract {

    interface View : IView {

        fun showCollectSuccess(success: Boolean)

        fun showCancelCollectSuccess(success: Boolean)
    }

    interface Presenter<in V : View> : IPresenter<V> {

        fun addCollectArticle(id: Int)

        fun cancelCollectArticle(id: Int)

    }

    interface Model : IModel {

        fun addCollectArticle(id: Int): Observable<HttpResult<Any>>

        fun cancelCollectArticle(id: Int): Observable<HttpResult<Any>>

    }

}