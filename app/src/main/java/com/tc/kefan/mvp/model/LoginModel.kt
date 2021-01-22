package com.tc.kefan.mvp.model

import com.tc.kefan.base.BaseModel
import com.tc.kefan.http.RetrofitHelper
import com.tc.kefan.mvp.contract.LoginContract
import com.tc.kefan.mvp.model.bean.HttpResult
import com.tc.kefan.mvp.model.bean.LoginData
import io.reactivex.Observable

/**
 * Created by tucai on 2018/5/27.
 */
class LoginModel : BaseModel(), LoginContract.Model {

    override fun loginWanAndroid(username: String, password: String): Observable<HttpResult<LoginData>> {
        return RetrofitHelper.service.loginWanAndroid(username, password)
    }

}