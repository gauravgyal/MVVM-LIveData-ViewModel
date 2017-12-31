package com.gauravgoyal.mvvm_with_testing.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.gauravgoyal.mvvm_with_testing.service.model.News;
import com.gauravgoyal.mvvm_with_testing.service.repository.NewsRepository;

public class NewsViewModel extends AndroidViewModel {
    private final LiveData<News> newsLiveData;

    public ObservableField<News> news = new ObservableField<>();

    public NewsViewModel(@NonNull Application application) {
        super(application);
        // a differnt source can be passed, here i am passing techcrunch
        newsLiveData = NewsRepository.getInstance().getNews("techcrunch");
    }

    public LiveData<News> getObservableProject() {
        return newsLiveData;
    }

    public void setNews(News news) {
        this.news.set(news);
    }

    /**
     * A creator is used to inject the project ID into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new NewsViewModel(application);
        }
    }
}
