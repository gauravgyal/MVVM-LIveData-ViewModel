package com.gauravgoyal.mvvm_with_testing.service.model

/**
 * Created by gauravgoyal on 15/12/17.
 */

import android.databinding.BaseObservable
import android.databinding.ObservableField
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Article {
    @SerializedName("source")
    @Expose
    var source: Source? = null
    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null

}
