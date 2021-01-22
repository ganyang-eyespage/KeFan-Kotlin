package com.tc.kefan.mvp.presenter

import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.KnowledgeContract
import com.tc.kefan.mvp.model.KnowledgeModel

/**
 * Created by tucai on 2018/5/12.
 */
class KnowledgePresenter : CommonPresenter<KnowledgeContract.Model, KnowledgeContract.View>(), KnowledgeContract.Presenter {

    override fun createModel(): KnowledgeContract.Model? = KnowledgeModel()

    override fun requestKnowledgeList(page: Int, cid: Int) {
        mModel?.requestKnowledgeList(page, cid)?.ss(mModel, mView) {
            mView?.setKnowledgeList(it.data)
        }
    }

}