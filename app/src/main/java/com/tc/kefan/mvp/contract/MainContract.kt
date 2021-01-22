package com.tc.kefan.mvp.contract

import com.tc.kefan.base.IModel
import com.tc.kefan.base.IPresenter
import com.tc.kefan.base.IView
import com.tc.kefan.mvp.model.bean.HttpResult
import com.tc.kefan.mvp.model.bean.UserInfoBody
import io.reactivex.Observable

/**
 * @author tucai
 * @date 2018/8/30
 * @desc
 */
interface MainContract {

    interface View : IView {
        fun showLogoutSuccess(success: Boolean)
        fun showUserInfo(bean: UserInfoBody)
    }

    interface Presenter : IPresenter<View> {
        fun logout()
        fun getUserInfo()
    }

    interface Model : IModel {
        fun logout(): Observable<HttpResult<Any>>
        fun getUserInfo(): Observable<HttpResult<UserInfoBody>>
    }

}