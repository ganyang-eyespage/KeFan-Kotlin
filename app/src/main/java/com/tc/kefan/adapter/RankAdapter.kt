package com.tc.kefan.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tc.kefan.R
import com.tc.kefan.mvp.model.bean.CoinInfoBean

/**
 * @author tucai
 * @date 2019/9/5
 * @desc
 */
class RankAdapter : BaseQuickAdapter<CoinInfoBean, BaseViewHolder>(R.layout.item_rank_list) {
    override fun convert(helper: BaseViewHolder?, item: CoinInfoBean?) {
        helper ?: return
        item ?: return

        val index = helper.layoutPosition

        helper.setText(R.id.tv_username, item.username)
                .setText(R.id.tv_score, item.coinCount.toString())
                .setText(R.id.tv_ranking, (index + 1).toString())
    }
}