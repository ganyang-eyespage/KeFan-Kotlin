package com.tc.kefan.mvp.presenter

import com.tc.kefan.base.BasePresenter
import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.NavigationContract
import com.tc.kefan.mvp.model.NavigationModel

/**
 * Created by tucai on 2018/5/13.
 */
class NavigationPresenter : BasePresenter<NavigationContract.Model, NavigationContract.View>(), NavigationContract.Presenter {

    override fun createModel(): NavigationContract.Model? = NavigationModel()

    override fun requestNavigationList() {
        mModel?.requestNavigationList()?.ss(mModel, mView) {
            mView?.setNavigationData(it.data)
        }
    }

}