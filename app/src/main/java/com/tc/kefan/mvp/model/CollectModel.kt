package com.tc.kefan.mvp.model

import com.tc.kefan.base.BaseModel
import com.tc.kefan.http.RetrofitHelper
import com.tc.kefan.mvp.contract.CollectContract
import com.tc.kefan.mvp.model.bean.CollectionArticle
import com.tc.kefan.mvp.model.bean.BaseListResponseBody
import com.tc.kefan.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * Created by tucai on 2018/6/9.
 */
class CollectModel : BaseModel(), CollectContract.Model {

    override fun getCollectList(page: Int): Observable<HttpResult<BaseListResponseBody<CollectionArticle>>> {
        return RetrofitHelper.service.getCollectList(page)
    }

    override fun removeCollectArticle(id: Int, originId: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.removeCollectArticle(id, originId)
    }

}