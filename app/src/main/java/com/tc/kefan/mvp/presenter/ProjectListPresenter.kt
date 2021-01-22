package com.tc.kefan.mvp.presenter

import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.ProjectListContract
import com.tc.kefan.mvp.model.ProjectListModel

/**
 * Created by tucai on 2018/5/20.
 */
class ProjectListPresenter : CommonPresenter<ProjectListContract.Model, ProjectListContract.View>(), ProjectListContract.Presenter {

    override fun createModel(): ProjectListContract.Model? = ProjectListModel()

    override fun requestProjectList(page: Int, cid: Int) {
        mModel?.requestProjectList(page, cid)?.ss(mModel, mView, page == 1) {
            mView?.setProjectList(it.data)
        }
    }

}