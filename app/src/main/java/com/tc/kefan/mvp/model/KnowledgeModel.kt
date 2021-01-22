package com.tc.kefan.mvp.model

import com.tc.kefan.http.RetrofitHelper
import com.tc.kefan.mvp.contract.KnowledgeContract
import com.tc.kefan.mvp.model.bean.ArticleResponseBody
import com.tc.kefan.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * Created by tucai on 2018/5/12.
 */
class KnowledgeModel : CommonModel(), KnowledgeContract.Model {

    override fun requestKnowledgeList(page: Int, cid: Int): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getKnowledgeList(page, cid)
    }

}
