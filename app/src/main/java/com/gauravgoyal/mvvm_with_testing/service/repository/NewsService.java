package com.gauravgoyal.mvvm_with_testing.service.repository;

import com.gauravgoyal.mvvm_with_testing.service.model.News;
import com.gauravgoyal.mvvm_with_testing.utility.Constants;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface NewsService {
    String URL = Constants.Companion.getAPI_URL();

    @GET("top-headlines")
    Call<News> getNews(@Query("sources") String source);
}
