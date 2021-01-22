package com.tc.kefan.mvp.presenter

import com.tc.kefan.ext.ss
import com.tc.kefan.mvp.contract.HomeContract
import com.tc.kefan.mvp.model.HomeModel
import com.tc.kefan.mvp.model.bean.Article
import com.tc.kefan.mvp.model.bean.ArticleResponseBody
import com.tc.kefan.mvp.model.bean.HttpResult
import com.tc.kefan.utils.SettingUtil
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 * Created by tucai on 2018/4/21.
 */
class HomePresenter : CommonPresenter<HomeContract.Model, HomeContract.View>(), HomeContract.Presenter {

    override fun createModel(): HomeContract.Model? = HomeModel()

    override fun requestBanner() {
        mModel?.requestBanner()?.ss(mModel, mView, false) {
            mView?.setBanner(it.data)
        }
    }

    override fun requestArticles(num: Int) {
        mModel?.requestArticles(num)?.ss(mModel, mView, num == 0) {
            mView?.setArticles(it.data)
        }
    }

    override fun requestHomeData() {

        requestBanner()

        val observable = if (SettingUtil.getIsShowTopArticle()) {
            mModel?.requestArticles(0)
        } else {
            Observable.zip(mModel?.requestTopArticles(), mModel?.requestArticles(0),
                    BiFunction<HttpResult<MutableList<Article>>, HttpResult<ArticleResponseBody>,
                            HttpResult<ArticleResponseBody>> { t1, t2 ->
                        t1.data.forEach {
                            // 置顶数据中没有标识，手动添加一个标识
                            it.top = "1"
                        }
                        t2.data.datas.addAll(0, t1.data)
                        t2
                    })
        }
        observable?.ss(mModel, mView, false) {
            mView?.setArticles(it.data)
        }
    }

}