package com.gauravgoyal.mvvm_with_testing.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.gauravgoyal.mvvm_with_testing.BuildConfig;
import com.gauravgoyal.mvvm_with_testing.service.model.News;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsRepository {
    private NewsService newsService;
    private static NewsRepository projectRepository;

    private NewsRepository() {
        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apiKey", BuildConfig.NewsApiKey)
                        .build();

                Request request = original.newBuilder()
                        .url(url).build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewsService.URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsService = retrofit.create(NewsService.class);
    }

    public synchronized static NewsRepository getInstance() {
        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = new NewsRepository();
            }
        }
        return projectRepository;
    }

    public LiveData<News> getNews(String source) {
        final MutableLiveData<News> data = new MutableLiveData<>();
        newsService.getNews(source).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}
