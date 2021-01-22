package com.tc.kefan.mvp.model

import com.tc.kefan.http.RetrofitHelper
import com.tc.kefan.mvp.contract.HomeContract
import com.tc.kefan.mvp.model.bean.Article
import com.tc.kefan.mvp.model.bean.ArticleResponseBody
import com.tc.kefan.mvp.model.bean.Banner
import com.tc.kefan.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * Created by tucai on 2018/4/21.
 */
class HomeModel : CommonModel(), HomeContract.Model {

    override fun requestBanner(): Observable<HttpResult<List<Banner>>> {
        return RetrofitHelper.service.getBanners()
    }

    override fun requestTopArticles(): Observable<HttpResult<MutableList<Article>>> {
        return RetrofitHelper.service.getTopArticles()
    }

    override fun requestArticles(num: Int): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getArticles(num)
    }

}