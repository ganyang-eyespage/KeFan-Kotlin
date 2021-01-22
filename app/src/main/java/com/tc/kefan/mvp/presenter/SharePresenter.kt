package com.tc.kefan.mvp.presenter

import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.ShareContract
import com.tc.kefan.mvp.model.ShareModel

/**
 * @author tucai
 * @date 2019/11/15
 * @desc
 */
class SharePresenter : CommonPresenter<ShareModel, ShareContract.View>(), ShareContract.Presenter {

    override fun createModel(): ShareModel? = ShareModel()

    override fun getShareList(page: Int) {
        mModel?.getShareList(page)?.ss(mModel, mView, page == 1) {
            mView?.showShareList(it.data)
        }
    }

    override fun deleteShareArticle(id: Int) {
        mModel?.deleteShareArticle(id)?.ss(mModel, mView, true) {
            mView?.showDeleteArticle(true)
        }
    }

}