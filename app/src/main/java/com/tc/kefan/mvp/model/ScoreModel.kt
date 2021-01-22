package com.tc.kefan.mvp.model

import com.tc.kefan.base.BaseModel
import com.tc.kefan.http.RetrofitHelper
import com.tc.kefan.mvp.contract.ScoreContract
import com.tc.kefan.mvp.model.bean.BaseListResponseBody
import com.tc.kefan.mvp.model.bean.HttpResult
import com.tc.kefan.mvp.model.bean.UserScoreBean
import io.reactivex.Observable

/**
 * @author tucai
 * @date 2019/9/5
 * @desc
 */
class ScoreModel : BaseModel(), ScoreContract.Model {
    override fun getUserScoreList(page: Int): Observable<HttpResult<BaseListResponseBody<UserScoreBean>>> {
        return RetrofitHelper.service.getUserScoreList(page)
    }
}