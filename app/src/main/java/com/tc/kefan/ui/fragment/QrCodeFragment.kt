package com.tc.kefan.ui.fragment

import android.view.View
import com.tc.kefan.R
import com.tc.kefan.base.BaseFragment

/**
 * @author tucai
 * @date 2019/11/17
 * @desc 扫码下载
 */
class QrCodeFragment : BaseFragment() {

    companion object {
        fun getInstance(): QrCodeFragment = QrCodeFragment()
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_qr_code

    override fun initView(view: View) {
    }

    override fun lazyLoad() {
    }
}