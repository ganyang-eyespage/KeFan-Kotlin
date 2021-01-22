package com.tc.kefan.mvp.contract

/**
 * Created by tucai on 2018/6/10.
 */
interface ContentContract {

    interface View : CommonContract.View {

    }

    interface Presenter : CommonContract.Presenter<View> {

    }

    interface Model : CommonContract.Model {

    }

}