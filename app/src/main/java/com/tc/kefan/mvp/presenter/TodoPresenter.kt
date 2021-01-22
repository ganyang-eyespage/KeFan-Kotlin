package com.tc.kefan.mvp.presenter

import com.tc.kefan.base.BasePresenter
import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.TodoContract
import com.tc.kefan.mvp.model.TodoModel

/**
 * Created by tucai on 2018/8/7.
 */
class TodoPresenter : BasePresenter<TodoContract.Model, TodoContract.View>(), TodoContract.Presenter {

    override fun createModel(): TodoContract.Model? = TodoModel()

    override fun getAllTodoList(type: Int) {
        mModel?.getTodoList(type)?.ss(mModel, mView) {

        }
    }

    override fun getNoTodoList(page: Int, type: Int) {
        mModel?.getNoTodoList(page, type)?.ss(mModel, mView, page == 1) {
            mView?.showNoTodoList(it.data)
        }
    }

    override fun getDoneList(page: Int, type: Int) {
        mModel?.getDoneList(page, type)?.ss(mModel, mView, page == 1) {
            mView?.showNoTodoList(it.data)
        }
    }

    override fun deleteTodoById(id: Int) {
        mModel?.deleteTodoById(id)?.ss(mModel, mView) {
            mView?.showDeleteSuccess(true)
        }
    }

    override fun updateTodoById(id: Int, status: Int) {
        mModel?.updateTodoById(id, status)?.ss(mModel, mView) {
            mView?.showUpdateSuccess(true)
        }
    }

}