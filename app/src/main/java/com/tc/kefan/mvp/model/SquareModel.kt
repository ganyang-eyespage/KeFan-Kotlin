package com.tc.kefan.mvp.model

import com.tc.kefan.http.RetrofitHelper
import com.tc.kefan.mvp.contract.SquareContract
import com.tc.kefan.mvp.model.bean.ArticleResponseBody
import com.tc.kefan.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @author tucai
 * @date 2019/11/16
 * @desc
 */
class SquareModel : CommonModel(), SquareContract.Model {
    override fun getSquareList(page: Int): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getSquareList(page)
    }
}