package com.tc.kefan.mvp.presenter

import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.SquareContract
import com.tc.kefan.mvp.model.SquareModel

/**
 * @author tucai
 * @date 2019/11/16
 * @desc
 */
class SquarePresenter : CommonPresenter<SquareModel, SquareContract.View>(), SquareContract.Presenter {

    override fun createModel(): SquareModel? = SquareModel()

    override fun getSquareList(page: Int) {
        mModel?.getSquareList(page)?.ss(mModel, mView, page == 0) {
            mView?.showSquareList(it.data)
        }
    }

}