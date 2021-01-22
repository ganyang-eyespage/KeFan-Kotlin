package com.tc.kefan.mvp.contract

import com.tc.kefan.base.IModel
import com.tc.kefan.base.IPresenter
import com.tc.kefan.base.IView

/**
 * @author tucai
 * @date 2019/11/17
 * @desc
 */
interface SystemContract {

    interface View : IView {
        fun scrollToTop()
    }

    interface Presenter : IPresenter<View> {

    }

    interface Model : IModel {

    }

}