package com.tc.kefan.mvp.presenter

import com.tc.kefan.base.BasePresenter
import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.ProjectContract
import com.tc.kefan.mvp.model.ProjectModel

/**
 * Created by tucai on 2018/5/15.
 */
class ProjectPresenter : BasePresenter<ProjectContract.Model, ProjectContract.View>(), ProjectContract.Presenter {

    override fun createModel(): ProjectContract.Model? = ProjectModel()

    override fun requestProjectTree() {
        mModel?.requestProjectTree()?.ss(mModel, mView) {
            mView?.setProjectTree(it.data)
        }
    }

}