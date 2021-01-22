package com.tc.kefan.mvp.presenter

import com.tc.kefan.mvp.contract.ContentContract
import com.tc.kefan.mvp.model.ContentModel

/**
 * Created by tucai on 2018/6/10.
 */
class ContentPresenter : CommonPresenter<ContentContract.Model, ContentContract.View>(), ContentContract.Presenter {

    override fun createModel(): ContentContract.Model? = ContentModel()

}