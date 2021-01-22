package com.tc.kefan.mvp.model

import com.tc.kefan.base.BaseModel
import com.tc.kefan.http.RetrofitHelper
import com.tc.kefan.mvp.contract.TCListContract
import com.tc.kefan.mvp.model.bean.BaseListResponseBody
import com.tc.kefan.mvp.model.bean.CoinInfoBean
import com.tc.kefan.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @Author : yanggan
 * @Date   : 2021/1/22 2:35 PM
 */
class TCListModel : BaseModel(), TCListContract.Model {
    override fun getRankList(page: Int): Observable<HttpResult<BaseListResponseBody<CoinInfoBean>>> {
        return RetrofitHelper.service.getRankList(page)
    }
}