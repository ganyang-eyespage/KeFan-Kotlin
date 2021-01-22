package com.tc.kefan.mvp.presenter

import com.tc.kefan.base.BasePresenter
import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.ScoreContract
import com.tc.kefan.mvp.model.ScoreModel

/**
 * @author tucai
 * @date 2019/9/5
 * @desc
 */
class ScorePresenter : BasePresenter<ScoreContract.Model, ScoreContract.View>(), ScoreContract.Presenter {

    override fun createModel(): ScoreContract.Model? = ScoreModel()

    override fun getUserScoreList(page: Int) {
        mModel?.getUserScoreList(page)?.ss(mModel, mView) {
            mView?.showUserScoreList(it.data)
        }
    }
}