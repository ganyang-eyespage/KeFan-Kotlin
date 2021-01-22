package com.tc.kefan.mvp.model

import com.tc.kefan.http.RetrofitHelper
import com.tc.kefan.mvp.contract.ShareContract
import com.tc.kefan.mvp.model.bean.HttpResult
import com.tc.kefan.mvp.model.bean.ShareResponseBody
import io.reactivex.Observable

/**
 * @author tucai
 * @date 2019/11/15
 * @desc
 */
class ShareModel : CommonModel(), ShareContract.Model {
    override fun getShareList(page: Int): Observable<HttpResult<ShareResponseBody>> {
        return RetrofitHelper.service.getShareList(page)
    }

    override fun deleteShareArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.deleteShareArticle(id)
    }
}