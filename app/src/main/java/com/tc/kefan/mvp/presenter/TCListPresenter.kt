package com.tc.kefan.mvp.presenter

import com.tc.kefan.base.BasePresenter
import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.TCListContract
import com.tc.kefan.mvp.model.TCListModel

/**
 * @Author : yanggan
 * @Date   : 2021/1/22 2:34 PM
 */
class TCListPresenter : BasePresenter<TCListContract.Model, TCListContract.View>(), TCListContract.Presenter {

    override fun createModel(): TCListContract.Model? = TCListModel()

    override fun getRankList(page: Int) {
        mModel?.getRankList(page)?.ss(mModel, mView) {
            mView?.showRankList(it.data)
        }
    }
}