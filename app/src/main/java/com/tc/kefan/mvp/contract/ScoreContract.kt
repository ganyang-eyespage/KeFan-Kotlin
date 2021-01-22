package com.tc.kefan.mvp.contract

import com.tc.kefan.base.IModel
import com.tc.kefan.base.IPresenter
import com.tc.kefan.base.IView
import com.tc.kefan.mvp.model.bean.BaseListResponseBody
import com.tc.kefan.mvp.model.bean.HttpResult
import com.tc.kefan.mvp.model.bean.UserScoreBean
import io.reactivex.Observable

/**
 * @author tucai
 * @date 2019/9/5
 * @desc
 */
interface ScoreContract {

    interface View : IView {
        fun showUserScoreList(body: BaseListResponseBody<UserScoreBean>)
    }

    interface Presenter : IPresenter<View> {
        fun getUserScoreList(page: Int)
    }

    interface Model : IModel {
        fun getUserScoreList(page: Int): Observable<HttpResult<BaseListResponseBody<UserScoreBean>>>
    }

}