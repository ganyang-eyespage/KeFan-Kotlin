package com.tc.kefan.mvp.presenter

import com.tc.kefan.base.BasePresenter
import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.RankContract
import com.tc.kefan.mvp.model.RankModel

/**
 * @author tucai
 * @date 2019/9/5
 * @desc
 */
class RankPresenter : BasePresenter<RankContract.Model, RankContract.View>(), RankContract.Presenter {

    override fun createModel(): RankContract.Model? = RankModel()

    override fun getRankList(page: Int) {
        mModel?.getRankList(page)?.ss(mModel, mView) {
            mView?.showRankList(it.data)
        }
    }
}