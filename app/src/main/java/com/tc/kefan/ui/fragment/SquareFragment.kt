package com.tc.kefan.ui.fragment

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.tc.kefan.R
import com.tc.kefan.adapter.HomeAdapter
import com.tc.kefan.app.App
import com.tc.kefan.base.BaseMvpListFragment
import com.tc.kefan.constant.Constant
import com.tc.kefan.event.RefreshShareEvent
import com.tc.kefan.ext.showSnackMsg
import com.tc.kefan.ext.showToast
import com.tc.kefan.mvp.contract.SquareContract
import com.tc.kefan.mvp.model.bean.Article
import com.tc.kefan.mvp.model.bean.ArticleResponseBody
import com.tc.kefan.mvp.presenter.SquarePresenter
import com.tc.kefan.ui.activity.CommonActivity
import com.tc.kefan.ui.activity.ContentActivity
import com.tc.kefan.ui.activity.LoginActivity
import com.tc.kefan.utils.NetWorkUtil
import kotlinx.android.synthetic.main.fragment_refresh_layout.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author tucai
 * @date 2019/11/16
 * @desc 广场
 */
class SquareFragment : BaseMvpListFragment<SquareContract.View, SquarePresenter>(), SquareContract.View {

    companion object {
        fun getInstance(): SquareFragment = SquareFragment()
    }

    private val datas = mutableListOf<Article>()

    private val mAdapter: HomeAdapter by lazy {
        HomeAdapter(activity, datas)
    }

    override fun createPresenter(): SquarePresenter = SquarePresenter()

    override fun attachLayoutRes(): Int = R.layout.fragment_square

    override fun useEventBus(): Boolean = true

    override fun hideLoading() {
        super.hideLoading()
        if (isRefresh) {
            mAdapter.setEnableLoadMore(true)
        }
    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        if (isRefresh) {
            mAdapter.setEnableLoadMore(true)
        } else {
            mAdapter.loadMoreFail()
        }
    }

    override fun initView(view: View) {
        setHasOptionsMenu(true)
        super.initView(view)

        recyclerView.adapter = mAdapter

        mAdapter.run {
            bindToRecyclerView(recyclerView)
            setOnLoadMoreListener(onRequestLoadMoreListener, recyclerView)
            onItemClickListener = this@SquareFragment.onItemClickListener
            onItemChildClickListener = this@SquareFragment.onItemChildClickListener
        }
    }

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.getSquareList(0)
    }

    override fun onRefreshList() {
        mAdapter.setEnableLoadMore(false)
        mPresenter?.getSquareList(0)
    }

    override fun onLoadMoreList() {
        val page = mAdapter.data.size / pageSize
        mPresenter?.getSquareList(page)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRefreshShare(event: RefreshShareEvent) {
        if (event.isRefresh) {
            lazyLoad()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_square, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_add -> {
                Intent(activity, CommonActivity::class.java).run {
                    putExtra(Constant.TYPE_KEY, Constant.Type.SHARE_ARTICLE_TYPE_KEY)
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun scrollToTop() {
        recyclerView.run {
            if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
                scrollToPosition(0)
            } else {
                smoothScrollToPosition(0)
            }
        }
    }

    override fun showSquareList(body: ArticleResponseBody) {
        body.datas.let {
            mAdapter.run {
                if (isRefresh) {
                    replaceData(it)
                } else {
                    addData(it)
                }
                pageSize = body.size
                if (body.over) {
                    loadMoreEnd(isRefresh)
                } else {
                    loadMoreComplete()
                }
            }
        }
        if (mAdapter.data.isEmpty()) {
            mLayoutStatusView?.showEmpty()
        } else {
            mLayoutStatusView?.showContent()
        }
    }

    override fun showCollectSuccess(success: Boolean) {
        if (success) {
            showToast(resources.getString(R.string.collect_success))
        }
    }

    override fun showCancelCollectSuccess(success: Boolean) {
        if (success) {
            showToast(resources.getString(R.string.cancel_collect_success))
        }
    }

    /**
     * ItemClickListener
     */
    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
        if (datas.size != 0) {
            val data = datas[position]
            ContentActivity.start(activity, data.id, data.title, data.link)
        }
    }

    /**
     * ItemChildClickListener
     */
    private val onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
                if (datas.size != 0) {
                    val data = datas[position]
                    when (view.id) {
                        R.id.iv_like -> {
                            if (isLogin) {
                                if (!NetWorkUtil.isNetworkAvailable(App.context)) {
                                    showSnackMsg(resources.getString(R.string.no_network))
                                    return@OnItemChildClickListener
                                }
                                val collect = data.collect
                                data.collect = !collect
                                mAdapter.setData(position, data)
                                if (collect) {
                                    mPresenter?.cancelCollectArticle(data.id)
                                } else {
                                    mPresenter?.addCollectArticle(data.id)
                                }
                            } else {
                                Intent(activity, LoginActivity::class.java).run {
                                    startActivity(this)
                                }
                                showToast(resources.getString(R.string.login_tint))
                            }
                        }
                    }
                }
            }

}