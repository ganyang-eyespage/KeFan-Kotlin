package com.tc.kefan.mvp.contract

import com.tc.kefan.base.IModel
import com.tc.kefan.base.IPresenter
import com.tc.kefan.base.IView
import com.tc.kefan.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author tucai
 * @date 2019/11/15
 * @desc
 */
interface ShareArticleContract {

    interface View : IView {
        fun getArticleTitle(): String
        fun getArticleLink(): String

        fun showShareArticle(success: Boolean)
    }

    interface Presenter : IPresenter<View> {
        fun shareArticle()
    }

    interface Model : IModel {
        fun shareArticle(map: MutableMap<String, Any>): Observable<HttpResult<Any>>
    }

}