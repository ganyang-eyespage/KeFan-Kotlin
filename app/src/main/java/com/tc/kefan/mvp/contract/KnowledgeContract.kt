package com.tc.kefan.mvp.contract

import com.tc.kefan.mvp.model.bean.ArticleResponseBody
import com.tc.kefan.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * Created by tucai on 2018/5/12.
 */
interface KnowledgeContract {

    interface View : CommonContract.View {

        fun scrollToTop()

        fun setKnowledgeList(articles: ArticleResponseBody)

    }

    interface Presenter : CommonContract.Presenter<View> {

        fun requestKnowledgeList(page: Int, cid: Int)

    }

    interface Model : CommonContract.Model {

        fun requestKnowledgeList(page: Int, cid: Int): Observable<HttpResult<ArticleResponseBody>>

    }

}