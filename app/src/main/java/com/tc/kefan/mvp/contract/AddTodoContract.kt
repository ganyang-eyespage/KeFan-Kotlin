package com.tc.kefan.mvp.contract

import com.tc.kefan.base.IModel
import com.tc.kefan.base.IPresenter
import com.tc.kefan.base.IView
import com.tc.kefan.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * Created by tucai on 2018/8/11.
 */
interface AddTodoContract {

    interface View : IView {

        fun showAddTodo(success: Boolean)

        fun showUpdateTodo(success: Boolean)

        fun getType(): Int
        fun getCurrentDate(): String
        fun getTitle(): String
        fun getContent(): String
        fun getStatus(): Int
        fun getItemId(): Int
        fun getPriority(): String

    }

    interface Presenter : IPresenter<View> {

        fun addTodo()

        fun updateTodo(id: Int)

    }

    interface Model : IModel {

        fun addTodo(map: MutableMap<String, Any>): Observable<HttpResult<Any>>

        fun updateTodo(id: Int, map: MutableMap<String, Any>): Observable<HttpResult<Any>>

    }


}