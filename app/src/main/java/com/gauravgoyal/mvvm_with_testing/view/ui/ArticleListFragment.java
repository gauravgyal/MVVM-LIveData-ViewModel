package com.gauravgoyal.mvvm_with_testing.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gauravgoyal.mvvm_with_testing.R;
import com.gauravgoyal.mvvm_with_testing.databinding.FragmentNewsListBinding;
import com.gauravgoyal.mvvm_with_testing.service.model.Article;
import com.gauravgoyal.mvvm_with_testing.service.model.News;
import com.gauravgoyal.mvvm_with_testing.view.adapter.NewsAdapter;
import com.gauravgoyal.mvvm_with_testing.view.callback.OnClickCallback;
import com.gauravgoyal.mvvm_with_testing.viewmodel.NewsViewModel;

import java.util.List;

public class ArticleListFragment extends Fragment {
    public static final String TAG = "ArticleListFragment";
    private NewsAdapter newsAdapter;
    private FragmentNewsListBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_list, container, false);

        newsAdapter = new NewsAdapter();
        binding.projectList.setAdapter(newsAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NewsViewModel.Factory factory = new NewsViewModel.Factory(
                getActivity().getApplication());

        final NewsViewModel viewModel = ViewModelProviders.of(this, factory)
                .get(NewsViewModel.class);

        binding.setIsLoading(true);

        observeViewModel(viewModel);
    }

    private void observeViewModel(NewsViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getObservableProject().observe(this, new Observer<News>() {
            @Override
            public void onChanged(@Nullable News news) {
                if (news != null) {
                    binding.setIsLoading(false);
                    newsAdapter.setProjectList(news.getArticles());
                }
            }
        });
    }
}
