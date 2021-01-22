package com.tc.kefan.mvp.model

import com.tc.kefan.http.RetrofitHelper
import com.tc.kefan.mvp.contract.SearchListContract
import com.tc.kefan.mvp.model.bean.ArticleResponseBody
import com.tc.kefan.mvp.model.bean.HttpResult
import io.reactivex.Observable

class SearchListModel : CommonModel(), SearchListContract.Model {

    override fun queryBySearchKey(page: Int, key: String): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.queryBySearchKey(page, key)
    }

}