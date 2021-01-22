package com.tc.kefan.mvp.contract

import com.tc.kefan.base.IModel
import com.tc.kefan.base.IPresenter
import com.tc.kefan.base.IView
import com.tc.kefan.mvp.model.bean.HttpResult
import com.tc.kefan.mvp.model.bean.LoginData
import io.reactivex.Observable

/**
 * Created by tucai on 2018/5/27.
 */
interface LoginContract {

    interface View : IView {

        fun loginSuccess(data: LoginData)

        fun loginFail()

    }

    interface Presenter : IPresenter<View> {

        fun loginWanAndroid(username: String, password: String)

    }

    interface Model : IModel {

        fun loginWanAndroid(username: String, password: String): Observable<HttpResult<LoginData>>

    }

}