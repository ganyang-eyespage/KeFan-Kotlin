package com.tc.kefan.mvp.model

import com.tc.kefan.base.BaseModel
import com.tc.kefan.http.RetrofitHelper
import com.tc.kefan.mvp.contract.ProjectContract
import com.tc.kefan.mvp.model.bean.HttpResult
import com.tc.kefan.mvp.model.bean.ProjectTreeBean
import io.reactivex.Observable

/**
 * Created by tucai on 2018/5/15.
 */
class ProjectModel : BaseModel(), ProjectContract.Model {

    override fun requestProjectTree(): Observable<HttpResult<List<ProjectTreeBean>>> {
        return RetrofitHelper.service.getProjectTree()
    }

}