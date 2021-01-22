package com.tc.kefan.mvp.presenter

import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.SearchListContract
import com.tc.kefan.mvp.model.SearchListModel

class SearchListPresenter : CommonPresenter<SearchListContract.Model, SearchListContract.View>(), SearchListContract.Presenter {

    override fun createModel(): SearchListContract.Model? = SearchListModel()

    override fun queryBySearchKey(page: Int, key: String) {
        mModel?.queryBySearchKey(page, key)?.ss(mModel, mView, page == 0) {
            mView?.showArticles(it.data)
        }
    }

}