package com.tc.kefan.mvp.contract

import com.tc.kefan.base.IModel
import com.tc.kefan.base.IPresenter
import com.tc.kefan.base.IView
import com.tc.kefan.mvp.model.bean.BaseListResponseBody
import com.tc.kefan.mvp.model.bean.CoinInfoBean
import com.tc.kefan.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * @Author : yanggan
 * @Date   : 2021/1/22 2:27 PM
 */
class TCListContract {

    interface View : IView {
        fun showRankList(body: BaseListResponseBody<CoinInfoBean>)
    }

    interface Presenter : IPresenter<View> {
        fun getRankList(page: Int)
    }

    interface Model : IModel {
        fun getRankList(page: Int): Observable<HttpResult<BaseListResponseBody<CoinInfoBean>>>
    }
}