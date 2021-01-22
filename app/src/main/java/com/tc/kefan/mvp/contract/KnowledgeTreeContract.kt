package com.tc.kefan.mvp.contract

import com.tc.kefan.base.IModel
import com.tc.kefan.base.IPresenter
import com.tc.kefan.base.IView
import com.tc.kefan.mvp.model.bean.HttpResult
import com.tc.kefan.mvp.model.bean.KnowledgeTreeBody
import io.reactivex.Observable

/**
 * Created by tucai on 2018/5/8.
 */
interface KnowledgeTreeContract {

    interface View : IView {

        fun scrollToTop()

        fun setKnowledgeTree(lists: List<KnowledgeTreeBody>)

    }

    interface Presenter : IPresenter<View> {

        fun requestKnowledgeTree()

    }

    interface Model : IModel {

        fun requestKnowledgeTree(): Observable<HttpResult<List<KnowledgeTreeBody>>>

    }

}