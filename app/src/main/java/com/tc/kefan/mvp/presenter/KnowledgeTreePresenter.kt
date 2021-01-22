package com.tc.kefan.mvp.presenter

import com.tc.kefan.base.BasePresenter
import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.KnowledgeTreeContract
import com.tc.kefan.mvp.model.KnowledgeTreeModel

/**
 * Created by tucai on 2018/5/8.
 */
class KnowledgeTreePresenter : BasePresenter<KnowledgeTreeContract.Model, KnowledgeTreeContract.View>(), KnowledgeTreeContract.Presenter {

    override fun createModel(): KnowledgeTreeContract.Model? = KnowledgeTreeModel()

    override fun requestKnowledgeTree() {
        mModel?.requestKnowledgeTree()?.ss(mModel, mView) {
            mView?.setKnowledgeTree(it.data)
        }
    }

}