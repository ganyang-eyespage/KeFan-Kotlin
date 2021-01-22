package com.tc.kefan.ui.fragment

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.tc.kefan.R
import com.tc.kefan.adapter.HomeAdapter
import com.tc.kefan.app.App
import com.tc.kefan.base.BaseMvpListFragment
import com.tc.kefan.constant.Constant
import com.tc.kefan.event.ColorEvent
import com.tc.kefan.ext.showSnackMsg
import com.tc.kefan.ext.showToast
import com.tc.kefan.mvp.contract.SearchListContract
import com.tc.kefan.mvp.model.bean.Article
import com.tc.kefan.mvp.model.bean.ArticleResponseBody
import com.tc.kefan.mvp.presenter.SearchListPresenter
import com.tc.kefan.ui.activity.ContentActivity
import com.tc.kefan.ui.activity.LoginActivity
import com.tc.kefan.utils.NetWorkUtil
import kotlinx.android.synthetic.main.fragment_refresh_layout.*
import kotlinx.android.synthetic.main.fragment_search_list.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SearchListFragment : BaseMvpListFragment<SearchListContract.View, SearchListContract.Presenter>(), SearchListContract.View {

    companion object {
        fun getInstance(bundle: Bundle): SearchListFragment {
            val fragment = SearchListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var mKey = ""

    /**
     * datas
     */
    private val datas = mutableListOf<Article>()

    /**
     * Adapter
     */
    private val mAdapter: HomeAdapter by lazy {
        HomeAdapter(activity, datas)
    }


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

    override fun attachLayoutRes(): Int = R.layout.fragment_search_list

    override fun createPresenter(): SearchListContract.Presenter = SearchListPresenter()

    override fun useEventBus(): Boolean = true

    override fun initView(view: View) {
        super.initView(view)

        mKey = arguments?.getString(Constant.SEARCH_KEY, "") ?: ""

        recyclerView.adapter = mAdapter

        mAdapter.run {
            setOnLoadMoreListener(onRequestLoadMoreListener, recyclerView)
            onItemClickListener = this@SearchListFragment.onItemClickListener
            onItemChildClickListener = this@SearchListFragment.onItemChildClickListener
            // setEmptyView(R.layout.fragment_empty_layout)
        }

        floating_action_btn.setOnClickListener {
            scrollToTop()
        }

    }

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.queryBySearchKey(0, mKey)
    }

    override fun onRefreshList() {
        mAdapter.setEnableLoadMore(false)
        mPresenter?.queryBySearchKey(0, mKey)
    }

    override fun onLoadMoreList() {
        val page = mAdapter.data.size / pageSize
        mPresenter?.queryBySearchKey(page, mKey)
    }

    override fun showCancelCollectSuccess(success: Boolean) {
        if (success) {
            showToast(getString(R.string.cancel_collect_success))
        }
    }

    override fun showCollectSuccess(success: Boolean) {
        if (success) {
            showToast(getString(R.string.collect_success))
        }
    }

    override fun showArticles(articles: ArticleResponseBody) {
        articles.datas.let {
            mAdapter.run {
                if (isRefresh) {
                    replaceData(it)
                } else {
                    addData(it)
                }
                pageSize = articles.size
                if (articles.over || articles.curPage == articles.pageCount) {
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

    override fun scrollToTop() {
        recyclerView.run {
            if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
                scrollToPosition(0)
            } else {
                smoothScrollToPosition(0)
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun refreshColor(event: ColorEvent) {
        if (event.isRefresh) {
            floating_action_btn.backgroundTintList = ColorStateList.valueOf(event.color)
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