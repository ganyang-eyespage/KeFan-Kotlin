package com.tc.kefan.ui.activity

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.tc.kefan.R
import com.tc.kefan.adapter.TCListAdapter
import com.tc.kefan.base.BaseMvpActivity
import com.tc.kefan.ext.showToast
import com.tc.kefan.mvp.contract.TCListContract
import com.tc.kefan.mvp.model.bean.BaseListResponseBody
import com.tc.kefan.mvp.model.bean.CoinInfoBean
import com.tc.kefan.mvp.presenter.TCListPresenter
import com.tc.kefan.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_refresh_layout.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * @Author : yanggan
 * @Date   : 2021/1/22 2:26 PM
 */
class TCListActivity : BaseMvpActivity<TCListContract.View, TCListContract.Presenter>(),TCListContract.View{

    /**
     * 每页数据的个数
     */
    private var pageSize = 20;

    private var datas = mutableListOf<CoinInfoBean>()

    /**
     * RecyclerView Divider
     */
    private val recyclerViewItemDecoration by lazy {
        SpaceItemDecoration(this)
    }

    private val mAdapter: TCListAdapter by lazy {
        TCListAdapter()
    }

    /**
     * is Refresh
     */
    private var isRefresh = true


    override fun createPresenter(): TCListContract.Presenter = TCListPresenter()

    override fun attachLayoutRes(): Int = R.layout.activity_tc_list_demo

    override fun showLoading() {
        // swipeRefreshLayout.isRefreshing = isRefresh
    }

    override fun hideLoading() {
        swipeRefreshLayout?.isRefreshing = false
        if (isRefresh) {
            mAdapter.setEnableLoadMore(true)
        }
    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        mLayoutStatusView?.showError()
        if (isRefresh) {
            mAdapter.setEnableLoadMore(true)
        } else {
            mAdapter.loadMoreFail()
        }
    }

    override fun initData() {
    }

    override fun initView() {
        super.initView()
        mLayoutStatusView = multiple_status_view
        toolbar.run {
            title = getString(R.string.score_list)
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        swipeRefreshLayout.run {
            setOnRefreshListener(onRefreshListener)
        }
        recyclerView.adapter = mAdapter
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@TCListActivity)
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(recyclerViewItemDecoration)
        }
        mAdapter.run {
            bindToRecyclerView(recyclerView)
            setOnLoadMoreListener(onRequestLoadMoreListener, recyclerView)
            onItemClickListener = this@TCListActivity.onItemClickListener
            onItemChildClickListener = this@TCListActivity.onItemChildClickListener
        }
    }


    override fun start() {
        mLayoutStatusView?.showLoading()
        mPresenter?.getRankList(1)
    }

    override fun showRankList(body: BaseListResponseBody<CoinInfoBean>) {
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
        datas = mAdapter.data
        if (mAdapter.data.isEmpty()) {
            mLayoutStatusView?.showEmpty()
        } else {
            mLayoutStatusView?.showContent()
        }
    }

    /**
     * RefreshListener
     */
    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        isRefresh = true
        mAdapter.setEnableLoadMore(false)
        mPresenter?.getRankList(1)
    }
    /**
     * LoadMoreListener
     */
    private val onRequestLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {
        isRefresh = false
        swipeRefreshLayout.isRefreshing = false
        val page = mAdapter.data.size / pageSize + 1
        mPresenter?.getRankList(page)
    }

    /**
     * ItemClickListener
     */
    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
        if (datas.size != 0) {
            val data = datas[position]
            showToast(data.username)
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
                        R.id.tv_score -> {
                            var count = data.coinCount
                            showToast("score is $count")
                        }
                    }
                }
            }

}