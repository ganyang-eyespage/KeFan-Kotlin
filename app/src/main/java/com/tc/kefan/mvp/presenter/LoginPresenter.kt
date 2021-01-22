package com.tc.kefan.mvp.presenter

import com.tc.kefan.base.BasePresenter
import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.LoginContract
import com.tc.kefan.mvp.model.LoginModel

/**
 * Created by tucai on 2018/5/27.
 */
class LoginPresenter : BasePresenter<LoginContract.Model, LoginContract.View>(), LoginContract.Presenter {

    override fun createModel(): LoginContract.Model? = LoginModel()

    override fun loginWanAndroid(username: String, password: String) {
        mModel?.loginWanAndroid(username, password)?.ss(mModel, mView) {
            mView?.loginSuccess(it.data)
        }
    }

}