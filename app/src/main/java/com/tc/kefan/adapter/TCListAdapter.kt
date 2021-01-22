package com.tc.kefan.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tc.kefan.R
import com.tc.kefan.mvp.model.bean.CoinInfoBean

/**
 * @Author : yanggan
 * @Date   : 2021/1/22 2:31 PM
 */
class TCListAdapter : BaseQuickAdapter<CoinInfoBean, BaseViewHolder>(R.layout.item_tc_list_demo) {
    override fun convert(helper: BaseViewHolder?, item: CoinInfoBean?) {
        helper ?: return
        item ?: return

        val index = helper.layoutPosition

        helper.setText(R.id.tv_username, item.username)
                .setText(R.id.tv_score, item.coinCount.toString())
                .addOnClickListener(R.id.tv_score)
                .setText(R.id.tv_ranking, (index + 1).toString())
    }
}