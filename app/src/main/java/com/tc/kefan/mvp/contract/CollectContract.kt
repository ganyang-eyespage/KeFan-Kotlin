package com.tc.kefan.mvp.contract

import com.tc.kefan.base.IModel
import com.tc.kefan.base.IPresenter
import com.tc.kefan.base.IView
import com.tc.kefan.mvp.model.bean.CollectionArticle
import com.tc.kefan.mvp.model.bean.BaseListResponseBody
import com.tc.kefan.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * Created by tucai on 2018/6/9.
 */
interface CollectContract {

    interface View : IView {

        fun setCollectList(articles: BaseListResponseBody<CollectionArticle>)

        fun showRemoveCollectSuccess(success: Boolean)

        fun scrollToTop()

    }

    interface Presenter : IPresenter<View> {

        fun getCollectList(page: Int)

        fun removeCollectArticle(id: Int, originId: Int)

    }

    interface Model : IModel {

        fun getCollectList(page: Int): Observable<HttpResult<BaseListResponseBody<CollectionArticle>>>

        fun removeCollectArticle(id: Int, originId: Int): Observable<HttpResult<Any>>

    }

}