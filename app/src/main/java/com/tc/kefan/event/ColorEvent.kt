package com.tc.kefan.event

import com.tc.kefan.utils.SettingUtil

/**
 * Created by tucai on 2018/6/18.
 */
class ColorEvent(var isRefresh: Boolean, var color: Int = SettingUtil.getColor())