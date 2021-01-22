package com.tc.kefan.mvp.presenter

import com.tc.kefan.base.BasePresenter
import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.ShareArticleContract
import com.tc.kefan.mvp.model.ShareArticleModel

/**
 * @author tucai
 * @date 2019/11/16
 * @desc
 */
class ShareArticlePresenter : BasePresenter<ShareArticleModel, ShareArticleContract.View>(), ShareArticleContract.Presenter {

    override fun createModel(): ShareArticleModel? = ShareArticleModel()

    override fun shareArticle() {
        val title = mView?.getArticleTitle().toString()
        val link = mView?.getArticleLink().toString()

        if (title.isEmpty()) {
            mView?.showMsg("文章标题不能为空")
            return
        }
        if (link.isEmpty()) {
            mView?.showMsg("文章链接不能为空")
            return
        }
        val map = mutableMapOf<String, Any>()
        map["title"] = title
        map["link"] = link
        mModel?.shareArticle(map)?.ss(mModel, mView, true) {
            mView?.showShareArticle(true)
        }

    }

}