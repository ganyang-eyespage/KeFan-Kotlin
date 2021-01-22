package com.tc.kefan.mvp.model

import com.tc.kefan.base.BaseModel
import com.tc.kefan.http.RetrofitHelper
import com.tc.kefan.mvp.contract.KnowledgeTreeContract
import com.tc.kefan.mvp.model.bean.HttpResult
import com.tc.kefan.mvp.model.bean.KnowledgeTreeBody
import io.reactivex.Observable

/**
 * Created by tucai on 2018/5/8.
 */
class KnowledgeTreeModel : BaseModel(), KnowledgeTreeContract.Model {

    override fun requestKnowledgeTree(): Observable<HttpResult<List<KnowledgeTreeBody>>> {
        return RetrofitHelper.service.getKnowledgeTree()
    }

}