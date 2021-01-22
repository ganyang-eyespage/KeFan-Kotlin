package com.tc.kefan.mvp.model

import com.tc.kefan.base.BaseModel
import com.tc.kefan.http.RetrofitHelper
import com.tc.kefan.mvp.contract.ShareArticleContract
import com.tc.kefan.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author tucai
 * @date 2019/11/15
 * @desc
 */
class ShareArticleModel : BaseModel(), ShareArticleContract.Model {
    override fun shareArticle(map: MutableMap<String, Any>): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.shareArticle(map)
    }
}