package com.gauravgoyal.mvvm_with_testing.service.model

/**
 * Created by gauravgoyal on 15/12/17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Source {
    @SerializedName("id")
    @Expose
    private val id: String? = null
    @SerializedName("name")
    @Expose
    private val name: String? = null

}