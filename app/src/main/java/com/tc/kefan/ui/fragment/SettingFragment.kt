package com.tc.kefan.ui.fragment

import android.os.Bundle
import android.view.View
import com.tc.kefan.R
import com.tc.kefan.base.BaseFragment

/**
 * Created by tucai on 2018/6/10.
 */
class SettingFragment : BaseFragment() {

    companion object {
        fun getInstance(bundle: Bundle): SettingFragment {
            val fragment = SettingFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_setting

    override fun initView(view: View) {
    }

    override fun lazyLoad() {
    }
}