package com.tc.kefan.mvp.presenter

import com.tc.kefan.base.BasePresenter
import com.tc.kefan.mvp.contract.SystemContract
import com.tc.kefan.mvp.model.SystemModel

/**
 * @author tucai
 * @date 2019/11/17
 * @desc
 */
class SystemPresenter : BasePresenter<SystemModel, SystemContract.View>(),
    SystemContract.Presenter {

    override fun createModel(): SystemModel? = SystemModel()

}